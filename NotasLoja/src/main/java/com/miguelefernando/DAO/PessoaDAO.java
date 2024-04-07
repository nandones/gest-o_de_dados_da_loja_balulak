package com.miguelefernando.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
 * @since 03/24
 * @version 1.0
 */
public class PessoaDAO {

    private int id;
    private String nome;
    private String cidade;
    private String uf;
    private String cpf;
    private BancoDAO banco;
    private int admin;

    /**
     * construtor vazio, gerando apenas um objeto bancoDAO para posterior<br>
     * conseguir uma Connection através do método getConexao
     * @see BancoDAO
     * @see BancoDAO#getConexao
     * @author fernando
     * @since 03/24
     * @version 1.0
     */
    public PessoaDAO() {
        this.banco = new BancoDAO();
    }

    /**
     * Construtor com ID para registros pré-existentes -p/ TODO dados svindos do excel- <br>
     * e para O unmarshalling das tuplas do MySQL<br>
     * a column admin expressa um TODO: separar pessoas em funcionários e clientes
     *
     * @param id_cliente
     * @param nome
     * @param cidade
     * @param uf
     * @param cpf
     *
     * @see PessoaDAO#SalvarPessoa
     * @author fernando
     * @since 03/24
     * @version 1.0
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
     * Construtor sem ID para marshalling <br>
     * contando com o autoincrease da table Pessoa
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
     * Insere um novo registro na table pessoa sem informar o id,<br>
     * contando com o MySQL para essa função.
     * @throws SQLException
     * @return boolean confirmando a operação
     * @author fernando
     * @since 03/24
     * @version 1.0
     *
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
    /**
     * simplesmente um seletor para caso o objeto inserido possui id previamente informado ou não<br>
     * TODO: a inserção com o id informado não está operacional no momentpo que esta versão corresponde a 1.0
     * @return boolean confiremando a inserção
     * @author fernando
     * @since 03/24
     * @version 1.0
     */
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
    /**
     * método que retorna uma lista de PessaoDAO correspondente ás tuplas do bd
     * @return ArrayList&lt;ConsultaJoinPedidoEPedido_Produto&gt;
     * @author fernando
     * @since 03/24
     * @version 1.0
     */
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
    /**
     * CUIDADO: não é possível excluir um clinte com pedidos em seu nome,<br>
     * essa operação exigiria excluir uma PK referenciada em outra tabela.<br>
     * esse método só existe por ser considerado de bom tom para aplicarmos todos os <br>
     * métodos aprendidos em sala, um sistema a sério estaria utilizando uma coluna que,br.
     * seria preenchida com DATE  da exclusão da conta (null significa que a conta está ativa) 
     * @return boolean para confirmar a exclusão
     * @author fernando
     * @since 03/24
     * @version 1.0
     */
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
            JOptionPane.showMessageDialog(null, "NÃO É POSSIVEL EXCLUIR UM CLIENTE COM PEDIDOS", "ERRO", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, "nao fora excluido nenhum registro", ex);
        }

        return excluido;
    }

    /***
     * a partir de um id como parâmetro, o método retorna a tupla da table pessoa<br>
     * correspondente a esse id como objeto de unmashaling
     * @param codigo int
     * @return objeto PessoaDAO buscado
     * @author fernando
     * @since 03/24
     * @version 1.0
     */
    public PessoaDAO getPessoa(int codigo) {
        Connection conexao = this.banco.getConexao();

        String sql = "SELECT * FROM pessoa WHERE id = ?";
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
    /**
     * ATENÇÃO: exige objeto PessoaDAO correspondente á uma tupla ao chamar este método,<br>
     * por não aceitar parâmetros, esta Pessoa, com this.id será localizada e terá as columns<br>
     * nome, cidade, uf, CPF substituidos pelos atributos da atual PessoaDAO instanciada
     * @return 
     */
    public boolean update() {
        Connection conexao = this.banco.getConexao();

        String sql = "UPDATE pessoa SET nome = ?, cidade = ?, uf = ?, CPF = ? WHERE id = ?";
        PreparedStatement consulta;
        boolean atualizado = false;//

        try {
            consulta = conexao.prepareStatement(sql);
            consulta.setString(1, this.nome);
            consulta.setString(2, this.cidade);
            consulta.setString(3, this.uf);
            consulta.setString(4, this.cpf);
            consulta.setInt(5, id);
            int linhasAtualizadas = consulta.executeUpdate();
            if (linhasAtualizadas > 0) {
                atualizado = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atualizado;
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
