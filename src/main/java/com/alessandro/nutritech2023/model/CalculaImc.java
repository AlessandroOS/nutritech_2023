package com.alessandro.nutritech2023.model;

import java.math.BigDecimal;

public class CalculaImc {

    private BigDecimal altura;
    private Double peso;

    public CalculaImc(BigDecimal altura, Double peso) {
        this.altura = altura;
        this.peso = peso;
    }

    public Imc calcula() {
        Double imc = peso.doubleValue() / (altura.doubleValue() * altura.doubleValue());

        ImcStatus imcStatus = ImcStatus.find(imc);
        return new Imc(new BigDecimal(imc), imcStatus);
    }
}

