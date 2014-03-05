package com.inventario.aplicacion.gui;

import com.inventario.listener.MonitorListener;
import com.inventario.listener.MonitorViewInterface;
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author Zulma
 */
public class JLabelMonitor extends JLabel implements MonitorViewInterface {

	public JLabelMonitor() {
		super();
	}
	
    public JLabelMonitor(MonitorListener monitor) {
        super();
        // setBorder(BorderFactory.createLineBorder(Color.blue));
        setPreferredSize(new Dimension(40, 40));
        monitor.addView(this);
    }
    
    public void addMonitor(MonitorListener monitor) {
        monitor.addView(this);
    }

    @Override
    public void changeState(boolean state) {
        setText(state ? "*" : "");
    }
}
