package com.miguelefernando.notasloja.main;

import com.miguelefernando.DAO.ConsultaJoinPedidoEPedido_Produto;
import com.miguelefernando.DAO.PessoaDAO;
import com.miguelefernando.DAO.ProdutoDAO;
import com.miguelefernando.notasloja.utils.Mock;
import com.miguelefernando.notasloja.view.Janela;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * \lfvgheruo
 *
 * @author Fernando
 */
public class main {

    public static void main(String[] args) {
//        Janela mi = new Janela();
//        mi.setVisible(true);
//        mi.setLocationRelativeTo(null);

        ConsultaJoinPedidoEPedido_Produto ppp = new ConsultaJoinPedidoEPedido_Produto(1);
        ppp.listarJoin();
    }
}
