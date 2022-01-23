package eus.ehu.adsi.arkanoid.vista; 
 
import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.EventQueue; 
import java.awt.FlowLayout; 
 
import javax.swing.JButton; 
import javax.swing.JDialog; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JRadioButton; 
import javax.swing.border.EmptyBorder; 
import javax.swing.border.LineBorder; 
import javax.swing.event.AncestorEvent; 
import javax.swing.event.AncestorListener; 
import javax.swing.event.ChangeEvent; 
import javax.swing.event.ChangeListener; 
 
import org.json.JSONArray; 
import org.json.JSONObject; 
 
import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.modelo.Config;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.Boton; 
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaNormal; 
import eus.ehu.adsi.arkanoid.vista.claseObjetos.PanelNegro; 
import eus.ehu.adsi.arkanoid.vista.claseObjetos.RadioButton; 
import eus.ehu.adsi.arkanoid.vista.claseObjetos.ScrollPane; 
import eus.ehu.adsi.arkanoid.vista.claseObjetos.Slider; 
import eus.ehu.adsi.arkanoid.vista.claseObjetos.TabbedPanel; 
 
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
import java.util.Observable; 
import java.util.Observer; 

import javax.swing.SwingConstants; 
import javax.swing.BoxLayout; 
import java.awt.Component; 
 
@SuppressWarnings("deprecation")
public class IU_Personalizacion extends JFrame implements Observer { 
 
	private final JPanel tabPanel = new JPanel(); 
	private TabbedPanel personalizarPestanas; 
	private TransparentPanel cbButtons, cbpLabels, clButtons, clpLabels, cppLabels, cpButtons, cfpLabels, cfButtons; 
	private TransparentPanel sonidosButtons; 
	private ScrollPane jspB, jspF, jspL, jspP; 
	private Slider jsBloques, jsPaddle, jsBola; 
	 
	private JSONArray colores, sonidos; 
    private JSONObject personalizablesJugador; 
    private ButtonGroup bg1, bg2, bg3, bg4, bgS; 
    private Clip clip; 
    private InterfazBase ib; 
 
    /** 
	 * Launch the application. 
	 */ 
	public void mostrarVentana() { 
		EventQueue.invokeLater(new Runnable() { 
			public void run() { 
				try { 
					IU_Personalizacion frame = new IU_Personalizacion(); 
					frame.setVisible(true); 
					frame.setResizable(false); 
					frame.setLocationRelativeTo(null); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
			} 
		}); 
	} 
 
	/** 
	 * Create the dialog. 
	 */ 
	@SuppressWarnings("deprecation") 
	public IU_Personalizacion() { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 600, 600); 
		getContentPane().setLayout(new BorderLayout()); 
		 
		Arkanoid.getArkanoid().addObserver(this); 
		 
		ib = new InterfazBase("PERSONALIZAR"); 
		((InterfazBase) ib).setEventoRegreso(new IU_Niveles()); 
		getContentPane().add(ib, BorderLayout.CENTER); 
		ib.setIdentificado(Arkanoid.getArkanoid().isIdentificado()); 
		 
		tabPanel.setBorder(new EmptyBorder(10,10,10,10)); 
		tabPanel.setBackground(new Color(0,0,0,0)); 
		tabPanel.setLayout(new GridLayout(0, 1, 0, 0)); 
		{ 
			personalizarPestanas = new TabbedPanel();	 
			 
			personalizarPestanas.addChangeListener(new ChangeListener() { 
                @Override 
                public void stateChanged(ChangeEvent e) { 
                    repaint(); 
                    revalidate(); 
                } 
            }); 
			 
			tabPanel.add(personalizarPestanas); 
			{ 
				PanelNegro coloresPersonalizar = new PanelNegro(); 
				personalizarPestanas.addTab("COLORES", null, coloresPersonalizar, null); 
				coloresPersonalizar.setLayout(new GridLayout(0, 1, 0, 0)); 
				{ 
					ScrollPane scrollPane = new ScrollPane(); 
					coloresPersonalizar.add(scrollPane); 
					{ 
						TransparentPanel colores = new TransparentPanel(); 
						scrollPane.setViewportView(colores); 
						colores.setLayout(new GridLayout(4, 0, 0, 0)); 
						{ 
							TransparentPanel coloresFondo = new TransparentPanel(); 
							coloresFondo.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(255, 255, 255), 2, true))); 
							colores.add(coloresFondo); 
							coloresFondo.setLayout(new BoxLayout(coloresFondo, BoxLayout.X_AXIS)); 
							{ 
								Component horizontalStrut = Box.createHorizontalStrut(15); 
								coloresFondo.add(horizontalStrut); 
							} 
							{ 
								EtiquetaNormal lblNewLabel = new EtiquetaNormal("Fondo"); 
								lblNewLabel.setVerticalAlignment(SwingConstants.TOP); 
								lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER); 
								lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
								coloresFondo.add(lblNewLabel); 
							} 
							{ 
								Component horizontalStrut = Box.createHorizontalStrut(13); 
								coloresFondo.add(horizontalStrut); 
							} 
							{ 
								TransparentPanel fondo = new TransparentPanel(); 
								coloresFondo.add(fondo); 
								fondo.setLayout(new GridLayout(2, 1, 0, 0)); 
								{ 
									TransparentPanel panel = new TransparentPanel(); 
									panel.setBorder(new EmptyBorder(10, 35, 5, 0)); 
									fondo.add(panel); 
									panel.setLayout(new GridLayout(0, 1, 0, 0)); 
									{ 
										jspF = new ScrollPane(); 
										jspF.setBorder(null); 
										panel.add(jspF); 
										{ 
											cfpLabels = new TransparentPanel(); 
											jspF.setViewportView(cfpLabels); 
											cfpLabels.setLayout(new GridLayout(1, 0, 0, 0)); 
										} 
									} 
								} 
								{ 
									cfButtons = new TransparentPanel(); 
									cfButtons.setBorder(new EmptyBorder(0, 0, 0, 0)); 
									fondo.add(cfButtons); 
									cfButtons.setLayout(new BoxLayout(cfButtons, BoxLayout.X_AXIS)); 
								} 
							} 
						} 
						{ 
							TransparentPanel coloresBola = new TransparentPanel(); 
							coloresBola.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(255, 255, 255), 2, true))); 
							colores.add(coloresBola); 
							coloresBola.setLayout(new BoxLayout(coloresBola, BoxLayout.X_AXIS)); 
							{ 
								Component horizontalStrut = Box.createHorizontalStrut(15); 
								coloresBola.add(horizontalStrut); 
							} 
							{ 
								EtiquetaNormal lblNewLabel_1 = new EtiquetaNormal("Bola"); 
								coloresBola.add(lblNewLabel_1); 
							} 
							{ 
								Component horizontalStrut = Box.createHorizontalStrut(30); 
								coloresBola.add(horizontalStrut); 
							} 
							{ 
								TransparentPanel bola = new TransparentPanel(); 
								coloresBola.add(bola); 
								bola.setLayout(new GridLayout(2, 0, 0, 0)); 
								{ 
									TransparentPanel panel = new TransparentPanel(); 
									panel.setBorder(new EmptyBorder(10, 35, 5, 0)); 
									bola.add(panel); 
									panel.setLayout(new GridLayout(0, 1, 0, 0)); 
									{ 
										jspB = new ScrollPane(); 
										panel.add(jspB); 
										{ 
											cbpLabels = new TransparentPanel(); 
											jspB.setViewportView(cbpLabels); 
											cbpLabels.setLayout(new GridLayout(1, 0, 0, 0)); 
										} 
									} 
								} 
								{ 
									cbButtons = new TransparentPanel(); 
									cbButtons.setBorder(new EmptyBorder(0, 0, 0, 0)); 
									bola.add(cbButtons); 
									cbButtons.setLayout(new BoxLayout(cbButtons, BoxLayout.X_AXIS)); 
								} 
							} 
						} 
						{ 
							TransparentPanel coloresLadrillos = new TransparentPanel(); 
							coloresLadrillos.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(255, 255, 255), 2, true))); 
							colores.add(coloresLadrillos); 
							coloresLadrillos.setLayout(new BoxLayout(coloresLadrillos, BoxLayout.X_AXIS)); 
							{ 
								Component horizontalStrut = Box.createHorizontalStrut(15); 
								coloresLadrillos.add(horizontalStrut); 
							} 
							{ 
								EtiquetaNormal lblNewLabel_2 = new EtiquetaNormal("Ladrillos"); 
								coloresLadrillos.add(lblNewLabel_2); 
							} 
							{ 
								TransparentPanel ladrillos = new TransparentPanel(); 
								coloresLadrillos.add(ladrillos); 
								ladrillos.setLayout(new GridLayout(2, 0, 0, 0)); 
								{ 
									TransparentPanel panel = new TransparentPanel(); 
									panel.setBorder(new EmptyBorder(10, 30, 5, 0)); 
									ladrillos.add(panel); 
									panel.setLayout(new GridLayout(0, 1, 0, 0)); 
									{ 
										jspL = new ScrollPane(); 
										panel.add(jspL); 
										{ 
											clpLabels = new TransparentPanel(); 
											jspL.setViewportView(clpLabels); 
											clpLabels.setLayout(new GridLayout(1, 0, 0, 0)); 
										} 
									} 
								} 
								{ 
									clButtons = new TransparentPanel(); 
									clButtons.setBorder(new EmptyBorder(0, 0, 0, 10)); 
									ladrillos.add(clButtons); 
									clButtons.setLayout(new BoxLayout(clButtons, BoxLayout.X_AXIS)); 
								} 
							} 
						} 
						{ 
							TransparentPanel coloresPaddle = new TransparentPanel(); 
							coloresPaddle.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(255, 255, 255), 2, true))); 
							colores.add(coloresPaddle); 
							coloresPaddle.setLayout(new BoxLayout(coloresPaddle, BoxLayout.X_AXIS)); 
							{ 
								Component horizontalStrut = Box.createHorizontalStrut(15); 
								coloresPaddle.add(horizontalStrut); 
							} 
							{ 
								EtiquetaNormal lblNewLabel_3 = new EtiquetaNormal("Paddle"); 
								coloresPaddle.add(lblNewLabel_3); 
							} 
							{ 
								Component horizontalStrut = Box.createHorizontalStrut(8); 
								coloresPaddle.add(horizontalStrut); 
							} 
							{ 
								TransparentPanel paddle = new TransparentPanel(); 
								coloresPaddle.add(paddle); 
								paddle.setLayout(new GridLayout(2, 0, 0, 0)); 
								{ 
									TransparentPanel panel = new TransparentPanel(); 
									panel.setBorder(new EmptyBorder(10, 35, 5, 0)); 
									paddle.add(panel); 
									panel.setLayout(new GridLayout(0, 1, 0, 0)); 
									{ 
										jspP = new ScrollPane(); 
										panel.add(jspP); 
										{ 
											cppLabels = new TransparentPanel(); 
											jspP.setViewportView(cppLabels); 
											cppLabels.setLayout(new GridLayout(1, 0, 0, 0)); 
										} 
									} 
								} 
								{ 
									cpButtons = new TransparentPanel(); 
									cpButtons.setBorder(new EmptyBorder(0, 0, 0, 0)); 
									paddle.add(cpButtons); 
									cpButtons.setLayout(new BoxLayout(cpButtons, BoxLayout.X_AXIS)); 
								} 
							} 
						} 
					} 
				} 
			} 
			{ 
				PanelNegro sonidoPersonalizar = new PanelNegro(); 
				personalizarPestanas.addTab("SONIDOS", null, sonidoPersonalizar, null); 
				sonidoPersonalizar.setLayout(new GridLayout(0, 1, 0, 0)); 
				{ 
					ScrollPane scrollPane = new ScrollPane(); 
					sonidoPersonalizar.add(scrollPane); 
					{ 
						TransparentPanel panel = new TransparentPanel(); 
						panel.setBorder(new EmptyBorder(10, 0, 0, 0)); 
						scrollPane.setViewportView(panel); 
						panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
						{ 
							EtiquetaNormal lblNewLabel_4 = new EtiquetaNormal("Escoge la música que quieras escuchar:"); 
							lblNewLabel_4.setAlignmentX(Component.CENTER_ALIGNMENT); 
							panel.add(lblNewLabel_4); 
						} 
						{ 
							sonidosButtons = new TransparentPanel(); 
							sonidosButtons.setBorder(new EmptyBorder(20, 0, 0, 0)); 
							panel.add(sonidosButtons); 
							sonidosButtons.setLayout(new GridLayout(0, 1, 0, 0)); 
						} 
					} 
				} 
			} 
			{ 
				PanelNegro dimensionesPersonalizar = new PanelNegro(); 
				personalizarPestanas.addTab("DIMENSIONES", null, dimensionesPersonalizar, null); 
				dimensionesPersonalizar.setLayout(new GridLayout(0, 1, 0, 0)); 
				{ 
					ScrollPane scrollPane = new ScrollPane(); 
					dimensionesPersonalizar.add(scrollPane); 
					{ 
						TransparentPanel panel = new TransparentPanel(); 
						scrollPane.setViewportView(panel); 
						panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
						{ 
							EtiquetaNormal lblNewLabel_5 = new EtiquetaNormal("Personaliza los elementos del juego:"); 
							lblNewLabel_5.setAlignmentX(Component.CENTER_ALIGNMENT); 
							panel.add(lblNewLabel_5); 
						} 
						{ 
							TransparentPanel panel_1 = new TransparentPanel(); 
							panel_1.setBorder(new EmptyBorder(0, 0, 0, 25));
							panel.add(panel_1); 
							panel_1.setLayout(new GridLayout(3, 0, 0, 0)); 
							{ 
								TransparentPanel panel_2 = new TransparentPanel(); 
								panel_1.add(panel_2);
								panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
								{ 
									TransparentPanel panel_3 = new TransparentPanel(); 
									panel_2.add(panel_3); 
									panel_3.setLayout(new GridLayout(0, 1, 0, 0)); 
									{ 
										EtiquetaNormal lblNewLabel_6 = new EtiquetaNormal("Número bloques"); 
										lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER); 
										panel_3.add(lblNewLabel_6); 
									} 
								} 
								{ 
									TransparentPanel panel_3 = new TransparentPanel(); 
									panel_2.add(panel_3); 
									panel_3.setLayout(new GridLayout(0, 1, 0, 0)); 
									{ 
										jsBloques = new Slider(); 
										jsBloques.setMinorTickSpacing(11); 
										jsBloques.setMinimum(22); 
										jsBloques.setMaximum(88); 
										jsBloques.setMajorTickSpacing(11); 
										panel_3.add(jsBloques); 
									} 
								} 
							} 
							{ 
								TransparentPanel panel_2 = new TransparentPanel(); 
								panel_1.add(panel_2);
								panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
								{ 
									TransparentPanel panel_3 = new TransparentPanel(); 
									panel_2.add(panel_3); 
									panel_3.setLayout(new GridLayout(0, 1, 0, 0)); 
									{ 
										EtiquetaNormal lblNewLabel_7 = new EtiquetaNormal("Tamaño paddle"); 
										lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER); 
										panel_3.add(lblNewLabel_7); 
									} 
								} 
								{ 
									TransparentPanel panel_3 = new TransparentPanel(); 
									panel_2.add(panel_3); 
									panel_3.setLayout(new GridLayout(0, 1, 0, 0)); 
									{ 
										jsPaddle = new Slider(); 
										jsPaddle.setMinorTickSpacing(5); 
										jsPaddle.setMinimum(40); 
										jsPaddle.setMaximum(80); 
										jsPaddle.setMajorTickSpacing(10); 
										panel_3.add(jsPaddle); 
									} 
								} 
							} 
							{ 
								TransparentPanel panel_2 = new TransparentPanel(); 
								panel_1.add(panel_2);
								panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
								{ 
									TransparentPanel panel_3 = new TransparentPanel(); 
									panel_2.add(panel_3); 
									panel_3.setLayout(new GridLayout(0, 1, 0, 0)); 
									{ 
										EtiquetaNormal lblNewLabel_8 = new EtiquetaNormal("Velocidad bola"); 
										lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER); 
										panel_3.add(lblNewLabel_8); 
									} 
								} 
								{ 
									TransparentPanel panel_3 = new TransparentPanel(); 
									panel_2.add(panel_3); 
									panel_3.setLayout(new GridLayout(0, 1, 0, 0)); 
									{ 
										jsBola = new Slider(); 
										jsBola.setMinorTickSpacing(1); 
										jsBola.setMinimum(1); 
										jsBola.setMaximum(7); 
										jsBola.setMajorTickSpacing(1); 
										panel_3.add(jsBola); 
									} 
								} 
							} 
						} 
					} 
				} 
			} 
		} 
		ib.panelPrincipal.setLayout(new GridLayout(0, 1, 0, 0)); 
		 
		ib.panelPrincipal.add(tabPanel); 
		 
		{ 
			JPanel buttonPane = new JPanel(); 
			buttonPane.setBackground(new Color(0,0,0,0)); 
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT)); 
			 
			ib.add(buttonPane, BorderLayout.SOUTH); 
			{ 
				Boton guardarButton = new Boton("GUARDAR"); 
				guardarButton.addActionListener(new ActionListener() { 
		            public void actionPerformed(ActionEvent e) { 
		                guardarPersonalizacion(); 
		                Arkanoid.getArkanoid().deleteObserver(IU_Personalizacion.this); 
		                new IU_Niveles().setVisible(true);
		                dispose();
		            } 
		        }); 
				buttonPane.add(guardarButton); 
			} 
		} 
		 
		getPersonalizables(); 
		ponerColores(); 
		ponerSonidos(); 
		if (Arkanoid.getArkanoid().isIdentificado()) {
			ponerNivelPersonalizado(); 
		}
        
        
        boolean identificado = Arkanoid.getArkanoid().isIdentificado(); 
        if (!identificado) { 
        	personalizarPestanas.setEnabledAt(2, false); 
        	personalizarPestanas.setToolTipTextAt(2, "Tienes que iniciar sesión!"); 
        }
         
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
        if (Arkanoid.getArkanoid().isIdentificado()) {
        	personalizablesJugador = Arkanoid.getArkanoid().obtenerPersonalizacionUsuario(); 
        } else {
        	personalizablesJugador = new JSONObject();
        	personalizablesJugador.put("PathMusica", Config.PATH_MUSICA);
        	personalizablesJugador.put("CodigoFondo", Config.BACKGROUND_COLOR);
        	personalizablesJugador.put("CodigoBola", Config.BALL_COLOR);
        	personalizablesJugador.put("CodigoPaddle", Config.PADDLE_COLOR);
        	personalizablesJugador.put("CodigoLadrillo", Config.BRICK_COLOR);
        }
        
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
                RadioButton jrb = new RadioButton(nombre); 
                jrb.setMargin(new Insets(0, 40, 0, 0)); 
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
                color.setBorder(new LineBorder(Color.white, 2, true)); 
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
	 
	private RadioButton addUserPreference(RadioButton jrb, String name, String codUsu) { 
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
            RadioButton jrb = new RadioButton(nombre); 
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
            hor.add(Box.createRigidArea(new Dimension(10, 0)));
            hor.add(pause); 
            ver.add(hor); 
        } 
        sonidosButtons.add(ver); 
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
         
      boolean identificado = Arkanoid.getArkanoid().isIdentificado(); 
        if (identificado) { 
        	//Atributos nivel personalizado 
            int bloques = jsBloques.getValue(); 
            int paddle = jsPaddle.getValue(); 
            int bola = jsBola.getValue(); 
            String atributos = ""+bloques+","+paddle+","+bola+""; 
 
            Arkanoid.getArkanoid().actualizarPersonalizacionDB(sonido, color1, color2, color3, color4, atributos); 
            Arkanoid.getArkanoid().actualizarPersonalizacionUsu(sonido, color1, color2, color3, color4, atributos); 
            Double[] datos = new Double[3]; 
            datos[0] = (double) bola; 
            datos[1] = (double) paddle; 
            datos[2] = (double)bloques; 
            Arkanoid.getArkanoid().updateConfig(datos); 
        }  
         
        Arkanoid.getArkanoid().updateColores(color1, color2, color3, color4); 
    	Arkanoid.getArkanoid().updateMusica(sonido);         
    } 
     
    private void onCancel() { 
        dispose(); 
    } 
     
    @SuppressWarnings("deprecation") 
	@Override 
	public void update(Observable arg0, Object arg1) { 
		if (arg1 instanceof Boolean){ 
			ib.setIdentificado((boolean) arg1); 
			personalizarPestanas.setEnabledAt(2, (boolean)arg1); 
			}
			if (!(boolean)arg1) {
				personalizarPestanas.setSelectedComponent(personalizarPestanas.getComponentAt(0));
			}
			else {
				new IU_Personalizacion().mostrarVentana();
				dispose();
		}
	} 
} 
