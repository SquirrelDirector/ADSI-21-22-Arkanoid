package eus.ehu.adsi.arkanoid.vista;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Panel extends JPanel{

	public Panel() {
		this.setBackground(new Color(0x000000));
		this.setBorder(new EmptyBorder(10,10,10,10));		
	}
}
