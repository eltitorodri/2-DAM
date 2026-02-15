package org.example.Actividad2_SMTP;

import org.apache.commons.net.smtp.*;
import javax.swing.*;
import java.awt.*;
import java.io.Writer;
import java.io.IOException;

public class ClienteSMTP extends JFrame {

    private JTextField txtServer, txtPort, txtUser, txtFrom, txtTo, txtSubject;
    private JPasswordField txtPass;
    private JTextArea txtBody;
    private JRadioButton rbSinTLS, rbConTLS;
    private JButton btnConectar, btnEnviar;

    private AuthenticatingSMTPClient client;

    public ClienteSMTP() {
        super("Cliente SMTP - Actividad 2");
        setLayout(new BorderLayout());

        // Panel Norte: Configuración de servidor
        JPanel pnlNorte = new JPanel(new GridLayout(5, 2, 5, 5));
        pnlNorte.setBorder(BorderFactory.createTitledBorder("Configuración Servidor"));
        pnlNorte.add(new JLabel("Servidor SMTP:")); txtServer = new JTextField("localhost"); pnlNorte.add(txtServer);
        pnlNorte.add(new JLabel("Puerto:")); txtPort = new JTextField("25"); pnlNorte.add(txtPort);
        pnlNorte.add(new JLabel("Usuario:")); txtUser = new JTextField(); pnlNorte.add(txtUser);
        pnlNorte.add(new JLabel("Clave:")); txtPass = new JPasswordField(); pnlNorte.add(txtPass);

        rbSinTLS = new JRadioButton("Sin TLS", true);
        rbConTLS = new JRadioButton("Con TLS");
        ButtonGroup grupoTLS = new ButtonGroup();
        grupoTLS.add(rbSinTLS); grupoTLS.add(rbConTLS);
        pnlNorte.add(rbSinTLS); pnlNorte.add(rbConTLS);

        // Panel Centro: Datos del correo
        JPanel pnlCentro = new JPanel(new BorderLayout());
        pnlCentro.setBorder(BorderFactory.createTitledBorder("Datos del Correo"));
        JPanel pnlCampos = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlCampos.add(new JLabel("Remitente (De):")); txtFrom = new JTextField(); pnlCampos.add(txtFrom);
        pnlCampos.add(new JLabel("Destinatario (Para):")); txtTo = new JTextField(); pnlCampos.add(txtTo);
        pnlCampos.add(new JLabel("Asunto:")); txtSubject = new JTextField(); pnlCampos.add(txtSubject);

        txtBody = new JTextArea(8, 30);
        pnlCentro.add(pnlCampos, BorderLayout.NORTH);
        pnlCentro.add(new JScrollPane(txtBody), BorderLayout.CENTER);

        // Panel Sur: Botones
        JPanel pnlSur = new JPanel();
        btnConectar = new JButton("Conectar");
        btnEnviar = new JButton("Enviar Mensaje");
        btnEnviar.setEnabled(false);
        pnlSur.add(btnConectar);
        pnlSur.add(btnEnviar);

        // Eventos
        btnConectar.addActionListener(e -> gestionarConexion());
        btnEnviar.addActionListener(e -> enviarCorreo());

        add(pnlNorte, BorderLayout.NORTH);
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void gestionarConexion() {
        if ("Conectar".equals(btnConectar.getText())) {
            try {
                client = new AuthenticatingSMTPClient();

                client.connect(txtServer.getText(), Integer.parseInt(txtPort.getText()));
                client.ehlo(txtServer.getText());

                if (rbConTLS.isSelected()) {
                    if (client.execTLS()) {
                        client.ehlo(txtServer.getText());
                        JOptionPane.showMessageDialog(this, "TLS negociado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(this, "Fallo en STARTTLS");
                        return;
                    }
                }

                String usuario = txtUser.getText();
                String pass = new String(txtPass.getPassword());

                if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.LOGIN, usuario, pass)) {
                    JOptionPane.showMessageDialog(this, "Usuario autenticado");
                    btnConectar.setText("Desconectar");
                    btnEnviar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Error de autenticación");
                    client.disconnect();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            desconectar();
        }
    }

    private void desconectar() {
        try {
            if (client != null && client.isConnected()) {
                client.logout();
                client.disconnect();
            }
            btnConectar.setText("Conectar");
            btnEnviar.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Servidor desconectado");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void enviarCorreo() {
        try {
            SimpleSMTPHeader header = new SimpleSMTPHeader(txtFrom.getText(), txtTo.getText(), txtSubject.getText());

            if (!client.setSender(txtFrom.getText())) {
                JOptionPane.showMessageDialog(this, "Remitente inválido");
                return;
            }

            if (!client.addRecipient(txtTo.getText())) {
                JOptionPane.showMessageDialog(this, "Destinatario inválido o no autorizado");
                return;
            }

            Writer writer = client.sendMessageData();
            if (writer == null) {
                JOptionPane.showMessageDialog(this, "Error al enviar mensaje: sendMessageData() returned null");
                return;
            }

            writer.write(header.toString());
            writer.write(txtBody.getText());
            writer.close();

            if (client.completePendingCommand()) {
                JOptionPane.showMessageDialog(this, "Mensaje enviado correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al finalizar envío");
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al enviar mensaje: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        new ClienteSMTP();
    }
}
