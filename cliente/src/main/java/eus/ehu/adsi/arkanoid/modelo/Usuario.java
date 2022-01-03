package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class Usuario {

	private String email;
	private String contrasena;
	private Coleccion<T> atributosPersonalizado;
	private String pathPerfil;
	private String pathMusica;
	private String codigoColorFondo;
	private String codigoColorBola;
	private String codigoColorLadrillo;
	private String codigoColorPaddle;
	private int nivelDefault;
	private Coleccion<LogroObtenido> susLogros;
	private ArrayList<Puntuacion> susPuntuaciones;

	public Usuario() {
		// TODO - implement Usuario.Usuario
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
	public <susPuntuaciones> JSONArray obtenerRankingPersonal(int dificultad) {
		JSONArray ranking = new JSONArray();
		Iterator<Puntuacion> it = susPuntuaciones.iterator();
		while(it.hasNext()){
			Puntuacion p = it.next();
			if (dificultad==0 || dificultad==p.getNivel()){
				JSONObject puntuacion = new JSONObject();
				puntuacion.put("usuario", p.getUsuario());
				puntuacion.put("tiempo", p.getTiempo());
				puntuacion.put("puntuacion", p.getPuntuacion());
				ranking.put(puntuacion);
			}
		}
		return ranking;
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

}