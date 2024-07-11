package com.miguelefernando.notasloja.view;

import com.miguelefernando.DAO.consultaJoinsCategoriaEQuantidadePorClienteDAO;
import com.miguelefernando.DAO.consultaJoinsMarcaEQuantidadePorClienteDAO;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * classe responsavel pela exibição do gráfico com o histórico de categoria <br>
 * das compras do cliente
 */
public class GraficoPizzaCategoriasCliente extends JFrame {

    public GraficoPizzaCategoriasCliente(ArrayList<consultaJoinsCategoriaEQuantidadePorClienteDAO> listaCategorias, ArrayList<consultaJoinsMarcaEQuantidadePorClienteDAO> listaMarcas) {
        setTitle("Histórico do Cliente");
        setSize(950, 700);
        setLocationRelativeTo(null);

        // Painel principal para conter os gráficos
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridLayout(1, 2)); // Define layout com 1 linha e 2 colunas

        // Cria e adiciona os gráficos ao painel principal
        ChartPanel painelGraficoMarcas = CriarGraficoMarcas(listaMarcas);
        ChartPanel painelGraficoCategorias = CriarGraficoCategorias(listaCategorias);

        painelPrincipal.add(painelGraficoMarcas);
        painelPrincipal.add(painelGraficoCategorias);

        add(painelPrincipal); // Adiciona o painel principal ao JFrame

        setVisible(true);
    }

    public ChartPanel CriarGraficoMarcas(ArrayList<consultaJoinsMarcaEQuantidadePorClienteDAO> lista) {
        DefaultPieDataset pizza = new DefaultPieDataset();

        for (consultaJoinsMarcaEQuantidadePorClienteDAO item : lista) {
            String marca = item.getMarca();
            int quantidade = item.getQuantidade();
            pizza.setValue(marca, quantidade);
        }

        JFreeChart grafico = ChartFactory.createPieChart("Histórico do Cliente com Marcas", pizza, true, true, false);
        return new ChartPanel(grafico);
    }

    public ChartPanel CriarGraficoCategorias(ArrayList<consultaJoinsCategoriaEQuantidadePorClienteDAO> lista) {
        DefaultPieDataset pizza = new DefaultPieDataset();

        for (consultaJoinsCategoriaEQuantidadePorClienteDAO item : lista) {
            String categoria = item.getCategoria();
            int quantidade = item.getQuantidade();
            pizza.setValue(categoria, quantidade);
        }

        JFreeChart grafico = ChartFactory.createPieChart("Histórico do Cliente com Categorias", pizza, true, true, false);
        return new ChartPanel(grafico);
    }
}
