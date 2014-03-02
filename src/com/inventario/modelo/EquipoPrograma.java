package com.inventario.modelo;

import com.inventario.util.Format;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
@Entity
@Table(name = "equipo_programa")
public class EquipoPrograma implements Serializable {

	private static final long serialVersionUID = 11l;

	@EmbeddedId
	private EquipoProgramaId id;

	@MapsId("idPrograma")
	@ManyToOne
	@JoinColumn(name = "id_programa")
	private Programa programa;
	@MapsId("idEquipo")
	@ManyToOne
	@JoinColumn(name = "id_equipo")
	private EquipoComputo equipo;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date vigencia;

	public EquipoPrograma() {
	}

	public EquipoPrograma(EquipoComputo equipo) {
		id = new EquipoProgramaId();
		id.setIdEquipo(equipo.getId());
		// 
		this.equipo = equipo;
	}

	public EquipoProgramaId getId() {
		return id;
	}

	public void setId(EquipoProgramaId id) {
		this.id = id;
	}

	public Date getVigencia() {
		return vigencia;
	}

	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public EquipoComputo getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoComputo equipo) {
		this.equipo = equipo;
	}

	@Override
	public String toString() {
		return String.format("%s (%s)", programa, Format.DATE.format(vigencia));
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 19 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final EquipoPrograma other = (EquipoPrograma) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}
	

}
