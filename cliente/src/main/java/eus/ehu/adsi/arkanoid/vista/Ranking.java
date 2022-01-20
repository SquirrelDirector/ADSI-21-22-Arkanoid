package eus.ehu.adsi.arkanoid.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import org.json.JSONArray;
import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JScrollPane;

public class Ranking {

	private JFrame frame;
	private JSONArray ranking = new JSONArray();
	private JPanel panel = new JPanel();
	private boolean personal = false;

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
		frame.setVisible(true);
		/*// puntuaciones de prueba
		
		JSONObject puntuacion1 = new JSONObject();
		puntuacion1.put("usuario", "Angela");
		puntuacion1.put("tiempo", "12345");
		puntuacion1.put("puntuacion", 9999);

		JSONObject puntuacion2 = new JSONObject();
		puntuacion2.put("usuario", "Pepito");
		puntuacion2.put("tiempo", "12345678");
		puntuacion2.put("puntuacion", 10000);
		
		ranking.put(puntuacion1);
		ranking.put(puntuacion2);
		
		// fin de las puntuaciones de prueba */
		
		
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
		
		JPanel textoRanking = new JPanel();
		textoRanking.setBounds(0, 91, 668, 48);
		panelGeneral.add(textoRanking);

		JButton botonGlobal = new JButton("Global");
		botonGlobal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				personal = false;
				//ranking = Arkanoid.getArkanoid().mostrarRanking(0, personal);
				for(int i = 0; i<ranking.length(); i++) {
					JSONObject puntuacion = ranking.getJSONObject(i);
					String nombreUsuario = puntuacion.getString("usuario");
					String tiempo = puntuacion.getString("tiempo");
					int puntos = puntuacion.getInt("puntuacion");
					
					JLabel labelNombre = new JLabel(nombreUsuario);
					panel.add(labelNombre);
					JLabel labelTiempo = new JLabel(tiempo);
					panel.add(labelTiempo);
					JLabel labelPuntos = new JLabel(""+puntos);
					panel.add(labelPuntos);
				}
			}
		});
		textoRanking.setLayout(new GridLayout(0, 2, 0, 0));
		textoRanking.add(botonGlobal);
		
		JButton botonPersonal = new JButton("Personal");
		botonPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				personal = true;
				ranking = Arkanoid.getArkanoid().mostrarRanking(0, personal);
				for(int i = 0; i<ranking.length(); i++) {
					JSONObject puntuacion = ranking.getJSONObject(i);
					String nombreUsuario = puntuacion.getString("usuario");
					String tiempo = puntuacion.getString("tiempo");
					int puntos = puntuacion.getInt("puntuacion");
					
					JLabel labelNombre = new JLabel(nombreUsuario);
					panel.add(labelNombre);
					JLabel labelTiempo = new JLabel(tiempo);
					panel.add(labelTiempo);
					JLabel labelPuntos = new JLabel(""+puntos);
					panel.add(labelPuntos);
				}
			}
		});
		textoRanking.add(botonPersonal);
		
		JPanel panelBotonesNiveles = new JPanel();
		panelBotonesNiveles.setBounds(0, 144, 668, 39);
		panelGeneral.add(panelBotonesNiveles);
		
		JButton botonAbsolute = new JButton("Absolute");
		botonAbsolute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				personal = true;
				ranking = Arkanoid.getArkanoid().mostrarRanking(0, personal);
				for(int i = 0; i<ranking.length(); i++) {
					JSONObject puntuacion = ranking.getJSONObject(i);
					String nombreUsuario = puntuacion.getString("usuario");
					String tiempo = puntuacion.getString("tiempo");
					int puntos = puntuacion.getInt("puntuacion");
					
					JLabel labelNombre = new JLabel(nombreUsuario);
					panel.add(labelNombre);
					JLabel labelTiempo = new JLabel(tiempo);
					panel.add(labelTiempo);
					JLabel labelPuntos = new JLabel(""+puntos);
					panel.add(labelPuntos);
				}
				
			}
		});
		panelBotonesNiveles.add(botonAbsolute);
		
		JButton botonRookie = new JButton("Rookie");
		botonRookie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				personal = true;
				ranking = Arkanoid.getArkanoid().mostrarRanking(1, personal);
				for(int i = 0; i<ranking.length(); i++) {
					JSONObject puntuacion = ranking.getJSONObject(i);
					String nombreUsuario = puntuacion.getString("usuario");
					String tiempo = puntuacion.getString("tiempo");
					int puntos = puntuacion.getInt("puntuacion");
					
					JLabel labelNombre = new JLabel(nombreUsuario);
					panel.add(labelNombre);
					JLabel labelTiempo = new JLabel(tiempo);
					panel.add(labelTiempo);
					JLabel labelPuntos = new JLabel(""+puntos);
					panel.add(labelPuntos);
				}
			}
		});
		panelBotonesNiveles.add(botonRookie);
		
		JButton botonStandard = new JButton("Standard");
		botonStandard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				personal = true;
				ranking = Arkanoid.getArkanoid().mostrarRanking(2, personal);
				for(int i = 0; i<ranking.length(); i++) {
					JSONObject puntuacion = ranking.getJSONObject(i);
					String nombreUsuario = puntuacion.getString("usuario");
					String tiempo = puntuacion.getString("tiempo");
					int puntos = puntuacion.getInt("puntuacion");
					
					JLabel labelNombre = new JLabel(nombreUsuario);
					panel.add(labelNombre);
					JLabel labelTiempo = new JLabel(tiempo);
					panel.add(labelTiempo);
					JLabel labelPuntos = new JLabel(""+puntos);
					panel.add(labelPuntos);
				}
			}
		});
		panelBotonesNiveles.add(botonStandard);
		
		JButton botonExpert = new JButton("Expert");
		botonExpert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				personal = true;
				ranking = Arkanoid.getArkanoid().mostrarRanking(3, personal);
				for(int i = 0; i<ranking.length(); i++) {
					JSONObject puntuacion = ranking.getJSONObject(i);
					String nombreUsuario = puntuacion.getString("usuario");
					String tiempo = puntuacion.getString("tiempo");
					int puntos = puntuacion.getInt("puntuacion");
					
					JLabel labelNombre = new JLabel(nombreUsuario);
					panel.add(labelNombre);
					JLabel labelTiempo = new JLabel(tiempo);
					panel.add(labelTiempo);
					JLabel labelPuntos = new JLabel(""+puntos);
					panel.add(labelPuntos);
				}
			}
		});
		panelBotonesNiveles.add(botonExpert);
		
		JButton botonImposible = new JButton("Impossible");
		botonImposible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				personal = true;
				ranking = Arkanoid.getArkanoid().mostrarRanking(4, personal);
				for(int i = 0; i<ranking.length(); i++) {
					JSONObject puntuacion = ranking.getJSONObject(i);
					String nombreUsuario = puntuacion.getString("usuario");
					String tiempo = puntuacion.getString("tiempo");
					int puntos = puntuacion.getInt("puntuacion");
					
					JLabel labelNombre = new JLabel(nombreUsuario);
					panel.add(labelNombre);
					JLabel labelTiempo = new JLabel(tiempo);
					panel.add(labelTiempo);
					JLabel labelPuntos = new JLabel(""+puntos);
					panel.add(labelPuntos);
				}
			}
		});
		panelBotonesNiveles.add(botonImposible);
		
		JPanel panelPuntuaciones = new JPanel();
		panelPuntuaciones.setBounds(26, 195, 619, 214);
		panelGeneral.add(panelPuntuaciones);
		panelPuntuaciones.setLayout(new BorderLayout(0, 0));
		
		JLabel labelTitulos = new JLabel("Nombre                                           Tiempo                                 Puntos ");
		panelPuntuaciones.add(labelTitulos, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		panelPuntuaciones.add(scrollPane, BorderLayout.CENTER);
		
		
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		
		ranking = Arkanoid.getArkanoid().mostrarRanking(0, personal);
		for(int i = 0; i<ranking.length(); i++) {
			JSONObject puntuacion = ranking.getJSONObject(i);
			String nombreUsuario = puntuacion.getString("usuario");
			String tiempo = puntuacion.getString("tiempo");
			int puntos = puntuacion.getInt("puntuacion");
			
			JLabel labelNombre = new JLabel(nombreUsuario);
			panel.add(labelNombre);
			JLabel labelTiempo = new JLabel(tiempo);
			panel.add(labelTiempo);
			JLabel labelPuntos = new JLabel(""+puntos);
			panel.add(labelPuntos);
			
		}
	}
}
