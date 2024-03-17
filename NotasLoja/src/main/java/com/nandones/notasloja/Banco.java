/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nandones.notasloja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Miguel
 */
public class Banco {
    
    private String url;
    private String porta;
    private String banco;
    private String usuario;
    private String senha;
    private String nomeBanco;

    public Banco() {
        this.porta = "3306";
        this.banco = "Banco_Notas_Loja";
        this.usuario = "root";
        this.senha = "";
        this.nomeBanco = "mysql";
    }
    
    public Connection getConexao(){
        
           Connection conexao = null;
           
           try {
               this.url = "jdbc:"+this.banco+"://localhost"+this.porta+"/"+this.nomeBanco;
               
               conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
           } catch(SQLException ex){
               System.out.println("problemas na conexão: "+ex.getMessage());
               
           }return conexao;
    }

    
}
