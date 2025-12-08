package org.example.actividad11_segundoexamen;

public class Jugador extends Thread {

    private int id;
    private Arbitro arbitro;

    public Jugador(int id, Arbitro arbitro) {
        this.id = id;
        this.arbitro = arbitro;
        setName("Jugador-" + id);
    }

    @Override
    public void run() {
        while (!arbitro.juegoEstado()) {

            synchronized (arbitro) {
                while (!arbitro.juegoEstado() && arbitro.obtenerTurno() != id) {
                    try {
                        arbitro.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (arbitro.juegoEstado()) {
                    arbitro.notifyAll();
                    break;
                }

                int intento = 1 + (int)(10 * Math.random());
                arbitro.jugar(id, intento);

                // Despertar a los demás jugadores para que comprueben el turno
                arbitro.notifyAll();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println(getName() + ": termina mi ejecución");
    }
}
