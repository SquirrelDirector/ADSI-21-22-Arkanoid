package eus.ehu.adsi.arkanoid.controlador;

public class GestorDB {

	private static GestorDB miGestorDB;

	private GestorDB() {}

	public static GestorDB getGestorDB() {
		if (miGestorDB == null) {
			miGestorDB = new GestorDB();
		}
		return miGestorDB;
	}

	public ResultadoSQL execSQL() {
		// TODO - implement GestorDB.execSQL
		throw new UnsupportedOperationException();
	}

	public void ejecutarCambio(String sentencia) {

	}
}