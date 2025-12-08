package org.example.actividad6_segundoexamen;

import javafx.scene.control.ProgressBar;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class HiloCorredor implements Runnable {

    private final String nombre;
    private final long tiempoDormido;
    private final ProgressBar progressBar;
    private final Label contadorText;
    private final int meta = 1000;
    private int contador = 0;
    private final AppController controller;

    public HiloCorredor(String nombre, long tiempoDormido, ProgressBar progressBar,
                        Label contadorText, AppController controller) {
        this.nombre = nombre;
        this.tiempoDormido = tiempoDormido;
        this.progressBar = progressBar;
        this.contadorText = contadorText;
        this.controller = controller;
    }

    @Override
    public void run() {

        try {
            while (contador < meta) {
                Thread.sleep(tiempoDormido);

                contador++;

                Platform.runLater(()-> {
                    progressBar.setProgress((double) contador / meta);
                    contadorText.setText("Contador: " + contador);
                });
            }

            Platform.runLater(() -> {
                controller.carreraFinalizada(nombre);
            });

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Thread getThread() {
        return new Thread(this, nombre);
    }

}