package com.alessandro.nutritech2023.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "peso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Peso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDate data;
}