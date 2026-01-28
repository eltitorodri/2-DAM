package org.example.TCP.Ejemplo1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEjemplo1Servidor {
    public static void main(String[] args) throws IOException {


        int numeroPuerto = 6000;
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        Socket clienteConectado = null;
        System.out.println("Esperando al cliente...");
        clienteConectado = servidor.accept();

        //CREAMOS EL FLUJO DE ENTRADA DEL CLIENTE

        InputStream entrada = null;
        entrada = clienteConectado.getInputStream();
        DataInputStream flujoEntrada = new DataInputStream(entrada);

        // CREAMOS EL CLIENTE QUE ME ENVIA EL MENSAJE
        System.out.println("Recibiendo del cliente: \n\t" + flujoEntrada.readUTF());

        // CREAMOS EL FLUJO DE SALIDA AL CLIENTE

        OutputStream salida = null;
        salida = clienteConectado.getOutputStream();
        DataOutputStream flujoSalida = new DataOutputStream(salida);

        //ENVIO UN SALUDO AL CLIENTE

        flujoSalida.writeUTF("Saludos al cliente desde el server");

        // CERRAMOS LOS STREAMS Y SOCKETS

        entrada.close();
        flujoEntrada.close();
        salida.close();
        flujoSalida.close();
        clienteConectado.close();
        servidor.close();

    }
}
