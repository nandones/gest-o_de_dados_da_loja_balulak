package com.miguelefernando.DAO;

/**
 *
 * @author Fernando
 */
public class Pedido_ProdutoDAO {
    private int id_pedido;
    private int id_produto;
    private int quantidade;

    public Pedido_ProdutoDAO(int id_pedido, int id_produto, int quantidade) {
        this.id_pedido = id_pedido;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
    }
    
    
}

