package com.example.swapy.Exceptions;

public class ElementoNoEncontradoException  extends RuntimeException{

    public ElementoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}