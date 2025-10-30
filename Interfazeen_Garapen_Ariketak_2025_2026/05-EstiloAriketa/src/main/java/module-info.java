module com.mycompany.estiloariketa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.estiloariketa to javafx.fxml;
    exports com.mycompany.estiloariketa;
}
