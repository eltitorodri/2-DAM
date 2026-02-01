package org.example.examen23.Actividad3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloPersonaje extends Thread {

    private Socket cliente;
    private int idAsignado;
    private PersonajeOnePiece[] personajes;

    public HiloPersonaje(Socket cliente, int idAsignado, PersonajeOnePiece[] personajes) {
        this.cliente = cliente;
        this.idAsignado = idAsignado;
        this.personajes = personajes;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
            salidaObjeto.flush();

            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            // Enviar ID al cliente
            salida.writeInt(idAsignado);
            salida.flush();

            while (true) {
                String recibido = entrada.readUTF();

                if ("*".equals(recibido)) {
                    System.out.println("X Cliente " + idAsignado + " se ha desconectado");
                    break;
                }

                PersonajeOnePiece personaje = buscarPersonaje(recibido);

                salidaObjeto.writeObject(personaje);
                salidaObjeto.flush();
            }

            cliente.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PersonajeOnePiece buscarPersonaje(String recibido) {
        for (PersonajeOnePiece personaje : personajes) {
            if (personaje != null && personaje.nombre.equalsIgnoreCase(recibido)) {
                return personaje;
            }
        }

        return new PersonajeOnePiece(
                new Barco("-", "-", "-", 0, 0),
                new Fruta("-", "-", "-"),
                "NO EXISTE",
                recibido
        );
    }
}
