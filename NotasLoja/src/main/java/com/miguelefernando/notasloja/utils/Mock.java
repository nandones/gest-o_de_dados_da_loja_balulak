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

        BancoDAO banco = new BancoDAO();
        Connection conexao = banco.getConexao();

        String sql = "SET SQL_SAFE_UPDATES = 0; "
                + "DELETE FROM pessoa WHERE nome IS NOT NULL; "
                + "DELETE FROM produto WHERE nome IS NOT NULL; "
                + "DELETE FROM produtopedido WHERE id_pedido IS NOT NULL; "
                + "DELETE FROM pedido_produto WHERE id IS NOT NULL;";

        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar todos os dados do bdd: " + ex.getMessage());
        }

        sql = "INSERT INTO pessoa(nome, cidade, uf, cpf, admin) VALUES (?, ?, ?, ?, 0);";
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
        try {

            System.out.println(p1.salvarPessoaSemId());
            System.out.println(p2.salvarPessoaSemId());
            System.out.println(p3.salvarPessoaSemId());
            System.out.println(p4.salvarPessoaSemId());
            System.out.println(p5.salvarPessoaSemId());

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

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
}
