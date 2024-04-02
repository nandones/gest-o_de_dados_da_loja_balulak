package com.miguelefernando.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

 
public class BancoDAO {
    
    private String url;
    private String porta;
    private String banco;
    private String usuario;
    private String senha;
    private String nomeBanco;

    public BancoDAO() {
        this.porta = "3306";
        this.banco = "mysql";
        this.usuario = "root";
        this.senha = "rooteiro";
        this.nomeBanco = "trab_lpg_1";
    }
    
    public Connection getConexao(){
        
           Connection conexao = null;
           
           try {
               this.url = "jdbc:"+this.banco+"://localhost:"+this.porta+"/"+this.nomeBanco+"?serverTimezone=America/Sao_Paulo";
               
               
               conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
               System.out.println("conected");
           } catch(SQLException ex){
               System.out.println("problemas na conexão: "+ex.getMessage());
               
           }return conexao;
    }
    
    

    
}
