module com.mycompany.sudoku.ariketa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    opens com.mycompany.sudoku.ariketa to javafx.fxml;
    exports com.mycompany.sudoku.ariketa;
    requires com.google.gson;
}
