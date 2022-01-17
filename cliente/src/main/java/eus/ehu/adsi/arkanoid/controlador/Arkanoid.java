package eus.ehu.adsi.arkanoid.controlador;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.*;
import eus.ehu.adsi.arkanoid.modelo.*;

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
		// TODO - implement Arkanoid.Arkanoid
		throw new UnsupportedOperationException();
	}

	public static Arkanoid getArkanoid() {
		// TODO - implement Arkanoid.getArkanoid
		throw new UnsupportedOperationException();
	}

	private void update() {
		// TODO - implement Arkanoid.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param emailUsuario
	 */
	public JSON obtenerPersonalizacion(int emailUsuario) {
		// TODO - implement Arkanoid.obtenerPersonalizacion
		throw new UnsupportedOperationException();
	}

	public JSON obtenerPersonalizacionUsuario() {
		// TODO - implement Arkanoid.obtenerPersonalizacionUsuario
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pathMusica
	 * @param colorFondo
	 * @param colorBola
	 * @param colorPaddle
	 * @param colorLadrillo
	 */
	public void actualizarPersonalizacionDB(int pathMusica, int colorFondo, int colorBola, int colorPaddle, int colorLadrillo) {
		// TODO - implement Arkanoid.actualizarPersonalizacionDB
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pathMusica
	 * @param colorFondo
	 * @param colorBola
	 * @param colorPaddle
	 * @param colorLadrillo
	 */
	public void actualizarPersonalizacionUsu(int pathMusica, int colorFondo, int colorBola, int colorPaddle, int colorLadrillo) {
		// TODO - implement Arkanoid.actualizarPersonalizacionUsu
		throw new UnsupportedOperationException();
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
		// TODO - implement Arkanoid.actualizarUltimaPartida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idNivel
	 */
	public JSON obtenerDatosNivel(int idNivel) {
		// TODO - implement Arkanoid.obtenerDatosNivel
		throw new UnsupportedOperationException();
	}

	public JSON getLogros() {
		// TODO - implement Arkanoid.getLogros
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSON getInfoLogro(string nombre) {
		// TODO - implement Arkanoid.getInfoLogro
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 * @param pass
	 */
	public int iniciarSesion(string mail, string pass) {
		// TODO - implement Arkanoid.iniciarSesion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usr
	 * @param mail
	 * @param pass
	 */
	public int registrarse(string usr, string mail, string pass) {
		// TODO - implement Arkanoid.registrarse
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 */
	public int recuperarContrasena(string mail) {
		// TODO - implement Arkanoid.recuperarContrasena
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 * @param pass
	 */
	public void cambiarContrasena(string mail, string pass) {
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
	public void publicarResultados(string redSocial) {
		// TODO - implement Arkanoid.publicarResultados
		throw new UnsupportedOperationException();
	}

}