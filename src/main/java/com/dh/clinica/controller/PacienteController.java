package com.dh.clinica.controller;

import com.dh.clinica.persistence.entities.Paciente;
import com.dh.clinica.service.PacienteService;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;
    private static final Logger logger = Logger.getLogger(PacienteController.class);

    //crear paciente
    @PostMapping("")
    public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
        Optional<Paciente> result;
        try {
            result = pacienteService.guardar(paciente);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
        return result.isPresent() ?  ResponseEntity.ok(result.get()) : ResponseEntity.notFound().build();
    }

    //obtener todos los pacientes
    @GetMapping("")
    public ResponseEntity<List<Paciente>> getAll() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    //obtener un paciente por id
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable Integer id) {
        Optional<Paciente> result = pacienteService.buscarPorId(id);
        return result.isPresent() ? ResponseEntity.ok(result.get()) :  ResponseEntity.notFound().build();
    }

    //borrar un paciente de acuerdo a su id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        try {
            pacienteService.borrar(id);
        } catch (Exception e) {
            logger.debug(String.format("No se encuentra el paciente con id %s", id));
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(String.format("El paciente %s fue eliminado", id));
    }

    //actualizar un paciente
    @PutMapping("")
    public ResponseEntity<Paciente> update(@RequestBody Paciente paciente) {
        Optional<Paciente> result;
        try {
            result = pacienteService.modificar(paciente);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.get());
    }
}