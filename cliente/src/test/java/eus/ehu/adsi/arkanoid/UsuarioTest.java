package eus.ehu.adsi.arkanoid;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.modelo.CatalogoLogros;
import eus.ehu.adsi.arkanoid.modelo.Logro;
import eus.ehu.adsi.arkanoid.modelo.LogroObtenido;
import eus.ehu.adsi.arkanoid.modelo.Usuario;

public class UsuarioTest {

	@Test
	public void testCotejarLogros() throws ParseException {
		Arkanoid arka = Arkanoid.getArkanoid();
		Usuario user = new Usuario();
		System.out.println("---------------------------Prueba cotejarLogros---------------------------");
		ArrayList<LogroObtenido> userLogros = user.getListaLogros();
		Date fechaActual = new SimpleDateFormat("yyyy-MM-dd")
				.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern(" yyyy-mm-dd")));
		String sDate1 = "31/12/1998";
		Date fechaDefault = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		/* Simulamos que un usuario ya tiene algunos logros obtenidos o en proceso */
		Logro l1 = new Logro(1, "ganarMismoNivel1", "gana 5 partidas del nivel 1 para obtener este logro", 5);
		Logro l2 = new Logro(2, "ganarMismoNivel2", "gana 5 partidas del nivel 2 para obtener este logro", 5);
		Logro l3 = new Logro(3, "ganarMismoNivel3", "gana 5 partidas del nivel 3 para obtener este logro", 5);
		LogroObtenido lo1 = new LogroObtenido(fechaActual, l1, 80);
		LogroObtenido lo2 = new LogroObtenido(fechaDefault, l2, 0);
		LogroObtenido lo3 = new LogroObtenido(fechaActual, l3, 100);
		userLogros.add(lo1);
		userLogros.add(lo2);
		userLogros.add(lo3);

		JSONObject logN1 = new JSONObject();
		logN1.put("nombre", "ganarMismoNivel1");

		JSONObject logN2 = new JSONObject();
		logN2.put("nombre", "ganarMismoNivel2");

		JSONObject logN3 = new JSONObject();
		logN3.put("nombre", "ganarMismoNivel3");

		/*
		 * Simulacion de datos obtenidos en una partida terminada, ahora cotejarLogros
		 * deberia de devovler un JSONArray formado solo con los logros obtenidos en esa
		 * partida
		 */

		/*
		 * En esta simulacion el usuario ya tiene un logro desbloqueado, otro lo tiene a
		 * un 80%, es decir, gana una partida mas y ya lo desbloquea y tiene otro logro
		 * con un progreso del 0% Teniendo esto en cuenta, el usuario unicamente habra
		 * desbloqueado el logro ganarMismoNivel2 pues habra ganado las 5 partidas
		 * necesarias Por otra parte, al haber ganado tambien el nivel3 si bien no
		 * consigue desbloquear el logro su progreso si que aumenta en un 20%, asi que
		 * si gana 4 veces mas desbloqueara el logro
		 */
		JSONArray logrosObtenidosEnPartida = new JSONArray();
		logrosObtenidosEnPartida.put(logN1); // Ha ganado en el nivel 1 y con esto ya suma 5 victorias en este nivel;
												// luego desbloquea logro

		assertEquals(1, logrosObtenidosEnPartida.length());
		JSONArray nuevosLogros = user.cotejarLogros(logrosObtenidosEnPartida);
		assertEquals(1, nuevosLogros.length());

		assertEquals("ganarMismoNivel1", nuevosLogros.getJSONObject(0).get("nombre"));
		assertNotEquals("ganarMismoNivel2", nuevosLogros.getJSONObject(0).get("nombre"));
		assertNotEquals("ganarMismoNivel3", nuevosLogros.getJSONObject(0).get("nombre"));

		logrosObtenidosEnPartida = new JSONArray();
		logrosObtenidosEnPartida.put(logN2); // Ha ganado en el nivel 2. No desbloquea logro pero aumenta en 1/5
												// victorias para desbloquearlo

		assertEquals(1, logrosObtenidosEnPartida.length());
		nuevosLogros = user.cotejarLogros(logrosObtenidosEnPartida);
		assertEquals(0, nuevosLogros.length());

		logrosObtenidosEnPartida = new JSONArray();
		logrosObtenidosEnPartida.put(logN3); // Ha ganado en el nivel 3. No desbloquea logro porque ya lo tenia
												// desbloqueado previamente

		assertEquals(1, logrosObtenidosEnPartida.length());
		nuevosLogros = user.cotejarLogros(logrosObtenidosEnPartida);
		assertEquals(0, nuevosLogros.length());

		/*
		 * 3 doritos despues, el usuario ha ganado 4 veces el nivel 3 y ya ha
		 * desbloqueado el logro, vuamosss a comprobarlo
		 */
		logrosObtenidosEnPartida = new JSONArray();
		System.out.println();
		System.out.println("2da victoria en nivel 2");
		logrosObtenidosEnPartida.put(logN2); // juega una vez
		nuevosLogros = user.cotejarLogros(logrosObtenidosEnPartida);
		assertEquals(0, nuevosLogros.length()); // Comprobamos que todavia no ha desbloqueado el logro

		logrosObtenidosEnPartida = new JSONArray();
		logrosObtenidosEnPartida.put(logN2); // vuelve a jugar
		System.out.println();
		System.out.println("3ra victoria en nivel 2");
		nuevosLogros = user.cotejarLogros(logrosObtenidosEnPartida);
		assertEquals(0, nuevosLogros.length()); // Comprobamos que todavia no ha desbloqueado el logro

		logrosObtenidosEnPartida = new JSONArray();
		logrosObtenidosEnPartida.put(logN2); // vuelve a jugar
		System.out.println();
		System.out.println("4ta victoria en nivel 2");
		nuevosLogros = user.cotejarLogros(logrosObtenidosEnPartida);
		assertEquals(0, nuevosLogros.length()); // Comprobamos que todavia no ha desbloqueado el logro

		logrosObtenidosEnPartida = new JSONArray();
		logrosObtenidosEnPartida.put(logN2); // vuelve a jugar
		System.out.println();
		System.out.println("5ta victoria en nivel 2");
		nuevosLogros = user.cotejarLogros(logrosObtenidosEnPartida);
		assertEquals(1, nuevosLogros.length()); // Comprobamos que todavia no ha desbloqueado el logro
		assertEquals("ganarMismoNivel2", nuevosLogros.getJSONObject(0).get("nombre"));

	}

	@Test
	public void testActualizarProgreso() throws ParseException {
		Usuario user = new Usuario();
		System.out.println("---------------------------Prueba actualizarProgreso---------------------------");
		ArrayList<LogroObtenido> userLogros = user.getListaLogros();
		Date fechaActual = new SimpleDateFormat("yyyy-MM-dd")
				.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern(" yyyy-mm-dd")));
		String sDate1 = "31/12/1998";
		Date fechaDefault = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		/* Simulamos que un usuario ya tiene algunos logros obtenidos o en proceso */
		Logro l1 = new Logro(1, "ganarMismoNivel1", "gana 5 partidas del nivel 1 para obtener este logro", 5);
		Logro l2 = new Logro(2, "ganarMismoNivel2", "gana 5 partidas del nivel 2 para obtener este logro", 5);
		Logro l3 = new Logro(3, "ganarMismoNivel3", "gana 5 partidas del nivel 3 para obtener este logro", 5);
		LogroObtenido lo1 = new LogroObtenido(fechaActual, l1, 80);
		LogroObtenido lo2 = new LogroObtenido(fechaDefault, l2, 0);
		LogroObtenido lo3 = new LogroObtenido(fechaActual, l3, 100);
		userLogros.add(lo1);
		userLogros.add(lo2);
		userLogros.add(lo3);

		/* Cada vez que se gane una partida se le sumara al progreso (1/Objetivo)*100 */
		int aumento = (100 / l1.getObjetivo());

		user.actualizarProgreso(aumento, l1.getNombre());
		assertEquals(100, lo1.getProgreso(), 1);

		aumento = (100 / l2.getObjetivo());
		user.actualizarProgreso(aumento, l2.getNombre());
		assertEquals(20, lo2.getProgreso(), 1);

		aumento = (100 / l3.getObjetivo());
		user.actualizarProgreso(aumento, l3.getNombre());// Como este logro ya lo tiene desbloqueado, su progreso no
															// aumentara
		assertEquals(100, lo3.getProgreso(), 1);
		assertNotEquals(120, lo3.getProgreso(), 1);

	}

}
