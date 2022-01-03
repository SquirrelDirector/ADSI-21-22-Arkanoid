package eus.ehu.adsi.arkanoid.controlador;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public class GestorPuntuaciones {

	private static GestorPuntuaciones miGestorPuntuaciones;

	private GestorPuntuaciones() {
	}

	public static GestorPuntuaciones getGestorPuntuaciones() {
		if (miGestorPuntuaciones==null){
			miGestorPuntuaciones = new GestorPuntuaciones();
		}
		return miGestorPuntuaciones;
	}

	/**
	 * 
	 * @param dificultad
	 */
	public JSONArray obtenerRanking(int dificultad) {
		JSONArray ranking = new JSONArray();
		try {
			ResultadoSQL resultado = null;
			if(dificultad==0){
				ResultadoSQL resultado = GestorDB.getGestorDB().execSQL("SELECT NombreUsuario, Tiempo, Numero FROM Puntuacion ORDER BY Numero ASC");
			}
			else{
				ResultadoSQL resultado = GestorDB.getGestorDB().execSQL("SELECT nombreUsuario, Tiempo, Numero FROM Puntuacion WHERE idNivel =="+ dificultad +" ORDER BY Numero ASC");
			}
			if (resultado != null){
				while (resultado.hasNext()){
					JSONObject puntuacion = new JSONObject();
					puntuacion.put("usuario", (String)resultado.get("NombreUsuario"));
					puntuacion.put("tiempo", (String)resultado.get("Tiempo"));
					puntuacion.put("puntuacion", (Integer)resultado.get("Numero"));
					ranking.put(puntuacion);
				}
				resultado.close();
			}
			
		} catch (SQLException e){e.printStackTrace(); System.out.println("No se han podido obtener las puntuaciones.");} finally {
			GestorDB.getGestorDB().cerrarConexion();
		}
		return ranking;
	}
}