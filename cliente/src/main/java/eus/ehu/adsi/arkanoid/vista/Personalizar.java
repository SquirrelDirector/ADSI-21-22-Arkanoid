package eus.ehu.adsi.arkanoid.vista;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Personalizar extends JDialog {
    private JPanel contentPane;
    private JButton guardarButton;
    private JButton volverButton;
    private JLabel tituloPersonalizar;
    private JTabbedPane personalizarPestañas;
    private JPanel coloresPersonalizar;
    private JPanel sonidoPersonalizar;
    private JPanel dimensaionesPersonalizar;
    private JScrollPane jspColores;
    private JScrollPane jspSonido;
    private JScrollPane jspDimenciones;

    private JSONArray colores;
    private JSONArray sonidos;
    private JSONObject personalizablesJugador;

    public Personalizar() {

        this.setPreferredSize(new Dimension(500, 500));
        
        getPersonalizables();
        
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(guardarButton);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ponerColores(colores, coloresPersonalizar, jspColores);
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
        colorO.put("#FF0000", "rojo");
        JSONObject color1 = new JSONObject();
        color1.put("#FF9200", "naranja");
        JSONObject color2 = new JSONObject();
        color2.put("#FCFF00", "amarillo");
        JSONObject color3 = new JSONObject();
        color3.put("#5EFF00", "verde");
        colores = new JSONArray();
        colores.put(colorO);
        colores.put(color1);
        colores.put(color2);
        colores.put(color3);
    }

    private void ponerColores(JSONArray coloresA, JPanel panelColores, JScrollPane coloresJsp) {
        JSONObject colorObjeto;
        panelColores = new JPanel();
        for (int i = 0; i < coloresA.length(); i++) {
            //colorObjeto = (JSONObject) coloresA.get(i);
            //String codigo = colorObjeto.getString("Codigo");
            //String nombre = colorObjeto.getString("nombre");
            Button color = new Button();
            color.setBackground(Color.red);
            panelColores.add(color);
        }
        coloresJsp.setViewportView(panelColores);
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
