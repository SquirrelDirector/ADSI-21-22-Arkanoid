package eus.ehu.adsi.arkanoid.modelo;

import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;

public class Usuario {

	private String email;
	private String nombreUsuario;
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

	public Usuario(String email1,String nombreUsuario1, String atributosPersonalizado1,String pathPerfil1,String pathMusica1,String codigoColorFondo1,String codigoColorBola1,
			String codigoColorLadrillo1,String codigoColorPaddle1) {
		this.email = email1;
		this.nombreUsuario = nombreUsuario1;
		this.atributosPersonalizado = atributosPersonalizado1;
		this.pathPerfil = pathPerfil1;
		this.pathMusica = pathMusica1;
		this.codigoColorFondo = codigoColorFondo1;
		this.codigoColorBola = codigoColorBola1;
		this.codigoColorLadrillo = codigoColorLadrillo1;
		this.codigoColorPaddle = codigoColorPaddle1;
	}

	public String getEmail() {
		return email;
	}
	
	public JSONObject getDatosUsuario() {
		JSONObject datos = new JSONObject();
		datos.put("PathPerfil", pathPerfil);
		datos.put("NombreUsuario", nombreUsuario);
		datos.put("Email", email);
		return datos;
	}

	public JSONObject obtenerPersonalizacionUsuario() {
		JSONObject personalizado = new JSONObject();
		personalizado.put("PathMusica", pathMusica);
		personalizado.put("CodigoFondo", codigoColorFondo);
		personalizado.put("CodigoBola", codigoColorBola);
		personalizado.put("CodigoPaddle", codigoColorPaddle);
		personalizado.put("CodigoLadrillo", codigoColorLadrillo);
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
	
	/**
	 * 
	 * @param pathAvatar
	 * @param nombreUsu
	 */
	public void actualizarDatosUsu(String pathAvatar, String nombreUsu) {
		this.pathPerfil = pathAvatar;
		this.nombreUsuario = nombreUsu;
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