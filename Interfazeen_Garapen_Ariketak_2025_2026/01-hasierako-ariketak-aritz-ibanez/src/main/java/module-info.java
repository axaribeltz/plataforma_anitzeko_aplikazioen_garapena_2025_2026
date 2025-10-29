module com.mycompany.hasierako.ariketak.aritz.ibanez {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.hasierako.ariketak.aritz.ibanez to javafx.fxml;
    exports com.mycompany.hasierako.ariketak.aritz.ibanez;
}
