module com.example.solceller {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.solceller to javafx.fxml;
    exports com.example.solceller;
}