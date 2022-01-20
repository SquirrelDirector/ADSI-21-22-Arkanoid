package eus.ehu.adsi.arkanoid;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;

public class TestIdentificarse {

	private String rightName="BPrhpEw8";
	private String rightMail="Allon1933@einrot.com";
	private String rightPass="8DYP~q3pdz)Ye,-y";
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIniciarSesion() {
		assertEquals(1, Arkanoid.getArkanoid().iniciarSesion("", "")); //1.1
		assertEquals(2, Arkanoid.getArkanoid().iniciarSesion(rightMail, "")); //1.1
		assertEquals(3, Arkanoid.getArkanoid().iniciarSesion("Fixer1965@rhyta.com", "5r5R?.aKjN$UbNck")); //1.2
		assertEquals(0, Arkanoid.getArkanoid().iniciarSesion(rightMail, rightPass)); //1.3
		Arkanoid.getArkanoid().cerrarSesion();
		assertEquals(3, Arkanoid.getArkanoid().iniciarSesion(rightMail, "5r5R?.aKjN$UbNck")); //1.4
		assertEquals(3, Arkanoid.getArkanoid().iniciarSesion("Fixer1965@rhyta.com", rightPass)); //1.5
		assertEquals(3, Arkanoid.getArkanoid().iniciarSesion(rightMail, "8dyp~Q3PDZ)yE,-Y")); //1.6
		
	}
	
	@Test
	public void testIsIdentificado() {
		assertEquals(false, Arkanoid.getArkanoid().isIdentificado());
		Arkanoid.getArkanoid().iniciarSesion(rightMail, rightPass);
		assertEquals(true, Arkanoid.getArkanoid().isIdentificado());
		Arkanoid.getArkanoid().cerrarSesion();
		assertEquals(false, Arkanoid.getArkanoid().isIdentificado());
	}
	
	@Test
	public void testRegistrarse() {
		assertEquals(1, Arkanoid.getArkanoid().registrarse(rightName, "Fixer1965@rhyta.com", "5r5R?.aKjN$UbNck", "5r5R?.aKjN$UbNck")); //3.2
		assertEquals(0, Arkanoid.getArkanoid().iniciarSesion("Fixer1965@rhyta.com", "5r5R?.aKjN$UbNck")); //3.2
		Arkanoid.getArkanoid().cerrarCuenta("Fixer1965@rhyta.com");
		assertEquals(4, Arkanoid.getArkanoid().registrarse("EArang", rightMail, "5r5R?.aKjN$UbNck", "5r5R?.aKjN$UbNck")); //3.3
		assertEquals(3, Arkanoid.getArkanoid().registrarse("EArang", "Fixer1965@rhyta.com", "1234", "1234")); //3.4
		assertEquals(5, Arkanoid.getArkanoid().registrarse("EArang", "Fixer1965@rhyta.com", "5r5R?.aKjN$UbNck", "1234")); //3.5
	}
	
	@Test
	public void testCambiarContrasena() {
		assertEquals(0, Arkanoid.getArkanoid().cambiarContrasena(rightMail, "5r5R?.aKjN$UbNck", "5r5R?.aKjN$UbNck")); //4.3.1
		assertEquals(1, Arkanoid.getArkanoid().cambiarContrasena(rightMail, rightPass, "1234")); //4.3.2
		assertEquals(2, Arkanoid.getArkanoid().cambiarContrasena(rightMail, "1234", "1234"));
		assertEquals(0, Arkanoid.getArkanoid().cambiarContrasena(rightMail, "5r5R?.aKjN$UbNck", "5r5R?.aKjN$UbNck")); //4.3.4
	}

}
