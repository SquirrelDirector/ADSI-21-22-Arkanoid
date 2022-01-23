package eus.ehu.adsi.arkanoid.vista;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.BotonLogro;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaNormal;
import eus.ehu.adsi.arkanoid.vista.claseObjetos.ScrollPane;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Panel;

public class IU_Logros extends JFrame implements Observer {

	private InterfazBase contentPane;
	private JLabel lblLogout;
	private JLabel lblDescrp_content;
	private JLabel lblFecha_content;
	private JButton btn;
	private JSONArray logros = new JSONArray();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	String fecha = dateFormat.format(new Date());

	/**
	 * Launch the application.
	 */
	public static void mostrarVentana() {
		try {
			IU_Logros dialog = new IU_Logros();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public IU_Logros() {
		contentPane = new InterfazBase("Logros");
		contentPane.setIdentificado(Arkanoid.getArkanoid().isIdentificado());
		Arkanoid.getArkanoid().addObserver(this);
		setContentPane(contentPane);
		((InterfazBase) contentPane).setEventoRegreso(new IU_Inicial());
		setVisible(true);
		/*
		 * creamos los logros a mano para simular el array de json JSONObject logro1 =
		 * new JSONObject(); logro1.put("nombre", "Rey de los Principiantes");
		 * logro1.put("descripcion", "este es el logro del rey de los principiantes");
		 * logro1.put("fechaObtencion", fecha); logro1.put("progreso", 1);
		 * 
		 * JSONObject logro2 = new JSONObject(); logro2.put("nombre", "Ranking Master");
		 * logro2.put("descripcion", "este es el logro del ranking master");
		 * logro2.put("fechaObtencion", fecha); logro2.put("progreso", 1);
		 * 
		 * JSONObject logro3 = new JSONObject(); logro3.put("nombre", "Novato Total");
		 * logro3.put("descripcion", "este es el logro del novato total");
		 * logro3.put("fechaObtencion", fecha); logro3.put("progreso", 1);
		 * 
		 * JSONObject logro4 = new JSONObject(); logro4.put("nombre",
		 * "Vigilante del abismo"); logro4.put("descripcion",
		 * "este es el logro del vigilante del abismo"); logro4.put("fechaObtencion",
		 * fecha); logro4.put("progreso", 1);
		 * 
		 * JSONObject logro5 = new JSONObject(); logro5.put("nombre", "Sol de Mexico");
		 * logro5.put("descripcion", "este es el logro del sol de mexico");
		 * logro5.put("fechaObtencion", fecha); logro5.put("progreso", 0);
		 * 
		 * JSONObject logro6 = new JSONObject(); logro6.put("nombre", "Flor palida");
		 * logro6.put("descripcion", "este es el logro de la flor palida");
		 * logro6.put("fechaObtencion", fecha); logro6.put("progreso", 0);
		 * 
		 * logros = new JSONArray(); logros.put(logro1); logros.put(logro2);
		 * logros.put(logro3); logros.put(logro4); logros.put(logro5);
		 * logros.put(logro6);
		 */
		setBounds(100, 100, 750, 574);
		contentPane.panelPrincipal.setLayout(new CardLayout(0, 0));

		// Panel panel_logros = new Panel();
		// contentPane.panelPrincipal.add(panel_logros);
		JPanel UI = new JPanel();
		UI.setBackground(new Color(0, 0, 0, 0));
		UI.setLayout(new BorderLayout());
		JPanel panel_logros = new JPanel();
		panel_logros.setBackground(new Color(0, 0, 0, 0));
		panel_logros.setLayout(new GridLayout(0, 1, 45, 45));
		panel_logros.setBorder(new EmptyBorder(20, 20, 20, 20));
		UI.add(panel_logros, BorderLayout.CENTER);

		ScrollPane scrollPane = new ScrollPane();

		panel_logros.add(scrollPane);

		Panel panelDentroScrollPane = new Panel();

		scrollPane.setViewportView(panelDentroScrollPane);
		panelDentroScrollPane.setLayout(new GridLayout(0, 2, 45, 45));

		JPanel panel_descp_logro = new JPanel();
		panel_descp_logro.setBackground(new Color(0, 0, 0, 0));
		panel_descp_logro.setLayout(new GridLayout(2, 0, 0, 0));
		panel_descp_logro.setBorder(new EmptyBorder(20, 20, 20, 20));
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
		Arkanoid arka = Arkanoid.getArkanoid();
		JSONArray logros = arka.getLogros();

		for (int i = 0; i < logros.length(); i++) {
			JSONObject logro = logros.getJSONObject(i);
			String nombreLogro = logro.getString("nombre");
			btn = new BotonLogro(nombreLogro, logro);
			btn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					JSONObject evt = ((BotonLogro) e.getComponent()).getInfoLogroAsociado();
					lblDescrp_content.setText(evt.getString("descripcion"));
					if (evt.getInt("progreso") == 100) {
						lblFecha_content.setText("" + evt.get("fechaObtencion"));
					} else {
						lblFecha_content.setText("");
					}
					repaint();
					revalidate();

				}
			});
			// panel_logros.add(btn);
			panelDentroScrollPane.add(btn);

		}
		scrollPane.repaint();
		scrollPane.revalidate();

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Boolean)
			contentPane.setIdentificado((boolean) arg1);
	}

}
