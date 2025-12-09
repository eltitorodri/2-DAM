import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
        Consumidor c = new Consumidor("C", queue);
        Productor p = new Productor("P", "archivo.txt", queue);
        p.start();
        c.start();
    }
}