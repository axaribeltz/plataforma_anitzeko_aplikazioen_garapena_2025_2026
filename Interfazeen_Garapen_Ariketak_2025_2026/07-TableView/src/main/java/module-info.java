module com.mycompany.tableview {
    requires javafx.controls;
    requires javafx.fxml;
    opens models;
    opens com.mycompany.tableview to javafx.fxml;
    exports com.mycompany.tableview;
}
