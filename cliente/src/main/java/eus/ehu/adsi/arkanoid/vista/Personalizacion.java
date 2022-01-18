package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;

public class Personalizacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton guardarButton, volverButton;
	private JTabbedPane personalizarPestanas;
	private JPanel cbButtons, cbpLabels, cfButtons, cfpLabels, clButtons, clpLabels, cppLabels, cpButtons;
	private JPanel sonidosButtons;
	private JScrollPane jspB, jspF, jspL, jspP;
	private JSlider jsBloques, jsPaddle, jsBola;
	
	private JSONArray colores, sonidos;
    private JSONObject personalizablesJugador;
    private ButtonGroup bg1, bg2, bg3, bg4, bgS;
    private Clip clip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Personalizacion dialog = new Personalizacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Personalizacion() {
		setBounds(100, 100, 520, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		setLocationRelativeTo(null);
		setResizable(false);
		{
			personalizarPestanas = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(personalizarPestanas);
			{
				JPanel coloresPersonalizar = new JPanel();
				coloresPersonalizar.setBorder(new EmptyBorder(5, 5, 5, 5));
				personalizarPestanas.addTab("COLORES", null, coloresPersonalizar, null);
				coloresPersonalizar.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBorder(null);
					coloresPersonalizar.add(scrollPane);
					{
						JPanel colores = new JPanel();
						scrollPane.setViewportView(colores);
						colores.setLayout(new GridLayout(4, 0, 0, 0));
						{
							JPanel coloresFondo = new JPanel();
							coloresFondo.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(0, 0, 0), 2, true)));
							colores.add(coloresFondo);
							coloresFondo.setLayout(new BoxLayout(coloresFondo, BoxLayout.X_AXIS));
							{
								Component horizontalStrut = Box.createHorizontalStrut(15);
								coloresFondo.add(horizontalStrut);
							}
							{
								JLabel lblNewLabel = new JLabel("Fondo");
								lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
								lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
								coloresFondo.add(lblNewLabel);
							}
							{
								Component horizontalStrut = Box.createHorizontalStrut(8);
								coloresFondo.add(horizontalStrut);
							}
							{
								JPanel fondo = new JPanel();
								coloresFondo.add(fondo);
								fondo.setLayout(new GridLayout(2, 1, 0, 0));
								{
									JPanel panel = new JPanel();
									panel.setBorder(new EmptyBorder(10, 35, 5, 0));
									fondo.add(panel);
									panel.setLayout(new GridLayout(0, 1, 0, 0));
									{
										jspF = new JScrollPane();
										jspF.setBorder(null);
										panel.add(jspF);
										{
											cfpLabels = new JPanel();
											jspF.setViewportView(cfpLabels);
											cfpLabels.setLayout(new GridLayout(1, 0, 0, 0));
										}
									}
								}
								{
									cfButtons = new JPanel();
									cfButtons.setBorder(new EmptyBorder(0, 5, 0, 0));
									fondo.add(cfButtons);
									cfButtons.setLayout(new BoxLayout(cfButtons, BoxLayout.X_AXIS));
								}
							}
						}
						{
							JPanel coloresBola = new JPanel();
							coloresBola.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(0, 0, 0), 2, true)));
							colores.add(coloresBola);
							coloresBola.setLayout(new BoxLayout(coloresBola, BoxLayout.X_AXIS));
							{
								Component horizontalStrut = Box.createHorizontalStrut(15);
								coloresBola.add(horizontalStrut);
							}
							{
								JLabel lblNewLabel_1 = new JLabel("Bola");
								coloresBola.add(lblNewLabel_1);
							}
							{
								Component horizontalStrut = Box.createHorizontalStrut(19);
								coloresBola.add(horizontalStrut);
							}
							{
								JPanel bola = new JPanel();
								coloresBola.add(bola);
								bola.setLayout(new GridLayout(2, 0, 0, 0));
								{
									JPanel panel = new JPanel();
									panel.setBorder(new EmptyBorder(10, 35, 5, 0));
									bola.add(panel);
									panel.setLayout(new GridLayout(0, 1, 0, 0));
									{
										jspB = new JScrollPane();
										jspB.setBorder(null);
										panel.add(jspB);
										{
											cbpLabels = new JPanel();
											jspB.setViewportView(cbpLabels);
											cbpLabels.setLayout(new GridLayout(1, 0, 0, 0));
										}
									}
								}
								{
									cbButtons = new JPanel();
									cbButtons.setBorder(new EmptyBorder(0, 5, 0, 0));
									bola.add(cbButtons);
									cbButtons.setLayout(new BoxLayout(cbButtons, BoxLayout.X_AXIS));
								}
							}
						}
						{
							JPanel coloresLadrillos = new JPanel();
							coloresLadrillos.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(0, 0, 0), 2, true)));
							colores.add(coloresLadrillos);
							coloresLadrillos.setLayout(new BoxLayout(coloresLadrillos, BoxLayout.X_AXIS));
							{
								Component horizontalStrut = Box.createHorizontalStrut(15);
								coloresLadrillos.add(horizontalStrut);
							}
							{
								JLabel lblNewLabel_2 = new JLabel("Ladrillos");
								coloresLadrillos.add(lblNewLabel_2);
							}
							{
								JPanel ladrillos = new JPanel();
								coloresLadrillos.add(ladrillos);
								ladrillos.setLayout(new GridLayout(2, 0, 0, 0));
								{
									JPanel panel = new JPanel();
									panel.setBorder(new EmptyBorder(10, 30, 5, 0));
									ladrillos.add(panel);
									panel.setLayout(new GridLayout(0, 1, 0, 0));
									{
										jspL = new JScrollPane();
										jspL.setBorder(null);
										panel.add(jspL);
										{
											clpLabels = new JPanel();
											jspL.setViewportView(clpLabels);
											clpLabels.setLayout(new GridLayout(1, 0, 0, 0));
										}
									}
								}
								{
									clButtons = new JPanel();
									clButtons.setBorder(new EmptyBorder(0, 0, 0, 0));
									ladrillos.add(clButtons);
									clButtons.setLayout(new BoxLayout(clButtons, BoxLayout.X_AXIS));
								}
							}
						}
						{
							JPanel coloresPaddle = new JPanel();
							coloresPaddle.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(0, 0, 0), 2, true)));
							colores.add(coloresPaddle);
							coloresPaddle.setLayout(new BoxLayout(coloresPaddle, BoxLayout.X_AXIS));
							{
								Component horizontalStrut = Box.createHorizontalStrut(15);
								coloresPaddle.add(horizontalStrut);
							}
							{
								JLabel lblNewLabel_3 = new JLabel("Paddle");
								coloresPaddle.add(lblNewLabel_3);
							}
							{
								Component horizontalStrut = Box.createHorizontalStrut(5);
								coloresPaddle.add(horizontalStrut);
							}
							{
								JPanel paddle = new JPanel();
								coloresPaddle.add(paddle);
								paddle.setLayout(new GridLayout(2, 0, 0, 0));
								{
									JPanel panel = new JPanel();
									panel.setBorder(new EmptyBorder(10, 35, 5, 0));
									paddle.add(panel);
									panel.setLayout(new GridLayout(0, 1, 0, 0));
									{
										jspP = new JScrollPane();
										jspP.setBorder(null);
										panel.add(jspP);
										{
											cppLabels = new JPanel();
											jspP.setViewportView(cppLabels);
											cppLabels.setLayout(new GridLayout(1, 0, 0, 0));
										}
									}
								}
								{
									cpButtons = new JPanel();
									cpButtons.setBorder(new EmptyBorder(0, 5, 0, 0));
									paddle.add(cpButtons);
									cpButtons.setLayout(new BoxLayout(cpButtons, BoxLayout.X_AXIS));
								}
							}
						}
					}
				}
			}
			{
				JPanel sonidoPersonalizar = new JPanel();
				sonidoPersonalizar.setBorder(new EmptyBorder(5, 5, 5, 5));
				personalizarPestanas.addTab("SONIDOS", null, sonidoPersonalizar, null);
				sonidoPersonalizar.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBorder(null);
					sonidoPersonalizar.add(scrollPane);
					{
						JPanel panel = new JPanel();
						panel.setBorder(new EmptyBorder(10, 0, 0, 0));
						scrollPane.setViewportView(panel);
						panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
						{
							JLabel lblNewLabel_4 = new JLabel("Escoge la m�sica que quieras escuchar:");
							lblNewLabel_4.setAlignmentX(Component.CENTER_ALIGNMENT);
							panel.add(lblNewLabel_4);
						}
						{
							sonidosButtons = new JPanel();
							sonidosButtons.setBorder(new EmptyBorder(15, 0, 0, 0));
							panel.add(sonidosButtons);
							sonidosButtons.setLayout(new GridLayout(0, 1, 0, 0));
						}
					}
				}
			}
			{
				JPanel dimensionesPersonalizar = new JPanel();
				dimensionesPersonalizar.setBorder(new EmptyBorder(5, 5, 5, 5));
				personalizarPestanas.addTab("DIMENSIONES", null, dimensionesPersonalizar, null);
				dimensionesPersonalizar.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBorder(null);
					dimensionesPersonalizar.add(scrollPane);
					{
						JPanel panel = new JPanel();
						panel.setBorder(new EmptyBorder(10, 0, 0, 55));
						scrollPane.setViewportView(panel);
						panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
						{
							JLabel lblNewLabel_5 = new JLabel("Personaliza los elementos del juego:");
							panel.add(lblNewLabel_5);
						}
						{
							JPanel panel_1 = new JPanel();
							panel.add(panel_1);
							panel_1.setLayout(new GridLayout(3, 0, 0, 0));
							{
								JPanel panel_2 = new JPanel();
								panel_1.add(panel_2);
								panel_2.setLayout(new GridLayout(0, 2, 0, 0));
								{
									JPanel panel_3 = new JPanel();
									panel_2.add(panel_3);
									panel_3.setLayout(new GridLayout(0, 1, 0, 0));
									{
										JLabel lblNewLabel_6 = new JLabel("N�mero bloques");
										lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
										panel_3.add(lblNewLabel_6);
									}
								}
								{
									JPanel panel_3 = new JPanel();
									panel_2.add(panel_3);
									panel_3.setLayout(new GridLayout(0, 1, 0, 0));
									{
										jsBloques = new JSlider();
										jsBloques.setSnapToTicks(true);
										jsBloques.setMinorTickSpacing(11);
										jsBloques.setMinimum(22);
										jsBloques.setMaximum(66);
										jsBloques.setMajorTickSpacing(11);
										jsBloques.setPaintLabels(true);
										jsBloques.setPaintTicks(true);
										panel_3.add(jsBloques);
									}
								}
							}
							{
								JPanel panel_2 = new JPanel();
								panel_1.add(panel_2);
								panel_2.setLayout(new GridLayout(0, 2, 0, 0));
								{
									JPanel panel_3 = new JPanel();
									panel_2.add(panel_3);
									panel_3.setLayout(new GridLayout(0, 1, 0, 0));
									{
										JLabel lblNewLabel_7 = new JLabel("Tama�o paddle");
										lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
										panel_3.add(lblNewLabel_7);
									}
								}
								{
									JPanel panel_3 = new JPanel();
									panel_2.add(panel_3);
									panel_3.setLayout(new GridLayout(0, 1, 0, 0));
									{
										jsPaddle = new JSlider();
										jsPaddle.setSnapToTicks(true);
										jsPaddle.setPaintTicks(true);
										jsPaddle.setPaintLabels(true);
										jsPaddle.setMinorTickSpacing(5);
										jsPaddle.setMinimum(40);
										jsPaddle.setMaximum(80);
										jsPaddle.setMajorTickSpacing(10);
										panel_3.add(jsPaddle);
									}
								}
							}
							{
								JPanel panel_2 = new JPanel();
								panel_1.add(panel_2);
								panel_2.setLayout(new GridLayout(0, 2, 0, 0));
								{
									JPanel panel_3 = new JPanel();
									panel_2.add(panel_3);
									panel_3.setLayout(new GridLayout(0, 1, 0, 0));
									{
										JLabel lblNewLabel_8 = new JLabel("Velocidad bola");
										lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
										panel_3.add(lblNewLabel_8);
									}
								}
								{
									JPanel panel_3 = new JPanel();
									panel_2.add(panel_3);
									panel_3.setLayout(new GridLayout(0, 1, 0, 0));
									{
										jsBola = new JSlider();
										jsBola.setSnapToTicks(true);
										jsBola.setMinorTickSpacing(1);
										jsBola.setMinimum(1);
										jsBola.setMaximum(7);
										jsBola.setMajorTickSpacing(1);
										jsBola.setPaintTicks(true);
										jsBola.setPaintLabels(true);
										panel_3.add(jsBola);
									}
								}
							}
						}
					}
				}
			}
		}
		{
			JPanel topPane = new JPanel();
			topPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(topPane, BorderLayout.NORTH);
			{
				volverButton = new JButton("");
				volverButton.setIcon(new ImageIcon("/general/back.png"));
				volverButton.setBorder(null);
				volverButton.setContentAreaFilled(false);
				topPane.add(volverButton);
			}
			{
				JLabel tituloPersonalizar = new JLabel("PERSONALIZAR");
				tituloPersonalizar.setFont(new Font("Tahoma", Font.PLAIN, 20));
				topPane.add(tituloPersonalizar);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				guardarButton = new JButton("GUARDAR");
				buttonPane.add(guardarButton);
			}
		}
		
		getPersonalizables();
		ponerColores();
		ponerSonidos();
		inciadoSesion();
        ponerNivelPersonalizado();
        
        if (!personalizarPestanas.isEnabledAt(2)){
            personalizarPestanas.setToolTipTextAt(2, "Tienes que iniciar sesi�n!");
        }
        
        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarPersonalizacion();
            }
        });
        
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
     // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
	}
	
	private void getPersonalizables() {
        JSONObject personalizables = Arkanoid.getArkanoid().obtenerPersonalizables();
        colores = personalizables.getJSONArray("colores");
        sonidos = personalizables.getJSONArray("sonidos");
        personalizablesJugador = Arkanoid.getArkanoid().obtenerPersonalizacionUsuario();
    }
	
	private void ponerColores() {
        JSONObject colorObjeto;
        bg1 = new ButtonGroup();
        bg2 = new ButtonGroup();
        bg3 = new ButtonGroup();
        bg4 = new ButtonGroup();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < colores.length(); i++) {
                colorObjeto = (JSONObject) colores.get(i);
                String nombre = colorObjeto.getString("Nombre");
                String codigo = colorObjeto.getString("Codigo");
                //Nombre colores - buttongroup
                JRadioButton jrb = new JRadioButton(nombre);
                jrb.setMargin(new Insets(0, 25, 0, 0));
                jrb.setToolTipText(codigo);
                if (j==0) {
                    jrb = addUserPreference(jrb, codigo,"CodigoFondo");
                    bg1.add(jrb);
                }
                else if (j==1) {
                    jrb = addUserPreference(jrb, codigo, "CodigoBola");
                    bg2.add(jrb);
                }
                else if (j==2) {
                    jrb = addUserPreference(jrb, codigo, "CodigoPaddle");
                    bg3.add(jrb);
                }
                else {
                    jrb = addUserPreference(jrb, codigo, "CodigoLadrillo");
                    bg4.add(jrb);
                }
                //Colores - Labels
                JLabel color = new JLabel();
                color.setBorder(new LineBorder(Color.black, 2, true)); //Cambiar a blanco al juntar las ramas
                color.setPreferredSize(new Dimension(40, 40));
                int[] rgb = obtenerRGB(codigo);
                color.setBackground(new Color(rgb[0], rgb[1], rgb[2]));
                color.setOpaque(true);
                JLabel espacio = new JLabel();
                if (j==0) {
                    cfpLabels.add(color);
                    cfpLabels.add(espacio);
                    cfButtons.add(jrb);
                }
                else if (j==1) {
                    cbpLabels.add(color);
                    cbpLabels.add(espacio);
                    cbButtons.add(jrb);
                }
                else if (j==2) {
                    clpLabels.add(color);
                    clpLabels.add(espacio);
                    clButtons.add(jrb);
                }
                else {
                    cppLabels.add(color);
                    cppLabels.add(espacio);
                    cpButtons.add(jrb);
                }
            }
        }
    }
	
	private JRadioButton addUserPreference(JRadioButton jrb, String name, String codUsu) {
        String codigo = personalizablesJugador.getString(codUsu);
        if (name.equals(codigo)) jrb.setSelected(true);
        return jrb;
    }

    private int[] obtenerRGB(String codigo) {
        String[] codS = codigo.split(",");
        int r = Integer.parseInt(codS[0]);
        int g = Integer.parseInt(codS[1]);
        int b = Integer.parseInt(codS[2]);
        int[] rgb = new int[3];
        rgb[0] = r;
        rgb[1] = g;
        rgb[2] = b;
        return rgb;
    }
    
    private void ponerSonidos() {
        bgS = new ButtonGroup();
        Box ver = Box.createVerticalBox();
        JSONObject sonidoObjeto;
        for (int i = 0; i < sonidos.length(); i++) {
            Box hor = Box.createHorizontalBox();
            sonidoObjeto = (JSONObject) sonidos.get(i);
            String nombre = sonidoObjeto.getString("Nombre");
            final String path = sonidoObjeto.getString("Path");
            JRadioButton jrb = new JRadioButton(nombre);
            jrb.setMargin(new Insets(15, 15, 15, 15));
            jrb = addUserPreference(jrb, path, "PathMusica");
            bgS.add(jrb);
            hor.add(jrb);
            JButton play = new JButton();
            play.setBorder(null);
            play.setContentAreaFilled(false);
            play.setIcon(new ImageIcon(getClass().getResource("/sonidoPersonalizar/play.png")));
            JButton pause = new JButton();
            pause.setBorder(null);
            pause.setContentAreaFilled(false);
            pause.setIcon(new ImageIcon(getClass().getResource("/sonidoPersonalizar/pausa.png")));

            play.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
                        if (clip != null && clip.isActive()) {
                        	clip.stop();
                        }
                        clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                        
                    } catch (Exception ex) {
                        System.out.println("Error with playing sound.");
                        ex.printStackTrace();
                    }
                }
            });
            pause.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    clip.stop();
                }
            });

            hor.add(play);
            hor.add(Box.createRigidArea(new Dimension(10, 0))); //Para a�adir espacio entre botones
            hor.add(pause);
            ver.add(hor);
        }
        sonidosButtons.add(ver);
    }
    
    private void inciadoSesion(){
        //boolean identificado = Arkanoid.getArkanoid().isIdentificado();
        boolean identificado = true;
        if (identificado) {
            personalizarPestanas.setEnabledAt(2, true);
        } else {
            personalizarPestanas.setEnabledAt(2, false);
        }
    }

    private void ponerNivelPersonalizado(){
        String atributos = personalizablesJugador.getString("atributosPersonalizado");
        String[] valores = atributos.split(",");
        jsBloques.setValue(Integer.parseInt(valores[0]));
        jsPaddle.setValue(Integer.parseInt(valores[1]));
        jsBola.setValue(Integer.parseInt(valores[2]));
    }

    private void guardarPersonalizacion(){
        //Colores
        String color1 = "";
        String color2 = "";
        String color3 = "";
        String color4 = "";
        String sonido = "";
        for (Enumeration<AbstractButton> buttons = bg1.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                color1 = button.getToolTipText();
            }
        }
        for (Enumeration<AbstractButton> buttons = bg2.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                color2 = button.getToolTipText();
            }
        }
        for (Enumeration<AbstractButton> buttons = bg3.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                color3 = button.getToolTipText();
            }
        }
        for (Enumeration<AbstractButton> buttons = bg4.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                color4 = button.getToolTipText();
            }
        }
        //Sonido
        for (Enumeration<AbstractButton> buttons = bgS.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
            	String path = button.getText();
            	path = path.replace(" ", "");
            	if (path.contains("6")) {
            		sonido = "/sonidoPersonalizar/"+path+".mp3";
            	} else {
            		sonido = "/sonidoPersonalizar/"+path+".wav";
            	}
            }
        }

        //Atributos nivel personalizado
        int bloques = jsBloques.getValue();
        int paddle = jsPaddle.getValue();
        int bola = jsBola.getValue();
        String atributos = ""+bloques+","+paddle+","+bola+"";

        Arkanoid.getArkanoid().actualizarPersonalizacionDB(sonido, color1, color2, color3, color4, atributos);
        //Arkanoid.getArkanoid().actualizarPersonalizacionUsu(sonido, color1, color2, color3, color4, atributos);
    }
    
    private void onCancel() {
        dispose();
    }
}
