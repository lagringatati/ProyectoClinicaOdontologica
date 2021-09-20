package com.dh.clinica.service;

import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.repository.OdontologoRepository;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;
    private static final Logger logger = Logger.getLogger(OdontologoService.class);

    //metodos CRUD Odontologo

    public Optional<Odontologo> guardar(Odontologo o) {
        return Optional.ofNullable(odontologoRepository.save(o));
    }

    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> buscarPorId(Integer odontologoId) {
        return odontologoRepository.findById(odontologoId);
    }

    public void borrar(Integer odontologoId) {
        odontologoRepository.deleteById(odontologoId);
    }

    public Optional<Odontologo> modificar(Odontologo o) throws NotFoundException {
        Optional<Odontologo> result = buscarPorId(o.getId());
        if (result.isEmpty()) {
            logger.debug(String.format("No se encuentra el odontologo con id %s", o.getId()));
            throw new NotFoundException("El odontologo no se encuentra en la base de datos.");
        }
        Odontologo odontologo = result.get();
        odontologo.setNombre(Optional.ofNullable(o.getNombre()).orElse(odontologo.getNombre()));
        odontologo.setApellido(Optional.ofNullable(o.getApellido()).orElse(odontologo.getApellido()));
        odontologo.setMatricula(Optional.ofNullable(o.getMatricula()).orElse(odontologo.getMatricula()));
        return guardar(odontologo);
    }
}