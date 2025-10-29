public class UsaHilo {
    public static void main(String[] args) {
        HiloSimple hs = new HiloSimple();
        hs.start();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 1){
                System.out.println("Fuera del hilo..." + i);
            }
        }
    }
}