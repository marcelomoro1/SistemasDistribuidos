import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        Scanner teclado = new Scanner(System.in);
        try {
            ITrataNomes d = (ITrataNomes) Naming.lookup("rmi://localhost/TrataNomes");    
            System.out.println("Pegando informacoes!!");

            String nome;
            System.out.print("Nome completo: ");
            nome = teclado.nextLine();

            System.out.println("Email: " + d.gerarEmail(nome));
            System.out.println("Nome "+ d.pegarNome(nome));
            System.out.println("Sobrenome " + d.pegarSobrenome(nome));

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}