package com.inventario.util;

import com.inventario.interfaces.gui.OptionAbstractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jimmy
 */
public class Option<T> extends OptionBean<T> {

    public static Logger log = LoggerFactory.getLogger(Option.class);

    public Option(T id, String label) {
        super(id, label);
    }

    public boolean match(T id) {
        return getId() == id;
    }

    public boolean match(Option<T> option) {
        return match(option.getId());
    }

    public boolean match(OptionAbstractModel oam) {
        return oam.getSelected() != null && oam.getSelected().getId().equals(getId());
    }

    public static class FixedOptionsModel<T> extends OptionArrayModel<Option<T>, T> {

        public FixedOptionsModel(Option[] items) {
            super(items);
        }
    }
}
