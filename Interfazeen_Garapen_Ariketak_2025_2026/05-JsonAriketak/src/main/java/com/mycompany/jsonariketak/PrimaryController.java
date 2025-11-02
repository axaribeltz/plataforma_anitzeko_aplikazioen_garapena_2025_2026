package com.mycompany.jsonariketak;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Pertsona;

public class PrimaryController implements Initializable {

    @FXML
    private TextField txtIzena;
    @FXML
    private TextField txtAbizena;
    @FXML
    private TextField txtAdina;
    @FXML
    private TextArea txtAreaAfizioak;
    @FXML
    private Button btnExit;
    @FXML
    private MenuButton menuButton;

    private List<Pertsona> pertsonak;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        kargatuPertsonak();
    }

    private void kargatuPertsonak() {
        try (InputStream pertsonakJson = getClass().getResourceAsStream("/json/pertsonak.json")) {
            if (pertsonakJson == null) {
                System.err.println("Ez da aurkitu pertsonak.json");
                return;
            }

            java.lang.reflect.Type pertsonaListaTipoa = new TypeToken<List<Pertsona>>() {}.getType();
            pertsonak = new Gson().fromJson(new InputStreamReader(pertsonakJson), pertsonaListaTipoa);

            // Gogoratu garbitzeaz
            menuButton.getItems().clear();
            for (Pertsona b : pertsonak) {
                MenuItem item = new MenuItem(b.getIzena() + " " + b.getAbizena());
                item.setOnAction(e -> erakutsiPertsonarenDatuak(b));
                menuButton.getItems().add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void erakutsiPertsonarenDatuak(Pertsona b) {
        txtIzena.setText(b.getIzena());
        txtAbizena.setText(b.getAbizena());
        txtAdina.setText(String.valueOf(b.getAdina()));
        txtAreaAfizioak.setText(String.join("\n", b.getAfizioa()));

        // Aldatu textua segun eta ze izenetan klikatu
        menuButton.setText(b.getIzena());
    }

    @FXML
    private void btnExit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
