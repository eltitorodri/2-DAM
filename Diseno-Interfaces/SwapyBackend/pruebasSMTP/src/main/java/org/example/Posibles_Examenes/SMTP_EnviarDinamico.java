package org.example.Posibles_Examenes;

import org.apache.commons.net.smtp.SMTPClient;

import javax.swing.*;
import java.io.*;

public class SMTP_EnviarDinamico {

    public static void main(String[] args) {
        SMTPClient client = new SMTPClient();

        try {
            // Conectar al servidor local
            client.connect("localhost");
            System.out.println("Código conexión: " + client.getReplyCode());

            // HELO obligatorio
            client.login("localhost");
            System.out.println("Código HELO: " + client.getReplyCode());

            // Usuario y contraseña eliminados porque Mercury local permite enviar sin autenticar
            // Esto evita el error 501/503

            // Remitente y destinatario (enviarse a sí mismo para evitar error)
            String remitente = "prueba@localhost";
            String destinatario = "prueba@localhost";

            if (!client.setSender(remitente)) {
                JOptionPane.showMessageDialog(null, "Remitente inválido");
                return;
            }

            if (!client.addRecipient(destinatario)) {
                JOptionPane.showMessageDialog(null, "Destinatario inválido o no autorizado");
                return;
            }

            String asunto = JOptionPane.showInputDialog("Asunto:");
            String cuerpo = JOptionPane.showInputDialog("Cuerpo:");

            Writer w = client.sendMessageData();
            if (w == null) {
                JOptionPane.showMessageDialog(null, "No se puede enviar el mensaje. Comprueba remitente/destinatario.");
                return;
            }

            w.write("Subject: " + asunto + "\n\n" + cuerpo);
            w.close();

            if (client.completePendingCommand()) {
                JOptionPane.showMessageDialog(null, "Mensaje enviado correctamente");
                System.out.println("Código final DATA: " + client.getReplyCode());
            } else {
                JOptionPane.showMessageDialog(null, "Error al enviar mensaje");
            }

            client.logout();
            client.disconnect();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al enviar mensaje");
            e.printStackTrace();
        }
    }
}
