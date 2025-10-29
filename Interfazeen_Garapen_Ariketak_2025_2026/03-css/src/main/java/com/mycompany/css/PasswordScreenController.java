/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.css;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class PasswordScreenController implements Initializable {

    @FXML
    private PasswordField txtPass;
    @FXML
    private TextField txtUser;
    @FXML
    private Button btnAccepted;
    @FXML
    private Button closeButton;
    @FXML
    private TextArea textArea;
    
    

    /**
     * Initializes the controller class.
     */
    
    private String user = "kaixo";
    private String password = "kaixo";
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Bloquear números en txtUser
        txtUser.addEventFilter(KeyEvent.KEY_TYPED, event -> {
        String character = event.getCharacter();

            // Solo actuar si es un dígito
            if (!character.isEmpty() && Character.isDigit(character.charAt(0))) {
                System.out.println("No se permiten números: " + character);
                event.consume(); // Evita que se escriba
                showAlertErrorNum();
            }
        });
    }    

    @FXML
    private void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void konprobatu(ActionEvent event) {
        String enteredUser = txtUser.getText();
        String enteredPass = txtPass.getText();

        if (enteredUser.equals(user) && enteredPass.equals(password)) {
            // Credenciales correctas → TextArea erakusten dugu
            textArea.setVisible(true);
        } else {
            // Credenciales incorrectas → mostramos error
            showAlertError();
            textArea.setVisible(false);
        }  
    }

    private void showAlertError() {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Usuario o contraseña incorrectos");
            alert.setTitle("Error");
            alert.setContentText("Por favor, revisa tus datos e inténtalo de nuevo.");
            alert.showAndWait();
    }
    
    private void showAlertErrorNum() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Entrada no válida");
        alert.setContentText("No se permiten números en el nombre de usuario.");
        alert.show();
    }
}
