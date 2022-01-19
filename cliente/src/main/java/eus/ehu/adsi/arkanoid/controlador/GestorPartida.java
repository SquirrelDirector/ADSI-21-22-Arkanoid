package eus.ehu.adsi.arkanoid.controlador;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.modelo.Partida;
<<<<<<< HEAD
import eus.ehu.adsi.arkanoid.vista.Tablero;
=======
>>>>>>> refs/heads/feature-publicacion-resultados

public class GestorPartida {

	private static GestorPartida miGestorPartida;
	private Partida partida;

	private GestorPartida() {
<<<<<<< HEAD
		
=======
		partida = new Partida();//TEST
>>>>>>> refs/heads/feature-publicacion-resultados
	}

	public static GestorPartida getGestorPartida() {
<<<<<<< HEAD
		if (miGestorPartida == null) miGestorPartida = new GestorPartida();
        return miGestorPartida;
=======
		if(miGestorPartida==null) {
			miGestorPartida=new GestorPartida();
		}
		return miGestorPartida;
>>>>>>> refs/heads/feature-publicacion-resultados
	}

	public JSONObject getDatosPartidaActual() {
<<<<<<< HEAD
		// TODO - implement GestorPartida.getDatosPartidaActual
		throw new UnsupportedOperationException();
=======
		return partida.getDatosPartidaActual();
>>>>>>> refs/heads/feature-publicacion-resultados
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