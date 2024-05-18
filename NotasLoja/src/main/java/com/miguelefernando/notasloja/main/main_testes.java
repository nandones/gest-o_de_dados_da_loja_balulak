/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miguelefernando.notasloja.main;

import com.miguelefernando.DAO.consultaJoinsCategoriaEQuantidadePorClienteDAO;
import com.miguelefernando.DAO.consultaJoinsMarcaEQuantidadePorClienteDAO;
import com.miguelefernando.notasloja.view.GraficoPizzaCategoriasCliente;
import java.util.ArrayList;

/**
 *
 * @author nando
 */
public class main_testes {
    public static void main(String[] args) {
        consultaJoinsMarcaEQuantidadePorClienteDAO a = new consultaJoinsMarcaEQuantidadePorClienteDAO(1);
        ArrayList dataMarcas = a.listarJoin();
        consultaJoinsCategoriaEQuantidadePorClienteDAO b = new consultaJoinsCategoriaEQuantidadePorClienteDAO(1);
        ArrayList dataCategorias = a.listarJoin();
        GraficoPizzaCategoriasCliente c = new GraficoPizzaCategoriasCliente(dataMarcas, dataCategorias);
    }
}
