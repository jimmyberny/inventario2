package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.gui.CatalogoPanel;
import com.inventario.bd.ListaProvider;
import com.inventario.bd.Saver;
import com.inventario.interfaces.DataProvider;
import com.inventario.interfaces.Editor;
import com.inventario.modelo.EquipoComputo;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;

/**
 *
 * @author Enrique
 */
public class EquipoPanel extends CatalogoPanel<EquipoComputo> {

	private EquipoEditor editor;

	@Override
	protected void inicializar() {
		editor = new EquipoEditor(app, monitor);
	}

	@Override
	protected Editor<EquipoComputo> getEditor() {
		return editor;
	}

	@Override
	protected Saver<EquipoComputo> getSaver() {
		return new Saver<>(app);
	}

	@Override
	protected DataProvider<EquipoComputo> getProvider() {
		return new ListaProvider<EquipoComputo>(app) {

			@Override
			public Class getClase() {
				return EquipoComputo.class;
			}

			@Override
			protected void decorate(Criteria cr) {
				cr.setFetchMode("programas", FetchMode.JOIN);
			}

		};
	}

	@Override
	public String getTitulo() {
		return "Equipos de compúto";
	}

}
