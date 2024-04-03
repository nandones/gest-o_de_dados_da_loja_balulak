package com.miguelefernando.notasloja.utils;

public class ArredondaDoisDigitos {

    public static double arredondarParaDuasCasas(double numero) {
        return Math.round(numero * 100.0) / 100.0;
    }


}
