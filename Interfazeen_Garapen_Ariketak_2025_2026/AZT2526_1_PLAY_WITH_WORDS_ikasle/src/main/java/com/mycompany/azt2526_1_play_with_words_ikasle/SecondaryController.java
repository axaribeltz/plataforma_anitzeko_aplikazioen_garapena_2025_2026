package com.mycompany.azt2526_1_play_with_words_ikasle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.User;

public class SecondaryController implements Initializable {
    @FXML
    private TableColumn<User, String> colNick;
    @FXML
    private TableColumn<User, Integer> colScore;
    @FXML
    private TableColumn<User, String> colType;
    @FXML
    private Label titleLabel;
    @FXML
    private TableView<User> tblUsers;
    
    private final Gson gson = new Gson();
    private ObservableList<User> usersList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadPertsons();
    }
        
    private void loadPertsons() {
        try (InputStream wordUsersJson = getClass().getResourceAsStream("/json/wordUsers.json")) {
            if (wordUsersJson == null) {
                System.err.println("Ez da aurkitu User.json");
                mostrarAlertError();
                return;
            }
            java.lang.reflect.Type usersListType = new TypeToken<List<User>>() {}.getType();
            List<User> users = gson.fromJson(new InputStreamReader(wordUsersJson), usersListType);

            usersList = FXCollections.observableList(users);

            colNick.setCellValueFactory(new PropertyValueFactory<>("nick"));
            colType.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            colScore.setCellValueFactory(new PropertyValueFactory<>("score"));

            tblUsers.setItems(usersList);

        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    private void mostrarAlertError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error reading file");
        alert.setTitle("Error");
        alert.setContentText("Error reading file");
        alert.showAndWait();
    }
}