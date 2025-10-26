import java.util.List;

public class HiloNumerosLetras implements Runnable{

    int tipo;

    List<String> letras = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");

    List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);


    public HiloNumerosLetras(int tipo) {
        this.tipo = tipo;
    }

    public void run() {
        if (tipo == 1) {
            for (Integer num : numeros) {
                System.out.println(num);
            }
        } else if (tipo == 2) {
            for (String letra : letras) {
                System.out.println(letra);
            }
        }
    }
}
