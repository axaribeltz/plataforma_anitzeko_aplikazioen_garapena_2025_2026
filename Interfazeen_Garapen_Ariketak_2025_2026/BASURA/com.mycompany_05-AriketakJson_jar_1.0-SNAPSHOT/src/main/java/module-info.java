module com.mycompany.ariketakjson {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    opens models;
    opens com.mycompany.ariketakjson to javafx.fxml;
    exports com.mycompany.ariketakjson;
    requires com.google.gson;
}
