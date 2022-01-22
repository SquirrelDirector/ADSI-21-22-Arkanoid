package eus.ehu.adsi.arkanoid.vista.ventanas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.vista.InterfazBase;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.Boton;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaNormal;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JButton;

public class IU_PublicarResultados extends JFrame {

	private InterfazBase pnlPrincipal;
	private JPanel pnlCabecera;
	private JPanel pnlMain;
	private JLabel lblHeader;
	private JLabel lblTextoAdicional;
	private JButton btnTwitter;
	private JButton btnFacebook;
	private JButton btnMail;

	public static void main(String[] args) {
		new IU_PublicarResultados().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public IU_PublicarResultados() {
		setBounds(100, 100, 550, 450);
		pnlPrincipal = new InterfazBase("Redes Sugeridas");
		//pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		//pnlPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(pnlPrincipal);
		pnlPrincipal.centrarCabecera();
		pnlPrincipal.ocultarBotonRegreso();
		pnlPrincipal.ocultarPanelIdentidad();
		//pnlPrincipal.add(getPnlCabecera(), BorderLayout.NORTH);
		pnlPrincipal.panelPrincipal.setLayout(new CardLayout());
		pnlPrincipal.panelPrincipal.add(getPnlMain());
		setVisible(true);
	}

	private JPanel getPnlMain() {
		if (pnlMain == null) {
			pnlMain = new JPanel();
			pnlMain.setBackground(new Color(0,0,0,0));
			pnlMain.setLayout(new GridLayout(4, 1, 10, 10));
			pnlMain.setBorder(new EmptyBorder(10,10,10,10));
			pnlMain.add(getLblTextoAdicional());
			pnlMain.add(getBtnTwitter());
			pnlMain.add(getBtnFacebook());
			pnlMain.add(getBtnMail());
		}
		return pnlMain;
	}
	
	private JLabel getLblTextoAdicional() {
		if (lblTextoAdicional == null) {
			lblTextoAdicional = new EtiquetaNormal("\u00A1Comparte tus resultados con tus amigos y r\u00E9tales!");
			lblTextoAdicional.setHorizontalAlignment(JLabel.CENTER);
		}
		return lblTextoAdicional;
	}
	private JButton getBtnTwitter() {
		if (btnTwitter == null) {
			btnTwitter = new Boton("Twitter");
			btnTwitter.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Arkanoid.getArkanoid().publicarResultados("twitter");
				}
			});
		}
		return btnTwitter;
	}
	private JButton getBtnFacebook() {
		if (btnFacebook == null) {
			btnFacebook = new Boton("Facebook");
			btnFacebook.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Arkanoid.getArkanoid().publicarResultados("facebook");
				}
			});
		}
		return btnFacebook;
	}
	private JButton getBtnMail() {
		if (btnMail == null) {
			btnMail = new Boton("E-Mail");
			btnMail.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Arkanoid.getArkanoid().publicarResultados("email");
				}
			});
		}
		return btnMail;
	}
}
