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
	public JSON obtenerDatosNivel(int idNivel) {
		ResultadoSQL rs = GestorDB.getGestorDB().execSQL2("SELECT Velocidad, Anchura, Aceleración, Num_Ladrillos FROM Nivel WHERE idNivel=%int%");
		try {
			rs.next();
			int Velocidad = rs.getInt("Velocidad");
			int Anchura = rs.getInt("Anchura");
			int Aceleración = rs.getInt("Aceleración");
			int NumLadrillos = rs.getInt("NumLadrillos");
		}
		return rs;
	}
	

}