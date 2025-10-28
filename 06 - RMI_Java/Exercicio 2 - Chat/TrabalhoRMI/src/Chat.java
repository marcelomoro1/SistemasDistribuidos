import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
/**
 *
 * @author Patrick
 */
public class Chat extends UnicastRemoteObject implements IChat{
    public LinkedList<String> listaMensagens;

    public Chat() throws RemoteException{
        listaMensagens = new LinkedList<>();
    }
    
    @Override
    public void receberMensagem(String frase) throws RemoteException{
        listaMensagens.add(frase);
        //System.out.println(frase);
    }

    @Override
    public LinkedList<String> lerMensagem()throws RemoteException{
        return listaMensagens;
    }
    
    @Override
    public String pegaDataHora() throws RemoteException {     
        Date obj = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(obj);
    }   
}
