package eus.ehu.adsi.arkanoid.vista;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class PnlIdentidad extends JPanel {
	private JPanel pnlIdentidad;
	private JPanel pnlFoto;
	private JPanel pnlNoIdentificado;
	private JLabel lblIdentidad;
	private ImageIcon imagen;
	private JLabel picLabel;
	private JPanel pnlIdentificado;
	private JLabel lblIdentificado;
	private JPanel pnlCerrarSesionGeneral;
	private JButton btnCerrarSesion;
	/**
	 * Create the panel.
	 */
	public PnlIdentidad() {
		imagen= new ImageIcon(getClass().getResource("/imagenesAvatar/Avatar1.png"));
		setBackground(Color.BLACK);
		setLayout(new CardLayout(0, 0));
		add(getPnlIdentidad());

	}

	private JPanel getPnlIdentidad() {
		if (pnlIdentidad == null) {
			pnlIdentidad = new JPanel();
			pnlIdentidad.setLayout(new BorderLayout(0, 0));
			pnlIdentidad.add(getPnlFoto(), BorderLayout.WEST);
			pnlIdentidad.add(getPnlNoIdentificado(), BorderLayout.CENTER);
			pnlIdentidad.add(getPnlIdentificado(), BorderLayout.CENTER);
		}
		return pnlIdentidad;
	}
	private JPanel getPnlFoto() {
		if (pnlFoto == null) {
			pnlFoto = new JPanel();
			pnlFoto.setBackground(Color.BLACK);
			pnlFoto.setLayout(new GridLayout(1, 1, 0, 0));
			picLabel= new JLabel(imagen);
			picLabel.setHorizontalAlignment(SwingConstants.CENTER);
			picLabel.setVerticalAlignment(SwingConstants.CENTER);
			pnlFoto.add(picLabel);
		}
		return pnlFoto;
	}
	private JPanel getPnlNoIdentificado() {
		if (pnlNoIdentificado == null) {
			pnlNoIdentificado = new JPanel();
			pnlNoIdentificado.setBackground(Color.BLACK);
			pnlNoIdentificado.setLayout(new GridLayout(0, 1, 0, 0));
			pnlNoIdentificado.add(getLblIdentidad());
			pnlNoIdentificado.setVisible(false);
		}
		return pnlNoIdentificado;
	}
	private JLabel getLblIdentidad() {
		if (lblIdentidad == null) {
			lblIdentidad = new EtiquetaNormal("Iniciar Sesi\u00F3n");
			lblIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
			lblIdentidad.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblIdentidad.setForeground(Color.WHITE);
		}
		return lblIdentidad;
	}
	private JPanel getPnlIdentificado() {
		if (pnlIdentificado == null) {
			pnlIdentificado = new JPanel();
			pnlIdentificado.setBackground(Color.BLACK);
			pnlIdentificado.setLayout(new BorderLayout(0, 0));
			pnlIdentificado.add(getLblIdentificado(), BorderLayout.CENTER);
			pnlIdentificado.add(getPnlCerrarSesionGeneral(), BorderLayout.SOUTH);
		}
		return pnlIdentificado;
	}
	private JLabel getLblIdentificado() {
		if (lblIdentificado == null) {
			lblIdentificado = new EtiquetaTitulo("Nombre_Usuario_Identificado");
			lblIdentificado.setFont(new Font("Good Times", Font.PLAIN, 20));
			lblIdentificado.setHorizontalAlignment(SwingConstants.CENTER);
			lblIdentificado.setForeground(Color.WHITE);
			lblIdentificado.setBackground(Color.BLACK);
		}
		return lblIdentificado;
	}
	private JPanel getPnlCerrarSesionGeneral() {
		if (pnlCerrarSesionGeneral == null) {
			pnlCerrarSesionGeneral = new JPanel();
			pnlCerrarSesionGeneral.setBackground(Color.BLACK);
			pnlCerrarSesionGeneral.setLayout(new BorderLayout(0, 0));
			pnlCerrarSesionGeneral.add(getBtnCerrarSesion(), BorderLayout.EAST);
		}
		return pnlCerrarSesionGeneral;
	}
	private JButton getBtnCerrarSesion() {
		if (btnCerrarSesion == null) {
			btnCerrarSesion = new Boton("Cerrar Sesi\u00F3n");
			btnCerrarSesion.setText("Log Out");
		}
		return btnCerrarSesion;
	}
}
