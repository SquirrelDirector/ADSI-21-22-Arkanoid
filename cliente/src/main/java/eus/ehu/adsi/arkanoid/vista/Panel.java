package eus.ehu.adsi.arkanoid.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Panel extends JPanel{

	public Panel() {
		this.setBackground(new Color(0,0,0,191));
		this.setBorder(new EmptyBorder(10,10,10,10));
	}
}
