package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

public class Usuario {

	private String email;
	private String contrasena;
	private ArrayList atributosPersonalizado;
	private String pathPerfil;
	private String pathMusica;
	private String codigoColorFondo;
	private String codigoColorBola;
	private String codigoColorLadrillo;
	private String codigoColorPaddle;
	private int nivelDefault;
	private ArrayList<LogroObtenido> susLogros;
	private ArrayList<Puntuacion> susPuntuaciones;
	
	//NUEVO!
	private int mejorTiempo;
	private int mejorPuntuacion;
	

	public Usuario() {
		throw new UnsupportedOperationException();
	}

	public String getEmail() {
		// TODO - implement Usuario.getEmail
		throw new UnsupportedOperationException();
	}

	public JSON obtenerPersonalizacionUsuario() {
		// TODO - implement Usuario.obtenerPersonalizacionUsuario
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dificultad
	 */
	public JSON obtenerRankingPersonal(int dificultad) {
		// TODO - implement Usuario.obtenerRankingPersonal
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public boolean tieneLogro(string nombre) {
		// TODO - implement Usuario.tieneLogro
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSON getInfoLogro(String nombre) {
		// TODO - implement Usuario.getInfoLogro
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
		// TODO - implement Usuario.actualizarPersonalizacionUsu
		throw new UnsupportedOperationException();
	}

	public boolean isIdentificado() {
		return email!=null;
	}

	public JSONObject getDatosHistoricosJugador() {
		JSONObject json = new JSONObject();
		json.put("mejorTiempo", mejorTiempo);
		json.put("mejorPuntuacion", mejorPuntuacion);
		return json;
	}

	public JSON getLogros() {
		// TODO - implement Usuario.getLogros
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param logro
	 */
	public LogroObtenido buscarLogro(String logro) {
		// TODO - implement Usuario.buscarLogro
		throw new UnsupportedOperationException();
	}

	public Iterator<LogroObtenido> getIterador() {
		// TODO - implement Usuario.getIterador
		throw new UnsupportedOperationException();
	}

}