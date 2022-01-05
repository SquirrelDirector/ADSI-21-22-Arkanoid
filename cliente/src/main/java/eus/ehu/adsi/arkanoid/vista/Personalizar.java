package eus.ehu.adsi.arkanoid.vista;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

public class Personalizar extends JDialog {
    private JPanel contentPane;
    private JButton guardarButton, volverButton;
    private JLabel tituloPersonalizar;
    private JTabbedPane personalizarPestañas;
    private JPanel coloresPersonalizar, colorFondo, colorBola, colorLadrillos, colorPaddle, cbButtons,
            cbpLabels, cfButtons, cfpLabels, clButtons, clpLabels, cppLabels, cpButtons;
    private JPanel sonidoPersonalizar, sonidosButtons;
    private JPanel dimensaionesPersonalizar;
    private JLabel fondoLabel, bolaLabel, ladrilloLabel, paddleLabel;
    private JScrollPane jspC1, jspC2, jspF1, jspF2, jspL1, jspL2, jspP1, jspP2, jspSonidos;
    private JSlider jsBloques, jsPaddle, jsBola;

    private JSONArray colores, sonidos;
    private JSONObject personalizablesJugador;
    private ButtonGroup bg1, bg2, bg3, bg4, bgS;
    private Clip clip;
    private final Font font;

    public Personalizar() {

        this.setPreferredSize(new Dimension(535, 600));
        
        getPersonalizables();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(guardarButton);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        font = new Font("Serif", Font.PLAIN, 14);

        ponerColores();
        ponerSonidos();
        inciadoSesion();
        ponerNivelPersonalizado();

        if (!personalizarPestañas.isEnabledAt(2)){
            personalizarPestañas.setToolTipTextAt(2, "Tienes que iniciar sesión!");
        }

        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarPersonalizacion();
            }
        });

        volverButton.setBorder(null);
        volverButton.setContentAreaFilled(false);

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

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setLocationRelativeTo(null);
    }

    private void getPersonalizables() {
        /*JSONObject personalizables = Arkanoid.getArkanoid().obtenerPersonalizables();
        colores = personalizables.getJSONArray("colores");
        sonidos = personalizables.getJSONArray("sonidos");
        personalizablesJugador = Arkanoid.getArkanoid().obtenerPersonalizacionUsuario();*/
        //Para pruebas
        JSONObject colorO = new JSONObject();
        colorO.put("Codigo", "255,0,0");
        colorO.put("Nombre", "Rojo");
        JSONObject color1 = new JSONObject();
        color1.put("Codigo", "255,181,0");
        color1.put("Nombre", "Naranja");
        JSONObject color2 = new JSONObject();
        color2.put("Codigo", "244,255,0");
        color2.put("Nombre", "Amarillo");
        JSONObject color3 = new JSONObject();
        color3.put("Codigo", "78,255,0");
        color3.put("Nombre", "Verde");
        colores = new JSONArray();
        colores.put(colorO);
        colores.put(color1);
        colores.put(color2);
        colores.put(color3);
        personalizablesJugador = new JSONObject();
        personalizablesJugador.put("PathMusica", "/sonidoPersonalizar/background5.wav");
        personalizablesJugador.put("CodigoFondo", "255,0,0");
        personalizablesJugador.put("CodigoBola", "255,181,0");
        personalizablesJugador.put("CodigoLadrillo", "244,255,0");
        personalizablesJugador.put("CodigoPaddle", "78,255,0");
        personalizablesJugador.put("atributosPersonalizado", "44,60,4");
        JSONObject sonidoO = new JSONObject();
        sonidoO.put("Path", "/sonidoPersonalizar/background1.wav");
        sonidoO.put("Nombre", "Sonido 1");
        JSONObject sonido1 = new JSONObject();
        sonido1.put("Path", "/sonidoPersonalizar/background2.wav");
        sonido1.put("Nombre", "Sonido 2");
        JSONObject sonido2 = new JSONObject();
        sonido2.put("Path", "/sonidoPersonalizar/background3.wav");
        sonido2.put("Nombre", "Sonido 3");
        JSONObject sonido3 = new JSONObject();
        sonido3.put("Path", "/sonidoPersonalizar/background4.wav");
        sonido3.put("Nombre", "Sonido 4");
        JSONObject sonido4 = new JSONObject();
        sonido4.put("Path", "/sonidoPersonalizar/background5.wav");
        sonido4.put("Nombre", "Sonido 5");
        sonidos = new JSONArray();
        sonidos.put(sonidoO);
        sonidos.put(sonido1);
        sonidos.put(sonido2);
        sonidos.put(sonido3);
        sonidos.put(sonido4);
    }

    private void ponerColores() {
        cbButtons = new JPanel();
        cbpLabels = new JPanel();
        cfButtons = new JPanel();
        cfpLabels = new JPanel();
        clButtons = new JPanel();
        clpLabels = new JPanel();
        cpButtons = new JPanel();
        cppLabels = new JPanel();
        JSONObject colorObjeto;
        for (int j = 0; j < 4; j++) {
            if (j==0) bg1 = new ButtonGroup();
            else if (j==1) bg2 = new ButtonGroup();
            else if (j==2) bg3 = new ButtonGroup();
            else bg4 = new ButtonGroup();
            Box hori1 = Box.createHorizontalBox();
            for (int i = 0; i < colores.length(); i++) {
                colorObjeto = (JSONObject) colores.get(i);
                String nombre = colorObjeto.getString("Nombre");
                String codigo = colorObjeto.getString("Codigo");
                //Nombre colores - buttongroup
                JRadioButton jrb = new JRadioButton(nombre);
                jrb.setFont(font);
                jrb.setToolTipText(codigo);
                jrb.setMargin(new Insets(0, 10, 0, 10));
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
                hori1.add(jrb);
                //Colores - Labels
                JLabel color = new JLabel();
                color.setBorder(new LineBorder(Color.black, 2, true));
                color.setPreferredSize(new Dimension(50, 50));
                int[] rgb = obtenerRGB(codigo);
                color.setBackground(new Color(rgb[0], rgb[1], rgb[2]));
                color.setOpaque(true);
                JLabel espacio = new JLabel();
                espacio.setPreferredSize(new Dimension(20, 0));
                if (j==0) {
                    cfpLabels.add(color);
                    cfpLabels.add(espacio);
                }
                else if (j==1) {
                    cbpLabels.add(color);
                    cbpLabels.add(espacio);
                }
                else if (j==2) {
                    clpLabels.add(color);
                    clpLabels.add(espacio);
                }
                else {
                    cppLabels.add(color);
                    cppLabels.add(espacio);
                }

            }
            if (j==0) {
                cfButtons.add(hori1);
                jspF2.setViewportView(cfButtons);
                jspF1.setViewportView(cfpLabels);
            }
            else if (j==1) {
                cbButtons.add(hori1);
                jspC2.setViewportView(cbButtons);
                jspC1.setViewportView(cbpLabels);
            }
            else if (j==2) {
                clButtons.add(hori1);
                jspL2.setViewportView(clButtons);
                jspL1.setViewportView(clpLabels);
            }
            else {
                cpButtons.add(hori1);
                jspP2.setViewportView(cpButtons);
                jspP1.setViewportView(cppLabels);
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
        sonidosButtons = new JPanel();
        bgS = new ButtonGroup();
        Box ver = Box.createVerticalBox();
        JSONObject sonidoObjeto;
        for (int i = 0; i < sonidos.length(); i++) {
            Box hor = Box.createHorizontalBox();
            sonidoObjeto = (JSONObject) sonidos.get(i);
            String nombre = sonidoObjeto.getString("Nombre");
            final String path = sonidoObjeto.getString("Path");
            JRadioButton jrb = new JRadioButton(nombre);
            jrb.setFont(font);
            jrb.setToolTipText(path);
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
            hor.add(Box.createRigidArea(new Dimension(10, 0))); //Para añadir espacio entre botones
            hor.add(pause);
            ver.add(hor);
        }
        sonidosButtons.add(ver);
        jspSonidos.setViewportView(sonidosButtons);
    }

    private void onCancel() {
        dispose();
    }

    private void inciadoSesion(){
        //boolean identificado = Arkanoid.getArkanoid().isIdentificado();
        boolean identificado = true;
        if (identificado) {
            personalizarPestañas.setEnabledAt(2, true);
        } else {
            personalizarPestañas.setEnabledAt(2, false);
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
                sonido = button.getToolTipText();
            }
        }

        //Atributos nivel personalizado
        int bloques = jsBloques.getValue();
        int paddle = jsPaddle.getValue();
        int bola = jsBola.getValue();
        String atributos = ""+bloques+","+paddle+","+bola+"";

        //Arkanoid.getArkanoid().actualizarPersonalizacionDB(sonido, color1, color2, color3, color4, atributos);
        //Arkanoid.getArkanoid().actualizarPersonalizacionUsu(sonido, color1, color2, color3, color4, atributos);
    }

    public static void main(String[] args) {
        Personalizar dialog = new Personalizar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
