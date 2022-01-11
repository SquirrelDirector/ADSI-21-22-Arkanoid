package eus.ehu.adsi.arkanoid.vista;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class InterfazBase extends JPanel {
	Image icon;
	/**
	 * Create the panel.
	 */
	public InterfazBase() {
		icon = new ImageIcon(getClass().getClassLoader().getResource("fondo_arkanoid.png")).getImage();
	}

	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
        g.drawImage(icon, 0, 0, null);
	}
}
