public class SolicitaSuspender {

    private boolean suspender;

    public synchronized void setSuspender(boolean suspender) {
        this.suspender = suspender;
        if (!suspender) {
            notify();
        }
    }

    public synchronized void esperandoParaReanudar() {
        while (suspender) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido mientras estaba suspendido.");
            }
        }
    }
}
