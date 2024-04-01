/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nandones.notasloja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Miguel
 */
public class Cliente {
    
    private int id_cliente;
    private String cidade;
    private String uf;
    private String cpf;
    private Banco banco;

    public Cliente() {
        this.banco = new Banco();
    }

    public Cliente(int id_cliente, String cidade, String uf, String cpf) {
        this.id_cliente = id_cliente;
        this.cidade = cidade;
        this.uf = uf;
        this.cpf = cpf;
        this.banco = new Banco();
    }
    
    
    public boolean salvarCliente() throws SQLException{
        Connection conexao = this.banco.getConexao();
        boolean resultado = false;
        
        String sql = "INSERT INTO cliente(id_cliente, cidade, uf, cpf) VALUES (?, ?, ?, ?)";
        PreparedStatement consulta;
        
        try {
            
            
            consulta = conexao.prepareStatement(sql);
            consulta.setInt(1, this.id_cliente);
            consulta.setString(2, this.cidade);
            consulta.setString(3, this.uf);
            consulta.setString(4, this.cpf);
            resultado = true;
            
        }catch (SQLException ex){
            resultado = false;
            System.out.println("Erro ao inserir dados de Pessoa: "+ex.getMessage());
        }
        return resultado;
    }
    
    
    
    
    
    
}
