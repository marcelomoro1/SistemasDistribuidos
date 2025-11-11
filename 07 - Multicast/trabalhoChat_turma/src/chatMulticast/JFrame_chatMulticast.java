/**
 * pacote que coordena o frame do chat no que se  refere a interface gráfica e comunicação com o usuário
 */
package chatMulticast;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import util.ComunicadorUDP;

/**
 *
 * @author Turma Sistemas Distribuídos 2019-2
 */
public class JFrame_chatMulticast extends javax.swing.JFrame {

    public JFrame_chatMulticast() {
        initComponents();
        
        //usa nosso listModel dinamico
        jList1.setModel(listModel);
        
        //seleção única para mensagem privada
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Chat = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Mensagens = new javax.swing.JTextArea();
        jTextField_textoDeEnvio = new javax.swing.JTextField();
        jButton_Enviar = new javax.swing.JButton();
        jLabel_Porta = new javax.swing.JLabel();
        jTextField_Porta = new javax.swing.JTextField();
        jLabel_ServidorIP = new javax.swing.JLabel();
        jButton_Sair = new javax.swing.JButton();
        jTextField_GrupoIP = new javax.swing.JTextField();
        jLabel_Nick = new javax.swing.JLabel();
        jTextField_Nick = new javax.swing.JTextField();
        jButton_Conectar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea_Mensagens.setEditable(false);
        jTextArea_Mensagens.setColumns(20);
        jTextArea_Mensagens.setRows(5);
        jTextArea_Mensagens.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea_Mensagens);

        jTextField_textoDeEnvio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_textoDeEnvioKeyPressed(evt);
            }
        });

        jButton_Enviar.setText("Enviar");
        jButton_Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EnviarActionPerformed(evt);
            }
        });

        jLabel_Porta.setText("Porta");

        jTextField_Porta.setText("3456");

        jLabel_ServidorIP.setText("Grupo IP:");

        jButton_Sair.setText("Sair");
        jButton_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SairActionPerformed(evt);
            }
        });

        jTextField_GrupoIP.setText("239.1.2.3");

        jLabel_Nick.setText("Apelido");

        jButton_Conectar.setText("Conectar");
        jButton_Conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ConectarActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        jLabel1.setText("Mensagem");

        jLabel2.setText("Membros");

        javax.swing.GroupLayout jPanel_ChatLayout = new javax.swing.GroupLayout(jPanel_Chat);
        jPanel_Chat.setLayout(jPanel_ChatLayout);
        jPanel_ChatLayout.setHorizontalGroup(
            jPanel_ChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ChatLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel_ServidorIP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField_GrupoIP, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Porta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField_Porta, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_Nick)
                .addGap(18, 18, 18)
                .addComponent(jTextField_Nick, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                .addComponent(jButton_Conectar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Sair)
                .addGap(2, 2, 2))
            .addGroup(jPanel_ChatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_textoDeEnvio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Enviar))
            .addGroup(jPanel_ChatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ChatLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_ChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ChatLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(103, 103, 103)))
                .addContainerGap())
        );
        jPanel_ChatLayout.setVerticalGroup(
            jPanel_ChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ChatLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_textoDeEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Enviar)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ServidorIP)
                    .addComponent(jTextField_GrupoIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Porta)
                    .addComponent(jTextField_Porta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Nick)
                    .addComponent(jTextField_Nick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Conectar)
                    .addComponent(jButton_Sair))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Chat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Chat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * classe interna que garante que o processo de ler/ouvir mensagens seja executado concomitantemente
     */
/**

     */
        class ThreadReceptora extends Thread {

            @Override
            public void run() {
                JFrame meuFrame = JFrame_chatMulticast.this;
                String meuNick = jTextField_Nick.getText();

                // Loop infinito de recebimento
                while (true) {
                    try {
                        DatagramPacket pacote = ComunicadorUDP.recebeMensagem(socket);
                        InetAddress ipRemetente = pacote.getAddress();
                        String msgRecebida = new String(pacote.getData(), 0, pacote.getLength());

                        // --- Um usuário ENTROU ---
                        if (msgRecebida.startsWith("JOIN:")) {
                            String nick = msgRecebida.substring(5); // "JOIN:Maria" -> "Maria"

                            if (!nick.equals(meuNick)) {
                                mapaUsuarios.put(nick, ipRemetente);

                                // Responde em MULTICAST
                                String msgResposta = "PRESENT:" + meuNick;
                                int portaCorreta = Integer.parseInt(jTextField_Porta.getText());

                                DatagramPacket pacoteResposta = ComunicadorUDP.montaMensagem(
                                        msgResposta,
                                        jTextField_GrupoIP.getText(), // Envia para o GRUPO
                                        portaCorreta
                                );

                                socket.send(pacoteResposta);

                                // Adiciona na JList (GUI) de forma segura
                                javax.swing.SwingUtilities.invokeLater(() -> {
                                    if (!listModel.contains(nick)) {
                                        listModel.addElement(nick);
                                        jTextArea_Mensagens.append("--- " + nick + " (" + ipRemetente.getHostAddress() + ") entrou na sala ---\n");
                                    }
                                });
                            }

                        // --- Um usuário ANTIGO respondeu ao JOIN ---
                        } else if (msgRecebida.startsWith("PRESENT:")) {
                            String nick = msgRecebida.substring(8); // "PRESENT:Joao" -> "Joao"

                            // Ignora o seu próprio "PRESENT" (que será recebido via loopback)
                            if (!nick.equals(meuNick)) {
                                mapaUsuarios.put(nick, ipRemetente);

                                javax.swing.SwingUtilities.invokeLater(() -> {
                                    if (!listModel.contains(nick)) {
                                        listModel.addElement(nick);
                                    }
                                });
                            } // Fim do if !nick.equals(meuNick)

                        // --- Um usuário SAIU ---
                        } else if (msgRecebida.startsWith("LEAVE:")) {
                            String nick = msgRecebida.substring(6); // "LEAVE:Maria" -> "Maria"
                            mapaUsuarios.remove(nick);

                            javax.swing.SwingUtilities.invokeLater(() -> {
                                listModel.removeElement(nick);
                                jTextArea_Mensagens.append("--- " + nick + " saiu da sala ---\n");
                            });

                        } else {

                            if (msgRecebida.startsWith("PRIVADO")) {
                                try {

                                    String remetente = msgRecebida.split("[\\s(]")[2]; 

                                    if (remetente.equals(meuNick)) {
                                        // É uma msg que eu enviei (para outro ou para mim).
                                        // O método enviarMsg() já a exibiu. Ignorar.
                                        continue; 
                                    }
                                } catch (Exception e) {

                                    System.err.println("Erro ao parsear msg privada: " + e.getMessage());
                                }
                            }


                            javax.swing.SwingUtilities.invokeLater(() -> {
                                jTextArea_Mensagens.append(msgRecebida + "\n");
                            });
                        }
                    } catch (IOException e) {

                        if (socket != null && !socket.isClosed()) {
                            JOptionPane.showMessageDialog(meuFrame, e.getMessage());
                        }
                        break; // Sai do loop se o socket fechar
                    }
                }
            }
        }

    /**
     * método privado que avisa que um computador saiu do grupo e encerra a conexão
     * @throws IOException
     * @throws NumberFormatException
     * @throws NullPointerException 
     */
    private void sairDoSistema() throws IOException, NumberFormatException, NullPointerException {
        try {
           String msg = "LEAVE:" + jTextField_Nick.getText();
            DatagramPacket pacote = ComunicadorUDP.montaMensagem(msg, jTextField_GrupoIP.getText(), Integer.parseInt(jTextField_Porta.getText()));
            socket.send(pacote);

        } catch (IOException | NumberFormatException | NullPointerException e) {
            if (e.getClass().toString().equals("class java.lang.NullPointerException")) {
                JOptionPane.showMessageDialog(this, "Você está saindo sem ter se conectado");
            } else {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } finally {
           // Fecha o socket antes de sair
            if (socket != null && !socket.isClosed()) {
                socket.leaveGroup(grupo); // Sai do grupo
                socket.close(); // Fecha o socket
            }
            System.exit(0);
        }
    }

    /**
     * método privado que realiza a conexão do computador em um grupo multicast,
     * tendo como referência endereço virtual do grupo e a porta do socket. O processo de escuta/leitura
     * é circundado por thread de leitura
     * @param evt contém o evento recebido pelo tratador
     */
    private void jButton_ConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ConectarActionPerformed
        jTextField_GrupoIP.getText();
        jTextField_Porta.getText();
        try {
            if (jTextField_Nick.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Preencha seu nick");
            } else {
                //DEFINO O IP DO GRUPO
                grupo = InetAddress.getByName(jTextField_GrupoIP.getText());

                //CRIO O SOCKET MULTICAST COM A PORTA ESPECIFICADA
                socket = new MulticastSocket(Integer.parseInt(jTextField_Porta.getText()));

                //ENTRA NO GRUPO MULTICAST PARA RECEBER AS MENSAGENS
                socket.joinGroup(grupo);

                //CRIO A THREAD PARA RECEBER AS MENSAGENS
                ThreadReceptora tR = new ThreadReceptora();
                tR.start();

                JOptionPane.showMessageDialog(this, "Conectado com sucesso!");

                // Adiciona o PRÓPRIO nick à lista
                String meuNick = jTextField_Nick.getText();
                listModel.addElement(meuNick);
                
      
                jButton_Conectar.setEnabled(false);
                jTextField_Nick.setEnabled(false);
                jTextField_GrupoIP.setEnabled(false);
                jTextField_Porta.setEnabled(false);

                String msg = "JOIN:" + jTextField_Nick.getText();
                DatagramPacket pacote = ComunicadorUDP.montaMensagem(msg, jTextField_GrupoIP.getText(), Integer.parseInt(jTextField_Porta.getText()));
                socket.send(pacote);
                jTextField_textoDeEnvio.requestFocus();
            }
        } catch (HeadlessException | IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton_ConectarActionPerformed
   
    /**
     * método privado que captura a mensagem escrita na caixa de texto mais o apelido da caixa de texto nick
     * monta a mensagem e a envia ao grupo
     * @throws IOException
     * @throws NumberFormatException
     * @throws NullPointerException 
     */
    private void enviarMsg() throws IOException, NumberFormatException, NullPointerException {
            try {
                String msgTexto = jTextField_textoDeEnvio.getText();
                String meuNick = jTextField_Nick.getText();

                if (msgTexto.isEmpty()) {
                    return;
                }

                String usuarioSelecionado = jList1.getSelectedValue();
                String ipDestino;
                String msgCompleta;
                int porta = Integer.parseInt(jTextField_Porta.getText());

                if (usuarioSelecionado == null) {
                    // (Multicast)
                    ipDestino = jTextField_GrupoIP.getText();
                    msgCompleta = "GERAL " + meuNick + ": " + msgTexto;

                    DatagramPacket pacote = ComunicadorUDP.montaMensagem(msgCompleta, ipDestino, porta);
                    socket.send(pacote);

                } else {
                    // (Unicast)

                    if (usuarioSelecionado.equals(meuNick)) {
                        jTextArea_Mensagens.append("--- Você não pode enviar uma mensagem privada para si mesmo ---\n");
                        jTextField_textoDeEnvio.setText("");
                        return; // Para a execução
                    }

                    InetAddress ipUsuario = mapaUsuarios.get(usuarioSelecionado);
                    if (ipUsuario == null) {
                        jTextArea_Mensagens.append("--- ERRO: Não foi possível encontrar o IP de " + usuarioSelecionado + " ---\n");
                        return;
                    }

                    ipDestino = ipUsuario.getHostAddress();
                    msgCompleta = "PARTICULAR (" + meuNick + " -> " + usuarioSelecionado + "): " + msgTexto;

                    DatagramPacket pacote = ComunicadorUDP.montaMensagem(msgCompleta, ipDestino, porta);
                    socket.send(pacote);

                    jTextArea_Mensagens.append(msgCompleta + "\n");
                }



                jTextField_textoDeEnvio.setText("");

            } catch (IOException | NumberFormatException | NullPointerException e) {
                if (e.getClass().toString().equals("class java.lang.NullPointerException")) {
                    JOptionPane.showMessageDialog(this, "Você precisa conectar antes de enviar msgs");
                } else {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        }

    /**
     * método privado que trata o envio de mensagens escritas na caixa de texto de mensagens 
     * @param evt contém o evento recebido pelo tratador
     */
    private void jButton_EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EnviarActionPerformed
        try {
            this.enviarMsg();
            jTextField_textoDeEnvio.requestFocus();
        } catch (IOException | NumberFormatException | NullPointerException e) {

        }
    }//GEN-LAST:event_jButton_EnviarActionPerformed

    /**
     * método privado que trata o evento do botão sair, ou seja, finaliza o sistema
     * @param evt contém o evento recebido pelo tratador
     */
    private void jButton_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SairActionPerformed
        try {
            this.sairDoSistema();
        } catch (IOException | NumberFormatException | NullPointerException e) {

        }
    }//GEN-LAST:event_jButton_SairActionPerformed

    /**
     * método privado que trata o pressionamento das teclas Enter ou Esc quando o foco estiver na caixa de envio de texto
     * @param evt contém o evento recebido pelo tratador
     */
    private void jTextField_textoDeEnvioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_textoDeEnvioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                this.enviarMsg();
            } catch (IOException | NumberFormatException | NullPointerException e) {

            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            try {
                this.sairDoSistema();
            } catch (IOException | NumberFormatException | NullPointerException e) {

            }
        }
    }//GEN-LAST:event_jTextField_textoDeEnvioKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame_chatMulticast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_chatMulticast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_chatMulticast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_chatMulticast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_chatMulticast().setVisible(true);
            }
        });
    }

    InetAddress grupo;
    MulticastSocket socket;
    //LinkedList<String> lista = new LinkedList<>();
    //Lista de contatos que liga o Nick ao IP
    HashMap<String, InetAddress> mapaUsuarios = new HashMap<>();
    
    //controlador da lista para adicionar/remover nicks
    DefaultListModel<String> listModel = new DefaultListModel<>();
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Conectar;
    private javax.swing.JButton jButton_Enviar;
    private javax.swing.JButton jButton_Sair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_Nick;
    private javax.swing.JLabel jLabel_Porta;
    private javax.swing.JLabel jLabel_ServidorIP;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel_Chat;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea_Mensagens;
    private javax.swing.JTextField jTextField_GrupoIP;
    private javax.swing.JTextField jTextField_Nick;
    private javax.swing.JTextField jTextField_Porta;
    private javax.swing.JTextField jTextField_textoDeEnvio;
    // End of variables declaration//GEN-END:variables
}
