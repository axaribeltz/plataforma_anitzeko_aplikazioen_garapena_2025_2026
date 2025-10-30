/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.sudoku.ariketa;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author 2AM3-4
 */
public class PrimaryController implements Initializable {

    @FXML
    private GridPane grid;

    private final TextField[][] gridTextFields = new TextField[9][9];

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                TextField tf = new TextField();
                tf.setPrefSize(100, 30);
                tf.setAlignment(Pos.CENTER);
                tf.setStyle("-fx-border-color: gray; -fx-font-size: 8px;");
                gridTextFields[fila][col] = tf;
                grid.add(tf, col, fila);
            }
        }
    }
}
