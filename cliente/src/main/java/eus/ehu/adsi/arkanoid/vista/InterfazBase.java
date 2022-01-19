package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class InterfazBase extends JPanel {
	Image icon;
	public JPanel panelCabecera;
	public JPanel panelPrincipal;
	/**
	 * Create the panel.
	 */
	public InterfazBase(String texto) {
		icon = new ImageIcon(getClass().getResource("/fondo_arkanoid.png")).getImage();
		this.setLayout(new BorderLayout());
		panelCabecera = new JPanel();
		panelCabecera.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelCabecera.setBackground(new Color(0,0,0,0));
		this.add(panelCabecera, BorderLayout.NORTH);
		
		Boton volverButton = new Boton("<");
		panelCabecera.add(volverButton);
		
		EtiquetaTitulo tituloPersonalizar = new EtiquetaTitulo(texto);
		panelCabecera.add(tituloPersonalizar);		
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0,0,0,200));
		this.add(panelPrincipal, BorderLayout.CENTER);
		
		//this.add(new Panel());
		
	}

	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
        g.drawImage(icon, 0, 0, this.getWidth(), this.getWidth(), null);
	}
}
