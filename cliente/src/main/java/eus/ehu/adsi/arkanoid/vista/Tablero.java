package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.modelo.Bloque;
import eus.ehu.adsi.arkanoid.modelo.Config;
import eus.ehu.adsi.arkanoid.modelo.Cronometro;
import eus.ehu.adsi.arkanoid.modelo.Partida;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.Boton;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaNormal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;

@SuppressWarnings({ "serial", "deprecation" })
public class Tablero extends JFrame implements Observer, KeyListener {

	private JPanel contentPane;
	private PanelTablero tableroPanel;
	
	private Clip clip; 
	private EtiquetaNormal cronometro, score, lives;
	private int puntuacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tablero frame = new Tablero();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tablero() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Config.SCREEN_WIDTH + 20, Config.SCREEN_HEIGHT + 115);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.black);
		
		addKeyListener(this);
		
		this.createBufferStrategy(1);
		
		setFocusable(true);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setBackground(Color.black);
		panel_1.setBorder(new EmptyBorder(5, 0, 5, 0));
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(0);
		panel_2.setBackground(Color.black);
		panel_1.add(panel_2);
		
		cronometro = new EtiquetaNormal(" 0:00 ");
		cronometro.setBorder(new LineBorder(Color.white));
		panel_2.add(cronometro);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(15);
		flowLayout_1.setHgap(0);
		panel_3.setBackground(Color.black);
		panel_1.add(panel_3);
		
		puntuacion = 0;
		score = new EtiquetaNormal("Score:  "+puntuacion);
		panel_3.add(score);
		
		Component horizontalStrut = Box.createHorizontalStrut(50);
		panel_3.add(horizontalStrut);
		
		lives = new EtiquetaNormal("Lives:  "+Config.PLAYER_LIVES);
		panel_3.add(lives);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setVgap(15);
		flowLayout_2.setHgap(0);
		panel_4.setBackground(Color.black);
		panel_1.add(panel_4);
		
		Boton reiniciarButton = new Boton("REINICIAR PARTIDA");
		reiniciarButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		reiniciarButton.setBackground(new Color(255,255,255));
		reiniciarButton.setForeground(new Color(0x000000));
		reiniciarButton.setBorder(new EmptyBorder(5,10,5,10));
		reiniciarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				dispose();
				new Tablero();
				clip.stop();
				//Partida miPartida = Partida.getMiPartida();
				//miPartida.generarPartida();
			}
		});
		
		panel_4.add(reiniciarButton);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.black);
		panel_1.add(panel_5);
		
		JButton play = new JButton("");
		play.setIcon(new ImageIcon(Tablero.class.getResource("/sonidoPersonalizar/play.png")));
		play.setBorder(null);
        play.setContentAreaFilled(false);
        play.setFocusPainted(false);
        play.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		super.mouseClicked(e);
        		if (!clip.isActive()) {
                	clip.start();
                }
        		requestFocus();
        	}
		});
        
		panel_5.add(play);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		panel_5.add(horizontalStrut_1);
		
		JButton pause = new JButton("");
		pause.setIcon(new ImageIcon(Tablero.class.getResource("/sonidoPersonalizar/pausa.png")));
		pause.setBorder(null);
        pause.setContentAreaFilled(false);
        pause.setFocusPainted(false);
        pause.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		super.mouseClicked(e);
        		clip.stop();
        		requestFocus();
        	}
		});
		panel_5.add(pause);
		
		jugar();
		
		tableroPanel = new PanelTablero();
		contentPane.add(tableroPanel, BorderLayout.CENTER);
		setVisible(true);
		
		Arkanoid.getArkanoid().addObserverPartida(this);
		Arkanoid.getArkanoid().addObserverCrono(this);
	}
	
	
	private void jugar() {
		Arkanoid.getArkanoid().jugar();
		reproducirSonido();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Partida) {
			System.out.println(arg);
			if (arg instanceof Bloque) {
				puntuacion++;
				score.setText("Score:  "+puntuacion);
			} else if (arg instanceof Integer) {
				lives.setText("Lives:  "+(int)arg);
			}
			Graphics g = this.tableroPanel.getGraphics();
			tableroPanel.updateTablero(arg, g);
		} else if (o instanceof Cronometro) {
            cronometro.setText(" "+(String)arg+" ");
        }	
	}
	
	private void reproducirSonido(){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(Config.PATH_MUSICA));
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(KeyEvent event) {
		tableroPanel.moverPaddle(event);
	}
  
    public void keyReleased(KeyEvent event) {
		tableroPanel.pararPaddle(event);
	}
  
    public void keyTyped(KeyEvent arg0) {
    }
}

