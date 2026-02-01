package org.example.examen2425.Actividad3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 6000);

            ObjectInputStream entradaObjeto =
                    new ObjectInputStream(socket.getInputStream());

            DataInputStream entrada =
                    new DataInputStream(socket.getInputStream());
            DataOutputStream salida =
                    new DataOutputStream(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);

            int id = entrada.readInt();
            System.out.println("🎮 Entrenador conectado con ID: " + id);

            while (true) {
                System.out.print("\nIntroduce número Pokedex (* para salir): ");
                String opcion = sc.nextLine();

                salida.writeUTF(opcion);
                salida.flush();

                if ("*".equals(opcion)) {
                    break;
                }

                Pokemon p = (Pokemon) entradaObjeto.readObject();
                System.out.println("\n📄 DATOS DEL POKÉMON");
                System.out.println(p);
            }

            socket.close();
            System.out.println("👋 Desconectado del servidor");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
