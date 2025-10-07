
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) {
        String host = "localhost";
        int porta = 12345;

        System.out.println("Tentando conectar ao servidor em " + host + ":" + porta);

        try (Socket socket = new Socket(host, porta)) {
            System.out.println("Conectado com sucesso!");

            // Usa o Comunicador para receber a mensagem
            Comunicador comunicador = new Comunicador(socket);
            String frutaRecebida = comunicador.recebeMensagem();
            
            System.out.println("------------------------------------");
            System.out.println("CÓDIGO RECEBIDO: " + frutaRecebida);
            System.out.println("------------------------------------");

        } catch (UnknownHostException e) {
            System.err.println("Servidor não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de conexão com o servidor. Ele está rodando? Erro: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao receber o objeto: " + e.getMessage());
        }
    }
}