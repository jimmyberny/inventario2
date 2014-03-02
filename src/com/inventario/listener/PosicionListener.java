package com.inventario.listener;

import java.util.EventListener;

/**
 *
 * @author Enrique
 */
public interface PosicionListener extends EventListener{

    public void actualizarPosicion(int pos, int total);
}
