package eus.ehu.adsi.arkanoid.modelo;

import java.util.Collection;
import java.util.Observable;

import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class Partida extends Observable {

	private int vidasRestantes;
	private ArrayList<Logro> listaLogros;
	private ArrayList<Bloque> bloques;
	private int puntuacion;
	private Paddle paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 50);
	private Bola bola = new Bola(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
	private static Partida miPartida;
	private Cronometro crono;
	public boolean ganar = false;
	public boolean gameOver = false;
	

	public Partida() {

		
	}
	
	public Cronometro getCrono() {
		return crono;
	}
	
	public void iniciarCrono(){
        if (this.crono == null || crono.estaParado()) this.crono = new Cronometro();
    }

	public void restarVidas() {
		vidasRestantes--;
		if (vidasRestantes == 0) {
			gameOver = true;
			tiempo.parar();
			//TODO: llamar a la funcionalidad de la vista de perder
		}
	}

	public JSONObject getDatosPartidaActual() {
		JSONObject datos = new JSONObject();
		datos.put("tiempoPartida", tiempo);
		datos.put("puntuacionConseguida", puntuacion);
		JSONArray logros = new JSONArray();
		Iterator<Logro> itr = listaLogros.iterator();
		while(itr.hasNext()) {
			Logro l = itr.next();
			JSONObject logro = new JSONObject();
			logro.put("nombreLogro", l.getNombre());
			logros.put(logro);
		}
		datos.put("logros", logros);
		return datos;
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
		if (miPartida == null) miPartida = new Partida();
        return miPartida;
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
			tiempo.parar();
			//TODO: llamada a la vista de has ganado, guardar puntuacion, publicar resultados
		}
	}
	
	public void generarPartida() {
		//TODO: generar bloques bola y paddle e iniciar cronometro
	}
	
	public int getNumBloques() {
		this.bloques.size();
	}

}