package com.inventario.bd;

import com.inventario.error.InventarioException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Zulma
 */
public abstract class TransaccionSr {

    private static final Logger log = LoggerFactory.getLogger(TransaccionSr.class);
    
    private SessionFactory f;

	public TransaccionSr(SessionFactory factory) {
		this.f = factory;
	}

	public abstract void execInTransaction(Session s, Object... params) throws InventarioException;

	public void exec(Object... params) throws InventarioException {
		org.hibernate.Transaction tx = null;
		Exception err = null;
		try {
			Session s = f.getCurrentSession();
			if (s.getTransaction() != null && s.getTransaction().isActive()) {
				s.getTransaction().rollback();
			}
			tx = s.beginTransaction();
			execInTransaction(s, params);
			tx.commit();
		} catch (InventarioException | HibernateException ex) {
			err = ex;
		} catch (Exception ex) {
			err = ex;
		} finally {
			if (err != null) {
				log.error(err.getMessage(), err);
				if (tx != null) {
					tx.rollback();
				}
				throw new InventarioException("No se pudo completar la transaccion", err);
			}
		}
	}
}
