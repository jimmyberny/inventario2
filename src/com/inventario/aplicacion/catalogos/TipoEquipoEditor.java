package com.inventario.aplicacion.catalogos;

import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Editor;
import com.inventario.listener.MonitorListener;
import com.inventario.modelo.TipoEquipo;

/**
 *
 * @author Zulma
 */
public class TipoEquipoEditor extends Editor<TipoEquipo> {

	private TipoEquipo tipo;

	public TipoEquipoEditor(Aplicacion app, MonitorListener monitor) {
		initComponents();

		monitor.listenTo(jtfNombre);
	}

	@Override
	public void initNoItem() {
		tipo = null;
		limpiar();
		setActivo(false);
	}

	@Override
	public void initItem() {
		tipo = new TipoEquipo();
		limpiar();
		setActivo(true);
	}

	@Override
	public void mostrarItem(TipoEquipo item) throws InventarioException {
		this.tipo = item;
		mostrar();
		setActivo(true);
	}

	@Override
	public void borrarItem(TipoEquipo item) throws InventarioException {
		this.tipo = item;
		mostrar();
		setActivo(false);
	}

	private void mostrar() {
		jtfNombre.setText(tipo.getNombre());
	}

	@Override
	public TipoEquipo getItem() throws InventarioException {
		tipo.setNombre(jtfNombre.getText());
		return tipo;
	}

	@Override
	public void setActivo(boolean activo) {
		jtfNombre.setEnabled(activo);
	}

	@Override
	public void actualizar() {
		// No, no, no
	}

	@Override
	public void limpiar() {
		jtfNombre.setText(null);
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();

        jLabel1.setText("Nombre");
        jLabel1.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfNombre.setPreferredSize(new java.awt.Dimension(160, 26));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(261, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jtfNombre;
    // End of variables declaration//GEN-END:variables

}
