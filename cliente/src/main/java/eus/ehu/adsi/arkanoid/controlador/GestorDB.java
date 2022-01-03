package eus.ehu.adsi.arkanoid.controlador;

import java.sql.ResultSet;

public class GestorDB {

	private static GestorDB miGestorDB;

	private GestorDB() {}

	public static GestorDB getGestorDB() {
		if (miGestorDB == null) {
			miGestorDB = new GestorDB();
		}
		return miGestorDB;
	}

	public ResultSet execSQL(String sentencia) {
		return null;
	}

	public void ejecutarCambio(String sentencia) {

	}

	public void cerrarConexion() {

	}
}