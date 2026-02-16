package org.example.ApuntesExamen;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class clienteFTPBasico extends JFrame {

    private FTPClient ftp = new FTPClient();

    private JTextField txtServidor;
    private JTextField txtUsuario;
    private JPasswordField txtClave;

    private JButton btnConectar, btnSubir, btnListar, btnDescargar, btnBorrar,
            btnCrearDir, btnEliminarDir, btnDesconectar;

    public clienteFTPBasico() {
        setTitle("Cliente FTP Completo - PSP");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos de texto
        txtServidor = new JTextField();
        txtUsuario = new JTextField();
        txtClave = new JPasswordField();

        btnConectar = new JButton("Conectar");
        btnSubir = new JButton("Subir");
        btnListar = new JButton("Listar");
        btnDescargar = new JButton("Descargar");
        btnBorrar = new JButton("Borrar Archivo");
        btnCrearDir = new JButton("Crear Directorio");
        btnEliminarDir = new JButton("Eliminar Directorio");
        btnDesconectar = new JButton("Desconectar");

        // Deshabilitados al inicio
        btnSubir.setEnabled(false);
        btnListar.setEnabled(false);
        btnDescargar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnCrearDir.setEnabled(false);
        btnEliminarDir.setEnabled(false);
        btnDesconectar.setEnabled(false);

        // Layout
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Servidor:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        add(txtServidor, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        add(txtUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        add(txtClave, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        add(btnConectar, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(btnSubir, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        add(btnListar, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(btnDescargar, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        add(btnBorrar, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(btnCrearDir, gbc);
        gbc.gridx = 1; gbc.gridy = 6;
        add(btnEliminarDir, gbc);

        gbc.gridx = 1; gbc.gridy = 7;
        add(btnDesconectar, gbc);

        // 🔌 ACCIONES

        // Conectar
        btnConectar.addActionListener(e -> conectar());

        // Subir
        btnSubir.addActionListener(e -> subirArchivo());

        // Listar
        btnListar.addActionListener(e -> listarArchivos());

        // Descargar
        btnDescargar.addActionListener(e -> descargarArchivo());

        // Borrar
        btnBorrar.addActionListener(e -> borrarArchivo());

        // Crear directorio
        btnCrearDir.addActionListener(e -> crearDirectorio());

        // Eliminar directorio
        btnEliminarDir.addActionListener(e -> eliminarDirectorio());

        // Desconectar
        btnDesconectar.addActionListener(e -> desconectar());
    }

    // ====================== MÉTODOS ======================
    private void mostrar(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void conectar() {
        try {
            ftp.connect(txtServidor.getText());
            boolean login = ftp.login(txtUsuario.getText(), new String(txtClave.getPassword()));

            if (login) {
                ftp.enterLocalPassiveMode();
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                mostrar("CONEXIÓN REALIZADA CON ÉXITO");

                btnSubir.setEnabled(true);
                btnListar.setEnabled(true);
                btnDescargar.setEnabled(true);
                btnBorrar.setEnabled(true);
                btnCrearDir.setEnabled(true);
                btnEliminarDir.setEnabled(true);
                btnDesconectar.setEnabled(true);

            } else {
                mostrar("Usuario o contraseña incorrectos");
            }
        } catch (IOException ex) {
            mostrar("Error conexión: " + ex.getMessage());
        }
    }

    private void subirArchivo() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try (FileInputStream fis = new FileInputStream(file)) {
                boolean ok = ftp.storeFile(file.getName(), fis);
                mostrar(ok ? "Archivo subido correctamente" : "No se pudo subir el archivo");
            } catch (IOException ex) {
                mostrar("Error: " + ex.getMessage());
            }
        }
    }

    private void listarArchivos() {
        try {
            FTPFile[] archivos = ftp.listFiles();
            StringBuilder sb = new StringBuilder();
            for (FTPFile f : archivos) {
                sb.append(f.getName()).append("\n");
            }
            if (sb.length() == 0) sb.append("Directorio vacío");
            mostrar(sb.toString());
        } catch (IOException ex) {
            mostrar("Error al listar archivos");
        }
    }

    private void descargarArchivo() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del archivo a descargar:");
        if (nombre != null) {
            JFileChooser fc = new JFileChooser();
            fc.setSelectedFile(new File(nombre));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File destino = fc.getSelectedFile();
                try (FileOutputStream fos = new FileOutputStream(destino)) {
                    boolean ok = ftp.retrieveFile(nombre, fos);
                    mostrar(ok ? "Archivo descargado correctamente" : "No se pudo descargar");
                } catch (IOException ex) {
                    mostrar("Error: " + ex.getMessage());
                }
            }
        }
    }

    private void borrarArchivo() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del archivo a borrar:");
        if (nombre != null) {
            try {
                boolean ok = ftp.deleteFile(nombre);
                mostrar(ok ? "Archivo eliminado" : "No se pudo eliminar");
            } catch (IOException ex) {
                mostrar("Error al borrar archivo");
            }
        }
    }

    private void crearDirectorio() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del nuevo directorio:");
        if (nombre != null) {
            try {
                boolean ok = ftp.makeDirectory(nombre);
                mostrar(ok ? "Directorio creado" : "No se pudo crear");
            } catch (IOException ex) {
                mostrar("Error al crear directorio");
            }
        }
    }

    private void eliminarDirectorio() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del directorio a eliminar:");
        if (nombre != null) {
            try {
                boolean ok = ftp.removeDirectory(nombre);
                mostrar(ok ? "Directorio eliminado" : "No se pudo eliminar");
            } catch (IOException ex) {
                mostrar("Error al eliminar directorio");
            }
        }
    }

    private void desconectar() {
        try {
            ftp.logout();
            ftp.disconnect();
            mostrar("Desconectado");
        } catch (IOException ignored) {}
    }

    // ====================== MAIN ======================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new clienteFTPBasico().setVisible(true);
        });
    }
}
