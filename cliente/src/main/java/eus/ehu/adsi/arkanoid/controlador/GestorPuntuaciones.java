package eus.ehu.adsi.arkanoid.controlador;

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
		ResultadoSQL resultado = null;
		String preg;
		if(dificultad==0){
			preg="SELECT NombreUsuario, Tiempo, Numero FROM Puntuacion ORDER BY Numero DESC,ValorFechaHora, NombreUsuario ASC";
			
		}
		else{
			preg="SELECT NombreUsuario, Tiempo, Numero FROM Puntuacion WHERE idNivel ="+dificultad+" ORDER BY Numero DESC, ValorFechaHora, NombreUsuario ASC";
		}
		resultado = GestorDB.getGestorDB().execSQL(preg);
		if (resultado != null){
			while (resultado.hasNext()){
				JSONObject puntuacion = new JSONObject();
				puntuacion.put("usuario", (String)resultado.get("NombreUsuario"));
				puntuacion.put("tiempo", Integer.parseInt((String) resultado.get("Tiempo")));
				puntuacion.put("puntuacion", Integer.parseInt((String) resultado.get("Numero")));
				ranking.put(puntuacion);
				resultado.next();
			}
			
		}
		return ranking;
	}
}