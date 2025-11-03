import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SolicitaSuspender control = new SolicitaSuspender();
        myHilo hilo = new myHilo(control);

        hilo.start();

        String opcion;

        System.out.println("Introduce S para suspender, R para reanudar, * para salir:");

        do {
            opcion = sc.nextLine();

            if (opcion.equalsIgnoreCase("S")) {
                System.out.println("➡️  Suspendiendo hilo...");
                control.setSuspender(true);
            } else if (opcion.equalsIgnoreCase("R")) {
                System.out.println("▶️  Reanudando hilo...");
                control.setSuspender(false);
            }

        } while (!opcion.equals("*"));

        System.out.println("⏹ Finalizando programa...");
        hilo.pararHilo();
        control.setSuspender(false);

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("✅ Contador final leído desde main: " + hilo.getContador());
    }
}
