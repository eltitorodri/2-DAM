package org.example.examenpractico_segundo;

import javafx.scene.control.Label;

public class Cultivo {

    private String estado = "Sin Plantar";
    private int nivelCrecimiento = 0;
    private boolean necesidad;
    private Label label;

    // Setter para asociar el label de la UI
    public void setLabel(Label label) {
        this.label = label;
        cambiarEstado(); // Inicializa el color del label
    }

    public synchronized void cambiarEstado() {
        if (estado.equals("Sin Plantar")) {
            label.setStyle("-fx-background-color: #a55f2a; -fx-text-fill: black;");
        } else if (estado.equals("Crecimiento activo")) {
            label.setStyle("-fx-background-color: #00ff00; -fx-text-fill: white;");
        } else if (estado.equals("Listo")) {
            label.setStyle("-fx-background-color: #e3c700; -fx-text-fill: black;");
        }
    }

    public void marcarNecesidad() {
        necesidad = true;
    }

    public void regar() {
        necesidad = false;
    }

    public synchronized boolean necesidadAgua() {
        return necesidad;
    }

    public synchronized void avanzarCrecimiento() {
        if (necesidadAgua()) {
            nivelCrecimiento++;
            if (nivelCrecimiento < 5) {
                estado = "Crecimiento activo";
            } else {
                estado = "Listo";
            }

            cambiarEstado();
            regar(); // Una vez regado, ya no necesita agua
        }
    }

    // Getter y setter para estado, útil en Controller
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
