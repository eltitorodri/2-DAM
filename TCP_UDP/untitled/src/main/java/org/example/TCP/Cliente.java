package org.example.TCP;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int Puerto = 6000;

        Socket cliente = new Socket(host, Puerto);

        InetAddress i = cliente.getInetAddress();
        System.out.println("Puerto local: " + cliente.getLocalPort());
        System.out.println("Puerto remoto: " + cliente.getPort());
        System.out.println("Host remoto: " + i.getHostName().toString());
        System.out.println("IP Host remoto: " + i.getHostAddress().toString());

        cliente.close();

    }
}
