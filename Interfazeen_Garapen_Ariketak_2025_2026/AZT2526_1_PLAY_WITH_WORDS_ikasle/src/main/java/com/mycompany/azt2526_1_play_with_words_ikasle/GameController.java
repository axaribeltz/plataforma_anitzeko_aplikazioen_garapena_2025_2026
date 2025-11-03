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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

    String[] wordsArray = {"mendebaldea", "argitasuna", "zientzia", "ikastetxea", "liburutegia", "egunkaria", "gizarte", "hezkuntza", "irakasleak", "euskarazko", "berdintasuna", "kulturala", "herritarra", "elkarrizketa", "ekintzailea", "garapena", "komunikazioa", "ikerketa", "teknologia", "adiskidetasun", "berrikuntza", "ingurumena", "hizkuntza", "elkartea", "proiektua", "aurkezpena", "sormena", "jarduera", "bizikidetza", "gogoeta"};
    String hitzaRandom = wordsArray[new Random().nextInt(wordsArray.length)];
        
    String hitza = "mendebaldea";
    int karaktereKopurua = hitzaRandom.length();
    private final TextField[] gridTextFields = new TextField[karaktereKopurua];

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Random hitza: " + hitzaRandom);
        showUserDate(gameUser);
        crearCeldas();
    }

    private void crearCeldas() {
        for (int col = 0; col < karaktereKopurua; col++) {
            TextField tf = new TextField();
            tf.setPrefSize(100, 100);
            tf.setAlignment(Pos.CENTER);
            tf.setStyle("-fx-border-color: blue; -fx-border-width: 1;");
            gridTextFields[col] = tf;
            gridPane.add(tf, col, 0);
        }
    }

    private void showUserDate(User gameUser) {
        userName.setText(gameUser.getNick());
        userScore.setText(String.valueOf(gameUser.getScore()));
        pointsAtPlay.setText(String.valueOf(karaktereKopurua));
    }

    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void btnStart(ActionEvent event) {
        getNewWord();
    }

    @FXML
    private void btnReset(ActionEvent event) {
        getNewWord();
    }

    @FXML
    private void SwitchLetter(ActionEvent event) {
    }

    @FXML
    private void SwitchSolve(ActionEvent event) {
        String enteredWord = lblWord.getText();
            
        if (enteredWord.equals(hitzaRandom)) {
            InfoAlert("CORRECT!!! YOU HAVE 1 MORE POINTS");
        } else {
            Alert("You lost");
        }
    }
    
    private void getNewWord(){
        hitzaRandom = wordsArray[new Random().nextInt(wordsArray.length)];
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
        alert.setTitle("LOSSER");
        alert.setHeaderText(null);
        alert.setContentText(mezua);
        alert.showAndWait();
    }

}
