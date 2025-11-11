package comunicacao;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.text.SimpleDateFormat;

// Imports do JGroups
import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.Event;
import org.jgroups.util.Util; // Embora não usemos para read/writeString, pode ser útil para outros

// Imports para State Transfer e Correção de Thread (Swing)
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.SwingUtilities;

// --- Imports para a CORREÇÃO DEFINITIVA (Java I/O) ---
import java.io.DataInputStream;
import java.io.DataOutputStream;
// ---------------------------------------------------


/**
 * Classe que gerencia toda a comunicação JGroups,
 * notificações e transferência de estado (histórico).
 */
public class Comunicador extends ReceiverAdapter {

    JChannel channel;
    List<Address> listaMembros; // Usado para comparar quem entrou/saiu
    String frase;
    Message mensagem;
    JFrame_chatJGROUPS meuFrame;
    StringBuffer membrosStringBuffer;

    // Formatador de data otimizado (reutilizado)
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM, HH:mm:ss");

    /**
     * Inicia a conexão com o canal JGroups e solicita o
     * histórico de mensagens (State Transfer).
     */
    public void iniciar(JFrame_chatJGROUPS meuFrame) throws Exception {

        System.setProperty("java.net.preferIPv4Stack", "true");//desabilita ipv6

        this.channel = new JChannel();      // Cria o canal
        this.channel.setReceiver(this);   // Define esta classe como "ouvinte"
        this.meuFrame = meuFrame;           // Guarda a referência do JFrame

        this.channel.setName(meuFrame.getjTextField_apelido().getText());
        this.channel.connect(meuFrame.getTitle());

        // --- Solicita o estado (histórico) do grupo ---
        System.out.println("Conectado. Solicitando histórico (estado)...");
        this.channel.getState(null, 10000); // Timeout de 10 seg
        // --- FIM DA MODIFICAÇÃO ---

        // Atualiza a lista de membros (preenchida pelo viewAccepted)
        if (membrosStringBuffer != null) {
             this.meuFrame.getjTextArea_listaMembros().setText(membrosStringBuffer.toString());
        }
    }

    /**
     * Envia uma mensagem para o grupo (participante = null)
     * ou para um membro específico.
     */
    public void enviar(String frase, String participante) {
        try {
            if (participante == null) {
                // Mensagem para o grupo (broadcast)
                this.mensagem = new Message(null, frase);
            } else {
                // Mensagem privada (unicast)
                Address dest = null; // Endereço de destino
                
                // (Verifica se a lista de membros não é nula)
                if (this.listaMembros == null) {
                    System.err.println("ERRO: Lista de membros ainda não foi inicializada.");
                    return;
                }
                
                for (int i = 0; i < this.listaMembros.size(); i++) {
                    if (participante.equals(listaMembros.get(i).toString())) {
                        System.out.println("Achouuuu (destino da msg privada)");
                        dest = listaMembros.get(i);
                        break;
                    }
                }

                if (dest != null) {
                    this.mensagem = new Message(dest, frase);
                } else {
                    System.err.println("ERRO: Destinatário privado não encontrado: " + participante);
                    return; 
                }
            }
            this.channel.send(this.mensagem);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(meuFrame, "Algo ocorreu de errrado ao enviar sua mensagem!!");
        }
    }

    /**
     * Fecha o canal e finaliza a conexão.
     */
    public void finalizar() {
        if (this.channel != null) {
            this.channel.close();
        }
    }

    /**
     * Callback: Chamado ao receber uma mensagem.
     * MODIFICADO: Agora é "Thread-safe", usando SwingUtilities.
     */
    @Override
    public void receive(Message msg) {
        Date dt = new Date();
        String dataFormatada = sdf.format(dt);
        
        // Monta a linha da mensagem
        final String messageLine = "[" + dataFormatada + "] " + msg.getSrc() + " disse: "
                + msg.getObject().toString() + "\n";

        // Manda a 'task' de atualizar a GUI para a Thread do Swing (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Trava o componente para ser "atômico" com getState/setState
                synchronized(meuFrame.getjTextArea_mensagensGerais()) {
                    meuFrame.getjTextArea_mensagensGerais().append(messageLine);
                }
            }
        });
    }

    /**
     * Callback: Chamado quando a lista de membros muda (alguém entra ou sai).
     * MODIFICADO:
     * 1. Anuncia quem entrou/saiu (de forma thread-safe).
     * 2. Busca o IP de cada membro para a lista.
     */
    @Override
    public void viewAccepted(View view_atual) {
        
        List<Address> newMembers = view_atual.getMembers();

        if (this.listaMembros != null) {
            // VERIFICA QUEM SAIU
            for (Address oldMember : this.listaMembros) {
                if (!newMembers.contains(oldMember)) {
                    String memberName = oldMember.toString();
                    String dataFormatada = sdf.format(new Date());
                    final String exitMessage = "[" + dataFormatada + "] SISTEMA: " + memberName + " SAIU DO CHAT\n";
                    
                    // Atualiza a GUI na thread correta
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            synchronized(meuFrame.getjTextArea_mensagensGerais()) {
                                meuFrame.getjTextArea_mensagensGerais().append(exitMessage);
                            }
                        }
                    });
                }
            }
            
            // VERIFICA QUEM ENTROU
            for (Address newMember : newMembers) {
                if (!this.listaMembros.contains(newMember)) {
                    String memberName = newMember.toString();
                    
                    // Não anuncie para mim mesmo que eu entrei
                    if (this.channel != null && !newMember.equals(this.channel.getAddress())) {
                        String dataFormatada = sdf.format(new Date());
                        final String enterMessage = "[" + dataFormatada + "] SISTEMA: " + memberName + " ENTROU NO CHAT\n";
                        
                        // Atualiza a GUI na thread correta
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                synchronized(meuFrame.getjTextArea_mensagensGerais()) {
                                    meuFrame.getjTextArea_mensagensGerais().append(enterMessage);
                                }
                            }
                        });
                    }
                }
            }
        }

        // ATUALIZA a lista antiga para a próxima vez
        this.listaMembros = newMembers; 
        
        // ATUALIZA A GUI (Lista de Membros e ComboBox)
        this.membrosStringBuffer = new StringBuffer();
        
        // Limpa os componentes (deve ser na thread do Swing)
        SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                meuFrame.getjTextArea_listaMembros().setText("");
                meuFrame.getjComboBox_listaParticipantesGrupo().removeAllItems();
                meuFrame.getjComboBox_listaParticipantesGrupo().addItem("Selecione o participante");
                
                // Repopula a GUI na mesma thread
                for (int i = 0; i < listaMembros.size(); i++) {
                    Address logicalAddr = listaMembros.get(i);
                    final String logicalName = logicalAddr.toString(); 

                    Object physAddr = null;
                    try {
                        physAddr = channel.down(new Event(Event.GET_PHYSICAL_ADDRESS, logicalAddr));
                    } catch (Exception e) {
                        System.err.println("Erro ao buscar endereço físico para " + logicalName + ": " + e.getMessage());
                    }
                    String physAddrStr = (physAddr != null) ? physAddr.toString() : "N/A";
                    
                    final String fullDisplay = logicalName + " [" + physAddrStr + "]"; 

                    membrosStringBuffer.append(fullDisplay + "\n");
                    meuFrame.getjTextArea_listaMembros().setText(membrosStringBuffer.toString());
                    meuFrame.getjComboBox_listaParticipantesGrupo().addItem(logicalName);
                }
             }
        });
    }

    @Override
    public void suspect(Address mbr) {
        JOptionPane.showMessageDialog(meuFrame, "PROCESSO SUSPEITO DE FALHA: " + mbr);
    }
    
    // --- MÉTODOS PARA STATE TRANSFER (HISTÓRICO) ---

    /**
     * Callback: Chamado em um membro EXISTENTE (coordenador)
     * quando um NOVO membro entra e pede o estado (histórico).
     * * CORRIGIDO: Usa DataOutputStream.writeUTF (Java puro)
     */
    @Override
    public void getState(OutputStream output) throws Exception {
        synchronized (this.meuFrame.getjTextArea_mensagensGerais()) {
            String history = this.meuFrame.getjTextArea_mensagensGerais().getText();
            System.out.println("getState: Fornecendo histórico para novo membro (" + history.length() + " bytes)");
            
            // --- CORREÇÃO DEFINITIVA ---
            // Não usamos mais 'Util.writeString', usamos Java puro.
            new DataOutputStream(output).writeUTF(history);
            // -------------------------
        }
    }

    /**
     * Callback: Chamado no NOVO membro
     * (que acabou de entrar) após ele receber o estado.
     * * CORRIGIDO: Usa DataInputStream.readUTF (Java puro)
     */
    @Override
    public void setState(InputStream input) throws Exception {
        
        // --- CORREÇÃO DEFINITIVA ---
        // Não usamos mais 'Util.readString', usamos Java puro.
        final String history = new DataInputStream(input).readUTF();
        // -------------------------
        
        System.out.println("setState: Histórico recebido (" + history.length() + " bytes)");

        // Atualiza a GUI na thread correta (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                synchronized(meuFrame.getjTextArea_mensagensGerais()) {
                    meuFrame.getjTextArea_mensagensGerais().setText(history);
                    meuFrame.getjTextArea_mensagensGerais().setCaretPosition(
                        meuFrame.getjTextArea_mensagensGerais().getDocument().getLength()
                    );
                }
            }
        });
    }
}