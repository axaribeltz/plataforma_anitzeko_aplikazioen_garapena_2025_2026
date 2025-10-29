module com.mycompany.jsonariketak {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    opens models;
    opens com.mycompany.jsonariketak to javafx.fxml;
    exports com.mycompany.jsonariketak;
    requires com.google.gson;
}
