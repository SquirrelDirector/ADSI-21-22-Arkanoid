package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Window.Type;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IUIdentificarse extends JFrame {

	private static IUIdentificarse miIUIdentificarse;
	private JPanel contentPane;
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
	public static void main(String[] args){
		new IUIdentificarse().mostrarVentana();
	}
	
	public void mostrarVentana() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IUIdentificarse frame = new IUIdentificarse();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static IUIdentificarse getMiIUIdentificarse(){
		if (miIUIdentificarse==null)
			miIUIdentificarse=new IUIdentificarse();
		return miIUIdentificarse;
	}

	/**
	 * Create the frame.
	 */
	private IUIdentificarse() {
		initialize();
		ark=Arkanoid.getArkanoid();
	}
	private void initialize() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblLogin(), BorderLayout.NORTH);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
	}

	private JLabel getLblLogin() {
		if (lblLogin == null) {
			lblLogin = new JLabel("INICIA SESI\u00D3N");
			lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogin.setVerticalAlignment(SwingConstants.TOP);
		}
		return lblLogin;
	}
	private JTextField getEmail() {
		if (email == null) {
			email = new JTextField();
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
			panel.add(getEmail());
			panel.add(getPassword());
			panel.add(getBtnAcceder());
		}
		return panel;
	}
	private JButton getBtnAcceder() {
		if (btnAcceder == null) {
			btnAcceder = new JButton("ACCEDER");
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
			btnSignup = new JButton("REG\u00CDSTRATE");
			btnSignup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnSignup.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//IURegistrarse.getMiIURegistrarse().mostrarVentana();
					//setVisible(false);
					Date hoy=new Date();
					JSONObject json=new JSONObject();
					json.put("hoy", hoy);
					System.out.println(json.get("hoy"));
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
			panel_1.add(getBtnForgotPassword());
			panel_1.add(getLblSignUp());
			panel_1.add(getBtnSignup());
		}
		return panel_1;
	}
	private JButton getBtnForgotPassword() {
		if (btnForgotPassword == null) {
			btnForgotPassword = new JButton("RECUPERAR CONTRASE\u00D1A");
			btnForgotPassword.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					IURecuperar.getMiIURecuperar().mostrarVentana();
					setVisible(false);
				}
			});
		}
		return btnForgotPassword;
	}
	private JLabel getLblSignUp() {
		if (lblSignUp == null) {
			lblSignUp = new JLabel("\u00BFA\u00FAn no tienes cuenta?");
		}
		return lblSignUp;
	}
	private JPasswordField getPassword() {
		if (password == null) {
			password = new JPasswordField();
			password.setToolTipText("CONTRASE\u00D1A");
		}
		return password;
	}
}
