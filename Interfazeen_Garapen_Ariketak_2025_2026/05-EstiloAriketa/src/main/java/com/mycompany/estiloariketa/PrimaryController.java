/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.estiloariketa;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Aritz
 */
public class PrimaryController implements Initializable {

    @FXML
    private Label label_1;
    @FXML
    private Label label_2;
    @FXML
    private Label label_3;
    @FXML
    private TextField txtNum1;
    @FXML
    private TextField txtNum2;
    @FXML
    private Button btnSum;
    @FXML
    private TextField lblResult;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void sumar(ActionEvent event) {
        if (txtNum1.getText().isEmpty() || txtNum2.getText().isEmpty()) {
            showAlertInvalidInput();
            return;
        }

        try {
            int num1 = Integer.parseInt(txtNum1.getText());
            int num2 = Integer.parseInt(txtNum2.getText());
            int sum = num1 + num2;
            lblResult.setText(String.valueOf(sum));
        } catch (NumberFormatException e) {
            showAlertInvalidInput();
        }
    }

    private void showAlertInvalidInput() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Entrada no válida");
        alert.setContentText("Por favor, ingresa solo números.");
        alert.showAndWait();
    }

    @FXML
    private void Konprobatu(KeyEvent event) {
        String character = event.getCharacter();
        // Bloquea cualquier cosa que no sea dígito
        if (!character.isEmpty() && !Character.isDigit(character.charAt(0))) {
            System.out.println("Caracter no permitido: " + character);
            event.consume(); // bloquea la tecla
            showAlertInvalidInput(); // opcional, muestra alerta
        }
    }
}
