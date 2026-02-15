package org.example.ApuntesExamen;

import org.apache.commons.net.smtp.SMTPClient;

public class SMTP_Conexion {

    public static void main(String[] args) throws Exception {

        SMTPClient client = new SMTPClient();
        client.connect("localhost");

        if (client.login()) {
            System.out.println("Conexión realizada");
        }

        client.disconnect();
    }
}

