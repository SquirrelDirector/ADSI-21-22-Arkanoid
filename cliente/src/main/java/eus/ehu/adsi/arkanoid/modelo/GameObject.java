package eus.ehu.adsi.arkanoid.modelo;

public abstract class GameObject {

	public double x;
	public double y;
	
	public GameObject(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract double left();

	public abstract double right();

	public abstract double top();

	public abstract double bottom();

}