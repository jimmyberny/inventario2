/**
 * inventario | Mar 5, 2014 4:04:39 AM
 */
package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.aplicacion.Configuracion;
import com.inventario.aplicacion.InventarioApp;
import com.inventario.datas.DatosGeneral;
import com.inventario.error.InventarioException;
import com.inventario.gui.util.DialogoUtil;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Vista;
import com.inventario.listener.MonitorListener;
import com.inventario.modelo.Evento;
import com.inventario.util.Format;
import com.inventario.util.Option;
import java.util.Date;
import javax.swing.JComponent;

/**
 *
 * @author None
 */
public class ReporteEvento extends javax.swing.JPanel implements Vista {

    private Aplicacion app;
    private DatosGeneral dGeneral;
    private MonitorListener monitor;
    private Configuracion configuracion;
    
    private Option.FixedOptionsModel<String> tipos = InventarioApp.MAN_TIPOS.getModel();

    private Evento evento;

    public ReporteEvento() {
        initComponents();
        jcbSetFecha.setEditor(jtfFecha);
        jcbSetFecha.setMaxSelectableDate(new Date());
        
        jcbxTipo.setModel(tipos);
        
        monitor = new MonitorListener();

        jlmMonitor.addMonitor(monitor);
        monitor.listenTo(jtaObservaciones);
        monitor.listenTo(jcbSetFecha);
    }

    @Override
    public void inicializar(Aplicacion app) throws InventarioException {
        this.app = app;

        configuracion = ((Principal) app).getConfiguracion();
        dGeneral = (DatosGeneral) app.getDatos(InventarioApp.AD_GENERAL);
    }

    @Override
    public JComponent getVista() {
        return this;
    }

    @Override
    public String getTitulo() {
        return "Reportar evento";
    }

    @Override
    public void activar() throws InventarioException {
        try {
            evento = (Evento) app.getBus().get(InventarioApp.BUS_EVENTO);
            // Mostrar el evento
            if (evento != null) {
                jtfEvento.setText(Format.OBJECT.format(evento));
                jtfFechaProgramada.setText(Format.DATE.format(evento.getFecha()));
                jtfFecha.setText(Format.DATE.format(evento.getFechaRealizado()));
                tipos.setSelectedById(evento.getTipo());
                jtfEquipo.setText(Format.OBJECT.format(evento.getEquipo()));
                jtfResponsable.setText(Format.OBJECT.format(evento.getEquipo().getEmpleado()));
                
                jtaIndicaciones.setText(evento.getInstruccion());
                jtaObservaciones.setText(evento.getObservaciones());
                
                // Editar si aún no ha sido realizado
                setActivo(evento.getFechaRealizado() == null);
            } else {
                limpiar();
                setActivo(false);
            }
            
            
        } catch (ClassCastException ccex) {
            throw new InventarioException("Error en la interacción de las vistas.", ccex);
        }
    }

    @Override
    public boolean ocultar() {
        return !monitor.isDirty() || DialogoUtil.yes(this, "Hay cambios sin guardar. ¿Desea descartar los cambios?");
    }
    
    private void limpiar() {
        jtfEvento.setText(null);
        jtfFecha.setText(null);
        jcbSetFecha.setDate(null);
        
        tipos.setSelected(null);
        jtfEquipo.setText(null);
        jtfResponsable.setText(null);
        
        jtaIndicaciones.setText(null);
        jtaObservaciones.setText(null);
    }
    
    private void setActivo(boolean activo) {
        jtfEvento.setEnabled(activo);
        jtfFecha.setEnabled(activo);
        jcbSetFecha.setEnabled(activo);
        
        jtfEquipo.setEnabled(activo);
        jtfResponsable.setEnabled(activo);
        
        jtaIndicaciones.setEnabled(activo);
        jtaObservaciones.setEnabled(activo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlmMonitor = new com.inventario.aplicacion.gui.JLabelMonitor();
        jbGuardar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfEvento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfEquipo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfResponsable = new javax.swing.JTextField();
        jcbxTipo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaIndicaciones = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaObservaciones = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jtfFechaProgramada = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfFecha = new javax.swing.JTextField();
        jcbSetFecha = new com.inventario.gui.util.JCalendarButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 34));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 2));

        jlmMonitor.setPreferredSize(new java.awt.Dimension(26, 26));
        jPanel1.add(jlmMonitor);

        jbGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/save-small.png"))); // NOI18N
        jbGuardar.setText("Guardar");
        jbGuardar.setPreferredSize(new java.awt.Dimension(120, 30));
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(jbGuardar);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jLabel1.setText("Evento");
        jLabel1.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfEvento.setEditable(false);
        jtfEvento.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel2.setText("Tipo de evento");
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfEquipo.setEditable(false);
        jtfEquipo.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel3.setText("Equipo");
        jLabel3.setPreferredSize(new java.awt.Dimension(140, 26));

        jLabel4.setText("Responsable");
        jLabel4.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfResponsable.setEditable(false);
        jtfResponsable.setPreferredSize(new java.awt.Dimension(160, 26));

        jcbxTipo.setEnabled(false);
        jcbxTipo.setPreferredSize(new java.awt.Dimension(160, 26));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Indicaciones"));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(180, 100));

        jtaIndicaciones.setEditable(false);
        jtaIndicaciones.setColumns(20);
        jtaIndicaciones.setRows(5);
        jScrollPane1.setViewportView(jtaIndicaciones);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Observaciones"));

        jtaObservaciones.setColumns(20);
        jtaObservaciones.setRows(5);
        jtaObservaciones.setPreferredSize(new java.awt.Dimension(180, 100));
        jScrollPane2.setViewportView(jtaObservaciones);

        jLabel5.setText("Fecha programada");
        jLabel5.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfFechaProgramada.setEditable(false);
        jtfFechaProgramada.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel6.setText("Fecha de realización");
        jLabel6.setPreferredSize(new java.awt.Dimension(140, 26));

        jtfFecha.setEditable(false);
        jtfFecha.setPreferredSize(new java.awt.Dimension(160, 26));

        jcbSetFecha.setPreferredSize(new java.awt.Dimension(40, 26));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfFechaProgramada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbxTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfEquipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbSetFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfFechaProgramada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcbSetFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        if (monitor.isDirty()) {
            try {
                evento.setFechaRealizado(jcbSetFecha.getDate());
                evento.setObservaciones(jtaObservaciones.getText());
                if (evento.getFechaRealizado() == null) {
                    new AppMensaje("El reporte será guardado pero AÚN NO SE CONSIDERA COMO TERMINADO pues no ha indicado la fecha de relización.").mostrar(this);
                }
                
                dGeneral.guardarReporte(evento);
                
                // Reset estado
                monitor.setDirty(false);
                setActivo(false);
                app.getBus().remove(InventarioApp.BUS_EVENTO); // Reset estado
                new AppMensaje("El reporte ha sido guardado correctamente").mostrar(this);
            } catch (InventarioException iex) {
                new AppMensaje("No se pudo guardar el reporte.", iex).mostrar(this);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbGuardar;
    private com.inventario.gui.util.JCalendarButton jcbSetFecha;
    private javax.swing.JComboBox jcbxTipo;
    private com.inventario.aplicacion.gui.JLabelMonitor jlmMonitor;
    private javax.swing.JTextArea jtaIndicaciones;
    private javax.swing.JTextArea jtaObservaciones;
    private javax.swing.JTextField jtfEquipo;
    private javax.swing.JTextField jtfEvento;
    private javax.swing.JTextField jtfFecha;
    private javax.swing.JTextField jtfFechaProgramada;
    private javax.swing.JTextField jtfResponsable;
    // End of variables declaration//GEN-END:variables
}
