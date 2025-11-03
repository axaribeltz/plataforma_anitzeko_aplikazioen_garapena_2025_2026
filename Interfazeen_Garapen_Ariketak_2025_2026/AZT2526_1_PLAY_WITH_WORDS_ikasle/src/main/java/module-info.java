module com.mycompany.azt2526_1_play_with_words_ikasle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.google.gson;
    opens models;
    opens com.mycompany.azt2526_1_play_with_words_ikasle to javafx.fxml;
    exports com.mycompany.azt2526_1_play_with_words_ikasle;
    
}
