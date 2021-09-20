package com.dh.clinica.service;

import com.dh.clinica.persistence.entities.Domicilio;
import com.dh.clinica.persistence.repository.DomicilioRepository;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {

    @Autowired
    private DomicilioRepository domicilioRepository;
    private static final Logger logger = Logger.getLogger(DomicilioService.class);

    //metodos CRUD Domicilio

    public Optional<Domicilio> guardar(Domicilio d) {
        return Optional.ofNullable(domicilioRepository.save(d));
    }

    public List<Domicilio> buscarTodos() {
        return domicilioRepository.findAll();
    }

    public Optional<Domicilio> buscarPorId(Integer domicilioId) {
        return domicilioRepository.findById(domicilioId);
    }

    public void delete(Integer domicilioId) {
        domicilioRepository.deleteById(domicilioId);
    }

    public Optional<Domicilio> modificar(Domicilio d) throws NotFoundException {
        Optional<Domicilio> result = buscarPorId(d.getId());
        if (result.isEmpty()) {
            logger.debug(String.format("No se encuentra el domicilio con id %s", d.getId()));
            throw new NotFoundException("El domicilio no se encuentra en la base de datos.");
        }
        Domicilio domicilio = result.get();
        domicilio.setCalle(Optional.ofNullable(d.getCalle()).orElse(domicilio.getCalle()));
        domicilio.setNumero(Optional.ofNullable(d.getNumero()).orElse(domicilio.getNumero()));
        domicilio.setLocalidad(Optional.ofNullable(d.getLocalidad()).orElse(domicilio.getLocalidad()));
        domicilio.setProvincia(Optional.ofNullable(d.getProvincia()).orElse(domicilio.getProvincia()));
        return guardar(domicilio);
    }
}