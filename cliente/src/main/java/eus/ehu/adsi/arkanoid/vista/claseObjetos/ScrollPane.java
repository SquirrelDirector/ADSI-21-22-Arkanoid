package eus.ehu.adsi.arkanoid.vista.claseObjetos;

import java.awt.Color;

import javax.swing.JScrollPane;

public class ScrollPane extends JScrollPane{

	public ScrollPane() {
		this.getViewport().setBackground(new Color(2,7,41));
		this.setBorder(null);
	}
}
