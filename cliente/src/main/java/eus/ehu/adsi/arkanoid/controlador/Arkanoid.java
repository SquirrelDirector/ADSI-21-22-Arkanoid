package eus.ehu.adsi.arkanoid.controlador;

import javax.swing.*;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.*;
import java.util.Observable;

import eus.ehu.adsi.arkanoid.modelo.*;

public class Arkanoid extends Observable {

	private static Arkanoid miArkanoid;
	/**
	 * Game variables
	 */
	/*private Game game;
	private Partida partida = new Partida();
	private double lastFt;
	private double currentSlice;*/
	private Usuario usuario;

	private Arkanoid() {
		
	}

	public static Arkanoid getArkanoid() {
		if (miArkanoid==null)
			miArkanoid=new Arkanoid();
		return miArkanoid;
	}

	private void update() {
		// TODO - implement Arkanoid.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param emailUsuario
	 */
	public JSONObject obtenerPersonalizacion(int emailUsuario) {
		// TODO - implement Arkanoid.obtenerPersonalizacion
		throw new UnsupportedOperationException();
	}

	public JSONObject obtenerPersonalizacionUsuario() {
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
		// TODO - implement Arkanoid.mostrarRanking
		throw new UnsupportedOperationException();
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
	public JSONObject obtenerDatosNivel(int idNivel) {
		// TODO - implement Arkanoid.obtenerDatosNivel
		throw new UnsupportedOperationException();
	}

	public JSONArray getLogros() {
		// TODO - implement Arkanoid.getLogros
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSONObject getInfoLogro(String nombre) {
		// TODO - implement Arkanoid.getInfoLogro
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 * @param pass
	 */
	public int iniciarSesion(String mail, String pass) {
		if (mail.equals(""))//TODO - filtrar por expresiones regulares
			return 1;
		if (pass.equals(""))
			return 2;
		
		pass=cifrar(pass); //se hace en una función separada para facilitar la posibilidad de cambiar el algoritmo de resumen
		
		JSONObject datos=GestorUsuarios.getGestorUsuario().importarUsuario(mail, pass);
		if (datos==null)
			return 3;
		
		usuario=new Usuario(datos);
		notifyObservers(usuario.getPerfil());
		return 0;
	}
	
	private String cifrar(String pass) {
		String hash=pass.hashCode()+"";
		return hash;
	}

	public void cerrarSesion(){
		if (!usuario.isIdentificado()) //caso a priori imposible de suceder
			notifyObservers(usuario.getPerfil());
		usuario=new Usuario();
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
		// TODO - implement Arkanoid.getResultadosPartida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param datosPartida
	 * @param datosHistoricos
	 */
	private void agregarJSON(JSONObject datosPartida, JSONObject datosHistoricos) {
		// TODO - implement Arkanoid.agregarJSON
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

}