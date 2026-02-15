package org.example.Posibles_Examenes;

import org.apache.commons.net.ftp.*;

public class FTP_ListarRecursivo {

    public static void listar(FTPClient ftp, String path) throws Exception {
        FTPFile[] files = ftp.listFiles(path);
        for(FTPFile file : files){
            System.out.println(path + "/" + file.getName());
            if(file.isDirectory() && !file.getName().equals(".") && !file.getName().equals(".."))
                listar(ftp, path + "/" + file.getName());
        }
    }

    public static void main(String[] args) throws Exception {
        FTPClient ftp = new FTPClient();
        ftp.connect("localhost");
        ftp.login("usuario","1234");
        ftp.enterLocalPassiveMode();

        listar(ftp, "/"); // raíz
        ftp.logout();
        ftp.disconnect();
    }
}
