package org.example.examen2425.Actividad2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente2 {
    public static void main(String[] args) throws IOException {


        String host = "localhost";
        int puerto = 6000;
        Socket cliente = new Socket(host, puerto);

        InetAddress i = cliente.getInetAddress();

        DataOutputStream mensaje = new DataOutputStream(cliente.getOutputStream());
        mensaje.writeUTF("*");

        DataInputStream input = new DataInputStream(cliente.getInputStream());

        System.out.println("Este es el mensaje que me ha enviado el SERVIDOR...\n\t"+input.readUTF());

        cliente.close();

    }
}