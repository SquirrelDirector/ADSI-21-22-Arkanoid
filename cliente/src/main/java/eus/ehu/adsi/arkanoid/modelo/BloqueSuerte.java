package eus.ehu.adsi.arkanoid.modelo;

public class BloqueSuerte extends Bloque {

	private ComportamientoBloque comportamiento;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public BloqueSuerte(double x, double y) {
		super(x, y);
		
		int num = (int) Math.floor((Math.random()*3)+1);

        switch (num) 
        {
            case 1:
                comportamiento = new ModificacionBola();
                break;
            case 2:
                comportamiento = new ModificacionPaddle();
                break;
            case 3:
                comportamiento = new DestruccionBloque();
                break;
            default:
                comportamiento = new DestruccionBloque();
                break;
        }
	}

	public void romper() {
		if (this.destroyed == false) {
			this.comportamiento.romper();
		}
		super.romper();
	}

	public void setComportamiento(ComportamientoBloque pCom) {
		comportamiento = pCom;
	}
}