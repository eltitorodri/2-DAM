package org.example.tcp_objetos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket cliente = new Socket("localhost", 6000);

        ObjectInputStream entradaObjeto = new ObjectInputStream(cliente.getInputStream());
        DataInputStream entrada = new DataInputStream(cliente.getInputStream());
        DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

        Scanner input = new Scanner(System.in);

        int id = entrada.readInt();
        System.out.println("Cliente conectado con ID: " + id);

        while(true){

            System.out.println("\nIntroduce el nombre de tu Jose (* para salir)");
            String linea = input.nextLine();

            salida.writeUTF(linea);
            salida.flush();

            if ("*".equals(linea)) break;

            Jose p = (Jose) entradaObjeto.readObject();
            System.out.println("\nDATOS DE JOSE");
            System.out.println(p);
        }

        cliente.close();
        System.out.println("Desconectando del servidor");
        System.out.println( " . . . . . . . . . ");

    }
}
