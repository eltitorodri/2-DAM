package org.example.ApuntesExamen;

import org.apache.commons.net.ftp.FTPClient;
import java.io.FileOutputStream;

public class FTP_Descargar {

    public static void main(String[] args) throws Exception {

        FTPClient ftp = new FTPClient();
        ftp.connect("localhost");
        ftp.login("usuario2", "1234");

        FileOutputStream fos = new FileOutputStream("C:/descarga.txt");

        boolean descarga = ftp.retrieveFile("test.txt", fos);

        if (descarga)
            System.out.println("Descarga correcta");

        fos.close();
        ftp.logout();
        ftp.disconnect();
    }
}

