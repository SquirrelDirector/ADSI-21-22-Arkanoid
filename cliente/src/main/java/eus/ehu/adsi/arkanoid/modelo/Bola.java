package eus.ehu.adsi.arkanoid.modelo;

public class Bola extends GameObject {

	public double radius = Config.BALL_RADIUS;
	public double velocityX = Config.BALL_VELOCITY;
	public double velocityY = Config.BALL_VELOCITY;;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Bola(int x, int y) {
		super(x,y);
	}

	/**
	 * 
	 * @param n
	 */
	public void modificarBola(double n) {
		// TODO - implement Bola.modificarBola
		throw new UnsupportedOperationException();
	}

}