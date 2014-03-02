package com.inventory.test;

import com.inventario.modelo.Area;
import com.inventario.modelo.Empleado;
import com.inventario.modelo.EquipoPrograma;
import com.inventario.modelo.ItemFecha;
import com.inventario.modelo.Usuario;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import junit.framework.Assert;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author José Bernardo Goméz-Andrade
 */
public class HibernateLoader {

	public static final Logger log = LoggerFactory.getLogger(HibernateLoader.class);

	public void test() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Assert.assertNotNull(sf);

		Session cs = sf.getCurrentSession();
		Assert.assertNotNull(cs);

		cs.beginTransaction();
		Criteria cr = cs.createCriteria(Area.class);
		List<Area> areas = cr.list();
		Assert.assertNotNull(areas);

		for (Area a : areas) {
			log.info("{} -> {}", a.getId(), a.getNombre());
			Empleado e = new Empleado();
			e.setNombre("Armando");
			e.setPaterno("Gomez");
			e.setMaterno("Andrade");
			e.setClave(11111);
			e.setArea(a);
			cs.save(e);
		}
		cs.getTransaction().commit();
	}

	public void test2() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Assert.assertNotNull(sf);

		Session s = sf.getCurrentSession();
		s.beginTransaction();
		List<Empleado> es = s.createQuery("from Empleado as e join fetch e.area").list();
		for (Empleado e : es) {
			log.info("{} -> {} at {}", e.getId(), e.getNombre(), e.getArea());
			Usuario u = new Usuario();
			u.setUsuario("jimmy");
			u.setContrasena("james");
			u.setEmpleado(e);

			s.save(u);
		}

		s.getTransaction().commit();
	}

	// @Test
	public void test3() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Assert.assertNotNull(sf);

		Session s = sf.getCurrentSession();
		s.beginTransaction();
		List<Usuario> us = s.createQuery("from Usuario as u join fetch u.empleado").list();
		for (Usuario u : us) {
			log.info("{} -> {}", u.getUsuario(), u.getEmpleado().getNombre());
		}

		s.getTransaction().commit();
	}

	// @Test
	public void test4() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Assert.assertNotNull(sf);

		Session s = sf.getCurrentSession();
		s.beginTransaction();
		Usuario smp = new Usuario();
		smp.setUsuario("noob");
		smp.setContrasena("supernoob");
		Empleado emp = new Empleado();
		emp.setNombre("Test");
		emp.setPaterno("Paterno");
		emp.setMaterno("Materno");
		emp.setClave(1324);
		smp.setEmpleado(emp);

		Area area = (Area) s.createQuery("from Area").setMaxResults(1).uniqueResult();
		emp.setArea(area);

		// Guardar
		s.save(smp);

		s.getTransaction().commit();
	}

	// @Test
	public void test5() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();

		String[] gpos = new String[]{"EEE", "AAA", "BBB", "DDD", "CCC"};
		Calendar ci = Calendar.getInstance();
		Random rg = new Random();

		for (int i = 0; i < 50; i++) {
			ItemFecha ift = new ItemFecha();
			ift.setGrupo(gpos[rg.nextInt(gpos.length)]);
			ci.setTimeInMillis(ci.getTimeInMillis() - rg.nextInt(Integer.MAX_VALUE));
			ift.setFecha(ci.getTime());

			s.save(ift);
		}
		DetachedCriteria dc = DetachedCriteria.forClass(ItemFecha.class);
		dc.setProjection(Projections.max("fecha"));

//        List<ItemFecha> items = s.createCriteria(ItemFecha.class)
//                .add(Subqueries.eq("fecha", dc))
//                .list();
		List<Object[]> items = s.createQuery("from ItemFecha as itf where itf.id = "
				+ "(select iif.id from ItemFecha ass iif where iif.)"
				+ "group by").list();

		for (Object[] arr : items) {
			for (Object obj : arr) {
				log.info("{}", obj);
			}
		}

		s.getTransaction().commit();
	}

	@Test
	public void test6() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		
		
		List<EquipoPrograma> eps = s.createCriteria(EquipoPrograma.class).list();
		s.getTransaction().commit();
	}
}
