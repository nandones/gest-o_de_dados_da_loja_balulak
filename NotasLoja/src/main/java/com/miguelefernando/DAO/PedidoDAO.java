package com.miguelefernando.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class PedidoDAO {
    
    private int id;
    private int id_cliente;
    private Date emissao;   
    private Date fechamento;
    private char status;
    private double total;
    private BancoDAO banco;

    /**
     * construtor com id para dados legados <br>
     * e desserializações
     * @param id
     * @param id_cliente
     * @param emissao
     * @param fechamento
     * @param status
     * @param total
     */
    public PedidoDAO(int id, int id_cliente, Date emissao, Date fechamento, char status, double total) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.emissao = emissao;
        this.fechamento = fechamento;
        this.status = status;
        this.total = total;
        this.banco = new BancoDAO();
    }

    /**
     * construtor sem id, condizente com o banco normalizado <br>
     * e suas inserções
     * @param id_cliente
     * @param emissao
     * @param fechamento
     * @param status
     * @param total 
     */
    public PedidoDAO(int id_cliente, Date emissao, Date fechamento, char status, double total) {
        this.id_cliente = id_cliente;
        this.emissao = emissao;
        this.fechamento = fechamento;
        this.status = status;
        this.total = total;
        this.banco = new BancoDAO();
    }

    public PedidoDAO() {
        this.banco = new BancoDAO();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public Date getFechamento() {
        return fechamento;
    }

    public void setFechamento(Date fechamento) {
        this.fechamento = fechamento;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public BancoDAO getBanco() {
        return banco;
    }

    public void setBanco(BancoDAO banco) {
        this.banco = banco;
    }
    
    
        public boolean salvarPedidoDAO() throws SQLException {
        Connection conexao = this.banco.getConexao();
        boolean resultado;

        String sql = "INSERT INTO pedido(id_cliente, emissao, fechamento, status, total) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement consulta;

        try {

            consulta = conexao.prepareStatement(sql);
            consulta.setInt(1, this.id_cliente);
            consulta.setDate(2, this.emissao);
            consulta.setDate(3, this.fechamento);
            consulta.setString(4, String.valueOf(this.status));
            consulta.setDouble(5, this.total);

            consulta.execute();
            resultado = true;

        } catch (SQLException ex) {
            resultado = false;
            System.out.println("Erro ao inserir dados de Pedido: " + ex.getMessage());
        }
        return resultado;
    }
        
        public ArrayList<PedidoDAO> listarPedidosDAO() {

        Connection conexao = this.banco.getConexao();
        ArrayList<PedidoDAO> lista = new ArrayList<>();

        String sql = "SELECT * FROM pedido  ";
        ResultSet resultados;

        try {
            resultados = conexao.createStatement().executeQuery(sql);
            PedidoDAO objeto;
            while (resultados.next()) {
                int id = resultados.getInt("id");
                int id_cliente = resultados.getInt("id_cliente");
                Date emissao = resultados.getDate("emissao");
                Date fechamento = resultados.getDate("fechamento");
                char status = resultados.getString("status").charAt(0);
                double total = resultados.getDouble("total");
                
                objeto = new PedidoDAO(id, id_cliente, emissao, fechamento, status, total);
                lista.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NA LEITURA DE DADOS DO BD: " + ex.getMessage());
        }
        return lista;
    }
        
    
    
    
}


