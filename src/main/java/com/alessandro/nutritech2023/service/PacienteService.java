package com.alessandro.nutritech2023.service;

import com.alessandro.nutritech2023.exceptions.PacienteNotFoundException;
import com.alessandro.nutritech2023.model.Paciente;
import com.alessandro.nutritech2023.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente savePaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void deletePacienteById(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new PacienteNotFoundException("Paciente não encontrado");
        }
        pacienteRepository.deleteById(id);
    }

    public void update(Long id, Paciente paciente) {
        Optional<Paciente> optionalPaciente = getPacienteById(id);
        if (optionalPaciente.isEmpty()) {
            throw new PacienteNotFoundException("Paciente não encontrado");
        }

        Paciente existingPaciente = optionalPaciente.get();
        existingPaciente.setNome(paciente.getNome());
        existingPaciente.setEmail(paciente.getEmail());
        existingPaciente.setSenha(paciente.getSenha());
        existingPaciente.setGenero(paciente.getGenero());
        existingPaciente.setAltura(paciente.getAltura());

        savePaciente(existingPaciente);
    }

    public Optional<Paciente> getPacienteById(Long id)  {
        return Optional.ofNullable(pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado")));
    }
}
