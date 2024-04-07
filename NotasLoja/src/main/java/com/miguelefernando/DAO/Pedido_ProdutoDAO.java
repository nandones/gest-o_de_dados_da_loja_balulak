package com.miguelefernando.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe DAO que representa a table intermediaria que salva as relações n:n
 * entre pedido e produto
 * @see BancoDAO
 * @author fernando
 * @since 03/24
 * @version 1.0
 */
public class Pedido_ProdutoDAO {

    private int id_pedido;
    private int id_produto;
    private int quantidade;
    private BancoDAO banco;

    /**
     * Construtor para marshalling e unmarshalling da table, visto que não possui nenhum campo <br>
     * com autoincrease
     * @param id_pedido
     * @param id_produto
     * @param quantidade 
     * @author fernando
     * @since 03/24
     * @version 1.0
     */
    public Pedido_ProdutoDAO(int id_pedido, int id_produto, int quantidade) {
        this.id_pedido = id_pedido;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        banco = new BancoDAO();
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    /**
     * insere um novo registro na table pedido_produto
     * @return boolean confirmando a inserção
     * @throws SQLException 
     * @author fernando
     * @since 03/24
     * @version 1.0
     */
    public boolean salvarPedido_ProdutoDAO() throws SQLException {
        Connection conexao = this.banco.getConexao();
        boolean resultado;

        String sql = "INSERT INTO pedido_produto (id_pedido, id_produto, quantidade) VALUES (?, ?, ?);";
        PreparedStatement consulta;

        try {

            consulta = conexao.prepareStatement(sql);
            consulta.setInt(1, this.id_pedido);
            consulta.setInt(2, this.id_produto);
            consulta.setInt(3, this.quantidade);

            consulta.execute();
            resultado = true;

        } catch (SQLException ex) {
            resultado = false;
            System.out.println("Erro ao inserir dados de Pedido: " + ex.getMessage());
        }
        return resultado;
    }

}
