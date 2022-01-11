package eus.ehu.adsi.arkanoid.modelo;

import java.util.Date;

import java.util.ArrayList;
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
	private ArrayList<LogroObtenido> susLogros;
	private Coleccion<Puntuacion> susPuntuaciones;

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
	public JSON obtenerRankingPersonal(int dificultad) {
		// TODO - implement Usuario.obtenerRankingPersonal
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public boolean tieneLogro(String nombre) {
		for (LogroObtenido i : susLogros) {
			if (i.esLogro(nombre))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSONObject getInfoLogro(String nombre) {
		JSONObject infoLogro = new JSONObject();
		Iterator<LogroObtenido> itr = this.getIterador();
		boolean enc = false;
		if (tieneLogro(nombre)) {
			while (itr.hasNext() && !enc) {
				LogroObtenido unLogro = itr.next();
				if (unLogro.getNombre().equals(nombre)) {
					enc = true;
					infoLogro.put("nombre", unLogro.getNombre());
					infoLogro.put("descripcion", unLogro.getDescripcion());
					infoLogro.put("fechaObtencion", unLogro.getFechaObtencion());
					infoLogro.put("progreso", (double) unLogro.getProgreso());
				}
			}
		} else {
			LogroObtenido unLogro = buscarLogro(nombre);
			infoLogro.put("nombre", nombre);
			infoLogro.put("descripcion", unLogro.getDescripcion());
		}
		return infoLogro;
	}

	/**
	 * 
	 * @param pathMusica
	 * @param colorFondo
	 * @param colorBola
	 * @param colorPaddle
	 * @param colorLadrillo
	 */
	public void actualizarPersonalizacionUsu(int pathMusica, int colorFondo, int colorBola, int colorPaddle,
			int colorLadrillo) {
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

	public JSONArray getLogros() {
		JSONArray logros = new JSONArray();
		for (LogroObtenido logro : susLogros) {
			JSONObject infoLogro = new JSONObject();
			infoLogro.put("nombre", logro.getNombre());
			infoLogro.put("descripcion", logro.getDescripcion());
			infoLogro.put("fechaObtencion", logro.getFechaObtencion());
			infoLogro.put("progreso", (double) logro.getProgreso());
			logros.put(infoLogro);
		}
		return logros;
	}

	/**
	 * 
	 * @param logro
	 */
	public LogroObtenido buscarLogro(String logro) {
		for (LogroObtenido i : susLogros) {
			if (i.esLogro(logro))
				return i;
		}
		return null;
	}

	public Iterator<LogroObtenido> getIterador() {
		return this.susLogros.iterator();
	}

	public JSONArray cotejarLogros(JSONArray logros) {
		JSONArray nuevosLogros = new JSONArray();
		// TODO comparar los logros y actualizar progresos de logros
		for (int i = 0; i < logros.length(); i++) {
			JSONObject unLogro = logros.getJSONObject(i);					
			String nombreLogro = unLogro.getString("nombre");
			
			JSONObject logroUsuario = this.getInfoLogro(nombreLogro);
			double progresoUsuario = logroUsuario.getDouble("progreso");
			
			if (logroUsuario.getString("fechaObtencion")==null) {// esto quiere decir que el usuario no tiene este logro obtenido
				nuevosLogros.put(unLogro);
				//Anadimos el nuevo logro obtenido en partida a la lista de logros del usuario
				Logro nuevoLogro = CatalogoLogros.getMiCatalogoLogros().getLogro(nombreLogro);
				LogroObtenido nuevoLogroObtenido = new LogroObtenido(new Date(),nuevoLogro,1);
				susLogros.add(nuevoLogroObtenido);
			} else if (progresoUsuario < 1) {
				this.actualizarProgreso(1, nombreLogro);
			}
		}
		return nuevosLogros;

	}
	public void actualizarProgreso(int num, String logro) {
		Iterator<LogroObtenido> itr = susLogros.iterator();
		boolean enc = false;
		while(itr.hasNext() && !enc) {
			LogroObtenido unLogroObtenido = itr.next();
			if(unLogroObtenido.esLogro(logro)) {
				enc = true;
				unLogroObtenido.actualizarProgreso(num);
			}
			
		}
	}
}