module com.example.searchingproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.searchingproject to javafx.fxml;
    exports com.example.searchingproject;
}