/*
 *  inventario | Mar 5, 2014 7:04:09 AM 
 */
package com.inventario.aplicacion.gui;

import com.inventario.modelo.Evento;
import com.inventario.util.Format;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author None
 */
public class EventoTableModel extends AbstractTableModel {

    private List<Evento> items;

    public EventoTableModel(List<Evento> items) {
        if (items == null) {
            items = new ArrayList<>(0);
        }
        this.items = items;
    }

    public void setItems(List<Evento> items) {
        this.items = items;
        fireTableDataChanged();
    }

    public Evento getRow(int idx) {
        return items.get(idx);
    }

    public void update(int idx) {
        fireTableRowsUpdated(idx, idx);
    }

    public void remove(int pos) {
        fireTableRowsDeleted(pos, pos);
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Evento";
            case 1:
                return "Equipo";
            case 2:
                return "Fecha programada";
            case 3:
                return "Fecha realizado";
            default:
                return "Desconocido";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Evento rep = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rep.toString();
            case 1:
                return Format.OBJECT.format(rep.getEquipo());
            case 2:
                return Format.DATE.format(rep.getFecha());
            case 3:
                return Format.DATE.format(rep.getFechaRealizado());
            default:
                return "";
        }
    }

}
