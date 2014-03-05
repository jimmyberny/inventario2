/*
 *  inventario | Mar 5, 2014 6:00:24 AM 
 */
package com.inventario.aplicacion.gui;

import com.inventario.gui.util.DialogKeyEventDistpatcher;
import com.inventario.interfaces.gui.Cancelable;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.SwingUtilities;

/**
 *
 * @author None
 */
public class AcercaDe extends javax.swing.JDialog implements Cancelable{

    private AcercaDe(Frame parent, boolean modal) {
        super(parent, modal);
    }

    private AcercaDe(Dialog parent, boolean modal) {
        super(parent, modal);
    }

    public static void mostrar(Component padre) {
        AcercaDe ad;
        Window mw = SwingUtilities.getWindowAncestor(padre);
		if (mw instanceof Frame) {
			ad = new AcercaDe((Frame) mw, true);
		} else {
			ad = new AcercaDe((Dialog) mw, true);
		}
		ad.initComponents();
        ad.setLocationRelativeTo(padre);
        DialogKeyEventDistpatcher.dispatch(ad);
        ad.setVisible(true);
    }

    @Override
    public void cancel() {
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        jbAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Acerca de");

        jLabel1.setBackground(new java.awt.Color(254, 254, 254));
        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+8));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Aplicación de Inventario");
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(39, 30));
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jEditorPane1.setContentType("text/html"); // NOI18N
        jEditorPane1.setFont(jEditorPane1.getFont().deriveFont(jEditorPane1.getFont().getSize()+3f));
        jEditorPane1.setText("<html>\n  <head>\n\n  </head>\n  <body>\n    La aplicación de inventario fue elaborado por Zulma y Enrique como proyecto de residencia.\n  </body>\n</html>\n");
        jScrollPane1.setViewportView(jEditorPane1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 34));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 2));

        jbAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/accept.png"))); // NOI18N
        jbAceptar.setText("Aceptar");
        jbAceptar.setPreferredSize(new java.awt.Dimension(120, 30));
        jbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(jbAceptar);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAceptarActionPerformed
        cancel();
    }//GEN-LAST:event_jbAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAceptar;
    // End of variables declaration//GEN-END:variables
}
