package com.miguelefernando.notasloja.main;

import com.miguelefernando.DAO.Banco;
import com.miguelefernando.DAO.PessoaDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class main {

    public static void main(String[] args) {
        PessoaDAO pessoa = new PessoaDAO(15,"willen", "lages", "sc", "000.000.000-00");
        try {
            System.out.println(pessoa.salvarClienteSemId());
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
