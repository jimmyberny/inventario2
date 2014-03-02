package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.aplicacion.Configuracion;
import com.inventario.aplicacion.InventarioApp;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.AccesoDatos;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Vista;
import com.inventario.modelo.Usuario;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Zulma
 */
public class Principal extends javax.swing.JFrame implements Aplicacion, ActionListener {

	public static final Logger log = LoggerFactory.getLogger(Principal.class);
	private Map<String, Vista> vistas;
	private Map<String, AccesoDatos> datos;

	private Usuario usuario;
	private Vista vistaActual = null;
	private final CardLayout cards;
	//
	private Configuracion conf;
	private SessionFactory hsFactory;

	public Principal(Configuracion conf, SessionFactory hsFactory) {
		initComponents();
		this.conf = conf;
		this.hsFactory = hsFactory;

		vistas = new HashMap<>(20);
		datos = new HashMap<>(5);
		cards = (CardLayout) jpContenido.getLayout();

		// Ocultar el menu
		jmbMenu.setVisible(false);
		jpEncabezado.setVisible(false);

		//
		jmiAreas.setActionCommand(InventarioApp.AREAS);
		jmiAreas.addActionListener(this);
		jmiEmpleados.setActionCommand(InventarioApp.EMPLEADOS);
		jmiEmpleados.addActionListener(this);
		jmiUsuarios.setActionCommand(InventarioApp.USUARIOS);
		jmiUsuarios.addActionListener(this);
		jmiProgramas.setActionCommand(InventarioApp.PROGRAMAS);
		jmiProgramas.addActionListener(this);
		jmiTipos.setActionCommand(InventarioApp.TIPOS_EQUIPO);
		jmiTipos.addActionListener(this);
		jmiEquipos.setActionCommand(InventarioApp.EQUIPOS);
		jmiEquipos.addActionListener(this);

		// Primera pantalla, el login
		mostrarTarea(InventarioApp.LOGIN);
	}

	@Override
	public final void mostrarTarea(String tarea) {
		if (vistaActual != null
				&& (!vistaActual.ocultar() || vistaActual.getClass().getCanonicalName().equals(tarea))) {
			return; // No se puede cerrar la vista actual
		}

		Vista vista = vistas.get(tarea);
		if (vista == null) {
			try {
				vista = (Vista) Class.forName(tarea).newInstance();
				vista.inicializar(this);
				vistas.put(tarea, vista); // Agregar a la cache
				jpContenido.add(vista.getVista(), tarea); // Agregar al panel
			} catch (InstantiationException |
					IllegalAccessException |
					ClassNotFoundException |
					InventarioException ex) {
				log.error(ex.getMessage(), ex);
			}
		}
		// Intentando mostrar
		if (vista != null) {
			try {
				// 
				vista.activar();
				jlbTitulo.setText(String.format("«%s»", vista.getTitulo()));
				cards.show(jpContenido, tarea);
			} catch (InventarioException ex) {
				log.error(ex.getMessage(), ex);
				new AppMensaje(ex).mostrar(this);
				// Mostrar un dialogo para decir que no se pudo mostrar la vista
			}
		}
	}

	@Override
	public AccesoDatos getDatos(String clase) {
		AccesoDatos ad = datos.get(clase);
		if (ad == null) {
			try {
				ad = (AccesoDatos) Class.forName(clase).newInstance();
				ad.init(hsFactory);
				datos.put(clase, ad);
			} catch (ClassNotFoundException |
					InstantiationException |
					IllegalAccessException ex) {
				log.error(ex.getMessage(), ex);
			}
		}
		return ad;
	}

	@Override
	public void ingresar(Usuario usuario) {
		this.usuario = usuario;
		jpEncabezado.setVisible(true);
		jmbMenu.setVisible(true);
		jlUsuario.setText(usuario.getUsuario());

		// Tarea inicial
		mostrarTarea(InventarioApp.INICIO);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mostrarTarea(e.getActionCommand()); // Ok! n_n
	}

	@Override
	public Usuario getUsuario() {
		return usuario; // Ok, usuario que ingreso a la aplicación
	}

	@Override
	public SessionFactory getSessionFactory() {
		return hsFactory;
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpContenido = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpEncabezado = new javax.swing.JPanel();
        jlbTitulo = new javax.swing.JLabel();
        jlUsuario = new javax.swing.JLabel();
        jmbMenu = new javax.swing.JMenuBar();
        jmAdmin = new javax.swing.JMenu();
        jmiAreas = new javax.swing.JMenuItem();
        jmiEmpleados = new javax.swing.JMenuItem();
        jmiUsuarios = new javax.swing.JMenuItem();
        jmiProgramas = new javax.swing.JMenuItem();
        jmiTipos = new javax.swing.JMenuItem();
        jmiEquipos = new javax.swing.JMenuItem();
        jmAcerca = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventario");
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setPreferredSize(new java.awt.Dimension(840, 680));
        setState(javax.swing.JFrame.MAXIMIZED_BOTH);

        jpContenido.setLayout(new java.awt.CardLayout());

        jLabel1.setText("jLabel1");
        jpContenido.add(jLabel1, "card2");

        getContentPane().add(jpContenido, java.awt.BorderLayout.CENTER);

        jpEncabezado.setPreferredSize(new java.awt.Dimension(100, 40));
        jpEncabezado.setLayout(new java.awt.BorderLayout());

        jlbTitulo.setBackground(java.awt.Color.white);
        jlbTitulo.setFont(jlbTitulo.getFont().deriveFont(jlbTitulo.getFont().getStyle() | java.awt.Font.BOLD, jlbTitulo.getFont().getSize()+5));
        jlbTitulo.setText("<titulo>");
        jlbTitulo.setOpaque(true);
        jpEncabezado.add(jlbTitulo, java.awt.BorderLayout.CENTER);

        jlUsuario.setBackground(java.awt.Color.white);
        jlUsuario.setFont(jlUsuario.getFont().deriveFont((jlUsuario.getFont().getStyle() | java.awt.Font.ITALIC)));
        jlUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlUsuario.setText("<usuario>");
        jlUsuario.setOpaque(true);
        jlUsuario.setPreferredSize(new java.awt.Dimension(200, 15));
        jpEncabezado.add(jlUsuario, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jpEncabezado, java.awt.BorderLayout.PAGE_START);

        jmAdmin.setText("Administración");

        jmiAreas.setText("Areas");
        jmAdmin.add(jmiAreas);

        jmiEmpleados.setText("Empleados");
        jmAdmin.add(jmiEmpleados);

        jmiUsuarios.setText("Usuarios");
        jmAdmin.add(jmiUsuarios);

        jmiProgramas.setText("Programas");
        jmAdmin.add(jmiProgramas);

        jmiTipos.setText("Tipos de equipo");
        jmAdmin.add(jmiTipos);

        jmiEquipos.setText("Equipos");
        jmAdmin.add(jmiEquipos);

        jmbMenu.add(jmAdmin);

        jmAcerca.setText("Acerca de");
        jmbMenu.add(jmAcerca);

        setJMenuBar(jmbMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlUsuario;
    private javax.swing.JLabel jlbTitulo;
    private javax.swing.JMenu jmAcerca;
    private javax.swing.JMenu jmAdmin;
    private javax.swing.JMenuBar jmbMenu;
    private javax.swing.JMenuItem jmiAreas;
    private javax.swing.JMenuItem jmiEmpleados;
    private javax.swing.JMenuItem jmiEquipos;
    private javax.swing.JMenuItem jmiProgramas;
    private javax.swing.JMenuItem jmiTipos;
    private javax.swing.JMenuItem jmiUsuarios;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpEncabezado;
    // End of variables declaration//GEN-END:variables
}
