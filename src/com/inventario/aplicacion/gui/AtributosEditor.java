package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.buscadores.ModeloLista;
import com.inventario.error.InventarioException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Zulma
 */
public class AtributosEditor extends javax.swing.JPanel implements ListSelectionListener {

    public static final Logger log = LoggerFactory.getLogger(AtributosEditor.class);
    public static final String ATRIBUTOS = "atributos";

    private Properties props;
    private ModeloLista<Atributo> modelo;
    //
    private int anterior = -1;
    private int index = -1;
    private Atributo atributo;

    public AtributosEditor() {
        initComponents();

        props = new Properties();
        modelo = new ModeloLista<>();
        jlAtributos.setModel(modelo);

        jlAtributos.addListSelectionListener(this); // n_n
    }

    public ModeloLista<Atributo> getModelo() {
        return modelo;
    }

    public void clear() {
        props.clear();
        modelo.setItems(new ArrayList<Atributo>(0)); // Empty
    }

    public void setData(byte[] datos) {
        props.clear();
        try {
            props.loadFromXML(new ByteArrayInputStream(datos));
        } catch (InvalidPropertiesFormatException ipfex) {
            log.error(ipfex.getMessage(), ipfex);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        List<Atributo> atributos = new ArrayList<>(props.size());
        Iterator<Map.Entry<Object, Object>> it = props.entrySet().iterator();
        while (it.hasNext()) {
            atributos.add(new Atributo(it.next()));
        }
        modelo.setItems(atributos);
    }

    public byte[] getData() throws InventarioException {
        try {
            props.clear();
            for (Atributo att : modelo.getItems()) {
                props.put(att.getNombre(), att.getValor());
            }

            // -->
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            props.storeToXML(baos, "Caracteristicas de un equipo de compúto");
            return baos.toByteArray();
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            throw new InventarioException("No se pueden guardar las características");
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        jbGuardar.setEnabled(enabled);
        jbNuevo.setEnabled(enabled);
        jbQuitar.setEnabled(enabled);
    }

    private void guardarAtributo() {
        if (atributo != null) {
            atributo.setNombre(jtfNombre.getText());
            atributo.setValor(jtfValor.getText());

        }
    }

    private void setActivo(boolean activo) {
        jtfNombre.setEnabled(activo);
        jtfValor.setEnabled(activo);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            index = jlAtributos.getSelectedIndex();
            if (anterior != index) {
                guardarAtributo();
                modelo.refresh(anterior);
            }
            anterior = index;
            if (index != -1) {
                atributo = modelo.getElementAt(index);
                if (atributo != null) {
                    jtfNombre.setText(atributo.getNombre());
                    jtfNombre.setEnabled(false);
                    jtfValor.setText(atributo.getValor());
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspLista = new javax.swing.JScrollPane();
        jlAtributos = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jbNuevo = new javax.swing.JButton();
        jbQuitar = new javax.swing.JButton();
        jbGuardar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfValor = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jspLista.setPreferredSize(new java.awt.Dimension(220, 139));

        jlAtributos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspLista.setViewportView(jlAtributos);

        add(jspLista, java.awt.BorderLayout.LINE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(10, 40));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 2));

        jbNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/add.png"))); // NOI18N
        jbNuevo.setPreferredSize(new java.awt.Dimension(40, 36));
        jbNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNuevoActionPerformed(evt);
            }
        });
        jPanel2.add(jbNuevo);

        jbQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/delete.png"))); // NOI18N
        jbQuitar.setPreferredSize(new java.awt.Dimension(40, 36));
        jbQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQuitarActionPerformed(evt);
            }
        });
        jPanel2.add(jbQuitar);

        jbGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/save-small.png"))); // NOI18N
        jbGuardar.setPreferredSize(new java.awt.Dimension(40, 36));
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(jbGuardar);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jLabel1.setText("Característica");
        jLabel1.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfNombre.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel2.setText("Valor");
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfValor.setPreferredSize(new java.awt.Dimension(160, 26));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfValor, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(188, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jbNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNuevoActionPerformed
        index = -1;
        jlAtributos.clearSelection();
        atributo = new Atributo();
        jtfNombre.setText(null);
        jtfValor.setText(null);
        // Habilitar la caja
        setActivo(true);
    }//GEN-LAST:event_jbNuevoActionPerformed

    private void jbQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQuitarActionPerformed
        if (index != -1) {
            setActivo(false);
        }
    }//GEN-LAST:event_jbQuitarActionPerformed

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        if (index != -1) {
            modelo.remove(index);
        } else {
            guardarAtributo();
            modelo.add(atributo);
        }
        setActivo(true);
    }//GEN-LAST:event_jbGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbNuevo;
    private javax.swing.JButton jbQuitar;
    private javax.swing.JList jlAtributos;
    private javax.swing.JScrollPane jspLista;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfValor;
    // End of variables declaration//GEN-END:variables

}
