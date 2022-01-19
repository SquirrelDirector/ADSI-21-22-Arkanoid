package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class Partida {

	private int vidasRestantes;
	private ArrayList<Bloque> bloques;
	//NUEVO!
	private ArrayList<Logro> logrosLista;
	//
	private int tiempo;
	private int puntuacion;
	//private Paddle paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 50);
	//private Bola bola = new Bola(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
	
	private static Partida miPartida;

	public Partida() {
		throw new UnsupportedOperationException();
	}

	public void restarVidas() {
		// TODO - implement Partida.restarVidas
		throw new UnsupportedOperationException();
	}

	public JSONObject getDatosPartidaActual() {
		JSONObject datos = new JSONObject();
		datos.put("tiempoPartida", tiempo);
		datos.put("puntuacionConseguida", puntuacion);
		JSONArray logros = new JSONArray();
		Iterator<Logro> itr = logrosLista.iterator();
		while(itr.hasNext()) {
			Logro l = itr.next();
			JSONObject logroObj = new JSONObject();
			logroObj.put("nombreLogro", l.getNombre());
			logros.put(logroObj);
		}
		datos.put("logrosConseguidos", logros);
		return datos;
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

	

}