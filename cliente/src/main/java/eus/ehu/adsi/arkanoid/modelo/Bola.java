package eus.ehu.adsi.arkanoid.modelo;

public class Bola extends GameObject {

	double radius = eus.ehu.adsi.arkanoid.modelo.Configuracion.BALL_RADIUS;
	public double velocityX;
	public double velocityY;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Bola(int x, int y) {
		// TODO - implement Bola.Bola
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param partida
	 * @param paddle
	 */
	public void update(Partida partida, Paddle paddle) {
		// TODO - implement Bola.update
		throw new UnsupportedOperationException();
	}

	public double left() {
		// TODO - implement Bola.left
		throw new UnsupportedOperationException();
	}

	public double right() {
		// TODO - implement Bola.right
		throw new UnsupportedOperationException();
	}

	public double top() {
		// TODO - implement Bola.top
		throw new UnsupportedOperationException();
	}

	public double bottom() {
		// TODO - implement Bola.bottom
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param n
	 */
	public void modificarBola(double n) {
		this.radius += n;
	}

}