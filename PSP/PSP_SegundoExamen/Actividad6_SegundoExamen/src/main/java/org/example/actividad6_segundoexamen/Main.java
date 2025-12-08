package org.example.actividad6_segundoexamen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("activity_carrera_de_caballos.fxml"));

        primaryStage.setTitle("Carrera de Caballitos");
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
