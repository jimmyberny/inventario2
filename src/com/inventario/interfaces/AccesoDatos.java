package com.inventario.interfaces;

import org.hibernate.SessionFactory;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public interface AccesoDatos {

    public void init(SessionFactory factory);
}
