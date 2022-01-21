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
		
	}

	/**
	 * 
	 * @param mPaddle
	 * @param mBall
	 */
	public static void testCollision(Paddle mPaddle, Bola mBall) {
		
		if (!Game.isIntersecting(mPaddle, mBall))
			return;
		mBall.velocityY = -Config.BALL_VELOCITY;
		if (mBall.x < mPaddle.x)
			mBall.velocityX = -Config.BALL_VELOCITY;
		else
			mBall.velocityX = Config.BALL_VELOCITY;
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
			System.out.println("he golpeado");
			mBall.velocityX = ballFromLeft ? -Config.BALL_VELOCITY : Config.BALL_VELOCITY;
		} else {
			mBall.velocityY = ballFromTop ? -Config.BALL_VELOCITY : Config.BALL_VELOCITY;
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
	public static java.util.List<Bloque> initializeBricks(java.util.List<Bloque> bricks) {
		// TODO - implement Game.initializeBricks
		throw new UnsupportedOperationException();
	}

}