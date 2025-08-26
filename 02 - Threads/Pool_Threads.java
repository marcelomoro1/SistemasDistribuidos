import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pool_Threads {
    public static void main(String[] args) {
        // Cria um pool fixo de 3 threads
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            int tarefa = i;
            pool.execute(() -> {
                System.out.println("Executando tarefa " + tarefa + " na thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // simulando trabalho
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        pool.shutdown(); // encerra a pool
    }
}