package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.buscadores.AreaBuscador;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Editor;
import com.inventario.listener.MonitorListener;
import com.inventario.modelo.Area;
import com.inventario.modelo.Empleado;
import com.inventario.util.Format;

/**
 *
 * @author Zulma
 */
public class EmpleadoEditor extends Editor<Empleado> {

	private Aplicacion app;
	private Empleado empleado;
	//
	private Area area = null;

	public EmpleadoEditor(Aplicacion app, MonitorListener monitor) {
		initComponents();

		this.app = app;
		
		monitor.listenTo(jtfNombre);
		monitor.listenTo(jtfPaterno);
		monitor.listenTo(jtfMaterno);
		monitor.listenTo(jtfClave);
		monitor.listenTo(jtfArea);
	}

	@Override
	public void initNoItem() {
		empleado = null;
		limpiar();
		setActivo(false);
	}

	@Override
	public void initItem() {
		empleado = new Empleado();
		limpiar();
		setActivo(true);
	}

	@Override
	public void mostrarItem(Empleado item) throws InventarioException {
		this.empleado = item;
		mostrar();
		setActivo(true);
	}

	@Override
	public void borrarItem(Empleado item) throws InventarioException {
		this.empleado = item;
		mostrar();
		setActivo(false);
	}

	private void mostrar() {
		jtfNombre.setText(empleado.getNombre());
		jtfPaterno.setText(empleado.getPaterno());
		jtfMaterno.setText(empleado.getMaterno());
		jtfClave.setText(Format.INTEGER.format(empleado.getClave()));
		
		jtfArea.setText(empleado.getArea().toString());
		area = empleado.getArea();
	}

	@Override
	public Empleado getItem() throws InventarioException {
		empleado.setNombre(jtfNombre.getText());
		empleado.setPaterno(jtfPaterno.getText());
		empleado.setMaterno(jtfMaterno.getText());
		empleado.setClave(Format.INTEGER.parse(jtfClave.getText()));
		if (empleado.getArea() != area) { // Si lo modificaron setealo
			empleado.setArea(area);
		}

		return empleado;
	}

	@Override
	public void setActivo(boolean activo) {
		jtfNombre.setEnabled(activo);
		jtfPaterno.setEnabled(activo);
		jtfMaterno.setEnabled(activo);
		jtfClave.setEnabled(activo);
		jbRemoveArea.setEnabled(activo);
		jbSetArea.setEnabled(activo);
	}

	@Override
	public void actualizar() {
		// Nada que hacer
	}

	@Override
	public void limpiar() {
		jtfNombre.setText(null);
		jtfPaterno.setText(null);
		jtfMaterno.setText(null);
		jtfClave.setText(null);
		jtfArea.setText(null);
		area = null;
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfPaterno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfMaterno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfClave = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfArea = new javax.swing.JTextField();
        jbSetArea = new javax.swing.JButton();
        jbRemoveArea = new javax.swing.JButton();

        jLabel2.setText("Nombre");
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfNombre.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel3.setText("Apellido paterno");
        jLabel3.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfPaterno.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel4.setText("Apellido materno");
        jLabel4.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfMaterno.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel5.setText("Clave");
        jLabel5.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfClave.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel6.setText("Área");
        jLabel6.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfArea.setEditable(false);
        jtfArea.setPreferredSize(new java.awt.Dimension(160, 26));

        jbSetArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/search.png"))); // NOI18N
        jbSetArea.setPreferredSize(new java.awt.Dimension(40, 26));
        jbSetArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSetAreaActionPerformed(evt);
            }
        });

        jbRemoveArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/trash_can.png"))); // NOI18N
        jbRemoveArea.setPreferredSize(new java.awt.Dimension(40, 26));
        jbRemoveArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoveAreaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfPaterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfMaterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSetArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbRemoveArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSetArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbRemoveArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(152, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbRemoveAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoveAreaActionPerformed
        area = null;
		jtfArea.setText(null);
    }//GEN-LAST:event_jbRemoveAreaActionPerformed

    private void jbSetAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSetAreaActionPerformed
        AreaBuscador ab = AreaBuscador.mostrar(this, app);
		if (ab.isAceptar()) {
			area = ab.getItem();
			jtfArea.setText(area.toString());
		}
    }//GEN-LAST:event_jbSetAreaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jbRemoveArea;
    private javax.swing.JButton jbSetArea;
    private javax.swing.JTextField jtfArea;
    private javax.swing.JTextField jtfClave;
    private javax.swing.JTextField jtfMaterno;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfPaterno;
    // End of variables declaration//GEN-END:variables

}
