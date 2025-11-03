public class myHilo extends Thread {

    private boolean enEjecucion = true;
    private int contador = 0;
    private SolicitaSuspender control;

    public myHilo(SolicitaSuspender control) {
        this.control = control;
    }

    public void pararHilo() {
        enEjecucion = false;
    }

    public int getContador() {
        return contador;
    }

    @Override
    public void run() {
        while (enEjecucion) {
            contador++;
            System.out.println("Contador = " + contador);

            control.esperandoParaReanudar();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido.");
            }
        }

        System.out.println("Hilo finalizado. Contador final = " + contador);
    }
}
