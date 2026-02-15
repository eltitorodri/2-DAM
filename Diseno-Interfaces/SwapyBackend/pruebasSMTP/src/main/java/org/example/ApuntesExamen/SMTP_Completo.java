package org.example.ApuntesExamen;

import org.apache.commons.net.smtp.SMTPClient;

import java.io.*;
import java.util.Scanner;

public class SMTP_Completo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SMTPClient client = new SMTPClient();

        try {
            // Pedir servidor SMTP
            System.out.print("Servidor SMTP: ");
            String servidor = sc.nextLine();

            // Conectar
            client.connect(servidor);
            System.out.println("Código conexión: " + client.getReplyCode());

            // HELO obligatorio
            client.login("localhost");
            System.out.println("Código HELO: " + client.getReplyCode());

            // Pedir remitente y destinatario
            System.out.print("Remitente: ");
            String from = sc.nextLine();

            System.out.print("Destinatario: ");
            String to = sc.nextLine();

            // Configurar remitente y destinatario
            if (!client.setSender(from)) {
                System.out.println("Remitente inválido");
                return;
            }

            if (!client.addRecipient(to)) {
                System.out.println("Destinatario inválido o no autorizado");
                return;
            }

            // Preparar mensaje
            Writer writer = client.sendMessageData();
            if (writer == null) {
                System.out.println("No se puede enviar mensaje. Comprueba remitente/destinatario.");
                return;
            }

            writer.write("Subject: Examen\n\nMensaje de prueba enviado desde Java");
            writer.close();

            if (client.completePendingCommand()) {
                System.out.println("Mensaje enviado correctamente");
                System.out.println("Código final DATA: " + client.getReplyCode());
            } else {
                System.out.println("Error al enviar mensaje");
            }

            // Cerrar sesión
            client.logout();
            client.disconnect();

        } catch (Exception e) {
            System.out.println("Error al enviar mensaje");
            e.printStackTrace();
        }
    }
}
