package com.inventario.util;

import com.inventario.interfaces.Identificable;
import com.inventario.interfaces.gui.OptionAbstractModel;

/**
 *
 * Created Apr 10, 2013, 4:28:51 PM At La Laja
 *
 * @author Berny
 */
public abstract class OptionArrayModel<T extends Identificable<U>, U> extends OptionAbstractModel<T, U> {

	private final T[] items;

	public OptionArrayModel(T[] items) {
		this.items = items;
	}

	@Override
	public T getItemById(U id) {
		T res = null;
		for (T t : items) {
			if (t.getId().equals(id)) {
				res = t;
				break;
			}
		}
		return res;
	}

	@Override
	public int getSize() {
		return items.length;
	}

	@Override
	public T getElementAt(int index) {
		return items[index];
	}

}
