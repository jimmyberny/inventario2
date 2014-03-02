package com.inventario.aplicacion.buscadores;

import com.inventario.error.InventarioException;
import com.inventario.gui.util.DialogKeyEventDistpatcher;
import com.inventario.interfaces.gui.Cancelable;
import com.inventario.modelo.Area;
import java.awt.Dialog;
import java.awt.Frame;
import javax.swing.JDialog;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public abstract class Buscador<T> extends JDialog implements Cancelable{

	protected T item;
	protected ModeloLista<T> modelo;
	protected EstadoDialogo estado;

	public Buscador(Frame parent, boolean modal) {
		super(parent, modal);
		init();
	}
	
	public Buscador(Dialog parent, boolean modal) {
		super(parent, modal);
		init();
	}
	
	private void init() {
		estado = new EstadoDialogo();
		DialogKeyEventDistpatcher.dispatch(this);
	}

	@Override
	public void cancel() {
		estado.cancelar();
		this.dispose();
	}
	
	protected void aceptar(int index) throws InventarioException {
		if (index >= 0) {
			item = modelo.getElementAt(index);
			estado.aceptar();
			this.dispose();
		} else {
			throw new InventarioException("Seleccion no válida");
		}
	}
	
	public T getItem() {
		return item;
	}

	public boolean isAceptar() {
		return estado.isAceptar();
	}

	public boolean isCancelar() {
		return estado.isCancelar();
	}
	
	
}
