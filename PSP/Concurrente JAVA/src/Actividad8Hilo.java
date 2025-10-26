public class Actividad8Hilo extends Thread{
    public void run(){
        int n;
        int contador = 0;
        for(n = 1; n <= 10; n++){
            if (n  % 2 == 0){
                System.out.println("Par: "+n+"\n");
                contador+=n;
            }
        }
        System.out.println("Suma de los pares es: "+contador+".\n");
        System.out.println("*********************************\n");

    }
}
