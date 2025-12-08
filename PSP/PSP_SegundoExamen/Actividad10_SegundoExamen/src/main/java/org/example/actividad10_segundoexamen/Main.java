package org.example.actividad10_segundoexamen;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // --- Configuración ---
        final String FICHERO = "C:\\Users\\rodrii\\Desktop\\PSP_SegundoExamen\\Actividad10_SegundoExamen\\src\\main\\java\\org\\example\\actividad10_segundoexamen\\datos.txt"; // Asegúrate de que este archivo existe
        final int NUM_CONSUMIDORES = 3;

        // 1. Crear el Búfer Compartido
        Almacen almacen = new Almacen();

        // 2. Crear el Productor
        Productor productor = new Productor(almacen, FICHERO, NUM_CONSUMIDORES);

        // 3. Crear los Consumidores y un array para gestionarlos
        Consumidor[] consumidores = new Consumidor[NUM_CONSUMIDORES];
        for (int i = 0; i < NUM_CONSUMIDORES; i++) {
            consumidores[i] = new Consumidor(almacen, "Consumidor-" + (i + 1));
        }

        // --- Inicio de Hilos ---
        productor.start();
        for (Consumidor c : consumidores) {
            c.start();
        }

        // --- Comprobación de Estado (Punto clave de la actividad) ---

        // Esperar a que el productor termine (join)
        productor.join();
        System.out.println("\n--- COMPROBANDO ESTADOS DESPUÉS DE QUE EL PRODUCTOR TERMINÓ ---\n");

        // 4. Utiliza getState()
        for (Consumidor c : consumidores) {
            // Esperamos un momento para darle tiempo a cada consumidor de procesar su 'veneno'
            c.join(100);

            Thread.State estado = c.getState();
            System.out.println(c.getName() + " Estado: " + estado);

            // Si el estado NO es TERMINATED, significa que está esperando (bloqueado)
            if (estado != Thread.State.TERMINATED) {
                System.out.println("  **¡Problema! El consumidor no ha finalizado. Probablemente está esperando la señal de finalización.**");
            }
        }
    }
}