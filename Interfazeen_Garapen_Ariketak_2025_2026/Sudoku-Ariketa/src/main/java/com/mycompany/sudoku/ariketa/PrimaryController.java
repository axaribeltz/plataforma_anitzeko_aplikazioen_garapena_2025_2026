/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.sudoku.ariketa;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    private JsonArray tableros;
    private final int[][] solucion = new int[9][9];
    private TextField celdaSeleccionada = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crearCeldas();  // Sudoku taula sortu
        cargarSudokusDesdeJSON(); // JSONetik datuak kargatu
        generarSudoku(); // Sudoku bat aukeratu eta erakutsi
    }

    private void crearCeldas() {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                TextField tf = new TextField();
                tf.setPrefSize(40, 40);
                tf.setAlignment(Pos.CENTER);
                tf.getStyleClass().add("text-field-sudoku");

                // Borden lodiera kalkulatzeko
                int top = (fila % 3 == 0) ? 3 : 1;
                int left = (col % 3 == 0) ? 3 : 1;
                int bottom = (fila == 8) ? 3 : 1;
                int right = (col == 8) ? 3 : 1;

                tf.setStyle("-fx-border-color: grey; "
                        + "-fx-border-width: " + top + " " + right + " " + bottom + " " + left + "; "
                        + "-fx-font-size: 12px;");

                // Aukeraketa klikarekin
                tf.setOnMouseClicked(e -> {
                    if (tf.isEditable()) {
                        if (celdaSeleccionada != null) {
                            celdaSeleccionada.getStyleClass().remove("selected");
                        }
                        celdaSeleccionada = tf;
                        tf.getStyleClass().add("selected");
                    }
                });

                gridTextFields[fila][col] = tf;
                grid.add(tf, col, fila);
            }
        }
    }

    private void cargarSudokusDesdeJSON() {
        try (InputStream is = getClass().getResourceAsStream("/json/sudoku.json")) {
            if (is != null) {
                JsonElement root = JsonParser.parseReader(new InputStreamReader(is));
                tableros = root.getAsJsonArray();
            } else {
                System.err.println("No se encontró sudoku.json en resources/");
                tableros = new JsonArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
            tableros = new JsonArray();
        }
    }

    private void generarSudoku() {
        if (tableros == null || tableros.size() == 0) {
            return;
        }

        int index = new Random().nextInt(tableros.size());
        JsonArray sudoku = tableros.get(index).getAsJsonObject().getAsJsonArray("sudoku");

        for (int i = 0; i < 81; i++) {
            int fila = i / 9;
            int col = i % 9;

            JsonObject celda = sudoku.get(i).getAsJsonObject();
            String valor = celda.get("balorea").getAsString();
            String estado = celda.get("egoera").getAsString();

            TextField tf = gridTextFields[fila][col];
            tf.getStyleClass().removeAll("fixed", "selected", "correct", "incorrect", "invalid");

            solucion[fila][col] = Integer.parseInt(valor);

            if (estado.equalsIgnoreCase("IKUSI")) {
                tf.setText(valor);
                tf.setEditable(false);
                tf.getStyleClass().add("fixed");
            } else {
                tf.setText("");
                tf.setEditable(true);
            }
        }
    }

    @FXML
    private void onNumberPress(ActionEvent event) {
        if (celdaSeleccionada == null) {
            return;
        }

        String numero = ((javafx.scene.control.Button) event.getSource()).getText();
        celdaSeleccionada.setText(numero);
        celdaSeleccionada.getStyleClass().removeAll("correct", "incorrect", "invalid");
    }

    @FXML
    private void onLoadPuzzle(ActionEvent event) {
        generarSudoku();
    }

    @FXML
    private void onSolve(ActionEvent event) {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                TextField tf = gridTextFields[fila][col];
                tf.setText(String.valueOf(solucion[fila][col]));
                tf.getStyleClass().removeAll("selected", "correct", "incorrect", "invalid");
                tf.getStyleClass().add("fixed");
            }
        }
    }

    @FXML
    private void onCheck(ActionEvent event) {
        boolean correcto = true;
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                TextField tf = gridTextFields[fila][col];
                if (!tf.isEditable()) {
                    continue;
                }

                tf.getStyleClass().removeAll("correct", "incorrect", "invalid");
                String text = tf.getText().trim();
                try {
                    int val = Integer.parseInt(text);
                    if (val == solucion[fila][col]) {
                        tf.getStyleClass().add("correct");
                    } else {
                        tf.getStyleClass().add("incorrect");
                        correcto = false;
                    }
                } catch (Exception e) {
                    tf.getStyleClass().add("invalid");
                    correcto = false;
                }
            }
        }
        System.out.println(correcto ? "¡Sudoku correcto!" : "Hay errores.");
    }
}
