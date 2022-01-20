package eus.ehu.adsi.arkanoid.vista.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class VentanaFinPartida extends JFrame {
	//TODO Observer de Inicio de sesión
	private JPanel pnlPrincipal;
	private JPanel pnlEnhorabuena;
	private JPanel pnlMain;
	private JLabel lblNewLabel;
	private JPanel pnlDatos;
	private JPanel pnlBotones;
	private JButton btnInicio;
	private JButton btnNuevaPartida;
	private JButton btnCompartir;
	private JPanel pnlEstatico;
	private JPanel pnlDinamico;
	private JSONObject datos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaFinPartida frame = new VentanaFinPartida();
					frame.setVisible(true);
					frame.getDatosPartida();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaFinPartida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 381);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(new BorderLayout(0, 0));
		pnlPrincipal.add(getPnlEnhorabuena(), BorderLayout.NORTH);
		pnlPrincipal.add(getPnlMain(), BorderLayout.CENTER);
		gestionarEventos();
		
	}

	private JPanel getPnlEnhorabuena() {
		if (pnlEnhorabuena == null) {
			pnlEnhorabuena = new JPanel();
			pnlEnhorabuena.setLayout(new GridLayout(0, 1, 0, 0));
			pnlEnhorabuena.add(getLblNewLabel());
		}
		return pnlEnhorabuena;
	}
	private JPanel getPnlMain() {
		if (pnlMain == null) {
			pnlMain = new JPanel();
			pnlMain.setLayout(new BorderLayout(0, 0));
			pnlMain.add(getPnlDatos(), BorderLayout.CENTER);
			pnlMain.add(getPnlBotones(), BorderLayout.SOUTH);
		}
		return pnlMain;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Enhorabuena!");
		}
		return lblNewLabel;
	}
	private JPanel getPnlDatos() {
		if (pnlDatos == null) {
			pnlDatos = new JPanel();
			pnlDatos.setLayout(new BorderLayout(0, 0));
			pnlDatos.add(getPnlEstatico(), BorderLayout.WEST);
			pnlDatos.add(getPnlDinamico(), BorderLayout.CENTER);
		}
		return pnlDatos;
	}
	private JPanel getPnlBotones() {
		if (pnlBotones == null) {
			pnlBotones = new JPanel();
			pnlBotones.setLayout(new GridLayout(1, 0, 0, 0));
			pnlBotones.add(getBtnInicio());
			pnlBotones.add(getBtnNuevaPartida());
			pnlBotones.add(getBtnCompartir());
		}
		return pnlBotones;
	}
	private JButton getBtnInicio() {
		if (btnInicio == null) {
			btnInicio = new JButton("Inicio");
		}
		return btnInicio;
	}
	private JButton getBtnNuevaPartida() {
		if (btnNuevaPartida == null) {
			btnNuevaPartida = new JButton("Nueva partida");
		}
		return btnNuevaPartida;
	}
	private JButton getBtnCompartir() {
		if (btnCompartir == null) {
			btnCompartir = new JButton("Compartir");
		}
		return btnCompartir;
	}
	
	private void getDatosPartida() {
		datos = Arkanoid.getArkanoid().getResultadosPartida();
		if(datos.get("tiempoPartida")!=null) {
			getPnlEstatico().add(new JLabel("Tiempo"));
			getPnlDinamico().add(new JLabel(datos.get("tiempoPartida").toString()));
		}
		if(datos.get("puntuacionConseguida")!=null) {
			getPnlEstatico().add(new JLabel("Puntos obtenidos"));
			getPnlDinamico().add(new JLabel(datos.get("puntuacionConseguida").toString()));
		}
		if(datos.get("mejorTiempo")!=null) {
			getPnlEstatico().add(new JLabel("Record tiempo"));
			getPnlDinamico().add(new JLabel(datos.get("mejorTiempo").toString()));
		}
		if(datos.get("mejorPuntuacion")!=null) {
			getPnlEstatico().add(new JLabel("Record puntos"));
			getPnlDinamico().add(new JLabel(datos.get("mejorPuntuacion").toString()));
		}
	}
	
	private JPanel getPnlEstatico() {
		if (pnlEstatico == null) {
			pnlEstatico = new JPanel();
			pnlEstatico.setLayout(new GridLayout(4, 1, 0, 0));
		}
		return pnlEstatico;
	}
	private JPanel getPnlDinamico() {
		if (pnlDinamico == null) {
			pnlDinamico = new JPanel();
			pnlDinamico.setLayout(new GridLayout(4, 1, 0, 0));
		}
		return pnlDinamico;
	}
	
	private void gestionarEventos() {
		btnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				throw new UnsupportedOperationException("¡Implementar evento de clic en botón de inicio!");
			}
		});
		
		btnNuevaPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				throw new UnsupportedOperationException("¡Implementar evento de clic en botón de volver a jugar!");
			}
		});
		
		btnCompartir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(datos.get("mejorTiempo")!=null && datos.get("mejorPuntuacion")!=null) {
					new IU_PublicarResultados();
				}else {
					throw new UnsupportedOperationException("¡Se debe redirigir a Iniciar Sesión!");
				}
			}
		});
	}
}
