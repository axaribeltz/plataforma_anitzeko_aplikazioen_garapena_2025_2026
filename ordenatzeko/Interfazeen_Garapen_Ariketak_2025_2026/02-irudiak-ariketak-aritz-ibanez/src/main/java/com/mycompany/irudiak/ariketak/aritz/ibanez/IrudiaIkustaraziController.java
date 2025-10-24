/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.irudiak.ariketak.aritz.ibanez;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class IrudiaIkustaraziController implements Initializable {

    @FXML
    private Button btnSearchImg;
    @FXML
    private ImageView irudia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
    }    

    @FXML
    private void btnSearchImg(ActionEvent event)  {
        try {
            Window window = btnSearchImg.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("C:\\Users\\2AM3-4\\Documents\\NetBeansProjects\\Interfazeen_Garapen_Ariketak_2025_2026\\02-irudiak-ariketak-aritz-ibanez\\src\\main\\resources"));
            fileChooser.setTitle("Irudia bilatu");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos Imagen","*.png", "*.jpg"));
     
            File selectedFile = fileChooser.showOpenDialog(window);
        
            if (selectedFile != null) {
                FileInputStream imageSelected = new FileInputStream(selectedFile);
            
                Image image = new Image(imageSelected);
                irudia.setImage(image);
            }            
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
