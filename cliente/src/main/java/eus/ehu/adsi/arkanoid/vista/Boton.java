package eus.ehu.adsi.arkanoid.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class Boton extends JButton {
	/**
	 * Create the panel.
	 */
	public Boton(String texto) {
		super(texto);
		this.setFont(new Font("Good Times", Font.PLAIN, 20));
		this.setBackground(new Color(0,0,0,191));
		this.setForeground(new Color(0xFFFFFF));
		this.setBorder(null);
		this.setFocusPainted(false);
		this.setBorder(new EmptyBorder(10,10,10,10));
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            	e.getComponent().setBackground(new Color(0,0,0,75));
            }
		});
	}

}
