package com.mycompany.azt2526_1_play_with_words_ikasle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import models.User;

public class PrimaryController implements Initializable {

    @FXML
    private Pane paneLogin;
    @FXML
    private Label txtLogin;
    @FXML
    private Label txtPurpleLabel;
    @FXML
    private Label txtOrangeLabel;
    @FXML
    private Label txtBlueLabel;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnUsersScore;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField txtFieldNick;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnEnter;
    @FXML
    private Label txtNick;
    @FXML
    private Label txtPassword;

    // Creamos el usuario aceptado (nick = Aritz, pass = 12345)
    private final User userAccepted = new User("Aritz", "12345", "player", 123);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBack.setVisible(false);
        btnUsersScore.setVisible(false);
        btnPlay.setVisible(false);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void btnEnter(ActionEvent event) {
        String enteredUser = txtFieldNick.getText();
        String enteredPass = txtFieldPassword.getText();

        if (enteredUser.equals(userAccepted.getNick()) && enteredPass.equals(userAccepted.getPassword())) {
            // Credenciales correctas → cambiamos de escena
            btnBack.setVisible(true);
            btnUsersScore.setVisible(true);
            btnPlay.setVisible(true);
            titleLabel.setVisible(false);
            txtFieldNick.setVisible(false);
            txtFieldPassword.setVisible(false);
            btnEnter.setVisible(false);
            txtNick.setVisible(false);
            txtPassword.setVisible(false);
        } else {
            // Credenciales incorrectas → mostramos error
            mostrarAlertError();
        }
    }

    private void mostrarAlertError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Authentication error");
        alert.setTitle("Error");
        alert.setContentText("Wrong, user or password");
        alert.showAndWait();
    }

    @FXML
    private void btnBack(ActionEvent event) {
        btnBack.setVisible(false);
        btnUsersScore.setVisible(false);
        btnPlay.setVisible(false);
        titleLabel.setVisible(true);
            txtFieldNick.setVisible(true);
            txtFieldPassword.setVisible(true);
            btnEnter.setVisible(true);
            txtNick.setVisible(true);
            txtPassword.setVisible(true);
    }

    @FXML
    private void switchToGame(ActionEvent event) throws IOException {
        App.setRoot("game");
    }
}
