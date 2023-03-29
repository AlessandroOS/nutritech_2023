package com.alessandro.nutritech2023.model.dto;

import com.alessandro.nutritech2023.util.DecimalUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GastoEnergeticoDto {

    private Double valor;

    public String getValor() {
        return DecimalUtil.format(valor);
    }

}
