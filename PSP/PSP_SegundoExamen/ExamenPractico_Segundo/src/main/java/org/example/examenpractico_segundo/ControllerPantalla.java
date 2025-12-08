package org.example.examenpractico_segundo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class ControllerPantalla {

    // Labels de parcelas
    @FXML private Label labelParcela1, labelParcela2, labelParcela3, labelParcela4;
    @FXML private Label labelParcela5, labelParcela6, labelParcela7, labelParcela8;

    // Botones de riego
    @FXML private Button botonRegar1, botonRegar2, botonRegar3, botonRegar4;
    @FXML private Button botonRegar5, botonRegar6, botonRegar7, botonRegar8;

    // Botones de control general
    @FXML private Button botonPausar, botonComenzar, botonReiniciar;

    // TextFields
    @FXML private TextField textFiled1, textFiled2, textFiled3, textFiled4;
    @FXML private TextField textFiled5, textFiled6, textFiled7, textFiled8;

    // Lista de Cultivos
    private List<Cultivo> cultivos;

    // Sistema de riego
    private SistemaRiego sistemaRiego;

    @FXML
    public void initialize() {
        // Inicializamos lista de cultivos
        cultivos = new ArrayList<>();
        Label[] labels = {labelParcela1, labelParcela2, labelParcela3, labelParcela4,
                labelParcela5, labelParcela6, labelParcela7, labelParcela8};

        for (Label label : labels) {
            Cultivo c = new Cultivo();
            c.setLabel(label);
            cultivos.add(c);
        }

        // Inicializamos el sistema de riego
        sistemaRiego = new SistemaRiego();
        sistemaRiego.setCultivos(cultivos);
    }

    // Métodos para botones de riego individuales
    @FXML private void regarParcela1() { sistemaRiego.regarParcela(cultivos.get(0)); }
    @FXML private void regarParcela2() { sistemaRiego.regarParcela(cultivos.get(1)); }
    @FXML private void regarParcela3() { sistemaRiego.regarParcela(cultivos.get(2)); }
    @FXML private void regarParcela4() { sistemaRiego.regarParcela(cultivos.get(3)); }
    @FXML private void regarParcela5() { sistemaRiego.regarParcela(cultivos.get(4)); }
    @FXML private void regarParcela6() { sistemaRiego.regarParcela(cultivos.get(5)); }
    @FXML private void regarParcela7() { sistemaRiego.regarParcela(cultivos.get(6)); }
    @FXML private void regarParcela8() { sistemaRiego.regarParcela(cultivos.get(7)); }

    // Métodos de control general
    @FXML private void iniciarSimulacion() { /* Aquí se pondrán los hilos */ }
    @FXML private void pausarSimulacion() { /* Pausa los hilos */ }
    @FXML private void reiniciarSimulacion() {
        for (Cultivo c : cultivos) {
            c.setEstado("Sin Plantar");
            c.cambiarEstado();
        }
    }
}
