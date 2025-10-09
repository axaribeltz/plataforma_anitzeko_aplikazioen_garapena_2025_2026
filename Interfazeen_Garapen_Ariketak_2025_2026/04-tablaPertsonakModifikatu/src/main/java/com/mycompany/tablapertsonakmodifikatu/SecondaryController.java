/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tablapertsonakmodifikatu;

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
public class SecondaryController implements Initializable {

    @FXML
    private TextField txtIzena;
    @FXML
    private TextField txtAbizena;
    @FXML
    private TextField txtAdina;
    @FXML
    private Button btnGorde;
    @FXML
    private Button btnExit;

    /**
     * Initializes the controller class.
     */
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

    Pertsona pertsona;

    public void setPertsona(Pertsona pertsona) {
        this.pertsona = pertsona;
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initAttributes(ObservableList<Pertsona> pertsonak) {
        this.pertsonaList = pertsonak;
    }

    void initAttributes(ObservableList<Pertsona> pertsonak, Pertsona p) {
        this.txtIzena.setText(p.getIzena());
        this.txtAbizena.setText(p.getAbizena());
        this.txtAdina.setText(p.getAdina() + "");
        this.pertsonaList = pertsonak;
        this.pertsona = p;
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
            Pertsona p = new Pertsona(izena, abizena, urteak);

            if(!pertsonaList.contains(p)) {
                this.pertsona.setIzena(izena);
                this.pertsona.setAbizena(abizena);
                this.pertsona.setAdina(urteak);
                info("ALDAKETA EGINDA");
            } else {
                setPertsona(p);
            }             
                
            if (!errepikatutaDago(pertsonaList, p)) {
                System.out.println("Ez da errepikatua");
                this.pertsonaList.add(p);
                setRespuestaList(pertsonaList);
                Stage stage = (Stage) this.btnGorde.getScene().getWindow();
                stage.close();
            } else {
                error("PERTSONA EXISTITZEN DA");
            }
        } catch (NumberFormatException e) {
            error("FORMA DESEGOKIAN IDATZI DUZU");
        }
    }

    private boolean errepikatutaDago(ObservableList<Pertsona> pertsonaList, Pertsona pertsona) {
        boolean respuesta = false;

        for (Pertsona p : pertsonaList) {
            if (p.getIzena().equals(pertsona.getIzena()) && p.getAbizena().equals(pertsona.getAbizena()) && p.getAdina() == pertsona.getAdina()) {
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }

    private void error(String errorMsg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(errorMsg);
        alert.showAndWait();
    }
    
    private void info(String ingoMsg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(ingoMsg);
        alert.showAndWait();
    }
}
