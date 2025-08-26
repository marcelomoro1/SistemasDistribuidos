import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExclusaoMutuaExemplo {
    private static int contador = 0;
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable tarefa = () -> {
            for (int i = 0; i < 5; i++) {
                lock.lock(); //Entrando na seção critica
                try {
                    int valor = contador;
                    System.out.println(Thread.currentThread().getName() + " acessou contador = " + valor);
                    contador = valor + 1;
                } finally {
                    lock.unlock(); //Desbloqueando a seção critica
                }

                try {
                    Thread.sleep(500); //Simulando trabalho fora da seção crítica
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //As threads que vão disputar no contador
        Thread t1 = new Thread(tarefa, "Thread-1");
        Thread t2 = new Thread(tarefa, "Thread-2");
        Thread t3 = new Thread(tarefa, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}