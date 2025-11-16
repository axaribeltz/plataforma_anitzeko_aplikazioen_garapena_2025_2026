/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dbariketa;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modeloa.Bezeroak;
import modeloa.DBBezeroak;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class PrimaryController implements Initializable {

    @FXML
    private TableView<Bezeroak> tableView;
    @FXML
    private TableColumn<Bezeroak, Integer> colId;
    @FXML
    private TableColumn<Bezeroak, String> colIzena;
    @FXML
    private TableColumn<Bezeroak, String> colHiria;
    @FXML
    private TableColumn<Bezeroak, String> colSexua;
    @FXML
    private TableColumn<Bezeroak, String> colMugikorra;
    @FXML
    private TableColumn<Bezeroak, LocalDate> colJaiotzeData;

    private DBBezeroak dbBezeroak;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbBezeroak = new DBBezeroak();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        colHiria.setCellValueFactory(new PropertyValueFactory<>("hiria"));
        colSexua.setCellValueFactory(new PropertyValueFactory<>("sexua"));
        colMugikorra.setCellValueFactory(new PropertyValueFactory<>("mugikorra"));
        colJaiotzeData.setCellValueFactory(new PropertyValueFactory<>("jaiotzeData"));
        bezeroakKargatu();
    }

    public void bezeroakKargatu() {
        ObservableList<Bezeroak> bezeroak = FXCollections.observableArrayList(dbBezeroak.bezeroakLortu());
        tableView.setItems(bezeroak);
    }

    @FXML
    private void CreateNewClient(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Stage newClientStage = new Stage();

            Scene scene = new Scene(fxmlLoader.load());
            newClientStage.setScene(scene);

            SecondaryController secondaryCOntroller = fxmlLoader.getController();
            secondaryCOntroller.setPrimaryController(this); // Pasar una referencia al controlador principal

            newClientStage.showAndWait(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ModifyClient(ActionEvent event) {
        Bezeroak hautatua = tableView.getSelectionModel().getSelectedItem();

        if (hautatua == null) {
            showAlertError("Hautatu bezero bat editatzeko.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

            SecondaryController controller = loader.getController();
            controller.setPrimaryController(this);
            controller.loadClientData(hautatua);  

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            bezeroakKargatu(); 

        } catch (Exception e) {
            showAlertError("Errorea bezeroa editatzean: " + e.getMessage());
        }
    }

    @FXML
    private void DeleteClient(ActionEvent event) {
        Bezeroak bezeroa = tableView.getSelectionModel().getSelectedItem();
        if (bezeroa != null) {
            try {
                dbBezeroak.bezeroaEzabatu(bezeroa.getId());
                bezeroakKargatu();
            } catch (Exception e) {
                showAlertError("Errorea bezeroa ezabatzean: " + e.getMessage());
            }
        } else {
            showAlertError("Hautatu bezero bat ezabatzeko.");
        }
    }

    @FXML
    private void CloseApp(ActionEvent event) {
        Platform.exit();
    }

    private void showAlertError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setTitle("Error");
        alert.setContentText(error);
        alert.showAndWait();
    }

    public DBBezeroak getDbBezeroak() {
        return dbBezeroak;
    }
}
