package com.dh.clinica.dto;

import com.dh.clinica.persistence.entities.Odontologo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OdontologoDto {

    //atributos dto
    private Integer id;
    private String nombre;
    private String apellido;
    private String matricula;

    //coinstructores dto
    public OdontologoDto() {
    }
    public OdontologoDto(String nombre, String apellido, String matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    //entidad de persistencia
    public Odontologo toEntity(){
        Odontologo entity = new Odontologo();
        entity.setId(id);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setMatricula(matricula);
        return entity;
    }
}