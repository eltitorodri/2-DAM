package org.example.ApuntesExamen;

import org.apache.commons.net.ftp.*;

public class FTP_Listar {

    public static void main(String[] args) {
        FTPClient ftp = new FTPClient();
        try {
            // Conexión al servidor FTP
            ftp.connect("127.0.0.1");
            int reply = ftp.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)) {
                System.out.println("No se pudo conectar al servidor FTP. Código: " + reply);
                return;
            }

            // Login con usuario y contraseña
            if(!ftp.login("usuario1", "1234")) {
                System.out.println("Error de autenticación: usuario o contraseña incorrectos.");
                return;
            }

            // Modo pasivo (recomendado para evitar problemas de firewall)
            ftp.enterLocalPassiveMode();

            // Cambiar al directorio raíz (o al que corresponda)
            if(!ftp.changeWorkingDirectory("/")) {
                System.out.println("No se pudo acceder al directorio raíz.");
                return;
            }

            // Listar archivos
            FTPFile[] files = ftp.listFiles();

            if(files.length == 0) {
                System.out.println("No hay archivos en el directorio.");
            } else {
                System.out.println("Archivos en el directorio:");
                for (FTPFile file : files) {
                    System.out.println(file.getName());
                }
            }

            // Logout y desconexión
            ftp.logout();
            ftp.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
