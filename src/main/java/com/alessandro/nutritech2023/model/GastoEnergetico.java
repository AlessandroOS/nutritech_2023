package com.alessandro.nutritech2023.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "gasto_energetico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GastoEnergetico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDate data;
}