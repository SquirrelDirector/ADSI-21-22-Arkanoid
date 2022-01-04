package eus.ehu.adsi.arkanoid.vista;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import javafx.scene.layout.Border;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

public class Personalizar extends JDialog {
    private JPanel contentPane;
    private JButton guardarButton, volverButton;
    private JLabel tituloPersonalizar;
    private JTabbedPane personalizarPestañas;
    private JPanel coloresPersonalizar, colorFondo, colorBola, colorLadrillos, colorPaddle;
    private JPanel sonidoPersonalizar;
    private JPanel dimensaionesPersonalizar;
    private JLabel fondoLabel, bolaLabel, ladrilloLabel, paddleLabel;
    private JScrollPane jspC1, jspC2, jspF1, jspF2, jspL1, jspL2, jspP1, jspP2;
    private JPanel cbButtons, cbpLabels, cfButtons, cfpLabels, clButtons, clpLabels, cppLabels, cpButtons;

    private JSONArray colores, sonidos;
    private JSONObject personalizablesJugador;
    private ButtonGroup bg1, bg2, bg3, bg4;

    public Personalizar() {

        this.setPreferredSize(new Dimension(500, 600));
        
        getPersonalizables();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(guardarButton);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ponerColores();
        ponerSonidos();

        inciadoSesion();

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
        personalizablesJugador.put("CodigoFondo", "255,0,0");
        personalizablesJugador.put("CodigoBola", "255,181,0");
        personalizablesJugador.put("CodigoLadrillo", "244,255,0");
        personalizablesJugador.put("CodigoPaddle", "78,255,0");
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
                if (j==0) cfpLabels.add(color);
                else if (j==1) cbpLabels.add(color);
                else if (j==2) clpLabels.add(color);
                else cppLabels.add(color);
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
        //Colores
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

    }

    private void onCancel() {
        dispose();
    }

    private void guardarPersonalizacion(){
        //Colores
        String color1 = "";
        String color2 = "";
        String color3 = "";
        String color4 = "";
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

        //Arkanoid.getArkanoid().actualizarPersonalizacionDB(color1, color2, color3, color4);
        //Arkanoid.getArkanoid().actualizarPersonalizacionUsu(color1, color2, color3, color4);
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

    public static void main(String[] args) {
        Personalizar dialog = new Personalizar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
