package eus.ehu.adsi.arkanoid.modelo;

public class Partida {

	private int vidasRestantes;
	private Coleccion<Bloque> bloques;
	private int tiempo;
	private int puntuacion;
	private Paddle paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 50);
	private Bola bola = new Bola(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
	private static Partida miPartida;

	public Partida() {
		// TODO - implement Partida.Partida
		throw new UnsupportedOperationException();
	}

	public void restarVidas() {
		// TODO - implement Partida.restarVidas
		throw new UnsupportedOperationException();
	}

	public JSON getDatosPartidaActual() {
		// TODO - implement Partida.getDatosPartidaActual
		throw new UnsupportedOperationException();
	}

	public void incrementarPuntuacion() {
		// TODO - implement Partida.incrementarPuntuacion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param n
	 */
	public void romperBloque(int n) {
		// TODO - implement Partida.romperBloque
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param n
	 */
	public void modificarPaddle(double n) {
		// TODO - implement Partida.modificarPaddle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param n
	 */
	public void modificarBola(double n) {
		// TODO - implement Partida.modificarBola
		throw new UnsupportedOperationException();
	}

	public static Partida getMiPartida() {
		return Partida.miPartida;
	}

}