package eus.ehu.adsi.arkanoid.modelo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import org.json.JSONArray;
import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.vista.ventanas.VentanaFinPartida;

@SuppressWarnings("deprecation")
public class Partida extends Observable {

	private int vidasRestantes;
	private ArrayList<Logro> listaLogros = new ArrayList<Logro>();
	private ArrayList<Bloque> bloques;
	private int puntuacion;
	private Paddle paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 50);
	private Bola bola = new Bola(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
	private static Partida miPartida;
	private Cronometro crono = new Cronometro();
	public boolean ganar = false;
	public boolean gameOver = false;
	
	private Partida() {}
	
	public static Partida getMiPartida() {
		if (miPartida == null) miPartida = new Partida();
        return miPartida;
	}
	
	public Cronometro getCrono() {
		return crono;
	}
	
	public void iniciarCrono(){
        if (this.crono == null || crono.estaParado()) this.crono = new Cronometro();
    }

	public void restarVidas() {
		vidasRestantes--;
		System.out.println("vidas-"+vidasRestantes);
		setChanged();
		notifyObservers(vidasRestantes);
		if (vidasRestantes == 0) {
			gameOver = true;
			crono.parar();
			setChanged();
			notifyObservers("hasPerdido");
		}
	}
	public void addLogro(Logro pLogro) {
		this.listaLogros.add(pLogro);
	}
	public JSONObject getDatosPartidaActual() {
		JSONObject datos = new JSONObject();
		datos.put("tiempoPartida", crono.getSegundosTotales());
		datos.put("puntuacionConseguida", puntuacion);
		return datos;
	}

	public JSONArray getLogrosPartida(){
		JSONArray logros = new JSONArray();
		Iterator<Logro> itr = listaLogros.iterator();
		while(itr.hasNext()) {
			Logro l = itr.next();
			JSONObject logro = new JSONObject();
			logro.put("nombre", l.getNombre());
			logros.put(logro);
		}
		return logros;
	}
	public void setLogrosPartida(JSONArray logrosDesbloqueados) {
		this.listaLogros = new ArrayList<>();
		CatalogoLogros catL = CatalogoLogros.getMiCatalogoLogros();
		for(int i = 0; i < logrosDesbloqueados.length(); i++) {
			Logro log = catL.getLogro(logrosDesbloqueados.getJSONObject(i).getString("nombre"));
			listaLogros.add(log);
		}
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
		}
	}

	/**
	 * 
	 * @param n
	 */
	public void modificarPaddle(double n) {
		this.paddle.modificarPaddle(n);
	}
	
	public void moverPaddleRight() {
		this.paddle.moveRight();
	}
	
	public void moverPaddleLeft() {
		this.paddle.moveLeft();
	}
	
	public void pararPaddle() {
		this.paddle.stopMove();
	}
	
	public void updatePaddle() {
		this.paddle.update();
		setChanged();
		notifyObservers(paddle);
	}

	/**
	 * 
	 * @param n
	 */
	public void modificarBola(double n) {
		this.bola.modificarBola(n);
	}

	public void testBola() {
		Iterator<Bloque> it = bloques.iterator();
		while (it.hasNext()) {
			Bloque bloque = it.next();
			Game.testCollision(bloque, bola, this);
			if (bloque.destroyed) {
				setChanged();
				notifyObservers(bloque);
				it.remove();
			}
		}
		bola.update();
		setChanged();
		notifyObservers(bola);
	}
	
	public void testPaddle() {
		Game.testCollision(paddle, bola);
	}
	
	public boolean ganar() {
		if (this.bloques.size() == 0) {
			ganar = true;
			crono.parar();

			setChanged();
			notifyObservers("FinPartida");
		}
		
		return ganar;
		
	}
	public void cotejarLogros(Usuario user) {
		int idNivelActual = user.getNivelDefault();
		Logro ganarMismoNivel = CatalogoLogros.getMiCatalogoLogros().getLogro("ganarMismoNivel"+idNivelActual);
		Logro ganarPartidasSeguidas = CatalogoLogros.getMiCatalogoLogros().getLogro("ganarPartidasSeguidas");
		
		int tiempoActual = this.getTiempo();
		if(tiempoActual<60) {
			Logro speedrun = CatalogoLogros.getMiCatalogoLogros().getLogro("speedrun");
			this.addLogro(speedrun);
		}
		this.addLogro(ganarPartidasSeguidas);
		this.addLogro(ganarMismoNivel);
		
		JSONArray logrosObtenidos = this.getLogrosPartida();
		try {
			this.setLogrosPartida(user.cotejarLogros(logrosObtenidos));
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void generarPartida() {
		//Generar posicion del bloque de la suerte
		int posX = (int) Math.floor((Math.random()*Config.COUNT_BLOCKS_X)+1);
		int posY = (int) Math.floor((Math.random()*Config.COUNT_BLOCKS_Y)+1);
		
		//Generar bloques
		bloques = new ArrayList<Bloque>();
		for (int iX = 0; iX < Config.COUNT_BLOCKS_X; ++iX) {
			for (int iY = 0; iY < Config.COUNT_BLOCKS_Y; ++iY) {
				if (posX==iX && posY==iY) {
					bloques.add(new BloqueSuerte(
							(iX + 1) * (Config.BLOCK_WIDTH + 3) + 22,
							(iY + 2) * (Config.BLOCK_HEIGHT + 3) + 50)
							);
				}else {
					bloques.add(new Bloque(
							(iX + 1) * (Config.BLOCK_WIDTH + 3) + 22,
							(iY + 2) * (Config.BLOCK_HEIGHT + 3) + 50)
							);
				}
			}
		}
		
		//Reiniciar bola
		bola.x = Config.SCREEN_WIDTH / 2;
		bola.y = Config.SCREEN_HEIGHT / 2;
		bola.radius = Config.BALL_RADIUS;
		
		//Reiniciar paddle
		paddle.x = Config.SCREEN_WIDTH / 2;
		paddle.sizeX = Config.PADDLE_WIDTH;
		
		//Asignar numero de vidas
		vidasRestantes = Config.PLAYER_LIVES;
		
		ganar = false;
		gameOver = false;
		
	}
	
	public int getNumBloques() {
		return this.bloques.size();
	}

	public int getPuntuacion(){
		return puntuacion;
	}

	public int getTiempo(){
		return crono.getSegundosTotales();
	}
	
	public ArrayList<Bloque> getBloques(){
		return this.bloques;
	}

	public void actualizarPuntuacion(int nivel){
		this.puntuacion = (int) ((((float)puntuacion*((float)vidasRestantes+1)*(float)nivel)/(float)crono.getSegundosTotales())*1000.0);
	}

}