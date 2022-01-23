package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.Boton;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaTitulo;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.InputContrasena;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPasswordField;

public class IU_Renovar extends JFrame {

	private static IU_Renovar miIU_Renovar;
	private InterfazBase contentPane;
	private JPanel panelRenovar;
	private JButton btnEnviar;
	private JPanel panel_2;
	private JButton btnReturn;
	private JLabel lblNewLabel;
	private JPasswordField txtPassword;
	private JPasswordField txtRepeat;
	
	private static String email;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public void mostrarVentana(String mail) {
		email=mail;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Renovar frame = new IU_Renovar();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static IU_Renovar getMiIU_Renovar(){
		if (miIU_Renovar==null)
			miIU_Renovar=new IU_Renovar();
		return miIU_Renovar;
	}
	
	/**
	 * Create the frame.
	 */
	private IU_Renovar() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 500);
		contentPane = new InterfazBase("RECUPERAR");
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.ocultarPanelIdentidad();
		contentPane.panelPrincipal.add(getPanelRenovar(), BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evt) {
                Arkanoid.getArkanoid().cerrarSesion();
            }
        });
	}

	private JPanel getPanelRenovar() {
		if (panelRenovar == null) {
			panelRenovar = new JPanel();
			panelRenovar.setBorder(new EmptyBorder(40, 0, 158, 0));
			panelRenovar.setBackground(new Color(0,0,0,0));
			panelRenovar.setLayout(new BorderLayout(0, 0));
			panelRenovar.add(getPanel_1(), BorderLayout.CENTER);
			panelRenovar.add(getBtnEnviar(), BorderLayout.SOUTH);
		}
		return panelRenovar;
	}
	private JButton getBtnEnviar() {
		if (btnEnviar == null) {
			btnEnviar = new Boton("ENVIAR");
			btnEnviar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int cod=Arkanoid.getArkanoid().cambiarContrasena(email, txtPassword.getText(), txtRepeat.getText()); //FIXME - email se inicializa pero el valor se pierde al llegar aquí
					switch (cod) {
					case 0: //todo bien
						IU_Identificarse.getMiIU_Identificarse().mostrarVentana();
						((JFrame)SwingUtilities.getRoot(e.getComponent())).dispose();
						break;
					case 1: //las contraseñas no coinciden
						JOptionPane.showMessageDialog(null, "Las contraseñas introducidas no coinciden.");
						break;
					case 2: //contraseña con formato incorrecto
						JOptionPane.showMessageDialog(null, "Introduzca una contraseña válida. Debe tener al menos 6 caracteres, una mayúscula, una minúscula, un número y un caracter especial.");
						break;
					}
				}
			});
		}
		return btnEnviar;
	}
	
	private JPasswordField getTxtPassword() {
		if (txtPassword == null) {
			txtPassword = new InputContrasena("CONTRASE\u00D1A");
			txtPassword.setToolTipText("CONTRASE\u00D1A");
		}
		return txtPassword;
	}
	private JPasswordField getTxtRepeat() {
		if (txtRepeat == null) {
			txtRepeat = new InputContrasena("REPETIR CONTRASE\u00D1A");
			txtRepeat.setToolTipText("REPETIR CONTRASE\u00D1A");
			txtRepeat.addActionListener(new AbstractAction()
			{
			    @Override
			    public void actionPerformed(ActionEvent e)
			    {
			    	int cod=Arkanoid.getArkanoid().cambiarContrasena(email, txtPassword.getText(), txtRepeat.getText()); //FIXME - email se inicializa pero el valor se pierde al llegar aquí
					switch (cod) {
					case 0: //todo bien
						IU_Identificarse.getMiIU_Identificarse().mostrarVentana();
						dispose();
						break;
					case 1: //las contraseñas no coinciden
						JOptionPane.showMessageDialog(null, "Las contraseñas introducidas no coinciden.");
						break;
					case 2: //contraseña con formato incorrecto
						JOptionPane.showMessageDialog(null, "Introduzca una contraseña válida. Debe tener al menos 6 caracteres, una mayúscula, una minúscula, un número y un caracter especial.");
						break;
					}
			    }
			});
		}
		return txtRepeat;
	}
	private JPanel getPanel_1() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new EmptyBorder(0, 0, 40, 0));
			panel.setLayout(new GridLayout(0, 1, 0, 20));
			panel.setBackground(new Color(0,0,0,0));
			panel.add(getTxtPassword());
			panel.add(getTxtRepeat());
		}
		return panel;
	}
}
