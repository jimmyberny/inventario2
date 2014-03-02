package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.gui.CatalogoPanel;
import com.inventario.bd.ListaProvider;
import com.inventario.bd.Saver;
import com.inventario.interfaces.DataProvider;
import com.inventario.interfaces.Editor;
import com.inventario.modelo.Area;

/**
 *
 * @author Zulma
 */
public class AreaPanel extends CatalogoPanel<Area> {

    private AreaEditor editor;

    @Override
    protected void inicializar() {
        editor = new AreaEditor(monitor);
    }

    @Override
    protected Editor<Area> getEditor() {
        return editor;
    }

    @Override
    protected Saver<Area> getSaver() {
        return new Saver<>(app);
    }

    @Override
    protected DataProvider<Area> getProvider() {
        return new ListaProvider<Area>(app) {

            @Override
            public Class getClase() {
                return Area.class;
            }
        };
    }

    @Override
    public String getTitulo() {
        return "Áreas";
    }

}
