package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ArrayList<Integer> lista1 = new ArrayList<Integer>();
        ArrayList<Integer> lista2 = new ArrayList<Integer>();


        Thread t1 = new Thread() {
            public void run() {
                Numeros.popularAleatorio(lista1,10, 10000);
            }
        };
        t1.start();


        Thread t2 = new Thread() {
            public void run() {
                Numeros.popularAleatorio(lista2,10, 3000);
            }
        };
        t2.start();

        try {
            t1.join(); //espera a thread t1 terminar
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Numeros.exibir(lista1);
        Numeros.exibir(lista2);

        System.out.println("Ordenando as listas");

        System.out.println("Lista 1");

        Thread t3 = new Thread() {
            public void run() {
                Numeros.bubbleSort(lista1);
                Numeros.exibir(lista1);
            }
        };
        t3.start();
        t3.join();

        System.out.println("Lista 2");

        Thread t4 = new Thread() {
            public void run() {
                Numeros.bubbleSort(lista2);
                Numeros.exibir(lista2);
            }
        };
        t4.start();
        t4.join();

    }
}