package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.gui.CatalogoPanel;
import com.inventario.bd.ListaProvider;
import com.inventario.bd.Saver;
import com.inventario.interfaces.DataProvider;
import com.inventario.interfaces.Editor;
import com.inventario.modelo.Usuario;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class UsuarioPanel extends CatalogoPanel<Usuario> {

    private UsuarioEditor editor;

    @Override
    protected void inicializar() {
        editor = new UsuarioEditor(app, monitor);
    }

    @Override
    protected Editor<Usuario> getEditor() {
        return editor;
    }

    @Override
    protected Saver<Usuario> getSaver() {
        return new Saver<>(app);
    }

    @Override
    protected DataProvider<Usuario> getProvider() {
        return new ListaProvider<Usuario>(app) {

            @Override
            public Class getClase() {
                return Usuario.class;
            }
        };
    }

    @Override
    public String getTitulo() {
        return "Usuarios";
    }

}
