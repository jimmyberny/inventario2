package com.inventario.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Zulma
 */
@Entity
@Table(name = "tipo_equipo")
public class TipoEquipo implements Serializable{

    private static final long serialVersionUID = 42L;
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2" )
    private String id;
    @Column
    private String nombre;

    public TipoEquipo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	@Override
	public String toString() {
		return nombre;
	}

}
