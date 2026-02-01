package org.example.examen23.Actividad3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        try {
            Socket cliente = new Socket("localhost", 6000);

            ObjectInputStream entradaObjeto = new ObjectInputStream(cliente.getInputStream());
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            Scanner sc = new Scanner(System.in);

            // Recibir ID del servidor
            int id = entrada.readInt();
            System.out.println("Cliente conectado con ID: " + id);

            while (true) {
                System.out.print("\nIntroduce nombre Personaje (* para salir): ");
                String linea = sc.nextLine();

                salida.writeUTF(linea);
                salida.flush();

                if ("*".equals(linea)) break;

                PersonajeOnePiece p = (PersonajeOnePiece) entradaObjeto.readObject();
                System.out.println("\n📄 DATOS DEL PERSONAJE");
                System.out.println(p);
            }

            cliente.close();
            System.out.println("👋 Desconectado del servidor");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
