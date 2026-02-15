package org.example.ApuntesExamen;

import org.apache.commons.net.smtp.*;
import java.io.*;

public class SMTP_Enviar {

    public static void main(String[] args) throws Exception {

        SMTPClient client = new SMTPClient();
        client.connect("localhost");

        client.login();

        client.setSender("admin@localhost");
        client.addRecipient("postmaster@localhost");

        Writer writer = client.sendMessageData();
        writer.write("Subject: Prueba\n");
        writer.write("\nHola desde Java");
        writer.close();

        client.completePendingCommand();
        client.logout();
        client.disconnect();

        System.out.println("Mensaje enviado");
    }
}

