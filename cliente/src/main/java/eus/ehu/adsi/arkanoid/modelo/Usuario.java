package eus.ehu.adsi.arkanoid.modelo;

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
	private Coleccion<LogroObtenido> susLogros;
	private Coleccion<Puntuacion> susPuntuaciones;

	public Usuario() {
		// TODO - implement Usuario.Usuario
		throw new UnsupportedOperationException();
	}

	public String getEmail() {
		// TODO - implement Usuario.getEmail
		throw new UnsupportedOperationException();
	}

	public String[] obtenerPersonalizacionUsuario() {
		String[] Datos = new String[4];
		Datos[1]=this.codigoColorFondo;
		Datos[2]=this.codigoColorBola;
		Datos[3]=this.codigoColorLadrillo;
		Datos[4]=this.codigoColorPaddle;
		return Datos;
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
		// TODO - implement Usuario.isIdentificado
		throw new UnsupportedOperationException();
	}

	public JSON getDatosHistoricosJugador() {
		// TODO - implement Usuario.getDatosHistoricosJugador
		throw new UnsupportedOperationException();
	}

	public JSON getLogros() {
		// TODO - implement Usuario.getLogros
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param logro
	 */
	public LogroObtenido buscarLogro(string logro) {
		// TODO - implement Usuario.buscarLogro
		throw new UnsupportedOperationException();
	}

	public Iterator<LogroObtenido> getIterador() {
		// TODO - implement Usuario.getIterador
		throw new UnsupportedOperationException();
	}

	public int getNivelDefault() {
		return nivelDefault;
	}
	
	public String[] obtenerDatosNivelPersonalizado() {
		return atributosPersonalizado.split(",");
		}
}