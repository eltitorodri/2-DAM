package org.example.actividad10_segundoexamen;

import java.util.LinkedList;

public class Almacen {

    private final int capacidad_maxima = 5;
    private final LinkedList<Character> datos = new LinkedList<>();
    private final char senal_finalizacion = '#';

    public synchronized void put(char c) throws InterruptedException {

        while (datos.size() == capacidad_maxima) {
            System.out.println("Buffer lleno. Productor esperando...");
            wait();
        }

        datos.addLast(c);
        if (c != senal_finalizacion) {
            System.out.println("Producido: " + c);
        } else {
            System.out.println("Producido: senal_finalizacion ('#')");
        }
        notifyAll();
    }

    public synchronized char get() throws InterruptedException {
        while (datos.isEmpty()) {
            System.out.println("Buffer vacio. Consumidor esperando...");
            wait();
        }

        char c = datos.removeFirst();
        notifyAll();
        return c;

    }

    public char getSenalFinalizacion() {

        return senal_finalizacion;

    }

}
