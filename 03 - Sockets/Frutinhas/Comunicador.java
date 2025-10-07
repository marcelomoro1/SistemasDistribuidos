
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Comunicador {

    private ObjectOutputStream escritor;
    private ObjectInputStream leitor;

    public Comunicador(Socket socket) throws IOException {
        this.escritor = new ObjectOutputStream(socket.getOutputStream());
        this.leitor = new ObjectInputStream(socket.getInputStream());
    }

    public String recebeMensagem() throws IOException, ClassNotFoundException {
        return (String) leitor.readObject();
    }

    public void enviaMensagem(String mensagem) throws IOException {
        escritor.flush();
        escritor.writeObject(mensagem);
    }
}