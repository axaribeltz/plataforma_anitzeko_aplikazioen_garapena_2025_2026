package com.mycompany.jsonariketak;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    private TableColumn<Pertsona, String> colUrteak;
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

    private ObservableList<Pertsona> pertsonak = FXCollections.observableArrayList(); // Inicializar la lista
    String aukeratutakoAukera;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPertsons();
    }

    private void loadPertsons() {
        try {
            String filePath = "C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Interfazeen_Garapen_Ariketak_2025_2026\\06-JsonAriketak\\src\\main\\resources\\json\\pertsonak.json";
            Gson gson = new Gson();

            java.lang.reflect.Type pertsonaListaTipoa = new TypeToken<List<Pertsona>>() {
            }.getType();
            List<Pertsona> pertsonaList = gson.fromJson(new FileReader(filePath), pertsonaListaTipoa); // Primero obtenemos la lista normal
            pertsonak = FXCollections.observableArrayList(pertsonaList); // Luego la convertimos a ObservableList

            colIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
            colAbizena.setCellValueFactory(new PropertyValueFactory<>("abizena"));
            colUrteak.setCellValueFactory(new PropertyValueFactory<>("adina"));
            tblPertsonak.setItems(pertsonak); 

            for (Pertsona b : pertsonak) {
                MenuItem item = new MenuItem(b.getIzena() + " " + b.getAbizena());
                item.setOnAction(e -> showPetsonData(b));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPetsonData(Pertsona b) {
        colIzena.setText(b.getIzena());
        colAbizena.setText(b.getAbizena());
        colUrteak.setText(String.valueOf(b.getAdina()));
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
        Pertsona p = this.tblPertsonak.getSelectionModel().getSelectedItem();

        if (p != null) {
            this.pertsonak.remove(p);
            this.tblPertsonak.refresh();
        } else {
            error("AUKERATU PERTSONA BAT");
        }
    }

    private void changeWindow(Pertsona p) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();
        SecondaryController controlador = loader.getController();

        if (aukeratutakoAukera.equals("new")) {
            controlador.initAttributes(pertsonak);
        } else if (aukeratutakoAukera.equals("update") && p != null) {
            controlador.initAttributes(pertsonak, p);
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        // CORRECCIÓN PRINCIPAL: Actualizar la tabla después de cerrar la ventana
        if (controlador.getRespuestaList() != null) {
            pertsonak.clear();
            pertsonak.addAll(controlador.getRespuestaList());
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
        
    }

    @FXML
    private void btnGordeKarpetan(ActionEvent event) {
       
    }
    
    private void info(String infoMsg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información");
        alert.setContentText(infoMsg);
        alert.showAndWait();
    }
}
