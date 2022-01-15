package eus.ehu.adsi.arkanoid.controlador;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.modelo.Partida;
import eus.ehu.adsi.arkanoid.vista.Tablero;

public class GestorPartida {

	private static GestorPartida miGestorPartida;

	private GestorPartida() {
		// TODO - implement GestorPartida.GestorPartida
		throw new UnsupportedOperationException();
	}

	public static GestorPartida getGestorPartida() {
		// TODO - implement GestorPartida.getGestorPartida
		throw new UnsupportedOperationException();
	}

	public JSONObject getDatosPartidaActual() {
		// TODO - implement GestorPartida.getDatosPartidaActual
		throw new UnsupportedOperationException();
	}
	
	@SuppressWarnings("deprecation")
	public void addObserver(Tablero tablero){
        Partida.getMiPartida().deleteObservers();
        Partida.getMiPartida().addObserver(tablero);
    }

}