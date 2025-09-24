package exemplo2_gerarEmail;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class ClienteTCPBasico {
    public static void main(String[] args) {
        try {
            int porta = 50000;
            String nome = JOptionPane.showInputDialog(null, "Nome completo");

            try (Socket cliente = new Socket("localhost", porta)) {
                Comunicador comunicador = new Comunicador();
                comunicador.enviaObjeto(cliente, nome); // Enviar nome
                // Receber objeto
            Pessoa p = (Pessoa) comunicador.recebeObjeto(cliente);
            if (p == null) {
                JOptionPane.showMessageDialog(null, "Seu nome já está na lista com um email gerado");
            } else {
                // Verificar que a pessoa foi recebida corretamente
                System.out.println("Pessoa recebida: " + p);
                JOptionPane.showMessageDialog(null, "Pessoa criada e recebida: " + p);
            }

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}