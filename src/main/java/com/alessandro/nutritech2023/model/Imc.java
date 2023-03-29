package com.alessandro.nutritech2023.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Imc {

    private BigDecimal value;
    private ImcStatus status;

    public Imc(BigDecimal value, ImcStatus status) {
        this.value = value;
        this.status = status;
    }

    public String getDescription() {
        return status.getDescription();
    }
}
