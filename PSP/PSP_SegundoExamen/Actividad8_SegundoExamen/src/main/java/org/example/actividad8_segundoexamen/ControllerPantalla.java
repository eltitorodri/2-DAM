package org.example.actividad8_segundoexamen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerPantalla {

    // Botones de control
    @FXML private Button comenzarProcesos;
    @FXML private Button suspenderPrimer;
    @FXML private Button suspenderSegundo;
    @FXML private Button reanudarPrimer;
    @FXML private Button reanudarSegundo;
    @FXML private Button botonFinalizar;

    // Componentes de la interfaz para el estado y contador
    @FXML private TextField textContador1;
    @FXML private TextField textContador2;
    @FXML private Label labelEstado1;
    @FXML private Label labelEstado2;

    private MiHilo hilo1;
    private MiHilo hilo2;

    @FXML
    public void initialize(){
        // Inicializar estados y contadores
        labelEstado1.setText("Parado");
        labelEstado2.setText("Parado");
        textContador1.setText("0");
        textContador2.setText("0");

        // Desactivar botones de control inicialmente
        suspenderPrimer.setDisable(true);
        suspenderSegundo.setDisable(true);
        reanudarPrimer.setDisable(true);
        reanudarSegundo.setDisable(true);
        botonFinalizar.setDisable(true);
    }

    @FXML
    public void comenzarProcesos() {
        // Crear y pasar la referencia del TextField a cada hilo
        hilo1 = new MiHilo("Hilo 1", 300, textContador1);
        hilo2 = new MiHilo("Hilo 2", 500, textContador2);

        hilo1.start();
        hilo2.start();

        comenzarProcesos.setDisable(true);

        // Habilitar la suspensión y finalización
        suspenderPrimer.setDisable(false);
        suspenderSegundo.setDisable(false);
        botonFinalizar.setDisable(false);

        labelEstado1.setText("Corriendo");
        labelEstado2.setText("Corriendo");
    }

    @FXML
    public void suspenderHilo1() {
        hilo1.suspenderHilo();
        labelEstado1.setText("Suspendido");
        suspenderPrimer.setDisable(true);
        reanudarPrimer.setDisable(false);
    }

    @FXML
    public void suspenderHilo2() {
        hilo2.suspenderHilo();
        labelEstado2.setText("Suspendido");
        suspenderSegundo.setDisable(true);
        reanudarSegundo.setDisable(false);
    }

    @FXML
    public void reanudarHilo1() {
        hilo1.reanudarHilo();
        labelEstado1.setText("Corriendo");
        reanudarPrimer.setDisable(true);
        suspenderPrimer.setDisable(false);
    }

    @FXML
    public void reanudarHilo2() {
        hilo2.reanudarHilo();
        labelEstado2.setText("Corriendo");
        reanudarSegundo.setDisable(true);
        suspenderSegundo.setDisable(false);
    }

    @FXML
    public void finalizarProcesos() {
        // Detener los hilos
        hilo1.detenerHilo();
        hilo2.detenerHilo();

        // Imprimir resultados en consola
        System.out.println("--- Proceso Finalizado ---");
        System.out.println("Valor final Hilo 1 = " + hilo1.getContador());
        System.out.println("Valor final Hilo 2 = " + hilo2.getContador());
        System.out.println("--------------------------");

        // Actualizar el estado en la interfaz
        labelEstado1.setText("Finalizado");
        labelEstado2.setText("Finalizado");

        // Desactivar todos los controles de operación
        suspenderPrimer.setDisable(true);
        suspenderSegundo.setDisable(true);
        reanudarPrimer.setDisable(true);
        reanudarSegundo.setDisable(true);
        botonFinalizar.setDisable(true);
    }
}