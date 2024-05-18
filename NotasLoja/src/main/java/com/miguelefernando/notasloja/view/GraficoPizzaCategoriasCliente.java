/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miguelefernando.notasloja.view;

import com.miguelefernando.DAO.consultaJoinsCategoriaEQuantidadePorClienteDAO;
import com.miguelefernando.DAO.consultaJoinsMarcaEQuantidadePorClienteDAO;
import java.util.ArrayList;
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

    public GraficoPizzaCategoriasCliente(ArrayList<consultaJoinsCategoriaEQuantidadePorClienteDAO> listaCategorias, ArrayList<consultaJoinsMarcaEQuantidadePorClienteDAO> listaMarcas) {
        setTitle("histórico do cliente");
        setSize(950,700);
        setLocationRelativeTo(this);
        CriarGraficoMarcas(listaMarcas);
        CriarGraficoCategorias(listaCategorias);
        setVisible(true);
    }
    
    public void CriarGraficoMarcas(ArrayList<consultaJoinsMarcaEQuantidadePorClienteDAO> lista){
        DefaultPieDataset pizza = new DefaultPieDataset();
        
        for (int i = 0; i < lista.size(); i++) {
            String marca = lista.get(i).getMarca();
            int quantidade = lista.get(i).getQuantidade();
            pizza.setValue(marca, quantidade);
        }
        

        
        JFreeChart grafico = ChartFactory.createPieChart("histórico do cliente com marcas", pizza, true, true, false);
        ChartPanel painel  = new ChartPanel(grafico);
        add(painel);
    }
    
    public void CriarGraficoCategorias(ArrayList<consultaJoinsCategoriaEQuantidadePorClienteDAO> lista){
        DefaultPieDataset pizza = new DefaultPieDataset();
        
        for (int i = 0; i < lista.size(); i++) {
            String categoria = lista.get(i).getCategoria();
            int quantidade = lista.get(i).getQuantidade();
            pizza.setValue(categoria, quantidade);
        }
        

        
        JFreeChart grafico = ChartFactory.createPieChart("histórico do cliente com categorias", pizza, true, true, false);
        ChartPanel painel  = new ChartPanel(grafico);
        add(painel);
    }
    
    
}
