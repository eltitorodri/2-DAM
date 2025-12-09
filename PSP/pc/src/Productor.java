import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;

public class Productor extends  Thread {
    String nombre;
    String ruta;
    BlockingQueue<String> queue;

    public Productor(String nombre, String ruta, BlockingQueue<String> queue) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.queue = queue;
    }

    @Override
    public void run() {
        try(FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                queue.add(linea);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
