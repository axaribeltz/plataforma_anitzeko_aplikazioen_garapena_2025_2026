package com.mycompany.azt2526_1_play_with_words_ikasle;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.User;

public class GameController implements Initializable {

    @FXML
    private Label userName;
    @FXML
    private Label userScore;
    @FXML
    private Label pointsAtPlay;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnStart;
    @FXML
    private Button newGame;
    @FXML
    private Button btnLetter;
    @FXML
    private Button btnSolve;
    @FXML
    private TextField lblLetter; 
    @FXML
    private TextField lblWord;
    @FXML
    private GridPane gridPane;

    private final User gameUser = new User("Aritz", "12345", "player", 123);

    private final String[] wordsArray = {
        "mendebaldea", "argitasuna", "zientzia", "ikastetxea", "liburutegia",
        "egunkaria", "gizarte", "hezkuntza", "irakasleak", "euskarazko",
        "berdintasuna", "kulturala", "herritarra", "elkarrizketa", "ekintzailea",
        "garapena", "komunikazioa", "ikerketa", "teknologia", "adiskidetasun",
        "berrikuntza", "ingurumena", "hizkuntza", "elkartea", "proiektua",
        "aurkezpena", "sormena", "jarduera", "bizikidetza", "gogoeta"
    };

    private String hitzaRandom;
    private TextField[] gridTextFields;
    private int puntuJokoan;
    private boolean partidaAktibo = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUserDate(gameUser);
        getNewWord();
        crearCeldas();
        partidaAktibo = true;
    }

    private void showUserDate(User gameUser) {
        userName.setText(gameUser.getNick());
        userScore.setText(String.valueOf(gameUser.getScore()));
    }

    private void crearCeldas() {
        gridPane.getChildren().clear();
        gridTextFields = new TextField[hitzaRandom.length()];
        for (int col = 0; col < hitzaRandom.length(); col++) {
            TextField tf = new TextField();
            tf.setPrefSize(100, 100);
            tf.setAlignment(Pos.CENTER);
            tf.setStyle("-fx-border-color: blue; -fx-border-width: 1;");
            tf.setEditable(false);
            gridTextFields[col] = tf;
            gridPane.add(tf, col, 0);
        }
    }

    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void btnStart(ActionEvent event) {
        partidaAktibo = true;
        getNewWord();
        crearCeldas();
    }

    @FXML
    private void btnReset(ActionEvent event) {
        getNewWord();
        crearCeldas();
    }

    @FXML
    private void SwitchLetter(ActionEvent event) {
        if (!partidaAktibo) {
            Alert("Hasi lehenengo partida bat.");
            return;
        }

        String letraIngresada = lblLetter.getText().trim().toLowerCase();
        if (letraIngresada.isEmpty()) {
            Alert("Ez dago karektarik idatzita");
            return;
        }
        if (letraIngresada.length() > 1) {
            Alert("Karaktere bat bakarrik sartu behar duzu.");
            return;
        }

        char letra = letraIngresada.charAt(0);
        boolean letraAcertada = false;

        for (int i = 0; i < hitzaRandom.length(); i++) {
            if (hitzaRandom.charAt(i) == letra) {
                gridTextFields[i].setText(String.valueOf(letra));
                letraAcertada = true;
            }
        }

        if (!letraAcertada) {
            puntuJokoan--;
            pointsAtPlay.setText(String.valueOf(puntuJokoan));
            Alert("Letra hori ez dago hitzean. Puntu bat galdu duzu.");
            if (puntuJokoan <= 0) {
                Alert("Galdu duzu partida!");
                partidaAktibo = false;
            }
        }

        lblLetter.clear();

        // Comprobamos si ha ganado
        if (haGanado()) {
            InfoAlert("ZORIONAK! Asmatuta!");
            partidaAktibo = false;
            gameUser.setScore(gameUser.getScore() + 1);
            userScore.setText(String.valueOf(gameUser.getScore()));
        }
    }

    @FXML
    private void SwitchSolve(ActionEvent event) {
        if (!partidaAktibo) {
            Alert("Hasi lehenengo partida bat.");
            return;
        }

        String enteredWord = lblWord.getText().trim().toLowerCase();
        if (enteredWord.isEmpty()) {
            Alert("Idatzi hitza lehenengo.");
            return;
        }

        if (enteredWord.equals(hitzaRandom)) {
            InfoAlert("CORRECT!!! YOU HAVE 1 MORE POINT");
            gameUser.setScore(gameUser.getScore() + 1);
            userScore.setText(String.valueOf(gameUser.getScore()));
        } else {
            Alert("You lost");
        }

        partidaAktibo = false;
        lblWord.clear();
    }

    private boolean haGanado() {
        for (int i = 0; i < hitzaRandom.length(); i++) {
            if (gridTextFields[i].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void getNewWord() {
        hitzaRandom = wordsArray[new Random().nextInt(wordsArray.length)];
        puntuJokoan = hitzaRandom.length();
        pointsAtPlay.setText(String.valueOf(puntuJokoan));
        System.out.println("Palabra Random: " + hitzaRandom);
    }

    private void InfoAlert(String mezua) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WINNER");
        alert.setHeaderText(null);
        alert.setContentText(mezua);
        alert.showAndWait();
    }

    private void Alert(String mezua) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("INFO");
        alert.setHeaderText(null);
        alert.setContentText(mezua);
        alert.showAndWait();
    }
}