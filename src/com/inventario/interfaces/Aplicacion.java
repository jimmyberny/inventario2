package com.inventario.interfaces;

import com.inventario.error.InventarioException;
import com.inventario.modelo.Mensaje;
import com.inventario.modelo.Usuario;
import java.util.Map;
import org.hibernate.SessionFactory;

/**
 *
 * @author Zulma
 */
public interface Aplicacion {

    public SessionFactory getSessionFactory();
    
    public Usuario getUsuario();
    
    public void ingresar(Usuario usuario);
    
    public AccesoDatos getDatos(String clase);
    
    public void mostrarTarea(String tarea);
    
    public Map<String, Object> getBus();
    
    public void enviarMensaje(Mensaje mensaje) throws InventarioException; // Esto no es apropiado
}
