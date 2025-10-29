module com.mycompany.tablapertsonakmodifikatu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    opens modelo;
    opens com.mycompany.tablapertsonakmodifikatu to javafx.fxml;
    exports com.mycompany.tablapertsonakmodifikatu;
}
