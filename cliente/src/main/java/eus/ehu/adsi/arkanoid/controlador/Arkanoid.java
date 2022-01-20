package eus.ehu.adsi.arkanoid.controlador;

import javax.mail.MessagingException;
import javax.swing.*;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.Observable;
import java.util.Random;
import java.util.regex.Pattern;

import eus.ehu.adsi.arkanoid.modelo.*;
import java.awt.Color;
import eus.ehu.adsi.arkanoid.vista.Tablero;

public class Arkanoid extends Observable {

	private String usrCod;
	private static Arkanoid miArkanoid;
	/**
	 * Game variables
	 */
	private Game game = new Game();
	private double lastFt;
	private double currentSlice;
	private Usuario usuario;

	private Arkanoid() {

	}

	public static Arkanoid getArkanoid() {
		if (miArkanoid == null) {
			miArkanoid = new Arkanoid();
		}
		return miArkanoid;
	}

	public void jugar() {
		Partida miPartida = Partida.getMiPartida();
		miPartida.generarPartida();
		
		game.setRunning(true);

		while (game.isRunning()) {

			long time1 = System.currentTimeMillis();

			if (!miPartida.gameOver && !miPartida.ganar) {
				game.setTryAgain(false);
				update();

				// to simulate low FPS
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {}

			} else {
				if (game.isTryAgain()) {
					game.setTryAgain(false);
					miPartida.generarPartida();
				}
			}

			long time2 = System.currentTimeMillis();
			double elapsedTime = time2 - time1;

			lastFt = elapsedTime;

			double seconds = elapsedTime / 1000.0;
			if (seconds > 0.0) {
				double fps = 1.0 / seconds;
			}

		}

	}
	
	private void update() {
		currentSlice += lastFt;

		for (; currentSlice >= Config.FT_SLICE; currentSlice -= Config.FT_SLICE) {
			Partida miPartida = Partida.getMiPartida();
			miPartida.testPaddle();
			miPartida.testBola();
			
			//comprobar si se han roto todos los bloques
			miPartida.ganar();
		}
	}

	public boolean isIdentificado() {
		return usuario.isIdentificado();
	}

	public JSONObject obtenerPersonalizables() {
		JSONObject resultado = new JSONObject();
		resultado.put("colores", obtenerTodosPersonalizablesPorTabla("color"));
		resultado.put("sonidos", obtenerTodosPersonalizablesPorTabla("audio"));
		return resultado;
	}
	
	public JSONObject obtenerAvatares() {
		JSONObject resultado = new JSONObject();
		resultado.put("avatares", obtenerTodosPersonalizablesPorTabla("imagen"));
		return resultado;
	}

	private JSONArray obtenerTodosPersonalizablesPorTabla(String tabla) {
		JSONArray opciones = new JSONArray();
		ResultadoSQL resconsulta = GestorDB.getGestorDB().execSQL("SELECT * FROM "+ tabla);
		if (resconsulta != null) {
			while (resconsulta.hasNext()) {
				JSONObject informacion = new JSONObject();
				String val = "";
				if (tabla.equals("color")) {
					val = "Codigo";
				} else {
					val = "Path";
				}
				informacion.put(val, resconsulta.get(val));
				informacion.put("Nombre", resconsulta.get("Nombre"));
				opciones.put(informacion);
				resconsulta.next();
			}
		}
		return opciones;
	}

	public JSONObject obtenerPersonalizacionUsuario() {
		return usuario.obtenerPersonalizacionUsuario();
	}
	
	public JSONObject obtenerDatosUsuario() {
		return usuario.getDatosUsuario();
	}

	/**
	 * 
	 * @param pathMusica
	 * @param colorFondo
	 * @param colorBola
	 * @param colorPaddle
	 * @param colorLadrillo
	 */
	public void actualizarPersonalizacionDB(String pathMusica, String colorFondo, String colorBola, String colorPaddle, String colorLadrillo, String atributosPersonalizacion) {
		String email = usuario.getEmail();
		GestorUsuarios.getGestorUsuario().actualizarPersonalizacion(email, pathMusica, colorFondo, colorBola, colorPaddle, colorLadrillo, atributosPersonalizacion);
	}
	
	/**
	 * 
	 * @param pathAvatar
	 * @param nombreUsu
	 */
	public void actualizarDatosUsuDB(String pathAvatar, String nombreUsu) {
		String email = usuario.getEmail();
		GestorUsuarios.getGestorUsuario().actualizarDatosUsuDB(email, pathAvatar, nombreUsu);
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
		usuario.actualizarPersonalizacionUsu(pathMusica, colorFondo, colorBola, colorPaddle, colorLadrillo, atributosPersonalizacion);
	}
	
	/**
	 * 
	 * @param pathAvatar
	 * @param nombreUsu
	 */
	public void actualizarDatosUsu(String pathAvatar, String nombreUsu) {
		usuario.actualizarDatosUsu(pathAvatar, nombreUsu);
	}

	/**
	 * 
	 * @param dificultad
	 * @param isPersonal
	 */
	public JSONArray mostrarRanking(int dificultad, boolean isPersonal) {
		
		if (isPersonal){
			return usuario.obtenerRankingPersonal(dificultad);
		}
		else{
			return GestorPuntuaciones.getGestorPuntuaciones().obtenerRanking(dificultad);
		}
	}

	/**
	 * 
	 * @param idNivel
	 */
	public void actualizarUltimaPartida(int idNivel) {
		usuario.actualizarUltimaPartida(idNivel);
	}

	/**
	 * 
	 * @param idNivel
	 */
	public Double[] obtenerDatosNivel(int idNivel) {
        if (idNivel==6) {
            String[] Datos = usuario.obtenerDatosNivelPersonalizado();
            Double[] DatosFinal = new Double[3];
            DatosFinal[1]= Double.parseDouble(Datos[1]);
            DatosFinal[2]= Double.parseDouble(Datos[2]);
            DatosFinal[3]= Double.parseDouble(Datos[3]);
            return DatosFinal;
        }
        else {
            return GestorNiveles.getGestorNiveles().obtenerDatosNivel(idNivel);
        }
    }

	public JSONArray getLogros() {
		return usuario.getLogros();
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSONObject getInfoLogro(String nombre) {
		return usuario.getInfoLogro(nombre);
	}

	/**
	 * 
	 * @param mail
	 * @param pass
	 */
	public int iniciarSesion(String mail, String pass) {
		String regPass[] = new String[] { 
		"^.{6,}$", "^.*\\p{javaLowerCase}.*$", "^.*\\p{javaUpperCase}.*$", "^.*\\d.*$", "^.*\\p{Punct}.*$"};
		
		if (!Pattern.matches("^\\p{Graph}+@\\p{Graph}+\u002e\\p{Graph}+$", mail)) //cadena de caracteres + @ + cadena de caracteres + . + cadena de caracteres
			return 1;
		for (String regex : regPass) {
			if (!Pattern.matches(regex, pass)) //minimo de 6 caracteres con mayusculas, minusculas, numeros y simbolos.
				return 2;
		}
		
		pass=resumir(pass); //se hace en una funcion separada para facilitar la posibilidad de cambiar el algoritmo de resumen
		
		JSONObject datos=GestorUsuarios.getGestorUsuario().importarUsuario(mail, pass);
		if (datos==null)
			return 3;
		
		usuario=new Usuario(datos);
		notifyObservers(usuario.getPerfil());
		return 0;
	}
	
	private String resumir(String pass) {
		String hash=pass.hashCode()+"";
		return hash;
	}

	public void cerrarSesion(){
		if (!usuario.isIdentificado()) //caso a priori imposible de suceder
			notifyObservers(usuario.getPerfil());
		usuario=new Usuario();
	}

	/**
	 * 
	 * @param usr
	 * @param mail
	 * @param pass
	 */
	public int registrarse(String usr, String mail, String pass, String rePass) {
		String regPass[] = new String[] { 
				"^.{6,}$", "^.*\\p{javaLowerCase}.*$", "^.*\\p{javaUpperCase}.*$", "^.*\\d.*$", "^.*\\p{Punct}.*$"};
		
		if (GestorUsuarios.getGestorUsuario().existeNombre(usr))
			return 1;
		if (!Pattern.matches("^\\p{Graph}+@\\p{Graph}+\u002e\\p{Graph}+$", mail)) //cadena de caracteres + @ + cadena de caracteres + . + cadena de caracteres
			return 2;
		for (String regex : regPass) {
			if (!Pattern.matches(regex, pass)) //minimo de 6 caracteres con mayusculas, minusculas, numeros y simbolos.
				return 3;
		}
		if(GestorUsuarios.getGestorUsuario().existeUsuario(mail))
			return 4;
		pass=resumir(pass);
		rePass=resumir(rePass);
		if (!pass.equals(rePass))
			return 5;
		
		pass=resumir(pass);
		GestorUsuarios.getGestorUsuario().crearUsuario(usr, mail, pass);
		JSONObject datos=GestorUsuarios.getGestorUsuario().importarUsuario(mail, pass);
		usuario=new Usuario(datos);
		return 0;
		
	}

	/**
	 * 
	 * @param mail
	 */
	public int recuperarContrasena(String mail) {
		if (!Pattern.matches("^\\p{Graph}+@\\p{Graph}+\u002e\\p{Graph}+$", mail)) //cadena de caracteres + @ + cadena de caracteres + . + cadena de caracteres
			return 1;
		if (!GestorUsuarios.getGestorUsuario().existeUsuario(mail))
			return 2;
		try{
			usrCod= (new Random().nextInt((999999 - 100000) + 1) + 100000)+"";
			GestorRedes.getGestorRedes().enviarRecuperacion(mail,usrCod);
			return 0;
		}catch (MessagingException e){
			return 3;
		}
	}

	/**
	 * 
	 * @param mail
	 * @param pass
	 */
	public int cambiarContrasena(String mail, String pass, String rePass) {
		
		String regPass[] = new String[] { 
				"^.{6,}$", "^.*\\p{javaLowerCase}.*$", "^.*\\p{javaUpperCase}.*$", "^.*\\d.*$", "^.*\\p{Punct}.*$"};
		for (String regex : regPass) {
			if (!Pattern.matches(regex, pass)) //minimo de 6 caracteres con mayusculas, minusculas, numeros y simbolos.
				return 2;
		}
		
		pass=resumir(pass);
		rePass=resumir(rePass);
		if (!pass.equals(rePass))
			return 1;
		
		GestorUsuarios.getGestorUsuario().cambiarContrasena(mail, pass);
		return 0;
	}
	
	public int cerrarCuenta(String mail){
		if (!GestorUsuarios.getGestorUsuario().existeUsuario(mail))
			return 1;
		GestorUsuarios.getGestorUsuario().cerrarCuenta(mail);
		return 0;
	}

	public JSONObject getResultadosPartida() {
		Partida miPartida = Partida.getMiPartida();
		
		JSONObject datosPartida = miPartida.getDatosPartidaActual();
		if(usuario.isIdentificado()){
			JSONObject datosHistoricos = usuario.getDatosHistoricosJugador();
			agregarJSON(datosPartida, datosHistoricos);
		}
		return datosPartida;
	}

	/**
	 * 
	 * @param datosPartida
	 * @param datosHistoricos
	 */
	private void agregarJSON(JSONObject datosPartida, JSONObject datosHistoricos) {
		// TODO - implement Arkanoid.publicarResultados
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param redSocial
	 */
	public void publicarResultados(String redSocial) {
		/*JSONObject datosPartida = GestorPartida.getGestorPartida().getDatosPartidaActual();
		JSONObject datosHistoricos = usuario.getDatosHistoricosJugador();
		GestorRedes.getGestorRedes().publicarResultados(redSocial, 
														Integer.parseInt(datosPartida.get("puntuacionConseguida").toString()), 
														Integer.parseInt(datosPartida.get("tiempoPartida").toString()), 
														Integer.parseInt(datosHistoricos.get("mejorPuntuacion").toString()), 
														Integer.parseInt(datosHistoricos.get("mejorTiempo").toString()), 
														datosPartida.getJSONArray("logrosConseguidos"));*/
	}

	public int getUltimaPartida() {
		return usuario.getNivelDefault();
	}
	
	/**
	 * 
	 * @param nombreUsu
	 */
	public boolean comprobarNombre(String nombreUsu) {
		return GestorUsuarios.getGestorUsuario().comprobarNombre(nombreUsu);
	}
	
	public void updateConfig(double Velocidad, double Anchura, int Num_Ladrillos) { 
		Config.BALL_VELOCITY=Velocidad/2; 
		Config.PADDLE_WIDTH=Anchura; 
		Config.COUNT_BLOCKS_Y=Num_Ladrillos/Config.COUNT_BLOCKS_X; 
	} 
	 
	public void updateColores(String Fondo, String Bola, String Ladrillo, String Paddle) { 
		Config.BACKGROUND_COLOR = new Color(Integer.parseInt(Fondo)); 
		Config.BALL_COLOR = new Color(Integer.parseInt(Bola));
		Config.BRICK_COLOR = new Color(Integer.parseInt(Ladrillo));
		Config.PADDLE_COLOR = new Color(Integer.parseInt(Paddle));
	} 
	 
	public void updateMusica(String path) { 
		Config.PATH_MUSICA = path; 
	} 
	public int comprobarCodigo(String cod) {
		if (!Pattern.matches("\\d{6,6}", cod))
			return 1;
		if (!usrCod.equals(cod))
			return 2;
		return 0;
	}

	public void addObserverCrono(Tablero tablero){ 
		GestorPartida.getGestorPartida().addObserverCrono(tablero); 
    } 
	
	public void addObserverPartida(Tablero tablero){ 
		GestorPartida.getGestorPartida().addObserverPartida(tablero); 
    } 
}