package org.example.Posibles_Examenes;

import org.apache.commons.net.ftp.FTPClient;
import javax.swing.*;

public class FTP_EliminarConfirm {

    public static void main(String[] args) throws Exception {
        FTPClient ftp = new FTPClient();
        ftp.connect("localhost");
        ftp.login("usuario","1234");
        ftp.enterLocalPassiveMode();

        String file = "test.txt";
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Deseas eliminar " + file + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if(opcion == JOptionPane.YES_OPTION){
            if(ftp.deleteFile(file))
                JOptionPane.showMessageDialog(null, "Archivo eliminado");
            else
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
        }

        ftp.logout();
        ftp.disconnect();
    }
}

