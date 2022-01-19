package eus.ehu.adsi.arkanoid.controlador;

import javax.mail.MessagingException;
import javax.swing.*;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.*;
import java.util.Observable;
import java.util.Random;
import java.util.regex.Pattern;

import eus.ehu.adsi.arkanoid.modelo.*;

public class Arkanoid extends Observable {

	private String usrCod;
	private static Arkanoid miArkanoid;
	/**
	 * Game variables
	 */
	/*private Game game;
	private Partida partida = new Partida();
	private double lastFt;
	private double currentSlice;*/
	private Usuario usuario;

	private Arkanoid() {
		
	}

	public static Arkanoid getArkanoid() {
		if (miArkanoid==null)
			miArkanoid=new Arkanoid();
		return miArkanoid;
	}

	private void update() {
		// TODO - implement Arkanoid.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param emailUsuario
	 */
	public JSONObject obtenerPersonalizacion(int emailUsuario) {
		// TODO - implement Arkanoid.obtenerPersonalizacion
		throw new UnsupportedOperationException();
	}

	public JSONObject obtenerPersonalizacionUsuario() {
		// TODO - implement Arkanoid.obtenerPersonalizacionUsuario
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
	public void actualizarPersonalizacionDB(int pathMusica, int colorFondo, int colorBola, int colorPaddle, int colorLadrillo) {
		// TODO - implement Arkanoid.actualizarPersonalizacionDB
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
		// TODO - implement Arkanoid.actualizarPersonalizacionUsu
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dificultad
	 * @param isPersonal
	 */
	public JSONArray mostrarRanking(int dificultad, boolean isPersonal) {
		// TODO - implement Arkanoid.mostrarRanking
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idNivel
	 */
	public void actualizarUltimaPartida(int idNivel) {
		// TODO - implement Arkanoid.actualizarUltimaPartida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idNivel
	 */
	public JSONObject obtenerDatosNivel(int idNivel) {
		// TODO - implement Arkanoid.obtenerDatosNivel
		throw new UnsupportedOperationException();
	}

	public JSONArray getLogros() {
		// TODO - implement Arkanoid.getLogros
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSONObject getInfoLogro(String nombre) {
		// TODO - implement Arkanoid.getInfoLogro
		throw new UnsupportedOperationException();
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
	public int registrarse(String usr, String mail, String pass) {
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
		if (!pass.equals(rePass))
			return 1;
		String regPass[] = new String[] { 
				"^.{6,}$", "^.*\\p{javaLowerCase}.*$", "^.*\\p{javaUpperCase}.*$", "^.*\\d.*$", "^.*\\p{Punct}.*$"};
		for (String regex : regPass) {
			if (!Pattern.matches(regex, pass)) //minimo de 6 caracteres con mayusculas, minusculas, numeros y simbolos.
				return 2;
		}
		
		GestorUsuarios.getGestorUsuario().cambiarContrasena(mail, pass);
		return 0;
	}

	public JSONObject getResultadosPartida() {
		// TODO - implement Arkanoid.getResultadosPartida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param datosPartida
	 * @param datosHistoricos
	 */
	private void agregarJSON(JSONObject datosPartida, JSONObject datosHistoricos) {
		// TODO - implement Arkanoid.agregarJSON
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param redSocial
	 */
	public void publicarResultados(String redSocial) {
		// TODO - implement Arkanoid.publicarResultados
		throw new UnsupportedOperationException();
	}

	public int comprobarCodigo(String cod) {
		if (!Pattern.matches("\\d{6,6}", cod))
			return 1;
		if (!usrCod.equals(cod))
			return 2;
		return 0;
	}

}