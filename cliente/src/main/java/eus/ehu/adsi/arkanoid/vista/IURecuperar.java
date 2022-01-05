package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;

public class IURecuperar extends JFrame {

	private static IURecuperar window;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnValidar;
	private JPanel panel_1;
	private JButton btnReenviar;
	private JLabel lblReenviar;
	private JTextField txtEmail;
	private JTextArea txtrSeHaEnviado;
	private JTextField txtKey;
	private JPanel panel_2;
	private JButton btnReturn;

	/**
	 * Launch the application.
	 */
	public void mostrarVentana() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IURecuperar frame = new IURecuperar();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static IURecuperar getMiIURecuperar(){
		if (window==null)
			window=new IURecuperar();
		return window;
	}
	
	/**
	 * Create the frame.
	 */
	private IURecuperar() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 240, 277);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel_2(), BorderLayout.NORTH);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new EmptyBorder(20, 0, 20, 0));
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.add(getTxtEmail());
			panel.add(getTxtrSeHaEnviado());
			panel.add(getTxtKey());
		}
		return panel;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("RECUPERAR");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
	private JButton getBtnValidar() {
		if (btnValidar == null) {
			btnValidar = new JButton("VALIDAR");
			btnValidar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					IURenovar.getMiIURenovar().mostrarVentana(txtEmail.getText());
					((JFrame)SwingUtilities.getRoot(e.getComponent())).dispose();
				}
			});
		}
		return btnValidar;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(0, 1, 0, 5));
			panel_1.add(getLblReenviar());
			panel_1.add(getBtnReenviar());
			panel_1.add(getBtnValidar());
		}
		return panel_1;
	}
	private JButton getBtnReenviar() {
		if (btnReenviar == null) {
			btnReenviar = new JButton("REENVIAR");
			btnReenviar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int cod=Arkanoid.getArkanoid().recuperarContrasena(txtEmail.getText());
					switch (cod){
					case 0: //todo bien
						
						break;
					case 1: //correo no válido
						
						break;
					case 2: //correo inexistente
						
						break;
					}
				}
			});
		}
		return btnReenviar;
	}
	private JLabel getLblReenviar() {
		if (lblReenviar == null) {
			lblReenviar = new JLabel("\u00BFA\u00FAn no ha llegado?");
		}
		return lblReenviar;
	}
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setToolTipText("CORREO ELECTR\u00D3NICO");
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}
	private JTextArea getTxtrSeHaEnviado() {
		if (txtrSeHaEnviado == null) {
			txtrSeHaEnviado = new JTextArea();
			txtrSeHaEnviado.setEditable(false);
			txtrSeHaEnviado.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtrSeHaEnviado.setText("Se ha enviado un correo con\r\nun c\u00F3digo de validaci\u00F3n.\r\nIntrod\u00FAzcalo a continuaci\u00F3n");
		}
		return txtrSeHaEnviado;
	}
	private JTextField getTxtKey() {
		if (txtKey == null) {
			txtKey = new JTextField();
			txtKey.setToolTipText("C\u00D3DIGO");
			txtKey.setColumns(10);
		}
		return txtKey;
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
			gbc_lblNewLabel.gridwidth = 3;
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 0;
			panel_2.add(getLblNewLabel(), gbc_lblNewLabel);
		}
		return panel_2;
	}
	private JButton getBtnReturn() {
		if (btnReturn == null) {
			btnReturn = new JButton("<");
			btnReturn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					IUIdentificarse.getMiIUIdentificarse().mostrarVentana();
					((JFrame)SwingUtilities.getRoot(e.getComponent())).dispose();
				}
			});
		}
		return btnReturn;
	}
}
