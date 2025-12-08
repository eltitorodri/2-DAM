package org.example.actividad9_segundoexamen;

import javafx.application.Platform;
import javafx.scene.control.TextField;

public class MiHilo extends Thread{

    private volatile boolean suspendido;
    private volatile boolean detenido;
    private final int sleepTime;
    private int contador = 0;
    private final String nombre;
    private final TextField texto;

    public MiHilo(String nombre, int sleepTime, TextField texto) {
        this.nombre = nombre;
        this.sleepTime = sleepTime;
        this.texto = texto;
    }

    public int getContador() {
        return contador;
    }

    public void detenerHilo() {
        this.detenido = true;
        interrupt();
    }

    @Override
    public void run() {
        try {
            while (!detenido && !Thread.currentThread().isInterrupted()) {

                contador++;

                // Actualizar la interfaz gráfica
                Platform.runLater(() -> {
                    texto.setText(String.valueOf(contador));
                });

                System.out.println(nombre + " corriendo. Contador: "+ contador);

                // Si Thread.sleep() es interrumpido, lanza InterruptedException
                Thread.sleep(sleepTime);
            }
        }catch (InterruptedException e) {
            System.out.println(nombre + " interrumpido mientras dormía.");
        }

        System.out.println(nombre + " finalizado. Contador: "+ contador);
        Platform.runLater(() -> {
            texto.setText(String.valueOf(contador));
        });

    }

}
