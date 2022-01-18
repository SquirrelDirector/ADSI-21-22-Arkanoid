package eus.ehu.adsi.arkanoid.controlador;

import eus.ehu.adsi.arkanoid.modelo.*;
import org.json.JSONArray;
import org.json.JSONObject;

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

	private void update() {
		// TODO - implement Arkanoid.update
		throw new UnsupportedOperationException();
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
		usuario = new Usuario("naiara@gmail.com","Naiara07","44,60,4","/imagenesAvatar/Avatar1.png","/sonidoPersonalizar/Sonido1.wav","255,0,0","255,181,0","244,255,0","78,255,0");
		return usuario.obtenerPersonalizacionUsuario();
	}
	
	public JSONObject obtenerDatosUsuario() {
		usuario = new Usuario("naiara@gmail.com","Naiara07","44,60,4","/imagenesAvatar/Avatar1.png","/sonidoPersonalizar/Sonido1.wav","255,0,0","255,181,0","244,255,0","78,255,0");
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
	public JSONObject mostrarRanking(int dificultad, boolean isPersonal) {
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

	public JSONObject getLogros() {
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

	/**
	 * 
	 * @param nombreUsu
	 */
	public boolean comprobarNombre(String nombreUsu) {
		return GestorUsuarios.getGestorUsuario().comprobarNombre(nombreUsu);
	}
}