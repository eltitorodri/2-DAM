package org.example.actividad11_segundoexamen;

public class Arbitro {

    private int numJugadores;
    private int turno;
    private int numAdivinar;
    private boolean estado;

    public Arbitro(int numJugadores) {
        this.numJugadores = numJugadores;
        this.turno = 1; // Turno empieza en 1
        this.numAdivinar = 1 + (int)(10 * Math.random());
        this.estado = false;

        System.out.println("ARBITRO: Número generado (oculto). Comienza el juego con " + numJugadores + " jugadores.");
    }

    public int obtenerTurno() {
        return turno;
    }

    public boolean juegoEstado() {
        return estado;
    }

    public synchronized void jugar(int id, int numeroJugado) {
        if (estado) return;

        System.out.println("ARBITRO: Jugador " + id + " juega con " + numeroJugado);

        if (numeroJugado == numAdivinar) {
            estado = true;
            System.out.println("¡El ganador es el Jugador " + id + "! El número era " + numAdivinar);
            System.out.println("ARBITRO: Fin del juego");
        } else {
            // Actualizamos turno
            turno = (turno % numJugadores) + 1;
            System.out.println("ARBITRO: No acertó. Ahora es el turno del jugador " + turno);
        }
    }
}
