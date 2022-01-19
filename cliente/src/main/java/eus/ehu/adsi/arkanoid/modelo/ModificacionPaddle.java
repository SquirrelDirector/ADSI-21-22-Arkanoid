package eus.ehu.adsi.arkanoid.modelo;

public class ModificacionPaddle implements ComportamientoBloque {

	public ModificacionPaddle() {}

	@Override
	public void romper() {
		// TODO Auto-generated method stub
		
		Partida miPartida = Partida.getMiPartida();
		miPartida.modificarPaddle(0);
	}

}