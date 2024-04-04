package com.miguelefernando.notasloja.main;

import com.miguelefernando.DAO.PessoaDAO;
import com.miguelefernando.DAO.ProdutoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *\lfvgheruo
 * @author Fernando
 */
public class main {

    public static void main(String[] args) {
        PessoaDAO p1 = new PessoaDAO("willen", "lages", "sc", "000.000.000-00");
        PessoaDAO p2 = new PessoaDAO("MONARK", "eua", "ac", "000.000.000-01");
        PessoaDAO p3 = new PessoaDAO("NANDO MOURA", "sp", "sp", "000.000.000-02");
        PessoaDAO p4 = new PessoaDAO("NATHAN FISHERMAN", "balneario", "sc", "000.000.000-03");
        PessoaDAO p5 = new PessoaDAO("ADAM SANDVER", "eua", "pr", "000.000.000-04");
        try {
            System.out.println(p1.salvarPessoaSemId());
            System.out.println(p2.salvarPessoaSemId());
            System.out.println(p3.salvarPessoaSemId());
            System.out.println(p4.salvarPessoaSemId());
            System.out.println(p5.salvarPessoaSemId());

            
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ProdutoDAO pr1 = new ProdutoDAO (20.0, "camisa you ken do ramon", 'r');
        ProdutoDAO pr2 = new ProdutoDAO (20.0, "moletom verde do luisao", 'p');
        try {
            System.out.println(pr1.salvarProduto());
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }          
        
        PessoaDAO dummy = new PessoaDAO();
        ArrayList<PessoaDAO> pessoas = dummy.listarPessoasDAO();
        for (int i = 0; i < pessoas.size(); i++) {
            System.out.println("nome : " + pessoas.get(i).getNome() + " -- CPF: " + pessoas.get(i).getCpf());
            
        }    

    }
}
