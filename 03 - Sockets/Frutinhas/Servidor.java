
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Random;

public class Servidor {

    public static void main(String[] args) {
        int porta = 12345;

        //lista de frutas
        List<String> frutas = List.of(
            "Maçã", "Banana", "Laranja", "Uva", 
            "Morango", "Abacaxi", "Manga", "Pera"
        );
        
        //objeto que gera numeros aleatorios
        Random geradorAleatorio = new Random();

        try (ServerSocket servidorSocket = new ServerSocket(porta)) {
            System.out.println("Servidor iniciado na porta " + porta + ". Aguardando clientes...");

            while (true) {
                // O método accept bloqueia a execução até que um cliente se conecte
                Socket socketCliente = servidorSocket.accept();
                System.out.println("Cliente conectado: " + socketCliente.getInetAddress().getHostAddress());

                try {
                    //Cria o comunicador pro cliente
                    Comunicador comunicador = new Comunicador(socketCliente);

                    //Escolhe uma fruta da lista
                    int indiceSorteado = geradorAleatorio.nextInt(frutas.size());
                    String frutaSorteada = frutas.get(indiceSorteado);

                    //Enviando pro cliente
                    System.out.println("Enviando fruta '" + frutaSorteada + "' para o cliente.");
                    comunicador.enviaMensagem(frutaSorteada);

                } catch (IOException e) {
                    System.err.println("Erro na comunicação com o cliente: " + e.getMessage());
                } finally {
                    socketCliente.close();
                    System.out.println("Conexão com o cliente encerrada.");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}