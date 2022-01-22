package eus.ehu.adsi.arkanoid.vista;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

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
	
	private boolean identificado;
	/**
	 * Create the panel.
	 */
	public PnlIdentidad() {
		imagen= new ImageIcon(getClass().getResource("/imagenesAvatar/Avatar1.png"));
		setBackground(new Color(0,0,0,0));
		setLayout(new CardLayout(0, 0));
		add(getPnlIdentidad());

	}

	private JPanel getPnlIdentidad() {
		if (pnlIdentidad == null) {
			pnlIdentidad = new JPanel();
			pnlIdentidad.setLayout(new BorderLayout(0, 0));
			pnlIdentidad.setBackground(new Color(2,4,40));
			pnlIdentidad.setBorder(new EmptyBorder(5,10,5,10));
			pnlIdentidad.add(getPnlFoto(), BorderLayout.WEST);
			pnlIdentidad.add(getPnlNoIdentificado(), BorderLayout.CENTER);
			//pnlIdentidad.add(getPnlIdentificado(), BorderLayout.CENTER);
			pnlIdentidad.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					IU_Identificarse.getMiIU_Identificarse().mostrarVentana();
				}
			});
		}
		return pnlIdentidad;
	}
	private JPanel getPnlFoto() {
		if (pnlFoto == null) {
			pnlFoto = new JPanel();
			pnlFoto.setBackground(new Color(0,0,0,0));
			pnlFoto.setLayout(new GridLayout(1, 1, 0, 0));
			pnlFoto.setBorder(new EmptyBorder(0,0,0,20));
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
			pnlNoIdentificado.setBackground(new Color(0,0,0,0));
			pnlNoIdentificado.setLayout(new GridLayout(0, 1, 0, 0));
			pnlNoIdentificado.add(getLblIdentidad());
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
			pnlIdentificado.setBackground(new Color(0,0,0,0));
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
			lblIdentificado.setBackground(new Color(0,0,0,0));
		}
		return lblIdentificado;
	}
	private JPanel getPnlCerrarSesionGeneral() {
		if (pnlCerrarSesionGeneral == null) {
			pnlCerrarSesionGeneral = new JPanel();
			pnlCerrarSesionGeneral.setBackground(new Color(0,0,0,0));
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
		btnCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				Arkanoid.getArkanoid().cerrarSesion();
			}
		});
		return btnCerrarSesion;
	}
	
	public void setIdentificado(boolean id){
		if (!identificado&&id){
			JSONObject perfil=Arkanoid.getArkanoid().getPerfil();
			imagen= new ImageIcon(getClass().getResource(perfil.getString("foto")));
			getLblIdentificado().setText(perfil.getString("nombre"));
			pnlIdentidad.remove(pnlNoIdentificado);
			pnlIdentidad.add(getPnlIdentificado());
		}else if (identificado&&!id){
			pnlIdentidad.remove(pnlIdentificado);
			pnlIdentidad.add(getPnlNoIdentificado());
		}
		identificado=id;
		repaint();
		revalidate();
	}
}
