package eus.ehu.adsi.arkanoid.controlador;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.modelo.Partida;
import eus.ehu.adsi.arkanoid.vista.Tablero;

public class GestorPartida {

	private static GestorPartida miGestorPartida;

	private GestorPartida() {
		
	}

	public static GestorPartida getGestorPartida() {
		if (miGestorPartida == null) miGestorPartida = new GestorPartida();
        return miGestorPartida;
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
	
	@SuppressWarnings("deprecation")
	public void addObserverCrono(Tablero tablero){
        Partida.getMiPartida().getCrono().addObserver(tablero);
    }

}