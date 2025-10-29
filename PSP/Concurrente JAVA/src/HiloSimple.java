public class HiloSimple extends Thread {
    public void run() {

        int resultado = 0; 

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0){
                System.out.println("En el Hilo..." + i);
                resultado+=i;
            }
        }
        System.out.println("El resultado es: " + resultado);
    }
}