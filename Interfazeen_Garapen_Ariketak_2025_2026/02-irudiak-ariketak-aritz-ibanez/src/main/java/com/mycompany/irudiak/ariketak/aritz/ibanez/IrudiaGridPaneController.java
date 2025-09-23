/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.irudiak.ariketak.aritz.ibanez;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class IrudiaGridPaneController implements Initializable {

    @FXML
    private GridPane gridPane;
    
    String[] imagenes ={"images/ajolote.png","images/aye_aye.png","images/cangrejoYeti.png",
        "images/chauliodus.png","images/diablilloespinoso.png","images/dragonMarino.png",
        "images/gusanoCalamar.png","images/mixini.png","images/petaurodelazucar.png",
        "images/pezmancha.png","images/pezsapopeludo.png","images/picozapato.png",
        "images/pulpoDumbo.png","images/ratonLemur.png","images/solenodonte.png","images/tardigrado.png"};

    int columns = 4;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < imagenes.length; i++) {
            try {
                
                Image image = new Image(getClass().getResourceAsStream("/" + imagenes[i]));
                ImageView imageView = new ImageView(image);
                
                imageView.setFitWidth(150);   // zabalera max
                imageView.setFitHeight(150);  // altuera max
                imageView.setPreserveRatio(true); // mantiene proporción
                               
                int row = i / columns;
                int column = i % columns;
                
                gridPane.add(imageView, column, row);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }    
    
}
