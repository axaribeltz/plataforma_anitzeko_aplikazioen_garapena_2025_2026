module com.mycompany.azterketa.proba {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    opens models;
    opens com.mycompany.azterketa.proba to javafx.fxml;
    exports com.mycompany.azterketa.proba;
    requires com.google.gson;
}
