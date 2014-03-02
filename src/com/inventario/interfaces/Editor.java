package com.inventario.interfaces;

import com.inventario.error.InventarioException;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Enrique
 * @param <T>
 */
public abstract class Editor<T> extends JPanel {

    public abstract void initNoItem();

    public abstract void initItem();

    public abstract void mostrarItem(T item) throws InventarioException;

    public abstract void borrarItem(T item) throws InventarioException;

    public abstract T getItem() throws InventarioException;

    public abstract void setActivo(boolean activo);

    public abstract void actualizar();

    public abstract void limpiar();

    public JComponent getComponente() {
        return this;
    }
}
