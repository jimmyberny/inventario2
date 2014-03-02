package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.buscadores.BuscadorEmpleado;
import com.inventario.aplicacion.buscadores.BuscadorTipo;
import com.inventario.error.ErrorUtil;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Editor;
import com.inventario.listener.MonitorListener;
import com.inventario.modelo.Empleado;
import com.inventario.modelo.EquipoComputo;
import com.inventario.modelo.TipoEquipo;
import com.inventario.util.Format;

/**
 *
 * @author Enrique
 */
public class EquipoEditor extends Editor<EquipoComputo> {

	private Aplicacion app;
	private EquipoComputo equipo;

	private TipoEquipo tipo;
	private Empleado empleado;

	public EquipoEditor(Aplicacion app, MonitorListener monitor) {
		initComponents();

		this.app = app;
		epeProgramas.init(app); // Obligatorio

		monitor.listenTo(jtfSerie);
		monitor.listenTo(jtfActivoFijo);
		monitor.listenTo(jtfMarca);
		monitor.listenTo(jtfModelo);
		monitor.listenTo(jtfTipo);
		monitor.listenTo(jtfEmpleado);
		monitor.listenTo(jcbEstado);
		monitor.listenTo(jtaDescripcion);
		aeCaracteristicas.getModelo().addListDataListener(monitor);
		epeProgramas.getModelo().addListDataListener(monitor);
	}

	@Override
	public void initNoItem() {
		equipo = null;
		limpiar();
		setActivo(false);
	}

	@Override
	public void initItem() {
		equipo = new EquipoComputo();
		limpiar();
		setActivo(true);
	}

	@Override
	public void mostrarItem(EquipoComputo item) throws InventarioException {
		this.equipo = item;
		mostrar();
		setActivo(true);
	}

	@Override
	public void borrarItem(EquipoComputo item) throws InventarioException {
		this.equipo = item;
		mostrar();
		setActivo(false);
	}

	private void mostrar() {
		jtfSerie.setText(equipo.getSerie());
		jtfActivoFijo.setText(equipo.getActivoFijo());
		jtfMarca.setText(equipo.getMarca());
		jtfModelo.setText(equipo.getModelo());
        
		tipo = equipo.getTipo();
		jtfTipo.setText(Format.OBJECT.format(tipo));
        
		empleado = equipo.getEmpleado();
		jtfEmpleado.setText(Format.OBJECT.format(empleado));
		jcbEstado.setSelectedItem(equipo.getEstado()); // Es simple String vs String
		jtaDescripcion.setText(equipo.getDescripcion());
		
		aeCaracteristicas.setData(equipo.getCaracteristicas());
		epeProgramas.setEquipo(equipo);
	}

	@Override
	public EquipoComputo getItem() throws InventarioException {
		// Unas validaciones
		ErrorUtil err = new ErrorUtil();
		err.siVacio(jtfSerie.getText(), "Falta ingresar la serie");
		err.siVacio(jtfActivoFijo.getText(), "Falta ingresar el Activo Fijo");
		err.siVacio(jtfMarca.getText(), "Falta ingresar la marca");
		err.siVacio(jtfModelo.getText(), "Falta ingresar el modelo");
		err.siNull(tipo, "Falta seleccionar el tipo");
		err.siNull(jcbEstado.getSelectedItem(), "Falta seleccionar un estado");
		err.notificar("Faltan datos");
		
		equipo.setSerie(jtfSerie.getText());
		equipo.setActivoFijo(jtfActivoFijo.getText());
		equipo.setMarca(jtfMarca.getText());
		equipo.setModelo(jtfModelo.getText());
		if (equipo.getTipo() != tipo) {
			equipo.setTipo(tipo);
		}
		if (equipo.getEmpleado() != empleado) {
			equipo.setEmpleado(empleado);
		}
		equipo.setEstado(jcbEstado.getSelectedItem().toString());
		equipo.setDescripcion(jtaDescripcion.getText());
		equipo.setCaracteristicas(aeCaracteristicas.getData());
		// epeProgramas por referencia ya debieron haber sido setteados los valores nuevos
		return equipo;
	}

	@Override
	public void setActivo(boolean activo) {
		jtfSerie.setEnabled(activo);
		jtfActivoFijo.setEnabled(activo);
		jtfMarca.setEnabled(activo);
		jtfModelo.setEnabled(activo);
		jbSetTipo.setEnabled(activo); // Tipo
		jbSetEmpleado.setEnabled(activo); // Empleado
		jbRemoveEmpleado.setEnabled(activo); // Empleado
		jcbEstado.setEnabled(activo);
		jtaDescripcion.setEnabled(activo);
		aeCaracteristicas.setEnabled(activo);
		epeProgramas.setEnabled(activo);
	}

	@Override
	public void actualizar() {
		// Nada
	}

	@Override
	public void limpiar() {
		jtfSerie.setText(null);
		jtfActivoFijo.setText(null);
		jtfMarca.setText(null);
		jtfModelo.setText(null);
		jtfTipo.setText(null);
		jtfEmpleado.setText(null);
		jcbEstado.setSelectedIndex(-1); // Sin valor
		jtaDescripcion.setText(null);
		
		aeCaracteristicas.clear();
		epeProgramas.clear(); // Ok, si quieres borrar todos, borralos manualmente :P
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jtfMarca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtfModelo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfSerie = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfTipo = new javax.swing.JTextField();
        jtfActivoFijo = new javax.swing.JTextField();
        jtfEmpleado = new javax.swing.JTextField();
        jbSetTipo = new javax.swing.JButton();
        jbSetEmpleado = new javax.swing.JButton();
        jbRemoveEmpleado = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jcbEstado = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDescripcion = new javax.swing.JTextArea();
        aeCaracteristicas = new com.inventario.aplicacion.gui.AtributosEditor();
        epeProgramas = new com.inventario.aplicacion.catalogos.EquipoProgramaEditor();

        setLayout(new java.awt.BorderLayout());

        jtfMarca.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel1.setText("Serie");
        jLabel1.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfModelo.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel6.setText("Empleado responsable");
        jLabel6.setPreferredSize(new java.awt.Dimension(140, 26));

        jLabel3.setText("Marca");
        jLabel3.setPreferredSize(new java.awt.Dimension(140, 26));

        jLabel4.setText("Modelo");
        jLabel4.setPreferredSize(new java.awt.Dimension(140, 26));

        jLabel2.setText("Activo fijo");
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfSerie.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel5.setText("Tipo");
        jLabel5.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfTipo.setEnabled(false);
        jtfTipo.setPreferredSize(new java.awt.Dimension(160, 26));

        jtfActivoFijo.setPreferredSize(new java.awt.Dimension(160, 26));

        jtfEmpleado.setEnabled(false);
        jtfEmpleado.setPreferredSize(new java.awt.Dimension(160, 26));

        jbSetTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/search.png"))); // NOI18N
        jbSetTipo.setPreferredSize(new java.awt.Dimension(40, 26));
        jbSetTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSetTipoActionPerformed(evt);
            }
        });

        jbSetEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/search.png"))); // NOI18N
        jbSetEmpleado.setPreferredSize(new java.awt.Dimension(40, 26));
        jbSetEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSetEmpleadoActionPerformed(evt);
            }
        });

        jbRemoveEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/trash_can.png"))); // NOI18N
        jbRemoveEmpleado.setPreferredSize(new java.awt.Dimension(40, 26));
        jbRemoveEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoveEmpleadoActionPerformed(evt);
            }
        });

        jLabel7.setText("Estado");
        jLabel7.setToolTipText("Excelente, Bueno, Regular, Malo");
        jLabel7.setPreferredSize(new java.awt.Dimension(140, 26));

        jcbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Excelente", "Bueno", "Regular", "Malo" }));
        jcbEstado.setSelectedIndex(-1);
        jcbEstado.setPreferredSize(new java.awt.Dimension(160, 26));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfSerie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfActivoFijo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSetTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSetEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbRemoveEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfActivoFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbSetTipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSetEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbRemoveEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(10, 10));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(10, 10));

        jtaDescripcion.setColumns(20);
        jtaDescripcion.setRows(5);
        jScrollPane1.setViewportView(jtaDescripcion);

        jTabbedPane1.addTab("Descripción", jScrollPane1);
        jTabbedPane1.addTab("Características", aeCaracteristicas);

        epeProgramas.setPreferredSize(new java.awt.Dimension(10, 10));
        jTabbedPane1.addTab("Programas", epeProgramas);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jbSetTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSetTipoActionPerformed
		BuscadorTipo bt = BuscadorTipo.mostrar(this, app);
		if (bt.isAceptar()) {
			tipo = bt.getItem();
			jtfTipo.setText(tipo.toString());
		}
    }//GEN-LAST:event_jbSetTipoActionPerformed

    private void jbSetEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSetEmpleadoActionPerformed
		BuscadorEmpleado be = BuscadorEmpleado.mostrar(this, app);
		if (be.isAceptar()) {
			empleado = be.getItem();
			jtfEmpleado.setText(empleado.toString());
		}
    }//GEN-LAST:event_jbSetEmpleadoActionPerformed

    private void jbRemoveEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoveEmpleadoActionPerformed
		empleado = null;
		jtfEmpleado.setText(null);
    }//GEN-LAST:event_jbRemoveEmpleadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventario.aplicacion.gui.AtributosEditor aeCaracteristicas;
    private com.inventario.aplicacion.catalogos.EquipoProgramaEditor epeProgramas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbRemoveEmpleado;
    private javax.swing.JButton jbSetEmpleado;
    private javax.swing.JButton jbSetTipo;
    private javax.swing.JComboBox jcbEstado;
    private javax.swing.JTextArea jtaDescripcion;
    private javax.swing.JTextField jtfActivoFijo;
    private javax.swing.JTextField jtfEmpleado;
    private javax.swing.JTextField jtfMarca;
    private javax.swing.JTextField jtfModelo;
    private javax.swing.JTextField jtfSerie;
    private javax.swing.JTextField jtfTipo;
    // End of variables declaration//GEN-END:variables

}
