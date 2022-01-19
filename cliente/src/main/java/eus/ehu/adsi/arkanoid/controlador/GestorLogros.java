package eus.ehu.adsi.arkanoid.controlador;

import org.json.JSONObject;

<<<<<<< HEAD
import eus.ehu.adsi.arkanoid.modelo.CatalogoLogros;

=======
>>>>>>> refs/remotes/origin/feature-personalizacion
public class GestorLogros {

	private static GestorLogros miGestorLogros = null;
	CatalogoLogros unCatalogoLogros;
	private GestorLogros() {
		unCatalogoLogros = CatalogoLogros.getMiCatalogoLogros();
	}

	public static GestorLogros getGestorLogros() {
		if (miGestorLogros == null) {
			miGestorLogros = new GestorLogros();
		}
		return miGestorLogros;
	}

	/**
	 * 
	 * @param nombre
	 */
	public boolean buscarLogro(String nombre) {
		
		return unCatalogoLogros.buscarLogro(nombre);
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSONObject getInfoLogro(String nombre) {
<<<<<<< HEAD
		return unCatalogoLogros.getInfoLogro(nombre);
=======
		// TODO - implement GestorLogros.getInfoLogro
		throw new UnsupportedOperationException();
>>>>>>> refs/remotes/origin/feature-personalizacion
	}

}