package org.example.Posibles_Examenes;

import org.apache.commons.net.smtp.SMTPClient;
import java.io.Writer;

public class SMTP_Codigos {

    public static void main(String[] args) {
        SMTPClient client = new SMTPClient();
        try {
            // Conectar al servidor
            client.connect("localhost");
            System.out.println("Código conexión: " + client.getReplyCode());

            // HELO
            client.login("localhost");
            System.out.println("Código HELO: " + client.getReplyCode());

            // NOTA: Mercury en localhost NO requiere AUTH LOGIN si estás usando el usuario del servidor
            // Configura tu remitente como postmaster@localhost
            String remitente = "postmaster@localhost";
            String destinatario = "postmaster@localhost";

            // MAIL FROM
            if(!client.setSender(remitente)) {
                System.out.println("Error MAIL FROM, código: " + client.getReplyCode());
                client.disconnect();
                return;
            }
            System.out.println("Código MAIL FROM: " + client.getReplyCode());

            // RCPT TO
            if(!client.addRecipient(destinatario)) {
                System.out.println("Destinatario inválido o no autorizado, código: " + client.getReplyCode());
                client.disconnect();
                return;
            }
            System.out.println("Código RCPT TO: " + client.getReplyCode());

            // DATA
            Writer writer = client.sendMessageData();
            if(writer == null){
                System.out.println("Error en DATA: " + client.getReplyCode());
                client.disconnect();
                return;
            }

            writer.write("Subject: Examen SMTP\n\nMensaje de prueba enviado desde Java");
            writer.close();

            if(client.completePendingCommand()){
                System.out.println("Mensaje enviado correctamente");
                System.out.println("Código DATA final: " + client.getReplyCode());
            } else {
                System.out.println("Error al enviar mensaje: " + client.getReplyCode());
            }

            client.logout();
            client.disconnect();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
