package com.inventory.test;

import com.inventario.aplicacion.Configuracion;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class ConfiguracionTest {

    @Test
    public void crear(){
        Configuracion cfg = new Configuracion();
        Assert.assertNotNull(cfg);
    }
}
