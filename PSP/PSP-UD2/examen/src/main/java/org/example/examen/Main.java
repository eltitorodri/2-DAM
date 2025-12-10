package org.example.examen;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int jugadores = 3;
        if (args.length > 0) {
            try {
                jugadores = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {}
        }

        Arbitro arbitro = new Arbitro(jugadores);
        Jugador[] hilos = new Jugador[jugadores];

        for (int i = 0; i < jugadores; i++) {
            hilos[i] = new Jugador(i + 1, arbitro);
            hilos[i].start();
        }

        for (Jugador j : hilos) {
            j.join();
        }

        System.out.println("Juego terminado.");
    }
}

