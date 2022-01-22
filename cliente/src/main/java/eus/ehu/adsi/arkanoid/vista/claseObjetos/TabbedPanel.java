package eus.ehu.adsi.arkanoid.vista.claseObjetos;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedPanel extends JTabbedPane{
	
	public TabbedPanel() {
		this.setBackground(new Color(2,7,41));
		this.setTabPlacement(JTabbedPane.TOP);
		this.setForeground(new Color(0xFFFFFF));
	}

}
