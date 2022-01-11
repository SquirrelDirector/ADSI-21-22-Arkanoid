package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

import java.awt.Panel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Logros extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblLogout;
	private JLabel lblDescrp_content;
	private JLabel lblFecha_content;
	private JButton btn;
	private Actioner ac;
	private JSONArray logros = new JSONArray();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	String fecha = dateFormat.format(new Date());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Logros dialog = new Logros();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Logros() {
		/* creamos los logros a mano para simular el array de json */
		JSONObject logro1 = new JSONObject();
		logro1.put("nombre", "Rey de los Principiantes");
		logro1.put("descripcion", "este es el logro del rey de los principiantes");
		logro1.put("fechaObtencion", fecha);
		logro1.put("progreso", 1);

		JSONObject logro2 = new JSONObject();
		logro2.put("nombre", "Ranking Master");
		logro2.put("descripcion", "este es el logro del ranking master");
		logro2.put("fechaObtencion", fecha);
		logro2.put("progreso", 1);

		JSONObject logro3 = new JSONObject();
		logro3.put("nombre", "Novato Total");
		logro3.put("descripcion", "este es el logro del novato total");
		logro3.put("fechaObtencion", fecha);
		logro3.put("progreso", 1);

		JSONObject logro4 = new JSONObject();
		logro4.put("nombre", "Vigilante del abismo");
		logro4.put("descripcion", "este es el logro del vigilante del abismo");
		logro4.put("fechaObtencion", fecha);
		logro4.put("progreso", 1);

		JSONObject logro5 = new JSONObject();
		logro5.put("nombre", "Sol de Mexico");
		logro5.put("descripcion", "este es el logro del sol de mexico");
		logro5.put("fechaObtencion", fecha);
		logro5.put("progreso", 0);

		JSONObject logro6 = new JSONObject();
		logro6.put("nombre", "Flor palida");
		logro6.put("descripcion", "este es el logro de la flor palida");
		logro6.put("fechaObtencion", fecha);
		logro6.put("progreso", 0);

		logros = new JSONArray();
		logros.put(logro1);
		logros.put(logro2);
		logros.put(logro3);
		logros.put(logro4);
		logros.put(logro5);
		logros.put(logro6);

		setBounds(100, 100, 750, 574);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		Panel panel_titulo = new Panel();
		contentPanel.add(panel_titulo, BorderLayout.NORTH);
		panel_titulo.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblTitulo = new JLabel("LOGROS");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		panel_titulo.add(lblTitulo);

		Panel panel_usuario = new Panel();
		panel_titulo.add(panel_usuario);
		panel_usuario.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_usuario.add(lblNewLabel);

		lblLogout = new JLabel("Logout");
		lblLogout.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLogout.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_usuario.add(lblLogout, BorderLayout.SOUTH);

		JPanel panel_logros = new JPanel();
		contentPanel.add(panel_logros, BorderLayout.CENTER);
		panel_logros.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_descp_logro = new JPanel();
		contentPanel.add(panel_descp_logro, BorderLayout.SOUTH);
		panel_descp_logro.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel lblDescrp = new JLabel("Descripcion del logro:");
		lblDescrp.setHorizontalAlignment(SwingConstants.CENTER);
		panel_descp_logro.add(lblDescrp);

		lblDescrp_content = new JLabel("");
		panel_descp_logro.add(lblDescrp_content);

		JLabel lblFecha = new JLabel("Logro conseguido el:");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		panel_descp_logro.add(lblFecha);

		lblFecha_content = new JLabel("");
		panel_descp_logro.add(lblFecha_content);



		// Arkanoid arka = Arkanoid.getArkanoid();
		// JSONArray logros = arka.getLogros();

		

		for (int i = 0; i < logros.length(); i++) {
			JSONObject logro = logros.getJSONObject(i);
			System.out.println(logro);			
			String nombreLogro = logro.getString("nombre");
			/*
			JLabel lblNewLabel_1 = new JLabel(nombreLogro);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			panel_logros.add(lblNewLabel_1); 
			*/
			btn= new JButton(nombreLogro);
			ac = new Actioner(logro, lblDescrp_content, lblFecha_content);
			btn.addActionListener(ac);
			panel_logros.add(btn);

		}

	}

}
