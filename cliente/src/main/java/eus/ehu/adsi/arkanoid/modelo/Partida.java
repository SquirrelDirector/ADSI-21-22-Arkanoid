package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Partida {

	private int vidasRestantes;
	private ArrayList<Bloque> bloques;
	private Cronometro tiempo;
	private int puntuacion;
	private Paddle paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 50);
	private Bola bola = new Bola(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
	private static Partida miPartida;
	public boolean ganar = false;
	public boolean gameOver = false;
	

	public Partida() {
		// TODO - implement Partida.Partida
		throw new UnsupportedOperationException();
	}

	public void restarVidas() {
		vidasRestantes--;
		if (vidasRestantes == 0) {
			gameOver = true;
			//TODO: llamar a la funcionalidad de la vista de perder
		}
	}

	public JSON getDatosPartidaActual() {
		// TODO - implement Partida.getDatosPartidaActual
		throw new UnsupportedOperationException();
	}

	public void incrementarPuntuacion() {
		this.puntuacion++;
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
		
		//TODO: setChange()
		//TODO: notifyObservers()
	}
	
	public void testPaddle() {
		Game.testCollision(paddle, bola);
	}
	
	public void ganar() {
		if (puntuacion == (Config.COUNT_BLOCKS_X * Config.COUNT_BLOCKS_Y)) {
			ganar = true;
			//TODO: llamada a la vista de has ganado, guardar puntuacion, publicar resultados
		}
	}
	
	public void generarPartida() {
		//TODO: generar bloques bola y paddle
	}

}