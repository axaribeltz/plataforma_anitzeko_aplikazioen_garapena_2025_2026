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
import modelo.Jokalaria;
import com.google.gson.Gson;
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
    private TableView<Jokalaria> tblJokalariak;
    @FXML
    private TableColumn<Jokalaria, String> colIzena;
    @FXML
    private TableColumn<Jokalaria, Integer> colAdina;
    @FXML
    private TableColumn<Jokalaria, String> colPosizioa;
    @FXML
    private TableColumn<Jokalaria, String> colKluba;
    @FXML
    private Button btnAddPerson;
    @FXML
    private Button btnDeletePerson;
    @FXML
    private Button btnChangePerson;
    

    String jsonAll = "[\n"
            + " {\n"
            + " \"nombre\": \"Lucía Arrieta\",\n"
            + " \"pais\": \"España\",\n"
            + " \"posicion\": \"Portera\",\n"
            + " \"edad\": 24,\n"
            + " \"club\": \"CN Sabadell\",\n"
            + " \"estadisticas\": {\n"
            + " \"partidos_jugados\": 38,\n"
            + " \"paradas\": 215,\n"
            + " \"goles_encajados\": 72\n"
            + " }\n"
            + " },\n"
            + " {\n"
            + " \"nombre\": \"Giulia Romano\",\n"
            + " \"pais\": \"Italia\",\n"
            + " \"posicion\": \"Defensa\",\n"
            + " \"edad\": 27,\n"
            + " \"club\": \"Pro Recco\",\n"
            + " \"estadisticas\": {\n"
            + " \"partidos_jugados\": 42,\n"
            + " \"goles\": 18,\n"
            + " \"asistencias\": 25\n"
            + " }\n"
            + " },\n"
            + " {\n"
            + " \"nombre\": \"Nora Varga\",\n"
            + " \"pais\": \"Hungría\",\n"
            + " \"posicion\": \"Boyas\",\n"
            + " \"edad\": 22,\n"
            + " \"club\": \"UVSE Budapest\",\n"
            + " \"estadisticas\": {\n"
            + " \"partidos_jugados\": 30,\n"
            + " \"goles\": 34,\n"
            + " \"exclusiones_provocadas\": 19\n"
            + " }\n"
            + " },\n"
            + " {\n"
            + " \"nombre\": \"Emily Carter\",\n"
            + " \"pais\": \"Estados Unidos\",\n"
            + " \"posicion\": \"Delantera\",\n"
            + " \"edad\": 25,\n"
            + " \"club\": \"Stanford University\",\n"
            + " \"estadisticas\": {\n"
            + " \"partidos_jugados\": 45,\n"
            + " \"goles\": 51,\n"
            + " \"asistencias\": 32\n"
            + " }\n"
            + " },\n"
            + " {\n"
            + " \"nombre\": \"Maite Etxebarria\",\n"
            + " \"pais\": \"España\",\n"
            + " \"posicion\": \"Exterior\",\n"
            + " \"edad\": 23,\n"
            + " \"club\": \"Leioa WLB\",\n"
            + " \"estadisticas\": {\n"
            + " \"partidos_jugados\": 36,\n"
            + " \"goles\": 29,\n"
            + " \"robos\": 14\n"
            + " }\n"
            + " }\n"
            + "]";

    Gson gson = new Gson();
    Jokalaria[] Jokalariak = gson.fromJson(jsonAll, Jokalaria[].class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Jokalaria> jokalariLista = FXCollections.observableArrayList(Jokalariak);

        colIzena.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colAdina.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colPosizioa.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        colKluba.setCellValueFactory(new PropertyValueFactory<>("club"));

        tblJokalariak.setItems(jokalariLista);
    }

    @FXML
    private void SwitchToAddPerson(ActionEvent event) {
    }

    @FXML
    private void SwitchToChangePerson(ActionEvent event) {
    }

    @FXML
    private void SwitchToDeletePerson(ActionEvent event) {
    }
}
