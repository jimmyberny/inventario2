package com.inventario.util;

/**
 *
 * @author jimmy
 */
public class OptionGroup<T> {

	private final Option<T>[] ops;

	public OptionGroup(Option<T>... option) {
		this.ops = option;
	}

	public Option.FixedOptionsModel getModel() {
		return new Option.FixedOptionsModel(ops);
	}

	public String str(T key) {
		if (key == null) {
			return null;
		}
		for (Option o : ops) {
			if (o.match(key)) {
				return o.toString();
			}
		}
		return "Indefinido";
	}
}
