package com.miguelefernando.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *zxdklvv
 * @author Fernando
 */
public class ProdutoDAO {
    
    private int id;
    private double preco;
    private BancoDAO banco;

    public ProdutoDAO(int id, double preco, BancoDAO banco) {
        this.id = id;
        this.preco = preco;
        this.banco = banco;
    }
    
        public ProdutoDAO(double preco) {
        //ID AUTOINCREMENT
        this.preco = preco;
        this.banco = new BancoDAO();
    }
    
        public boolean salvarProdutoSemId() throws SQLException {
        Connection conexao = this.banco.getConexao();
        boolean resultado;

        String sql
                = "INSERT INTO produto(preco) VALUES (?);";
        PreparedStatement consulta;

        try {

            consulta = conexao.prepareStatement(sql);
            consulta.setDouble(1, this.preco);

            consulta.execute();
            resultado = true;

        } catch (SQLException ex) {
            resultado = false;
            System.out.println("Erro ao inserir dados de Pessoa: " + ex.getMessage());
        }
        return resultado;
    }
    
}

