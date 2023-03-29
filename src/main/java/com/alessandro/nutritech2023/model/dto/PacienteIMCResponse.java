package com.alessandro.nutritech2023.model.dto;

import com.alessandro.nutritech2023.util.DecimalUtil;
import java.math.BigDecimal;

public class PacienteIMCResponse {

    private BigDecimal value;
    private String description;

    public String getValue() {
        return DecimalUtil.format(value);
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
