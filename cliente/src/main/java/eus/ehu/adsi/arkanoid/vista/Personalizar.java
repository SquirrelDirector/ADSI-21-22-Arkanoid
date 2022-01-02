package eus.ehu.adsi.arkanoid.vista;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
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

    private JSONArray colores;
    private JSONArray sonidos;
    private JSONObject personalizablesJugador;

    public Personalizar() {
        
        getPersonalizables();
        
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(guardarButton);

        ponerColores();
        ponerSonidos();

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
    }

    private void getPersonalizables() {
        JSONObject personalizables = Arkanoid.getArkanoid().obtenerPersonalizables();
        colores = personalizables.getJSONArray("colores");
        sonidos = personalizables.getJSONArray("sonidos");
        personalizablesJugador = Arkanoid.getArkanoid().obtenerPersonalizacionUsuario();
    }

    private void ponerColores() {

    }

    private void ponerSonidos() {

    }

    private void onCancel() {
        dispose();
    }

    private void guardarPersonalizacion(){
        //Arkanoid.getArkanoid().actualizarPersonalizacionUsu();
        //Arkanoid.getArkanoid().actualizarPersonalizacionDB();
    }

    public static void main(String[] args) {
        Personalizar dialog = new Personalizar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
