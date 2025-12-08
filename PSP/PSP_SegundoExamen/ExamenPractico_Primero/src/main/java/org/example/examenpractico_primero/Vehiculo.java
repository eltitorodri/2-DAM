package org.example.examenpractico_primero;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Vehiculo extends Thread {
    private Label label;
    private double dx;
    private double dy;
    private double velocidad;
    private Semaforo semaforo;

    public Vehiculo(Label label, double dx, double dy, double velocidad, Semaforo semaforo) {
        this.label = label;
        this.dx = dx;
        this.dy = dy;
        this.velocidad = velocidad;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (semaforo) {
                if (!semaforo.isVerde()) {
                    continue;
                }
            }

            Platform.runLater(() -> {
                label.setLayoutX(label.getLayoutX() + dx * velocidad);
                label.setLayoutY(label.getLayoutY() + dy * velocidad);
            });
        }
    }
}
