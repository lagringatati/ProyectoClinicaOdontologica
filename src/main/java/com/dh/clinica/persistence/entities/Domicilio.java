package com.dh.clinica.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "domicilios")
public class Domicilio {

    @Id
    @SequenceGenerator(name = "domicilioSequence", sequenceName = "domicilioSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domicilioSequence")
    //atributos
    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    //constructores
    public Domicilio() {
    }
    public Domicilio(Integer id, String calle, String numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    //getters y setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    //sobreescritura to string Domicilio
    @Override
    public String toString() {
        return "Domicilio {" +
            "id=" + id +
            ", calle='" + calle + '\'' +
            ", numero='" + numero + '\'' +
            ", localidad='" + localidad + '\'' +
            ", provincia='" + provincia + '\'' +
        '}';
    }
}