package com.alessandro.nutritech2023.model;

public enum ImcStatus {

    MAGREZA("Magreza"),
    NORMAL("Normal"), SOBREPESO("Sobrepeso"),
    OBESO_GRAU_I("Obesidade Grau I"),
    OBESO_GRAU_II("Obesidade Grau II"),
    OBESO_GRAU_III("Obesidade Grau III");

    private final String description;

    private ImcStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ImcStatus find(Double imc) {
        if (imc < 18.5)
            return MAGREZA;
        if (imc >=18.6 && imc <= 24.9)
            return NORMAL;
        if (imc >= 25 && imc <= 29.9)
            return SOBREPESO;
        if (imc >= 30 && imc <= 34.9)
            return OBESO_GRAU_I;
        if (imc >= 35 && imc <= 39.9)
            return OBESO_GRAU_II;
        if (imc > 40)
            return OBESO_GRAU_III;

        throw new RuntimeException("Valor de IMC inválido");
    }
}
