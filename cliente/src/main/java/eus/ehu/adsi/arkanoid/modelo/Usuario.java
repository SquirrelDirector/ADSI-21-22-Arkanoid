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
		for (LogroObtenido i : susLogros) {
			if (i.esLogro(nombre))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSONObject getInfoLogro(String nombre) {
		JSONObject infoLogro = new JSONObject();
		Iterator<LogroObtenido> itr = this.getIterador();
		boolean enc = false;
		if (tieneLogro(nombre)) {
			while (itr.hasNext() && !enc) {
				LogroObtenido unLogro = itr.next();
				if (unLogro.getNombre().equals(nombre)) {
					enc = true;
					infoLogro.put("nombre", unLogro.getNombre());
					infoLogro.put("descripcion", unLogro.getDescripcion());
					infoLogro.put("fechaObtencion", unLogro.getFechaObtencion());
					infoLogro.put("progreso", (double) unLogro.getProgreso());
				}
			}
		} else {
			LogroObtenido unLogro = buscarLogro(nombre);
			infoLogro.put("nombre", nombre);
			infoLogro.put("descripcion", unLogro.getDescripcion());
		}
		return infoLogro;
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
		return email!=null;
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

	public JSONArray getLogros() {
		JSONArray logros = new JSONArray();
		for (LogroObtenido logro : susLogros) {
			JSONObject infoLogro = new JSONObject();
			infoLogro.put("nombre", logro.getNombre());
			infoLogro.put("descripcion", logro.getDescripcion());
			infoLogro.put("fechaObtencion", logro.getFechaObtencion());
			infoLogro.put("progreso", (double) logro.getProgreso());
			logros.put(infoLogro);
		}
		return logros;
	}

	/**
	 * 
	 * @param logro
	 */
	public LogroObtenido buscarLogro(String logro) {
		for (LogroObtenido i : susLogros) {
			if (i.esLogro(logro))
				return i;
		}
		return null;
	}

	public Iterator<LogroObtenido> getIterador() {
		return this.susLogros.iterator();
	}
	
	public void actualizarUltimaPartida(int idNivel) {
		this.nivelDefault=idNivel;
	}

	public JSONArray cotejarLogros(JSONArray logros) {
		JSONArray nuevosLogros = new JSONArray();
		// TODO comparar los logros y actualizar progresos de logros
		for (int i = 0; i < logros.length(); i++) {
			JSONObject unLogro = logros.getJSONObject(i);					
			String nombreLogro = unLogro.getString("nombre");
			
			JSONObject logroUsuario = this.getInfoLogro(nombreLogro);
			double progresoUsuario = logroUsuario.getDouble("progreso");
			
			if (logroUsuario.getString("fechaObtencion")==null) {// esto quiere decir que el usuario no tiene este logro obtenido
				nuevosLogros.put(unLogro);
				//Anadimos el nuevo logro obtenido en partida a la lista de logros del usuario
				Logro nuevoLogro = CatalogoLogros.getMiCatalogoLogros().getLogro(nombreLogro);
				LogroObtenido nuevoLogroObtenido = new LogroObtenido(new Date(),nuevoLogro,1);
				susLogros.add(nuevoLogroObtenido);
			} else if (progresoUsuario < 1) {
				this.actualizarProgreso(1, nombreLogro);
			}
		}
		return nuevosLogros;

	}
	
	public void actualizarProgreso(int num, String logro) {
		Iterator<LogroObtenido> itr = susLogros.iterator();
		boolean enc = false;
		while(itr.hasNext() && !enc) {
			LogroObtenido unLogroObtenido = itr.next();
			if(unLogroObtenido.esLogro(logro)) {
				enc = true;
				unLogroObtenido.actualizarProgreso(num);
			}
			
		}
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
	
	public void actualizarUltimaPartida(int idNivel) {
        this.nivelDefault=idNivel;
    }
}