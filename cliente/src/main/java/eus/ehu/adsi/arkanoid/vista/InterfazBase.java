package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.Boton;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaTitulo;

public class InterfazBase extends JPanel {
	Image icon;
	public JPanel panelInicial;
	public JPanel panelPrincipal;
	private Boton volverButton;
	public JPanel panelCabecera;
	public JPanel panelIdentidades;
	private EtiquetaTitulo tituloPersonalizar;
	/**
	 * Create the panel.
	 */
	public InterfazBase(String texto) {
		icon = new ImageIcon(getClass().getResource("/fondo_arkanoid.png")).getImage();
		this.setLayout(new BorderLayout());
		panelInicial = new JPanel();
		panelInicial.setBackground(new Color(0,0,0,0));
		this.add(panelInicial, BorderLayout.NORTH);		
		panelInicial.setLayout(new BorderLayout(0, 0));
		
		panelCabecera = new JPanel();
		panelCabecera.setBackground(new Color(0,0,0,0));
		panelInicial.add(panelCabecera, BorderLayout.WEST);
		
		volverButton = new Boton("<");
		panelCabecera.add(volverButton);
		
		tituloPersonalizar = new EtiquetaTitulo(texto);
		panelCabecera.add(tituloPersonalizar);
		
		panelIdentidades = new PnlIdentidad();
		panelIdentidades.setBackground(new Color(0,0,0,0));
		panelInicial.add(panelIdentidades, BorderLayout.EAST);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0,0,0,0));
		this.add(panelPrincipal, BorderLayout.CENTER);		
	}

	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
        g.drawImage(icon, 0, 0, this.getWidth(), this.getWidth(), null);
	}


	public void ocultarBotonRegreso() {
		panelCabecera.remove(volverButton);
	}
	
	public void ocultarPanelIdentidad() {
		panelInicial.remove(panelIdentidades);
	}
	
	public void setEventoRegreso(final JFrame ventanaAnterior) {
		volverButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (ventanaAnterior!=null){
					ventanaAnterior.setVisible(true);
					ventanaAnterior.setLocationRelativeTo(null);
				}
				JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(e.getComponent());
				if (topFrame instanceof Observer)
					Arkanoid.getArkanoid().deleteObserver((Observer) topFrame);
				topFrame.dispose();
			}
		});
	}
	
	public void setIdentificado(boolean id){
		((PnlIdentidad)panelIdentidades).setIdentificado(id);
	}


	public void centrarCabecera() {
		panelInicial.setLayout(new CardLayout());
	}
}
