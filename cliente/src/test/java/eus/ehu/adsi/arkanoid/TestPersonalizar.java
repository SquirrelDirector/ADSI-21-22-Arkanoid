package eus.ehu.adsi.arkanoid;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

public class TestPersonalizar {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPersonalizar() {
		Arkanoid.getArkanoid().registrarse("test", "test@gmail.com", "test1Personalizar@", "test1Personalizar@");
		JSONObject datos = Arkanoid.getArkanoid().obtenerPersonalizacionUsuario();
		assertEquals("/sonidoPersonalizar/Sonido1.wav", datos.get("PathMusica"));
		assertEquals("255,0,0", datos.get("CodigoFondo"));
		assertEquals("255,181,0", datos.get("CodigoBola"));
		assertEquals("78,255,0", datos.get("CodigoPaddle"));
		assertEquals("0,153,255", datos.get("CodigoLadrillo"));
		assertEquals("44,60,4", datos.get("atributosPersonalizado"));
	}
}
