package com.miguelefernando.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe utilizada por entidades DAO para se conectarem ao MySQL; deve ser
 * alterada <br>
 * para os dados MySQL do PC que estiver rodando a aplicação.
 * @author fernando
 * @since 03/24
 * @version 1.0
 */
public class BancoDAO {

    private String url;
    private String porta;
    private String banco;
    private String usuario;
    private String senha;
    private String nomeBanco;

    /**
     * informe aqui, não por parâmetro, mas editando o código, o endereço de
     * <br>
     * seu banco de dados MySQL
     * @author fernando
     * @since 03/24
     * @version 1.0
     */
    public BancoDAO() {
        this.porta = "3306";
        this.banco = "mysql";
        this.usuario = "root";
        this.senha = "";
        this.nomeBanco = "trab_lpg_1";
    }

    /**
     * método que se conecta ao banco de dados MySQL embasado em como o código
     * fora preenchido <br>
     * a respeito de seu construtor
     *
     * @return Connection conexao
     * @since 03/24
     * @version 1.0
     */
    public Connection getConexao() {
        Connection conexao = null;

        try {
            this.url = "jdbc:" + this.banco + "://localhost:" + this.porta + "/" + this.nomeBanco + "?serverTimezone=America/Sao_Paulo";

            conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
            System.out.println("conected");
        } catch (SQLException ex) {
            System.out.println("problemas na conexão: " + ex.getMessage());

        }
        return conexao;
    }

}
