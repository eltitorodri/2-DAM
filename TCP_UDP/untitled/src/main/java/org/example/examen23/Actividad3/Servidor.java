package org.example.examen23.Actividad3;

import org.example.examen2425.Actividad3.HiloEntrenador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static int contador = 1;

    public static void main(String[] args) throws IOException {

        int puerto = 6000;
        ServerSocket serverSocket = new ServerSocket(puerto);

        System.out.println("SERVIDOR ESCUCHANDO DESDE EL PUERTO " + puerto + " . . .");

        // Inicializar personajes
        PersonajeOnePiece[] personajes = new PersonajeOnePiece[3];
        Barco barco = new Barco("Las Tres Marias", "Zoroak y Chansey", "Velero", 200, 300);
        Fruta fruta = new Fruta("Rojada", "Esto es una fruta estimulante", "Pomelo");

        personajes[0] = new PersonajeOnePiece(barco, fruta, "Atacante", "Eren");
        personajes[1] = new PersonajeOnePiece(barco, fruta, "Defensor", "Armin");
        personajes[2] = new PersonajeOnePiece(barco, fruta, "Intermediaria", "Mikasa");

        while (true) {
            Socket cliente = serverSocket.accept();

            int idAsignado = contador++;
            System.out.println("--> Cliente conectado con ID: " + idAsignado);

            new HiloPersonaje(cliente, idAsignado, personajes).start();
        }
    }
}
