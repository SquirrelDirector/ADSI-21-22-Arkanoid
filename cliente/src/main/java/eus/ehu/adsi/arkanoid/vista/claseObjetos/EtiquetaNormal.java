package eus.ehu.adsi.arkanoid.vista.claseObjetos;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class EtiquetaNormal extends JLabel {
	
	public EtiquetaNormal(String texto) {
		super(texto);
		this.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.setForeground(new Color(0xFFFFFF));
	}

}
