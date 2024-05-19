/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miguelefernando.notasloja.view;

import com.miguelefernando.DAO.consultaJoinsCategoriaEQuantidadePorClienteDAO;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author fernando
 * classe responsavel pela exibição do gráfico com o histórico de categoria <br>
 * das compras do cliente
 */
public class GraficoPizzaCategoriasCliente extends JFrame {

    public GraficoPizzaCategoriasCliente(ArrayList<consultaJoinsCategoriaEQuantidadePorClienteDAO> lista, ResourceBundle traducoes) {
        
        setTitle(traducoes.getString("hist_cliente"));
        setSize(950,700);
        setLocationRelativeTo(this);
        CriarGrafico(lista, traducoes);
        setVisible(true);
    }
    
    public void CriarGrafico(ArrayList<consultaJoinsCategoriaEQuantidadePorClienteDAO> lista, ResourceBundle traducoes){
        DefaultPieDataset pizza = new DefaultPieDataset();
        
        for (int i = 0; i < lista.size(); i++) {
            String categoria = lista.get(i).getCategoria();
            int quantidade = lista.get(i).getQuantidade();
            pizza.setValue(categoria, quantidade);
        }
        

        
        JFreeChart grafico = ChartFactory.createPieChart(traducoes.getString("hist_cliente"), pizza, true, true, false);
        ChartPanel painel  = new ChartPanel(grafico);
        add(painel);
    }
    
    
}
