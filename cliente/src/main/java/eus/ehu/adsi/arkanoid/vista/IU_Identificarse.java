package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.Boton;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaNormal;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaTitulo;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.InputContrasena;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.InputTexto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Window.Type;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IU_Identificarse extends JFrame {

	private static IU_Identificarse miIU_Identificarse;
	private InterfazBase contentPane;
	private JLabel lblLogin;
	private JTextField email;
	private JPanel panel;
	private JButton btnAcceder;
	private JButton btnSignup;
	private JPanel panel_1;
	private JButton btnForgotPassword;
	private JLabel lblSignUp;
	private JPasswordField password;
	private Arkanoid ark;

	/**
	 * Launch the application.
	 */
	
	public void mostrarVentana() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Identificarse frame = new IU_Identificarse();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static IU_Identificarse getMiIU_Identificarse(){
		if (miIU_Identificarse==null)
			miIU_Identificarse=new IU_Identificarse();
		return miIU_Identificarse;
	}

	/**
	 * Create the frame.
	 */
	private IU_Identificarse() {
		initialize();
		ark=Arkanoid.getArkanoid();
	}
	private void initialize() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 540);
		contentPane = new InterfazBase("LOG IN");
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.ocultarPanelIdentidad();
		contentPane.ocultarBotonRegreso();
		//contentPane.add(getLblLogin(), BorderLayout.NORTH);
		contentPane.panelPrincipal.add(getPanel(), BorderLayout.CENTER);
		contentPane.panelPrincipal.add(getPanel_1(), BorderLayout.SOUTH);
	}

	private JLabel getLblLogin() {
		if (lblLogin == null) {
			lblLogin = new EtiquetaTitulo("INICIA SESI\u00D3N");
			lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogin.setVerticalAlignment(SwingConstants.TOP);
		}
		return lblLogin;
	}
	private JTextField getEmail() {
		if (email == null) {
			email = new InputTexto("CORREO ELECTR\u00D3NICO");
			email.setToolTipText("CORREO ELECTR\u00D3NICO");
			email.setColumns(20);
		}
		return email;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new EmptyBorder(20, 0, 20, 0));
			panel.setLayout(new GridLayout(0, 1, 0, 5));
			panel.setBackground(new Color(0,0,0,0));
			panel.add(getEmail());
			panel.add(getPassword());
			panel.add(getBtnAcceder());
		}
		return panel;
	}
	private JButton getBtnAcceder() {
		if (btnAcceder == null) {
			btnAcceder = new Boton("ACCEDER");
			btnAcceder.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int cod=ark.iniciarSesion(email.getText(), password.getText());
					switch (cod){
					case 0: //todo bien
						((JFrame)SwingUtilities.getRoot(e.getComponent())).dispose();
						break;
					case 1: //correo no válido
						JOptionPane.showMessageDialog(null, "Introduzca un correo válido");
						break;
					case 2: //contraseña no válida
						JOptionPane.showMessageDialog(null, "Introduzca una contraseña válida");
						break;
					case 3: //credenciales no coincidentes
						JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
						break;
					}
				}
			});
		}
		return btnAcceder;
	}
	private JButton getBtnSignup() {
		if (btnSignup == null) {
			btnSignup = new Boton("REG\u00CDSTRATE");
			btnSignup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnSignup.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					IU_Registrarse.getMiIU_Registrarse().mostrarVentana();
					setVisible(false);
				}
			});
		}
		return btnSignup;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new EmptyBorder(10, 0, 0, 0));
			panel_1.setLayout(new GridLayout(0, 1, 0, 5));
			panel_1.setBackground(new Color(0,0,0,0));
			panel_1.add(getBtnForgotPassword());
			panel_1.add(getLblSignUp());
			panel_1.add(getBtnSignup());
		}
		return panel_1;
	}
	private JButton getBtnForgotPassword() {
		if (btnForgotPassword == null) {
			btnForgotPassword = new Boton("RECUPERAR CONTRASE\u00D1A");
			btnForgotPassword.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					IU_Recuperar.getMiIU_Recuperar().mostrarVentana();
					setVisible(false);
				}
			});
		}
		return btnForgotPassword;
	}
	private JLabel getLblSignUp() {
		if (lblSignUp == null) {
			lblSignUp = new EtiquetaNormal("\u00BFA\u00FAn no tienes cuenta?");
		}
		return lblSignUp;
	}
	private JPasswordField getPassword() {
		if (password == null) {
			password = new InputContrasena("CONTRASE\u00D1A");
			password.setToolTipText("CONTRASE\u00D1A");
		}
		return password;
	}
}
