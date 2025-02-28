package eus.ehu.adsi.arkanoid.vista.ventanas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.vista.IU_Identificarse;
import eus.ehu.adsi.arkanoid.vista.IU_Inicial;
import eus.ehu.adsi.arkanoid.vista.InterfazBase;
import eus.ehu.adsi.arkanoid.vista.Tablero;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.Boton;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaNormal;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.PanelNegro;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

@SuppressWarnings("deprecation")
public class VentanaFinPartida extends JFrame implements Observer{
	//TODO Observer de Inicio de sesi�n
	private InterfazBase uiBase;
	private JPanel pnlMain;
	private JPanel pnlDatos;
	private JPanel pnlBotones;
	private JButton btnInicio;
	private JButton btnNuevaPartida;
	private JButton btnCompartir;
	private JPanel pnlEstatico;
	private JPanel pnlDinamico;
	private JSONObject datos;

	/**
	 * Create the frame.
	 */
	public VentanaFinPartida(boolean haGanado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 525);
		setLocationRelativeTo(null);
		if(haGanado) {
			uiBase = new InterfazBase("¡Enhorabuena!");	
		} else {
			uiBase = new InterfazBase("Buen intento...");
		}
		
		uiBase.ocultarPanelIdentidad();
		uiBase.ocultarBotonRegreso();
		setContentPane(uiBase);
		uiBase.panelPrincipal.setLayout(new CardLayout());
		uiBase.panelPrincipal.add(getPnlMain());
		gestionarEventos();
		Arkanoid.getArkanoid().addObserver(this);
		
		this.setVisible(true);
		this.getDatosPartida();
	}

	private JPanel getPnlMain() {
		if (pnlMain == null) {
			pnlMain = new PanelNegro();
			pnlMain.setLayout(new BorderLayout(0, 0));
			pnlMain.setBackground(new Color(0,0,0,0));
			pnlMain.add(getPnlDatos(), BorderLayout.CENTER);
			pnlMain.add(getPnlBotones(), BorderLayout.SOUTH);
		}
		return pnlMain;
	}
	
	private JPanel getPnlDatos() {
		if (pnlDatos == null) {
			pnlDatos = new JPanel();
			pnlDatos.setLayout(new BorderLayout(0, 0));
			pnlDatos.setBorder(new EmptyBorder(25,25,25,25));
			pnlDatos.setBackground(new Color(0,0,0,0));
			pnlDatos.add(getPnlEstatico(), BorderLayout.WEST);
			pnlDatos.add(getPnlDinamico(), BorderLayout.CENTER);
		}
		return pnlDatos;
	}
	private JPanel getPnlBotones() {
		if (pnlBotones == null) {
			pnlBotones = new JPanel();
			pnlBotones.setBackground(new Color(0,0,0,0));
			pnlBotones.setLayout(new GridLayout(1, 0, 20, 20));
			pnlBotones.add(getBtnInicio());
			pnlBotones.add(getBtnNuevaPartida());
			pnlBotones.add(getBtnCompartir());
		}
		return pnlBotones;
	}
	private JButton getBtnInicio() {
		if (btnInicio == null) {
			btnInicio = new Boton("Inicio");
		}
		return btnInicio;
	}
	private JButton getBtnNuevaPartida() {
		if (btnNuevaPartida == null) {
			btnNuevaPartida = new Boton("Nueva partida");
		}
		return btnNuevaPartida;
	}
	private JButton getBtnCompartir() {
		if (btnCompartir == null) {
			btnCompartir = new Boton("Compartir");
		}
		return btnCompartir;
	}
	
	private void getDatosPartida() {
		datos = Arkanoid.getArkanoid().getResultadosPartida();
		if(datos.has("tiempoPartida")) {
			EtiquetaNormal datTiempo=new EtiquetaNormal(datos.get("tiempoPartida").toString());
			getPnlEstatico().add(new EtiquetaNormal("Tiempo"));
			getPnlDinamico().add(datTiempo);
			datTiempo.setHorizontalAlignment(JLabel.CENTER);
		}
		if(datos.has("puntuacionConseguida")) {
			EtiquetaNormal puntConseguida;
			int punt=Integer.parseInt(datos.get("puntuacionConseguida").toString());
			puntConseguida=new EtiquetaNormal(punt+"");
			getPnlEstatico().add(new EtiquetaNormal("Puntos obtenidos"));
			getPnlDinamico().add(puntConseguida);
			puntConseguida.setHorizontalAlignment(JLabel.CENTER);
		}
		if(datos.has("mejorTiempo")) {
			int mejorTiempoInt = Integer.parseInt(datos.get("mejorTiempo").toString());
			EtiquetaNormal mejorTiempo;
			if(mejorTiempoInt==Integer.MAX_VALUE) {
				mejorTiempo=new EtiquetaNormal("-");
			}else {
				mejorTiempo=new EtiquetaNormal(mejorTiempoInt+"");
			}
			getPnlEstatico().add(new EtiquetaNormal("Record tiempo"));
			getPnlDinamico().add(mejorTiempo);
			mejorTiempo.setHorizontalAlignment(JLabel.CENTER);
		}
		if(datos.has("mejorPuntuacion")) {
			EtiquetaNormal mejorPuntuacion;
			int puntuacion=Integer.parseInt(datos.get("mejorPuntuacion").toString());
			if(puntuacion==0) {
				mejorPuntuacion=new EtiquetaNormal("-");	
			}else {
				mejorPuntuacion=new EtiquetaNormal(puntuacion+"");
			}
			
			getPnlEstatico().add(new EtiquetaNormal("Record puntos"));
			getPnlDinamico().add(mejorPuntuacion);
			mejorPuntuacion.setHorizontalAlignment(JLabel.CENTER);
		}
	}
	
	private JPanel getPnlEstatico() {
		if (pnlEstatico == null) {
			pnlEstatico = new PanelNegro();
			pnlEstatico.setLayout(new GridLayout(4, 1, 0, 0));
		}
		return pnlEstatico;
	}
	private JPanel getPnlDinamico() {
		if (pnlDinamico == null) {
			pnlDinamico = new PanelNegro();
			pnlDinamico.setLayout(new GridLayout(4, 1, 0, 0));
		}
		return pnlDinamico;
	}
	
	private void gestionarEventos() {
		btnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				Arkanoid.getArkanoid().deleteObservers();
				new IU_Inicial().setVisible(true);
				dispose();
			}
		});
		
		btnNuevaPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				Arkanoid.getArkanoid().pararJuego();
				new Tablero();
				dispose();
			}
		});
		
		btnCompartir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(datos.has("mejorTiempo") && datos.has("mejorPuntuacion")){
					new IU_PublicarResultados();
				}else {
					IU_Identificarse.getMiIU_Identificarse().mostrarVentana();
				}
			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		if ((boolean)arg)
			new IU_PublicarResultados();
	}
}
