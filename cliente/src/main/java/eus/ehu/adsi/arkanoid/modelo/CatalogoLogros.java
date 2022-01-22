package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

public class CatalogoLogros {

	private ArrayList<Logro> listaLogros;
	private static CatalogoLogros miCatalogoLogros = null;

	private CatalogoLogros() {
		this.listaLogros = new ArrayList<Logro>();
	}

	public static CatalogoLogros getMiCatalogoLogros() {
		if (miCatalogoLogros == null) {
			miCatalogoLogros = new CatalogoLogros();
		}
		return miCatalogoLogros;
	}

	/**
	 * 
	 * @param nombre
	 */
	public boolean buscarLogro(String nombre) {
		boolean encontrado = false;
		for (Logro unLogro : listaLogros) {
			if (unLogro.getNombre().equals(nombre))
				encontrado = true;
		}
		return encontrado;
	}
	public Logro getLogro(String nombre) {		
		for (Logro unLogro : listaLogros) {
			if (unLogro.getNombre().equals(nombre))
				return unLogro;
		}
		return null;
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSONObject getInfoLogro(String nombre) { 
		/* Este metodo creo que se me colo en el diagrama de clases por la primera version del diagrama de secuencia
			y al cambiarlo a la segunda version se me olvido borrarlo, pero lo dejo implementado por si acaso pero no le veo utilidad xd */
		Iterator<Logro> itr = listaLogros.iterator();
		boolean encontrado = false;
		JSONObject infoLogro = new JSONObject();
		while (itr.hasNext() && !encontrado) {
			Logro unLogro = itr.next();
			if (unLogro.getNombre().equals(nombre)) {
				infoLogro.put("nombre", unLogro.getNombre());
				infoLogro.put("descripcion", unLogro.getDescripcion());
				infoLogro.put("objetivo",unLogro.getObjetivo());
				encontrado = true;
			}
		}
		return infoLogro;
	}
	public void addLogro(Logro pLogro) {
		this.listaLogros.add(pLogro);
	}

}