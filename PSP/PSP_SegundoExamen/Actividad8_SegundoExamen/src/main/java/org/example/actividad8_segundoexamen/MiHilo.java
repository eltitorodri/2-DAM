package org.example.actividad8_segundoexamen;

import javafx.application.Platform;
import javafx.scene.control.TextField;

public class MiHilo extends Thread{

    private volatile boolean suspendido = false;
    private volatile boolean detenido = false;
    private final int sleepTime;
    private int contador = 0;
    private final String nombre;

    private final TextField textContador; // Referencia al campo de texto del contador

    public MiHilo(String nombre, int sleepTime, TextField textContador) {
        this.nombre = nombre;
        this.sleepTime = sleepTime;
        this.textContador = textContador;
    }

    public int getContador() {
        return contador;
    }

    public void suspenderHilo() {
        this.suspendido = true;
    }

    public void detenerHilo() {
        this.detenido = true;
        reanudarHilo(); // Necesario para sacar al hilo del wait() si estaba suspendido
    }

    public synchronized void reanudarHilo() {
        this.suspendido = false;
        notify(); // Notifica al hilo que estaba esperando (wait)
    }

    @Override
    public void run() {
        try {
            while (!detenido) {
                synchronized (this) {
                    while (suspendido) {
                        wait(); // El hilo espera si está suspendido
                    }
                }

                contador++;
                // Actualizar la interfaz gráfica desde el hilo de trabajo
                Platform.runLater(() -> {
                    textContador.setText(String.valueOf(contador));
                });

                System.out.println(nombre + " contador = " + contador );
                Thread.sleep(sleepTime);
            }
        }catch (InterruptedException e){
            // Manejo de interrupción
        }

        System.out.println(nombre + " finalizado. Contador = " + contador);
        // Aseguramos que el TextField muestre el valor final
        Platform.runLater(() -> {
            textContador.setText(String.valueOf(contador));
        });
    }
}