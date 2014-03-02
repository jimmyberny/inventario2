package com.inventario.aplicacion.catalogos;

import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Editor;
import com.inventario.listener.MonitorListener;
import com.inventario.modelo.Programa;

/**
 *
 * @author Zulma
 */
public class ProgramaEditor extends Editor<Programa> {

	private Programa programa;

	public ProgramaEditor(Aplicacion app, MonitorListener monitor) {
		initComponents();

		monitor.listenTo(jtfNombre);
		monitor.listenTo(jtfVersion);
	}

	@Override
	public void initNoItem() {
		programa = null;
		limpiar();
		setActivo(false);
	}

	@Override
	public void initItem() {
		programa = new Programa();
		limpiar();
		setActivo(true);
	}

	@Override
	public void mostrarItem(Programa item) throws InventarioException {
		this.programa = item;
		mostrar();
		setActivo(true);
	}

	@Override
	public void borrarItem(Programa item) throws InventarioException {
		this.programa = item;
		mostrar();
		setActivo(false);
	}

	private void mostrar() {
		jtfNombre.setText(programa.getNombre());
		jtfVersion.setText(programa.getVersion());
	}

	@Override
	public Programa getItem() throws InventarioException {
		programa.setNombre(jtfNombre.getText());
		programa.setVersion(jtfVersion.getText());
		return programa;
	}

	@Override
	public void setActivo(boolean activo) {
		jtfNombre.setEnabled(activo);
		jtfVersion.setEnabled(activo);
	}

	@Override
	public void actualizar() {
		// Nada
	}

	@Override
	public void limpiar() {
		jtfNombre.setText(null);
		jtfVersion.setText(null);
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfVersion = new javax.swing.JTextField();

        jLabel1.setText("Nombre");
        jLabel1.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfNombre.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel2.setText("Versión");
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfVersion.setPreferredSize(new java.awt.Dimension(160, 26));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfVersion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(244, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfVersion;
    // End of variables declaration//GEN-END:variables

}
