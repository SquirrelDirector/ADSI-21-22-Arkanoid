package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.Boton;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaNormal;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaTitulo;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.InputTexto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IU_Recuperar extends JFrame {

	private static IU_Recuperar window;
	private InterfazBase contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnValidar;
	private JPanel panel_1;
	private JButton btnReenviar;
	private JLabel lblReenviar;
	private JTextField txtEmail;
	private JLabel txtrSeHaEnviado;
	private JTextField txtKey;
	private JPanel panel_2;
	private JButton btnReturn;
	private boolean primera=true;
	private JPanel panelForm;

	/**
	 * Launch the application.
	 */
	public void mostrarVentana() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Recuperar frame = new IU_Recuperar();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					primera=true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static IU_Recuperar getMiIU_Recuperar(){
		if (window==null)
			window=new IU_Recuperar();
		return window;
	}
	
	/**
	 * Create the frame.
	 */
	private IU_Recuperar() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 500);
		contentPane = new InterfazBase("RECUPERAR");
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.ocultarPanelIdentidad();
		contentPane.setEventoRegreso(IU_Identificarse.getMiIU_Identificarse());
		contentPane.panelPrincipal.setLayout(new BorderLayout(0, 0));
		contentPane.panelPrincipal.add(getPanelForm(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new EmptyBorder(20, 0, 20, 0));
			panel.setBackground(new Color(0,0,0,0));
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getTxtEmail(), BorderLayout.NORTH);
			panel.add(getTxtrSeHaEnviado());
			panel.add(getTxtKey(), BorderLayout.SOUTH);
		}
		return panel;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new EtiquetaTitulo("RECUPERAR");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
	private JButton getBtnValidar() {
		if (btnValidar == null) {
			btnValidar = new Boton("VALIDAR");
			//btnValidar.setVisible(false);
			btnValidar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int cod=Arkanoid.getArkanoid().comprobarCodigo(getTxtKey().getText());
					switch (cod) {
					case 0: //todo bien
						IU_Renovar.getMiIU_Renovar().mostrarVentana(txtEmail.getText());
						((JFrame)SwingUtilities.getRoot(e.getComponent())).dispose();
						break;
					case 1: //formato de clave incorrecto
						JOptionPane.showMessageDialog(null, "El código de validación tiene un formato incorrecto.");
						break;
					case 2: //codigo incorrecto
						JOptionPane.showMessageDialog(null, "El código de validación es incorrecto.");
						break;
					}
					
				}
			});
		}
		return btnValidar;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(0, 1, 0, 5));
			panel_1.setBackground(new Color(0,0,0,0));
			panel_1.add(getLblSend());
			panel_1.add(getBtnSend());
			panel_1.add(getBtnValidar());
		}
		return panel_1;
	}
	private JButton getBtnSend() {
		if (btnReenviar == null) {
			btnReenviar = new Boton("ENVIAR");
			btnReenviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnReenviar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int cod=Arkanoid.getArkanoid().recuperarContrasena(txtEmail.getText());
					switch (cod){
					case 0: //todo bien
						/*if(IU_Recuperar.getMiIU_Recuperar().isFirstTry())
							IU_Recuperar.getMiIU_Recuperar().aceptarClave();*/
						break;
					case 1: //correo no válido
						JOptionPane.showMessageDialog(null, "Introduzca un correo válido.");
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "La dirección de correo no corresponde a ningún usuario.");
						break;
					case 3: //correo inexistente
						JOptionPane.showMessageDialog(null, "La dirección de correo introducida no existe.");
						break;
					}
				}
			});
		}
		return btnReenviar;
	}
	protected boolean isFirstTry() {
		return primera;
	}

	protected void aceptarClave() { //TODO - Revisar por que no se refleja en la IU
		getBtnSend().setText("REENVIAR");
		getLblSend().setVisible(true);
		getBtnValidar().setVisible(true);
		getTxtrSeHaEnviado().setVisible(true);
		getTxtKey().setVisible(true);
		primera=false;
		repaint();
		revalidate();
	}

	private JLabel getLblSend() {
		if (lblReenviar == null) {
			lblReenviar = new EtiquetaNormal("\u00BFA\u00FAn no ha llegado?");
			//lblReenviar.setVisible(false);
		}
		return lblReenviar;
	}
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new InputTexto("CORREO ELECTR\u00D3NICO");
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}
	private JLabel getTxtrSeHaEnviado() {
		if (txtrSeHaEnviado == null) {
			txtrSeHaEnviado = new EtiquetaNormal("<HTML>Se ha enviado un correo con<br>un c\u00F3digo de validaci\u00F3n.<br>Introd\u00FAzcalo a continuaci\u00F3n</HTML>");
			//txtrSeHaEnviado.setVisible(false);
		}
		return txtrSeHaEnviado;
	}
	private JTextField getTxtKey() {
		if (txtKey == null) {
			txtKey = new InputTexto("C\u00D3DIGO");
			txtKey.setColumns(10);
			//txtKey.setVisible(false);
		}
		return txtKey;
	}
	private JPanel getPanelForm() {
		if (panelForm == null) {
			panelForm = new JPanel();
			panelForm.setBorder(new EmptyBorder(20, 10, 20, 10));
			panelForm.setLayout(new BorderLayout(0, 0));
			panelForm.add(getPanel(), BorderLayout.CENTER);
			panelForm.add(getPanel_1(), BorderLayout.SOUTH);
			panelForm.setBackground(new Color(0,0,0,0));
		}
		return panelForm;
	}
}
