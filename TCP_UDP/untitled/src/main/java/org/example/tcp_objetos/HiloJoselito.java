package org.example.tcp_objetos;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloJoselito extends Thread {

    private Socket cliente;
    private Integer nombre;
    private Jose[] joselitos;

    public HiloJoselito(Socket cliente, Integer nombre, Jose[] joselitos) {
        this.cliente = cliente;
        this.nombre = nombre;
        this.joselitos = joselitos;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
            salidaObjeto.flush();

            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            salida.writeInt(nombre);
            salida.flush();

            while(true){

                String recibido = entrada.readUTF();

                if ("*".equals(recibido)) {
                    System.out.println("X Cliente " + nombre + " se ha desconectado");
                    break;
                }

                Jose jose = buscarPersona(recibido);
                salidaObjeto.writeObject(jose);
                salidaObjeto.flush();

            }

            cliente.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private Jose buscarPersona(String recibido){
        for (Jose jose : joselitos) {
            if (jose != null && jose.nombre.equals(recibido)) {
                return jose;
            }
        }

        return new Jose(
                new Novia(" - ", " - ", 0),
                new Mascotas(" - ", " - ", 0),
                recibido,
                0
        );

    }

}
