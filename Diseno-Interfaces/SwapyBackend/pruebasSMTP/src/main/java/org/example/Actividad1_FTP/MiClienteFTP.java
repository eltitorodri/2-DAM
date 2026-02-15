package org.example.Actividad1_FTP;

import org.apache.commons.net.ftp.FTPClient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiClienteFTP extends JFrame {
    // Componentes
    private JTextField txtServer, txtUser;
    private JPasswordField txtPass;
    private JButton btnConectar, btnSubir, btnBajar;
    private FTPClient ftpClient;

    public MiClienteFTP() {
        super("Mi Cliente FTP");
        setLayout(new FlowLayout());

        // Inicializar campos
        txtServer = new JTextField(15);
        txtUser = new JTextField(10);
        txtPass = new JPasswordField(10); // Requisito: JPasswordField
        btnConectar = new JButton("Conectar");

        // Botones de operaciones (Inicialmente deshabilitados)
        btnSubir = new JButton("Subir Archivo");
        btnBajar = new JButton("Descargar Archivo");
        btnSubir.setEnabled(false);
        btnBajar.setEnabled(false);

        // Añadir a la ventana
        add(new JLabel("Servidor:")); add(txtServer);
        add(new JLabel("Usuario:")); add(txtUser);
        add(new JLabel("Clave:")); add(txtPass);
        add(btnConectar);
        add(btnSubir); add(btnBajar);

        // Evento del botón conectar
        btnConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectarServidor();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setVisible(true);
    }

    private void conectarServidor() {
        ftpClient = new FTPClient();
        String servidor = txtServer.getText();
        String usuario = txtUser.getText();
        String password = new String(txtPass.getPassword());

        try {
            ftpClient.connect(servidor);
            boolean login = ftpClient.login(usuario, password);

            if (login) {
                // ÉXITO: Habilitar botones y mostrar mensaje
                btnSubir.setEnabled(true);
                btnBajar.setEnabled(true);
                JOptionPane.showMessageDialog(this, "CONEXIÓN REALIZADA CON ÉXITO");
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new MiClienteFTP();
    }
}