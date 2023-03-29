package com.alessandro.nutritech2023.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PesoDto {
    private Long id;
    private double valor;
    private LocalDate data;
    private Long pacienteId;
}
