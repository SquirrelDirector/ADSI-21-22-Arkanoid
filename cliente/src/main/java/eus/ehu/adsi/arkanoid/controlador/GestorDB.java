package eus.ehu.adsi.arkanoid.controlador;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorDB {

	private static GestorDB miGestorDB;

	private GestorDB() {}

	public static GestorDB getGestorDB() {
		if (miGestorDB == null) {
			miGestorDB = new GestorDB();
		}
		return miGestorDB;
	}

	public ResultadoSQL execSQL(String sentencia) throws SQLException {
		return null;
	}

	public void ejecutarCambio(String sentencia) {

	}

	public void cerrarConexion() {

	}
}