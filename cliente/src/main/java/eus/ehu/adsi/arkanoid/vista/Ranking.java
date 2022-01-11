package eus.ehu.adsi.arkanoid.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class Ranking {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// puntuaciones de prueba
		
		JSONObject puntuacion1 = new JSONObject();
		puntuacion1.put("usuario", "Angela");
		puntuacion1.put("tiempo", "12345");
		puntuacion1.put("puntuacion", 9999);

		JSONObject puntuacion2 = new JSONObject();
		puntuacion2.put("usuario", "Pepito");
		puntuacion2.put("tiempo", "12345678");
		puntuacion2.put("puntuacion", 10000);
		
		JSONArray ranking = new JSONArray();
		ranking.put(puntuacion1);
		ranking.put(puntuacion2);
		
		// fin de las puntuaciones de prueba
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 668, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelGeneral = new JPanel();
		frame.getContentPane().add(panelGeneral, BorderLayout.CENTER);
		panelGeneral.setLayout(null);
		
		JLabel Ranking = new JLabel("Ranking");
		Ranking.setBounds(0, 0, 184, 56);
		Ranking.setFont(new Font("Lucida Grande", Font.PLAIN, 47));
		panelGeneral.add(Ranking);
		
		JSplitPane textoRanking = new JSplitPane();
		textoRanking.setBounds(0, 91, 668, 48);
		textoRanking.setResizeWeight(0.5);
		textoRanking.setContinuousLayout(true);
		panelGeneral.add(textoRanking);

		JButton botonGlobal = new JButton("Global");
		textoRanking.setLeftComponent(botonGlobal);
		
		JButton botonPersonal = new JButton("Personal");
		textoRanking.setRightComponent(botonPersonal);
		
		JPanel panelBotonesNiveles = new JPanel();
		panelBotonesNiveles.setBounds(0, 144, 668, 39);
		panelGeneral.add(panelBotonesNiveles);
		
		JButton botonAbsolute = new JButton("Absolute");
		panelBotonesNiveles.add(botonAbsolute);
		
		JButton botonRookie = new JButton("Rookie");
		panelBotonesNiveles.add(botonRookie);
		
		JButton botonStandard = new JButton("Standard");
		panelBotonesNiveles.add(botonStandard);
		
		JButton botonExpert = new JButton("Expert");
		panelBotonesNiveles.add(botonExpert);
		
		JButton botonImposible = new JButton("Impossible");
		panelBotonesNiveles.add(botonImposible);
		
		JPanel panelPuntuaciones = new JPanel();
		panelPuntuaciones.setBounds(26, 195, 619, 214);
		panelGeneral.add(panelPuntuaciones);
		panelPuntuaciones.setLayout(new BorderLayout(0, 0));
		
		JLabel labelTitulos = new JLabel("Nombre                                           Tiempo                                 Puntos ");
		panelPuntuaciones.add(labelTitulos, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		panelPuntuaciones.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//JLabel lblNewLabel_2 = new JLabel("New label");
		//panel.add(lblNewLabel_2);
		
		//ranking = Arkanoid.getArkanoid().mostrarRanking(0, false);
		for(int i = 0; i<ranking.length(); i++) {
			JSONObject puntuacion = ranking.getJSONObject(i);
			//String nombreUsuario = puntuacion.getString("usuario");
			String nombreUsuario = "Angela";
			String tiempo = puntuacion.getString("tiempo");
			String puntos = puntuacion.getString("puntuacion");
			
			JLabel labelPuntuacion = new JLabel(nombreUsuario);
			panel.add(labelPuntuacion);
			
			//
			
		}
	}
}
