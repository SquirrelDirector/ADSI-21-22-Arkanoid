package eus.ehu.adsi.arkanoid.vista.claseObjetos;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JSlider;

public class Slider extends JSlider{
	
	public Slider() {
		this.setBackground(new Color(2,7,41));
		this.setForeground(new Color(255,255,255));
		this.setFont(new Font("Good Times", Font.PLAIN, 14));
		this.setSnapToTicks(true);
		this.setPaintLabels(true);
		this.setPaintTicks(true);
	}
}
