package com.dh.clinica.controller;

import com.dh.clinica.persistence.entities.Domicilio;
import com.dh.clinica.service.DomicilioService;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    private DomicilioService domicilioService;
    private static final Logger logger = Logger.getLogger(DomicilioController.class);   //instancio Logger

    //agregar un domicilio
    @PostMapping("")
    public ResponseEntity<Domicilio> create(@RequestBody Domicilio domicilio) {
        Optional<Domicilio> result = domicilioService.guardar(domicilio);
        return result.isPresent() ?  ResponseEntity.ok(result.get()) : ResponseEntity.notFound().build();
    }

    //obtener todos
    @GetMapping("")
    public ResponseEntity<List<Domicilio>> getAll() {
        return ResponseEntity.ok(domicilioService.buscarTodos());
    }

    //obtener por id Domicilio
    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> getById(@PathVariable Integer id) {
        Optional<Domicilio> result = domicilioService.buscarPorId(id);
        return result.isPresent() ? ResponseEntity.ok(result.get()) :  ResponseEntity.notFound().build();
    }

    //borrar por id Domicilio
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        try {
            domicilioService.delete(id);
        } catch (Exception e) {
            logger.debug(String.format("No se encuentra el domicilio con id %s", id));
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(String.format("El domicilio %s fue eliminado", id));
    }

    //actualizar
    @PutMapping("")
    public ResponseEntity<Domicilio> update(@RequestBody Domicilio domicilio) {
        Optional<Domicilio> result;
        try {
            result = domicilioService.modificar(domicilio);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.get());
    }
}