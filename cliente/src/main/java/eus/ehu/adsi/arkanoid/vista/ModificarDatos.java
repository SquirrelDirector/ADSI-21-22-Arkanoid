package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.DropMode;

public class ModificarDatos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton guardarButton, volverButton;
	private JTextField nombreUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarDatos dialog = new ModificarDatos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificarDatos() {
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		setLocationRelativeTo(null);
		setResizable(false);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			contentPanel.add(scrollPane);
			{
				JPanel panel = new JPanel();
				scrollPane.setViewportView(panel);
				panel.setLayout(new GridLayout(3, 0, 0, 0));
				{
					JPanel avatarPanel = new JPanel();
					avatarPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(0, 0, 0), 2, true)));
					panel.add(avatarPanel);
					avatarPanel.setLayout(new GridLayout(2, 2, 0, 0));
					{
						JLabel lblNewLabel = new JLabel("CAMBIAR AVATAR");
						lblNewLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
						lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
						avatarPanel.add(lblNewLabel);
					}
					{
						JPanel panel_1 = new JPanel();
						panel_1.setBorder(new EmptyBorder(0, 0, 5, 0));
						avatarPanel.add(panel_1);
						panel_1.setLayout(new GridLayout(0, 5, 0, 0));
						{
							JLabel lblNewLabel_3 = new JLabel("");
							lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_3.setIcon(new ImageIcon("D:\\Uni\\ADSI-21-22-Arkanoid\\cliente\\src\\main\\resources\\imagenesAvatar\\usuario1.png"));
							panel_1.add(lblNewLabel_3);
						}
						{
							JLabel lblNewLabel_4 = new JLabel("");
							lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_4.setIcon(new ImageIcon("D:\\Uni\\ADSI-21-22-Arkanoid\\cliente\\src\\main\\resources\\imagenesAvatar\\usuario2.png"));
							panel_1.add(lblNewLabel_4);
						}
						{
							JLabel lblNewLabel_5 = new JLabel("");
							lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_5.setIcon(new ImageIcon("D:\\Uni\\ADSI-21-22-Arkanoid\\cliente\\src\\main\\resources\\imagenesAvatar\\usuario3.png"));
							panel_1.add(lblNewLabel_5);
						}
						{
							JLabel lblNewLabel_6 = new JLabel("");
							lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_6.setIcon(new ImageIcon("D:\\Uni\\ADSI-21-22-Arkanoid\\cliente\\src\\main\\resources\\imagenesAvatar\\usuario4.png"));
							panel_1.add(lblNewLabel_6);
						}
						{
							JLabel lblNewLabel_7 = new JLabel("");
							lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_7.setIcon(new ImageIcon("D:\\Uni\\ADSI-21-22-Arkanoid\\cliente\\src\\main\\resources\\imagenesAvatar\\usuario5.png"));
							panel_1.add(lblNewLabel_7);
						}
					}
				}
				{
					JPanel nombrePanel = new JPanel();
					nombrePanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(0, 0, 0), 2, true)));
					panel.add(nombrePanel);
					nombrePanel.setLayout(new GridLayout(2, 2, 0, 0));
					{
						JLabel lblNewLabel_1 = new JLabel("CAMBIAR NOMBRE DE USUARIO");
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						nombrePanel.add(lblNewLabel_1);
					}
					{
						JPanel panel_1 = new JPanel();
						nombrePanel.add(panel_1);
						{
							nombreUsuario = new JTextField();
							nombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
							nombreUsuario.setColumns(20);
							nombreUsuario.setPreferredSize(new Dimension(0, 25));
							panel_1.add(nombreUsuario);
						}
					}
				}
				{
					JPanel contrasenaPanel = new JPanel();
					contrasenaPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(0, 0, 0), 2, true)));
					panel.add(contrasenaPanel);
					contrasenaPanel.setLayout(new GridLayout(2, 1, 0, 0));
					{
						JLabel lblNewLabel_2 = new JLabel("CAMBIAR PASSWORD");
						lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
						contrasenaPanel.add(lblNewLabel_2);
					}
					{
						JPanel panel_1 = new JPanel();
						contrasenaPanel.add(panel_1);
						{
							passwordField = new JPasswordField();
							passwordField.setHorizontalAlignment(SwingConstants.CENTER);
							passwordField.setColumns(20);
							passwordField.setPreferredSize(new Dimension(0, 25));
							panel_1.add(passwordField);
						}
					}
				}
			}
		}
		{
			JPanel topPane = new JPanel();
			topPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(topPane, BorderLayout.NORTH);
			{
				volverButton = new JButton("");
				volverButton.setIcon(new ImageIcon("D:\\Uni\\ADSI-21-22-Arkanoid\\cliente\\src\\main\\resources\\general\\back.png"));
				volverButton.setBorder(null);
				volverButton.setContentAreaFilled(false);
				topPane.add(volverButton);
			}
			{
				JLabel tituloPersonalizar = new JLabel("MODIFICAR DATOS");
				tituloPersonalizar.setFont(new Font("Tahoma", Font.PLAIN, 20));
				topPane.add(tituloPersonalizar);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				guardarButton = new JButton("GUARDAR");
				buttonPane.add(guardarButton);
			}
		}
		
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
	}
    
    private void guardarPersonalizacion() {
    	
    }
}
