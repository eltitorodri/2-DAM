package org.example.ChatTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {


        try {

            int puerto = 6000;

            ServerSocket servidor = new ServerSocket(puerto);

            System.out.println("SERVIDOR ESCUCHANDO DESDE EL PUERTO "+puerto+ " . . . ");

            Socket cliente = servidor.accept();

            System.out.println("Cliente conectado");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

            String mensaje;

            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Cliente: " + mensaje);
                salida.println("Servidor: "+mensaje);
            }

            cliente.close();
            servidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
