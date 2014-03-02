package com.inventario.aplicacion;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Zulma
 */
public class Configuracion {

    public static final Logger log = LoggerFactory.getLogger(Configuracion.class);
    public static String APLICACION = "inventario.cfg";
    private File archivo;
    private Properties props;
    
    public Configuracion() {
        archivo = new File(String.format("%s%s%s", 
                System.getProperty("user.home"),
                System.getProperty("file.separator"),
                APLICACION));
        
        props = new Properties();
        cargarPorDefecto();
        try {
            props.load(new FileReader(archivo));
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            guardar();
        }
    }
    
    private void cargarPorDefecto() {
        props.setProperty("saludos", "cordiales");
    }
    
    private void guardar() {
        try {
            props.store(new FileWriter(archivo), "Configuracion de la aplicación Inventario v1");
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
    
    public Properties getProperties() {
        return props;
    }

    public String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
    
    public String getProperty(String key) {
        return props.getProperty(key);
    }
}
