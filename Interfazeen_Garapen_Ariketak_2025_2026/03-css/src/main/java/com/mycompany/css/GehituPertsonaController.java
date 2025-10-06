/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.css;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Pertsona;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class GehituPertsonaController implements Initializable {

    @FXML
    private TextField txtIzena;
    @FXML
    private TextField txtAbizena;
    @FXML
    private TextField txtAdina;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnGorde;
    
    private ObservableList<Pertsona> pertsonaList;
    
    public void setPertsonaList(ObservableList<Pertsona> pertsonaList) {
        this.pertsonaList = pertsonaList;
    }

    public ObservableList<Pertsona> getPertsonaList() {
        return pertsonaList;
    }
    private ObservableList<Pertsona> respuestaList;   

    public ObservableList<Pertsona> getRespuestaList() {
        return respuestaList;
    }

    public void setRespuestaList(ObservableList<Pertsona> respuestaList) {
        this.respuestaList = respuestaList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    void initAttributes (ObservableList<Pertsona> pertsonak) {
        this.pertsonaList = pertsonak;
    }
       
    @FXML
    private void btnExit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void switchToPrimary(ActionEvent event) {
        try {
            String izena = this.txtIzena.getText();
            String abizena = this.txtAbizena.getText();
            int urteak = Integer.parseInt(this.txtAdina.getText());
            Pertsona p = new Pertsona (izena,abizena,urteak);
            
            if(!errepikatutaDago(pertsonaList, p)) {
                System.out.println("Ez da errepikatua");
                this.pertsonaList.add(p);
                setRespuestaList(pertsonaList);
                Stage stage = (Stage) this.btnGorde.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText("PERTSONA EXISTITZEN DA");
                alert.showAndWait();
            } 
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("FORMA DESEGOKIAN IDATZI DUZU");
            alert.showAndWait();
        }
    }
    
     private boolean errepikatutaDago(ObservableList<Pertsona> pertsonaList, Pertsona pertsona) {
        boolean respuesta = false;
        
        for(Pertsona p : pertsonaList) {
            if(p.getIzena().equals(pertsona.getIzena()) && p.getAbizena().equals(pertsona.getAbizena()) && p.getAdina() == pertsona.getAdina()) {
                respuesta = true;
                break;
            }
        }
       return respuesta;
    }
    
}
