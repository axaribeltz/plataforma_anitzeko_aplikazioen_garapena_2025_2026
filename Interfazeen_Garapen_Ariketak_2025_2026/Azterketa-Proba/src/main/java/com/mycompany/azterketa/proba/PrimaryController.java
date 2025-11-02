/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.azterketa.proba;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import models.Jokalaria;

/**
 * FXML Controller class
 *
 * @author Aritz
 */
public class PrimaryController implements Initializable {

    @FXML
    private MenuButton menuButton;
    @FXML
    private TableView<Jokalaria> tblJokalariak;
    @FXML
    private TableColumn<Jokalaria, Integer> colId;
    @FXML
    private TableColumn<Jokalaria, String> colIzena;
    @FXML
    private TableColumn<Jokalaria, String> colAbizena;
    @FXML
    private TableColumn<Jokalaria, Integer> colAdina;
    @FXML
    private TableColumn<Jokalaria, String> colHiria;
    @FXML
    private TableColumn<Jokalaria, Integer> colPuntuak;
    @FXML
    private TableColumn<Jokalaria, Integer> colIrabazita;
    @FXML
    private TableColumn<Jokalaria, Integer> colGalduta;
    @FXML
    private Label wins;
    @FXML
    private Label losses;
    @FXML
    private Label points;
    @FXML
    private Button goButton;
    @FXML
    private TextField txtHidden;
    @FXML
    private ImageView imgView;
    @FXML
    private GridPane gridPaneAkats;

    private List<Jokalaria> jokalariak;
    private ObservableList<Jokalaria> jokalariakList;

    private int zenbakiSekretua;
    private int ahaleginak;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try (InputStream pertsonakJson = getClass().getResourceAsStream("/json/jokalariak.json")) {
            if (pertsonakJson == null) {
                System.err.println("Ez da aurkitu jokalariak.json");
                return;
            }

            java.lang.reflect.Type jokalariaListaTipoa = new TypeToken<List<Jokalaria>>() {
            }.getType();
            jokalariak = new Gson().fromJson(new InputStreamReader(pertsonakJson), jokalariaListaTipoa);

            jokalariakList = FXCollections.observableList(jokalariak);

            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
            colAbizena.setCellValueFactory(new PropertyValueFactory<>("abizena"));
            colAdina.setCellValueFactory(new PropertyValueFactory<>("adina"));
            colHiria.setCellValueFactory(new PropertyValueFactory<>("hiria"));
            colPuntuak.setCellValueFactory(new PropertyValueFactory<>("puntuak"));
            colIrabazita.setCellValueFactory(new PropertyValueFactory<>("irabazita"));
            colGalduta.setCellValueFactory(new PropertyValueFactory<>("galduta"));

            tblJokalariak.setItems(jokalariakList);

            // Gogoratu garbitzeaz
            menuButton.getItems().clear();
            for (Jokalaria b : jokalariak) {
                MenuItem item = new MenuItem(b.getIzena() + " " + b.getAbizena());
                item.setOnAction(e -> erakutsiJokalariarenDatuak(b));
                menuButton.getItems().add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtHidden.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { // si contiene letras u otros símbolos
                erakutsiAlerta("Bakarrik zenbakiak onartzen dira.");
                txtHidden.setText(oldValue); // revertir al valor anterior
            } else if (newValue.length() > 2) { // si tiene más de 2 dígitos
                erakutsiAlerta("Gehienez 2 digitu sar daitezke.");
                txtHidden.setText(oldValue); // revertir también
            }
        });
    }

    private void erakutsiJokalariarenDatuak(Jokalaria b) {
        wins.setText(String.valueOf(b.getIrabazita()));
        losses.setText(String.valueOf(b.getGalduta()));
        points.setText(String.valueOf(b.getPuntuak()));

        // Aldatu textua segun eta ze izenetan klikatu
        menuButton.setText(b.getIzena() + " " + b.getAbizena());

        // Generar número aleatorio entre 1 y 50
        zenbakiSekretua = new Random().nextInt(50) + 1;
        ahaleginak = 0;
        System.out.println("Zenbaki sekretua: " + zenbakiSekretua);

    }

    @FXML
    private void goButtonAction(ActionEvent event) {
        String input = txtHidden.getText().trim();
        int zenbakia;

        if (input.isEmpty()) {
            zenbakia = 0; // hutsik badago, 0 hartu
        } else {
            zenbakia = Integer.parseInt(input);
        }

        // Bilatu hautatutako jokalaria
        Jokalaria hautatutakoJokalaria = null;
        for (Jokalaria j : jokalariakList) {
            if ((j.getIzena() + " " + j.getAbizena()).equals(menuButton.getText())) {
                hautatutakoJokalaria = j;
                System.out.println("Hautatutako jokalaria: " + hautatutakoJokalaria);
                break;
            }
        }

        if (hautatutakoJokalaria == null) {
            erakutsiAlerta("Mesedez, aukeratu jokalari bat jarraitzeko.");
            return;
        }

        ahaleginak++; // Contador de errores o intentos

        if (zenbakia == zenbakiSekretua) {
            imgView.setImage(new javafx.scene.image.Image(
                    getClass().getResourceAsStream("/img/ok.png")));

            hautatutakoJokalaria.setIrabazita(hautatutakoJokalaria.getIrabazita() + 1);
            hautatutakoJokalaria.setPuntuak(hautatutakoJokalaria.getPuntuak() + kalkulaPuntuazioa(ahaleginak));

            tblJokalariak.refresh();
            erakutsiInfoAlerta("ZORIONAK! Asmatuta! Zenbakia: " + zenbakiSekretua);
        } else {
            // Diferenciar mayor o menor
            if (zenbakia > zenbakiSekretua) {
                imgView.setImage(new javafx.scene.image.Image(
                        getClass().getResourceAsStream("/img/geziaBera.png")));
            } else {
                imgView.setImage(new javafx.scene.image.Image(
                        getClass().getResourceAsStream("/img/geziaGora.png")));
            }

            // Marcar fila correspondiente en rojo
            if (ahaleginak <= gridPaneAkats.getRowCount()) {
                for (int col = 0; col < gridPaneAkats.getColumnCount(); col++) {
                    gridPaneAkats.getChildren().forEach(node -> {
                        Integer rowIndex = GridPane.getRowIndex(node);
                        if (rowIndex != null && rowIndex == ahaleginak - 1) {
                            node.setStyle("-fx-background-color: red;");
                        }
                    });
                }
            }

            if (ahaleginak >= gridPaneAkats.getRowCount()) {
                // Último intento, jugador pierde
                hautatutakoJokalaria.setGalduta(hautatutakoJokalaria.getGalduta() + 1);
                tblJokalariak.refresh();
                erakutsiInfoAlerta("GALDU DUZU! Zenbakia: " + zenbakiSekretua);
            }
        }
    }

    private int kalkulaPuntuazioa(int ahaleginak) {
        switch (ahaleginak) {
            case 1:
                return 5;
            case 2:
                return 4;
            case 3:
                return 3;
            case 4:
                return 2;
            case 5:
                return 1;
            default:
                return 0;
        }
    }

    private void erakutsiAlerta(String mezua) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("OHARRA");
        alert.setHeaderText(null);
        alert.setContentText(mezua);
        alert.showAndWait();
    }

    private void erakutsiInfoAlerta(String mezua) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMAZIOA");
        alert.setHeaderText(null);
        alert.setContentText(mezua);
        alert.showAndWait();
    }

}
