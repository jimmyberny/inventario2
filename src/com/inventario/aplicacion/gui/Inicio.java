package com.inventario.aplicacion.gui;

import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Vista;
import javax.swing.JComponent;

/**
 *
 * @author Enrique
 */
public class Inicio extends javax.swing.JPanel implements Vista {

    private Aplicacion app;

    public Inicio() {
        initComponents();
    }

    @Override
    public void inicializar(Aplicacion app) throws InventarioException {
        this.app = app;
    }

    @Override
    public JComponent getVista() {
        return this;
    }

    @Override
    public String getTitulo() {
        return "Bienvenido";
    }

    @Override
    public void activar() throws InventarioException {
        // Nada bueno por hacer
    }

    @Override
    public boolean ocultar() {
        return true; // Siempre se puede cerrar
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setText("Los pendientes y cosas por hacer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(273, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
