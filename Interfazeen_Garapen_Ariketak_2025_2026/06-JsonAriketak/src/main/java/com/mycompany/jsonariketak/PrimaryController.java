package com.mycompany.jsonariketak;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Pertsona;

public class PrimaryController implements Initializable {

    @FXML
    private TableView<Pertsona> tblPertsonak;
    @FXML
    private TableColumn<Pertsona, String> colIzena;
    @FXML
    private TableColumn<Pertsona, String> colAbizena;
    @FXML
    private TableColumn<Pertsona, Integer> colAdina;
    @FXML
    private Button btnAddPerson;
    @FXML
    private Button btnDeletePerson;
    @FXML
    private Button btnChangePerson;
    @FXML
    private Button btnIrekiKarpeta;
    @FXML
    private Button btnGordeKarpetan;

    private final Gson gson = new Gson();
    private ObservableList<Pertsona> pertsonakList;
    String aukeratutakoAukera;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPertsons();
    }

    private void loadPertsons() {
        try (InputStream pertsonakJson = getClass().getResourceAsStream("/json/pertsonak.json")) {
            if (pertsonakJson == null) {
                System.err.println("Ez da aurkitu pertsonak.json");
                return;
            }
            java.lang.reflect.Type pertsonakListaTipoa = new TypeToken<List<Pertsona>>() {
            }.getType();
            List<Pertsona> pertsonak = gson.fromJson(new InputStreamReader(pertsonakJson), pertsonakListaTipoa);

            pertsonakList = FXCollections.observableList(pertsonak);

            colIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
            colAbizena.setCellValueFactory(new PropertyValueFactory<>("abizena"));
            colAdina.setCellValueFactory(new PropertyValueFactory<>("adina"));

            tblPertsonak.setItems(pertsonakList);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void SwitchToAddPerson(ActionEvent event) throws IOException {
        aukeratutakoAukera = "new";
        changeWindow(null);
    }

    @FXML
    private void SwitchToChangePerson(ActionEvent event) throws IOException {
        aukeratutakoAukera = "update";
        Pertsona p = this.tblPertsonak.getSelectionModel().getSelectedItem();
        if (p != null) {
            changeWindow(p);
        }
    }

    @FXML
    private void SwitchToDeletePerson(ActionEvent event) throws IOException {
        Pertsona aukeratua = tblPertsonak.getSelectionModel().getSelectedItem();

        if (aukeratua == null) {
            error("Ez dago pertsonarik hautatuta.");
            return;
        }
        pertsonakList.remove(aukeratua); // elimina de la lista observable
        tblPertsonak.refresh(); // refresca la tabla
    }

    private void changeWindow(Pertsona p) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();
        SecondaryController controlador = loader.getController();

        if (aukeratutakoAukera.equals("new")) {
            controlador.initAttributes(pertsonakList);
        } else if (aukeratutakoAukera.equals("update") && p != null) {
            controlador.initAttributes(pertsonakList, p);
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        // CORRECCIÓN PRINCIPAL: Actualizar la tabla después de cerrar la ventana
        if (controlador.getRespuestaList() != null) {
            pertsonakList.clear();
            pertsonakList.addAll(controlador.getRespuestaList());
            tblPertsonak.refresh();
        }
    }

    private void error(String errorMsg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(errorMsg);
        alert.showAndWait();
    }

    @FXML
    private void btnIrekiKarpeta(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Aukeratu JSON fitxategia");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON fitxategiak", "*.json")
        );

        File file = fileChooser.showOpenDialog(((Stage) btnIrekiKarpeta.getScene().getWindow()));

        if (file != null) {
            try (FileReader reader = new FileReader(file)) {
                java.lang.reflect.Type tipo = new TypeToken<List<Pertsona>>() {
                }.getType();
                List<Pertsona> listaPertsonak = gson.fromJson(reader, tipo);

                pertsonakList = FXCollections.observableList(listaPertsonak);
                tblPertsonak.setItems(pertsonakList);
                tblPertsonak.refresh();

                info("Fitxategia ondo kargatu da: " + file.getName());
            } catch (Exception e) {
                error("Errorea fitxategia irakurtzean: " + e.getMessage());
            }
        } else {
            info("Ez da fitxategirik aukeratu.");
        }
    }

    @FXML
    private void btnGordeKarpetan(ActionEvent event) {
        if (pertsonakList == null || pertsonakList.isEmpty()) {
            error("Ez dago daturik gordetzeko.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Gorde JSON fitxategia");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON fitxategiak", "*.json")
        );
        fileChooser.setInitialFileName("pertsonak.json");

        File selectedFile = fileChooser.showSaveDialog(((Stage) btnGordeKarpetan.getScene().getWindow()));

        if (selectedFile != null) {
            try (FileWriter fw = new FileWriter(selectedFile, false)) {
                String jsonString = gson.toJson(pertsonakList);
                fw.write(jsonString);
                info("Fitxategia ondo gorde da: " + selectedFile.getAbsolutePath());
            } catch (IOException e) {
                error("Errorea fitxategia gordetzean: " + e.getMessage());
            }
        } else {
            info("Ez da karpetarik aukeratu.");
        }
    }

    private void info(String infoMsg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información");
        alert.setContentText(infoMsg);
        alert.showAndWait();
    }
}
