package org.example.actividad7_segundoexamen;

public class Persona extends Thread{

    private String nombre;
    private GestionarSaldoCuenta cuenta;

    public Persona(String nombre, GestionarSaldoCuenta cuenta) {
        this.nombre = nombre;
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        try{
            int cantidad = (int)(Math.random()*2000+1);
            System.out.println(nombre + " intenta ingresar "+ cantidad);
            cuenta.nuevoIngreso(cantidad);
            Thread.sleep(500);

            cantidad = (int)(Math.random()*2000+1);
            System.out.println(nombre + " intenta reintegrar "+ cantidad);
            cuenta.nuevoReintegro(cantidad);
            Thread.sleep(500);

            cantidad = (int)(Math.random()*2000+1);
            System.out.println(nombre + " intenta ingresar "+ cantidad);
            cuenta.nuevoIngreso(cantidad);
            Thread.sleep(500);

            cantidad = (int)(Math.random()*2000+1);
            System.out.println(nombre + " intenta reintegrar "+ cantidad);
            cuenta.nuevoReintegro(cantidad);
            Thread.sleep(500);

        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
