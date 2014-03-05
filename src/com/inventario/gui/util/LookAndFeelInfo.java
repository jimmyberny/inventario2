package com.inventario.gui.util;

import com.inventario.interfaces.Identificable;

/**
 *
 * @author None
 */
public class LookAndFeelInfo implements Identificable<String> {

    private final String className;
    private final String name;

    public LookAndFeelInfo(String className, String name) {
        this.className = className;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getId() {
        return className;
    }
}
