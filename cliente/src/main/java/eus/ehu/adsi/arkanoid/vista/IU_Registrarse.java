package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class IU_Registrarse extends JFrame {

	private static IU_Registrarse miIU_Registrarse;
	private JPanel contentPane;
	private JLabel lblSignUp;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lbSignIn;
	private JButton btnSignIn;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JButton btnRegistrarse;
	private JPasswordField txtPassword;
	private JPasswordField txtRepeat;

	/**
	 * Launch the application.
	 */
	public void mostrarVentana() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Registrarse frame = new IU_Registrarse();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static IU_Registrarse getMiIU_Registrarse(){
		if (miIU_Registrarse==null)
			miIU_Registrarse=new IU_Registrarse();
		return miIU_Registrarse;
	}
	
	/**
	 * Create the frame.
	 */
	private IU_Registrarse() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblSignUp(), BorderLayout.NORTH);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
	}

	private JLabel getLblSignUp() {
		if (lblSignUp == null) {
			lblSignUp = new JLabel("REG\u00CDSTRATE");
			lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblSignUp;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 1, 0, 7));
			panel.add(getTxtUsername());
			panel.add(getTxtEmail());
			panel.add(getTxtPassword());
			panel.add(getTxtRepeat());
			panel.add(getBtnRegistrarse());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(0, 1, 0, 0));
			panel_1.add(getLbSignIn());
			panel_1.add(getBtnSignIn());
		}
		return panel_1;
	}
	private JLabel getLbSignIn() {
		if (lbSignIn == null) {
			lbSignIn = new JLabel("\u00BFYa tienes cuenta?");
		}
		return lbSignIn;
	}
	private JButton getBtnSignIn() {
		if (btnSignIn == null) {
			btnSignIn = new JButton("INICIA SESI\u00D3N");
			btnSignIn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					IU_Identificarse.getMiIU_Identificarse().mostrarVentana();
					((JFrame)SwingUtilities.getRoot(e.getComponent())).dispose();
				}
			});
		}
		return btnSignIn;
	}
	private JTextField getTxtUsername() {
		if (txtUsername == null) {
			txtUsername = new JTextField();
			txtUsername.setToolTipText("NOMBRE DE USUARIO");
			txtUsername.setColumns(10);
		}
		return txtUsername;
	}
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setToolTipText("CORREO ELECTR\u00D3NICO");
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}
	private JButton getBtnRegistrarse() {
		if (btnRegistrarse == null) {
			btnRegistrarse = new JButton("CREAR CUENTA");
			btnRegistrarse.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int cod=Arkanoid.getArkanoid().registrarse(txtUsername.getText(), txtEmail.getText(), txtPassword.getText(), txtRepeat.getText());
					switch (cod){
					case 0: //todo bien
						
						break;
					case 1: //usuario repetido
						JOptionPane.showMessageDialog(null, "El nombre de usuario introducido ya existe");
						break;
					case 2: //correo no válido
						JOptionPane.showMessageDialog(null, "Introduzca un correo válido");
						break;
					case 3: //contrasena no válida
						JOptionPane.showMessageDialog(null, "Introduzca una contraseña válida");
						break;
					case 4: //cuenta ya existente
						JOptionPane.showMessageDialog(null, "La dirección de correo introducida ya existe");
						break;
					case 5: //contrasenas no coincidentes
						JOptionPane.showMessageDialog(null, "Las contraseñas introducidas no coinciden");
						break;
					}
				}
			});
		}
		return btnRegistrarse;
	}
	private JPasswordField getTxtPassword() {
		if (txtPassword == null) {
			txtPassword = new JPasswordField();
			txtPassword.setToolTipText("CONTRASE\u00D1A");
		}
		return txtPassword;
	}
	private JPasswordField getTxtRepeat() {
		if (txtRepeat == null) {
			txtRepeat = new JPasswordField();
			txtRepeat.setToolTipText("REPETIR CONTRASE\u00D1A");
		}
		return txtRepeat;
	}
}
