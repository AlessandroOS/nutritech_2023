package com.alessandro.nutritech2023.model;

import java.math.BigDecimal;

public class CalculaGastoEnergetico {

    private BigDecimal altura;
    private Double peso;
    private Integer idade;
    private Genero genero;

    public CalculaGastoEnergetico(BigDecimal altura, Double peso, Integer idade, Genero genero) {
        this.altura = altura;
        this.peso = peso;
        this.idade = idade;
        this.genero = genero;
    }

    public Double calculaIMC() {
        if (Genero.MASCULINO.equals(this.genero)) {
            Double resultado = 66 + (13.7 * peso.doubleValue()) + (5.0 * (altura.doubleValue() * 100)) - (6.8 * idade);

            return resultado;
        }
        return 665 + (9.6 * peso.doubleValue()) + (1.8 * altura.doubleValue()) - (4.7 * idade);
    }
}
