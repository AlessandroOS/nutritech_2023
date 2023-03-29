package com.alessandro.nutritech2023.controller;

import com.alessandro.nutritech2023.model.dto.PesoDto;
import com.alessandro.nutritech2023.model.Peso;
import com.alessandro.nutritech2023.service.PesoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pesos")
public class PesoController {

    private final PesoService pesoService;
    private final ModelMapper modelMapper;

    public PesoController(PesoService pesoService, ModelMapper modelMapper) {
        this.pesoService = pesoService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<PesoDto> createPeso(@RequestBody PesoDto pesoDto) {
        Peso peso = modelMapper.map(pesoDto, Peso.class);
        Peso savedPeso = pesoService.save(peso);
        PesoDto savedPesoDto = modelMapper.map(savedPeso, PesoDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPesoDto);
    }

    @GetMapping
    public ResponseEntity<List<PesoDto>> getPesos() {
        List<Peso> pesos = pesoService.findAll();
        List<PesoDto> pesoDtos = pesos.stream()
                .map(peso -> modelMapper.map(peso, PesoDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pesoDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PesoDto> getPesoById(@PathVariable Long id) {
        Optional<Peso> optionalPeso = pesoService.findById(id);
        if (optionalPeso.isPresent()) {
            PesoDto pesoDto = modelMapper.map(optionalPeso.get(), PesoDto.class);
            return ResponseEntity.ok(pesoDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePesoById(@PathVariable Long id) {
        pesoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<PesoDto>> getPesosByPacienteId(@PathVariable Long pacienteId) {
        List<Peso> pesos = pesoService.findPesosByPacienteId(pacienteId);
        List<PesoDto> pesoDtos = pesos.stream()
                .map(peso -> modelMapper.map(peso, PesoDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pesoDtos);
    }
}