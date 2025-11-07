module com.mycompany.sudoku.bestemodu {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.sudoku.bestemodu to javafx.fxml;
    exports com.mycompany.sudoku.bestemodu;
}
