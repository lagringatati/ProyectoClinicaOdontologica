package com.dh.clinica.controller;

import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.service.OdontologoService;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;
    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    //agregar un odontologo
    @PostMapping("")
    public ResponseEntity<Odontologo> create(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> result = odontologoService.guardar(odontologo);
        return result.isPresent() ?  ResponseEntity.ok(result.get()) : ResponseEntity.notFound().build();
    }

    //obtener todos los odontologos
    @GetMapping("")
    public ResponseEntity<List<Odontologo>> getAll() {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    //obtener odontologo por id
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> getById(@PathVariable Integer id) {
        Optional<Odontologo> result = odontologoService.buscarPorId(id);
        return result.isPresent() ? ResponseEntity.ok(result.get()) :  ResponseEntity.notFound().build();
    }

    //borrar un odontologo buscando por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        try {
            odontologoService.borrar(id);
        } catch (Exception e) {
            logger.debug(String.format("No se encuentra el odontologo con id %s", id));
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(String.format("El odontologo %s fue eliminado", id));
    }

    //actualizar un odontologo
    @PutMapping("")
    public ResponseEntity<Odontologo> update(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> result;
        try {
            result = odontologoService.modificar(odontologo);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.get());
    }
}