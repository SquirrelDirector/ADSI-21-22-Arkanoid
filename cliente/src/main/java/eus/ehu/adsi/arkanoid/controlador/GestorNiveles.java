package eus.ehu.adsi.arkanoid.controlador;

import org.json.JSONObject;

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
	public String[] obtenerDatosNivel(int idNivel) {
		JSONObject Nivel = GestorDB.getGestorDB().execSQL("SELECT Velocidad, Anchura, Num_Ladrillos FROM Nivel WHERE idNivel=%int%");
		String[] DatosNivel=new String[3];
		int i;
		for (i=1;i<4;i++) {
			Nivel.get("Velocidad")=DatosNivel[1];
			Nivel.get("Anchura")=DatosNivel[2];
			Nivel.get("Num_Ladrillos")=DatosNivel[3];
		}
		return DatosNivel;
	}

}