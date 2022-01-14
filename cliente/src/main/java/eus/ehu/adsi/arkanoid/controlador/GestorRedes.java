package eus.ehu.adsi.arkanoid.controlador;

import org.json.JSONArray;

import eus.ehu.adsi.arkanoid.modelo.EnvioRRSS;
import eus.ehu.adsi.arkanoid.modelo.EnvioRRSSFactory;

public class GestorRedes {

	private static GestorRedes miGestorRedes;

	private GestorRedes() {
		
	}

	public static GestorRedes getGestorRedes() {
		if (miGestorRedes==null) {
			miGestorRedes = new GestorRedes();
		}
		return miGestorRedes;
	}

	/**
	 * 
	 * @param redSocial
	 * @param puntuacionPartida
	 * @param tiempoPartida
	 * @param mejorPuntuacion
	 * @param mejorTiempo
	 * @param logro
	 */
	public void publicarResultados(String redSocial, int puntuacionPartida, int tiempoPartida, int mejorPuntuacion, int mejorTiempo, JSONArray logro) {
		String mensaje="He aquí cosas";
		EnvioRRSS envio = new EnvioRRSSFactory().getEnvioRRSS(redSocial);
		envio.enviar(mensaje);
	}

	/**
	 * 
	 * @param mail
	 */
	public void enviarRecuperacion(String mail) {
		// TODO - implement GestorRedes.enviarRecuperacion
		throw new UnsupportedOperationException();
	}

}