package org.example.examen23.Actividad3;

import java.io.Serializable;

public class Fruta implements Serializable {

    String nombre;
    String descripcion;
    String apariencia;

    public Fruta(String apariencia, String descripcion, String nombre) {
        this.apariencia = apariencia;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getApariencia() {
        return apariencia;
    }

    public void setApariencia(String apariencia) {
        this.apariencia = apariencia;
    }
}
