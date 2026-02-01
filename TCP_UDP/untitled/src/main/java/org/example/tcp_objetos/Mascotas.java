package org.example.tcp_objetos;

import java.io.Serializable;

public class Mascotas  implements Serializable {

    String nombre;
    String raza;
    Integer altura;

    public Mascotas(String nombre, String raza, Integer altura) {
        this.nombre = nombre;
        this.raza = raza;
        this.altura = altura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Mascotas: " +
                "nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", altura=" + altura +
                '}';
    }
}
