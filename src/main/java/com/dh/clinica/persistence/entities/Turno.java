package com.dh.clinica.persistence.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @SequenceGenerator(name = "turnoSequence", sequenceName = "turnoSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turnoSequence")
    @Column(name = "turno_id")
    //atributos
    private Integer id;
    private LocalDate fecha;

    //relaciones
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;

    //constructores
    public Turno() {
    }
    public Turno(LocalDate fecha, Paciente paciente, Odontologo odontologo) {
        this.fecha = fecha;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    //getters y setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Odontologo getOdontologo() {
        return odontologo;
    }
    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    //sibreescritura to string Turno
    @Override
    public String toString() {
        return "Turno{" +
            "id=" + id +
            ", fecha=" + fecha +
            ", paciente=" + paciente +
            ", odontologo=" + odontologo +
        '}';
    }
}