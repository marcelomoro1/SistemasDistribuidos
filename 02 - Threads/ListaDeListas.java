import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe para gerar e popular as listas
 */
class PopulaLista implements Runnable {
    private final List<Integer> lista;
    private final int tamanho;
    private final int min;
    private final int max;

    public PopulaLista(List<Integer> lista, int tamanho, int min, int max) {
        this.lista = lista;
        this.tamanho = tamanho;
        this.min = min;
        this.max = max;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            lista.add(random.nextInt(max - min + 1) + min);
        }
    }
}

/**
 * Classe para calcular a média de uma lista de números.
 */
class CalculaMedia implements Runnable {
    private final List<Integer> lista;
    private double media;

    public CalculaMedia(List<Integer> lista) {
        this.lista = lista;
        this.media = 0.0;
    }

    @Override
    public void run() {
        if (lista.isEmpty()) {
            this.media = 0.0;
            return;
        }

        double soma = 0;
        for (Integer numero : lista) {
            soma += numero;
        }
        this.media = soma / lista.size();
    }

    public double getMedia() {
        return media;
    }
}

public class ListaDeListas {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        
        int nNumeros = random.nextInt(99001) + 1000;
        int mListas = 1000;

        if (nNumeros < mListas) {
            System.out.println("O número total de números deve ser maior ou igual ao número de listas.");
            return;
        }

        List<List<Integer>> listaDeListas = new ArrayList<>();
        List<Thread> threadsPopuladoras = new ArrayList<>();
        
        int numerosPorLista = nNumeros / mListas;
        int numerosRestantes = nNumeros % mListas;
        int min = 1000;
        int max = 100000;

        System.out.println(String.format("Distribuindo %d números em %d listas.", nNumeros, mListas));

        for (int i = 0; i < mListas; i++) {
            List<Integer> lista = new ArrayList<>();
            listaDeListas.add(lista);

            int nElementos = numerosPorLista;
            if (i < numerosRestantes) {
                nElementos++;
            }
            
            Runnable worker = new PopulaLista(lista, nElementos, min, max);
            Thread thread = new Thread(worker);
            threadsPopuladoras.add(thread);
            thread.start();
        }

        for (Thread thread : threadsPopuladoras) {
            thread.join();
        }

        System.out.println("\nListas preenchidas. Calculando as médias...");

        List<Thread> threadsCalculadoras = new ArrayList<>();
        List<CalculaMedia> tarefasCalculo = new ArrayList<>();

        for (int i = 0; i < mListas; i++) {
            CalculaMedia tarefa = new CalculaMedia(listaDeListas.get(i));
            tarefasCalculo.add(tarefa);

            Thread thread = new Thread(tarefa);
            threadsCalculadoras.add(thread);
            thread.start();
        }

        for (Thread thread : threadsCalculadoras) {
            thread.join();
        }

        System.out.println("Médiass calculadas.");

        double somaDasMedias = 0;
        for (CalculaMedia tarefa : tarefasCalculo) {
            somaDasMedias += tarefa.getMedia();
        }
        
        double mediaGeral = somaDasMedias / mListas;
        System.out.println(String.format("\nA média geral de todas as listas é: %.2f", mediaGeral));
    }
}