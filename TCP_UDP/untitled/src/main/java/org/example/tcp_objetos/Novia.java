package org.example.tcp_objetos;

import java.io.Serializable;

public class Novia  implements Serializable {

    String nombre;
    String colorPelo;
    Integer edad;

    public Novia (String nombre, String colorPelo, Integer edad) {
        this.nombre = nombre;
        this.colorPelo = colorPelo;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColorPelo() {
        return colorPelo;
    }

    public void setColorPelo(String colorPelo) {
        this.colorPelo = colorPelo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Novia: " +
                "nombre='" + nombre + '\'' +
                ", colorPelo='" + colorPelo + '\'' +
                ", edad=" + edad +
                '}';
    }
}
