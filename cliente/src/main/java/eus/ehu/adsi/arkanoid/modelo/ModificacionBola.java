package eus.ehu.adsi.arkanoid.modelo;

public class ModificacionBola implements ComportamientoBloque {

	public ModificacionBola() {}

	@Override
	public void romper() {
		// TODO Auto-generated method stub
		
		Partida miPartida = Partida.getMiPartida();
		miPartida.modificarBola(2);
	}

}