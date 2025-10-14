package com.mycompany.estiloariketak;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.User;

public class SecondaryController {

    @FXML
    private Button SwitchToSecondaryView;

    /**
     * Initializes the controller class.
     */
    // Creamos el usuario aceptado (nick = Ana, pass = Ana123)
    private final User userAccepted = new User("Ana", "Ana123", "Ana Martínez");
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPass;

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void SwitchToSecondaryView(ActionEvent event) throws IOException {
        String enteredUser = txtUser.getText();
        String enteredPass = txtPass.getText();

        if (enteredUser.equals(userAccepted.getNick()) && enteredPass.equals(userAccepted.getPass())) {
            // Credenciales correctas → cambiamos de escena
            App.setRoot("profileScreen");
        } else {
            // Credenciales incorrectas → mostramos error
            mostrarAlertError();
        }
    }

    private void mostrarAlertError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Usuario o contraseña incorrectos");
        alert.setTitle("Error");
        alert.setContentText("Por favor, revisa tus datos e inténtalo de nuevo.");
        alert.showAndWait();
    }
}
