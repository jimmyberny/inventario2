package com.inventario.error;

/**
 *
 * @author Zulma
 */
public class InventarioException extends Exception {

    public InventarioException(String message) {
        super(message);
    }

    public InventarioException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventarioException(Throwable cause) {
        super(cause);
    }

}
