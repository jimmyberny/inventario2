/*
 *  inventario 
 */
package com.inventario.modelo;

/**
 *
 * @author None
 */
public class Mensaje {

    private String destinatario;
    private String asunto;
    private String mensaje;

    public Mensaje() {
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
