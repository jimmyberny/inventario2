package com.inventario.bd;

import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import org.hibernate.Session;

/**
 *
 * @author Enrique
 * @param <T>
 */
public class Saver<T> {

	private Transaccion registrar;
	private Transaccion modificar;
	private Transaccion borrar;

	public Saver(Aplicacion app) {
		registrar = new Transaccion(app.getSessionFactory()) {

			@Override
			public Object execInTransaction(Session s, Object... params) throws InventarioException {
				s.save(params[0]);
				return true;
			}
		};

		modificar = new Transaccion(app.getSessionFactory()) {

			@Override
			public Object execInTransaction(Session s, Object... params) throws InventarioException {
				s.update(params[0]);
				return true;
			}
		};

		borrar = new Transaccion(app.getSessionFactory()) {

			@Override
			public Object execInTransaction(Session s, Object... params) throws InventarioException {
				s.delete(params[0]);
				return true;
			}
		};
	}

	public boolean registrar(T item) throws InventarioException {
		return (boolean) registrar.exec(item);
	}

	public boolean modificar(T item) throws InventarioException {
		return (boolean) modificar.exec(item);
	}

	public boolean borrar(T item) throws InventarioException {
		return (boolean) borrar.exec(item);
	}

	public boolean puedeRegistrar() {
		return registrar != null;
	}

	public boolean puedeModificar() {
		return modificar != null;
	}

	public boolean puedeBorrar() {
		return borrar != null;
	}
}
