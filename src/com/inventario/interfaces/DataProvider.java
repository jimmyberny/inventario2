package com.inventario.interfaces;

import com.inventario.error.InventarioException;
import java.util.List;

/**
 *
 * @author Enrique
 * @param <T>
 */
public interface DataProvider<T> {

    public List<T> cargar() throws InventarioException;
}
