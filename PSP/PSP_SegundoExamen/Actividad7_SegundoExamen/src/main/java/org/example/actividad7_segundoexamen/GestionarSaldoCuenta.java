package org.example.actividad7_segundoexamen;

import java.util.Scanner;

public class GestionarSaldoCuenta {

    private float saldo;
    private float maximo;

    public GestionarSaldoCuenta(float saldo, float maximo) {
        this.saldo = saldo;
        this.maximo = maximo;
    }

    public float obtenerSaldo() {
        System.out.println("Tu saldo actual es: " + this.saldo);
        return this.saldo;
    }

    public synchronized void nuevoIngreso(float cantidad) {

        if (saldo + cantidad > maximo) {
            System.out.println("¡Error! No se puede superar el saldo máximo de " + maximo);
        } else {
            saldo += cantidad;
            System.out.println("Ingresado: " + cantidad + " | Saldo actual: " + saldo);
        }
    }

    public synchronized void nuevoReintegro(float cantidad) {
        if (cantidad > saldo) {
            System.out.println("¡Error! Saldo insuficiente para reintegro de " + cantidad);
        } else {
            saldo -= cantidad;
            System.out.println("Reintegrado: " + cantidad + " | Saldo actual: " + saldo);
        }
    }

}
