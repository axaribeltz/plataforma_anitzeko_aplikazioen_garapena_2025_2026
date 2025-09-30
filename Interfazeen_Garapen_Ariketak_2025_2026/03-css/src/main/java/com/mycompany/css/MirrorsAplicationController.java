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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class MirrorsAplicationController implements Initializable {

    @FXML
    private ImageView plantImg;
    @FXML
    private ImageView animalImg;
    @FXML
    private Button secondaryViewbtn;
    @FXML
    private Label lblOpcionElegida;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SwitchToSecondaryView(ActionEvent event) {
        String opcion = "LANDAREA";
        String imagen = "img/omakoBasoa.jpg";
        cargarOpcion(opcion,imagen);
    }

    @FXML
    private void loadPlant(MouseEvent event) {
        String opcion = "LANDAREA";
        String imagen = "img/pagadia.jpg";
        cargarOpcion(opcion,imagen);
    }

    @FXML
    private void loadAnimal(MouseEvent event) {
        String opcion = "ANIMALIA";
        String imagen = "img/aye_aye.png";
        cargarOpcion(opcion,imagen);
    }
    
    private void cargarOpcion (String opcion, String imagen) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mirrorsAplication2.fxml"));
            Parent root = loader.load();
            MirrorsAplication2Controller controlador = loader.getController();
            controlador.initAttributes(opcion,imagen);
            
            //Crear la escena y abrir
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            String respuesta = controlador.getRespuesta();
            this.lblOpcionElegida.setText(respuesta);

        } catch(IOException e) {
            //error
        }
    }
    
}
