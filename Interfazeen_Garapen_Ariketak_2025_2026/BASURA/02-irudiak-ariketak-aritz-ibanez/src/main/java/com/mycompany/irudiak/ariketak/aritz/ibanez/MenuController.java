/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.irudiak.ariketak.aritz.ibanez;

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
public class MenuController implements Initializable {

    @FXML
    private Button btnImage;
    @FXML
    private Button btnImgCarrousel;
    @FXML
    private Button btnImgGrid;
    @FXML
    private Button btnMenuBar;
    @FXML
    private Button btnSearchImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGoToImage(ActionEvent event) throws IOException {
        App.setRoot("irudiak");
    }

    @FXML
    private void btnGoToImgGrid(ActionEvent event) throws IOException  {
        App.setRoot("irudiaGridPane");
    }

    @FXML
    private void btnGoToImgCarrousel_(ActionEvent event) throws IOException {
        App.setRoot("irudienCarrousel");
    }

    @FXML
    private void btnSearchImg(ActionEvent event) throws IOException {
        App.setRoot("irudiaIkustarazi");
    }

    @FXML
    private void btnGoToMenuBar(ActionEvent event) throws IOException {
        App.setRoot("irudiakMenubar");
    }    
}
