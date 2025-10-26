public class Actividad8UsoHilo {
    public static void main(String[] args) {

        Actividad8Hilo hilo = new Actividad8Hilo();
        hilo.start();

        int n, c = 0;
        int contador = 0;
        for(n = 1; n < 10; n++){
            c = n % 2;
            if (c != 0) {
                contador+=n;
                System.out.println("Impar:" +n);
            }
        }
        System.out.println("La suma de los impares es: "+contador+"\n");
    }
}
