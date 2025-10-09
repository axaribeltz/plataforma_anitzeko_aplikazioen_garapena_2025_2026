/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tablapertsonakmodifikatu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Pertsona;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class PrimaryController implements Initializable {

    @FXML
    private TableView<Pertsona> tblPertsonak;
    @FXML
    private TableColumn<Pertsona, String> colIzena;
    @FXML
    private TableColumn<Pertsona, String> colAbizena;
    @FXML
    private TableColumn<Pertsona, String> colUrteak;
    @FXML
    private Button btnAddPerson;
    @FXML
    private Button btnDeletePerson;
    @FXML
    private Button btnChangePerson;

    /**
     * Initializes the controller class.
     */
  
    private ObservableList<Pertsona> pertsonak;    
    String aukeratutakoAukera;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pertsonak = FXCollections.observableArrayList(
                new Pertsona ("Aritz", "Ibañez", 24),
                new Pertsona ("Leire", "Aldasoro", 22),
                new Pertsona ("Naroa", "Elorriaga", 25)
        );
        colIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        this.colAbizena.setCellValueFactory(new PropertyValueFactory<>("abizena"));
        this.colUrteak.setCellValueFactory(new PropertyValueFactory<>("adina"));
        tblPertsonak.setItems(pertsonak);
        System.out.println(pertsonak);
    }
    
    @FXML
    private void SwitchToAddPerson(ActionEvent event) {
        aukeratutakoAukera = "new";
        Pertsona p = null;
        aldatuLeihoa(p);
    }
    
    @FXML
    private void SwitchToDeletePerson(ActionEvent event) {
        aukeratutakoAukera = "delete";
        Pertsona p = null;
        aldatuLeihoa(p);
    }

    @FXML
    private void SwitchToChangePerson(ActionEvent event) {
        aukeratutakoAukera = "update";
        Pertsona p = null;
        aldatuLeihoa(p);
    }
    
    private void aldatuLeihoa (Pertsona p) {
        try{
            //Beste fitxeroa kargatu
            FXMLLoader loader = new FXMLLoader (getClass().getResource(aukeratutakoAukera));
            Parent root = loader.load();
            SecondaryController controlador = loader.getController();
            
            
            if (aukeratutakoAukera.equals("new")) {
                controlador.initAttributes(pertsonak);
            } else if (aukeratutakoAukera.equals("update")) {
                p = this.tblPertsonak.getSelectionModel().getSelectedItem();
            } else if (p != null) {
                controlador.initAttributes(pertsonak, p);
            } else {
                error("SELECIONE PERTSONA");
            }
            
            //Sortzen dut eszena eta irekitzen dut
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            //Jarraitzeko itxi arte
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            //Beste leihotik iristen datua eskuratzeko
            System.out.println(controlador.getRespuestaList());
            if(controlador.getRespuestaList() != null)
                pertsonak = controlador.getRespuestaList();
            
        }catch(IOException e){
            System.out.println("Error");
        }
    }
    
    private void error(String errorMsg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(errorMsg);
        alert.showAndWait();
    }
    
    
}
