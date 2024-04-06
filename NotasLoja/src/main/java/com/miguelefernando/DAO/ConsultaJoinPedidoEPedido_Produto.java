/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class ConsultaJoinPedidoEPedido_Produto {

    private int id_pedido;
    private int id_produto;
    private int quantidade;
    private int id;
    private double preco;
    private String nome;
    private char sexo;
    private BancoDAO banco;

    public ConsultaJoinPedidoEPedido_Produto(int id) {
        this.id = id;
        this.banco = new BancoDAO();
    }

    public ConsultaJoinPedidoEPedido_Produto(int id_pedido, int id_produto, int quantidade, int id, double preco, char sexo, String nome) {
        this.id_pedido = id_pedido;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.id = id;
        this.preco = preco;
        this.sexo = sexo;
        this.nome = nome;
        this.banco = new BancoDAO();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public BancoDAO getBanco() {
        return banco;
    }

    public void setBanco(BancoDAO banco) {
        this.banco = banco;
    }

    public ArrayList<ConsultaJoinPedidoEPedido_Produto> listarJoin() {
        Connection conexao = this.banco.getConexao();
        ArrayList<ConsultaJoinPedidoEPedido_Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido_produto JOIN produto ON pedido_produto.id_produto = produto.id WHERE id_pedido = ?";
        PreparedStatement consulta;
        ResultSet resultados;

        try {
            consulta = conexao.prepareStatement(sql);
            consulta.setInt(1, this.id);
            resultados = consulta.executeQuery();

            while (resultados.next()) {
                int id_pedido = resultados.getInt("id_pedido");
                int id_produto = resultados.getInt("id_produto");
                int quantidade = resultados.getInt("quantidade");
                int id = resultados.getInt("id");
                double preco = resultados.getDouble("preco");
                String nome = resultados.getString("nome");
                char sexo = (resultados.getString("sexo")).charAt(0);

                ConsultaJoinPedidoEPedido_Produto ppp = new ConsultaJoinPedidoEPedido_Produto(id_pedido, id_produto, quantidade, id, preco, sexo, nome);
                lista.add(ppp);

            }
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i).getNome() + " ----- qtd: " + lista.get(i).getQuantidade() + " ------- preço unitario: " + lista.get(i).getPreco() + " ------- preço somado: " + lista.get(i).getPreco() * lista.get(i).getQuantidade());

            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NA LEITURA DE DADOS DO BD: " + ex.getMessage());
        }

        return lista;
    }

}
