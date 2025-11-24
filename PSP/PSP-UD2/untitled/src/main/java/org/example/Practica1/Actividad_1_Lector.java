package org.example.Practica1;

public class Actividad_1_Lector {

    public static void main(String[] args) {

        //Crear y lanzar 5 hilos
        for (int i = 0; i < 5; i++) {
            Actividad_1 hilo = new Actividad_1();
            hilo.start();
        }

    }

}
