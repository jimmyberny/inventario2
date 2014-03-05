/*
 *  inventario | 
 */

package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.InventarioApp;
import com.inventario.aplicacion.gui.CatalogoPanel;
import com.inventario.bd.Saver;
import com.inventario.datas.DatosGeneral;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.DataProvider;
import com.inventario.interfaces.Editor;
import com.inventario.modelo.Evento;
import java.util.List;

/**
 *
 * @author 
 */
public class EventoPanel extends CatalogoPanel<Evento>{

    private DatosGeneral dGeneral;
    
    @Override
    protected void inicializar() {
        dGeneral = (DatosGeneral) app.getDatos(InventarioApp.AD_GENERAL);
    }

    @Override
    protected Editor<Evento> getEditor() {
        return new EventoEditor(app, monitor);
    }

    @Override
    protected Saver<Evento> getSaver() {
        return new Saver<>(app);
    }

    @Override
    protected DataProvider<Evento> getProvider() {
        return new DataProvider<Evento>() {

            @Override
            public List<Evento> cargar() throws InventarioException {
                return dGeneral.getEventosEditables();
            }
        };
    }

    @Override
    public String getTitulo() {
        return "Programar mantenimiento";
    }
    
}
