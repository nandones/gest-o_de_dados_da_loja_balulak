/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miguelefernando.notasloja.utils;

import com.miguelefernando.DAO.BancoDAO;
import com.miguelefernando.DAO.PedidoDAO;
import com.miguelefernando.DAO.Pedido_ProdutoDAO;
import com.miguelefernando.DAO.PessoaDAO;
import com.miguelefernando.DAO.ProdutoDAO;
import com.miguelefernando.notasloja.main.main;
import java.sql.Connection;
import java.sql.Date;
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

    public void ResetPesoa() {
        BancoDAO banco = new BancoDAO();
        Connection conexao = banco.getConexao();
        String sql = "DELETE FROM pessoa WHERE nome IS NOT NULL; ";
        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar todos os dados do bdd: " + ex.getMessage());
        }
        sql = "ALTER TABLE pessoa AUTO_INCREMENT = 1;";
        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao resetar o autoincrement de pessoa para 1" + ex.getMessage());
        }
    }

    public void ResetProduto() {
        BancoDAO banco = new BancoDAO();
        Connection conexao = banco.getConexao();
        String sql = "DELETE FROM produto WHERE nome IS NOT NULL; ";
        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar todos os dados do bdd: " + ex.getMessage());
        }
        sql = "ALTER TABLE produto AUTO_INCREMENT = 1;";
        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao resetar o autoincrement para 1" + ex.getMessage());
        }
    }

    public void ResetPedido() {
        BancoDAO banco = new BancoDAO();
        Connection conexao = banco.getConexao();

        String sql = "DELETE FROM pedido WHERE id IS NOT NULL; ";

        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar todos os dados do bdd: " + ex.getMessage());
        }
        sql = "ALTER TABLE pedido AUTO_INCREMENT = 1;";
        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao resetar o autoincrement do pedido para 1" + ex.getMessage());
        }
    }

    public void ResetPedido_produto() {
        BancoDAO banco = new BancoDAO();
        Connection conexao = banco.getConexao();

        String sql = "DELETE FROM pedido_produto WHERE id_pedido IS NOT NULL; ";

        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar todos os dados do bdd: " + ex.getMessage());
        }
        sql = "ALTER TABLE pessoa AUTO_INCREMENT = 1;";
        try {
            Statement statement = conexao.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao resetar o autoincrement de pedido_produto para 1" + ex.getMessage());
        }
    }

    public void InserePessoas() {
        Connection conexao = new BancoDAO().getConexao();

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

    public void InsereProdutos() {
        Connection conexao = new BancoDAO().getConexao();

        ProdutoDAO p1 = new ProdutoDAO(40.0, "regata blindada", 'u');
        ProdutoDAO p2 = new ProdutoDAO(37.0, "saia", 'f');
        ProdutoDAO p3 = new ProdutoDAO(200.0, "camiseta insider", 'm');
        ProdutoDAO p4 = new ProdutoDAO(40.0, "camiseta insider", 'f');
        ProdutoDAO p5 = new ProdutoDAO(40.0, "corta vento", 'u');

        try {
            System.out.println(p1.salvarProduto());
            System.out.println(p2.salvarProduto());
            System.out.println(p3.salvarProduto());
            System.out.println(p4.salvarProduto());
            System.out.println(p5.salvarProduto());
        } catch (SQLException ex) {
            Logger.getLogger(Mock.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void InserePedido() {
        Connection conexao = new BancoDAO().getConexao();

        Date dataAtual = new Date(System.currentTimeMillis());

        PedidoDAO p1 = new PedidoDAO(1, new Date(2021 - 1900, 20, 12), new Date(2023 - 1900, 0, 7), 'f', 117);// cada anp deve ser subtraido por 1900 para funcionar, e o mes começa em 0 (jan = 0)
        PedidoDAO p2 = new PedidoDAO(4, dataAtual, new Date(2030 - 1900, 5, 1), 'a', 40);//cada vez que rodar o mock atualizara

        try {
            System.out.println(p1.salvarPedidoDAO());
            System.out.println(p2.salvarPedidoDAO());

        } catch (SQLException ex) {
            Logger.getLogger(Mock.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void InserePedido_produto() {
        Connection conexao = new BancoDAO().getConexao();

        Pedido_ProdutoDAO p1 = new Pedido_ProdutoDAO(1, 1, 2);
        Pedido_ProdutoDAO p2 = new Pedido_ProdutoDAO(1, 2, 1);
        Pedido_ProdutoDAO p3 = new Pedido_ProdutoDAO(2, 5, 1);

        try {
            System.out.println(p1.salvarPedido_ProdutoDAO());
            System.out.println(p2.salvarPedido_ProdutoDAO());
            System.out.println(p3.salvarPedido_ProdutoDAO());

        } catch (SQLException ex) {
            Logger.getLogger(Mock.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void init() {
        ResetPedido_produto();
        ResetPedido();
        ResetProduto();
        ResetPesoa();
        InserePessoas();
        InsereProdutos();
        InserePedido();
        InserePedido_produto();
    }
}
