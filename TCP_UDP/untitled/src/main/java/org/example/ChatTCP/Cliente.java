package org.example.ChatTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Cliente {
    public static void main(String[] args) throws IOException {

        try {

            Socket socket = new Socket("localhost", 6000);

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            Scanner teclado = new Scanner(System.in);
            String mensaje;

            while (true) {

                System.out.println("Tú: ");
                mensaje = teclado.nextLine();
                salida.println(mensaje);

                System.out.println(entrada.readLine());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
