package org.example.tcp_objetos;

import java.io.Serializable;

public class Jose implements Serializable {

    Novia novia;
    Mascotas mascotas;
    String nombre;
    Integer edad;

    public Jose(Novia novia, Mascotas mascotas, String nombre, Integer edad) {
        this.novia = novia;
        this.mascotas = mascotas;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Novia getNovia() {
        return novia;
    }

    public void setNovia(Novia novia) {
        this.novia = novia;
    }

    public Mascotas getMascotas() {
        return mascotas;
    }

    public void setMascotas(Mascotas mascotas) {
        this.mascotas = mascotas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Jose: " +
                "novia=" + novia +
                ", mascotas=" + mascotas +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
