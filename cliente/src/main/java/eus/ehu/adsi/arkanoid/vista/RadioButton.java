package eus.ehu.adsi.arkanoid.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JRadioButton;

public class RadioButton extends JRadioButton {
	
	public RadioButton (String texto) {
		super(texto);
		this.setFont(new Font("Good Times", Font.PLAIN, 15));
		this.setForeground(new Color(0xFFFFFF));
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
	}
}
