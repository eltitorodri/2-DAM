package org.example.examen2425.Actividad3;

import java.io.*;
import java.net.Socket;

public class HiloEntrenador extends Thread {

    private Socket cliente;
    private int id;
    private Pokemon[] pokedex;

    public HiloEntrenador(Socket cliente, int id, Pokemon[] pokedex) {
        this.cliente = cliente;
        this.id = id;
        this.pokedex = pokedex;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream salidaObjeto =
                    new ObjectOutputStream(cliente.getOutputStream());
            salidaObjeto.flush();

            DataInputStream entrada =
                    new DataInputStream(cliente.getInputStream());
            DataOutputStream salida =
                    new DataOutputStream(cliente.getOutputStream());

            // Enviar ID
            salida.writeInt(id);
            salida.flush();

            while (true) {
                String recibido = entrada.readUTF();

                if ("*".equals(recibido)) {
                    System.out.println("❌ Entrenador " + id + " desconectado");
                    break;
                }

                int numero = Integer.parseInt(recibido);
                Pokemon resultado = buscarPokemon(numero);

                salidaObjeto.writeObject(resultado);
                salidaObjeto.flush();
            }

            cliente.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Pokemon buscarPokemon(int numero) {
        for (Pokemon p : pokedex) {
            if (p != null && p.numeroPokedex == numero) {
                return p;
            }
        }

        // Pokémon no existe
        return new Pokemon(
                numero,
                "NO EXISTE",
                new TipoElemental("-", "-"),
                new Ataque("-", 0)
        );
    }
}
