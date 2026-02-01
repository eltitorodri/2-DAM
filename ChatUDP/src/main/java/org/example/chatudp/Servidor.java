package org.example.chatudp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor extends Application {

    private TextField campoMensaje;
    private TextArea areaMensajes;
    private Button btnEnviar, btnSalir;

    private ServerSocket servidor;
    private ArrayList<Socket> clientes = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        campoMensaje = new TextField();
        campoMensaje.setPromptText("Escribe tu mensaje aquí...");
        areaMensajes = new TextArea();
        areaMensajes.setEditable(false);

        btnEnviar = new Button("Enviar");
        btnSalir = new Button("Salir");

        VBox root = new VBox(10, areaMensajes, campoMensaje, btnEnviar, btnSalir);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Servidor Chat Multicliente");
        primaryStage.setScene(scene);
        primaryStage.show();

        iniciarServidor(6000);

        btnEnviar.setOnAction(e -> enviarMensaje());
        campoMensaje.setOnAction(e -> enviarMensaje());
        btnSalir.setOnAction(e -> cerrarServidor());
    }

    private void iniciarServidor(int puerto) {
        new Thread(() -> {
            try {
                servidor = new ServerSocket(puerto);
                Platform.runLater(() -> areaMensajes.appendText("Servidor iniciado en puerto " + puerto + "\n"));

                while (true) {
                    Socket cliente = servidor.accept();
                    clientes.add(cliente);
                    Platform.runLater(() ->
                            areaMensajes.appendText("Cliente conectado: " + cliente.getInetAddress() + "\n"));
                }

            } catch (IOException e) {
                Platform.runLater(() -> areaMensajes.appendText("Servidor detenido.\n"));
            }
        }).start();
    }

    private void enviarMensaje() {
        String mensaje = campoMensaje.getText().trim();
        if (mensaje.isEmpty()) return;

        Platform.runLater(() -> areaMensajes.appendText("Servidor: " + mensaje + "\n"));
        campoMensaje.clear();

        for (Socket cliente : clientes) {
            try {
                DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
                salida.writeUTF("Servidor: " + mensaje);
                salida.flush();
            } catch (IOException e) {
                Platform.runLater(() -> areaMensajes.appendText("Error enviando mensaje a un cliente.\n"));
            }
        }
    }

    private void cerrarServidor() {
        try {
            for (Socket cliente : clientes) cliente.close();
            if (servidor != null) servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
