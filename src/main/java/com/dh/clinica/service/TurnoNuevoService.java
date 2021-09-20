package com.dh.clinica.service;

import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.entities.Paciente;
import com.dh.clinica.persistence.entities.Turno;
import com.dh.clinica.persistence.repository.TurnoRepository;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoNuevoService {

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    private static final Logger logger = Logger.getLogger(TurnoNuevoService.class);

    //metodos CRUD turno
    public Optional<Turno> guardar(Turno turno, Integer pacienteId, Integer odontologoId) throws NotFoundException {
        Optional<Paciente> paciente = pacienteService.buscarPorId(pacienteId);
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(odontologoId);
        if (paciente.isEmpty() || odontologo.isEmpty()) {
            throw new NotFoundException("Error al obtener datos para guardar el turno.");
        }
        turno.setOdontologo(odontologo.get());
        turno.setPaciente(paciente.get());
        return Optional.ofNullable(turnoRepository.save(turno));
    }

    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    public Optional<Turno> buscarPorId(Integer id) {
        return turnoRepository.findById(id);
    }

    public void borrar(Integer id) {
        turnoRepository.deleteById(id);
    }

    public Optional<Turno> modificar(Turno t) {
        Optional<Turno> result = buscarPorId(t.getId());
        Optional<Paciente> paciente = pacienteService.buscarPorId(t.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(t.getOdontologo().getId());
        if (result.isEmpty()) {
            logger.debug(String.format("No se encuentra el turno con id %s", t.getId()));
        }
        Turno turno = result.get();
        turno.setFecha(Optional.ofNullable(t.getFecha()).orElse(turno.getFecha()));
        turno.setPaciente(paciente.get());
        turno.setOdontologo(odontologo.get());
        return Optional.ofNullable(turnoRepository.save(turno));
    }
}
