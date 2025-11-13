module com.mycompany.dbariketa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; 
    requires java.base;
    opens modeloa;
    opens com.mycompany.dbariketa to javafx.fxml;
    exports com.mycompany.dbariketa;
}
