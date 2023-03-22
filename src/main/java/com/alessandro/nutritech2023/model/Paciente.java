package com.alessandro.nutritech2023.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private String senha;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @OneToMany(mappedBy = "paciente")
    private List<Dieta> dietas;

    @OneToMany(mappedBy = "paciente")
    private List<Peso> pesos = new java.util.ArrayList<>();

    @Column(nullable = false)
    private Double altura;

    @OneToMany(mappedBy = "paciente")
    private List<GastoEnergetico> gastosEnergeticos = new java.util.ArrayList<>();
}