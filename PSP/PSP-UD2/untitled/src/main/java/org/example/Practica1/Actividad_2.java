package org.example.Practica1;

public class Actividad_2 implements Runnable {

    private String mensaje;

    public Actividad_2 (String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        long idHilo = Thread.currentThread().getId();

        try {
            Thread.sleep(idHilo * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hola mundo " + mensaje + "desde el hilo " +  idHilo);
    }
}
