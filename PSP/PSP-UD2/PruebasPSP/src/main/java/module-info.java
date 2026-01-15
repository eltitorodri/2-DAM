module org.example.pruebaspsp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.pruebaspsp to javafx.fxml;
    exports org.example.pruebaspsp;
}