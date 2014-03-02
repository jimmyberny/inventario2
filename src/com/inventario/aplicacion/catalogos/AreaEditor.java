package com.inventario.aplicacion.catalogos;

import com.inventario.error.InventarioException;
import com.inventario.interfaces.Editor;
import com.inventario.listener.MonitorListener;
import com.inventario.modelo.Area;

/**
 *
 * @author Zulma
 */
public class AreaEditor extends Editor<Area>{

    private Area area;
    
    public AreaEditor(MonitorListener monitor) {
        initComponents();
        
        monitor.listenTo(jtfNombre);
    }

    @Override
    public void initNoItem() {
        area = null;
        limpiar();
        setActivo(false);
    }

    @Override
    public void initItem() {
        area = new Area();
        limpiar();
        setActivo(true);
    }

    @Override
    public void mostrarItem(Area item) throws InventarioException {
        area = item;
        jtfNombre.setText(area.getNombre());
        setActivo(true);
    }

    @Override
    public void borrarItem(Area item) throws InventarioException {
        area = item;
        jtfNombre.setText(area.getNombre());
        setActivo(false);
    }

    @Override
    public Area getItem() throws InventarioException {
        area.setNombre(jtfNombre.getText());
        return area;
    }

    @Override
    public void setActivo(boolean activo) {
        jtfNombre.setEnabled(activo);
    }

    @Override
    public void actualizar() {
        // nada que hacer
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
