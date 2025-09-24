package exemplo2_gerarEmail;

import java.io.*;
import java.net.*;
import java.util.Set;

public class ThreadRecebedora extends Thread {
    private Socket socket;
    private Set<Pessoa> listaDePessoas;
    private IntefaceServidor servidor;

    public ThreadRecebedora(Socket socket, Set<Pessoa> listaDePessoas, IntefaceServidor servidor) {
        this.socket = socket;
        this.listaDePessoas = listaDePessoas;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try {
            Comunicador comunicador = new Comunicador();
            String nomePessoa = (String) comunicador.recebeObjeto(socket);

            // Gerar o e-mail
            String vetorNome[] = nomePessoa.split(" ");
            String email = vetorNome[0] + "." + vetorNome[vetorNome.length - 1] + "@ufn.edu.br";
            nomePessoa = nomePessoa.toUpperCase();
            email = email.toLowerCase();
            System.out.println("Recebido do cliente: " + nomePessoa);
            Pessoa p = new Pessoa(nomePessoa, email);

            // Verificar se a pessoa já existe
            synchronized (listaDePessoas) {
                boolean encontrado = listaDePessoas.contains(p);
                if (!encontrado) {
                    listaDePessoas.add(p); // Adiciona a pessoa à lista
                    System.out.println("Enviando pessoa: " + p);
                    ThreadEnviadora threadEnviadora = new ThreadEnviadora(socket, p); // Envia a pessoa criada
                    threadEnviadora.start(); // Inicia a ThreadEnviadora
                } else {
                    System.out.println("Pessoa já existe. Enviando null.");
                    ThreadEnviadora threadEnviadora = new ThreadEnviadora(socket, null); // Envia null se já existir
                    threadEnviadora.start(); // Inicia a ThreadEnviadora
                }

                // Adicionando a atualização da interface mesmo se a pessoa já existir
                System.out.println("Atualizando área de texto com: Cliente: " + nomePessoa + "\nEmail: " + email);
                servidor.atualizaAreaTexto("Cliente: " + nomePessoa + "\nEmail: " + email + "\n");
            }

            System.out.println("Verificando se a pessoa já existe na lista: " + p);
            if (!listaDePessoas.contains(p)) {
                System.out.println("Atualizando área de texto com: Cliente: " + nomePessoa + "\nEmail: " + email);
                servidor.atualizaAreaTexto("Cliente: " + nomePessoa + "\nEmail: " + email + "\n");
            } else {
                System.out.println("Pessoa já existe na lista: " + p);
            }


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao receber dados: " + e.getMessage());
        }
    }
}
