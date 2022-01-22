package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.Boton;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaNormal;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.InputContrasena;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.InputTexto;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.PanelNegro;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.RadioButton;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.ScrollPane;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.Component;

public class IU_ModificarDatos extends JFrame {

	private final JPanel datosPanel = new JPanel();
	private InputTexto nombreUsuario;
	private InputContrasena passwordField;
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
			IU_ModificarDatos frame = new IU_ModificarDatos();
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public IU_ModificarDatos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		getContentPane().setLayout(new BorderLayout());
		datosPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		datosPanel.setLayout(new GridLayout(0, 1, 0, 0));
				
		InterfazBase ib = new InterfazBase("MODIFICAR DATOS");
		getContentPane().add(ib, BorderLayout.CENTER);
		
		{
			ScrollPane scrollPane = new ScrollPane();
			datosPanel.add(scrollPane);
			{
				PanelNegro panel = new PanelNegro();
				scrollPane.setViewportView(panel);
				panel.setLayout(new GridLayout(3, 0, 0, 0));
				{
					PanelNegro avatarPanel = new PanelNegro();
					avatarPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(255, 255, 255), 2, true)));
					panel.add(avatarPanel);
					avatarPanel.setLayout(new BoxLayout(avatarPanel, BoxLayout.Y_AXIS));
					{
						EtiquetaNormal lblNewLabel = new EtiquetaNormal("CAMBIAR AVATAR");
						lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
						lblNewLabel.setBorder(new EmptyBorder(5, 0, 5, 0));
						lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
						avatarPanel.add(lblNewLabel);
					}
					{
						PanelNegro panel_1 = new PanelNegro();
						panel_1.setBorder(new EmptyBorder(0, 0, 5, 0));
						avatarPanel.add(panel_1);
						panel_1.setLayout(new GridLayout(2, 5, 0, 0));
						{
							PanelNegro panel_2 = new PanelNegro();
							panel_1.add(panel_2);
							panel_2.setLayout(new GridLayout(0, 1, 0, 0));
							{
								jspA = new ScrollPane();
								panel_2.add(jspA);
								{
									avatarLabels = new PanelNegro();
									avatarLabels.setBorder(new EmptyBorder(10, 35, 0, 15));
									jspA.setViewportView(avatarLabels);
									avatarLabels.setLayout(new GridLayout(1, 0, 0, 0));
								}
							}
						}
						{
							avataresButton = new PanelNegro();
							avataresButton.setBorder(new EmptyBorder(0, 0, 0, 15));
							panel_1.add(avataresButton);
							avataresButton.setLayout(new BoxLayout(avataresButton, BoxLayout.X_AXIS));
						}
					}
				}
				{
					PanelNegro nombrePanel = new PanelNegro();
					nombrePanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(255, 255, 255), 2, true)));
					panel.add(nombrePanel);
					nombrePanel.setLayout(new GridLayout(2, 2, 0, 0));
					{
						EtiquetaNormal lblNewLabel_1 = new EtiquetaNormal("CAMBIAR NOMBRE DE USUARIO");
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						nombrePanel.add(lblNewLabel_1);
					}
					{
						PanelNegro panel_1 = new PanelNegro();
						nombrePanel.add(panel_1);
						{
							nombreUsuario = new InputTexto("Nombre");
							panel_1.add(nombreUsuario);
						}
					}
				}
				{
					PanelNegro contrasenaPanel = new PanelNegro();
					contrasenaPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 5, 5),  new LineBorder(new Color(255, 255, 255), 2, true)));
					panel.add(contrasenaPanel);
					contrasenaPanel.setLayout(new GridLayout(2, 1, 0, 0));
					{
						EtiquetaNormal lblNewLabel_2 = new EtiquetaNormal("CAMBIAR PASSWORD");
						lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
						contrasenaPanel.add(lblNewLabel_2);
					}
					{
						PanelNegro panel_1 = new PanelNegro();
						contrasenaPanel.add(panel_1);
						{
							passwordField = new InputContrasena("CONTRASEÑA:");
							panel_1.add(passwordField);
						}
					}
				}
			}
		}
		ib.panelPrincipal.setLayout(new GridLayout(0, 1, 0, 0));
		
		ib.panelPrincipal.add(datosPanel);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			ib.add(buttonPane, BorderLayout.SOUTH);
			{
				Boton guardarButton = new Boton("GUARDAR");
				guardarButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            guardarPersonalizacion();
			        }
			    });
				buttonPane.add(guardarButton);
			}
		}
	    
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
			RadioButton jrb = new RadioButton(nombre);
			jrb = addUserPreference(jrb, path);
			jrb.setMargin(new Insets(0, 45, 0, 0));
			bgA.add(jrb);
			avataresButton.add(jrb);
		}
	}
	
	private RadioButton addUserPreference(RadioButton jrb, String pathPerfil) {
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
