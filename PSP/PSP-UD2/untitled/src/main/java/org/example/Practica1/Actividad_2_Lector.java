package org.example.Practica1;

public class Actividad_2_Lector {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            Actividad_2 segundoHilo = new Actividad_2("mensaje" + i + " ");
            Thread hilo = new Thread(segundoHilo);
            segundoHilo.run();
        }
    }
}