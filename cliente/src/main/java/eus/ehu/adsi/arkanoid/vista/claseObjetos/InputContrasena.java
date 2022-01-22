package eus.ehu.adsi.arkanoid.vista.claseObjetos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class InputContrasena extends JPasswordField{
	
	private String ph;

	public InputContrasena(String ph) {
		this.ph = ph;
		this.setFont(new Font("Good Times", Font.PLAIN, 20));
		this.setBackground(new Color(0x000000));
		this.setForeground(new Color(0xFFFFFF));
		this.setColumns(20);
		this.setEchoChar('*');
		this.setBorder(new EmptyBorder(5,10,5,10));
		this.setPreferredSize(new Dimension(0, 30));
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public InputContrasena() {
		this.ph = null;
	}

	//Para añadir un placeholder
	
	@Override
	public String getText() {
		String text = super.getText();

		if (text.trim().length() == 0 && ph != null) {
			text = ph;
		}

		return text;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (super.getPassword().length > 0 || ph == null) {
			return;
		}
		
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(super.getDisabledTextColor());
		g2.drawString(ph, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
	}
	
}
