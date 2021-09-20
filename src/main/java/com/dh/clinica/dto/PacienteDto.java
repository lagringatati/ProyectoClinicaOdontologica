package com.dh.clinica.dto;

import com.dh.clinica.persistence.entities.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PacienteDto {

    //atributos dto
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaIngreso;
    private DomicilioDto domicilio;

    //constructores
    public PacienteDto(){
    }
    public PacienteDto(String nombre, String apellido, String dni, LocalDate fechaIngreso, DomicilioDto domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    //entidad de persistencia Paciente
    public Paciente toEntity() {
        return new Paciente(id, nombre, apellido, dni, fechaIngreso, domicilio.toEntity());
    }
}