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
public class consultaJoinsMarcaEQuantidadePorClienteDAO {
    private int ID_cliente;
    private String marca;
    private int quantidade;
    private BancoDAO banco;

    /**
     * Construtor para marshalling a partir do id do id do cliente
     * @param ID_cliente 
     * @author fernando
     * @since 05/24
     * @version 1.1
     */
    public consultaJoinsMarcaEQuantidadePorClienteDAO(int ID_cliente) {
        this.ID_cliente = ID_cliente;
        banco = new BancoDAO();
    }

    public consultaJoinsMarcaEQuantidadePorClienteDAO(String marca, int quantidade) {
        this.marca = marca;
        this.quantidade = quantidade;
        this.banco = new BancoDAO();//acho que não precisa dessa variavel aqui
    }

    public int getID_cliente() {
        return ID_cliente;
    }

    public void setID_cliente(int ID_cliente) {
        this.ID_cliente = ID_cliente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BancoDAO getBanco() {
        return banco;
    }

    public void setBanco(BancoDAO banco) {
        this.banco = banco;
    }
    
    public ArrayList<consultaJoinsMarcaEQuantidadePorClienteDAO> listarJoin() {
        Connection conexao = this.banco.getConexao();
        ArrayList<consultaJoinsMarcaEQuantidadePorClienteDAO> lista = new ArrayList<>();
        String sql = "SELECT * FROM view_marca_quantidade_por_cliente WHERE pessoa_id = ?;";
        PreparedStatement consulta;
        ResultSet resultados;

        try {
            consulta = conexao.prepareStatement(sql);
            consulta.setInt(1, this.ID_cliente);
            resultados = consulta.executeQuery();

            while (resultados.next()) {
                String marca = resultados.getString("marca");
                int quantidade = resultados.getInt("quantidade");

                consultaJoinsMarcaEQuantidadePorClienteDAO marca_quantidade = new consultaJoinsMarcaEQuantidadePorClienteDAO(marca, quantidade);
                lista.add(marca_quantidade);

            }
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i).getMarca()+ " ----- qtd: " + lista.get(i).getQuantidade());

            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NA LEITURA DE DADOS DO BD: " + ex.getMessage());
        }

        return lista;
    }
    
    
    
}

