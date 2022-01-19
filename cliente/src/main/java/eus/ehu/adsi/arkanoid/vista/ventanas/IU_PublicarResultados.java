package eus.ehu.adsi.arkanoid.vista.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JButton;

public class IU_PublicarResultados extends JFrame {

	private JPanel pnlPrincipal;
	private JPanel pnlCabecera;
	private JPanel pnlMain;
	private JLabel lblHeader;
	private JLabel lblTextoAdicional;
	private JButton btnTwitter;
	private JButton btnFacebook;
	private JButton btnMail;


	/**
	 * Create the frame.
	 */
	public IU_PublicarResultados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(pnlPrincipal);
		pnlPrincipal.add(getPnlCabecera(), BorderLayout.NORTH);
		pnlPrincipal.add(getPnlMain(), BorderLayout.CENTER);
		setVisible(true);
	}

	private JPanel getPnlCabecera() {
		if (pnlCabecera == null) {
			pnlCabecera = new JPanel();
			pnlCabecera.setLayout(new GridLayout(2, 1, 0, 0));
			pnlCabecera.add(getLblHeader());
			pnlCabecera.add(getLblTextoAdicional());
		}
		return pnlCabecera;
	}
	private JPanel getPnlMain() {
		if (pnlMain == null) {
			pnlMain = new JPanel();
			pnlMain.setLayout(new GridLayout(3, 1, 0, 0));
			pnlMain.add(getBtnTwitter());
			pnlMain.add(getBtnFacebook());
			pnlMain.add(getBtnMail());
		}
		return pnlMain;
	}
	private JLabel getLblHeader() {
		if (lblHeader == null) {
			lblHeader = new JLabel("Redes Sugeridas");
		}
		return lblHeader;
	}
	private JLabel getLblTextoAdicional() {
		if (lblTextoAdicional == null) {
			lblTextoAdicional = new JLabel("\u00A1Comparte tus resultados con tus amigos y r\u00E9tales!");
		}
		return lblTextoAdicional;
	}
	private JButton getBtnTwitter() {
		if (btnTwitter == null) {
			btnTwitter = new JButton("Twitter");
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
			btnFacebook = new JButton("Facebook");
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
			btnMail = new JButton("E-Mail");
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
