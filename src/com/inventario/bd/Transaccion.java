package com.inventario.bd;

import com.inventario.error.InventarioException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Enrique
 */
public abstract class Transaccion {

    private static final Logger log = LoggerFactory.getLogger(Transaccion.class);
    //
    private SessionFactory f;

    public Transaccion(SessionFactory factory) {
        this.f = factory;
    }

    public abstract Object execInTransaction(Session s, Object... params) throws InventarioException;

    public Object exec(Object... params) throws InventarioException {
        org.hibernate.Transaction tx = null;
        Exception err = null;
        Object res = null;
        try {
            Session s = f.getCurrentSession();
            if (s.getTransaction() != null && s.getTransaction().isActive()) {
                s.getTransaction().rollback();
            }
            tx = s.beginTransaction();
            res = execInTransaction(s, params);
            tx.commit();
        } catch (HibernateException ex) {
            err = ex;
        } catch (Exception ex) {
            err = ex;
        } finally {
            if (err != null) {
                log.error(err.getMessage(), err);
                if (tx != null) {
                    tx.rollback();
                }
                throw new InventarioException("No se pudo ejecutar la instruccion", err);
            }
            return res;
        }
    }
}
