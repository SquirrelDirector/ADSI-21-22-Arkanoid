package eus.ehu.adsi.arkanoid;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.controlador.GestorDB;
import eus.ehu.adsi.arkanoid.modelo.Config;

public class TestPersonalizar {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPersonalizarUsuario() {
		//Registramos un nuevo usuario e iniciamos sesión para comprobar que las personalizaciones que tiene son 
		//las de default
		Arkanoid.getArkanoid().registrarse("test", "test@gmail.com", "test1Personalizar@", "test1Personalizar@");
		Arkanoid.getArkanoid().iniciarSesion("test@gmail.com", "test1Personalizar@");
		JSONObject datos = Arkanoid.getArkanoid().obtenerPersonalizacionUsuario();
		
		assertEquals("/sonidoPersonalizar/Sonido1.wav", datos.get("PathMusica"));
		assertEquals("255,0,0", datos.get("CodigoFondo"));
		assertEquals("255,181,0", datos.get("CodigoBola"));
		assertEquals("78,255,0", datos.get("CodigoPaddle"));
		assertEquals("0,153,255", datos.get("CodigoLadrillo"));
		assertEquals("44,60,4", datos.get("atributosPersonalizado"));
		
		
		//Actualizamos algunos datos y comprobamos que se han realizado los cambios
		Arkanoid.getArkanoid().actualizarPersonalizacionDB("/sonidoPersonalizar/Sonido1.wav", "255,181,0", "255,181,0", "78,255,0", "0,153,255", "55,60,4"); 
        Arkanoid.getArkanoid().actualizarPersonalizacionUsu("/sonidoPersonalizar/Sonido1.wav", "255,181,0", "255,181,0", "78,255,0", "0,153,255", "55,60,4"); 
        Double[] datosP = new Double[3]; 
        datosP[0] = (double) 4; 
        datosP[1] = (double) 60; 
        datosP[2] = (double) 55; 
        Arkanoid.getArkanoid().updateConfig(datosP); 
        JSONObject datos2 = Arkanoid.getArkanoid().obtenerPersonalizacionUsuario();
        
		assertEquals("/sonidoPersonalizar/Sonido1.wav", datos2.get("PathMusica"));
		assertEquals("255,181,0", datos2.get("CodigoFondo"));
		assertEquals("255,181,0", datos2.get("CodigoBola"));
		assertEquals("78,255,0", datos2.get("CodigoPaddle"));
		assertEquals("0,153,255", datos2.get("CodigoLadrillo"));
		assertEquals("55,60,4", datos2.get("atributosPersonalizado"));
		
		
		//Cerramos sesión, volvemos a iniciar sesión y comprobamos que los datos anteriormente
		//actualizados se mantienen
		Arkanoid.getArkanoid().cerrarSesion();
		Arkanoid.getArkanoid().iniciarSesion("test@gmail.com", "test1Personalizar@");
		
		JSONObject datos3 = Arkanoid.getArkanoid().obtenerPersonalizacionUsuario();
        
		assertEquals("/sonidoPersonalizar/Sonido1.wav", datos3.get("PathMusica"));
		assertEquals("255,181,0", datos3.get("CodigoFondo"));
		assertEquals("255,181,0", datos3.get("CodigoBola"));
		assertEquals("78,255,0", datos3.get("CodigoPaddle"));
		assertEquals("0,153,255", datos3.get("CodigoLadrillo"));
		assertEquals("55,60,4", datos3.get("atributosPersonalizado"));
		
		
		//Eliminamos el usuario creado para evitar que si se vuelven a ejecutar los test
		//no de problemas por que ya existe ese usuario
		GestorDB.getGestorDB().execSQL("DELETE FROM usuario WHERE Email='test@gmail.com'");
	}
	
	@Test
	public void testPersonalizarJugador() {
		//Primero cerramos sesión para asegurarnos que no tenemos datos del anterior
		Arkanoid.getArkanoid().cerrarSesion();
		
		//Para alguien que no está registrado comprobamos que las personalizaciones son 
		//las de default
		JSONObject datos = Arkanoid.getArkanoid().obtenerPersonalizacionUsuario();
		
		assertEquals("/sonidoPersonalizar/Sonido1.wav", datos.get("PathMusica"));
		assertEquals("255,0,0", datos.get("CodigoFondo"));
		assertEquals("255,181,0", datos.get("CodigoBola"));
		assertEquals("78,255,0", datos.get("CodigoPaddle"));
		assertEquals("0,153,255", datos.get("CodigoLadrillo"));
		
		
		//Actualizamos algunos datos y comprobamos que se han realizado los cambios
		//*Los cambios se reflejan solo en el config, que es de donde se sacan los valores 
			//al crear el tablero del juego
		Arkanoid.getArkanoid().updateColores("255,0,0", "255,0,0", "0,153,255", "78,255,0"); 
    	Arkanoid.getArkanoid().updateMusica("/sonidoPersonalizar/Sonido2.wav"); 
    	
		assertEquals("/sonidoPersonalizar/Sonido2.wav", Config.PATH_MUSICA);
		assertEquals("255,0,0", Config.BACKGROUND_COLOR);
		assertEquals("255,0,0", Config.BALL_COLOR);
		assertEquals("78,255,0", Config.PADDLE_COLOR);
		assertEquals("0,153,255", Config.BRICK_COLOR);
	}
}
