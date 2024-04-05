/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miguelefernando.notasloja.utils;

import com.miguelefernando.DAO.BancoDAO;
import com.miguelefernando.DAO.PessoaDAO;
import com.miguelefernando.DAO.ProdutoDAO;
import com.miguelefernando.notasloja.main.main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class Mock {

    public static void main(String[] args) {

        Connection conexao = new BancoDAO().getConexao();

        
        ProdutoDAO pr1 = new ProdutoDAO(20.0, "camisa you ken do ramon", 'r');
        ProdutoDAO pr2 = new ProdutoDAO(20.0, "moletom verde do luisao", 'p');
        try {
            System.out.println(pr1.salvarProduto());
            System.out.println(pr2.salvarProduto());
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        PessoaDAO dummy = new PessoaDAO();
        ArrayList<PessoaDAO> pessoas = dummy.listarPessoasDAO();
        for (int i = 0; i < pessoas.size(); i++) {
            System.out.println("nome : " + pessoas.get(i).getNome() + " -- CPF: " + pessoas.get(i).getCpf());

        }

    }

    public void DeleteAllPessoa() {
        BancoDAO banco = new BancoDAO();
        Connection conexao = banco.getConexao();

        String sql = "DELETE FROM pessoa WHERE nome IS NOT NULL; ";

        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar todos os dados do bdd: " + ex.getMessage());
        }
    }
    
    public void DeleteAllProduto() {
        BancoDAO banco = new BancoDAO();
        Connection conexao = banco.getConexao();

        String sql = "DELETE FROM pessoa WHERE nome IS NOT NULL; ";

        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar todos os dados do bdd: " + ex.getMessage());
        }
    }
    
    public void DeleteAllPedido() {
        BancoDAO banco = new BancoDAO();
        Connection conexao = banco.getConexao();

        String sql = "DELETE FROM pedido WHERE id IS NOT NULL; ";

        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar todos os dados do bdd: " + ex.getMessage());
        }
    }
    
    public void DeleteAllPedido_produto() {
        BancoDAO banco = new BancoDAO();
        Connection conexao = banco.getConexao();

        String sql = "DELETE FROM pedido_produto WHERE id_pedido IS NOT NULL; ";

        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar todos os dados do bdd: " + ex.getMessage());
        }
    }
    
    public void InserePessoas(){
        Connection conexao = new BancoDAO().getConexao();

        String sql = "INSERT INTO pessoa(nome, cidade, uf, cpf, admin) VALUES (?, ?, ?, ?, 0);";
        try {
            PreparedStatement consulta = conexao.prepareStatement(sql);
            consulta.execute();

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir dados de Pessoa: " + ex.getMessage());
        }
        PessoaDAO p1 = new PessoaDAO("willen", "lages", "sc", "000.000.000-00");
        PessoaDAO p2 = new PessoaDAO("MONARK", "eua", "ac", "000.000.000-01");
        PessoaDAO p3 = new PessoaDAO("NANDO MOURA", "sp", "sp", "000.000.000-02");
        PessoaDAO p4 = new PessoaDAO("NATHAN FISHERMAN", "balneario", "sc", "000.000.000-03");
        PessoaDAO p5 = new PessoaDAO("ADAM SANDVER", "eua", "pr", "000.000.000-04");
        System.out.println(p1.SalvarPessoa());
        System.out.println(p2.SalvarPessoa());
        System.out.println(p3.SalvarPessoa());
        System.out.println(p4.SalvarPessoa());
        System.out.println(p5.SalvarPessoa());
        
    }
}
