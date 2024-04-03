package com.miguelefernando.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * zxdklvv
 *
 * @author Fernando
 */
public class ProdutoDAO {

    private int id;
    private double preco;
    private String nome;
    private char sexo;
    private BancoDAO banco;
    
    /**
     * construtor com id somente para dados previamente cadastrados 
     * @param id
     * @param preco
     * @param nome
     * @param sexo 
     */
    public ProdutoDAO(int id, double preco, String nome, char sexo) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.sexo = sexo;
        this.banco = new BancoDAO();
    }
    /**
     * construtor com id somente para dados previamente cadastrados
     * @param id
     * @param preco
     * @param nome 
     */
    public ProdutoDAO(int id, double preco, String nome) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.banco = new BancoDAO();
    }
    /**
     * construtor com sexo da roupa
     * @param preco
     * @param nome
     * @param sexo 
     */
    public ProdutoDAO(double preco, String nome, char sexo) {
        this.preco = preco;
        this.nome = nome;
        this.sexo = sexo;
        this.banco = new BancoDAO();
    }
    /**
     * construtor sem sexo da roupa
     * @param preco
     * @param nome 
     */
    public ProdutoDAO(double preco, String nome) {
        this.preco = preco;
        this.nome = nome;
        this.banco = new BancoDAO();
    }

    
    /*
    this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.sexo = sexo;
    */
    @Deprecated
    public boolean salvarProdutoComIDComSexo() throws SQLException {
        Connection conexao = this.banco.getConexao();
        boolean resultado;

        String sql
                = "INSERT INTO produto(id, preco, nome, sexo) VALUES (?);";
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
    
    public boolean salvarProdutoComIDSemSexo() throws SQLException {
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
    
    public boolean salvarProdutoSemIDComSexo() throws SQLException {
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
    
    public boolean salvarProdutSemIDSemSexo() throws SQLException {
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
