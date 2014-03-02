package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.gui.CatalogoPanel;
import com.inventario.bd.ListaProvider;
import com.inventario.bd.Saver;
import com.inventario.interfaces.DataProvider;
import com.inventario.interfaces.Editor;
import com.inventario.modelo.Programa;

/**
 *
 * @author Zulma
 */
public class ProgramaPanel extends CatalogoPanel<Programa>{

	private ProgramaEditor editor;
	
	@Override
	protected void inicializar() {
		editor = new  ProgramaEditor(app, monitor);
	}

	@Override
	protected Editor<Programa> getEditor() {
		return editor;
	}

	@Override
	protected Saver<Programa> getSaver() {
		return new Saver<>(app);
	}

	@Override
	protected DataProvider<Programa> getProvider() {
		return new ListaProvider<Programa>(app) {
			
			@Override
			public Class getClase() {
				return Programa.class;
			}
		};
	}

	@Override
	public String getTitulo() {
		return "Programas";
	}

}
