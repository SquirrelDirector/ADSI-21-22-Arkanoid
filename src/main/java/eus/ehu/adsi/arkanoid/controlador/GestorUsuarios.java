package eus.ehu.adsi.arkanoid.controlador;

public class GestorUsuarios {

	private static GestorUsuarios miGestorUsuario;

	private GestorUsuarios() {
		// TODO - implement GestorUsuarios.GestorUsuarios
		throw new UnsupportedOperationException();
	}

	public static GestorUsuarios getGestorUsuario() {
		// TODO - implement GestorUsuarios.getGestorUsuario
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param emailUsuario
	 */
	public JSON obtenerPersonalizacion(int emailUsuario) {
		// TODO - implement GestorUsuarios.obtenerPersonalizacion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param emailUsuario
	 * @param pathMusica
	 * @param colorFondo
	 * @param colorBola
	 * @param colorPaddle
	 * @param colorLadrillo
	 */
	public void actualizarPersonalizacion(int emailUsuario, int pathMusica, int colorFondo, int colorBola, int colorPaddle, int colorLadrillo) {
		// TODO - implement GestorUsuarios.actualizarPersonalizacion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 * @param pass
	 */
	public JSON importarUsuario(String mail, String pass) {
		// TODO - implement GestorUsuarios.importarUsuario
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 */
	public boolean existeUsuario(String mail) {
		// TODO - implement GestorUsuarios.existeUsuario
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usr
	 * @param mail
	 * @param pass
	 */
	public void crearUsuario(String usr, String mail, String pass) {
		// TODO - implement GestorUsuarios.crearUsuario
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 * @param pass
	 */
	public void cambiarContrasena(String mail, String pass) {
		// TODO - implement GestorUsuarios.cambiarContrasena
		throw new UnsupportedOperationException();
	}

}