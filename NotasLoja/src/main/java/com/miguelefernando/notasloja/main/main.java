package com.miguelefernando.notasloja.main;

import com.miguelefernando.DAO.BancoDAO;
import com.miguelefernando.DAO.ConsultaJoinPedidoEPedido_Produto;
import com.miguelefernando.DAO.PessoaDAO;
import com.miguelefernando.DAO.ProdutoDAO;
import com.miguelefernando.notasloja.utils.Mock;
import com.miguelefernando.notasloja.view.Janela;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * chama o jframe
 *
 * @author Fernando
 */
public class main {

    public static void main(String[] args) {
        Janela mi = new Janela();
        mi.setVisible(true);
        mi.setLocationRelativeTo(null);

        String idioma;
        String pais;
        
        if(args.length != 2){ //executavel foi chamado sem argumentos/parametro cli
            idioma = System.getProperty("user.language"); //idioma na config do sistema operacional
            pais = System.getProperty("user.country"); //pais na config do sistema operacional
        }else{ //temos argumentos por cli
            idioma = args[0]; //idioma enviado por cli
            pais = args[1]; //pais enviado por cli
        }
        
        String busca = idioma + "_" + pais;
        boolean temTraducao = false;
        File diretorio = new File("idiomas");
        File[] listagemDiretorio = diretorio.listFiles();
        if (listagemDiretorio != null) { //lista de arquivos encontrados
            for (File arquivo : listagemDiretorio) {
                if(arquivo.getName().contains(busca)){
                    temTraducao = true;
                    break;
                } 
            }
        }
        if(!temTraducao){ //se nao encontrou entao usa uma traducao padrao
            idioma = "pt";
            pais = "BR";
        }
        
        
        
        Locale localCorrente;
        ResourceBundle traducoes = null;
        
        //para desenvolvimento local
        //localCorrente = new Locale(idioma, pais);
        //traducoes = ResourceBundle.getBundle("MessagesBundle", localCorrente);
        //teremos MessagesBundle_idioma_pais.properties
       
        //para o executavel .jar final
        InputStream newInputStream;
  
        String nomeArquivo = "./idiomas/MessagesBundle_"+idioma+"_"+pais+".properties";
        System.out.println(nomeArquivo);
        try {
            newInputStream = Files.newInputStream(Paths.get(nomeArquivo));
            traducoes = new PropertyResourceBundle(newInputStream);
            System.out.println(traducoes);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(traducoes.getString("cad_cliente_nome"));


    }
}
