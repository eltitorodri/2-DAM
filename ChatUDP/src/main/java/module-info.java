module org.example.chatudp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.chatudp to javafx.fxml;
    exports org.example.chatudp;
}