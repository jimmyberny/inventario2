/**
 * inventario | Mar 5, 2014 2:22:18 AM
 */
package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.InventarioApp;
import com.inventario.aplicacion.buscadores.BuscadorEquipo;
import com.inventario.datas.DatosGeneral;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Editor;
import com.inventario.listener.MonitorListener;
import com.inventario.modelo.EquipoComputo;
import com.inventario.modelo.Evento;
import com.inventario.util.Format;
import com.inventario.util.Option;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author None
 */
public class EventoEditor extends Editor<Evento> {

    private static final Logger log = LoggerFactory.getLogger(EventoEditor.class);

    private final Color alerta;
    private final Color original; // Original

    private Aplicacion app;
    private DatosGeneral dGeneral;

    private Evento evento;
    private EquipoComputo equipo;

    private Option.FixedOptionsModel<String> tipos = InventarioApp.MAN_TIPOS.getModel();

    public EventoEditor(Aplicacion app, MonitorListener monitor) {
        initComponents();
        this.app = app;
        this.dGeneral = (DatosGeneral) app.getDatos(InventarioApp.AD_GENERAL);

        jcbFecha.setEditor(jtfFecha);
        jcbFecha.setMinSelectableDate(new Date()); // Justo ahora
        jcbxTipo.setModel(tipos);

        monitor.listenTo(jtfEquipo);
        monitor.listenTo(jcbFecha);
        monitor.listenTo(jtfEvento);
        monitor.listenTo(jtaIndicaciones);
        tipos.addListDataListener(monitor);

        // Colores
        alerta = new Color(160, 40, 40);
        original = new Color(jtfEquipo.getBackground().getRGB());
        
        //
        jtfEquipo.addKeyListener(new BusquedaListener());
    }

    @Override
    public void initNoItem() {
        evento = null;
        limpiar();
        setActivo(false);
    }

    @Override
    public void initItem() {
        evento = new Evento();
        limpiar();
        setActivo(true);
    }

    @Override
    public void mostrarItem(Evento item) throws InventarioException {
        this.evento = item;
        mostrar();
        setActivo(true);
    }

    @Override
    public void borrarItem(Evento item) throws InventarioException {
        this.evento = item;
        mostrar();
        setActivo(false);
    }

    @Override
    public Evento getItem() throws InventarioException {
        evento.setEquipo(equipo);
        evento.setTipo(tipos.getSelectedId());
        evento.setNombre(jtfEvento.getText());
        evento.setInstruccion(jtaIndicaciones.getText());
        evento.setFecha(jcbFecha.getDate());
        evento.setObservaciones(""); // Empty string
        return evento;
    }

    private void mostrar() {
        equipo = evento.getEquipo();
        jtfEquipo.setText(Format.OBJECT.format(equipo));
        jtfResponsable.setText(Format.OBJECT.format(evento.getEquipo().getEmpleado()));
        jcbFecha.setDate(evento.getFecha());
        
        tipos.setSelectedById(evento.getTipo());
        jtfEvento.setText(evento.getNombre());
        jtaIndicaciones.setText(evento.getInstruccion());
    }

    @Override
    public void setActivo(boolean activo) {
        jtfEquipo.setEnabled(activo);
        jtfResponsable.setEnabled(activo);
        jtfFecha.setEnabled(activo);
        jcbxTipo.setEnabled(activo);
        jtfEvento.setEnabled(activo);

        jtaIndicaciones.setEnabled(activo);
    }

    @Override
    public void actualizar() {
        // Nada
    }

    @Override
    public void limpiar() {
        equipo = null;
        jtfEquipo.setText(null);
        jtfResponsable.setText(null);
        jcbFecha.setDate(null);
        tipos.setSelected(null);
        jtfEvento.setText(null);

        jtaIndicaciones.setText(null);
    }

    private class BusquedaListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            log.info("{}, {}", e.getKeyCode(), jtfEquipo.getText());
            if (!jtfEquipo.getText().trim().isEmpty() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    EquipoComputo equipo = dGeneral.getEquipo(jtfEquipo.getText());
                    if (equipo != null) {
                        jtfEquipo.setBackground(original);
                        EventoEditor.this.equipo = equipo;
                    } else {
                        jbSetEquipo.doClick(); // Ok
                    }
                } catch (InventarioException ex) {
                    log.error(ex.getMessage(), ex);
                }
            } else {
                jtfEquipo.setBackground(alerta);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfFecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfEquipo = new javax.swing.JTextField();
        jtfEvento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtfResponsable = new javax.swing.JTextField();
        jbSetEquipo = new javax.swing.JButton();
        jcbFecha = new com.inventario.gui.util.JCalendarButton();
        jLabel5 = new javax.swing.JLabel();
        jcbxTipo = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaIndicaciones = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Equipo");
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 26));

        jtfFecha.setEditable(false);
        jtfFecha.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel4.setText("Evento");
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 26));

        jLabel3.setText("Responsable");
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 26));

        jtfEquipo.setPreferredSize(new java.awt.Dimension(160, 26));

        jtfEvento.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel1.setText("Fecha programada");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 26));

        jtfResponsable.setEditable(false);
        jtfResponsable.setPreferredSize(new java.awt.Dimension(160, 26));

        jbSetEquipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/search.png"))); // NOI18N
        jbSetEquipo.setPreferredSize(new java.awt.Dimension(40, 26));
        jbSetEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSetEquipoActionPerformed(evt);
            }
        });

        jcbFecha.setPreferredSize(new java.awt.Dimension(40, 26));

        jLabel5.setText("Tipo de evento");
        jLabel5.setPreferredSize(new java.awt.Dimension(120, 26));

        jcbxTipo.setPreferredSize(new java.awt.Dimension(160, 26));

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
                        .addComponent(jtfEquipo, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSetEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbxTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSetEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jtaIndicaciones.setColumns(20);
        jtaIndicaciones.setRows(5);
        jScrollPane1.setViewportView(jtaIndicaciones);

        jTabbedPane1.addTab("Indicaciones", jScrollPane1);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jbSetEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSetEquipoActionPerformed
        BuscadorEquipo be = BuscadorEquipo.mostrar(this, app, jtfEquipo.getText());
        if (be.isAceptar()) {
            equipo = be.getItem();
            jtfEquipo.setText(Format.OBJECT.format(equipo));
            jtfResponsable.setText(Format.OBJECT.format(equipo.getEmpleado()));
            jtfEquipo.setBackground(original);
        }
    }//GEN-LAST:event_jbSetEquipoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbSetEquipo;
    private com.inventario.gui.util.JCalendarButton jcbFecha;
    private javax.swing.JComboBox jcbxTipo;
    private javax.swing.JTextArea jtaIndicaciones;
    private javax.swing.JTextField jtfEquipo;
    private javax.swing.JTextField jtfEvento;
    private javax.swing.JTextField jtfFecha;
    private javax.swing.JTextField jtfResponsable;
    // End of variables declaration//GEN-END:variables
}
