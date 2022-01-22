package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class IU_Renovar extends JFrame {

	private static IU_Renovar miIU_Renovar;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnEnviar;
	private JPanel panel_2;
	private JButton btnReturn;
	private JLabel lblNewLabel;
	private JPasswordField txtPassword;
	private JPasswordField txtRepeat;
	
	private String email;

	/**
	 * Launch the application.
	 */
	public void mostrarVentana(final String mail) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Renovar frame = new IU_Renovar();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					
					email=mail;
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
		setBounds(100, 100, 330, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel_2(), BorderLayout.NORTH);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		contentPane.add(getBtnEnviar(), BorderLayout.SOUTH);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new EmptyBorder(40, 0, 158, 0));
			panel.setLayout(new GridLayout(0, 1, 0, 5));
			panel.add(getTxtPassword());
			panel.add(getTxtRepeat());
		}
		return panel;
	}
	private JButton getBtnEnviar() {
		if (btnEnviar == null) {
			btnEnviar = new Boton("ENVIAR");
			btnEnviar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int cod=Arkanoid.getArkanoid().cambiarContrasena(email, txtPassword.getText(), txtRepeat.getText());
					switch (cod) {
					case 0: //todo bien
						IU_Identificarse.getMiIU_Identificarse().mostrarVentana();
						((JFrame)SwingUtilities.getRoot(e.getComponent())).dispose();
						break;
					case 1: //las contraseñas no coinciden
						JOptionPane.showMessageDialog(null, "Las contraseñas introducidas no coinciden.");
						break;
					case 2: //contraseña con formato incorrecto
						JOptionPane.showMessageDialog(null, "La contraseña tiene un formato incorrecto.");
						break;
					}
				}
			});
		}
		return btnEnviar;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			GridBagLayout gbl_panel_2 = new GridBagLayout();
			gbl_panel_2.columnWidths = new int[]{41, 60, 0, 0, 0};
			gbl_panel_2.rowHeights = new int[]{23, 0};
			gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel_2.setLayout(gbl_panel_2);
			GridBagConstraints gbc_btnReturn = new GridBagConstraints();
			gbc_btnReturn.anchor = GridBagConstraints.WEST;
			gbc_btnReturn.insets = new Insets(0, 0, 0, 5);
			gbc_btnReturn.gridx = 0;
			gbc_btnReturn.gridy = 0;
			panel_2.add(getBtnReturn(), gbc_btnReturn);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridwidth = 2;
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 0;
			panel_2.add(getLblNewLabel(), gbc_lblNewLabel);
		}
		return panel_2;
	}
	private JButton getBtnReturn() {
		if (btnReturn == null) {
			btnReturn = new Boton("<");
			btnReturn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					IU_Identificarse.getMiIU_Identificarse().mostrarVentana();
					((JFrame)SwingUtilities.getRoot(e.getComponent())).dispose();
				}
			});
		}
		return btnReturn;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new EtiquetaTitulo("RECUPERAR");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
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
		}
		return txtRepeat;
	}
}
