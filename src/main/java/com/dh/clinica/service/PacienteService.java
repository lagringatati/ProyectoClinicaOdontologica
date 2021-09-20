package com.dh.clinica.service;

import com.dh.clinica.persistence.entities.Domicilio;
import com.dh.clinica.persistence.entities.Paciente;
import com.dh.clinica.persistence.repository.PacienteRepository;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private DomicilioService domicilioService;
    private static final Logger logger = Logger.getLogger(PacienteService.class);

    //metodos CRUD paciente
    public Optional<Paciente> guardar(Paciente p) throws NotFoundException {
        Optional<Domicilio> pacienteDomicilio = p.getDomicilioId().isPresent() ? domicilioService.buscarPorId(p.getDomicilioId().get()) : domicilioService.guardar(p.getDomicilio());
        if (pacienteDomicilio.isEmpty()) {
            logger.debug(String.format("Error al obtener domicilio del paciente. Docimilio: %s", p.getDomicilio().toString()));
            throw new NotFoundException("El domicilio asociado no se encuentra en la base de datos.");
        }
        p.setDomicilio(pacienteDomicilio.get());
        return Optional.ofNullable(pacienteRepository.save(p));
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    public void borrar(Integer id) {
        pacienteRepository.deleteById(id);
    }

    public Optional<Paciente> modificar(Paciente p) throws NotFoundException {
        Optional<Paciente> result = buscarPorId(p.getId());
        if (result.isEmpty()) {
            logger.debug(String.format("No se encuentra el paciente con id %s", p.getId()));
            throw new NotFoundException("El paciente no se encuentra en la base de datos.");
        }
        Paciente paciente = result.get();
        paciente.setNombre(Optional.ofNullable(p.getNombre()).orElse(paciente.getNombre()));
        paciente.setApellido(Optional.ofNullable(p.getApellido()).orElse(paciente.getApellido()));
        paciente.setDni(Optional.ofNullable(p.getDni()).orElse(paciente.getDni()));
        paciente.setFechaIngreso(Optional.ofNullable(p.getFechaIngreso()).orElse(paciente.getFechaIngreso()));
        return guardar(paciente);
    }
}