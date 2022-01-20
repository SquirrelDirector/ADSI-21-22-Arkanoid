package eus.ehu.adsi.arkanoid.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class IU_Niveles {

	private JFrame frame;
	private int Nivel;
	private JLabel lblL;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Niveles window = new IU_Niveles();
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
	public IU_Niveles() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		lblL = new JLabel();
		Nivel=Arkanoid.getArkanoid().getUltimaPartida();
	//	Nivel=1;
		frame = new JFrame();
		frame.setBounds(100, 100, 951, 645);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel Titulo = new JPanel();
		frame.getContentPane().add(Titulo, BorderLayout.NORTH);
		
		JPanel Layout = new JPanel();
		frame.getContentPane().add(Layout, BorderLayout.CENTER);
		Layout.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel Izquierda = new JPanel();
		Layout.add(Izquierda);
		Izquierda.setLayout(new GridLayout(5, 1, 0, 0));
		
		
		JPanel Derecha = new JPanel();
		Layout.add(Derecha);
		Derecha.setLayout(new BorderLayout(0, 0));
		
		JPanel Botones2 = new JPanel();
		Derecha.add(Botones2, BorderLayout.SOUTH);
		Botones2.setLayout(new GridLayout(1, 2, 0, 0));
		
		JButton BotonPersonalizar = new JButton("Personalizar");
		if (Arkanoid.getArkanoid().isIdentificado()==false) {
			BotonPersonalizar.setEnabled(false);
		}
		BotonPersonalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Personalizacion();
			frame.dispose();
			}
		});
		Botones2.add(BotonPersonalizar);
		
		JButton BotonJugar = new JButton("Jugar");
		BotonJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Arkanoid.getArkanoid().actualizarUltimaPartida(Nivel);
				Arkanoid.getArkanoid().updateConfig(Arkanoid.getArkanoid().obtenerDatosNivel(Nivel));
				new Tablero().setVisible(true);
			frame.dispose();
			}
		});
		Botones2.add(BotonJugar);
		
		JPanel Descripcion = new JPanel();
		Derecha.add(Descripcion, BorderLayout.CENTER);
		
		Descripcion.add(lblL);
		
		switch (Nivel) {
		case 1:
			lblL.setText("Version f�cil del juego, ideal para principiantes y gente adapt�ndose al juego. 4 L�neas de bloques, anchura de la barra aumentada, velocidad est�ndar");
			break;
		case 2:
			lblL.setText("Versi�n media del juego, para gente con algo m�s de experiencia. 6 L�neas de bloques, anchura de barra y vel�cidad est�ndar");
			break;
		case 3:
			lblL.setText("Versi�n dif�cil del juego, para gente buscando un reto. 8 l�neas de bloques, anchura de barra est�ndar, vel�cidad aumentada");
			break;
		case 4:
			lblL.setText("Versi�n solo apta para quien busque completar el logro. 10 l�neas de bloques, anchura de barra reducida, vel�cidad aumentada");
			break;
		case 5:
			lblL.setText("Crea tu propio nivel y prueba tantas combinaciones de dificultad como quieras. Accede al bot�n de personalizar para cambiarlo. (La puntuaci�n no se guardar� en los r�nkings)");
			break;

		default:
			break;
		}
		
		JButton BotonNvl1 = new JButton("Novato");
		Izquierda.add(BotonNvl1);
		BotonNvl1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Nivel=1;
				lblL.setText("Version f�cil del juego, ideal para principiantes y gente adapt�ndose al juego. 4 L�neas de bloques, anchura de la barra aumentada, velocidad est�ndar");
			}
		});
		JButton BotonNvl2 = new JButton("Est\u00E1ndar");
		BotonNvl2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Nivel=2;
				lblL.setText("Versi�n media del juego, para gente con algo m�s de experiencia. 6 L�neas de bloques, anchura de barra y vel�cidad est�ndar");
			}
		});
		Izquierda.add(BotonNvl2);
		
		JButton BotonNvl3 = new JButton("Experto");
		BotonNvl3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Nivel=3;
				lblL.setText("Versi�n dif�cil del juego, para gente buscando un reto. 8 l�neas de bloques, anchura de barra est�ndar, vel�cidad aumentada");
			}
		});
		Izquierda.add(BotonNvl3);
		
		JButton BotonNvl4 = new JButton("Imposible");
		BotonNvl4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Nivel=4;
				lblL.setText("Versi�n solo apta para quien busque completar el logro. 10 l�neas de bloques, anchura de barra reducida, vel�cidad aumentada");
			}
		});
		Izquierda.add(BotonNvl4);
		
		JButton BotonNvl5 = new JButton("Personalizado");
		if (Arkanoid.getArkanoid().isIdentificado()==false) {
			BotonNvl5.setEnabled(false);
		}
		BotonNvl5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Nivel=5;
				lblL.setText("Crea tu propio nivel y prueba tantas combinaciones de dificultad como quieras. Accede al bot�n de personalizar para cambiarlo. (La puntuaci�n no se guardar� en los r�nkings)");
			}
		});
		Izquierda.add(BotonNvl5);
		
	}

}