package org.example.ApuntesExamen;

import org.apache.commons.net.ftp.FTPClient;
import java.io.FileInputStream;
import javax.swing.JFileChooser;

public class FTP_Subir {
    public static void main(String[] args) throws Exception {
        FTPClient ftp = new FTPClient();
        ftp.connect("127.0.0.1");
        ftp.login("usuario1", "1234");

        JFileChooser chooser = new JFileChooser();
        int opcion = chooser.showOpenDialog(null);
        if (opcion != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se seleccionó ningún archivo");
            return;
        }

        FileInputStream fis = new FileInputStream(chooser.getSelectedFile());
        boolean subida = ftp.storeFile(chooser.getSelectedFile().getName(), fis);

        if (subida)
            System.out.println("Fichero subido correctamente");

        fis.close();
        ftp.logout();
        ftp.disconnect();
    }
}
