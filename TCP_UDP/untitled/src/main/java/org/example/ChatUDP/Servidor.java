package org.example.ChatUDP;

import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5000);
            byte[] buffer = new byte[1024];

            System.out.println("Servidor UDP iniciado...");

            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                String mensaje = new String(
                        paquete.getData(), 0, paquete.getLength());

                System.out.println("Cliente: " + mensaje);

                String respuesta = "Servidor: " + mensaje;
                byte[] datosRespuesta = respuesta.getBytes();

                DatagramPacket paqueteRespuesta = new DatagramPacket(
                        datosRespuesta,
                        datosRespuesta.length,
                        paquete.getAddress(),
                        paquete.getPort()
                );

                socket.send(paqueteRespuesta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

