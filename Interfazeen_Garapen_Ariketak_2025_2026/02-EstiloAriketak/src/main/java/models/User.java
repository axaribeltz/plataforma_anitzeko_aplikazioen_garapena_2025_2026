/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package models;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class User {
    private String nick;
    private String pass;
    private String fullName;

    public User(String nick, String pass, String fullName) {
        this.nick = nick;
        this.pass = pass;
        this.fullName = fullName;
    }

    public String getNick() {
        return nick;
    }

    public String getPass() {
        return pass;
    }

    public String getFullName() {
        return fullName;
    }
}
