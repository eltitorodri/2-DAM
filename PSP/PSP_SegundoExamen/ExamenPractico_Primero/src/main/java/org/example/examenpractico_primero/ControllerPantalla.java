package org.example.examenpractico_primero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPantalla implements Initializable {

    @FXML private Label semaforoUno;
    @FXML private Label semaforoDos;
    @FXML private Label semaforoTres;
    @FXML private Label semaforoCuatro;
    @FXML private Label letraA;
    @FXML private Label letraE;
    @FXML private Button comenzarTrafico;

    private Semaforo semaHorizontal1;
    private Semaforo semaHorizontal3;
    private Semaforo semaVertical2;
    private Semaforo semaVertical4;

    private Timeline timelineSemaforos;
    private boolean horizontalVerde = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        semaHorizontal1 = new Semaforo(semaforoUno, true);
        semaHorizontal3 = new Semaforo(semaforoTres, true);
        semaVertical2 = new Semaforo(semaforoDos, false);
        semaVertical4 = new Semaforo(semaforoCuatro, false);

        timelineSemaforos = new Timeline(new KeyFrame(Duration.seconds(3), this::cambiarSemaforos));
        timelineSemaforos.setCycleCount(Timeline.INDEFINITE);
    }

    private void cambiarSemaforos(ActionEvent event) {
        horizontalVerde = !horizontalVerde;
        semaHorizontal1.setVerde(horizontalVerde);
        semaHorizontal3.setVerde(horizontalVerde);
        semaVertical2.setVerde(!horizontalVerde);
        semaVertical4.setVerde(!horizontalVerde);
    }

    @FXML
    private void comenzarTrafico(ActionEvent event) {
        timelineSemaforos.play();

        Vehiculo vehA = new Vehiculo(letraA, 0, 1, 2, semaVertical2);
        Vehiculo vehE = new Vehiculo(letraE, 1, 0, 2, semaHorizontal1);

        vehA.start();
        vehE.start();
    }
}
