package eus.ehu.adsi.arkanoid.vista.claseObjetos;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JRadioButton;

public class RadioButton extends JRadioButton {
	
	public RadioButton (String texto) {
		super(texto);
		this.setFont(new Font("Good Times", Font.PLAIN, 15));
		this.setBackground(new Color(2,7,41));
		this.setForeground(new Color(0xFFFFFF));
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
	}
}
