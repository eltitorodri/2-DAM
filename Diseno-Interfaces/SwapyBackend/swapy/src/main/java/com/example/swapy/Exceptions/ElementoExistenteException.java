package com.example.swapy.Exceptions;

public class ElementoExistenteException extends RuntimeException {

    private final String nombreCampo;

    public ElementoExistenteException(String nombreCampo, String mensaje) {
        super(mensaje);
        this.nombreCampo = nombreCampo;
    }

    public String getNombreCampo() {
        return nombreCampo;
    }
}
