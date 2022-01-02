package eus.ehu.adsi.arkanoid.controlador;

import eus.ehu.adsi.arkanoid.modelo.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

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

	public JSONObject obtenerPersonalizables() {
		JSONObject resultado = new JSONObject();
		resultado.put("colores", obtenerTodosPersonalizablesPorTabla("Color"));
		resultado.put("sonidos", obtenerTodosPersonalizablesPorTabla("Audio"));
		return resultado;
	}

	private JSONArray obtenerTodosPersonalizablesPorTabla(String tabla) {
		JSONArray opciones = new JSONArray();
		try {
			ResultSet resconsulta = GestorDB.getGestorDB().execSQL("SELECT * FROM "+ tabla);
			if (resconsulta != null) {
				int columnas = resconsulta.getMetaData().getColumnCount();
				int i = 1;
				while (resconsulta.next()) {
					JSONObject informacion = new JSONObject();
					while (i <= columnas) {
						if (i == 1) informacion.put("id", resconsulta.getString(i));
						else if (i == 2) informacion.put("nombre", resconsulta.getString(i));
						i++;
					}
					opciones.put(informacion);
					i = 1;
				}
				resconsulta.close();
			}
		} catch (SQLException e){e.printStackTrace(); System.out.println("No se han podido obtener los personalizables");} finally {
			GestorDB.getGestorDB().cerrarConexion();
		}
		return opciones;
	}

	public JSONObject obtenerPersonalizacionUsuario() {
		return usuario.obtenerPersonalizacionUsuario();
	}

	/**
	 * 
	 * @param pathMusica
	 * @param colorFondo
	 * @param colorBola
	 * @param colorPaddle
	 * @param colorLadrillo
	 */
	public void actualizarPersonalizacionDB(String pathMusica, String colorFondo, String colorBola, String colorPaddle, String colorLadrillo) {
		String email = usuario.getEmail();
		GestorUsuarios.getGestorUsuario().actualizarPersonalizacion(email, pathMusica, colorFondo, colorBola, colorPaddle, colorLadrillo);
	}

	/**
	 * 
	 * @param pathMusica
	 * @param colorFondo
	 * @param colorBola
	 * @param colorPaddle
	 * @param colorLadrillo
	 */
	public void actualizarPersonalizacionUsu(String pathMusica, String colorFondo, String colorBola, String colorPaddle, String colorLadrillo) {
		usuario.actualizarPersonalizacionUsu(pathMusica, colorFondo, colorBola, colorPaddle, colorLadrillo);
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

}