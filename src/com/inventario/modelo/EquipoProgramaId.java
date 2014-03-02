package com.inventario.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author Zulma
 */
@Embeddable
public class EquipoProgramaId implements Serializable {

	private static final long serialVersionUID = 13L;

	private String idEquipo;
	private String idPrograma;

	public EquipoProgramaId() {
	}

	public String getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(String idPrograma) {
		this.idPrograma = idPrograma;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 89 * hash + Objects.hashCode(this.idEquipo);
		hash = 89 * hash + Objects.hashCode(this.idPrograma);
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
		final EquipoProgramaId other = (EquipoProgramaId) obj;
		if (!Objects.equals(this.idEquipo, other.idEquipo)) {
			return false;
		}
		if (!Objects.equals(this.idPrograma, other.idPrograma)) {
			return false;
		}
		return true;
	}

	
}
