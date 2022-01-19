package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.Component;

public class ModificarDatos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton guardarButton, volverButton;
	private JTextField nombreUsuario;
	private JPasswordField passwordField;
	private JPanel avatarLabels, avataresButton;
	private JScrollPane jspA;
	
	private JSONArray avatares;
	private ButtonGroup bgA;
	private String pathPerfilUsu;
	private String nombre;
	private String email;

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
		setBounds(100, 100, 550, 400);
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
					avatarPanel.setLayout(new BoxLayout(avatarPanel, BoxLayout.Y_AXIS));
					{
						JLabel lblNewLabel = new JLabel("CAMBIAR AVATAR");
						lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
						lblNewLabel.setBorder(new EmptyBorder(5, 0, 5, 0));
						lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
						avatarPanel.add(lblNewLabel);
					}
					{
						JPanel panel_1 = new JPanel();
						panel_1.setBorder(new EmptyBorder(0, 0, 5, 0));
						avatarPanel.add(panel_1);
						panel_1.setLayout(new GridLayout(2, 5, 0, 0));
						{
							JPanel panel_2 = new JPanel();
							panel_1.add(panel_2);
							panel_2.setLayout(new GridLayout(0, 1, 0, 0));
							{
								jspA = new JScrollPane();
								jspA.setBorder(null);
								panel_2.add(jspA);
								{
									avatarLabels = new JPanel();
									avatarLabels.setBorder(new EmptyBorder(10, 35, 0, 15));
									jspA.setViewportView(avatarLabels);
									avatarLabels.setLayout(new GridLayout(1, 0, 0, 0));
								}
							}
						}
						{
							avataresButton = new JPanel();
							avataresButton.setBorder(new EmptyBorder(0, 0, 0, 10));
							panel_1.add(avataresButton);
							avataresButton.setLayout(new BoxLayout(avataresButton, BoxLayout.X_AXIS));
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
	    
	    getAvatares();
	    setInfoUsuario();
	    ponerAvatares();
	}
    
	private void getAvatares() {
		JSONObject avataresJ = Arkanoid.getArkanoid().obtenerAvatares();
		avatares = avataresJ.getJSONArray("avatares");
	}
	
	private void ponerAvatares() {
		JSONObject avatarObjeto;
		bgA = new ButtonGroup();
		for (int i = 0; i < avatares.length(); i++) {
			avatarObjeto = (JSONObject) avatares.get(i);
            String nombre = avatarObjeto.getString("Nombre");
            String path = avatarObjeto.getString("Path");
			//Labels - Imagen
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(getClass().getResource("/imagenesAvatar/Avatar"+(i+1)+".png")));
            label.setPreferredSize(new Dimension(25, 48));
            
			avatarLabels.add(label);
			if (i != avatares.length()-1) {
				JLabel espacio = new JLabel();
				avatarLabels.add(espacio);
			}
			
			//RadioButtons - Nombre
			JRadioButton jrb = new JRadioButton(nombre);
			jrb = addUserPreference(jrb, path);
			jrb.setMargin(new Insets(0, 26, 0, 0));
			bgA.add(jrb);
			avataresButton.add(jrb);
		}
	}
	
	private JRadioButton addUserPreference(JRadioButton jrb, String pathPerfil) {
        if (pathPerfil.equals(pathPerfilUsu)) jrb.setSelected(true);
        return jrb;
    }
	
	private void setInfoUsuario() {
		JSONObject datos = Arkanoid.getArkanoid().obtenerDatosUsuario();
		nombre = datos.getString("NombreUsuario");
		nombreUsuario.setText(nombre);
		pathPerfilUsu = datos.getString("PathPerfil");
		email = datos.getString("Email");
	}
	
    private void guardarPersonalizacion() {
    	String pathAvatar = "";
    	
    	for (Enumeration<AbstractButton> buttons = bgA.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                String nombre = button.getText();
                nombre = nombre.replace(" ", "");
                pathAvatar = "/imagenesAvatar/"+nombre+".png";
            }
        }
    	
    	String nombreUsu = nombreUsuario.getText();
    	
    	if (!nombreUsu.equals(" ")) {
    		boolean existeNombre = false;
    		if (!nombreUsu.equals(nombre)) {
    			existeNombre = Arkanoid.getArkanoid().comprobarNombre(nombreUsu);
    		}
    		
        	if (!existeNombre) {
        		Arkanoid.getArkanoid().actualizarDatosUsu(pathAvatar, nombreUsu);
        		Arkanoid.getArkanoid().actualizarDatosUsuDB(pathAvatar, nombreUsu);
        	}
        	else {
        		nombreUsuario.setText(nombre); 
        		JOptionPane.showMessageDialog (null, "Ese nombre existe, pruebe con otro!", "Nombre existente", JOptionPane.ERROR_MESSAGE);
        	}
    	} else JOptionPane.showMessageDialog (null, "El nombre no puede estar vacio!", "Nombre vacío", JOptionPane.ERROR_MESSAGE);

    	
    	char[] pass = passwordField.getPassword();
    	
    	if (pass.length!=0) {
    		String regPass[] = new String[] {  "^.{6,}$", "^.*\\p{javaLowerCase}.*$", "^.*\\p{javaUpperCase}.*$", "^.*\\d.*$", "^.*\\p{Punct}.*$"}; 
        	boolean buena = true;
        	for (String regex : regPass) { 
    			if (!Pattern.matches(regex, String.valueOf(pass))) //minimo de 6 caracteres con mayusculas, minusculas, numeros y simbolos. 
    				buena = false;
    		} 		
        				 
        	if (buena) Arkanoid.getArkanoid().cambiarContrasena(email, String.valueOf(pass),String.valueOf(pass));
        	else JOptionPane.showMessageDialog (null, "La contraseña tiene que contener mínimo 6 caracteres con mayúsculas, minúsculas, números y símbolos. ", "Mala contraseña", JOptionPane.ERROR_MESSAGE);
    	}
    }
}
