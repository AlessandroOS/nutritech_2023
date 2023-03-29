package com.alessandro.nutritech2023.repository;

import com.alessandro.nutritech2023.model.GastoEnergetico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GastoEnergeticoRepository extends JpaRepository<GastoEnergetico, Long> {
    Optional<GastoEnergetico> findByPacienteId(Long pacienteId);
}