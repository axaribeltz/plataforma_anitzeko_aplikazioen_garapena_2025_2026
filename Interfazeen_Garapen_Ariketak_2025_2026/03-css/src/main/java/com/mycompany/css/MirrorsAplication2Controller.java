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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class MirrorsAplication2Controller implements Initializable {

    @FXML
    private ImageView imgImagen;
    @FXML
    private Button secondaryButton;
    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtSelection;
    private String respuesta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToPrimary(ActionEvent event) {
        String aux = txtSelection.getText();
        setRespuesta(aux);
        
        //Cerramos la ventana
        Stage stage = (Stage) this.secondaryButton.getScene().getWindow();
        stage.close();
    }
    
    void initAttributes(String opcion, String imagen) {
        lblTitulo.setText(opcion);
        Image image = new Image(imagen);
        this.imgImagen.setImage(image);
    }
    
    String getRespuesta() {
        return respuesta;
    }
    
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
}
