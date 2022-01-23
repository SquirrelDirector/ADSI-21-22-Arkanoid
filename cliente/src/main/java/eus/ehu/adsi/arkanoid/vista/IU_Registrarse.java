package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class IU_Registrarse extends JFrame {

	private static IU_Registrarse miIU_Registrarse;
	private InterfazBase contentPane;
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 500);
		contentPane = new InterfazBase("REG\u00CDSTRATE");
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.ocultarBotonRegreso();
		contentPane.ocultarPanelIdentidad();
		contentPane.centrarCabecera();
		//contentPane.add(getLblSignUp(), BorderLayout.NORTH);
		contentPane.panelPrincipal.add(getPanel(), BorderLayout.CENTER);
		contentPane.panelPrincipal.add(getPanel_1(), BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evt) {
                Arkanoid.getArkanoid().cerrarSesion();
            }
        });
	}

	private JLabel getLblSignUp() {
		if (lblSignUp == null) {
			lblSignUp = new EtiquetaTitulo("REG\u00CDSTRATE");
			lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblSignUp;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 1, 0, 7));
			panel.setBackground(new Color(0,0,0,0));
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
			panel_1.setBackground(new Color(0,0,0,0));
			panel_1.add(getLbSignIn());
			panel_1.add(getBtnSignIn());
		}
		return panel_1;
	}
	private JLabel getLbSignIn() {
		if (lbSignIn == null) {
			lbSignIn = new EtiquetaNormal("\u00BFYa tienes cuenta?");
		}
		return lbSignIn;
	}
	private JButton getBtnSignIn() {
		if (btnSignIn == null) {
			btnSignIn = new Boton("INICIA SESI\u00D3N");
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
			txtUsername = new InputTexto("NOMBRE DE USUARIO");
			txtUsername.setColumns(10);
		}
		return txtUsername;
	}
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new InputTexto("CORREO ELECTR\u00D3NICO");
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}
	private JButton getBtnRegistrarse() {
		if (btnRegistrarse == null) {
			btnRegistrarse = new Boton("CREAR CUENTA");
			btnRegistrarse.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int cod=Arkanoid.getArkanoid().registrarse(txtUsername.getText(), txtEmail.getText(), txtPassword.getText(), txtRepeat.getText());
					switch (cod){
					case 0: //todo bien
						dispose();
						break;
					case 1: //usuario repetido
						JOptionPane.showMessageDialog(null, "El nombre de usuario introducido ya existe");
						break;
					case 2: //correo no válido
						JOptionPane.showMessageDialog(null, "Introduzca un correo válido");
						break;
					case 3: //contrasena no válida
						JOptionPane.showMessageDialog(null, "Introduzca una contraseña válida. Debe tener al menos 6 caracteres, una mayúscula, una minúscula, un número y un caracter especial.");
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
			txtPassword = new InputContrasena("CONTRASE\u00D1A");
		}
		return txtPassword;
	}
	private JPasswordField getTxtRepeat() {
		if (txtRepeat == null) {
			txtRepeat = new InputContrasena("REPETIR CONTRASE\u00D1A");
			txtRepeat.addActionListener(new AbstractAction()
			{
			    @Override
			    public void actionPerformed(ActionEvent e)
			    {
			    	int cod=Arkanoid.getArkanoid().registrarse(txtUsername.getText(), txtEmail.getText(), txtPassword.getText(), txtRepeat.getText());
					switch (cod){
					case 0: //todo bien
						dispose();
						break;
					case 1: //usuario repetido
						JOptionPane.showMessageDialog(null, "El nombre de usuario introducido ya existe");
						break;
					case 2: //correo no válido
						JOptionPane.showMessageDialog(null, "Introduzca un correo válido");
						break;
					case 3: //contrasena no válida
						JOptionPane.showMessageDialog(null, "Introduzca una contraseña válida. Debe tener al menos 6 caracteres, una mayúscula, una minúscula, un número y un caracter especial.");
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
		return txtRepeat;
	}
}
