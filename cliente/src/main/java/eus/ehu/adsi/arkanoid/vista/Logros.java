package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.CardLayout;

public class Logros extends JDialog {

	private InterfazBase contentPane;
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
		contentPane = new InterfazBase("Logros");
		setContentPane(contentPane);
		setVisible(true);
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
		contentPane.panelPrincipal.setLayout(new CardLayout(0, 0));
		
		
		
		//Panel panel_logros = new Panel();
		//contentPane.panelPrincipal.add(panel_logros);
		JPanel UI = new JPanel();
		UI.setLayout(new BorderLayout());
		JPanel panel_logros = new JPanel();
		panel_logros.setLayout(new GridLayout(0, 2, 0, 0));
		UI.add(panel_logros, BorderLayout.CENTER);

		JPanel panel_descp_logro = new JPanel();
		panel_descp_logro.setLayout(new GridLayout(2, 0, 0, 0));
		
		EtiquetaNormal lblDescrp = new EtiquetaNormal("Descripcion del logro:");
		lblDescrp.setHorizontalAlignment(SwingConstants.CENTER);
		panel_descp_logro.add(lblDescrp);

		lblDescrp_content = new EtiquetaNormal("");
		panel_descp_logro.add(lblDescrp_content);

		EtiquetaNormal lblFecha = new EtiquetaNormal("Logro conseguido el:");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		panel_descp_logro.add(lblFecha);

		lblFecha_content = new EtiquetaNormal("");
		panel_descp_logro.add(lblFecha_content);

		UI.add(panel_descp_logro, BorderLayout.SOUTH);
		
		contentPane.panelPrincipal.add(UI, "name_169472020845800");
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
			btn= new Boton(nombreLogro);
			ac = new Actioner(logro, lblDescrp_content, lblFecha_content);
			btn.addActionListener(ac);
			panel_logros.add(btn);

		}

	}

}
