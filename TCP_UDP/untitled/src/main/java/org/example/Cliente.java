package org.example;

/**
 * import javafx.application.Application;
 * import javafx.application.Platform;
 * import javafx.scene.Scene;
 * import javafx.scene.control.*;
 * import javafx.scene.layout.VBox;
 * import javafx.stage.Stage;
 *
 * import java.io.DataInputStream;
 * import java.io.IOException;
 * import java.net.Socket;
 *
 * public class Cliente extends Application {
 *
 *     private TextArea areaMensajes;
 *     private Button btnSalir;
 *     private String nombre;
 *     private Socket socket;
 *     private DataInputStream entrada;
 *
 *     @Override
 *     public void start(Stage primaryStage) {
 *         TextInputDialog dialog = new TextInputDialog();
 *         dialog.setHeaderText("Introduce tu nombre:");
 *         nombre = dialog.showAndWait().orElse("Anonimo");
 *
 *         areaMensajes = new TextArea();
 *         areaMensajes.setEditable(false);
 *         btnSalir = new Button("Salir");
 *
 *         VBox root = new VBox(10, areaMensajes, btnSalir);
 *         Scene scene = new Scene(root, 400, 300);
 *
 *         primaryStage.setTitle("Cliente: " + nombre);
 *         primaryStage.setScene(scene);
 *         primaryStage.show();
 *
 *         conectarServidor("localhost", 6000);
 *
 *         btnSalir.setOnAction(e -> salir());
 *     }
 *
 *     private void conectarServidor(String host, int puerto) {
 *         new Thread(() -> {
 *             try {
 *                 socket = new Socket(host, puerto);
 *                 entrada = new DataInputStream(socket.getInputStream());
 *
 *                 while (true) {
 *                     String mensaje = entrada.readUTF();
 *                     Platform.runLater(() -> areaMensajes.appendText(mensaje + "\n"));
 *                 }
 *
 *             } catch (IOException e) {
 *                 Platform.runLater(() -> areaMensajes.appendText("Conexión cerrada.\n"));
 *             }
 *         }).start();
 *     }
 *
 *     private void salir() {
 *         try {
 *             if (socket != null) socket.close();
 *         } catch (IOException e) {
 *             e.printStackTrace();
 *         }
 *         Platform.exit();
 *     }
 *
 *     public static void main(String[] args) {
 *         launch(args);
 *     }
 * }
 */



