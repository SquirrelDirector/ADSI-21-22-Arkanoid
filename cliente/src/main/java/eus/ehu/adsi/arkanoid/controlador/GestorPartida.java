package eus.ehu.adsi.arkanoid.controlador;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.modelo.Partida;

public class GestorPartida {

	private static GestorPartida miGestorPartida;
	private Partida partida;

	private GestorPartida() {
		partida = new Partida();//TEST
	}

	public static GestorPartida getGestorPartida() {
		if(miGestorPartida==null) {
			miGestorPartida=new GestorPartida();
		}
		return miGestorPartida;
	}

	public JSONObject getDatosPartidaActual() {
		return partida.getDatosPartidaActual();
	}

}