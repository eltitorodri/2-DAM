import java.util.concurrent.BlockingQueue;

public class Consumidor extends Thread {
    String nombre;
    BlockingQueue<String> queue;

    public Consumidor(String nombre, BlockingQueue<String> queue) {
        this.nombre = nombre;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String linea = queue.take();
            System.out.println(linea);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
