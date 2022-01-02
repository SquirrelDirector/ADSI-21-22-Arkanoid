package eus.ehu.adsi.arkanoid.modelo;

import org.json.JSONObject;

import java.util.Collection;

public class CatalogoLogros {

	private Collection<Logro> listaLogros;
	private static CatalogoLogros miCatalogoLogros;

	private CatalogoLogros() {
		// TODO - implement CatalogoLogros.CatalogoLogros
		throw new UnsupportedOperationException();
	}

	public static CatalogoLogros getMiCatalogoLogros() {
		return miCatalogoLogros;
	}

	/**
	 * 
	 * @param nombre
	 */
	public boolean buscarLogro(String nombre) {
		// TODO - implement CatalogoLogros.buscarLogro
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSONObject getInfoLogro(String nombre) {
		// TODO - implement CatalogoLogros.getInfoLogro
		throw new UnsupportedOperationException();
	}

}