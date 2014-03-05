package com.inventario.datas;

import com.inventario.bd.Transaccion;
import com.inventario.bd.TransaccionSr;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.AccesoDatos;
import com.inventario.modelo.Area;
import com.inventario.modelo.Empleado;
import com.inventario.modelo.EquipoComputo;
import com.inventario.modelo.EquipoPrograma2;
import com.inventario.modelo.Evento;
import com.inventario.modelo.Programa;
import com.inventario.modelo.TipoEquipo;
import com.inventario.modelo.Usuario;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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

    public List<EquipoComputo> getEquipos(final String activoFijo) throws InventarioException {
        return (List<EquipoComputo>) new Transaccion(factory) {

            @Override
            public Object execInTransaction(Session s, Object... params) throws InventarioException {
                Criteria cr = s.createCriteria(EquipoComputo.class)
                        .setFetchMode("empleado", FetchMode.JOIN);
                if (activoFijo != null) {
                    cr.add(Restrictions.ilike("activoFijo", activoFijo, MatchMode.ANYWHERE));
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

    public List<Evento> getEventosEditables() throws InventarioException {
        return (List<Evento>) new Transaccion(factory) {

            @Override
            public Object execInTransaction(Session s, Object... params) throws InventarioException {
                return s.createCriteria(Evento.class)
                        .add(Restrictions.isNull("fechaRealizado"))
                        .list();
            }
        }.exec();
    }

    public List<Evento> getPendientes(final Date inicio, final Date fin) throws InventarioException {
        return (List<Evento>) new Transaccion(factory) {

            @Override
            public Object execInTransaction(Session s, Object... params) throws InventarioException {
                Criteria cr = s.createCriteria(Evento.class)
                        .add(Restrictions.isNull("fechaRealizado"));
                cr.createCriteria("equipo").setFetchMode("empleado", FetchMode.JOIN);

                if (inicio != null) {
                    cr.add(Restrictions.ge("fecha", inicio));
                }
                if (fin != null) {
                    cr.add(Restrictions.lt("fecha", fin));
                }

                return cr.list();
            }
        }.exec();
    }

    public void guardarReporte(final Evento evento) throws InventarioException {
        new TransaccionSr(factory) {

            @Override
            public void execInTransaction(Session s, Object... params) throws InventarioException {
                s.update(evento);
            }
        }.exec();
    }
    
    public List<EquipoPrograma2> getVigenciasPorExpirar(final Date referencia) throws InventarioException {
        return (List<EquipoPrograma2>) new Transaccion(factory) {
            
            @Override
            public Object execInTransaction(Session s, Object... params) throws InventarioException {
                return s.createCriteria(EquipoPrograma2.class)
                        .setFetchMode("equipo", FetchMode.JOIN)
                        .setFetchMode("programa", FetchMode.JOIN)
                        .add(Restrictions.le("vigencia", referencia))
                        .add(Restrictions.eq("estado", EquipoPrograma2.NORMAL))
                        .list();
            }
        }.exec();
    }
    
    public void guardarNotificaciones(final List<EquipoPrograma2> alertas, final List<Evento> notificaciones) throws InventarioException {
        new TransaccionSr(factory) {
            
            @Override
            public void execInTransaction(Session s, Object... params) throws InventarioException {
                for (EquipoPrograma2 ep : alertas) {
                    s.update(ep);
                }
                for (Evento ev : notificaciones) {
                    s.save(ev);
                }
            }
        }.exec();
    }
}
