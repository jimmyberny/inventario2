package com.inventario.util;

import com.inventario.interfaces.Identificable;

/**
 *
 * @author jimmy
 */
public abstract class OptionBean<T> implements Identificable<T> {

	private final T id;
	private final String label;

	public OptionBean(T id, String label) {
		this.id = id;
		this.label = label;
	}

	@Override
	public T getId() {
		return id;
	}

	@Override
	public String toString() {
		return label;
	}
}
