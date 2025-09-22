module com.mycompany.irudiak.ariketak.aritz.ibanez {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.irudiak.ariketak.aritz.ibanez to javafx.fxml;
    exports com.mycompany.irudiak.ariketak.aritz.ibanez;
}
