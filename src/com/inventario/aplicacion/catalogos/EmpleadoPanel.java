package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.gui.CatalogoPanel;
import com.inventario.bd.ListaProvider;
import com.inventario.bd.Saver;
import com.inventario.interfaces.DataProvider;
import com.inventario.interfaces.Editor;
import com.inventario.modelo.Empleado;

/**
 *
 * @author Zulma
 */
public class EmpleadoPanel extends CatalogoPanel<Empleado> {

	private EmpleadoEditor editor;
	
	@Override
	protected void inicializar() {
		editor = new EmpleadoEditor(app, monitor);
	}

	@Override
	protected Editor<Empleado> getEditor() {
		return editor;
	}

	@Override
	protected Saver<Empleado> getSaver() {
		return new Saver<>(app);
	}

	@Override
	protected DataProvider<Empleado> getProvider() {
		return new ListaProvider<Empleado>(app) {

			@Override
			public Class getClase() {
				return Empleado.class;
			}
		};
	}

	@Override
	public String getTitulo() {
		return "Empleados";
	}

}
