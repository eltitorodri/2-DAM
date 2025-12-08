package org.example.actividad9_segundoexamen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerPantalla {

    @FXML private Button botonComenzar;
    @FXML private Button botonInterrumpirPrimero;
    @FXML private Button botonInterrumpirSegundo;
    @FXML private Button botonFinalizar;

    @FXML private TextField primerTextField;
    @FXML private TextField segundoTextField;

    @FXML private Label primerLabel;
    @FXML private Label segundoLabel;

    private MiHilo hilo1;
    private MiHilo hilo2;

    @FXML
    public void initialize() {

        primerLabel.setText("Parado");
        segundoLabel.setText("Parado");
        primerTextField.setText("0");
        segundoTextField.setText("0");

        botonInterrumpirPrimero.setDisable(true);
        botonInterrumpirSegundo.setDisable(true);
        botonFinalizar.setDisable(true);

    }

    @FXML
    public void comenzarProcesos() {

        // Nota: la cantidad de milisegundos debe ser diferente para demostrar concurrencia
        hilo1 = new MiHilo("Hilo 1", 500, primerTextField);
        hilo2 = new MiHilo("Hilo 2", 500, segundoTextField);

        hilo1.start();
        hilo2.start();

        botonComenzar.setDisable(true);

        botonInterrumpirPrimero.setDisable(false);
        botonInterrumpirSegundo.setDisable(false);
        botonFinalizar.setDisable(false);

        primerLabel.setText("Corriendo");
        segundoLabel.setText("Corriendo");

    }

    @FXML public void interrumpirHilo1() {
        // En la actividad 9, Interrumpir significa detener el hilo.
        hilo1.interrupt();
        primerLabel.setText("Interrumpido");

        // Desactivar el botón para que no se pueda interrumpir de nuevo un hilo ya detenido
        botonInterrumpirPrimero.setDisable(true);
    }

    @FXML public void interrumpirHilo2() {
        // En la actividad 9, Interrumpir significa detener el hilo.
        hilo2.interrupt();
        segundoLabel.setText("Interrumpido");

        botonInterrumpirSegundo.setDisable(true);
    }

    @FXML public void finalizarProcesos() {

        // Detener los hilos de forma controlada
        hilo1.detenerHilo();
        hilo2.detenerHilo();

        System.out.println("-----PROCESOS FINALIZADOS-----");
        System.out.println("Valor final del hilo 1: " + hilo1.getContador());
        System.out.println("Valor final del hilo 2: " + hilo2.getContador());
        System.out.println("------------------------------");

        primerLabel.setText("Finalizado");
        segundoLabel.setText("Finalizado");

        // Desactivamos todos los controles al finalizar
        botonInterrumpirPrimero.setDisable(true);
        botonInterrumpirSegundo.setDisable(true);
        botonFinalizar.setDisable(true);
    }

}