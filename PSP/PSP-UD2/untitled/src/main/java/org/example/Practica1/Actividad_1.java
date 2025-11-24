package org.example.Practica1;

public class Actividad_1 extends Thread{

    @Override
    public void run(){
        System.out.println("Hola Mundo! " + this.getId());
    }

}
