package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Partida {

	private int vidasRestantes;
	private ArrayList<Bloque> bloques;
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
		this.puntuacion += 10;
	}

	/**
	 * 
	 * @param n
	 */
	public void romperBloque(int n) {
		bloques.get(n).romper();
	}

	/**
	 * 
	 * @param n
	 */
	public void modificarPaddle(double n) {
		this.paddle.modificarPaddle(n);
	}

	/**
	 * 
	 * @param n
	 */
	public void modificarBola(double n) {
		this.bola.modificarBola(n);
	}

	public static Partida getMiPartida() {
		return Partida.miPartida;
	}
	
	public void testBola() {
		Iterator<Bloque> it = bloques.iterator();
		while (it.hasNext()) {
			Bloque bloque = it.next();
			Game.testCollision(bloque, bola, this);
			if (bloque.destroyed) {
				it.remove();
			}
		}
	}
	
	public void testPaddle() {
		Game.testCollision(paddle, bola);
	}

}