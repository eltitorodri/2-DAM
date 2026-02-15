package org.example.ApuntesExamen;

import org.apache.commons.net.ftp.FTPClient;

public class FTP_Eliminar {

    public static void main(String[] args) throws Exception {

        FTPClient ftp = new FTPClient();
        ftp.connect("127.0.0.1");
        ftp.login("usuario1", "1234");

        // Cambiar al directorio donde está el archivo (si aplica)
        ftp.changeWorkingDirectory("/"); // "/" es la raíz, cámbialo si usas otra carpeta

        String archivo = "test.txt";
        boolean eliminado = ftp.deleteFile(archivo);

        if (eliminado) {
            System.out.println("Fichero '" + archivo + "' eliminado correctamente");
        } else {
            System.out.println("No se pudo eliminar '" + archivo + "'. Comprueba nombre/permisos/directorio.");
        }

        ftp.logout();
        ftp.disconnect();
    }
}
