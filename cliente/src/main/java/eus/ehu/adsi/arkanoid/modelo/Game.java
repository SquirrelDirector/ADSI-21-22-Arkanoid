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
	public static void testCollision(Bloque mBrick, Bola mBall, Partida partida) {
		if (!Game.isIntersecting(mBrick, mBall))
			return;

		mBrick.romper();

		double overlapLeft = mBall.right() - mBrick.left();
		double overlapRight = mBrick.right() - mBall.left();
		double overlapTop = mBall.bottom() - mBrick.top();
		double overlapBottom = mBrick.bottom() - mBall.top();

		boolean ballFromLeft = overlapLeft < overlapRight;
		boolean ballFromTop = overlapTop < overlapBottom;

		double minOverlapX = ballFromLeft ? overlapLeft : overlapRight;
		double minOverlapY = ballFromTop ? overlapTop : overlapBottom;

		if (minOverlapX < minOverlapY) {
			//mBall.velocityX = ballFromLeft ? -Config.BALL_VELOCITY : Config.BALL_VELOCITY;
		} else {
			//mBall.velocityY = ballFromTop ? -Config.BALL_VELOCITY : Config.BALL_VELOCITY;
		}
	}

	/**
	 * 
	 * @param mA
	 * @param mB
	 */
	public static boolean isIntersecting(GameObject mA, GameObject mB) {
		return mA.right() >= mB.left() && mA.left() <= mB.right()
				&& mA.bottom() >= mB.top() && mA.top() <= mB.bottom();
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