package com.alessandro.nutritech2023.repository;

import com.alessandro.nutritech2023.model.Peso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesoRepository extends JpaRepository<Peso, Long> {

    List<Peso> findPesosByPacienteId(Long pacienteId);
}

