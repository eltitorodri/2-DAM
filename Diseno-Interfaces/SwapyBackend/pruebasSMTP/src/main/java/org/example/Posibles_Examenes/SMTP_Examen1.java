package org.example.Posibles_Examenes;

import org.apache.commons.net.smtp.SMTPClient;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SMTP_Examen1 extends JFrame {

    private JTextField txtServidor, txtPuerto, txtUsuario;
    private JPasswordField txtClave;
    private JRadioButton rbTLS, rbNoTLS;
    private JButton btnConectar, btnEnviar;
    private SMTPClient client;
    private boolean conectado = false;

    public SMTP_Examen1() {
        setTitle("Cliente SMTP");
        setSize(450, 300);
        setLayout(new GridLayout(7, 2));

        txtServidor = new JTextField("localhost");
        txtPuerto = new JTextField("25");
        txtUsuario = new JTextField();
        txtClave = new JPasswordField();

        rbTLS = new JRadioButton("Con TLS");
        rbNoTLS = new JRadioButton("Sin TLS", true);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbTLS);
        bg.add(rbNoTLS);

        btnConectar = new JButton("Conectar");
        btnEnviar = new JButton("Enviar");
        btnEnviar.setEnabled(false);

        add(new JLabel("Servidor:")); add(txtServidor);
        add(new JLabel("Puerto:")); add(txtPuerto);
        add(new JLabel("Usuario:")); add(txtUsuario);
        add(new JLabel("Clave:")); add(txtClave);
        add(rbTLS); add(rbNoTLS);
        add(btnConectar); add(btnEnviar);

        client = new SMTPClient();

        btnConectar.addActionListener(e -> {
            if(!conectado) conectar();
            else desconectar();
        });

        btnEnviar.addActionListener(e -> enviar());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void conectar() {
        try {
            // Conectar al servidor
            client.connect(txtServidor.getText(), Integer.parseInt(txtPuerto.getText()));
            System.out.println("Código conexión: " + client.getReplyCode());

            // HELO obligatorio
            client.login("localhost");
            System.out.println("Código HELO: " + client.getReplyCode());

            // TLS controlado (si Mercury lo soporta)
            if(rbTLS.isSelected()) {
                try {
                    client.sendCommand("EHLO localhost");
                    client.sendCommand("STARTTLS"); // Mercury local probablemente no soporta TLS
                    client.sendCommand("EHLO localhost");
                    System.out.println("TLS ejecutado (si lo soporta)");
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(this, "Servidor no soporta TLS");
                }
            }

            JOptionPane.showMessageDialog(this, "Conexión realizada correctamente");
            btnEnviar.setEnabled(true);
            btnConectar.setText("Desconectar");
            conectado = true;

        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "No se puede realizar la conexión");
            ex.printStackTrace();
        }
    }

    private void desconectar() {
        try {
            client.logout();
            client.disconnect();
            JOptionPane.showMessageDialog(this, "Desconectado correctamente");
            btnEnviar.setEnabled(false);
            btnConectar.setText("Conectar");
            conectado = false;
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al desconectar");
            ex.printStackTrace();
        }
    }

    private void enviar() {
        try {
            // Enviar a sí mismo para evitar "destinatario inválido"
            String remitente = txtUsuario.getText() + "@localhost";
            String destinatario = txtUsuario.getText() + "@localhost";

            if(!client.setSender(remitente)) {
                JOptionPane.showMessageDialog(this, "Remitente inválido");
                return;
            }

            if(!client.addRecipient(destinatario)) {
                JOptionPane.showMessageDialog(this, "Destinatario inválido o no autorizado");
                return;
            }

            Writer writer = client.sendMessageData();
            if(writer == null) {
                JOptionPane.showMessageDialog(this, "No se puede enviar mensaje. Comprueba remitente/destinatario.");
                return;
            }

            writer.write("Subject: Prueba SMTP\n\nMensaje de prueba enviado desde Java");
            writer.close();

            if(client.completePendingCommand()) {
                JOptionPane.showMessageDialog(this, "Mensaje enviado correctamente");
                System.out.println("Código final DATA: " + client.getReplyCode());
            } else {
                JOptionPane.showMessageDialog(this, "Error al enviar mensaje");
            }

        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al enviar mensaje");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SMTP_Examen1();
    }
}
