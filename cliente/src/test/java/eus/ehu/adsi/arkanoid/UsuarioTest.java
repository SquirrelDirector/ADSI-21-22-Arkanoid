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
		System.out.println("Prueba1");
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

		JSONArray logrosObtenidosEnPartida = new JSONArray();
		JSONObject logN1 = new JSONObject();
		logN1.put("nombre", "ganarMismoNivel1");

		JSONObject logN2 = new JSONObject();
		logN2.put("nombre", "ganarMismoNivel2");

		JSONObject logN3 = new JSONObject();
		logN3.put("nombre", "ganarMismoNivel3");
		CatalogoLogros catL = CatalogoLogros.getMiCatalogoLogros();
		catL.addLogro(l1);
		catL.addLogro(l2);
		catL.addLogro(l3);
		System.out.println(l1.getObjetivo());
		logrosObtenidosEnPartida.put(logN1);
		logrosObtenidosEnPartida.put(logN2);
		logrosObtenidosEnPartida.put(logN3);
			
		/*
		 * for(LogroObtenido i : userLogros) { System.out.println(i.getNombre()); }
		 */
		System.out.println(user.cotejarLogros(logrosObtenidosEnPartida));

	}

	@Test
	public void testActualizarProgreso() {
		
	}

}
