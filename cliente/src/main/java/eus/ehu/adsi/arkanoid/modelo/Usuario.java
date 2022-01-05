package eus.ehu.adsi.arkanoid.modelo;

import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;

public class Usuario {

	private String email;
	private String contrasena;
	private String atributosPersonalizado;
	private String pathPerfil;
	private String pathMusica;
	private String codigoColorFondo;
	private String codigoColorBola;
	private String codigoColorLadrillo;
	private String codigoColorPaddle;
	private int nivelDefault;
	private Collection<LogroObtenido> susLogros;
	private Collection<Puntuacion> susPuntuaciones;

	public Usuario() {
		// TODO - implement Usuario.Usuario
		throw new UnsupportedOperationException();
	}

	public String getEmail() {
		return email;
	}

	public JSONObject obtenerPersonalizacionUsuario() {
		JSONObject personalizado = new JSONObject();
		personalizado.put("pathMusica", pathMusica);
		personalizado.put("codColorFondo", codigoColorFondo);
		personalizado.put("codColorBola", codigoColorBola);
		personalizado.put("codColorPaddle", codigoColorPaddle);
		personalizado.put("codColorLadrillo", codigoColorLadrillo);
		personalizado.put("atributosPersonalizado", atributosPersonalizado);
		return personalizado;
	}

	/**
	 * 
	 * @param dificultad
	 */
	public JSONObject obtenerRankingPersonal(int dificultad) {
		// TODO - implement Usuario.obtenerRankingPersonal
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public boolean tieneLogro(String nombre) {
		// TODO - implement Usuario.tieneLogro
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSONObject getInfoLogro(String nombre) {
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
	public void actualizarPersonalizacionUsu(String pathMusica, String colorFondo, String colorBola, String colorPaddle, String colorLadrillo, String atributosPersonalizacion) {
		this.pathMusica = pathMusica;
		this.codigoColorFondo = colorFondo;
		this.codigoColorBola = colorBola;
		this.codigoColorLadrillo = colorLadrillo;
		this.codigoColorPaddle = colorPaddle;
		this.atributosPersonalizado = atributosPersonalizacion;
	}

	public boolean isIdentificado() {
		// TODO - implement Usuario.isIdentificado
		throw new UnsupportedOperationException();
	}

	public JSONObject getDatosHistoricosJugador() {
		// TODO - implement Usuario.getDatosHistoricosJugador
		throw new UnsupportedOperationException();
	}

	public JSONObject getLogros() {
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