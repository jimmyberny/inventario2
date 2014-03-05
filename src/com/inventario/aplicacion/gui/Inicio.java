package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.aplicacion.InventarioApp;
import com.inventario.datas.DatosGeneral;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Vista;
import com.inventario.util.DateUtil;
import com.inventario.util.Format;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Enrique
 */
public class Inicio extends javax.swing.JPanel implements Vista {

    private static final Logger log = LoggerFactory.getLogger(Inicio.class);

    private Aplicacion app;
    private DatosGeneral dGeneral;
    private EventoTableModel modelo;

    public Inicio() {
        initComponents();
    }

    @Override
    public void inicializar(Aplicacion app) throws InventarioException {
        this.app = app;
        dGeneral = (DatosGeneral) app.getDatos(InventarioApp.AD_GENERAL);

        // inicializar el modelo
        modelo = new EventoTableModel(null);
        jtEventos.setModel(modelo);
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

    private void cargar(Date inicio, Date fin) {
        try {
            log.info("Consultando de {} hasta {}",
                    Format.DATETIME.format(inicio),
                    Format.DATETIME.format(fin));
            modelo.setItems(dGeneral.getPendientes(inicio, fin));
        } catch (InventarioException ex) {
            new AppMensaje("No se pudo listar los eventos pendientes.", ex).mostrar(this);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspTabla = new javax.swing.JScrollPane();
        jtEventos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jbPendientes = new javax.swing.JButton();
        jbParaHoy = new javax.swing.JButton();
        jbParaEstaSemana = new javax.swing.JButton();
        jbSigSemana = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jbReportar = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jspTabla.setViewportView(jtEventos);

        add(jspTabla, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(100, 34));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        jbPendientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/search.png"))); // NOI18N
        jbPendientes.setText("Pendientes");
        jbPendientes.setPreferredSize(new java.awt.Dimension(120, 30));
        jbPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPendientesActionPerformed(evt);
            }
        });
        jPanel1.add(jbPendientes);

        jbParaHoy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/search.png"))); // NOI18N
        jbParaHoy.setText("Para hoy");
        jbParaHoy.setPreferredSize(new java.awt.Dimension(120, 30));
        jbParaHoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbParaHoyActionPerformed(evt);
            }
        });
        jPanel1.add(jbParaHoy);

        jbParaEstaSemana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/search.png"))); // NOI18N
        jbParaEstaSemana.setText("Para esta semana");
        jbParaEstaSemana.setPreferredSize(new java.awt.Dimension(160, 30));
        jbParaEstaSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbParaEstaSemanaActionPerformed(evt);
            }
        });
        jPanel1.add(jbParaEstaSemana);

        jbSigSemana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/search.png"))); // NOI18N
        jbSigSemana.setText("Siguiente semana");
        jbSigSemana.setPreferredSize(new java.awt.Dimension(160, 30));
        jbSigSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSigSemanaActionPerformed(evt);
            }
        });
        jPanel1.add(jbSigSemana);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setPreferredSize(new java.awt.Dimension(100, 34));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        jbReportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/accept.png"))); // NOI18N
        jbReportar.setText("Reportar");
        jbReportar.setPreferredSize(new java.awt.Dimension(120, 30));
        jbReportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbReportarActionPerformed(evt);
            }
        });
        jPanel2.add(jbReportar);

        add(jPanel2, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jbPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPendientesActionPerformed
        cargar(null, DateUtil.getStartOfDay(DateUtil.getNow()));
    }//GEN-LAST:event_jbPendientesActionPerformed

    private void jbParaHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbParaHoyActionPerformed
        Date now = DateUtil.getNow();
        cargar(DateUtil.getStartOfDay(now), DateUtil.getEndOfDay(now));
    }//GEN-LAST:event_jbParaHoyActionPerformed

    private void jbParaEstaSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbParaEstaSemanaActionPerformed
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, 1);
        Date tm = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, 7 - c.get(Calendar.DAY_OF_WEEK)); // El resto de la semana
        cargar(DateUtil.getStartOfDay(tm), DateUtil.getEndOfDay(c.getTime()));
    }//GEN-LAST:event_jbParaEstaSemanaActionPerformed

    private void jbSigSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSigSemanaActionPerformed
        Calendar c = Calendar.getInstance();
        c.add(Calendar.WEEK_OF_YEAR, 1); // Avanzar una semana
        c.set(Calendar.DAY_OF_WEEK, 1); // Moverse al primer día
        Date ini = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, 7); // Moverse al último día
        cargar(DateUtil.getStartOfDay(ini), DateUtil.getStartOfDay(c.getTime()));
    }//GEN-LAST:event_jbSigSemanaActionPerformed

    private void jbReportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbReportarActionPerformed
        if (jtEventos.getSelectedRow() != -1) {
            app.getBus().put(InventarioApp.BUS_EVENTO, modelo.getRow(jtEventos.getSelectedRow()));
            app.mostrarTarea(InventarioApp.REPORTE);
        }
    }//GEN-LAST:event_jbReportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbParaEstaSemana;
    private javax.swing.JButton jbParaHoy;
    private javax.swing.JButton jbPendientes;
    private javax.swing.JButton jbReportar;
    private javax.swing.JButton jbSigSemana;
    private javax.swing.JScrollPane jspTabla;
    private javax.swing.JTable jtEventos;
    // End of variables declaration//GEN-END:variables
}
