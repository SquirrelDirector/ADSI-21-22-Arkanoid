package eus.ehu.adsi.arkanoid.vista;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class InterfazBase extends JPanel {

	/**
	 * Create the panel.
	 */
	public InterfazBase() {

	}

	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("main/resources/fondo_arkanoid.png")), 0, 0, null);
	}
}
