package com.alessandro.nutritech2023.controller;

import com.alessandro.nutritech2023.exceptions.PacienteNotFoundException;
import com.alessandro.nutritech2023.model.Paciente;
import com.alessandro.nutritech2023.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Paciente> savePaciente(@RequestBody Paciente paciente) {
        Paciente savedPaciente = pacienteService.savePaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPaciente);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        try {
            pacienteService.update(id, paciente);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (PacienteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacienteById(@PathVariable Long id) {
        try {
            pacienteService.deletePacienteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (PacienteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        try {
            Optional<Paciente> paciente = pacienteService.getPacienteById(id);
            return new ResponseEntity(paciente, HttpStatus.OK);
        } catch (PacienteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
