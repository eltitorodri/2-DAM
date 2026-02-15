package org.example.Posibles_Examenes;

import org.apache.commons.net.ftp.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FTP_Examen1 extends JFrame {

    private JTextField txtServidor, txtUsuario;
    private JPasswordField txtClave;
    private JButton btnConectar, btnSubir, btnSalir;
    private FTPClient ftp;

    public FTP_Examen1() {

        setTitle("Cliente FTP");
        setSize(400,250);
        setLayout(new GridLayout(5,2));

        txtServidor = new JTextField();
        txtUsuario = new JTextField();
        txtClave = new JPasswordField();

        btnConectar = new JButton("Conectar");
        btnSubir = new JButton("Subir");
        btnSalir = new JButton("Salir");

        btnSubir.setEnabled(false);

        add(new JLabel("Servidor:"));
        add(txtServidor);
        add(new JLabel("Usuario:"));
        add(txtUsuario);
        add(new JLabel("Clave:"));
        add(txtClave);
        add(btnConectar);
        add(btnSubir);
        add(btnSalir);

        ftp = new FTPClient();

        btnConectar.addActionListener(e -> conectar());
        btnSubir.addActionListener(e -> subirArchivo());
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void conectar() {
        try {
            ftp.connect(txtServidor.getText());
            ftp.enterLocalPassiveMode();

            boolean login = ftp.login(
                    txtUsuario.getText(),
                    new String(txtClave.getPassword())
            );

            if(login){
                JOptionPane.showMessageDialog(this,
                        "CONEXIÓN REALIZADA CON ÉXITO");
                btnSubir.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error de autenticación");
            }

        } catch(Exception ex){
            JOptionPane.showMessageDialog(this,
                    "No se puede realizar la conexión");
        }
    }

    private void subirArchivo() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(this);

            File file = chooser.getSelectedFile();
            FileInputStream fis = new FileInputStream(file);

            boolean subida = ftp.storeFile(file.getName(), fis);

            if(subida)
                JOptionPane.showMessageDialog(this,
                        "Archivo subido correctamente");

            fis.close();

        } catch(Exception ex){
            JOptionPane.showMessageDialog(this,
                    "Error al subir archivo");
        }
    }

    public static void main(String[] args) {
        new FTP_Examen1();
    }
}

