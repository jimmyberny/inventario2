package com.inventario.listener;

import java.util.EventListener;

/**
 *
 * @author Enrique
 */
public interface EstadoListener extends EventListener {

    public void actualizarEstado(int estado);
}
