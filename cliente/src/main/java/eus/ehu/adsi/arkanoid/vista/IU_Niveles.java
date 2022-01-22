package eus.ehu.adsi.arkanoid.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.Boton;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaNormal;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
public class IU_Niveles implements Observer {

	private JFrame frame;
	private int nivel;
	private JLabel lblL;
	private InterfazBase base;
	private JButton botonPersonalizar;
	private JButton botonNvl5;
	/**
	 * Launch the application.
	 */
	public void mostrarVentana() {
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
		lblL = new EtiquetaNormal("");
		lblL.setBackground(new Color(2,4,40)); //TODO - Este color hijuelarremaracamadrequeloremilperrasparió no se aplica, sospecho del repaint de seleccionar()
		lblL.setForeground(new Color(255,255,255));
		
		frame = new JFrame();
		frame.setBounds(100, 100, 951, 645);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		if (Arkanoid.getArkanoid().isIdentificado())
			seleccionar(Arkanoid.getArkanoid().getUltimaPartida());
		else
			seleccionar(1);
		
		base=new InterfazBase("SELECCIONAR NIVEL");
		base.setIdentificado(Arkanoid.getArkanoid().isIdentificado());
		((InterfazBase) base).setEventoRegreso(new IU_Inicial());
		frame.add(base);
		
		JPanel principal=new JPanel();
		principal.setBackground(new Color(0,0,0,0));
		principal.setLayout(new BorderLayout(0, 0));
		base.add(principal);
		
		JPanel layout = new JPanel();
		principal.add(layout, BorderLayout.CENTER);
		layout.setLayout(new GridLayout(1, 2, 0, 0));
		layout.setBackground(new Color(0,0,0,0));
		layout.setBorder(new EmptyBorder(30,0,70,0));
		
		JPanel izquierda = new JPanel();
		layout.add(izquierda);
		izquierda.setBackground(new Color(0,0,0,0));
		izquierda.setLayout(new GridLayout(5, 1, 0, 0));
		
		
		JPanel derecha = new JPanel();
		layout.add(derecha);
		derecha.setLayout(new BorderLayout(0, 0));
		derecha.setBackground(new Color(0,0,0,0));
		derecha.setBorder(new EmptyBorder(0,0,0,20));
		
		JPanel botones = new JPanel();
		derecha.add(botones, BorderLayout.SOUTH);
		botones.setLayout(new GridLayout(1, 2, 0, 0));
		botones.setBackground(new Color(0,0,0,0));
		
		
		JPanel pnlPersonalizar=new JPanel();
		pnlPersonalizar.setLayout(new CardLayout());
		pnlPersonalizar.setBackground(new Color(0,0,0,0));
		pnlPersonalizar.setBorder(new EmptyBorder(0, 10, 0, 10));
		
		botonPersonalizar = new Boton("Personalizar");
		if (Arkanoid.getArkanoid().isIdentificado()==false) {
			botonPersonalizar.setEnabled(false);
		}
		botonPersonalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IU_Personalizacion();
				frame.dispose();
			}
		});
		botones.add(pnlPersonalizar);
		pnlPersonalizar.add(botonPersonalizar);
		
		JPanel pnlJugar=new JPanel();
		pnlJugar.setLayout(new CardLayout());
		pnlJugar.setBackground(new Color(0,0,0,0));
		pnlJugar.setBorder(new EmptyBorder(0, 30, 0, 30));
		
		JButton botonJugar = new Boton("Jugar");
		botonJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Arkanoid.getArkanoid().isIdentificado()){
					Arkanoid.getArkanoid().actualizarUltimaPartida(nivel);
				}
				Arkanoid.getArkanoid().updateConfig(Arkanoid.getArkanoid().obtenerDatosNivel(nivel));
				new Tablero().setVisible(true);
				frame.dispose();
			}
		});
		botones.add(pnlJugar);
		pnlJugar.add(botonJugar);
		
		JPanel descripcion = new JPanel();
		derecha.add(descripcion, BorderLayout.CENTER);
		descripcion.setBackground(new Color(0,0,0,0));
		descripcion.setLayout(new CardLayout());
		descripcion.setBorder(new EmptyBorder(0,0,100,0));
		descripcion.add(lblL);
		
		JPanel pnlNvl1=new JPanel();
		pnlNvl1.setLayout(new CardLayout());
		pnlNvl1.setBackground(new Color(0,0,0,0));
		pnlNvl1.setBorder(new EmptyBorder(10, 30, 10, 100));
		
		JButton botonNvl1 = new Boton("Novato");
		botonNvl1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionar(1);
			}
		});
		pnlNvl1.add(botonNvl1);
		izquierda.add(pnlNvl1);
		
		
		JPanel pnlNvl2=new JPanel();
		pnlNvl2.setLayout(new CardLayout());
		pnlNvl2.setBackground(new Color(0,0,0,0));
		pnlNvl2.setBorder(new EmptyBorder(10, 30, 10, 100));
		
		JButton botonNvl2 = new Boton("Est\u00E1ndar");
		botonNvl2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionar(2);
			}
		});
		pnlNvl2.add(botonNvl2);
		izquierda.add(pnlNvl2);
		
		
		JPanel pnlNvl3=new JPanel();
		pnlNvl3.setLayout(new CardLayout());
		pnlNvl3.setBackground(new Color(0,0,0,0));
		pnlNvl3.setBorder(new EmptyBorder(10, 30, 10, 100));
		
		JButton botonNvl3 = new Boton("Experto");
		botonNvl3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionar(3);
			}
		});
		pnlNvl3.add(botonNvl3);
		izquierda.add(pnlNvl3);
		
		
		JPanel pnlNvl4=new JPanel();
		pnlNvl4.setLayout(new CardLayout());
		pnlNvl4.setBackground(new Color(0,0,0,0));
		pnlNvl4.setBorder(new EmptyBorder(10, 30, 10, 100));
		
		JButton botonNvl4 = new Boton("Imposible");
		botonNvl4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionar(4);
			}
		});
		pnlNvl4.add(botonNvl4);
		izquierda.add(pnlNvl4);
		
		
		JPanel pnlNvl5=new JPanel();
		pnlNvl5.setLayout(new CardLayout());
		pnlNvl5.setBackground(new Color(0,0,0,0));
		pnlNvl5.setBorder(new EmptyBorder(10, 30, 10, 100));
		
		botonNvl5 = new Boton("Personalizado");
		if (Arkanoid.getArkanoid().isIdentificado()==false) {
			botonNvl5.setEnabled(false);
		}
		botonNvl5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionar(5);
			}
		});
		pnlNvl5.add(botonNvl5);
		izquierda.add(pnlNvl5);
		
	}
	
	private void seleccionar(int lvl){
		nivel=lvl;
		
		switch (nivel) {
		case 1:
			lblL.setText("<HTML>Version fácil del juego, ideal <br>para principiantes y gente adaptándose al juego. <br><br> - 4 líneas de bloques <br> - anchura de la barra aumentada <br> - velocidad estándar</HTML>");
			break;
		case 2:
			lblL.setText("<HTML>Dificultad original del juego, <br>para gente con algo más de experiencia. <br><br> - 6 líneas de bloques <br> - anchura de barra y velócidad estándar");
			break;
		case 3:
			lblL.setText("<HTML>Versión difícil del juego, <br>para gente buscando una experiencia desafiante. <br><br> - 8 líneas de bloques <br> - anchura de barra estándar <br> - velócidad aumentada</HTML>");
			break;
		case 4:
			lblL.setText("<HTML>Versión sólo apta para quien busque <br>completar el logro. <br><br> - 10 líneas de bloques <br> - anchura de barra reducida <br> - velocidad aumentada</HTML>");
			break;
		case 5:
			lblL.setText("<HTML>Crea tu propio nivel y prueba <br>tantas combinaciones de dificultad como quieras. <br><br>Accede al botón de personalizar para cambiarlo <br>(La puntuación no se guardará en los ránkings)</HTML>");
			break;
		}
		frame.repaint();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Boolean){
			base.setIdentificado((boolean) arg1);
			botonPersonalizar.setEnabled((boolean) arg1);
			botonNvl5.setEnabled((boolean) arg1);
		}
	}

}