package eus.ehu.adsi.arkanoid.controlador;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public class GestorPuntuaciones {

	private static GestorPuntuaciones miGestorPuntuaciones;

	private GestorPuntuaciones() {
		// TODO - implement GestorPuntuaciones.GestorPuntuaciones
		throw new UnsupportedOperationException();
	}

	public static GestorPuntuaciones getGestorPuntuaciones() {
		// TODO - implement GestorPuntuaciones.getGestorPuntuaciones
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dificultad
	 */
	public JSONArray obtenerRanking(int dificultad) {
		JSONArray ranking = new JSONArray();
		try {
			if(dificultad==0){
				ResultSet resultado = GestorDB.getGestorDB().execSQL("SELECT nombreUsuario, Tiempo, Numero FROM Puntuacion ORDER BY Numero ASC");
				if (resultado != null){
					int columnas = resultado.getMetaData().getColumnCount();
					int i = 1;
					while (resultado.next()){
						JSONObject puntuacion = new JSONObject();
						while (i <= columnas){
							if (i == 1) puntuacion.put("usuario", resultado.getString(i));
							else if (i == 2) puntuacion.put("tiempo", resultado.getInt(i));
							else if (i == 3) puntuacion.put("puntuacion", resultado.getInt(i));
							i++;
						}
						ranking.put(puntuacion);
						i = 1;
					}
					resultado.close();
				}
			}
			else{
				ResultSet resultado = GestorDB.getGestorDB().execSQL("SELECT nombreUsuario, Tiempo, Numero FROM Puntuacion WHERE idNivel =="+ dificultad +" ORDER BY Numero ASC");
				if (resultado != null){
					int columnas = resultado.getMetaData().getColumnCount();
					int i = 1;
					while (resultado.next()){
						JSONObject puntuacion = new JSONObject();
						while (i <= columnas){
							if (i == 1) puntuacion.put("usuario", resultado.getString(i));
							else if (i == 2) puntuacion.put("tiempo", resultado.getInt(i));
							else if (i == 3) puntuacion.put("puntuacion", resultado.getInt(i));
							i++;
						}
						ranking.put(puntuacion);
						i = 1;
					}
					resultado.close();
				}
			}
			
		} catch (SQLException e){e.printStackTrace(); System.out.println("No se han podido obtener las puntuaciones.");} finally {
			GestorDB.getGestorDB().cerrarConexion();
		}
		return ranking;
	}

}