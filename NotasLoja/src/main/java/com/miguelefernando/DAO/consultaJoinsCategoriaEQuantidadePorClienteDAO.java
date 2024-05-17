package com.miguelefernando.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nando
 */
public class consultaJoinsCategoriaEQuantidadePorClienteDAO {
    private int ID_cliente;
    private String categoria;
    private int quantidade;
    private BancoDAO banco;

    /**
     * Construtor para marshalling a partir do id do id do cliente
     * @param ID_cliente 
     * @author fernando
     * @since 05/24
     * @version 1.1
     */
    public consultaJoinsCategoriaEQuantidadePorClienteDAO(int ID_cliente) {
        this.ID_cliente = ID_cliente;
        banco = new BancoDAO();
    }

    public consultaJoinsCategoriaEQuantidadePorClienteDAO(String categoria, int quantidade) {
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.banco = new BancoDAO();//acho que não precisa dessa variavel aqui
    }

    public int getID_cliente() {
        return ID_cliente;
    }

    public void setID_cliente(int ID_cliente) {
        this.ID_cliente = ID_cliente;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BancoDAO getBanco() {
        return banco;
    }

    public void setBanco(BancoDAO banco) {
        this.banco = banco;
    }
    
    public ArrayList<consultaJoinsCategoriaEQuantidadePorClienteDAO> listarJoin() {
        Connection conexao = this.banco.getConexao();
        ArrayList<consultaJoinsCategoriaEQuantidadePorClienteDAO> lista = new ArrayList<>();
        String sql = "SELECT produto.categoria, pedido_produto.quantidade FROM pessoa JOIN pedido ON pessoa.id = pedido.id_cliente JOIN pedido_produto ON pedido_produto.id_pedido = pedido.id JOIN produto ON pedido_produto.id_produto = produto.id WHERE pessoa.id = ? ORDER BY produto.categoria";
        PreparedStatement consulta;
        ResultSet resultados;

        try {
            consulta = conexao.prepareStatement(sql);
            consulta.setInt(1, this.ID_cliente);
            resultados = consulta.executeQuery();

            while (resultados.next()) {
                String categoria = resultados.getString("categoria");
                int quantidade = resultados.getInt("quantidade");

                consultaJoinsCategoriaEQuantidadePorClienteDAO categoria_quantidade = new consultaJoinsCategoriaEQuantidadePorClienteDAO(categoria, quantidade);
                lista.add(categoria_quantidade);

            }
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i).getCategoria()+ " ----- qtd: " + lista.get(i).getQuantidade());

            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NA LEITURA DE DADOS DO BD: " + ex.getMessage());
        }

        return lista;
    }
    
    
    
}

