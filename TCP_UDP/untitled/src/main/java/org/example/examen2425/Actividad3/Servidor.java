package org.example.examen2425.Actividad3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static int contador = 1;

    public static void main(String[] args) throws IOException {

        int puerto = 6000;
        ServerSocket servidor = new ServerSocket(puerto);

        System.out.println("POKEDEX ESCUCHANDO DESDE EL PUERTO " + puerto + " ...");

        // Crear Pokedex
        Pokemon[] pokedex = new Pokemon[3];

        pokedex[0] = new Pokemon(
                1,
                "Bulbasaur",
                new TipoElemental("Planta", "Fuego"),
                new Ataque("Látigo Cepa", 45)
        );

        pokedex[1] = new Pokemon(
                4,
                "Charmander",
                new TipoElemental("Fuego", "Agua"),
                new Ataque("Ascuas", 50)
        );

        pokedex[2] = new Pokemon(
                7,
                "Squirtle",
                new TipoElemental("Agua", "Planta"),
                new Ataque("Pistola Agua", 40)
        );

        while (true) {
            Socket cliente = servidor.accept();
            int idAsignado = contador++;

            System.out.println("--> Entrenador conectado con ID: " + idAsignado);

            new HiloEntrenador(cliente, idAsignado, pokedex).start();
        }
    }
}
