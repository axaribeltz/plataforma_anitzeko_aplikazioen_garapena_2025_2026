package com.mycompany.irudiak.ariketak.aritz.ibanez;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class IrudiakMenubarController implements Initializable {

    @FXML
    private MenuItem addFile;
    @FXML
    private MenuItem seeFile;
    @FXML
    private MenuItem delete;
    @FXML
    private Button btnLeft;
    @FXML
    private Button btnRight;
    @FXML
    private ImageView img;
    @FXML
    private GridPane gridPane;

    private final List<Image> irudiak = new ArrayList<>();
    private int currentIndex = 0;

    private static final int COLUMNS = 3; // columnas para el grid
    private static final int IMAGE_SIZE = 150; // tamaño miniaturas

    @Override
    public void initialize(URL url, ResourceBundle rb) {    
    }    

    // ---------------- ADD FILE ----------------
    @FXML
    private void addFileBtn(ActionEvent event) {       
        try {
            Window window = gridPane.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("C:\\Users\\2AM3-4\\Documents\\NetBeansProjects\\Interfazeen_Garapen_Ariketak_2025_2026\\02-irudiak-ariketak-aritz-ibanez\\src\\main\\resources"));
            fileChooser.setTitle("Seleccionar imagen");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Archivos Imagen", "*.png", "*.jpg", "*.jpeg")
            );

            File selectedFile = fileChooser.showOpenDialog(window);

            if (selectedFile != null) {
                FileInputStream fis = new FileInputStream(selectedFile);
                Image image = new Image(fis);

                // Guardar en la lista
                irudiak.add(image);

                // Crear miniatura para GridPane
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(IMAGE_SIZE);
                imageView.setFitHeight(IMAGE_SIZE);
                imageView.setPreserveRatio(true);

                int index = irudiak.size() - 1;
                int row = index / COLUMNS;
                int col = index % COLUMNS;

                GridPane.setHalignment(imageView, HPos.CENTER);
                GridPane.setValignment(imageView, VPos.CENTER);
                gridPane.add(imageView, col, row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        gridPane.setVisible(true);
        
        img.setVisible(false);
        btnLeft.setVisible(false);
        btnRight.setVisible(false);
    }

    // ---------------- SEE FILE (Carrousel) ----------------
    @FXML
    private void seeFileBtn(ActionEvent event) {
        if (irudiak.isEmpty()) {
            System.out.println("No hay imágenes para mostrar.");
            return;
        }
        gridPane.setVisible(false);
        
        img.setVisible(true);
        btnLeft.setVisible(true);
        btnRight.setVisible(true);
        
        currentIndex = 0;
        img.setImage(irudiak.get(currentIndex));
    }

    // ---------------- NAVEGAR CARROUSEL ----------------
    @FXML
    private void btnGoToLeft(ActionEvent event) {
        if (!irudiak.isEmpty()) {
            currentIndex = (currentIndex - 1 + irudiak.size()) % irudiak.size();
            img.setImage(irudiak.get(currentIndex));
        }
    }

    @FXML
    private void btnGoToRight(ActionEvent event) {
        if (!irudiak.isEmpty()) {
            currentIndex = (currentIndex + 1) % irudiak.size();
            img.setImage(irudiak.get(currentIndex));
        }
    }

    // ---------------- DELETE ----------------
    @FXML
    private void deleteBtn(ActionEvent event) {
        if (!irudiak.isEmpty()) {
        
        Image imageToRemove = irudiak.get(currentIndex);

        
        irudiak.remove(currentIndex);

        
        gridPane.getChildren().removeIf(node -> {
            if (node instanceof ImageView) {
                ImageView iv = (ImageView) node;
                return iv.getImage() == imageToRemove;
            }
                return false;
            });

        
            if (irudiak.isEmpty()) {
            
                img.setImage(null);
                img.setVisible(false);
                btnLeft.setVisible(false);
                btnRight.setVisible(false);
                currentIndex = 0;
            } else {
            
                if (currentIndex >= irudiak.size()) {
                    currentIndex = irudiak.size() - 1;
                }
                img.setImage(irudiak.get(currentIndex));
            }
        }
    }
}