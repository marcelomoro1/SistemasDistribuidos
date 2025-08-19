import java.util.ArrayList;
import java.util.List;

class ListaCompartilhada {
    private final List<Integer> numeros = new ArrayList<>();

    public synchronized void adicionarNumero(int umNumero) {
        numeros.add(umNumero);
        System.out.println(Thread.currentThread().getName() + " adicionou: " + umNumero);
    }

    public synchronized List<Integer> retornarNumeros() {
        return new ArrayList<>(numeros);
    }
}

// Thread PAR
class ThreadDeNumerosPares extends Thread {
    private final ListaCompartilhada listaCompartilhada;

    public ThreadDeNumerosPares(ListaCompartilhada lista) {
        this.listaCompartilhada = lista;
    }

    @Override
    public void run() {
        // Add os pares de 100 a 200
        for (int i = 100; i <= 200; i += 2) {
            listaCompartilhada.adicionarNumero(i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {}
        }
    }
}

// Thread IMPAR
class ThreadDeNumerosImpares extends Thread {
    private final ListaCompartilhada listaCompartilhada;

    public ThreadDeNumerosImpares(ListaCompartilhada lista) {
        this.listaCompartilhada = lista;
    }

    @Override
    public void run() {
        // Add os impares de 1 a 99
        for (int i = 1; i <= 99; i += 2) {
            listaCompartilhada.adicionarNumero(i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {}
        }
    }
}

public class ThreadsPar_Impar {
    public static void main(String[] args) throws InterruptedException {
        ListaCompartilhada listaCompartilhada = new ListaCompartilhada();

        Thread t1 = new ThreadDeNumerosPares(listaCompartilhada);
        t1.setName("Thread-Par");

        Thread t2 = new ThreadDeNumerosImpares(listaCompartilhada);
        t2.setName("Thread-Impar");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Lista final: " + listaCompartilhada.retornarNumeros());
    }
}