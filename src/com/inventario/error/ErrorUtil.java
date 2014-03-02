package com.inventario.error;

import com.inventario.util.AppInt;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class ErrorUtil {

	private StringBuilder errores;

	public ErrorUtil() {
	}

	public void addError(String mensaje) {
		if (errores == null) {
			errores = new StringBuilder(256);
		}
		errores.append(String.format("* %s \n", mensaje));
	}

	public void siNull(Object value, String mensaje) {
		if (value == null) {
			addError(mensaje);
		}
	}

	public void siVacio(String val, String mensaje) {
		if (val == null || val.isEmpty()) {
			addError(mensaje);
		}
	}

	public boolean hayError() {
		return errores != null;
	}

	public String getError() {
		return errores.toString();
	}

	public void notificar(String titulo) throws InventarioException {
		if (hayError()) {
			InventarioException ie = new InventarioException(getError());
			throw new InventarioException(titulo, ie);
		}
	}
}
