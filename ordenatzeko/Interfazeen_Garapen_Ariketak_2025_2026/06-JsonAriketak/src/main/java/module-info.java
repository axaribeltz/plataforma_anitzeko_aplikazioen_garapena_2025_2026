module com.mycompany.jsonariketak {
    requires javafx.controls;
    requires javafx.fxml;
    opens models;
    opens com.mycompany.jsonariketak to javafx.fxml;
    exports com.mycompany.jsonariketak;
    requires com.google.gson;
    requires java.base;
}
