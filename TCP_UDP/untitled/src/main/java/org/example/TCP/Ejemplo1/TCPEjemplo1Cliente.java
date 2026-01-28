package org.example.TCP.Ejemplo1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPEjemplo1Cliente {
    public static void main(String[] args) throws IOException {


        String Host = "localhost";
        int numeroPuerto = 6000;

        System.out.println("PROGRAMA CLIENTE INICIADO....");
        Socket Cliente = new Socket(Host, numeroPuerto);

        // CREAMOS EL FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        // ENVIAMOS UN SALUDO AL SERVIDOR
        flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE");

        // CREAMOS EL FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

        // EL SERVIDOR ME ENVIA UN MENSAJE
        System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());

        // CERRAMOS STREAMS Y SOCKETS
        flujoSalida.close();
        flujoEntrada.close();
        Cliente.close();

    }
}
