package org.example.Posibles_Examenes;
import org.apache.commons.net.ftp.*;
import java.util.Scanner;

public class FTP_CambiarDirectorio {

    public static void main(String[] args) throws Exception {
        FTPClient ftp = new FTPClient();
        ftp.connect("localhost");
        ftp.login("usuario","1234");
        ftp.enterLocalPassiveMode();

        Scanner sc = new Scanner(System.in);
        System.out.print("Directorio a cambiar: ");
        String dir = sc.nextLine();

        if(ftp.changeWorkingDirectory(dir)){
            System.out.println("Directorio cambiado a " + dir);
            FTPFile[] files = ftp.listFiles();
            for(FTPFile f : files)
                System.out.println(f.getName());
        } else {
            System.out.println("No se puede cambiar a " + dir);
        }

        ftp.logout();
        ftp.disconnect();
    }
}

