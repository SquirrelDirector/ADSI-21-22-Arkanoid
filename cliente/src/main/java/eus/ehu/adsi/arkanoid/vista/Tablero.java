package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import eus.ehu.adsi.arkanoid.modelo.Config;
import eus.ehu.adsi.arkanoid.modelo.Cronometro;
import eus.ehu.adsi.arkanoid.modelo.Partida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Component;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.CardLayout;


@SuppressWarnings({ "serial", "deprecation" })
public class Tablero extends JFrame implements Observer {

	private JPanel contentPane, tableroPanel;
	
	private Clip clip;
	private JLabel cronometro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tablero frame = new Tablero();
					frame.setVisible(true);
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
		setBounds(100, 100, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Config.BACKGROUND_COLOR);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setBackground(Config.BACKGROUND_COLOR);
		panel_1.setBorder(new EmptyBorder(5, 0, 5, 0));
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(0);
		panel_2.setBackground(Config.BACKGROUND_COLOR);
		panel_1.add(panel_2);
		
		cronometro = new JLabel(" 0:00 ");
		cronometro.setForeground(Color.WHITE);
		cronometro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cronometro.setBorder(new LineBorder(Color.white));
		panel_2.add(cronometro);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(15);
		flowLayout_1.setHgap(0);
		panel_3.setBackground(Config.BACKGROUND_COLOR);
		panel_1.add(panel_3);
		
		JLabel score = new JLabel("Score:  0");
		score.setForeground(Color.WHITE);
		score.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_3.add(score);
		
		Component horizontalStrut = Box.createHorizontalStrut(50);
		panel_3.add(horizontalStrut);
		
		JLabel lives = new JLabel("Lives:  5");
		lives.setForeground(Color.WHITE);
		lives.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_3.add(lives);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setVgap(15);
		flowLayout_2.setHgap(0);
		panel_4.setBackground(Config.BACKGROUND_COLOR);
		panel_1.add(panel_4);
		
		JButton reiniciarButton = new JButton("REINICIAR PARTIDA");
		reiniciarButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		reiniciarButton.setBackground(new Color(255,255,255));
		reiniciarButton.setForeground(new Color(0x000000));
		reiniciarButton.setBorder(null);
		reiniciarButton.setFocusPainted(false);
		reiniciarButton.setBorder(new EmptyBorder(5,10,5,10));
		panel_4.add(reiniciarButton);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Config.BACKGROUND_COLOR);
		panel_1.add(panel_5);
		
		JButton play = new JButton("");
		play.setIcon(new ImageIcon(Tablero.class.getResource("/sonidoPersonalizar/play.png")));
		play.setBorder(null);
        play.setContentAreaFilled(false);
        play.setFocusPainted(false);
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (!clip.isActive()) {
                	clip.start();
                }
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
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clip.stop();
            }
        });
		panel_5.add(pause);
		
		tableroPanel = new PanelTablero();
		tableroPanel.setBackground(Config.BACKGROUND_COLOR);
		contentPane.add(tableroPanel, BorderLayout.CENTER);
		        
        jugar();
	}
	
	private void jugar() {
		//Arkanoid.getArkanoid().jugar();
		//Arkanoid.getArkanoid().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Partida) {
			//tableroPanel.update();
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

}
