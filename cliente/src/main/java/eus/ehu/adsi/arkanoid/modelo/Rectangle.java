package eus.ehu.adsi.arkanoid.modelo;

public class Rectangle extends GameObject {

	double sizeX;
	double sizeY;
	
	public Rectangle(double x, double y) {
		super(x,y);
	}
	
	public double left() {
		return x - sizeX / 2.0;
	}

	public double right() {
		return x + sizeX / 2.0;
	}

	public double top() {
		return y - sizeY / 2.0;
	}

	public double bottom() {
		return y + sizeY / 2.0;
	}
}