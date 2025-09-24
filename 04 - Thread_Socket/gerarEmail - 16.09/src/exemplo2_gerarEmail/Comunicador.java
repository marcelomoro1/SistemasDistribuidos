/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exemplo2_gerarEmail;

import java.io.*;
import java.net.Socket;

public class Comunicador {

    public void enviaObjeto(Socket socket, Object objeto) throws IOException {
        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
        saida.flush();
        saida.writeObject(objeto);
    }

    public Object recebeObjeto(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
        return entrada.readObject();
    }
}
