package com.alessandro.nutritech2023.model.dto;

import com.alessandro.nutritech2023.model.Dieta;
import com.alessandro.nutritech2023.model.GastoEnergetico;
import com.alessandro.nutritech2023.model.Genero;
import com.alessandro.nutritech2023.model.Peso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDto {
    private Long id;
    private String email;
    private String nome;
    private Genero genero;
    private LocalDate dataNascimento;
    private List<Dieta> dietas;
    private List<Peso> pesos;
    private Double altura;
    private List<GastoEnergetico> gastosEnergeticos;
}
