package org.example.ApuntesExamen;

import org.apache.commons.net.ftp.FTPClient;

public class FTP_CrearDirectorio {

    public static void main(String[] args) throws Exception {

        FTPClient ftp = new FTPClient();
        ftp.connect("127.0.0.1");
        ftp.login("usuario", "1234");

        boolean creado = ftp.makeDirectory("NuevaCarpeta");

        if (creado)
            System.out.println("Directorio creado");

        ftp.logout();
        ftp.disconnect();
    }
}

