package com.alessandro.nutritech2023.service;

import com.alessandro.nutritech2023.exceptions.PacienteNotFoundException;
import com.alessandro.nutritech2023.model.CalculaImc;
import com.alessandro.nutritech2023.model.dto.PacienteIMCResponse;
import com.alessandro.nutritech2023.model.Imc;
import com.alessandro.nutritech2023.model.Paciente;
import com.alessandro.nutritech2023.model.Peso;
import com.alessandro.nutritech2023.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

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

    public Paciente update(Long id, Paciente paciente) {
        Optional<Paciente> optionalPaciente = getPacienteById(id);
        if (optionalPaciente.isEmpty()) {
            throw new PacienteNotFoundException("Paciente não encontrado");
        }

        Paciente existingPaciente = optionalPaciente.get();
        existingPaciente.setNome(paciente.getNome());
        existingPaciente.setEmail(paciente.getEmail());
        existingPaciente.setGenero(paciente.getGenero());
        existingPaciente.setAltura(paciente.getAltura());

        savePaciente(existingPaciente);
        return existingPaciente;
    }

    public Optional<Paciente> getPacienteById(Long id) {
        return Optional.ofNullable(pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado")));
    }

    public PacienteIMCResponse calculaImc(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        Peso pesoRecente = getPesoRecente(paciente);

        CalculaImc calculaImc = new CalculaImc(new BigDecimal(paciente.getAltura()), pesoRecente.getValor());
        Imc imc = calculaImc.calcula();

        PacienteIMCResponse response = new PacienteIMCResponse();
        response.setDescription(imc.getDescription());
        response.setValue(imc.getValue());

        return response;
    }

    private Peso getPesoRecente(Paciente paciente) {
        List<Peso> pesos = paciente.getPesos();

        if (pesos.isEmpty()) {
            throw new EntityNotFoundException("Não há pesos registrados para este paciente");
        }

        return pesos.stream().max(Comparator.comparing(peso -> peso.getData()))
                .orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar um peso recente para o paciente"));
    }
}
