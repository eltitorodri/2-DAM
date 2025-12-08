package org.example.actividad7_segundoexamen;

public class Main {
    public static void main(String[] args) {
        GestionarSaldoCuenta cuenta = new GestionarSaldoCuenta(1000,5000);

        Persona p1 = new Persona("Rodrigo", cuenta);
        Persona p2 = new Persona("Maria", cuenta);

        p1.start();
        p2.start();

    }
}
