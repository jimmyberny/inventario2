package com.inventario.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Zulma
 */
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private EquipoComputo equipo;
    @Column
    private String tipo;
    @Column
    private String nombre;
    @Column
    private String instruccion;
    @Column
    private String observaciones;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fechaRealizado;

    public Evento() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EquipoComputo getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoComputo equipo) {
        this.equipo = equipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaRealizado() {
        return fechaRealizado;
    }

    public void setFechaRealizado(Date fechaRealizado) {
        this.fechaRealizado = fechaRealizado;
    }

}
