package org.example.ChatUDP;

import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccion = InetAddress.getByName("localhost");

            Scanner teclado = new Scanner(System.in);

            while (true) {
                System.out.print("Tú: ");
                String mensaje = teclado.nextLine();

                byte[] datos = mensaje.getBytes();
                DatagramPacket paquete = new DatagramPacket(
                        datos, datos.length, direccion, 5000);

                socket.send(paquete);

                byte[] buffer = new byte[1024];
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
                socket.receive(respuesta);

                String mensajeServidor = new String(
                        respuesta.getData(), 0, respuesta.getLength());

                System.out.println(mensajeServidor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

