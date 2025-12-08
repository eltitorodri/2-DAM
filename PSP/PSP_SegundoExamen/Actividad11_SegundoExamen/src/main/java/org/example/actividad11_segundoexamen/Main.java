package org.example.actividad11_segundoexamen;

public class Main {
    public static void main(String[] args) {

        int numeroJugadores = 3;

        Arbitro arbitro = new Arbitro(numeroJugadores);

        for (int i = 1; i <= numeroJugadores; i++) {
            Jugador j = new Jugador(i, arbitro);
            j.start();
        }

    }
}
