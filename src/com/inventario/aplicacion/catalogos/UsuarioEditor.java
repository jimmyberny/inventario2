package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.buscadores.BuscadorEmpleado;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Editor;
import com.inventario.listener.MonitorListener;
import com.inventario.modelo.Empleado;
import com.inventario.modelo.Usuario;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class UsuarioEditor extends Editor<Usuario> {

    private Aplicacion app;
    private Usuario usuario;
    //
    private Empleado empleado;

    public UsuarioEditor(Aplicacion app, MonitorListener monitor) {
        initComponents();
        this.app = app;

        monitor.listenTo(jtfEmpleado);
        monitor.listenTo(jtfUsuario);
        monitor.listenTo(jpfContra);
        monitor.listenTo(jpfContra2);

    }

    @Override
    public void initNoItem() {
        usuario = null;
        limpiar();
        setActivo(false);
    }

    @Override
    public void initItem() {
        usuario = new Usuario();
        limpiar();
        setActivo(true);
    }

    @Override
    public void mostrarItem(Usuario item) throws InventarioException {
        this.usuario = item;
        mostrar();
        setActivo(true);
    }

    @Override
    public void borrarItem(Usuario item) throws InventarioException {
        this.usuario = item;
        mostrar();
        setActivo(false);
    }

    private void mostrar() {
        empleado = usuario.getEmpleado();
        jtfEmpleado.setText(usuario.getEmpleado().toString());
        jtfUsuario.setText(usuario.getUsuario());
        jpfContra.setText(usuario.getContrasena());
    }

    @Override
    public Usuario getItem() throws InventarioException {
        String s1 = new String(jpfContra.getPassword());
        String s2 = new String(jpfContra2.getPassword());
        if (s1.isEmpty() || !s1.equals(s2)) {
            throw new InventarioException("La contraseña no coincide o esta vacía.");
        }

        if (usuario.getEmpleado() != empleado) {
            usuario.setEmpleado(empleado);
        }
        usuario.setUsuario(jtfUsuario.getText());
        usuario.setContrasena(s2);

        return usuario;
    }

    @Override
    public void setActivo(boolean activo) {
        jbSetEmpleado.setEnabled(activo);
        jtfUsuario.setEnabled(activo);
        jpfContra.setEnabled(activo);
        jpfContra2.setEnabled(activo);
    }

    @Override
    public void actualizar() {
        // Nada novedoso por hacer
    }

    @Override
    public void limpiar() {
        jtfEmpleado.setText(null);
        jtfUsuario.setText(null);
        jpfContra.setText(null);
        jpfContra2.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfEmpleado = new javax.swing.JTextField();
        jtfUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jpfContra = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jpfContra2 = new javax.swing.JPasswordField();
        jbSetEmpleado = new javax.swing.JButton();

        jLabel1.setText("Empleado");
        jLabel1.setPreferredSize(new java.awt.Dimension(140, 26));

        jLabel2.setText("Usuario");
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfEmpleado.setEditable(false);
        jtfEmpleado.setPreferredSize(new java.awt.Dimension(160, 26));

        jtfUsuario.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel3.setText("Contraseña");
        jLabel3.setPreferredSize(new java.awt.Dimension(140, 26));

        jpfContra.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel4.setText("Repetir contraseña");
        jLabel4.setPreferredSize(new java.awt.Dimension(140, 26));

        jpfContra2.setPreferredSize(new java.awt.Dimension(160, 26));

        jbSetEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/search.png"))); // NOI18N
        jbSetEmpleado.setPreferredSize(new java.awt.Dimension(40, 26));
        jbSetEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSetEmpleadoActionPerformed(evt);
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
                        .addComponent(jtfUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpfContra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSetEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpfContra2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSetEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfContra2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(162, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbSetEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSetEmpleadoActionPerformed
        BuscadorEmpleado be = BuscadorEmpleado.mostrar(this, app);
        if (be.isAceptar()) {
            empleado = be.getItem();
            jtfEmpleado.setText(empleado.toString());
        }
    }//GEN-LAST:event_jbSetEmpleadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jbSetEmpleado;
    private javax.swing.JPasswordField jpfContra;
    private javax.swing.JPasswordField jpfContra2;
    private javax.swing.JTextField jtfEmpleado;
    private javax.swing.JTextField jtfUsuario;
    // End of variables declaration//GEN-END:variables

}
