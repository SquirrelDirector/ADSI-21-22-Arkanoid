package eus.ehu.adsi.arkanoid.modelo;

import java.util.Collection;
import java.util.Observable;

import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class Partida extends Observable {

	private int vidasRestantes;
	private Collection<Bloque> bloques;
	private ArrayList<Logro> listaLogros;
	private int tiempo;
	private int puntuacion;
	private Paddle paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 50);
	private Bola bola = new Bola(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
	private static Partida miPartida;
	private Cronometro crono;

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
		if (miPartida == null) miPartida = new Partida();
        return miPartida;
	}

}