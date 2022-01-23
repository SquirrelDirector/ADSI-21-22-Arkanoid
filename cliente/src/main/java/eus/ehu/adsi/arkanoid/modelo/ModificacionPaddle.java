package eus.ehu.adsi.arkanoid.modelo;

public class ModificacionPaddle implements ComportamientoBloque {

	public ModificacionPaddle() {}

	@Override
	public void romper() {		
		Partida miPartida = Partida.getMiPartida();
		miPartida.modificarPaddle(100);
	}

}