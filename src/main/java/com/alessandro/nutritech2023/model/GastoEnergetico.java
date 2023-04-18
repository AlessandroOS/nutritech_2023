package com.alessandro.nutritech2023.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "gasto_energetico")
@Getter
@Setter
@NoArgsConstructor
public class GastoEnergetico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private LocalDate dataRegistro;

    @Column(nullable = false)
    private Double gastoEnergeticoTotal;

    @Column(nullable = false)
    private Double gastoEnergeticoBasal;

    @Column(nullable = false)
    private Double valor;

    public GastoEnergetico(Paciente paciente, Double valor) {
        this.paciente = paciente;
        this.valor = valor;
    }
}