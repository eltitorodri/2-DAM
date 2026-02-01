package org.example.examen2425.Actividad3;

import java.io.Serializable;

public class Ataque implements Serializable {

    String ataque;
    int damage;

    public Ataque(String ataque, int damage) {
        this.ataque = ataque;
        this.damage = damage;
    }
}
