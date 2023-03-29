package com.alessandro.nutritech2023.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column(nullable = false)
    private Double altura;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dieta> dietas;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Peso> pesos = new ArrayList<>();

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GastoEnergetico> gastosEnergeticos = new java.util.ArrayList<>();

    public Integer getIdade() {
        Period diff = Period.between(dataNascimento, LocalDate.now());
        return diff.getYears();
    }
}