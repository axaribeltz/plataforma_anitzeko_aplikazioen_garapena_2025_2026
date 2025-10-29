package com.mycompany.tableview;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private ObservableList<Pertsona> pertsonak;
    String aukeratutakoAukera;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pertsonak = FXCollections.observableArrayList(
                new Pertsona("Aritz", "Ibañez", 24),
                new Pertsona("Leire", "Aldasoro", 22),
                new Pertsona("Naroa", "Elorriaga", 25)
        );
        colIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        this.colAbizena.setCellValueFactory(new PropertyValueFactory<>("abizena"));
        this.colUrteak.setCellValueFactory(new PropertyValueFactory<>("adina"));
        tblPertsonak.setItems(pertsonak);
        System.out.println(pertsonak);
    }

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
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
            pertsonak = controlador.getRespuestaList();
            tblPertsonak.setItems(pertsonak);  // ← ESTO ES LO QUE FALTABA
            tblPertsonak.refresh();            // Opcional: forzar refresco visual
            System.out.println("Personas actualizadas: " + pertsonak);
        }
    }

    private void error(String errorMsg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(errorMsg);
        alert.showAndWait();
    }
}
