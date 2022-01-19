package eus.ehu.adsi.arkanoid.controlador;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.*;
import java.awt.image.BufferStrategy;

import eus.ehu.adsi.arkanoid.modelo.*;
import java.awt.Color;
import eus.ehu.adsi.arkanoid.vista.Tablero;

public class Arkanoid{ //extends JFrame implements KeyListener {

	private static Arkanoid miArkanoid;
	/**
	 * Game variables
	 */
	private Game game;
	private Partida partida = new Partida();
	private double lastFt;
	private double currentSlice;
	private Usuario usuario;

	private Arkanoid() {

		
	}

	public static Arkanoid getArkanoid() {
		if (miArkanoid == null) {
			miArkanoid = new Arkanoid();
		}
		return miArkanoid;
	}

	public void jugar() {
		
		game.setRunning(true);

		while (game.isRunning()) {

			long time1 = System.currentTimeMillis();

			if (!partida.gameOver && !partida.ganar) {
				game.setTryAgain(false);
				update();

				// to simulate low FPS
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {}

			} else {
				if (game.isTryAgain()) {
					game.setTryAgain(false);
					partida.generarPartida();
				}
			}

			long time2 = System.currentTimeMillis();
			double elapsedTime = time2 - time1;

			lastFt = elapsedTime;

			double seconds = elapsedTime / 1000.0;
			if (seconds > 0.0) {
				double fps = 1.0 / seconds;
			}

		}

	}
	
	private void update() {
		currentSlice += lastFt;

		for (; currentSlice >= Config.FT_SLICE; currentSlice -= Config.FT_SLICE) {
			Partida miPartida = Partida.getMiPartida();
			miPartida.testPaddle();
			miPartida.testBola();
			
			//comprobar si se han roto todos los bloques
			partida.ganar();
		}
	}

	public boolean isIdentificado() {
		return usuario.isIdentificado();
	}

	public JSONObject obtenerPersonalizables() {
		JSONObject resultado = new JSONObject();
		resultado.put("colores", obtenerTodosPersonalizablesPorTabla("color"));
		resultado.put("sonidos", obtenerTodosPersonalizablesPorTabla("audio"));
		return resultado;
	}
	
	public JSONObject obtenerAvatares() {
		JSONObject resultado = new JSONObject();
		resultado.put("avatares", obtenerTodosPersonalizablesPorTabla("imagen"));
		return resultado;
	}

	private JSONArray obtenerTodosPersonalizablesPorTabla(String tabla) {
		JSONArray opciones = new JSONArray();
		ResultadoSQL resconsulta = GestorDB.getGestorDB().execSQL("SELECT * FROM "+ tabla);
		if (resconsulta != null) {
			while (resconsulta.hasNext()) {
				JSONObject informacion = new JSONObject();
				String val = "";
				if (tabla.equals("color")) {
					val = "Codigo";
				} else {
					val = "Path";
				}
				informacion.put(val, resconsulta.get(val));
				informacion.put("Nombre", resconsulta.get("Nombre"));
				opciones.put(informacion);
				resconsulta.next();
			}
		}
		return opciones;
	}

	public JSONObject obtenerPersonalizacionUsuario() {
		return usuario.obtenerPersonalizacionUsuario();
	}
	
	public JSONObject obtenerDatosUsuario() {
		return usuario.getDatosUsuario();
	}

	/**
	 * 
	 * @param pathMusica
	 * @param colorFondo
	 * @param colorBola
	 * @param colorPaddle
	 * @param colorLadrillo
	 */
	public void actualizarPersonalizacionDB(String pathMusica, String colorFondo, String colorBola, String colorPaddle, String colorLadrillo, String atributosPersonalizacion) {
		String email = usuario.getEmail();
		GestorUsuarios.getGestorUsuario().actualizarPersonalizacion(email, pathMusica, colorFondo, colorBola, colorPaddle, colorLadrillo, atributosPersonalizacion);
	}
	
	/**
	 * 
	 * @param pathAvatar
	 * @param nombreUsu
	 */
	public void actualizarDatosUsuDB(String pathAvatar, String nombreUsu) {
		String email = usuario.getEmail();
		GestorUsuarios.getGestorUsuario().actualizarDatosUsuDB(email, pathAvatar, nombreUsu);
	}

	/**
	 * 
	 * @param pathMusica
	 * @param colorFondo
	 * @param colorBola
	 * @param colorPaddle
	 * @param colorLadrillo
	 */
	public void actualizarPersonalizacionUsu(String pathMusica, String colorFondo, String colorBola, String colorPaddle, String colorLadrillo, String atributosPersonalizacion) {
		usuario.actualizarPersonalizacionUsu(pathMusica, colorFondo, colorBola, colorPaddle, colorLadrillo, atributosPersonalizacion);
	}
	
	/**
	 * 
	 * @param pathAvatar
	 * @param nombreUsu
	 */
	public void actualizarDatosUsu(String pathAvatar, String nombreUsu) {
		usuario.actualizarDatosUsu(pathAvatar, nombreUsu);
	}

	/**
	 * 
	 * @param dificultad
	 * @param isPersonal
	 */
	public JSONArray mostrarRanking(int dificultad, boolean isPersonal) {
		
		if (isPersonal){
			return usuario.obtenerRankingPersonal(dificultad);
		}
		else{
			return GestorPuntuaciones.getGestorPuntuaciones().obtenerRanking(dificultad);
		}
	}

	/**
	 * 
	 * @param idNivel
	 */
	public void actualizarUltimaPartida(int idNivel) {
		usuario.actualizarUltimaPartida(idNivel);
	}

	/**
	 * 
	 * @param idNivel
	 */
	public String[] obtenerDatosNivel(int idNivel) {
		if (idNivel==6) {
			return usuario.obtenerDatosNivelPersonalizado();
		}
		else {
			return GestorNiveles.getGestorNiveles().obtenerDatosNivel(idNivel);
		}
	}

	public JSONArray getLogros() {
		return usuario.getLogros();
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSONObject getInfoLogro(String nombre) {
		return usuario.getInfoLogro(nombre);
	}

	/**
	 * 
	 * @param mail
	 * @param pass
	 */
	public int iniciarSesion(String mail, String pass) {
		// TODO - implement Arkanoid.iniciarSesion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usr
	 * @param mail
	 * @param pass
	 */
	public int registrarse(String usr, String mail, String pass) {
		// TODO - implement Arkanoid.registrarse
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 */
	public int recuperarContrasena(String mail) {
		// TODO - implement Arkanoid.recuperarContrasena
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 * @param pass
	 */
	public void cambiarContrasena(String mail, String pass) {
		// TODO - implement Arkanoid.cambiarContrasena
		throw new UnsupportedOperationException();
	}

	public JSONObject getResultadosPartida() {
		JSONObject datosPartida = partida.getDatosPartidaActual();
		if(usuario.isIdentificado()){
			JSONObject datosHistoricos = usuario.getDatosHistoricosJugador();
			agregarJSON(datosPartida, datosHistoricos);
		}
		return datosPartida;
	}

	/**
	 * 
	 * @param datosPartida
	 * @param datosHistoricos
	 */
	private void agregarJSON(JSONObject datosPartida, JSONObject datosHistoricos) {
		// TODO - implement Arkanoid.publicarResultados
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param redSocial
	 */
	public void publicarResultados(String redSocial) {
		// TODO - implement Arkanoid.publicarResultados
		throw new UnsupportedOperationException();
	}

	public int getUltimaPartida() {
		return usuario.getNivelDefault();
	}
	
	/**
	 * 
	 * @param nombreUsu
	 */
	public boolean comprobarNombre(String nombreUsu) {
		return GestorUsuarios.getGestorUsuario().comprobarNombre(nombreUsu);
	}
	
	public void updateConfig(double Velocidad, double Anchura, int Num_Ladrillos) { 
		Config.BALL_VELOCITY=Velocidad/2; 
		Config.PADDLE_WIDTH=Anchura; 
		Config.COUNT_BLOCKS_Y=Num_Ladrillos/Config.COUNT_BLOCKS_X; 
	} 
	 
	public void updateColores(String Fondo, String Bola, String Ladrillo, String Paddle) { 
		Config.BACKGROUND_COLOR = new Color(Integer.parseInt(Fondo)); 
		Config.BALL_COLOR = new Color(Integer.parseInt(Bola));
		Config.BRICK_COLOR = new Color(Integer.parseInt(Ladrillo));
		Config.PADDLE_COLOR = new Color(Integer.parseInt(Paddle));
	} 
	 
	public void updateMusica(String path) { 
		Config.PATH_MUSICA = path; 
	} 
}