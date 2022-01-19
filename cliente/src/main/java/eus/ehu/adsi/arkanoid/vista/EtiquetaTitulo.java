package eus.ehu.adsi.arkanoid.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class EtiquetaTitulo extends JLabel {
	
	public EtiquetaTitulo(String texto) {
		super(texto);
		this.setFont(new Font("Good Times", Font.PLAIN, 40));
		this.setForeground(new Color(0xFFFFFF));
	}

}
