package eus.ehu.adsi.arkanoid.modelo;

import java.awt.Desktop;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RRSSMail extends EnvioRRSS {

	@Override
	public void enviar(String mensaje) {
		try {
			String mensajeEncoded = URLEncoder.encode(mensaje, StandardCharsets.UTF_8.toString());
			String asuntoEncoded = URLEncoder.encode("Mira mis resultados en Arkanoid", StandardCharsets.UTF_8.toString());
	        Desktop.getDesktop().browse(new URL("mailto:mailto:?subject="+asuntoEncoded+"&body="+mensajeEncoded).toURI());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}