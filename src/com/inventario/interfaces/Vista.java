package com.inventario.interfaces;

import com.inventario.error.InventarioException;
import javax.swing.JComponent;

/**
 *
 * @author Zulma
 */
public interface Vista {

    /**
     * Confiere acceso a los ajustes generales de la aplicación, así como
     * configura la pantalla para mostrarse por primera vez.
     *
     * @param app La aplicación principal.
     * @throws InventarioException Si no puede configurarse la pantalla.
     */
    public void inicializar(Aplicacion app) throws InventarioException;

    public JComponent getVista();

    public String getTitulo();

    public void activar() throws InventarioException;

    public boolean ocultar();
}
