package com.inventario.aplicacion.buscadores;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class ModeloLista<T> extends AbstractListModel<T> {

	private List<T> items;

	public ModeloLista() {
		items = new ArrayList<>(0);
	}

	public void setItems(List<T> lista) {
		if (lista == null) {
			items = new ArrayList<>(0);
		} else {
			items = lista;
		}
		fireContentsChanged(this, 0, items.size() - 1);
	}

	public List<T> getItems() {
		return items;
	}

	@Override
	public int getSize() {
		return items.size();
	}

	@Override
	public T getElementAt(int index) {
		return items.get(index);
	}

	public void refresh(int index) {
		fireContentsChanged(this, index, index);
	}

	public void remove(int index) {
		items.remove(index);
		fireIntervalRemoved(this, index, index);
	}

	public void add(T item) {
		if (items.add(item)) {
			fireIntervalAdded(this, items.size() - 1, items.size() - 1);
		}

	}

}
