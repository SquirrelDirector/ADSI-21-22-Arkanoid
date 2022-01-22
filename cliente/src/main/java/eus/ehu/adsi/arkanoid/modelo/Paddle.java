package eus.ehu.adsi.arkanoid.modelo;

public class Paddle extends Rectangle {

	public double velocity = 0.0;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Paddle(double x, double y) {
		super(x,y);
		this.sizeX = Config.PADDLE_WIDTH;
		this.sizeY = Config.PADDLE_HEIGHT;
	}

	/**
	 * 
	 * @param g
	 */
	public void modificarPaddle(double n) {
		this.sizeX += n;
	}
	
	public void update() {
		x += velocity * Config.FT_STEP;
		System.out.println(Config.PADDLE_VELOCITY);
	}
	
	public void stopMove() {
		velocity = 0.0;
	}

	public void moveLeft() {
		if (left() > 0.0) {
			velocity = -Config.PADDLE_VELOCITY;
		} else {
			velocity = 0.0;
		}
	}

	public void moveRight() {
		if (right() < Config.SCREEN_WIDTH) {
			velocity = Config.PADDLE_VELOCITY;
		} else {
			velocity = 0.0;
		}
	}

}