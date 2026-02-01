package org.example.tcp_objetos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static int contador =1;

    public static void main(String[] args) throws IOException {

        int puerto = 6000;
        ServerSocket serverSocket = new ServerSocket(puerto);

        System.out.println("SERVIDOR ESCUCHANDO DESDE EL PUERTO . . . ");

        Jose[] joses = new Jose[3];
        Novia elena = new Novia("Elena", "Rojo", 18);
        Mascotas chuli = new Mascotas("Chuli", "Beaggle", 20);

        joses[0] = new Jose(elena,chuli, "Jose", 19);
        joses[1] = new Jose(elena,chuli, "Jose", 20);
        joses[2] = new Jose(elena,chuli, "Jose", 21);

        while(true){
            Socket cliente = serverSocket.accept();

            int idAsignado = contador++;
            System.out.println("--> Cliente conectado con ID: " + idAsignado);

            HiloJoselito hilo = new HiloJoselito(cliente,idAsignado, joses);
            hilo.start();
        }

    }

}
