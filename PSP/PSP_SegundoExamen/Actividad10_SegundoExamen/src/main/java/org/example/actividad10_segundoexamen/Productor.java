package org.example.actividad10_segundoexamen;

import java.io.FileReader;
import java.io.IOException;

public class Productor extends Thread{

    private final Almacen almacen;
    private final String nombreFichero;
    private final int numConsumidores;

    public Productor(Almacen almacen, String nombreFichero, int numConsumidores) {
        this.almacen = almacen;
        this.nombreFichero = nombreFichero;
        this.numConsumidores = numConsumidores;
    }

    @Override
    public void run() {
        try (FileReader fr = new FileReader(nombreFichero)) {
            int charLeido;

            while ((charLeido = fr.read()) != -1) {
                char c = (char) charLeido;
                almacen.put(c);
            }

            for (int i = 0; i < numConsumidores; i++) {
                almacen.put(almacen.getSenalFinalizacion());
            }
        } catch ( IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n PRODUCTOR A FINALIZADO SU PROCESO");
    }

}
