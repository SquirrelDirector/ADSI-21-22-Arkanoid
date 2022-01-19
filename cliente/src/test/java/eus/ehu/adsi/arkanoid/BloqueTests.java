package eus.ehu.adsi.arkanoid;

import static org.junit.Assert.*;

import org.junit.Test;

import eus.ehu.adsi.arkanoid.modelo.BloqueSuerte;
import eus.ehu.adsi.arkanoid.modelo.ComportamientoBloque;
import eus.ehu.adsi.arkanoid.modelo.DestruccionBloque;
import eus.ehu.adsi.arkanoid.modelo.ModificacionBola;
import eus.ehu.adsi.arkanoid.modelo.ModificacionPaddle;

public class BloqueTests {

	@Test
	public void testComportamientoRomper() {
		BloqueSuerte bloque = new BloqueSuerte(0,0);
		ComportamientoBloque comportamiento = new DestruccionBloque();
		bloque.setComportamiento(comportamiento);
		bloque.romper();
		
		//assertTrue();
	}
	
	@Test
	public void testComportamientoIncrementarBola() {
		BloqueSuerte bloque = new BloqueSuerte(0,0);
		ComportamientoBloque comportamiento = new ModificacionBola();
		bloque.setComportamiento(comportamiento);
		bloque.romper();
		
		//assertTrue();
	}
	
	@Test
	public void testComportamientoIncrementarPaddle() {
		BloqueSuerte bloque = new BloqueSuerte(0,0);
		ComportamientoBloque comportamiento = new ModificacionPaddle();
		bloque.setComportamiento(comportamiento);
		bloque.romper();
		
		//assertTrue();
	}

}
