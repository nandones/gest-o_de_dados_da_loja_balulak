/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miguelefernando.notasloja.main;

import com.miguelefernando.DAO.consultaJoinsCategoriaEQuantidadePorClienteDAO;

/**
 *
 * @author nando
 */
public class main_testes {
    public static void main(String[] args) {
        consultaJoinsCategoriaEQuantidadePorClienteDAO a = new  consultaJoinsCategoriaEQuantidadePorClienteDAO(1);
        a.listarJoin();
    }
}
