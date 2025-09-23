/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.irudiak.ariketak.aritz.ibanez;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class IrudienCarrouselController implements Initializable {


    @FXML
    private Button btnLeft;
    @FXML
    private Button btnRight;
    @FXML
    private ImageView img;
    /**
     * Initializes the controller class.
     */
    int currentImage = 0;
    
    String[] imagenes ={"images/ajolote.png","images/aye_aye.png","images/cangrejoYeti.png",
        "images/chauliodus.png","images/diablilloespinoso.png","images/dragonMarino.png",
        "images/gusanoCalamar.png","images/mixini.png","images/petaurodelazucar.png",
        "images/pezmancha.png","images/pezsapopeludo.png","images/picozapato.png",
        "images/pulpoDumbo.png","images/ratonLemur.png","images/solenodonte.png","images/tardigrado.png"};
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
        loadImage(currentImage);
    }    

    @FXML
    private void btnGoToLeft(ActionEvent event) {
        if (currentImage > 0) {
            currentImage--;
        }
        loadImage(currentImage);
    }

    @FXML
    private void btnGoToRight(ActionEvent event) {
        if (currentImage < imagenes.length) {
            currentImage++;
        } else {
            currentImage = 0;
        }
        loadImage(currentImage);
    }
    
    private void loadImage (int index) {
        Image image = new Image (getClass().getResourceAsStream("/" + imagenes[index]));
        img.setImage(image);
    }
}
