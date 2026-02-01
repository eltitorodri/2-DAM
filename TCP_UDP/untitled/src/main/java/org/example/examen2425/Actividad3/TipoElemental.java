package org.example.examen2425.Actividad3;

import java.io.Serializable;

public class TipoElemental implements Serializable {

    String elemento;
    String debilidad;

    public TipoElemental(String elemento, String debilidad) {
        this.elemento = elemento;
        this.debilidad = debilidad;
    }
}
