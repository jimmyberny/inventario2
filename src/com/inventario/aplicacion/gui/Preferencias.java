/**
 * inventario | Mar 4, 2014 5:18:21 AM
 */
package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.aplicacion.Configuracion;
import com.inventario.aplicacion.InventarioApp;
import com.inventario.error.InventarioException;
import com.inventario.gui.util.DialogoUtil;
import com.inventario.gui.util.LookAndFeelInfo;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Vista;
import com.inventario.interfaces.gui.OptionListModel;
import com.inventario.listener.MonitorListener;
import com.inventario.util.Format;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.skin.SkinInfo;

/**
 *
 * @author Admin
 */
public class Preferencias extends javax.swing.JPanel implements Vista {

    private Aplicacion app;
    private OptionListModel<LookAndFeelInfo, String> lafs;
    private MonitorListener monitor;

    private Configuracion configuracion;

    public Preferencias() {
        initComponents();

        monitor = new MonitorListener();
        jlmCambios.addMonitor(monitor);
    }

    @Override
    public void inicializar(Aplicacion app) throws InventarioException {
        this.app = app;

        configuracion = ((Principal) app).getConfiguracion();
        //
        List<LookAndFeelInfo> lafis = new ArrayList<>(30);
        for (UIManager.LookAndFeelInfo lafi : UIManager.getInstalledLookAndFeels()) {
            lafis.add(new LookAndFeelInfo(lafi.getClassName(), lafi.getName()));
        }
        Iterator<Map.Entry<String, SkinInfo>> entries
                = SubstanceLookAndFeel.getAllSkins().entrySet().iterator();
        for (; entries.hasNext();) {
            Map.Entry<String, SkinInfo> next = entries.next();
            lafis.add(new LookAndFeelInfo(next.getValue().getClassName(), next.getValue().getDisplayName()));
        }

        lafs = new OptionListModel<>(lafis);
        jcbxTema.setModel(lafs);
        lafs.addListDataListener(monitor);
        lafs.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                setLookAndFeel(lafs.getSelected());
            }
        });

        monitor.listenTo(jtfDias);
        monitor.listenTo(jtfServidorSmtp);
        monitor.listenTo(jtfPuertoSmtp);
        monitor.listenTo(jtfUsuario);
        monitor.listenTo(jpfContrasena);
    }

    private void setLookAndFeel(final LookAndFeelInfo lafInfo) {
        if (lafInfo != null
                && !lafInfo.getId().equals(UIManager.getLookAndFeel().getClass().getName())) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Object laf = Class.forName(lafInfo.getId()).newInstance();
                        if (laf instanceof LookAndFeel) {
                            UIManager.setLookAndFeel((LookAndFeel) laf);
                        } else if (laf instanceof SubstanceSkin) {
                            SubstanceLookAndFeel.setSkin((SubstanceSkin) laf);
                        }
                        SwingUtilities.updateComponentTreeUI(Preferencias.this.getTopLevelAncestor());
                    } catch (ClassNotFoundException |
                            InstantiationException |
                            IllegalAccessException |
                            UnsupportedLookAndFeelException gex) {
                    }
                }
            });
        }
    }

    @Override
    public JComponent getVista() {
        return this;
    }

    @Override
    public String getTitulo() {
        return "Preferencias";
    }

    @Override
    public void activar() throws InventarioException {
        // All green
        jtfDias.setText(configuracion.getProperty(InventarioApp.KC_DIAS_ALERTA));
        lafs.setSelectedById(configuracion.getProperty(InventarioApp.KC_LOOK_AND_FEEL));
        //
        jtfServidorSmtp.setText(configuracion.getProperty(InventarioApp.KC_SMTP_HOST));
        jtfPuertoSmtp.setText(configuracion.getProperty(InventarioApp.KC_SMTP_PORT));
        jtfUsuario.setText(configuracion.getProperty(InventarioApp.KC_EMAIL_USUARIO));
        jpfContrasena.setText(configuracion.getProperty(InventarioApp.KC_EMAIL_CLAVE));

        //
        monitor.setDirty(false);
    }

    @Override
    public boolean ocultar() {
        return !monitor.isDirty()
                || DialogoUtil.yes(this, "Hay cambios sin guardar. ¿Desea descartar los cambios?");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        jtfDias = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfServidorSmtp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfPuertoSmtp = new javax.swing.JTextField();
        jtfUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jpfContrasena = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcbxTema = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jlmCambios = new com.inventario.aplicacion.gui.JLabelMonitor();
        jbGuardar = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 177));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel2.setText("Dias para alertas");
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 26));

        jTextArea1.setLineWrap(true);
        jTextArea1.setTabSize(4);
        jTextArea1.setText("Es la cantidad de días a partir de la fecha actual en que se busca vigencia de programas a expirar, para así saber que esta próximo a vencer alguna licencia.\n");
        jTextArea1.setWrapStyleWord(true);

        jtfDias.setPreferredSize(new java.awt.Dimension(160, 26));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfDias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jTextArea1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);

        jLabel3.setText("Servidor SMTP");
        jLabel3.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfServidorSmtp.setPreferredSize(new java.awt.Dimension(140, 26));

        jLabel4.setText("Puerto SMTP");
        jLabel4.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfPuertoSmtp.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfUsuario.setPreferredSize(new java.awt.Dimension(140, 26));

        jLabel5.setText("Usuario");
        jLabel5.setPreferredSize(new java.awt.Dimension(140, 26));

        jLabel6.setText("Contraseña");
        jLabel6.setPreferredSize(new java.awt.Dimension(140, 26));

        jpfContrasena.setPreferredSize(new java.awt.Dimension(140, 26));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfServidorSmtp, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfPuertoSmtp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpfContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfServidorSmtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfPuertoSmtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel5);

        jLabel1.setText("Tema");
        jLabel1.setPreferredSize(new java.awt.Dimension(140, 26));

        jcbxTema.setPreferredSize(new java.awt.Dimension(160, 26));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbxTema, 0, 1, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel4);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(476, 34));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 2));

        jlmCambios.setPreferredSize(new java.awt.Dimension(26, 26));
        jPanel2.add(jlmCambios);

        jbGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/save-small.png"))); // NOI18N
        jbGuardar.setText("Guardar");
        jbGuardar.setPreferredSize(new java.awt.Dimension(120, 30));
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(jbGuardar);

        add(jPanel2, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        if (monitor.isDirty()) {
            try {
                // Una validación sencilla
                Format.INTEGER.parse(jtfDias.getText());
                
                //
                configuracion.getProperties().put(InventarioApp.KC_DIAS_ALERTA, jtfDias.getText());
                configuracion.getProperties().put(InventarioApp.KC_LOOK_AND_FEEL,
                        lafs.getSelectedId());
                configuracion.getProperties().put(InventarioApp.KC_EMAIL_USUARIO, jtfUsuario.getText());
                configuracion.getProperties().put(InventarioApp.KC_EMAIL_CLAVE, new String(jpfContrasena.getPassword()));
                configuracion.getProperties().put(InventarioApp.KC_SMTP_HOST, jtfServidorSmtp.getText());
                configuracion.getProperties().put(InventarioApp.KC_SMTP_PORT, jtfPuertoSmtp.getText());
                
                // default
                
                        
                // Guardar
                configuracion.guardar();
                
                monitor.setDirty(false);
                new AppMensaje("Configuración guardada exitosamente.").mostrar(this);
            } catch (InventarioException ex) {
                new AppMensaje("No se ha podido guardar la configuración.", ex).mostrar(this);
            }
        }
    }//GEN-LAST:event_jbGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JComboBox jcbxTema;
    private com.inventario.aplicacion.gui.JLabelMonitor jlmCambios;
    private javax.swing.JPasswordField jpfContrasena;
    private javax.swing.JTextField jtfDias;
    private javax.swing.JTextField jtfPuertoSmtp;
    private javax.swing.JTextField jtfServidorSmtp;
    private javax.swing.JTextField jtfUsuario;
    // End of variables declaration//GEN-END:variables
}
