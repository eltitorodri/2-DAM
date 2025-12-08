package org.example.examenpractico_segundo;

import java.util.List;

public class SistemaRiego {

    private List<Cultivo> cultivos;
    private int duracion;    // Opcional: duración del riego
    private int intervalo;   // Opcional: intervalo entre riegos
    private boolean riegoActivo;

    // Setter para asociar la lista de cultivos
    public void setCultivos(List<Cultivo> cultivos) {
        this.cultivos = cultivos;
    }

    public synchronized void regarParcela(Cultivo c) {
        if (c.necesidadAgua()) {
            c.regar();
            c.cambiarEstado();
            notifyAll(); // Para futuros hilos
        }
    }

    public synchronized void verificarNecesidad() {
        for (Cultivo c : cultivos) {
            if (c.necesidadAgua()) {
                c.cambiarEstado();
                notifyAll();
            }
        }
    }

    public synchronized void regarTodasNecesidades() {
        for (Cultivo c : cultivos) {
            if (c.necesidadAgua()) {
                regarParcela(c);
            }
        }
    }
}
