package com.alessandro.nutritech2023.service;

import com.alessandro.nutritech2023.model.*;
import com.alessandro.nutritech2023.model.dto.GastoEnergeticoDto;
import com.alessandro.nutritech2023.repository.GastoEnergeticoRepository;
import com.alessandro.nutritech2023.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
public class GastoEnergeticoService {
    private final GastoEnergeticoRepository gastoEnergeticoRepository;
    private final PacienteRepository pacienteRepository;

    public GastoEnergeticoService(GastoEnergeticoRepository gastoEnergeticoRepository, PacienteRepository pacienteRepository) {
        this.gastoEnergeticoRepository = gastoEnergeticoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public GastoEnergeticoDto salvaEcalculaGastoEnergetico(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        CalculaGastoEnergetico calculaGastoEnergetico = new CalculaGastoEnergetico(
                new BigDecimal(paciente.getAltura()), getPesoRecente(paciente).getValor(), paciente.getIdade(), paciente.getGenero());
        Double resultado = calculaGastoEnergetico.calculaIMC();
        GastoEnergetico gastoEnergetico = new GastoEnergetico(paciente, resultado);
        gastoEnergeticoRepository.save(gastoEnergetico);
        return new GastoEnergeticoDto(resultado);
    }

    public GastoEnergeticoDto getGastoEnergetico(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        GastoEnergetico gastoEnergetico = gastoEnergeticoRepository.findByPacienteId(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Gasto energético não encontrado para o paciente"));

        return new GastoEnergeticoDto(gastoEnergetico.getValor());
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
