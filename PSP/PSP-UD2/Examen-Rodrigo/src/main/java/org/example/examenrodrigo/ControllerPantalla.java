package org.example.examenrodrigo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ControllerPantalla implements Initializable {

    //Aqui declaramos los componentes del diseño en java fx

    @FXML private Label labelTiempoReal;
    @FXML private Label labelEstados;
    @FXML private Button botonIniciar;
    @FXML private Button botonDetener;


    BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
    private Productor productor;
    private Consumidor consumidor;

    //Aqui el initialize por que la extiendo Initializable
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        labelTiempoReal.setText("Esperando entrada...");
        labelEstados.setText("Estado...");
        labelTiempoReal.setText(String.valueOf(consumidor));

    }


    //Intento de hacer el action del boton comenzar
    @FXML
    private void comenzarProcesos(ActionEvent actionEvent) {
        productor = new Productor("Productor", "keys.txt",  queue );
        consumidor = new Consumidor(queue, "Consumidor");

        productor.start();
        consumidor.start();

        botonIniciar.setDisable(true);
    }


    //Intento de hacer el action del boton de parar
    @FXML
    private void detenerProcesos(ActionEvent actionEvent) {
        productor.interrupt();
        consumidor.interrupt();

        botonDetener.setDisable(true);
    }

}
