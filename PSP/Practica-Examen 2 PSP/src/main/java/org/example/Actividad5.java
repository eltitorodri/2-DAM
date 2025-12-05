package org.example;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.text.Text;
import javafx.scene.control.Button;


public class Actividad5 extends Application {

    private Text letra;
    private double x=1;
    private boolean derecha = true;
    private Timeline timeline;

    @Override
    public void start (Stage stage) {

        letra = new Text("A");
        letra.setX(1);
        letra.setY(100);
        letra.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        timeline = new Timeline(
                new KeyFrame(Duration.millis(20), e -> moverLetra())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        x = letra.getX();
        derecha = true;

        Button boton = new Button("Finalizar Hilo");

        boton.setOnAction(e -> {
            if(timeline.getStatus() == Animation.Status.RUNNING){
                timeline.pause();
                boton.setText("Reanudar Hilo");
            } else {
                timeline.play();
                boton.setText("Finalizar Hilo");
            }
        });

        Pane root = new Pane();
        root.getChildren().addAll(letra, boton);

        Scene scene = new Scene(root, 500, 200);
        stage.setScene(scene);
        stage.show();

    }

    private void moverLetra() {
        double width = 500;
        if(derecha) {
            x+=5;
            if (x > width - 40) {
                derecha = false;
            }
        } else {
            x -= 5;
            if (x <= 1){
                derecha = true;
            }
        }
        letra.setX(x);
    }

}
