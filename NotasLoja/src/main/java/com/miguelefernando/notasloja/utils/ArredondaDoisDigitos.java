package com.miguelefernando.notasloja.utils;

public class ArredondaDoisDigitos {

    public static double truncarParaDuasCasas(double numero) {
        return Math.round(numero * 100.0) / 100.0;
    }

    public static void main(String[] args) {
        double numero = 12.3456;
        double numeroTruncado = truncarParaDuasCasas(numero);
        System.out.println("Número original: " + numero);
        System.out.println("Número truncado: " + numeroTruncado);
    }

}
