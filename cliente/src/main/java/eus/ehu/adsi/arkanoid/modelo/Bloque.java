package eus.ehu.adsi.arkanoid.modelo;

public class Bloque extends Rectangle {

	public boolean destroyed = false;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Bloque(double x, double y) {
		super(x,y); 
	}

	public void romper() {
		if (this.destroyed == false) {
			Partida miPartida = Partida.getMiPartida();
			miPartida.incrementarPuntuacion();
		}
		this.destroyed = true;
	}

}