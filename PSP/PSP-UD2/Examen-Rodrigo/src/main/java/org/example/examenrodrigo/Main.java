package org.example.examenrodrigo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage  primaryStage) throws Exception {

        //Aqui enlazo el xml que he hecho en JavaFX
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("diseño.fxml"));

        //aqui lo cargo
        Parent root = fxmlLoader.load();

        //relaciono el controllerPantalla que cree con una instacio
        ControllerPantalla controller = fxmlLoader.getController();

        //aqui creo la escena con la resolucion, el titulo, y lo muestro con el show()
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Examen Astronautas");
        primaryStage.show();

    }

    //Aqui lo lanzo
    public static void main(String[] args) {
        launch(args);
    }
}

//ESTO LO TENGO AQUI POR QUE ERA LA ESTRUCTURA PRINCIPAL Y AHORA PUES LA HE PASADO

//BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
//Consumidor c = new Consumidor(queue, "Consumidor");
//Productor p = new Productor("Productor", "keys.txt",  queue );
//
//c.start();
//p.start();