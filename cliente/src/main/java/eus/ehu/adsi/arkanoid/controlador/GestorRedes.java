package eus.ehu.adsi.arkanoid.controlador;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONArray;

import eus.ehu.adsi.arkanoid.modelo.EnvioRRSS;
import eus.ehu.adsi.arkanoid.modelo.EnvioRRSSFactory;

public class GestorRedes {

	private static GestorRedes miGestorRedes;

	private GestorRedes() {
	}

	public static GestorRedes getGestorRedes() {
		if(miGestorRedes==null)
			miGestorRedes=new GestorRedes();
		return miGestorRedes;
	}

	/**
	 * 
	 * @param redSocial
	 * @param puntuacionPartida
	 * @param tiempoPartida
	 * @param mejorPuntuacion
	 * @param mejorTiempo
	 * @param logro
	 */
	public void publicarResultados(String redSocial, int puntuacionPartida, int tiempoPartida, int mejorPuntuacion, int mejorTiempo, JSONArray logro) {
		String mensaje="He conseguido "+puntuacionPartida+" puntos con un tiempo de "+tiempoPartida+" segs, siendo mi mejor puntuación de "+mejorPuntuacion+" y mi mejor tiempo "+mejorTiempo+"segs.";
		for(int i=0;i<logro.length(); i++) {
			if(i==0) {
				mensaje+="He conseguido los siguientes logros: ";
			}
			if(i!=logro.length()-1) {
				mensaje+=logro.getJSONObject(i).getString("nombreLogro")+", ";	
			}else {
				mensaje+=logro.getJSONObject(i).getString("nombreLogro");
			}
		}
		EnvioRRSS envio = new EnvioRRSSFactory().getEnvioRRSS(redSocial);
		envio.enviar(mensaje);
	}

	/**
	 * 
	 * @param mail
	 * @throws MessagingException 
	 */
	public void enviarRecuperacion(String mail, String cod) throws MessagingException {
		//https://netcorecloud.com/tutorials/send-email-in-java-using-gmail-smtp/
        String from = "noreply.arkanoid@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
        	return new PasswordAuthentication("noreply.arkanoid@gmail.com", "H43eVq5xDRVrNsuj");

            }

        });
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        message.setSubject("Código de cambio de contraseña");
        message.setText("Su código de cambio de contraseña es "+cod+", introdúzcalo en la aplicación para cambiar su contraseña. El código será válido mientras el programa siga ejecutándose y no se envíe otro correo nuevo.");
        System.out.println("sending...");
        Transport.send(message);
	}

}