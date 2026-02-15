package org.example.ApuntesExamen;

import org.apache.commons.net.ftp.FTPClient;
import java.util.Scanner;

public class FTP_ConexionConsola {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FTPClient ftp = new FTPClient();

        try {
            System.out.print("Servidor: ");
            String servidor = sc.nextLine();

            System.out.print("Usuario: ");
            String usuario = sc.nextLine();

            System.out.print("Clave: ");
            String clave = sc.nextLine();

            ftp.connect(servidor);
            boolean login = ftp.login(usuario, clave);

            if (login) {
                System.out.println("CONEXIÓN REALIZADA CON ÉXITO");
            } else {
                System.out.println("Error en autenticación");
            }

            ftp.logout();
            ftp.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

