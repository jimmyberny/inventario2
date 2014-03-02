package com.inventario.aplicacion.gui;

import java.util.Map;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class Atributo {

	private String nombre;
	private String valor;

	public Atributo() {
	}

	public Atributo(Map.Entry<Object, Object> entry) {
		nombre = entry.getKey().toString();
		valor = entry.getValue().toString();
	}
	
	public Atributo(String nombre, String valor) {
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return String.format("%s: %s", 
				nombre, 
				valor.length() > 15 ? valor.substring(0, 12) + "..." : valor);
	}

}
