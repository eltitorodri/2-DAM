package org.example.examenrodrigo;

import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class Consumidor extends Thread {

    //Aqui declaro las variables de la clase que son la cola y el nombre
    private BlockingQueue<String> queue;
    private String nombre;
    private Label label;
    private int contadorPills;
    private int contadorAlertas;

    //Los paso por constructor
    public Consumidor(BlockingQueue<String> queue, String nombre) {
        this.queue = queue;
        this.nombre = nombre;
        this.contadorPills = 0;
        this.contadorAlertas = 0;
    }

    //Creo el metodo run() por que extiendo consumidor de Thread
    @Override
    public void run() {
        while (true) {
            //leo las lineas o mejor dicho las intento leer
            try {
                String linea = queue.take();
                contadorPills++;
                System.out.println("\nConsumidor -> " + linea);
                try {
                    //aqui lo paso por el metodo detectarEstados
                    System.out.println("--------FALLOS DETECTADOS--------");
                    detectarEstados(linea);
                    contadorAlertas++;
                    System.out.println("---------------------------------");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //recojo la excepcion si salta
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\nContador de Pills --> " + contadorPills);
            System.out.println("\nContador de Alertas --> " + contadorAlertas);
        }
    }

    //En este metodo intento hacer separar los numeros de las letras y comprobar las alertas pero no me va o eso creo
    private synchronized void detectarEstados(String linea) {

        //separo las lineas por el ;
        String[] lineas = linea.split(";");
        for (int i = 0; i < lineas.length; i++) {
            String[] lineas1 = lineas[i].split(":");
            Integer TEMP = Integer.parseInt(lineas1[1]);
                if (TEMP > 85) {
                    System.out.println("ALERTA: SOBRECALENTAMIENTO");
                    label.setText("ALERTA: SOBRECALENTAMIENTO");
                } else if (TEMP < 30) {
                    System.out.println("ALERTA: BATERIA BAJA");
                } else if (TEMP > 1.5) {
                    System.out.println("ALERTA: RADIACION PELIGROSA");
                } else if (TEMP > 5) {
                    System.out.println("ALERTA: ROTACION EXCESIVA");
                }


            //Este era mi intento de antes intentado hacerlo pero estaba mal estoy lo estoy volviendo a intentar
            //simplemente lo que hace es ver si la linea es igual que eso (bueno que esta mal)
            //if(("TEMP: 85").equals(linea)) {
            //    System.out.println("ALERTA: SOBRECALENTAMIENTO");
            //} else if ("BATT: 30".equals(linea)) {
            //    System.out.println("ALERTA: BATERIA BAJA");
            //} else if ("RAD: 1.5".equals(linea)) {
            //    System.out.println("ALERTA: RADIACION PELIGROSA");
            //} else if ("VEL: 5".equals(linea)) {
            //    System.out.println("ALERTA: ROTACION EXCESIVA");
            //}
        }
    }
}
