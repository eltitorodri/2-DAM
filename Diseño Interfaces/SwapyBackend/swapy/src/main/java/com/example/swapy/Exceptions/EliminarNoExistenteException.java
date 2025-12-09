package com.example.swapy.Exceptions;

public class EliminarNoExistenteException extends RuntimeException {

    public EliminarNoExistenteException(String mensaje){
        super(mensaje);
    }
}
