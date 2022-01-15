package eus.ehu.adsi.arkanoid.modelo;

public class Game {

	private boolean running;
	private boolean tryAgain;

	public boolean isRunning() {
		return this.running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isTryAgain() {
		return this.tryAgain;
	}

	public void setTryAgain(boolean tryAgain) {
		this.tryAgain = tryAgain;
	}

	public Game() {
		// TODO - implement Game.Game
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mPaddle
	 * @param mBall
	 */
	public static void testCollision(Paddle mPaddle, Bola mBall) {
		// TODO - implement Game.testCollision
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mBrick
	 * @param mBall
	 * @param partida
	 */
	public static void testCollision(Bloque mBrick, Ball mBall, Partida partida) {
		// TODO - implement Game.testCollision
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mA
	 * @param mB
	 */
	public static boolean isIntersecting(GameObject mA, GameObject mB) {
		// TODO - implement Game.isIntersecting
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bricks
	 */
	public static java.util.List<Brick> initializeBricks(java.util.List<Brick> bricks) {
		// TODO - implement Game.initializeBricks
		throw new UnsupportedOperationException();
	}

}