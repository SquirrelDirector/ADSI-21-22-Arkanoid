package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class Usuario {

	private String nombreUsuario;
	private String email;
	private String atributosPersonalizado;
	private String pathPerfil;
	private String pathMusica;
	private String codigoColorFondo;
	private String codigoColorBola;
	private String codigoColorLadrillo;
	private String codigoColorPaddle;
	private int nivelDefault;
	private ArrayList<LogroObtenido> susLogros;
	private ArrayList<Puntuacion> susPuntuaciones;

	public Usuario() {
		
	}
	
	public Usuario(JSONObject datos) {
		//informacion de la cuenta
		nombreUsuario=datos.getString("nombreUsuario");
		email=datos.getString("email");
		pathPerfil=datos.getString("pathPerfil");
		
		//configuracion del juego
		pathMusica=datos.getString("pathMusica");
		codigoColorFondo=datos.getString("codigoColorFondo");
		codigoColorBola=datos.getString("codigoColorBola");
		codigoColorLadrillo=datos.getString("codigoColorLadrillo");
		codigoColorPaddle=datos.getString("codigoColorPaddle");
		nivelDefault=datos.getInt("nivelDefault");
		atributosPersonalizado=datos.getString("atributosPersonalizado");
		
		//puntuaciones del jugador
		JSONArray ranking=datos.getJSONArray("ranking");
		JSONObject partida;
		int lvl;
		Date fecha;
		int tiempo;
		int num;
		
		for (int i=0;i<ranking.length();i++){
			partida=ranking.getJSONObject(i);
			
			lvl=(int) partida.get("idNivel");
			fecha=(Date) partida.get("valorFechaHora");
			tiempo=(int) partida.get("tiempo");
			num=(int) partida.get("numero");
			
			susPuntuaciones.add(new Puntuacion(this, lvl, num, fecha, tiempo));
		}
		
		//datos de los logros
		JSONArray logros=datos.getJSONArray("logros");
		JSONObject logro;
		String nom;
		int id;
		String desc;
		float prog;
		int obj;
		Logro l;
		LogroObtenido lo;
		
		for (int i=0;i<logros.length();i++){
			logro=logros.getJSONObject(i);
			
			nom=(String) logro.get("nombre");
			id=(int) logro.get("IdLogro");
			desc=(String) logro.get("descripcion");
			obj=(int) logro.get("Objetivo");
			
			l=new Logro(id, nom, desc, obj);
			
			fecha=(Date) logro.get("fechaObtencion");
			prog=(float) logro.get("Progreso");
			
			lo=new LogroObtenido(fecha, l, prog);
			susLogros.add(lo);
			
		}
	}

	public String getEmail() {
		// TODO - implement Usuario.getEmail
		throw new UnsupportedOperationException();
	}

	public JSONObject obtenerPersonalizacionUsuario() {
		// TODO - implement Usuario.obtenerPersonalizacionUsuario
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dificultad
	 */
	public JSONArray obtenerRankingPersonal(int dificultad) {
		// TODO - implement Usuario.obtenerRankingPersonal
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public boolean tieneLogro(String nombre) {
		// TODO - implement Usuario.tieneLogro
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSONObject getInfoLogro(String nombre) {
		// TODO - implement Usuario.getInfoLogro
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pathMusica
	 * @param colorFondo
	 * @param colorBola
	 * @param colorPaddle
	 * @param colorLadrillo
	 */
	public void actualizarPersonalizacionUsu(int pathMusica, int colorFondo, int colorBola, int colorPaddle, int colorLadrillo) {
		// TODO - implement Usuario.actualizarPersonalizacionUsu
		throw new UnsupportedOperationException();
	}

	public boolean isIdentificado() {
		// TODO - implement Usuario.isIdentificado
		throw new UnsupportedOperationException();
	}

	public JSONObject getDatosHistoricosJugador() {
		// TODO - implement Usuario.getDatosHistoricosJugador
		throw new UnsupportedOperationException();
	}

	public JSONArray getLogros() {
		// TODO - implement Usuario.getLogros
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param logro
	 */
	public LogroObtenido buscarLogro(String logro) {
		// TODO - implement Usuario.buscarLogro
		throw new UnsupportedOperationException();
	}

	public Iterator<LogroObtenido> getIterador() {
		// TODO - implement Usuario.getIterador
		throw new UnsupportedOperationException();
	}

	public JSONObject getPerfil() {
		JSONObject perfil = new JSONObject();
		perfil.put("nombre", nombreUsuario);
		perfil.put("foto", pathPerfil);
		return perfil;
	}

}