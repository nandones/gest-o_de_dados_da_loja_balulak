package com.miguelefernando.DAO;

import java.sql.Date; // Importe a classe java.sql.Date

/**
 *
 * @author Fernando
 */
public class PedidoDAO {
    
    private int id;
    private int id_cliente;
    private Date emissao;   
    private Date fechamento;
    private char status;
    private double total;

    @Deprecated
    public PedidoDAO(int id, int id_cliente, Date emissao, Date fechamento, char status, double total) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.emissao = emissao;
        this.fechamento = fechamento;
        this.status = status;
        this.total = total;
    }

    public PedidoDAO(int id_cliente, Date emissao, Date fechamento, char status, double total) {
        this.id_cliente = id_cliente;
        this.emissao = emissao;
        this.fechamento = fechamento;
        this.status = status;
        this.total = total;
    }
    
    
}


