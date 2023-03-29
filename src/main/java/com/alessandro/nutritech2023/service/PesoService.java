package com.alessandro.nutritech2023.service;

import com.alessandro.nutritech2023.model.Peso;
import com.alessandro.nutritech2023.repository.PesoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PesoService {

    private final PesoRepository pesoRepository;

    public PesoService(PesoRepository pesoRepository) {
        this.pesoRepository = pesoRepository;
    }

    public Peso save(Peso peso) {
        return pesoRepository.save(peso);
    }

    public List<Peso> findAll() {
        return pesoRepository.findAll();
    }

    public Optional<Peso> findById(Long id) {
        return pesoRepository.findById(id);
    }

    public void deleteById(Long id) {
        pesoRepository.deleteById(id);
    }

    public List<Peso> findPesosByPacienteId(Long pacienteId) {
        return pesoRepository.findPesosByPacienteId(pacienteId);
    }
}
