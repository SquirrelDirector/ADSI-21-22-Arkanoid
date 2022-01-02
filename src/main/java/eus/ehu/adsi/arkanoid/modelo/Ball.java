package eus.ehu.adsi.arkanoid.modelo;

public class Ball {

	public double x;
	public double y;
	double radius = eus.ehu.adsi.arkanoid.modelo.Config.BALL_RADIUS;
	public double velocityX = eus.ehu.adsi.arkanoid.modelo.Config.BALL_VELOCITY;
	public double velocityY = eus.ehu.adsi.arkanoid.modelo.Config.BALL_VELOCITY;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Ball(int x, int y) {
		// TODO - implement Ball.Ball
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param g
	 */
	public void draw(java.awt.Graphics g) {
		// TODO - implement Ball.draw
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param scoreBoard
	 * @param paddle
	 */
	public void update(ScoreBoard scoreBoard, Paddle paddle) {
		// TODO - implement Ball.update
		throw new UnsupportedOperationException();
	}

	public double left() {
		// TODO - implement Ball.left
		throw new UnsupportedOperationException();
	}

	public double right() {
		// TODO - implement Ball.right
		throw new UnsupportedOperationException();
	}

	public double top() {
		// TODO - implement Ball.top
		throw new UnsupportedOperationException();
	}

	public double bottom() {
		// TODO - implement Ball.bottom
		throw new UnsupportedOperationException();
	}

}