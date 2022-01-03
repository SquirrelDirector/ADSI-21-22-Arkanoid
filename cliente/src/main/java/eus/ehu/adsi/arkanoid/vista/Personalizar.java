package eus.ehu.adsi.arkanoid.vista;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Personalizar extends JDialog {
    private JPanel contentPane;
    private JButton guardarButton, volverButton;
    private JLabel tituloPersonalizar;
    private JTabbedPane personalizarPestañas;
    private JPanel coloresPersonalizar, colorFondo, colorBola, colorLadrillos, colorPaddle;
    private JPanel sonidoPersonalizar;
    private JPanel dimensaionesPersonalizar;
    private JLabel fondoLabel, bolaLabel, ladrilloLabel, paddleLabel;
    private JLabel cf1;
    private JLabel cf2;
    private JLabel cf3;
    private JLabel cf4;
    private JPanel cfButtons;
    private JScrollPane jspFondo;
    private JScrollPane jspC1;
    private JScrollPane jspC2;
    private JPanel cbLabels;
    private JPanel cbButtons;

    private JSONArray colores, sonidos;
    private JSONObject personalizablesJugador;

    public Personalizar() {

        this.setPreferredSize(new Dimension(600, 600));
        
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

        getPreferredSize();
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
        cbLabels = new JPanel();
        JSONObject colorObjeto;
        ButtonGroup bg = new ButtonGroup();
        Box hori1 = Box.createHorizontalBox();
        Box hori2 = Box.createHorizontalBox();
        for (int i = 0; i < colores.length(); i++) {
            colorObjeto = (JSONObject) colores.get(i);
            //Nombre colores - buttongroup
            String nombre = colorObjeto.getString("Nombre");
            JRadioButton jrb = new JRadioButton(nombre);
            bg.add(jrb);
            hori1.add(jrb);
            //Colores - Labels
            String codigo = colorObjeto.getString("Codigo");
            JLabel color = new JLabel();
            color.setPreferredSize(new Dimension(50, 50));
            int[] rgb = obtenerRGB(codigo);
            color.setBackground(new Color(rgb[0],rgb[1],rgb[2]));
            color.setOpaque(true);
            hori2.add(color);
        }
        cbButtons.add(hori1);
        cbLabels.add(hori2);
        jspC1.setViewportView(cbLabels);
        jspC2.setViewportView(cbButtons);
    }

    /*private void ponerColores() {
        JSONObject colorObjeto;
        coloresPersonalizar = new JPanel();
        for (int j = 0; j < 4; j++){
            if (j==0) colorFondo = new JPanel();
            else if (j==1) colorBola = new JPanel();
            else if (j==2) colorLadrillos = new JPanel();
            else colorPaddle = new JPanel();
            for (int i = 0; i < colores.length(); i++) {
                colorObjeto = (JSONObject) colores.get(i);
                String codigo = colorObjeto.getString("Codigo");

                JLabel color = new JLabel();
                color.setPreferredSize(new Dimension(50, 50));
                int[] rgb = obtenerRGB(codigo);
                color.setBackground(new Color(rgb[0],rgb[1],rgb[2]));
                color.setOpaque(true);

                if (j==0) {
                    colorFondo.add(color);
                }
                else if (j==1) {
                    colorBola.add(color);
                }
                else if (j==2) {
                    colorLadrillos.add(color);
                }
                else {
                    colorPaddle.add(color);
                }
            }
            if (j==0) coloresPersonalizar.add(colorFondo);
            else if (j==1) coloresPersonalizar.add(colorBola);
            else if (j==2) coloresPersonalizar.add(colorLadrillos);
            else coloresPersonalizar.add(colorPaddle);
        }

        //personalizarPestañas.add(coloresPersonalizar);
    }*/

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

    private void addBorde(MouseEvent e){
        JButton btn = (JButton)e.getSource();
        btn.setBorder(new LineBorder(Color.WHITE));
    }

    private void ponerSonidos() {

    }

    private void onCancel() {
        dispose();
    }

    private void guardarPersonalizacion(){
        //Arkanoid.getArkanoid().actualizarPersonalizacionDB();
        //Arkanoid.getArkanoid().actualizarPersonalizacionUsu();
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
