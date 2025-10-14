module com.mycompany.estiloariketak {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.estiloariketak to javafx.fxml;
    exports com.mycompany.estiloariketak;
}
