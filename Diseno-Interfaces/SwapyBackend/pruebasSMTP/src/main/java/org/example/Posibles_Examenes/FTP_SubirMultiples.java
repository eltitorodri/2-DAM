package org.example.Posibles_Examenes;

import org.apache.commons.net.ftp.FTPClient;
import javax.swing.*;
import java.io.*;

public class FTP_SubirMultiples extends JFrame {

    public static void main(String[] args) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect("localhost");
            ftp.login("usuario1", "1234");
            ftp.enterLocalPassiveMode();

            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);
            chooser.showOpenDialog(null);
            File[] files = chooser.getSelectedFiles();

            for(File file : files){
                FileInputStream fis = new FileInputStream(file);
                if(ftp.storeFile(file.getName(), fis))
                    System.out.println(file.getName() + " subido correctamente");
                else
                    System.out.println("Error subiendo " + file.getName());
                fis.close();
            }

            ftp.logout();
            ftp.disconnect();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

