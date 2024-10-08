/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguelefernando.notasloja.view;

import com.miguelefernando.DAO.ConsultaJoinPedidoEPedido_Produto;
import com.miguelefernando.DAO.PedidoDAO;
import com.miguelefernando.DAO.PessoaDAO;
import com.miguelefernando.DAO.ProdutoDAO;
import com.miguelefernando.DAO.consultaJoinsCategoriaEQuantidadePorClienteDAO;
import com.miguelefernando.DAO.consultaJoinsMarcaEQuantidadePorClienteDAO;
import static com.miguelefernando.notasloja.view.Janela.idioma1;
import static com.miguelefernando.notasloja.view.Janela.pais1;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *Nesse painel é possível visualizar todos os <br>
 * pedidos do cliente selecionado anteriormente <br>
 * no menu, e abrir, o pedido, mostrando todas <br>
 * as informações do mesmo.
 * @author Miguel
 * @since 04/24
 * @version 1.0
 */
public class Painel_PerfilCliente extends javax.swing.JPanel {

    public ResourceBundle traducoes1;
    /**
    * Creates new form Painel_PerfilCliente
    * @author Miguel
    * @since 04/24
    * @version 1.0
    */
    int id;//variavel para identificar o id do cliente ao passar os dados para o grafico de pizza
    public Painel_PerfilCliente(String nome, String id, String cpf) {
        initComponents();
        setLabels(nome, id, cpf);
        setTabela(id);
        this.id = Integer.parseInt(id);
        
        
        ResourceBundle traducoes = null;
        InputStream newInputStream;
  
        String nomeArquivo = "./idiomas/MessagesBundle_"+idioma1+"_"+pais1+".properties";
        System.out.println(nomeArquivo);
        try {
            newInputStream = Files.newInputStream(Paths.get(nomeArquivo));
            traducoes = new PropertyResourceBundle(newInputStream);
            System.out.println(traducoes);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bt_voltar.setText(traducoes.getString("bt_voltar"));
        bt_relatorio.setText(traducoes.getString("bt_relatorio"));
        lingua_tabela(traducoes);
        traducoes1 = traducoes;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        lb_foto = new javax.swing.JLabel();
        lb_nome = new javax.swing.JLabel();
        lb_cpf = new javax.swing.JLabel();
        lb_id = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_tabela = new javax.swing.JTable();
        bt_relatorio = new javax.swing.JButton();
        bt_voltar = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        setBackground(new java.awt.Color(255, 204, 204));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_foto.setText("jLabel1");
        add(lb_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 90));

        lb_nome.setText("jLabel2");
        add(lb_nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 180, -1));

        lb_cpf.setText("jLabel4");
        add(lb_cpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 170, -1));

        lb_id.setText("jLabel5");
        add(lb_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 180, -1));

        jt_tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id Pedido", "Valor Total", "Emissão", "Fechamento", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_tabela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_tabelaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jt_tabela);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 430, 200));

        bt_relatorio.setText("Relatório");
        bt_relatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_relatorioActionPerformed(evt);
            }
        });
        add(bt_relatorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, 120, 40));

        bt_voltar.setText("Voltar");
        bt_voltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_voltarMouseClicked(evt);
            }
        });
        add(bt_voltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 120, 40));
    }// </editor-fold>//GEN-END:initComponents

   /**
    * Volta para o painel Cliente
    * @param evt java.awt.event.MouseEvent 
    * @author Miguel
    * @since 04/24
    * @version 1.0
    */
    private void bt_voltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_voltarMouseClicked
        Janela.p3 = new Painel_clientes();
        JFrame maininterface = (JFrame) SwingUtilities.getWindowAncestor(this);
        maininterface.getContentPane().remove(this);
        maininterface.add(Janela.p3, BorderLayout.CENTER);
        maininterface.pack();
    }//GEN-LAST:event_bt_voltarMouseClicked
    /**
    * Entrada no Pedido, onde será mostardo as <br>
    * informações do pedido assim que clicado <br>
    * no enter, será aberto o pedido selecionado
    * @param evt java.awt.event.KeyEvent
    * @author Miguel
    * @since 04/24
    * @version 1.0
   */
    private void jt_tabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_tabelaKeyReleased
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int id = Integer.parseInt((String) jt_tabela.getValueAt(jt_tabela.getSelectedRow(), 0));
            ConsultaJoinPedidoEPedido_Produto c = new ConsultaJoinPedidoEPedido_Produto(id);
            ArrayList<ConsultaJoinPedidoEPedido_Produto> lista = c.listarJoin();
            String notaFiscal = "";
            for (int i = 0; i < lista.size(); i++) {
                notaFiscal += (lista.get(i).getNome() + " ----- qtd: " + lista.get(i).getQuantidade() + " ------- "+ traducoes1.getString("preco_unitario")+": " + lista.get(i).getPreco() + " ------- "+traducoes1.getString("preco_total")+": " + lista.get(i).getPreco() * lista.get(i).getQuantidade()+"\n");
                

            }
        JOptionPane.showMessageDialog(null, notaFiscal, traducoes1.getString("id_pedido")+": "+id, JOptionPane.PLAIN_MESSAGE);
                
            
        }
    }//GEN-LAST:event_jt_tabelaKeyReleased
    /**
     * @param evt 
     */
    private void bt_relatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_relatorioActionPerformed

        consultaJoinsMarcaEQuantidadePorClienteDAO a = new consultaJoinsMarcaEQuantidadePorClienteDAO(id);
        ArrayList dataMarcas = a.listarJoin();
        consultaJoinsCategoriaEQuantidadePorClienteDAO b = new consultaJoinsCategoriaEQuantidadePorClienteDAO(id);
        ArrayList dataCategorias = b.listarJoin();
        GraficoPizzaCategoriasCliente c = new GraficoPizzaCategoriasCliente(dataCategorias, dataMarcas/*, traducoes1*/ );

        
    }//GEN-LAST:event_bt_relatorioActionPerformed
    /**
    * Método para inserir os dados do cliente em suas respectivas labels
    * @param nome String 
    * @param id String
    * @param cpf String
    * @author Miguel
    * @since 04/24
    * @version 1.0
    */
    public void setLabels(String nome, String id, String cpf){
        lb_nome.setText(nome);
        lb_id.setText(id);
        lb_cpf.setText(cpf);
    }
    /**
    * Método para inserir os dados do pedido no jtable<br>
    * usando o id para pegar somente os dados do cliente<br>
    * selecionado
    * @param id String
    * @author Miguel
    * @since 04/24
    * @version 1.0
    */
    public void setTabela(String id){
        
        PedidoDAO pedido = new PedidoDAO();
        ArrayList<PedidoDAO> listaPedido;
        listaPedido = (ArrayList<PedidoDAO>) pedido.listarPedidosDAO();
        DefaultTableModel modelo = (DefaultTableModel) this.jt_tabela.getModel();
        
        for(int i = 0; i < listaPedido.size(); i++ ){
            String idCliente = String.valueOf(listaPedido.get(i).getId_cliente());
            if(idCliente.equals(id)){
            String idString = String.valueOf(listaPedido.get(i).getId());
            String valorTotal = String.valueOf(listaPedido.get(i).getTotal());
            String emissao = String.valueOf(listaPedido.get(i).getEmissao());
            String fechamento = String.valueOf(listaPedido.get(i).getFechamento());
            String status = String.valueOf(listaPedido.get(i).getStatus());
            String [] linha = {
                idString,
                valorTotal,
                emissao,
                fechamento,
                status
                
            };
            modelo.addRow(linha);
        }
    }
        jt_tabela.changeSelection(0, 0, false, false);
}
    
    public void lingua_tabela(ResourceBundle traducoes){
        
        String [] novonome = {traducoes.getString("id_cliente"), traducoes.getString("valor_total"), traducoes.getString("emissao"), traducoes.getString("fechamento"), traducoes.getString("status")}; 
        DefaultTableModel modelo = (DefaultTableModel) this.jt_tabela.getModel();
        modelo.setColumnIdentifiers(novonome);
        jt_tabela.setModel(modelo);
}
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_relatorio;
    private javax.swing.JButton bt_voltar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_tabela;
    private javax.swing.JLabel lb_cpf;
    private javax.swing.JLabel lb_foto;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_nome;
    // End of variables declaration//GEN-END:variables
}
