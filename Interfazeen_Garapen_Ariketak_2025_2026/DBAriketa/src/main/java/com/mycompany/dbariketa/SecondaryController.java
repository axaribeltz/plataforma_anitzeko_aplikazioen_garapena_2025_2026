/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dbariketa;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modeloa.Bezeroak;
import modeloa.DBBezeroak;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class SecondaryController implements Initializable {

    @FXML
    private TextField izenaField;
    @FXML
    private TextField hiriaField;
    @FXML
    private TextField sexuaField;
    @FXML
    private TextField mugikorraField;
    @FXML
    private TextField jaiotzeDataField;
    
    private PrimaryController primaryController; // Referencia al controlador principal
    private DBBezeroak dbBezeroak;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setPrimaryController(PrimaryController primaryController) {
        this.primaryController = primaryController;
        this.dbBezeroak = primaryController.getDbBezeroak(); // Obtener la instancia de DBBezeroak
    }

    @FXML
    private void saveNewClient(ActionEvent event) {
        String izena = izenaField.getText();
        String hiria = hiriaField.getText();
        String sexua = sexuaField.getText();
        String telefonoa = mugikorraField.getText();
        String jaiotzeDataStr = jaiotzeDataField.getText();

        // Validar los datos (ejemplo básico)
        if (izena.isEmpty() || hiria.isEmpty() || sexua.isEmpty() || telefonoa.isEmpty() || jaiotzeDataStr.isEmpty()) {
            showAlertError("Datu guztiak idatzi, mesedez.");
            return;
        }

        LocalDate jaiotzeData = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            jaiotzeData = LocalDate.parse(jaiotzeDataStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Fetxa formatoa ezegokia. Erabili UUUU-HH-EE.");
            return;
        }

        // Crear el objeto Bezeroak
        Bezeroak bezeroBerria = new Bezeroak(0, izena, hiria, sexua, telefonoa, jaiotzeData);  // El ID se generará automáticamente en la base de datos

        // Guardar el nuevo Bezeroak en la base de datos
        try {
            dbBezeroak.bezeroaGehitu(bezeroBerria);
            // Cerrar la ventana después de guardar
            Stage stage = (Stage) izenaField.getScene().getWindow();
            stage.close();
            primaryController.bezeroakKargatu(); // Recargar los datos en la tabla principal
        } catch (Exception e) {
            System.err.println("Errorea bezeroa gordetzean: " + e.getMessage());
            // Mostrar un mensaje de error al usuario
        }
    }

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();
    }
    
    private void showAlertError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setTitle("Error");
        alert.setContentText(error);
        alert.showAndWait();
    }
}
