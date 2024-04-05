package com.miguelefernando.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * commom user = 0 e admin = 1<br><br>para puxar os dados do excel, deverá ser
 * feito um construtor com id<br>
 * no próprio MySQL podemos utilizar de:<br>
 * <br>
 * -- Desativa temporariamente a AUTO_INCREMENT para a coluna id<br>
 * SET @@auto_increment_increment=0;<br>
 * -- Insere o registro com o ID específico 100<br>
 * INSERT INTO tabela_exemplo (id, coluna1, coluna2) VALUES (100, valor1,
 * valor2);<br>
 * -- Reativa a AUTO_INCREMENT para a coluna id<br>
 * SET @@auto_increment_increment=1;<br>
 *
 * @author fernando e miguel
 * @since 0.0.1 04/24
 * @version 0.0.1
 * @author Fernando
 */
public class PessoaDAO {

    private int id;
    private String nome;
    private String cidade;
    private String uf;
    private String cpf;
    private BancoDAO banco;
    private int admin;

    public PessoaDAO() {
        this.banco = new BancoDAO();
    }

    /**
     * Construtor com ID para registros pré-existentes <br>
     * e para a desserialização das tuplas do MySQL
     *
     * @param id_cliente
     * @param nome
     * @param cidade
     * @param uf
     * @param cpf
     */
    public PessoaDAO(int id_cliente, String nome, String cidade, String uf, String cpf) {
        this.id = id_cliente;
        this.nome = nome;
        this.cidade = cidade;
        this.uf = uf;
        this.cpf = cpf;
        this.admin = 0;
        this.banco = new BancoDAO();
    }

    /**
     * Construtor sem ID para novos registros <br>
     * utilizado para serializar
     *
     * @param nome
     * @param cidade
     * @param uf
     * @param cpf
     *
     */
    public PessoaDAO(String nome, String cidade, String uf, String cpf) {
        //id no auto increment
        this.nome = nome;
        this.cidade = cidade;
        this.uf = uf;
        this.cpf = cpf;
        this.banco = new BancoDAO();
        this.admin = 0;
    }

    /**
     * Método para salvar um cliente com ID. // TODO: Implementar o método
     * salvarClienteComId()<br>
     *
     * @deprecated Este método será implementado no futuro.
     * @throws SQLException Se ocorrer um erro durante a execução do SQL.
     *
     * // TODO: Implementar o método salvarClienteComId()
     */
    @Deprecated
    private boolean salvarPessoaComId() throws SQLException {
        Connection conexao = this.banco.getConexao();
        boolean resultado;

        String sql = "SET @@auto_increment_increment=0; "
                + "INSERT INTO pessoa(id, nome, cidade, uf, cpf, admin) VALUES (?, ?, ?, ?, ?, 0);"
                + "SET @@auto_increment_increment=1;";
        PreparedStatement consulta;

        try {

            consulta = conexao.prepareStatement(sql);
            consulta.setInt(1, this.id);
            consulta.setString(2, this.nome);
            consulta.setString(3, this.cidade);
            consulta.setString(4, this.uf);
            consulta.setString(5, this.cpf);
            consulta.execute();
            resultado = true;

        } catch (SQLException ex) {
            resultado = false;
            System.out.println("Erro ao inserir dados de Pessoa: " + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Salva uma pessoa no bdd como admin = 0
     *
     * @return boolean confirmação do insert
     * @throws SQLException
     */
    private boolean salvarPessoaSemId() throws SQLException {
        Connection conexao = this.banco.getConexao();
        boolean resultado = false;

        String sql
                = "INSERT INTO pessoa(nome, cidade, uf, cpf, admin) VALUES (?, ?, ?, ?, 0);";
        PreparedStatement consulta;

        try {

            consulta = conexao.prepareStatement(sql);
            consulta.setString(1, this.nome);
            consulta.setString(2, this.cidade);
            consulta.setString(3, this.uf);
            consulta.setString(4, this.cpf);
            consulta.execute();
            resultado = true;

        } catch (SQLException ex) {
            resultado = false;
            System.out.println("Erro ao inserir dados de Pessoa: " + ex.getMessage());
        }
        return resultado;
    }

    public boolean SalvarPessoa() {
        if (this.id == 0) {
            try {
                System.out.println("entrou no sem id");
                return salvarPessoaSemId();
            } catch (SQLException ex) {
                Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, "erro ao inserir sem informar o id", ex);
            }
        }
        if (this.id != 0) {
            try {
                return salvarPessoaComId();
            } catch (SQLException ex) {
                Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, "erro ao inserir informando o id", ex);
            }
        }
        return false;
    }

    public ArrayList<PessoaDAO> listarPessoasDAO() {

        Connection conexao = this.banco.getConexao();
        ArrayList<PessoaDAO> lista = new ArrayList<>();

        String sql = "SELECT * FROM pessoa";
        ResultSet resultados;

        try {
            resultados = conexao.createStatement().executeQuery(sql);
            PessoaDAO objeto;
            while (resultados.next()) {
                int id = resultados.getInt("id");
                String nome = resultados.getString("nome");
                String cidade = resultados.getString("cidade");
                String uf = resultados.getString("uf");
                String cpf = resultados.getString("CPF");
                int admin = Integer.parseInt(resultados.getString("admin"));
                objeto = new PessoaDAO(id, nome, cidade, uf, cpf);
                lista.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NA LEITURA DE DADOS DO BD: " + ex.getMessage());
        }
        return lista;
    }

    public boolean excluir() {
        Connection conexao = this.banco.getConexao();

        String sql = "DELETE FROM pessoa WHERE id = ?";
        PreparedStatement consulta;
        boolean excluido = false;

        try {
            consulta = conexao.prepareStatement(sql);
            consulta.setInt(1, this.id);
            int linhasExcluidas = consulta.executeUpdate();
            if (linhasExcluidas > 0) {
                excluido = true;
            }
        } catch (SQLException ex) {
            excluido = false;
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, "nao fora excluido nenhum registro", ex);
        }

        return excluido;
    }

    public PessoaDAO getPessoa(int codigo) {
        Connection conexao = this.banco.getConexao();

        String sql = "DELETE FROM pessoa WHERE id = ?";
        PreparedStatement consulta;
        ResultSet resultado;

        PessoaDAO objeto = null;

        try {
            consulta = conexao.prepareStatement(sql);
            consulta.setInt(1, codigo);
            resultado = consulta.executeQuery();

            if (resultado.next()) {

                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String cidade = resultado.getString("cidade");
                String uf = resultado.getString("uf");
                String cpf = resultado.getString("CPF");
                int admin = Integer.parseInt(resultado.getString("admin"));
                objeto = new PessoaDAO(id, nome, cidade, uf, cpf);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objeto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BancoDAO getBanco() {
        return banco;
    }

    public void setBanco(BancoDAO banco) {
        this.banco = banco;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

}
