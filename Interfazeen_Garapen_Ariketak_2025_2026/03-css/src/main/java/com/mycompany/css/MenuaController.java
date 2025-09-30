/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.css;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class MenuaController implements Initializable {

    @FXML
    private Button btnPersonDetails;
    @FXML
    private Button btnLoginScreen;
    @FXML
    private Button btnPasswordScreen;
    @FXML
    private Button btnMirrorsScreen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGoToPersonDetails(ActionEvent event) throws IOException {
        App.setRoot("personDetails");
    }

    @FXML
    private void btnGoToLoginScreen(ActionEvent event) throws IOException {
        App.setRoot("loginScreen");
    }

    @FXML
    private void btnPasswordScreenAction(ActionEvent event) throws IOException {
        App.setRoot("passwordScreen");
    }

    @FXML
    private void btnMirrorsScreenAction(ActionEvent event) throws IOException {
        App.setRoot("mirrorsAplication");
    }
    
}
