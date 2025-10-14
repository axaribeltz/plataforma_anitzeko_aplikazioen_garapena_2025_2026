/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ariketakjson;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.Empleado;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class PrimaryController implements Initializable {

   
    @FXML
    private TableView<Empleado> tblEmpleados;
    @FXML
    private TableColumn<Empleado, String> colIzena;
    @FXML
    private TableColumn<Empleado, Integer> colId;
    @FXML
    private TableColumn<Empleado, String> colLantegia;
    @FXML
    private Button btnExit;
    
    String filePath = "C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Interfazeen_Garapen_Ariketak_2025_2026.empleado.json";
    Gson gson = new Gson();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            java.lang.reflect.Type tipoListaEmpleados = new TypeToken<List<Empleado>>().getType();
            List<Empleado> empleados = gson.fromJson(new FileReader(filePath), tipoListaEmpleados);
            ObservableList obList  = FXCollections.observableList(empleados);
            this.tblEmpleados.setItems(obList);
            this.cbmClientes.setItems(obList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @FXML
    private void SwitchToExit(ActionEvent event) {
    }
}
