package org.example.examen2425.Actividad3;

import java.io.Serializable;

public class Pokemon implements Serializable {

    int numeroPokedex;
    String nombre;
    TipoElemental elemento;
    Ataque atq;

    public Pokemon(int numeroPokedex, String nombre,
                   TipoElemental elemento, Ataque atq) {
        this.numeroPokedex = numeroPokedex;
        this.nombre = nombre;
        this.elemento = elemento;
        this.atq = atq;
    }

    @Override
    public String toString() {
        return "Nº Pokedex: " + numeroPokedex +
                "\nNombre: " + nombre +
                "\nElemento: " + elemento.elemento +
                "\nDebilidad: " + elemento.debilidad +
                "\nAtaque: " + atq.ataque +
                "\nDamage: " + atq.damage;
    }
}
