package org.example.examenrodrigo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;

public class Productor extends Thread{

    private String nombre ;
    private String ruta;
    private BlockingQueue<String> queue;

    public Productor(String nombre, String ruta, BlockingQueue<String> queue) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.queue = queue;
    }

    //Aqui el metodo run de thread
    @Override
    public void run() {
        //aqui leo el archivo de la ruta especificada
        try (FileReader fr = new FileReader(ruta)) {
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                queue.add(linea);
            }
            //recojo la excepcion si salta
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

