package com.inventario.aplicacion.buscadores;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.aplicacion.InventarioApp;
import com.inventario.datas.DatosGeneral;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.modelo.TipoEquipo;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Buscador de tipos de equipo.
 * <p>
 * Ok.
 *
 * @author Enrique
 */
public class BuscadorTipo extends Buscador<TipoEquipo> {
	
	private static final Logger log = LoggerFactory.getLogger(BuscadorTipo.class);
	
	private DatosGeneral dGeneral;
	
	public BuscadorTipo(Frame parent, boolean modal) {
		super(parent, modal);
	}
	
	public BuscadorTipo(Dialog parent, boolean modal) {
		super(parent, modal);
	}
	
	public static BuscadorTipo mostrar(Component padre, Aplicacion app) {
		BuscadorTipo bt;
		Window mw = SwingUtilities.getWindowAncestor(padre);
		if (mw instanceof Frame) {
			bt = new BuscadorTipo((Frame) mw, true);
		} else {
			bt = new BuscadorTipo((Dialog) mw, true);
		}
		bt.initComponents();
		bt.init(app);
		bt.setLocationRelativeTo(padre);
		bt.setVisible(true);
		return bt;
	}
	
	private void init(Aplicacion app) {
		dGeneral = (DatosGeneral) app.getDatos(InventarioApp.AD_GENERAL);

		// Settear
		modelo = new ModeloLista<>();
		jlTipos.setModel(modelo);
	}
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jbBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jbCancelar = new javax.swing.JButton();
        jbAceptar = new javax.swing.JButton();
        jspItems = new javax.swing.JScrollPane();
        jlTipos = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Buscador de Áreas");

        jPanel3.setPreferredSize(new java.awt.Dimension(554, 40));

        jbBuscar.setText("Buscar");
        jbBuscar.setPreferredSize(new java.awt.Dimension(120, 36));
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 26));

        jtfNombre.setPreferredSize(new java.awt.Dimension(160, 26));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel2.setPreferredSize(new java.awt.Dimension(542, 40));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 2));

        jbCancelar.setText("Cancelar");
        jbCancelar.setPreferredSize(new java.awt.Dimension(120, 36));
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(jbCancelar);

        jbAceptar.setText("Aceptar");
        jbAceptar.setPreferredSize(new java.awt.Dimension(120, 36));
        jbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAceptarActionPerformed(evt);
            }
        });
        jPanel2.add(jbAceptar);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jlTipos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspItems.setViewportView(jlTipos);

        getContentPane().add(jspItems, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
		try {
			modelo.setItems(dGeneral.getTipos(jtfNombre.getText()));
			log.info("Modelo {}", modelo.getSize());
		} catch (InventarioException ex) {
			new AppMensaje("No se pudo consultar los tipos de equipo", ex).mostrar(this);
		}
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
		cancel();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAceptarActionPerformed
		try {
			aceptar(jlTipos.getSelectedIndex());
		} catch (InventarioException ex) {
			new AppMensaje(ex).mostrar(this);
		}
    }//GEN-LAST:event_jbAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JList jlTipos;
    private javax.swing.JScrollPane jspItems;
    private javax.swing.JTextField jtfNombre;
    // End of variables declaration//GEN-END:variables

}
