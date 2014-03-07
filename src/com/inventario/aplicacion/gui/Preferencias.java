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
        jcbxTema = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfDias = new javax.swing.JTextField();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jlmCambios = new com.inventario.aplicacion.gui.JLabelMonitor();
        jbGuardar = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 177));

        jcbxTema.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel1.setText("Tema");
        jLabel1.setPreferredSize(new java.awt.Dimension(140, 26));

        jLabel2.setText("Dias para alertas");
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfDias.setPreferredSize(new java.awt.Dimension(160, 26));

        jTextArea1.setLineWrap(true);
        jTextArea1.setTabSize(4);
        jTextArea1.setText("Es la cantidad de días a partir de la fecha actual en que se busca vigencia de programas a expirar, para así saber que esta próximo a vencer alguna licencia.\n");
        jTextArea1.setWrapStyleWord(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfDias, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextArea1)
                            .addComponent(jcbxTema, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

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
                configuracion.guardar();
                //
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JComboBox jcbxTema;
    private com.inventario.aplicacion.gui.JLabelMonitor jlmCambios;
    private javax.swing.JTextField jtfDias;
    // End of variables declaration//GEN-END:variables
}
