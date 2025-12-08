package org.example.actividad6_segundoexamen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML private Button botonControl;
    @FXML private ProgressBar progressBar1;
    @FXML private ProgressBar progressBar2;
    @FXML private ProgressBar progressBar3;
    @FXML private Slider slider1;
    @FXML private Slider slider2;
    @FXML private Slider slider3;
    @FXML private Text textPrioridad1;
    @FXML private Text textPrioridad2;
    @FXML private Text textPrioridad3;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;

    private List<Thread> hilos;
    private boolean carreraEnCurso = false;
    private int hilosTerminados = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hilos = new ArrayList<Thread>();

        configurarSlider(slider1, textPrioridad1);
        configurarSlider(slider2, textPrioridad2);
        configurarSlider(slider3, textPrioridad3);

        botonControl.setDisable(false);
    }

    private void configurarSlider(Slider slider, Text prioridadText) {
        slider.setMin(Thread.MIN_PRIORITY);
        slider.setMax(Thread.MAX_PRIORITY);
        slider.setValue(Thread.NORM_PRIORITY);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);

        prioridadText.setText("Prioridad: " + (int) slider.getValue());
        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            prioridadText.setText("Prioridad: " + newVal.intValue());
        });
    }

    @FXML
    private void controlarAnimacion() {
        if (carreraEnCurso) return;

        hilos.clear();
        hilosTerminados = 0;
        carreraEnCurso = true;

        botonControl.setDisable(true);

        resetearComponentes();

        long tiempoDormido = 10L;

        HiloCorredor corredor1 = new HiloCorredor("Corredor 1", tiempoDormido, progressBar1, label1, this);
        Thread t1 = corredor1.getThread();
        t1.setPriority((int) slider1.getValue());
        hilos.add(t1);

        HiloCorredor corredor2 = new HiloCorredor("Corredor 2", tiempoDormido, progressBar2, label2, this);
        Thread t2 = corredor2.getThread();
        t2.setPriority((int) slider2.getValue());
        hilos.add(t2);

        HiloCorredor corredor3 = new HiloCorredor("Corredor 3", tiempoDormido, progressBar3, label3, this);
        Thread t3 = corredor3.getThread();
        t3.setPriority((int) slider3.getValue());
        hilos.add(t3);

        hilos.forEach(Thread::start);
    }

    private void resetearComponentes() {
        progressBar1.setProgress(0.0);
        progressBar2.setProgress(0.0);
        progressBar3.setProgress(0.0);

        label1.setText("Contador: 0");
        label2.setText("Contador: 0");
        label3.setText("Contador: 0");

        botonControl.setText("Comenzar Carrera");
    }

    public synchronized void carreraFinalizada(String nombreGanador) {

        hilosTerminados++;

        if (hilosTerminados == 1) {
            carreraEnCurso = false;

            botonControl.setText("¡GANADOR: " + nombreGanador + "! - Volver a empezar");

            hilos.forEach(Thread::interrupt);

            botonControl.setDisable(false);
        }
    }
}