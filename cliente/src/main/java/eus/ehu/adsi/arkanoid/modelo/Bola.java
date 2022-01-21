package eus.ehu.adsi.arkanoid.modelo;

import classic.eus.ehu.adsi.arkanoid.view.Config;
import classic.eus.ehu.adsi.arkanoid.view.Paddle;
import classic.eus.ehu.adsi.arkanoid.view.ScoreBoard;

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

	public double left() {
		return x - radius;
	}

	public double right() {
		return x + radius;
	}

	public double top() {
		return y - radius;
	}

	public double bottom() {
		return y + radius;
	}

	/**
	 * 
	 * @param n
	 */
	public void modificarBola(double n) {
		this.radius += n;
	}
	
	public void update() {
		x += velocityX * Config.FT_STEP;
		y += velocityY * Config.FT_STEP;

		if (left() < 0)
			velocityX = Config.BALL_VELOCITY;
		else if (right() > Config.SCREEN_WIDTH)
			velocityX = -Config.BALL_VELOCITY;
		if (top() < 0) {
			velocityY = Config.BALL_VELOCITY;
		} else if (bottom() > Config.SCREEN_HEIGHT) {
			velocityY = -Config.BALL_VELOCITY;
			Partida miPartida = Partida.getMiPartida();
			x = Config.SCREEN_WIDTH / 2;
			y = Config.SCREEN_HEIGHT / 2;
			miPartida.restarVidas();
		}

	}

}