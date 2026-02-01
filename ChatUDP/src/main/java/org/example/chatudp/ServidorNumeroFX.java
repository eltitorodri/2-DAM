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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ServidorNumeroFX extends Application {

    private TextArea areaVisor;
    private Button btnGenerar, btnSalir;

    private ServerSocket servidor;
    private ArrayList<Socket> clientes = new ArrayList<>();
    private int numeroAleatorio = -1;

    @Override
    public void start(Stage primaryStage) {
        areaVisor = new TextArea();
        areaVisor.setEditable(false);

        btnGenerar = new Button("Generar");
        btnSalir = new Button("Salir");

        VBox root = new VBox(10, areaVisor, btnGenerar, btnSalir);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Servidor Adivina el Número");
        primaryStage.setScene(scene);
        primaryStage.show();

        iniciarServidor(6000);

        btnGenerar.setOnAction(e -> generarNumero());
        btnSalir.setOnAction(e -> cerrarServidor());
    }

    private void iniciarServidor(int puerto) {
        new Thread(() -> {
            try {
                servidor = new ServerSocket(puerto);
                Platform.runLater(() -> areaVisor.appendText("Servidor iniciado en puerto " + puerto + "\n"));

                while (true) {
                    Socket cliente = servidor.accept();
                    clientes.add(cliente);
                    Platform.runLater(() -> areaVisor.appendText(
                            "Cliente conectado: " + cliente.getInetAddress() + ":" + cliente.getPort() + "\n"));

                    new HiloCliente(cliente).start();
                }

            } catch (IOException e) {
                Platform.runLater(() -> areaVisor.appendText("Servidor detenido.\n"));
            }
        }).start();
    }

    private void generarNumero() {
        Random r = new Random();
        numeroAleatorio = r.nextInt(26); // 0 a 25
        areaVisor.appendText("Número aleatorio generado: " + numeroAleatorio + "\n");
    }

    private void cerrarServidor() {
        try {
            for (Socket c : clientes) c.close();
            if (servidor != null) servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }

    // Hilo que atiende al cliente
    class HiloCliente extends Thread {
        private Socket cliente;

        HiloCliente(Socket cliente) {
            this.cliente = cliente;
        }

        @Override
        public void run() {
            try {
                DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

                while (true) {
                    String mensaje = entrada.readUTF();

                    if ("*".equals(mensaje)) {
                        Platform.runLater(() -> areaVisor.appendText(
                                "Cliente desconectado: " + cliente.getInetAddress() + ":" + cliente.getPort() + "\n"));
                        break;
                    }

                    int numero;
                    try {
                        numero = Integer.parseInt(mensaje);
                    } catch (NumberFormatException e) {
                        salida.writeUTF("No es un número válido.");
                        salida.flush();
                        continue;
                    }

                    if (numero == numeroAleatorio) {
                        salida.writeUTF("¡Correcto! Era " + numeroAleatorio);
                    } else {
                        salida.writeUTF("Incorrecto. Intenta de nuevo.");
                    }
                    salida.flush();
                }

                cliente.close();

            } catch (IOException e) {
                Platform.runLater(() -> areaVisor.appendText("Error con cliente.\n"));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

