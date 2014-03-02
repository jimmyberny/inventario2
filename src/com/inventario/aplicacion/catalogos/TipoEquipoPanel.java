package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.gui.CatalogoPanel;
import com.inventario.bd.ListaProvider;
import com.inventario.bd.Saver;
import com.inventario.interfaces.DataProvider;
import com.inventario.interfaces.Editor;
import com.inventario.modelo.TipoEquipo;

/**
 *
 * @author Zulma
 */
public class TipoEquipoPanel extends CatalogoPanel<TipoEquipo> {

	private TipoEquipoEditor editor;

	@Override
	protected void inicializar() {
		editor = new TipoEquipoEditor(app, monitor);
	}

	@Override
	protected Editor<TipoEquipo> getEditor() {
		return editor;
	}

	@Override
	protected Saver<TipoEquipo> getSaver() {
		return new Saver<>(app);
	}

	@Override
	protected DataProvider<TipoEquipo> getProvider() {
		return new ListaProvider<TipoEquipo>(app) {

			@Override
			public Class getClase() {
				return TipoEquipo.class;
			}
		};
	}

	@Override
	public String getTitulo() {
		return "Tipos de equipo";
	}

}
