package org.example.examen;

public class Jugador extends Thread {
    private final int id;
    private final Arbitro arbitro;

    public Jugador(int id, Arbitro arbitro) {
        this.id = id;
        this.arbitro = arbitro;
        setName("Jugador-" + id);
    }

    @Override
    public void run() {
        while (!arbitro.isJuegoAcabado()) {
            synchronized (arbitro) {
                while (arbitro.getTurno() != id && !arbitro.isJuegoAcabado()) {
                    try {
                        arbitro.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                if (arbitro.isJuegoAcabado()) break;
            }

            int intento = 1 + (int)(10 * Math.random());
            arbitro.jugar(id, intento);

            try {
                Thread.sleep(100); // pequeño retardo para ver salidas ordenadas
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
