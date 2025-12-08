package org.example.actividad8_segundoexamen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml_actividad_ocho.fxml"));
        Parent root = fxmlLoader.load();

        ControllerPantalla controller = fxmlLoader.getController();

        // El cierre de la ventana hace lo mismo que Finalizar Proceso
        stage.setOnCloseRequest(e -> {
            if (controller != null) {
                controller.finalizarProcesos();
            }
            // Permite que la aplicación termine
            Platform.exit();
        });

        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Control de Hilos");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}