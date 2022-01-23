package eus.ehu.adsi.arkanoid;

import static org.junit.Assert.*;

import javax.swing.SwingUtilities;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.glass.ui.Window;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.controlador.GestorDB;
import eus.ehu.adsi.arkanoid.modelo.EnvioRRSS;
import eus.ehu.adsi.arkanoid.modelo.EnvioRRSSFactory;
import eus.ehu.adsi.arkanoid.modelo.Partida;
import eus.ehu.adsi.arkanoid.modelo.RRSSFacebook;
import eus.ehu.adsi.arkanoid.modelo.RRSSMail;
import eus.ehu.adsi.arkanoid.modelo.RRSSTwitter;

public class TestPublicarResultados {

	private String rightMail="Allon1933@einrot.com";
	private String rightPass="8DYP~q3pdz)Ye,-y";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	//Cod 1.1
	@Test
	public void testCompartirSinIdentificar() {
		Partida p = Partida.getMiPartida();
		p.incrementarPuntuacion();
		JSONObject jso=p.getDatosPartidaActual();
		assertFalse(jso.has("mejorPuntuacion"));
		assertFalse(jso.has("mejorTiempo"));
		assertFalse(Arkanoid.getArkanoid().isIdentificado());
	}
	
	//Cod 1.2
		@Test
		public void testCompartirIdentificado() {
			Arkanoid.getArkanoid().iniciarSesion(rightMail, rightPass);
			Partida p = Partida.getMiPartida();
			p.incrementarPuntuacion();
			assertTrue(Arkanoid.getArkanoid().isIdentificado());
			Arkanoid.getArkanoid().cerrarSesion();
		}
		
		//Cod 2.2
		@Test
		public void testCompartirFacebook() {
			EnvioRRSS erss= new EnvioRRSSFactory().getEnvioRRSS("facebook");
			assertNotNull(erss);
			assertTrue(erss instanceof RRSSFacebook);
		}
		
		//Cod 2.1
		@Test
		public void testCompartirTwitter() {
			EnvioRRSS erss= new EnvioRRSSFactory().getEnvioRRSS("twitter");
			assertNotNull(erss);
			assertTrue(erss instanceof RRSSTwitter);
		}
		
		//Cod 2.3
		@Test
		public void testCompartirEmail() {
			EnvioRRSS erss= new EnvioRRSSFactory().getEnvioRRSS("email");
			assertNotNull(erss);
			assertTrue(erss instanceof RRSSMail);
		}
}
