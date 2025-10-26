public class UsaHiloLetras {
    public static void main(String[] args) {
        HiloNumerosLetras hiloNumeros = new HiloNumerosLetras(1); // tipo 1 → números
        HiloNumerosLetras hiloLetras = new HiloNumerosLetras(2);  // tipo 2 → letras

        Thread t1 = new Thread(hiloNumeros);
        Thread t2 = new Thread(hiloLetras);

        t1.start();
        t2.start();
    }
}