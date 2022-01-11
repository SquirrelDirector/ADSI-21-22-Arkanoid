package eus.ehu.adsi.arkanoid.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import org.json.JSONObject;

public class Actioner implements ActionListener {
	JSONObject info;
	JLabel lbl1, lbl2;
	public Actioner(JSONObject infoLogros, JLabel pLbl1, JLabel pLbl2) {
		info = infoLogros;
		lbl1 = pLbl1;
		lbl2 = pLbl2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lbl1.setText("");
		lbl2.setText("");
		
		lbl1.setText(this.info.getString("nombre"));
		if(info.getInt("progreso")!= 0) {
			lbl2.setText(this.info.getString("fechaObtencion"));	
		}
		

	}
	public JSONObject getInfo() {
		return info;
	}
}

