package eus.ehu.adsi.arkanoid.modelo;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.GestorDB;

public class Usuario {

	private String email;
	private String nombreUsuario;
	private String contrasena;
	private String atributosPersonalizado = Config.atributosPersonalizado;
	private String pathPerfil = Config.PATH_PERFIL;
	private String pathMusica = Config.PATH_MUSICA;
	private String codigoColorFondo = Config.BACKGROUND_COLOR;
	private String codigoColorBola = Config.BALL_COLOR;
	private String codigoColorLadrillo = Config.BRICK_COLOR;
	private String codigoColorPaddle = Config.PADDLE_COLOR;
	private int nivelDefault;
	private ArrayList<LogroObtenido> susLogros = new ArrayList<LogroObtenido>();
	private ArrayList<Puntuacion> susPuntuaciones = new ArrayList<>(); // Al cargar las puntuaciones, de mayor a menor!

	public Usuario() {

	}

	public Usuario(JSONObject datos) {
		// informacion de la cuenta
		nombreUsuario = datos.getString("nombreUsuario");
		email = datos.getString("email");
		pathPerfil = datos.getString("pathPerfil");

		// configuracion del juego
		pathMusica = datos.getString("pathMusica");
		codigoColorFondo = datos.getString("codigoColorFondo");
		codigoColorBola = datos.getString("codigoColorBola");
		codigoColorLadrillo = datos.getString("codigoColorLadrillo");
		codigoColorPaddle = datos.getString("codigoColorPaddle");
		nivelDefault = datos.getInt("nivelDefault");
		atributosPersonalizado = datos.getString("atributosPersonalizado");

		// puntuaciones del jugador
		JSONArray ranking = datos.getJSONArray("ranking");
		JSONObject partida;
		int lvl;
		Date fecha;
		int tiempo;
		int num;

		for (int i = 0; i < ranking.length(); i++) {
			partida = ranking.getJSONObject(i);
			lvl = Integer.parseInt((String)partida.get("idNivel"));
			fecha = new Date();
			try {
				fecha = new SimpleDateFormat("yyyy-MM-dd").parse((String) partida.get("valorFechaHora"));
			} catch (JSONException | ParseException e) {
			}
			tiempo = Integer.parseInt((String)partida.get("tiempo"));
			num = Integer.parseInt((String) partida.get("numero"));

			susPuntuaciones.add(new Puntuacion(this, lvl, num, fecha.toString(), tiempo));
		}

		// datos de los logros
		susLogros = new ArrayList<>();
		JSONArray logros = datos.getJSONArray("logros");
		JSONObject logro;
		String nom;
		int id;
		String desc;
		int prog;
		int obj;
		Logro l;
		LogroObtenido lo;

		for (int i = 0; i < logros.length(); i++) {
			logro = logros.getJSONObject(i);

			nom = (String) logro.get("nombre");
			id = Integer.parseInt((String) logro.get("idLogro"));
			desc = (String) logro.get("descripcion");
			obj = Integer.parseInt((String) logro.get("objetivo"));

			l = new Logro(id, nom, desc, obj);
			CatalogoLogros catL = CatalogoLogros.getMiCatalogoLogros();
			catL.addLogro(l);
			fecha = null;
			if (!logro.isNull("fechaObtencion"))
				try {
					fecha = new SimpleDateFormat("yyyy-MM-dd").parse((String) logro.get("fechaObtencion"));
				} catch (JSONException | ParseException e) {
				}

			prog = Integer.parseInt((String) logro.get("progreso"));
			lo = new LogroObtenido(fecha, l, prog);
			susLogros.add(lo);

		}
	}

	public String getEmail() {
		return email;
	}

	public String getNombreUsuario(){
		return nombreUsuario;
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
		while (it.hasNext()) {
			Puntuacion p = it.next();
			if (dificultad == 0 || dificultad == p.getNivel()) {
				JSONObject puntuacion = new JSONObject();
				puntuacion.put("usuario", p.getUsuario().getNombreUsuario());
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

		while (itr.hasNext() && !enc) {
			LogroObtenido unLogro = itr.next();
			if (unLogro.getNombre().equals(nombre)) {

				enc = true;
				infoLogro.put("nombre", unLogro.getNombre());
				infoLogro.put("descripcion", unLogro.getDescripcion());
				infoLogro.put("fechaObtencion", unLogro.getFechaObtencion());
				infoLogro.put("progreso", unLogro.getProgreso());
			}
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
	public void actualizarPersonalizacionUsu(String pathMusica, String colorFondo, String colorBola, String colorPaddle,
			String colorLadrillo, String atributosPersonalizacion) {
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
	
	/**
	 * Comprueba si el usuario está identificado
	 * @return true si está identificado, false en caso contrario
	 */
	public boolean isIdentificado() {
		return email != null;
	}

	/**
	 * Se obtienen los datos históricos del jugador. Esto es, mejor tiempo y mejor puntuación
	 * @return JSON con los datos
	 */
	public JSONObject getDatosHistoricosJugador() {
		JSONObject datos = new JSONObject();
		int mejorTiempo = Integer.MAX_VALUE;
		int mejorPuntuacion = 0;
		if (susPuntuaciones.size()>0){
			Puntuacion p = susPuntuaciones.get(0);
			mejorTiempo = p.getTiempo();
			
			Iterator<Puntuacion> it = susPuntuaciones.iterator();
			p = it.next();
			mejorPuntuacion = p.getTiempo();
			while(it.hasNext()){
				p = it.next();
				if(p.getTiempo()>mejorPuntuacion){
					mejorPuntuacion = p.getTiempo();
				}
			}
		}
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
			infoLogro.put("progreso", logro.getProgreso());
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
		this.nivelDefault = idNivel;
	}

	public JSONArray cotejarLogros(JSONArray logros) throws ParseException {
		JSONArray nuevosLogros = new JSONArray();
		for (int i = 0; i < logros.length(); i++) {
			JSONObject unLogro = logros.getJSONObject(i);
			String nombreLogro = unLogro.getString("nombre");
			JSONObject logroUsuario = this.getInfoLogro(nombreLogro);
			int progresoUsuario = logroUsuario.getInt("progreso");
			System.out.println(nombreLogro + " --> " + progresoUsuario);

			if (progresoUsuario < 100) {// esto quiere decir que el usuario no tiene este logro obtenido
				Logro logro = this.getLogro(nombreLogro);
				int aumento = (100 / logro.getObjetivo());
				this.actualizarProgreso(aumento, nombreLogro);
				int progresoActual = this.getInfoLogro(nombreLogro).getInt("progreso");
				System.out.println("Nuevo progreso: +" + aumento);
				System.out.println(progresoActual);
				/* Si al aumentar el progreso llega a 100 es que acaba de conseguirlo */
				if (progresoActual == 100) {
					nuevosLogros.put(unLogro);
					this.addNuevoLogro(nombreLogro);
				}
			}
		}
		return nuevosLogros;

	}

	public void addNuevoLogro(String nombreLogro) throws ParseException {
		Logro nuevoLogro = this.getLogro(nombreLogro);
		Date fechaActual = new SimpleDateFormat("yyyy-MM-dd")
				.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern(" yyyy-mm-dd")));
		LogroObtenido nuevoLogroObtenido = new LogroObtenido(fechaActual, nuevoLogro, 100);
		String user= this.email;
		int idLogro = nuevoLogro.getId();
		String sql ="INSERT INTO TieneLogro (Usuario, idLogro, Progreso) VALUES ('"+user+"', '"+idLogro+"', 100);";
		GestorDB.getGestorDB().execSQL(sql);
		this.susLogros.add(nuevoLogroObtenido);
	}

	public void actualizarProgreso(int num, String logro) {
		Iterator<LogroObtenido> itr = susLogros.iterator();
		boolean enc = false;
		while (itr.hasNext() && !enc) {
			LogroObtenido unLogroObtenido = itr.next();
			if (unLogroObtenido.esLogro(logro)) {
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

	public ArrayList<LogroObtenido> getListaLogros() {
		return susLogros;
	}

	public Logro getLogro(String nombre) {
		for (LogroObtenido i : susLogros) {
			if (i.esLogro(nombre))
				return i.getLogro();
		}
		return null;
	}

	public void anadirPuntuacion(Puntuacion p) {
		this.susPuntuaciones.add(p);
	}
}