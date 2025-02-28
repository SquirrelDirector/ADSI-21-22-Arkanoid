package eus.ehu.adsi.arkanoid.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

import org.json.JSONArray;
import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.Boton;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaNormal;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.ScrollPane;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.PanelNegro;

import java.awt.GridLayout;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Ranking implements Observer{

	private JFrame frame;
	private JSONArray ranking = new JSONArray();
	private JPanel panel = new PanelNegro();
	private boolean personal = false;
	private boolean identificado = false;
	InterfazBase ib;
	Boton botonPersonal;

	/**
	 * Launch the application.
	 */
	public void mostrarVentana(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ranking window = new Ranking();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ranking() {
		initialize();
		frame.repaint();
		frame.revalidate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Arkanoid.getArkanoid().addObserver(this);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 668, 443);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

		ib = new InterfazBase("RANKING");
		frame.getContentPane().add(ib, BorderLayout.CENTER);
		ib.setIdentificado(Arkanoid.getArkanoid().isIdentificado());
		ranking=Arkanoid.getArkanoid().mostrarRanking(0, personal);
		desplegarRanking(ranking);
		
		JPanel panelGeneral = new JPanel();
		panelGeneral.setBackground(new Color(0,0,0,0));
		panelGeneral.setLayout(null);
		frame.setLocationRelativeTo(null);

		ib.panelPrincipal.add(panelGeneral);
		ib.panelPrincipal.setLayout(new GridLayout(0, 1, 0, 0));
		ib.setEventoRegreso(new IU_Inicial());
		
		JPanel textoRanking = new JPanel();
		textoRanking.setBorder(new EmptyBorder(0, 10, 0, 10));
		textoRanking.setBounds(0, 6, 668, 67);
		textoRanking.setBackground(new Color(0,0,0,0));
		panelGeneral.add(textoRanking);

		Boton botonGlobal = new Boton("Global");
		botonGlobal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				personal = false;
				ranking = Arkanoid.getArkanoid().mostrarRanking(0, personal);
				desplegarRanking(ranking);
			}
		});
		textoRanking.setLayout(new GridLayout(0, 2, 10, 0));
		textoRanking.add(botonGlobal);
		
		
		botonPersonal = new Boton("Personal");
		botonPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Arkanoid.getArkanoid().isIdentificado()){
					IU_Identificarse.getMiIU_Identificarse().mostrarVentana();
					identificado = true;
				}
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				personal = true;
				ranking = Arkanoid.getArkanoid().mostrarRanking(0, personal);
				desplegarRanking(ranking);
			}
		});
		textoRanking.add(botonPersonal);
		
		JPanel panelBotonesNiveles = new JPanel();
		panelBotonesNiveles.setBounds(0, 85, 668, 47);
		panelBotonesNiveles.setBackground(new Color(0,0,0,0));
		panelGeneral.add(panelBotonesNiveles);
		
		Boton botonAbsolute = new Boton("Absolute");
		botonAbsolute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ranking = Arkanoid.getArkanoid().mostrarRanking(0, personal);
				desplegarRanking(ranking);
			}

		});
		panelBotonesNiveles.add(botonAbsolute);
		
		Boton botonRookie = new Boton("Rookie");
		botonRookie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				ranking = Arkanoid.getArkanoid().mostrarRanking(1, personal);
				desplegarRanking(ranking);
			}
		});
		panelBotonesNiveles.add(botonRookie);
		
		Boton botonStandard = new Boton("Standard");
		botonStandard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				ranking = Arkanoid.getArkanoid().mostrarRanking(2, personal);
				desplegarRanking(ranking);
			}
		});
		panelBotonesNiveles.add(botonStandard);
		
		Boton botonExpert = new Boton("Expert");
		botonExpert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				ranking = Arkanoid.getArkanoid().mostrarRanking(3, personal);
				desplegarRanking(ranking);
			}
		});
		panelBotonesNiveles.add(botonExpert);
		
		Boton botonImposible = new Boton("Impossible");
		botonImposible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				ranking = Arkanoid.getArkanoid().mostrarRanking(4, personal);
				desplegarRanking(ranking);
			}
		});
		panelBotonesNiveles.add(botonImposible);
		
		PanelNegro panelPuntuaciones = new PanelNegro();
		panelPuntuaciones.setBounds(26, 144, 619, 207);
		panelGeneral.add(panelPuntuaciones);
		panelPuntuaciones.setLayout(new BorderLayout(0, 0));
		textoRanking.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		PanelNegro panelTitulos = new PanelNegro();
		panelPuntuaciones.add(panelTitulos, BorderLayout.NORTH);
		panelTitulos.setLayout(new GridLayout(0,3,0,0));
		
		EtiquetaNormal labelNombre = new EtiquetaNormal("Nombre");
		panelTitulos.add(labelNombre);
		EtiquetaNormal labelTiempo = new EtiquetaNormal("Tiempo");
		panelTitulos.add(labelTiempo);
		EtiquetaNormal labelPuntos = new EtiquetaNormal("Puntos");
		panelTitulos.add(labelPuntos);
		
		ScrollPane scrollPane = new ScrollPane();
		panelPuntuaciones.add(scrollPane, BorderLayout.CENTER);
		
		
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
	}
	
	private void desplegarRanking(JSONArray ranking) {
		panel.removeAll();
		for(int i = 0; i<ranking.length(); i++) {
			JSONObject puntuacion = ranking.getJSONObject(i);
			String nombreUsuario = puntuacion.getString("usuario");
			int tiempo = puntuacion.getInt("tiempo");
			int puntos = puntuacion.getInt("puntuacion");
			
			EtiquetaNormal labelNombre = new EtiquetaNormal(nombreUsuario);
			panel.add(labelNombre);
			EtiquetaNormal labelTiempo = new EtiquetaNormal(tiempo+"");
			panel.add(labelTiempo);
			EtiquetaNormal labelPuntos = new EtiquetaNormal(""+puntos);
			panel.add(labelPuntos);
		}
		panel.revalidate();
		panel.repaint();
	}

	

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Boolean){
			ib.setIdentificado((boolean) arg1);
			if((boolean) arg1 && identificado){ //volver al ranking global
				identificado = false;
				Arkanoid.getArkanoid().deleteObserver(this);
				ranking = Arkanoid.getArkanoid().mostrarRanking(0, true);
				desplegarRanking(ranking);
			}
			else if(!(boolean) arg1){
				identificado = false;
				personal=false;
				ranking = Arkanoid.getArkanoid().mostrarRanking(0, personal);
				desplegarRanking(ranking);
			}
		}
	}
}
