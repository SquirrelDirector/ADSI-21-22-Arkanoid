package eus.ehu.adsi.arkanoid.controlador;

import org.json.JSONArray;

import eus.ehu.adsi.arkanoid.modelo.EnvioRRSS;
import eus.ehu.adsi.arkanoid.modelo.EnvioRRSSFactory;

public class GestorRedes {

	private static GestorRedes miGestorRedes;

	private GestorRedes() {
		// TODO - implement GestorRedes.GestorRedes
		throw new UnsupportedOperationException();
	}

	public static GestorRedes getGestorRedes() {
		// TODO - implement GestorRedes.getGestorRedes
		throw new UnsupportedOperationException();
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
		String mensaje="He aqu� cosas";
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