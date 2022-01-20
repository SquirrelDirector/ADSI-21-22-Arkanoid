package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import org.json.JSONArray;
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
	private Cronometro crono = new Cronometro();
	public boolean ganar = false;
	public boolean gameOver = false;
	

	private Partida() {

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
			crono.parar();
			//TODO: llamar a la funcionalidad de la vista de perder
		}
	}

	public JSONObject getDatosPartidaActual() {
		JSONObject datos = new JSONObject();
		datos.put("tiempoPartida", crono);
		datos.put("puntuacionConseguida", puntuacion);
		JSONArray logros = new JSONArray();
		Iterator<Logro> itr = listaLogros.iterator();
		while(itr.hasNext()) {
			Logro l = itr.next();
			JSONObject logro = new JSONObject();
			logro.put("nombreLogro", l.getNombre());
			logros.put(logro);
		}
		datos.put("logrosConseguidos", logros);
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
		if (bloques.get(n).destroyed) {
			setChanged();
			notifyObservers(bloques.get(n));
			bloques.remove(n);
		}
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
				setChanged();
				notifyObservers(it);
				it.remove();
			}
		}
		setChanged();
		notifyObservers(bola);
	}
	
	public void testPaddle() {
		Game.testCollision(paddle, bola);
		setChanged();
		notifyObservers(paddle);
	}
	
	public void ganar() {
		if (puntuacion == (Config.COUNT_BLOCKS_X * Config.COUNT_BLOCKS_Y)) {
			ganar = true;
			crono.parar();
			//TODO: llamada a la vista de has ganado, guardar puntuacion, publicar resultados
		}
	}
	
	public void generarPartida() {
		//Iniciar cronometro
		crono.reset();
		crono.run();
		
		//Generar bloques
		bloques.clear();
		for (int iX = 0; iX < Config.COUNT_BLOCKS_X; ++iX) {
			for (int iY = 0; iY < Config.COUNT_BLOCKS_Y; ++iY) {
				bloques.add(new Bloque(
						(iX + 1) * (Config.BLOCK_WIDTH + 3) + 22,
						(iY + 2) * (Config.BLOCK_HEIGHT + 3) + 50)
						);
			}
		}
		
		//Reiniciar bola
		bola.x = Config.SCREEN_WIDTH / 2;
		bola.y = Config.SCREEN_HEIGHT / 2;
		
		//Reiniciar paddle
		paddle.x = Config.SCREEN_WIDTH / 2;
		
		//Asignar numero de vidas
		vidasRestantes = Config.PLAYER_LIVES;
		
		//Iniciar cronometro
		crono.reset();
		crono.run();
	}
	
	public int getNumBloques() {
		return this.bloques.size();
	}

}