package com.inventario.datas;

import com.inventario.bd.Transaccion;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.AccesoDatos;
import com.inventario.modelo.Area;
import com.inventario.modelo.Empleado;
import com.inventario.modelo.Programa;
import com.inventario.modelo.TipoEquipo;
import com.inventario.modelo.Usuario;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Zulma
 */
public class DatosGeneral implements AccesoDatos {

	private SessionFactory factory;

	@Override
	public void init(SessionFactory factory) {
		this.factory = factory;
	}

	public Usuario getUsuario(final String usuario, final String contra) throws InventarioException {
		return (Usuario) new Transaccion(factory) {

			@Override
			public Object execInTransaction(Session s, Object... params) throws InventarioException {
				return s.createCriteria(Usuario.class)
						.add(Restrictions.eq("usuario", usuario))
						.add(Restrictions.eq("contrasena", contra))
						.uniqueResult();
			}
		}.exec();
	}

	public List<Area> getAreas(final String nombre) throws InventarioException {
		return (List<Area>) new Transaccion(factory) {

			@Override
			public Object execInTransaction(Session s, Object... params) throws InventarioException {
				Criteria cr = s.createCriteria(Area.class);
				if (nombre != null && !nombre.trim().isEmpty()) {
					cr.add(Restrictions.ilike("nombre", nombre, MatchMode.ANYWHERE));
				}
				return cr.list();
			}
		}.exec();
	}

	public List<Empleado> getEmpleados(final String nombre) throws InventarioException {
		return (List<Empleado>) new Transaccion(factory) {

			@Override
			public Object execInTransaction(Session s, Object... params) throws InventarioException {
				Criteria cr = s.createCriteria(Empleado.class);
				if (nombre != null && !nombre.trim().isEmpty()) {
					cr.add(Restrictions.ilike("nombre", nombre, MatchMode.ANYWHERE));
				}
				return cr.list();
			}
		}.exec();
	}

	public List<TipoEquipo> getTipos(final String nombre) throws InventarioException {
		return (List<TipoEquipo>) new Transaccion(factory) {

			@Override
			public Object execInTransaction(Session s, Object... params) throws InventarioException {
				Criteria cr = s.createCriteria(TipoEquipo.class);
				if (nombre != null && !nombre.trim().isEmpty()) {
					cr.add(Restrictions.ilike("nombre", nombre, MatchMode.ANYWHERE));
				}
				return cr.list();
			}
		}.exec();
	}
	
	public List<Programa> getProgramas(final String nombre) throws InventarioException {
		return (List<Programa>) new Transaccion(factory) {
			
			@Override
			public Object execInTransaction(Session s, Object... params) throws InventarioException {
				Criteria cr = s.createCriteria(Programa.class);
				if (nombre != null && !nombre.trim().isEmpty()) {
					cr.add(Restrictions.ilike("nombre", nombre, MatchMode.ANYWHERE));
				}
				return cr.list();
			}
		}.exec();
	}

}
