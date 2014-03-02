package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.aplicacion.buscadores.BuscadorPrograma;
import com.inventario.aplicacion.gui.*;
import com.inventario.aplicacion.buscadores.ModeloLista;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.listener.MonitorListener;
import com.inventario.modelo.EquipoComputo;
import com.inventario.modelo.EquipoPrograma2;
import com.inventario.util.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Zulma
 */
public class EquipoProgramaEditor extends javax.swing.JPanel implements ListSelectionListener {

	public static final Logger log = LoggerFactory.getLogger(AtributosEditor.class);
	public static final String ATRIBUTOS = "atributos";

	private static final int NONE = 0;
	private static final int ADD = 1;
	private static final int CHANGE = 2;
	private static final int DELETE = 3;

	private Aplicacion app;

	private EquipoComputo equipo;
	private ModeloLista<EquipoPrograma2> modelo;
	//
	private int anterior = -1;
	private int index = -1;
	private int estado = NONE;
	private EquipoPrograma2 programa;
	private MonitorListener monitor;
	private boolean ajustando = false;

	public EquipoProgramaEditor() {
		initComponents();

		modelo = new ModeloLista<>();
		jlProgramas.setModel(modelo);

		jlProgramas.addListSelectionListener(this); // n_n

		// Hagamos algo con el monitor
		monitor = new MonitorListener();
		monitor.listenTo(jtfPrograma);
		monitor.listenTo(jcbSetFecha);
		
		monitor.addView(jlMonitor);
	}

	/**
	 * Se debe llamar este método justo despúes de la inicialización del objeto.
	 *
	 * @param app La aplicación.
	 */
	public void init(Aplicacion app) {
		this.app = app;

		jcbSetFecha.setEditor(jtfVigencia, Format.DATE);
		jcbSetFecha.setMinSelectableDate(new Date()); // Right now
		setActivo(false);
	}

	public ModeloLista<EquipoPrograma2> getModelo() {
		return modelo;
	}

	public void clear() {
		modelo.setItems(new ArrayList<EquipoPrograma2>(0)); // Empty
		limpiar();
		setActivo(false);
	}

	public void setEquipo(EquipoComputo equipo) {
		this.equipo = equipo;
		if (this.equipo.getProgramas() == null) {
			this.equipo.setProgramas(new ArrayList<EquipoPrograma2>(0));
		}
		modelo.setItems(equipo.getProgramas());
		setActivo(false);
		limpiar();
		monitor.setDirty(false);
		anterior = -1; // Comenzar de nuevo la navegación
	}

	// La modificación se hará por referencia.
	@Override
	public void setEnabled(boolean enabled) {
		jbGuardar.setEnabled(enabled);
		jbNuevo.setEnabled(enabled);
		jbQuitar.setEnabled(enabled);
	}

	
	
	private void guardarPrograma() throws InventarioException {
		if (programa != null 
				&& monitor.isDirty()) {
			// Validaciones
			if (programa.getPrograma() == null) {
				throw new InventarioException("Falta información del programa");
			}
			programa.setVigencia(jcbSetFecha.getDate()); // La vigencia es opcional
			modelo.refresh(anterior);
		}
	}

	private void setActivo(boolean activo) {
		jbSetPrograma.setEnabled(activo);
		jcbSetFecha.setEnabled(activo);
	}

	private void limpiar() {
		jtfPrograma.setText(null);
		jcbSetFecha.setDate(null);
		monitor.setDirty(false);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting() && !ajustando) {
			index = jlProgramas.getSelectedIndex();
			if (anterior != index) {
				try {
					guardar();
				} catch (InventarioException ex) {
					new AppMensaje(ex).mostrar(this);
				}
			}
			anterior = index;
			if (index != -1) {
				programa = modelo.getElementAt(index);
				if (programa != null) {
					jtfPrograma.setText(programa.getPrograma().toString());
					jcbSetFecha.setDate(programa.getVigencia());
					estado = CHANGE; // Cambiar

					setActivo(true);
					monitor.setDirty(false);
				}
			}
		}
	}

	private void guardar() throws InventarioException {
		ajustando = true;
		if (monitor.isDirty()) {
			switch (estado) {
				case DELETE: {
					modelo.remove(index);
					break;
				}
				case ADD: {
					guardarPrograma();
					modelo.add(programa);
					break;
				}
				case CHANGE: {
					guardarPrograma();
					modelo.refresh(index);
					break;
				}
			}
			monitor.setDirty(false);
		}
		ajustando = false;
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspLista = new javax.swing.JScrollPane();
        jlProgramas = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jlMonitor = new com.inventario.aplicacion.gui.JLabelMonitor();
        jbNuevo = new javax.swing.JButton();
        jbQuitar = new javax.swing.JButton();
        jbGuardar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfPrograma = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfVigencia = new javax.swing.JTextField();
        jbSetPrograma = new javax.swing.JButton();
        jcbSetFecha = new com.inventario.gui.util.JCalendarButton();

        setLayout(new java.awt.BorderLayout());

        jspLista.setPreferredSize(new java.awt.Dimension(220, 139));

        jlProgramas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspLista.setViewportView(jlProgramas);

        add(jspLista, java.awt.BorderLayout.LINE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(10, 40));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 2));

        jlMonitor.setPreferredSize(new java.awt.Dimension(120, 36));
        jPanel2.add(jlMonitor);

        jbNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/add.png"))); // NOI18N
        jbNuevo.setPreferredSize(new java.awt.Dimension(40, 36));
        jbNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNuevoActionPerformed(evt);
            }
        });
        jPanel2.add(jbNuevo);

        jbQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/delete.png"))); // NOI18N
        jbQuitar.setPreferredSize(new java.awt.Dimension(40, 36));
        jbQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQuitarActionPerformed(evt);
            }
        });
        jPanel2.add(jbQuitar);

        jbGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/save-small.png"))); // NOI18N
        jbGuardar.setPreferredSize(new java.awt.Dimension(40, 36));
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(jbGuardar);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jLabel1.setText("Programa");
        jLabel1.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfPrograma.setEnabled(false);
        jtfPrograma.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel2.setText("Vigencia");
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfVigencia.setEnabled(false);
        jtfVigencia.setPreferredSize(new java.awt.Dimension(160, 26));

        jbSetPrograma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/search.png"))); // NOI18N
        jbSetPrograma.setPreferredSize(new java.awt.Dimension(40, 26));
        jbSetPrograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSetProgramaActionPerformed(evt);
            }
        });

        jcbSetFecha.setPreferredSize(new java.awt.Dimension(40, 26));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfPrograma, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfVigencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbSetFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSetPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSetPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcbSetFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(188, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jbNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNuevoActionPerformed
		index = -1;
		jlProgramas.clearSelection();

		programa = new EquipoPrograma2();
		programa.setEquipo(equipo);
		estado = ADD;
		limpiar();
		setActivo(true);
		monitor.setDirty(false);
    }//GEN-LAST:event_jbNuevoActionPerformed

    private void jbQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQuitarActionPerformed
		if (index != -1) {
			estado = DELETE;
			setActivo(false);
			monitor.setDirty(true);
		}
    }//GEN-LAST:event_jbQuitarActionPerformed

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
		try {
			guardar();
		} catch (InventarioException ex) {
			new AppMensaje("No se ha podido guardar", ex).mostrar(this);
		}
    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jbSetProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSetProgramaActionPerformed
		BuscadorPrograma bp = BuscadorPrograma.mostrar(this, app);
		if (bp.isAceptar()) {
			programa.setPrograma(bp.getItem()); // Seguro?
			jtfPrograma.setText(bp.getItem().toString());
		}
    }//GEN-LAST:event_jbSetProgramaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbNuevo;
    private javax.swing.JButton jbQuitar;
    private javax.swing.JButton jbSetPrograma;
    private com.inventario.gui.util.JCalendarButton jcbSetFecha;
    private com.inventario.aplicacion.gui.JLabelMonitor jlMonitor;
    private javax.swing.JList jlProgramas;
    private javax.swing.JScrollPane jspLista;
    private javax.swing.JTextField jtfPrograma;
    private javax.swing.JTextField jtfVigencia;
    // End of variables declaration//GEN-END:variables

}
