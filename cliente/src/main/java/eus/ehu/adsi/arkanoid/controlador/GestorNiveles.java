package eus.ehu.adsi.arkanoid.controlador;

import packCodigo.GestorBD;

public class GestorNiveles {

	private static GestorNiveles miGestorNiveles;

	private GestorNiveles() {
		// TODO - implement GestorNiveles.GestorNiveles
		throw new UnsupportedOperationException();
	}

	public static GestorNiveles getGestorNiveles() {
		// TODO - implement GestorNiveles.getGestorNiveles
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idNivel
	 */
	public JSONArray obtenerDatosNivel(int idNivel) {
		JSONARRAY Nivel = GestorDB.getGestorDB().execSQL2("SELECT Velocidad, Anchura, Aceleración, Num_Ladrillos FROM Nivel WHERE idNivel=%int%");
		return Nivel;
	}
	

}