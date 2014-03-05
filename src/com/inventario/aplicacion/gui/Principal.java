package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.aplicacion.Configuracion;
import com.inventario.aplicacion.InventarioApp;
import com.inventario.datas.DatosGeneral;
import com.inventario.error.InventarioException;
import com.inventario.gui.util.DialogoUtil;
import com.inventario.interfaces.AccesoDatos;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Vista;
import com.inventario.modelo.EquipoPrograma2;
import com.inventario.modelo.Evento;
import com.inventario.modelo.Usuario;
import com.inventario.util.DateUtil;
import com.inventario.util.Format;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
    private Map<String, Object> bus;
    private DatosGeneral dGeneral;

	public Principal(Configuracion conf, SessionFactory hsFactory) {
		initComponents();
		this.conf = conf;
		this.hsFactory = hsFactory;
        
        // Gestión particular
        dGeneral = new DatosGeneral();
        dGeneral.init(hsFactory); ///
        
		vistas = new HashMap<>(20);
		datos = new HashMap<>(5);
		cards = (CardLayout) jpContenido.getLayout();
        
        
        // Para intercomunicación entre vistas
        bus = new HashMap<>(10);

		// Ocultar el menu
		jmbMenu.setVisible(false);
		jpEncabezado.setVisible(false);
        
        //
        jmiPreferencias.setActionCommand(InventarioApp.PREFERENCIAS);
        jmiPreferencias.addActionListener(this);

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
		jmiEventos.setActionCommand(InventarioApp.EVENTOS);
		jmiEventos.addActionListener(this);
        jmiInicio.setActionCommand(InventarioApp.INICIO);
        jmiInicio.addActionListener(this);
        jmiConsulta.setActionCommand(InventarioApp.CONSULTA);
        jmiConsulta.addActionListener(this);
	}
    
    public void start() {
        
        // Para generar eventos automaticamente basados en las vigencia del software
        // El proceso depende de la configuración, por defecto son 5 dias.
        generarAutoeventos();

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
                vistaActual = vista;
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

    public Configuracion getConfiguracion() {
        return conf;
    }

    @Override
    public Map<String, Object> getBus() {
        return bus;
    }
    
    // Procedimiento para generar automaticamente los eventos
    private void generarAutoeventos() {
        try {
            int anticipación = Format.INTEGER.parse(conf.getProperty(InventarioApp.KC_DIAS_ALERTA), 5);
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, anticipación);
            
            List<EquipoPrograma2> vigenciasPorExpirar = dGeneral.getVigenciasPorExpirar(DateUtil.getEndOfDay(c.getTime()));
            List<Evento> eventos = new ArrayList<>(vigenciasPorExpirar.size());
            for (EquipoPrograma2 vpe : vigenciasPorExpirar) {
                Evento evt = new Evento();
                evt.setEquipo(vpe.getEquipo());
                evt.setTipo(InventarioApp.EXT_LICENCIA.getId());
                String cad = String.format("La vigencia de %s expira el %s", 
                        Format.OBJECT.format(vpe.getPrograma()),
                        Format.DATE.format(vpe.getVigencia()));
                evt.setNombre(cad.substring(0, 40));
                evt.setInstruccion(cad);
                evt.setObservaciones("");
                evt.setFecha(vpe.getVigencia()); // A lo más la fecha en que expira
                
                eventos.add(evt);
                vpe.setEstado(EquipoPrograma2.NOTIFICADO);
            }
            
            // Intentar guardar los eventos.
            dGeneral.guardarNotificaciones(vigenciasPorExpirar, eventos);
        } catch (InventarioException ex) {
            new AppMensaje("El proceso de generación de eventos automaticos falló.", ex).mostrar(this);
        }
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
        jmArchivo = new javax.swing.JMenu();
        jmiPreferencias = new javax.swing.JMenuItem();
        jmiCerrar = new javax.swing.JMenuItem();
        jmiSalir = new javax.swing.JMenuItem();
        jmAplicacion = new javax.swing.JMenu();
        jmiInicio = new javax.swing.JMenuItem();
        jmiEventos = new javax.swing.JMenuItem();
        jmiConsulta = new javax.swing.JMenuItem();
        jmAdmin = new javax.swing.JMenu();
        jmiAreas = new javax.swing.JMenuItem();
        jmiEmpleados = new javax.swing.JMenuItem();
        jmiUsuarios = new javax.swing.JMenuItem();
        jmiProgramas = new javax.swing.JMenuItem();
        jmiTipos = new javax.swing.JMenuItem();
        jmiEquipos = new javax.swing.JMenuItem();
        jmAcerca = new javax.swing.JMenu();
        jmiAcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventario");
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setState(javax.swing.JFrame.MAXIMIZED_BOTH);

        jpContenido.setLayout(new java.awt.CardLayout());

        jLabel1.setText("jLabel1");
        jpContenido.add(jLabel1, "card2");

        getContentPane().add(jpContenido, java.awt.BorderLayout.CENTER);

        jpEncabezado.setPreferredSize(new java.awt.Dimension(100, 40));
        jpEncabezado.setLayout(new java.awt.BorderLayout());

        jlbTitulo.setBackground(java.awt.Color.white);
        jlbTitulo.setFont(jlbTitulo.getFont().deriveFont(jlbTitulo.getFont().getStyle() | java.awt.Font.BOLD, jlbTitulo.getFont().getSize()+7));
        jlbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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

        jmArchivo.setText("Archivo");

        jmiPreferencias.setText("Preferencias");
        jmArchivo.add(jmiPreferencias);

        jmiCerrar.setText("Cerrar sesión");
        jmiCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCerrarActionPerformed(evt);
            }
        });
        jmArchivo.add(jmiCerrar);

        jmiSalir.setText("Salir");
        jmiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalirActionPerformed(evt);
            }
        });
        jmArchivo.add(jmiSalir);

        jmbMenu.add(jmArchivo);

        jmAplicacion.setText("Aplicación");

        jmiInicio.setText("Inicio");
        jmAplicacion.add(jmiInicio);

        jmiEventos.setText("Eventos");
        jmAplicacion.add(jmiEventos);

        jmiConsulta.setText("Consulta");
        jmAplicacion.add(jmiConsulta);

        jmbMenu.add(jmAplicacion);

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

        jmAcerca.setText("Ayuda");

        jmiAcercaDe.setText("Acerca de");
        jmiAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAcercaDeActionPerformed(evt);
            }
        });
        jmAcerca.add(jmiAcercaDe);

        jmbMenu.add(jmAcerca);

        setJMenuBar(jmbMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalirActionPerformed
        if (vistaActual != null && vistaActual.ocultar()
                && DialogoUtil.yes(this.getRootPane(), "¿Esta seguro que desea salir?")) {
            System.exit(0);
		}
    }//GEN-LAST:event_jmiSalirActionPerformed

    private void jmiCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCerrarActionPerformed
        if (vistaActual != null && vistaActual.ocultar()
                && DialogoUtil.yes(this.getRootPane(), "¿Esta seguro que desea cerrar sesión?")) {
            jmbMenu.setVisible(false); // Sin menu
            jpEncabezado.setVisible(false); // Sin titulo
            usuario = null; // Sin usuario
            bus.clear(); // Comunicacion reset
            vistas.clear(); // Vistas reset
            
            mostrarTarea(InventarioApp.LOGIN);
		}
    }//GEN-LAST:event_jmiCerrarActionPerformed

    private void jmiAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAcercaDeActionPerformed
        AcercaDe.mostrar(this);
    }//GEN-LAST:event_jmiAcercaDeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlUsuario;
    private javax.swing.JLabel jlbTitulo;
    private javax.swing.JMenu jmAcerca;
    private javax.swing.JMenu jmAdmin;
    private javax.swing.JMenu jmAplicacion;
    private javax.swing.JMenu jmArchivo;
    private javax.swing.JMenuBar jmbMenu;
    private javax.swing.JMenuItem jmiAcercaDe;
    private javax.swing.JMenuItem jmiAreas;
    private javax.swing.JMenuItem jmiCerrar;
    private javax.swing.JMenuItem jmiConsulta;
    private javax.swing.JMenuItem jmiEmpleados;
    private javax.swing.JMenuItem jmiEquipos;
    private javax.swing.JMenuItem jmiEventos;
    private javax.swing.JMenuItem jmiInicio;
    private javax.swing.JMenuItem jmiPreferencias;
    private javax.swing.JMenuItem jmiProgramas;
    private javax.swing.JMenuItem jmiSalir;
    private javax.swing.JMenuItem jmiTipos;
    private javax.swing.JMenuItem jmiUsuarios;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpEncabezado;
    // End of variables declaration//GEN-END:variables
}
