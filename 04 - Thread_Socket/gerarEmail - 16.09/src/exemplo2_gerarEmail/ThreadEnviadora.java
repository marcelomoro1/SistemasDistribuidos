
package exemplo2_gerarEmail;

import java.io.*;
import java.net.*;

public class ThreadEnviadora extends Thread {
    private final Socket socket;
    private final Object objeto;

    public ThreadEnviadora(Socket socket, Object objeto) {
        this.socket = socket;
        this.objeto = objeto;
    }

    @Override
    public void run() {
        try {
            Comunicador comunicador = new Comunicador();
            comunicador.enviaObjeto(socket, objeto);  // Envia o objeto
            System.out.println("Objeto enviado: " + objeto);
           
            socket.close();
            System.out.println("Socket fechado após o envio.");
        } catch (IOException e) {
            System.out.println("Erro ao enviar dados: " + e.getMessage());
        }
    }
}
