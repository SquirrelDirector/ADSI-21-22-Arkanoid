package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class Usuario {

	private String email;
	private String nombreUsuario;
	private String contrasena;
	private String atributosPersonalizado;
	private String pathPerfil;
	private String pathMusica;
	private String codigoColorFondo;
	private String codigoColorBola;
	private String codigoColorLadrillo;
	private String codigoColorPaddle;
	private int nivelDefault; //Al cargar las puntuaciones, de mayor a menor!
	private ArrayList<LogroObtenido> susLogros;
	private ArrayList<Puntuacion> susPuntuaciones;

	public Usuario(String email1,String nombreUsuario1, String atributosPersonalizado1,String pathPerfil1,String pathMusica1,String codigoColorFondo1,String codigoColorBola1,
			String codigoColorLadrillo1,String codigoColorPaddle1) {
		this.email = email1;
		this.nombreUsuario = nombreUsuario1;
		this.atributosPersonalizado = atributosPersonalizado1;
		this.pathPerfil = pathPerfil1;
		this.pathMusica = pathMusica1;
		this.codigoColorFondo = codigoColorFondo1;
		this.codigoColorBola = codigoColorBola1;
		this.codigoColorLadrillo = codigoColorLadrillo1;
		this.codigoColorPaddle = codigoColorPaddle1;
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
		return email;
	}
	
	public JSONObject getDatosUsuario() {
		JSONObject datos = new JSONObject();
		datos.put("PathPerfil", pathPerfil);
		datos.put("NombreUsuario", nombreUsuario);
		datos.put("Email", email);
		return datos;
	}

	public JSONObject obtenerPersonalizacionUsuario() {
		JSONObject personalizado = new JSONObject();
		personalizado.put("PathMusica", pathMusica);
		personalizado.put("CodigoFondo", codigoColorFondo);
		personalizado.put("CodigoBola", codigoColorBola);
		personalizado.put("CodigoPaddle", codigoColorPaddle);
		personalizado.put("CodigoLadrillo", codigoColorLadrillo);
		personalizado.put("atributosPersonalizado", atributosPersonalizado);
		return personalizado;
	}

	/**
	 * 
	 * @param dificultad
	 */
	public JSONArray obtenerRankingPersonal(int dificultad) {
		JSONArray ranking = new JSONArray();
		Iterator<Puntuacion> it = susPuntuaciones.iterator();
		while(it.hasNext()){
			Puntuacion p = it.next();
			if (dificultad==0 || dificultad==p.getNivel()){
				JSONObject puntuacion = new JSONObject();
				puntuacion.put("usuario", p.getUsuario());
				puntuacion.put("tiempo", p.getTiempo());
				puntuacion.put("puntuacion", p.getPuntuacion());
				ranking.put(puntuacion);
			}
		}
		return ranking;
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
	public void actualizarPersonalizacionUsu(String pathMusica, String colorFondo, String colorBola, String colorPaddle, String colorLadrillo, String atributosPersonalizacion) {
		this.pathMusica = pathMusica;
		this.codigoColorFondo = colorFondo;
		this.codigoColorBola = colorBola;
		this.codigoColorLadrillo = colorLadrillo;
		this.codigoColorPaddle = colorPaddle;
		this.atributosPersonalizado = atributosPersonalizacion;
	}
	
	/**
	 * 
	 * @param pathAvatar
	 * @param nombreUsu
	 */
	public void actualizarDatosUsu(String pathAvatar, String nombreUsu) {
		this.pathPerfil = pathAvatar;
		this.nombreUsuario = nombreUsu;
	}

	public boolean isIdentificado() {
		return email==null;
	}

	public JSONObject getDatosHistoricosJugador() {
		JSONObject datos = new JSONObject();
		Puntuacion p = susPuntuaciones.get(0);
		int mejorTiempo = p.getTiempo();
		int mejorPuntuacion = p.getPuntuacion();
		datos.put("mejorTiempo", mejorTiempo);
		datos.put("mejorPuntuacion", mejorPuntuacion);
		return datos;
	}

	public JSON getLogros() {
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

	public int getNivelDefault() {
		return nivelDefault;
	}
	
	public JSONObject getPerfil() {
		JSONObject perfil = new JSONObject();
		perfil.put("nombre", nombreUsuario);
		perfil.put("foto", pathPerfil);
		return perfil;
	}
	
	public String[] obtenerDatosNivelPersonalizado() {
		return atributosPersonalizado.split(",");
	}
}