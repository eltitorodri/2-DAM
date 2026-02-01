package org.example.examen23.Actividad3;

import java.io.Serializable;

public class PersonajeOnePiece implements Serializable {

    private static final long serialVersionUID = 1L;

    public Barco barco;
    public Fruta fruta;
    public String rol;
    public String nombre;

    public PersonajeOnePiece(Barco barco, Fruta fruta, String rol, String nombre) {
        this.barco = barco;
        this.fruta = fruta;
        this.rol = rol;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "\nRol: " + rol +
                "\nBarco: " + barco +
                "\nFruta: " + fruta;
    }
}
