package org.example.examen2425.Actividad2;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {


        int puerto = 6000;
        ServerSocket servidor = new ServerSocket(puerto);

        System.out.println("SERVIDOR ESCUCHANDO DESDE EL PUERTO " + puerto +" . . . ");

        InetAddress inet = InetAddress.getLocalHost();
        for (int i = 1; i <= 10; i++) {
            Socket cliente = servidor.accept();
            System.out.println("=====================");
            System.out.println("=> Conecta IP /"+inet.getHostAddress()+", Puerto remoto: " + cliente.getPort());

            InputStream input = null;
            input = new DataInputStream(cliente.getInputStream());
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());

            OutputStream output = null;
            output = new DataOutputStream(cliente.getOutputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            String cadenaDevuelta = entrada.readUTF();

            if (cadenaDevuelta.equals("*")) {
                System.out.println("\n\t=> Desconecta IP /"+inet.getHostAddress()+", Puerto remoto: " + cliente.getPort());
            } else {
                salida.writeUTF(cadenaDevuelta.toUpperCase());
            }
        }

        servidor.close();

    }
}
