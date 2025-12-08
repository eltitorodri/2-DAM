package org.example.actividad9_segundoexamen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("diseño.fxml"));
        Parent root = fxmlLoader.load();

        ControllerPantalla controller =  fxmlLoader.getController();

        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Actividad 9");
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
