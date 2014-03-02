package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.bd.NavegadorDatos;
import com.inventario.error.InventarioException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Zulma
 */
public class JPanelCtrlModelo extends javax.swing.JPanel implements ActionListener {

    private final static Logger log = LoggerFactory.getLogger(JPanelCtrlModelo.class);
    private final NavegadorDatos nav;

    public JPanelCtrlModelo(NavegadorDatos nav) {
        initComponents();
        this.nav = nav;

        jbNuevo.addActionListener(this);
        jbBorrar.addActionListener(this);
        jbGuardar.addActionListener(this);
		jbDescartar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(jbNuevo)) {
                nav.registrar();
            } else if (e.getSource().equals(jbBorrar)) {
                nav.borrar();
            } else if (e.getSource().equals(jbGuardar)) {
                nav.guardarCambios();
            } else if (e.getSource().equals(jbDescartar)) {
				nav.descartar();
			}
        } catch (InventarioException ex) {
            new AppMensaje("Error del modelo", ex).mostrar(this);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbNuevo = new javax.swing.JButton();
        jbBorrar = new javax.swing.JButton();
        jbGuardar = new javax.swing.JButton();
        jbDescartar = new javax.swing.JButton();

        jbNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/add_page.png"))); // NOI18N
        jbNuevo.setToolTipText("Nuevo registro");
        jbNuevo.setPreferredSize(new java.awt.Dimension(40, 40));

        jbBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/delete_page.png"))); // NOI18N
        jbBorrar.setToolTipText("Eliminar registro");
        jbBorrar.setPreferredSize(new java.awt.Dimension(40, 40));

        jbGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/save.png"))); // NOI18N
        jbGuardar.setToolTipText("Guardar cambios");
        jbGuardar.setPreferredSize(new java.awt.Dimension(40, 40));

        jbDescartar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/full_page.png"))); // NOI18N
        jbDescartar.setToolTipText("Descartar los cambios");
        jbDescartar.setPreferredSize(new java.awt.Dimension(40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jbDescartar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbDescartar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbBorrar;
    private javax.swing.JButton jbDescartar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbNuevo;
    // End of variables declaration//GEN-END:variables

}
