package org.example.examen;

public class Arbitro {
    private final int totalPlayers;
    private int turno;
    private final int objetivo;
    private boolean juegoAcabado = false;

    public Arbitro(int totalPlayers) {
        this.totalPlayers = totalPlayers;
        this.turno = 1;
        this.objetivo = 1 + (int)(10 * Math.random());
        System.out.println("Arbitro: número a adivinar (oculto) generado.");
    }

    public synchronized int getTurno() {
        return turno;
    }

    public synchronized boolean isJuegoAcabado() {
        return juegoAcabado;
    }

    public synchronized void jugar(int jugadorId, int numero) {
        if (juegoAcabado) return;

        if (jugadorId != turno) {
            System.out.println("Jugador " + jugadorId + " intentó jugar fuera de turno.");
            return;
        }

        System.out.println("Jugador " + jugadorId + " juega: " + numero);

        if (numero == objetivo) {
            juegoAcabado = true;
            System.out.println("Jugador " + jugadorId + " ha acertado. Número: " + objetivo);
            notifyAll();
            return;
        } else {
            System.out.println("Jugador " + jugadorId + " no ha acertado.");
            turno = (turno % totalPlayers) + 1;
            System.out.println("Turno siguiente: Jugador " + turno);
            notifyAll();
        }
    }
}
