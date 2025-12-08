package org.example.examenpractico_segundo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("diseño.fxml")); // Cambia "granja.fxml" por tu FXML real
        AnchorPane root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Granja Automatizada");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
