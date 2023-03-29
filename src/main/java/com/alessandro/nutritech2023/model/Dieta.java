package com.alessandro.nutritech2023.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dieta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Paciente paciente;

    @Column(nullable = false)
    private String descricao;

}

