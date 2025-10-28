import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TrataNomes extends UnicastRemoteObject implements ITrataNomes {
    
    public TrataNomes() throws RemoteException{
        
    }
    
    @Override
    public String gerarEmail(String nome) throws RemoteException {     
        String vetorNome[] = nome.split(" ");
        String email = vetorNome[0] + "." + vetorNome[vetorNome.length - 1] + "@ufn.edu.br";
        email = email.toLowerCase();
        return email;
    }   

    @Override
    public String pegarSobrenome(String nome) throws RemoteException {
        String vetorNome[] = nome.split(" ");
        String sobrenome = vetorNome[vetorNome.length - 1];
        return sobrenome;
    }   

    @Override
    public String pegarNome(String nome) throws RemoteException {   
        String vetorNome[] = nome.split(" ");
        String primeironome = vetorNome[0];
        return primeironome;        
    }   


}