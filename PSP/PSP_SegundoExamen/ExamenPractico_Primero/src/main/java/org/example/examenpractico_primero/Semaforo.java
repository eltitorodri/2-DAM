package org.example.examenpractico_primero;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Semaforo {
    private Label label;
    private boolean verde;

    public Semaforo(Label label, boolean inicialVerde) {
        this.label = label;
        this.verde = inicialVerde;
        actualizarLabel();
    }

    public synchronized boolean isVerde() {
        return verde;
    }

    public synchronized void setVerde(boolean valor) {
        verde = valor;
        Platform.runLater(this::actualizarLabel);
    }

    private void actualizarLabel() {
        if (verde) {
            label.setText("Verde");
            label.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        } else {
            label.setText("Rojo");
            label.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        }
    }
}
