package org.example.chatudp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteNumeroFX extends Application {

    private TextField campoNumero;
    private TextArea areaMensajes;
    private Button btnEnviar, btnLimpiar, btnSalir;

    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;

    @Override
    public void start(Stage primaryStage) {
        campoNumero = new TextField();
        campoNumero.setPromptText("Introduce un número 0-25");

        areaMensajes = new TextArea();
        areaMensajes.setEditable(false);

        btnEnviar = new Button("Enviar");
        btnLimpiar = new Button("Limpiar");
        btnSalir = new Button("Salir");

        VBox root = new VBox(10, areaMensajes, campoNumero, btnEnviar, btnLimpiar, btnSalir);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Cliente Adivina el Número");
        primaryStage.setScene(scene);
        primaryStage.show();

        conectarServidor("localhost", 6000);

        btnEnviar.setOnAction(e -> enviarNumero());
        btnLimpiar.setOnAction(e -> limpiar());
        btnSalir.setOnAction(e -> salir());
        campoNumero.setOnAction(e -> enviarNumero());
    }

    private void conectarServidor(String host, int puerto) {
        new Thread(() -> {
            try {
                socket = new Socket(host, puerto);
                entrada = new DataInputStream(socket.getInputStream());
                salida = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    String respuesta = entrada.readUTF();
                    Platform.runLater(() -> areaMensajes.appendText(respuesta + "\n"));
                }

            } catch (IOException e) {
                Platform.runLater(() -> areaMensajes.appendText("Conexión cerrada.\n"));
            }
        }).start();
    }

    private void enviarNumero() {
        String texto = campoNumero.getText().trim();
        if (texto.isEmpty()) return;

        try {
            salida.writeUTF(texto);
            salida.flush();
            campoNumero.clear();
        } catch (IOException e) {
            areaMensajes.appendText("Error enviando número.\n");
        }
    }

    private void limpiar() {
        campoNumero.clear();
        areaMensajes.clear();
    }

    private void salir() {
        try {
            salida.writeUTF("*");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

