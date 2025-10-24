module com.mycompany.css {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    opens modelo;
    opens com.mycompany.css to javafx.fxml;
    exports com.mycompany.css;
}
