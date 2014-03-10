/**
 * inventario | Mar 5, 2014 4:04:39 AM
 */
package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.aplicacion.InventarioApp;
import com.inventario.datas.DatosGeneral;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Vista;
import com.inventario.modelo.EquipoComputo;
import com.inventario.util.Format;
import javax.swing.JComponent;

/**
 *
 * @author None
 */
public class ConsultarEventos extends javax.swing.JPanel implements Vista {

    private Aplicacion app;
    private DatosGeneral dGeneral;
    
    private EventoTableModel historia;
    private EventoTableModel agenda;
    
    private EquipoComputo equipo;
    
    
    public ConsultarEventos() {
        initComponents();
    }

    @Override
    public void inicializar(Aplicacion app) throws InventarioException {
        this.app = app;

        dGeneral = (DatosGeneral) app.getDatos(InventarioApp.AD_GENERAL);
        historia = new EventoTableModel(null);
        jtHistoria.setModel(historia);
        
        agenda = new EventoTableModel(null);
        jtAgenda.setModel(agenda);
    }

    @Override
    public JComponent getVista() {
        return this;
    }

    @Override
    public String getTitulo() {
        return "Consultar información de equipo";
    }

    @Override
    public void activar() throws InventarioException {
    }

    @Override
    public boolean ocultar() {
        return true;
    }
    
    private void limpiar() {
        jtfActivoFijo.setText(null);
        
        jtfSerie.setText(null);
        jtfActivoFijo2.setText(null);
        jtfMarca.setText(null);
        jtfModelo.setText(null);
        jtfResponsable.setText(null);
        jtfEstado.setText(null);
        
        jtaObservaciones.setText(null);
    }
    
    private void mostrar() {
        jtfSerie.setText(equipo.getSerie());
        jtfActivoFijo2.setText(equipo.getActivoFijo());
        jtfMarca.setText(equipo.getMarca());
        jtfModelo.setText(equipo.getModelo());
        jtfResponsable.setText(Format.OBJECT.format(equipo.getEmpleado()));
        jtfEstado.setText(equipo.getEstado());
        
        jtaObservaciones.setText(equipo.getDescripcion());
    }
    
    private void buscar() {
        String cad = jtfActivoFijo.getText();
        if (!cad.isEmpty()) {
            try {
                // Buscar el item
                equipo = dGeneral.getEquipo(cad);
                if (equipo != null) {
                    limpiar(); // Por la info anterior.
                    mostrar();
                    
                    // Buscar los eventos.
                    historia.setItems(dGeneral.getEventos(equipo, Boolean.FALSE));
                    agenda.setItems(dGeneral.getEventos(equipo, Boolean.TRUE));
                } else {
                    new AppMensaje(String.format("No se encontró ningun resultado para %s.", cad)).mostrar(this);
                }
            } catch (InventarioException ex) {
                new AppMensaje("No se ha podido completar la consulta.", ex).mostrar(this);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfActivoFijo = new javax.swing.JTextField();
        jbBuscar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfSerie = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfActivoFijo2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfMarca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfModelo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfResponsable = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtfEstado = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaObservaciones = new javax.swing.JTextArea();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtHistoria = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtAgenda = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(220, 477));

        jLabel1.setText("Buscar");
        jLabel1.setPreferredSize(new java.awt.Dimension(160, 26));

        jtfActivoFijo.setPreferredSize(new java.awt.Dimension(160, 26));
        jtfActivoFijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfActivoFijoActionPerformed(evt);
            }
        });

        jbBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/search.png"))); // NOI18N
        jbBuscar.setText("Buscar");
        jbBuscar.setPreferredSize(new java.awt.Dimension(120, 26));
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfActivoFijo, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfActivoFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(257, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.LINE_START);

        jLabel2.setText("Serie");
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 26));

        jtfSerie.setEditable(false);
        jtfSerie.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel3.setText("Activo fijo");
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 26));

        jtfActivoFijo2.setEditable(false);
        jtfActivoFijo2.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel4.setText("Marca");
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 26));

        jtfMarca.setEditable(false);
        jtfMarca.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel5.setText("Modelo");
        jLabel5.setPreferredSize(new java.awt.Dimension(120, 26));

        jtfModelo.setEditable(false);
        jtfModelo.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel6.setText("Responsable");
        jLabel6.setPreferredSize(new java.awt.Dimension(120, 26));

        jtfResponsable.setEditable(false);
        jtfResponsable.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel7.setText("Estado");
        jLabel7.setPreferredSize(new java.awt.Dimension(120, 26));

        jtfEstado.setEditable(false);
        jtfEstado.setPreferredSize(new java.awt.Dimension(160, 26));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripción"));

        jtaObservaciones.setEditable(false);
        jtaObservaciones.setColumns(20);
        jtaObservaciones.setRows(5);
        jScrollPane3.setViewportView(jtaObservaciones);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfSerie, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfActivoFijo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfActivoFijo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Equipo", jPanel1);

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Anteriores"));

        jScrollPane1.setViewportView(jtHistoria);

        jSplitPane1.setTopComponent(jScrollPane1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Agendados"));

        jScrollPane2.setViewportView(jtAgenda);

        jSplitPane1.setRightComponent(jScrollPane2);

        jTabbedPane1.addTab("Eventos", jSplitPane1);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jtfActivoFijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfActivoFijoActionPerformed
        buscar();
    }//GEN-LAST:event_jtfActivoFijoActionPerformed

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_jbBuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JTable jtAgenda;
    private javax.swing.JTable jtHistoria;
    private javax.swing.JTextArea jtaObservaciones;
    private javax.swing.JTextField jtfActivoFijo;
    private javax.swing.JTextField jtfActivoFijo2;
    private javax.swing.JTextField jtfEstado;
    private javax.swing.JTextField jtfMarca;
    private javax.swing.JTextField jtfModelo;
    private javax.swing.JTextField jtfResponsable;
    private javax.swing.JTextField jtfSerie;
    // End of variables declaration//GEN-END:variables
}
