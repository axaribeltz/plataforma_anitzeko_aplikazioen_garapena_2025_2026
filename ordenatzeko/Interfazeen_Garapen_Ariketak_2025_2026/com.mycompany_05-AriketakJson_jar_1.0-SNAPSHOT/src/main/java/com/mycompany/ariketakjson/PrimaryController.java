package com.mycompany.ariketakjson;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Bezeroa;
import models.Langilea;

public class PrimaryController implements Initializable {

    @FXML
    private TableView<Langilea> tblLangileak;
    @FXML
    private TableColumn<Langilea, String> colIzena;
    @FXML
    private TableColumn<Langilea, Integer> colId;
    @FXML
    private TableColumn<Langilea, String> colLantegia;
    @FXML
    private Button btnExit;
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem switchToLangileak;
    @FXML
    private MenuItem switchToBezeroak;
    @FXML
    private Label labelTitle;
    @FXML
    private TableView<Bezeroa> tblBezeroak;
    @FXML
    private TableColumn<Bezeroa, String> colIzenaBezeroa;
    @FXML
    private TableColumn<Bezeroa, String> colAbizenaBezeroa;
    @FXML
    private TableColumn<Bezeroa, Integer> colAdinaBezeroa;
    @FXML
    private TableColumn<Bezeroa, String> colAfizioaBezeroa;

    private final Gson gson = new Gson();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialmente solo se muestra la tabla de Langileak
        tblBezeroak.setVisible(false);
        tblLangileak.setVisible(true);
        labelTitle.setText("LANGILEAK");

        langileakKargatu();
    }

    @FXML
    private void SwitchToExit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void switchToLangileak(ActionEvent event) {
        tblLangileak.setVisible(true);
        tblBezeroak.setVisible(false);
        labelTitle.setText("LANGILEAK");
        langileakKargatu();
    }

    @FXML
    private void switchToBezeroak(ActionEvent event) {
        tblLangileak.setVisible(false);
        tblBezeroak.setVisible(true);
        labelTitle.setText("BEZEROAK");
        bezeroakKargatu();
    }
    
    private void bezeroakKargatu() {
        try {
            String bezeroakPath = "C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Interfazeen_Garapen_Ariketak_2025_2026\\com.mycompany_05-AriketakJson_jar_1.0-SNAPSHOT\\src\\main\\resources\\json\\bezeroak.json";
            java.lang.reflect.Type BezeroListaTipoa = new TypeToken<List<Bezeroa>>() {}.getType();
            List<Bezeroa> bezeroak = gson.fromJson(new FileReader(bezeroakPath), BezeroListaTipoa);

            ObservableList<Bezeroa> obList = FXCollections.observableList(bezeroak);

            colIzenaBezeroa.setCellValueFactory(new PropertyValueFactory<>("izena"));
            colAbizenaBezeroa.setCellValueFactory(new PropertyValueFactory<>("abizena"));
            colAdinaBezeroa.setCellValueFactory(new PropertyValueFactory<>("adina"));
            //KONTUZ HEMEN ARRAY BAT DA
            colAfizioaBezeroa.setCellValueFactory(new PropertyValueFactory<>("afizioaAsString"));

            tblBezeroak.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void langileakKargatu() {
        try {
            String langileakPath = "C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Interfazeen_Garapen_Ariketak_2025_2026\\com.mycompany_05-AriketakJson_jar_1.0-SNAPSHOT\\src\\main\\resources\\json\\langileak.json";
            java.lang.reflect.Type LangileaListaTipoa = new TypeToken<List<Langilea>>() {}.getType();
            List<Langilea> langileak = gson.fromJson(new FileReader(langileakPath), LangileaListaTipoa);

            ObservableList<Langilea> obList = FXCollections.observableList(langileak);

            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
            colLantegia.setCellValueFactory(new PropertyValueFactory<>("lantegia"));

            tblLangileak.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
