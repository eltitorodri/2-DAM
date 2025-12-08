package org.example.actividad10_segundoexamen;

import org.example.actividad10_segundoexamen.Almacen;

import java.nio.Buffer;

public class Consumidor extends Thread {
    private final Almacen almacen;

    public Consumidor(Almacen almacen, String nombre) {
        super(nombre); // Asigna un nombre al hilo
        this.almacen = almacen;
    }

    @Override
    public void run() {
        char c;
        try {
            // Bucle infinito que espera por datos hasta recibir la señal.
            while (true) {
                c = almacen.get(); // Bloquea si el búfer está vacío

                // 1. Verifica si es la señal de finalización
                if (c == almacen.getSenalFinalizacion()) {
                    break; // Sale del bucle y finaliza el hilo
                }

                // 2. Visualiza el dato
                System.out.println("[" + getName() + "] Consumido: " + c);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 3. Mensaje de finalización
        System.out.println("\n*** " + getName() + " HA FINALIZADO SU PROCESO. ***");
    }
}