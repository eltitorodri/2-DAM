package org.example.actividad5_segundoexamen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class AppController implements Initializable {

    @FXML private Label letraO;
    @FXML private AnchorPane rootPane;
    @FXML private Button botonControl;

    private Timeline timeline;
    private int direccion = 1;
    private final double VELOCIDAD = 2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        letraO.setLayoutX(1.0);

        timeline = new Timeline(
                new KeyFrame(Duration.millis(10), this::moverLetra)
        );

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();

    }

    private void moverLetra(ActionEvent event) {

        double currentX = letraO.getLayoutX();

        double paneWidth = rootPane.getWidth();

        double letraWidth = letraO.getWidth();

        if (paneWidth > 0) {
            if (currentX + letraWidth >= paneWidth && direccion == 1) {
                direccion = -1;
            }
            else if (currentX <= 1.0 && direccion == -1) {
                direccion = 1;
            }
        }

        letraO.setLayoutX(currentX + (direccion * VELOCIDAD));

    }

    @FXML
    private void controlarAnimacion(ActionEvent event) {
        if (botonControl.getText().equals("Finalizar Hilo")) {
            timeline.stop();
            botonControl.setText("Reanudar Hilo");
        }
        else{
            timeline.play();
            botonControl.setText("Finalizar Hilo");
        }
    }

}
