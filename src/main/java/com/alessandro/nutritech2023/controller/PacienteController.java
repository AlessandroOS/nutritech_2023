package com.alessandro.nutritech2023.controller;

import com.alessandro.nutritech2023.exceptions.PacienteNotFoundException;
import com.alessandro.nutritech2023.model.dto.GastoEnergeticoDto;
import com.alessandro.nutritech2023.model.dto.PacienteDto;
import com.alessandro.nutritech2023.model.dto.PacienteIMCResponse;
import com.alessandro.nutritech2023.model.Paciente;
import com.alessandro.nutritech2023.service.GastoEnergeticoService;
import com.alessandro.nutritech2023.service.PacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;
    private final ModelMapper modelMapper;
    private final GastoEnergeticoService gastoEnergeticoService;

    public PacienteController(PacienteService pacienteService, ModelMapper modelMapper, GastoEnergeticoService gastoEnergeticoService) {
        this.pacienteService = pacienteService;
        this.modelMapper = modelMapper;
        this.gastoEnergeticoService = gastoEnergeticoService;
    }

    @PostMapping
    public ResponseEntity<PacienteDto> createPaciente(@RequestBody PacienteDto pacienteDto) {
        Paciente paciente = modelMapper.map(pacienteDto, Paciente.class);
        Paciente savedPaciente = pacienteService.savePaciente(paciente);
        PacienteDto savedPacienteDto = modelMapper.map(savedPaciente, PacienteDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPacienteDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> getPacienteById(@PathVariable Long id) {
        Optional<Paciente> optionalPaciente = pacienteService.getPacienteById(id);
        if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();
            PacienteDto pacienteDto = modelMapper.map(paciente, PacienteDto.class);
            return ResponseEntity.ok(pacienteDto);
        } else {
            throw new PacienteNotFoundException("Paciente não encontrado com o ID " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<PacienteDto>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        List<PacienteDto> pacientesDto = pacientes.stream()
                .map(p -> modelMapper.map(p, PacienteDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pacientesDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacienteById(@PathVariable Long id) {
        pacienteService.deletePacienteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> updatePaciente(@PathVariable Long id, @RequestBody PacienteDto pacienteDto) {
        Paciente paciente = modelMapper.map(pacienteDto, Paciente.class);
        Paciente updatedPaciente = pacienteService.update(id, paciente);
        PacienteDto updatedPacienteDto = modelMapper.map(updatedPaciente, PacienteDto.class);
        return ResponseEntity.ok(updatedPacienteDto);
    }

    @GetMapping("/{id}/imc")
    public ResponseEntity<PacienteIMCResponse> calculaImc(@PathVariable Long id) {
        PacienteIMCResponse imc = pacienteService.calculaImc(id);
        return ResponseEntity.ok(imc);
    }

    @GetMapping("/{id}/salva_e_calcula_gasto_energetico")
    public ResponseEntity<GastoEnergeticoDto> salvaEcalculaGastoEnergetico(@PathVariable Long id) {
        try {
            GastoEnergeticoDto gastoEnergetico = gastoEnergeticoService.salvaEcalculaGastoEnergetico(id);
            return ResponseEntity.ok(gastoEnergetico);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/get_gasto_energetico")
    public ResponseEntity<GastoEnergeticoDto> getGastoEnergetico(@PathVariable Long id) {
        try {
            GastoEnergeticoDto gastoEnergetico = gastoEnergeticoService.getGastoEnergetico(id);
            return ResponseEntity.ok(gastoEnergetico);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
