package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Numeros {

    public static void popularAleatorio(ArrayList<Integer> lista, int qtd, int valores) {
        Random gerador = new Random();
        for (; qtd > 0; qtd--) {
            lista.add( gerador.nextInt(valores) );
        }
        System.out.println("Lista populada");
    }


    public static void exibir(ArrayList<Integer> lista){
        for (Object item : lista) {
            System.out.println(item);
        }
    }

    public static void bubbleSort(ArrayList<Integer> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Se o elemento atual for maior que o próximo, troque-os
                if (lista.get(j) > lista.get(j + 1)) {
                    // Troca os elementos
                    int temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }


    public static void penteSort(ArrayList<Integer> lista) {
        int n = lista.size();
        int gap = n;
        boolean trocado = true;

        while (gap != 1 || trocado) {
            // Encontra o próximo gap
            gap = (int) (gap / 1.3);
            if (gap < 1) {
                gap = 1;
            }

            trocado = false;

            // Compara os elementos com o gap atual
            for (int i = 0; i < n - gap; i++) {
                if (lista.get(i) > lista.get(i + gap)) {
                    // Troca os elementos
                    int temp = lista.get(i);
                    lista.set(i, lista.get(i + gap));
                    lista.set(i + gap, temp);
                    trocado = true;
                }
            }
        }
    }
}
