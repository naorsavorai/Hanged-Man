module com.example.maman13 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.maman13 to javafx.fxml;
    exports com.example.maman13;
}