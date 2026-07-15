module com.example.project_paclibar {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.project_paclibar.controller to javafx.fxml;
    opens com.example.project_paclibar to javafx.fxml;
    exports com.example.project_paclibar;
}