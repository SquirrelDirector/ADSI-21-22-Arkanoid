package eus.ehu.adsi.arkanoid.modelo;

public class EnvioRRSSFactory {
	public EnvioRRSSFactory() {
		
	}

	/**
	 * 
	 * @param redSocial
	 */
	public EnvioRRSS getEnvioRRSS(String redSocial) {
		if(redSocial.equals("twitter")) {
			return new RRSSTwitter();
		}else if (redSocial.equals("facebook")) {
			return new RRSSFacebook();
		}else if(redSocial.equals("email")) {
			return new RRSSMail();
		}else {
			throw new UnsupportedOperationException("No se puede compartir con la red social "+redSocial);
		}
	}

}