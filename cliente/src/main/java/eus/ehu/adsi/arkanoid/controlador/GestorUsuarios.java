package eus.ehu.adsi.arkanoid.controlador;

import org.json.JSONObject;

public class GestorUsuarios {

	private static GestorUsuarios miGestorUsuario;

	private GestorUsuarios() {}

	public static GestorUsuarios getGestorUsuario() {
		if (miGestorUsuario == null) {
			miGestorUsuario = new GestorUsuarios();
		}
		return miGestorUsuario;
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
	public void actualizarPersonalizacion(String emailUsuario, String pathMusica, String colorFondo, String colorBola, String colorPaddle, String colorLadrillo, String atributosPersonalizacion) {
		GestorDB.getGestorDB().ejecutarCambio("UPDATE Usuario SET PathMusica="+pathMusica+", CodigoColorFondo="+colorFondo+", CodigoColorBola="+colorBola+", CodigoColorPaddle="+colorPaddle+", CodigoColorLadrillo="+colorLadrillo+", Atributo_Personalizado="+atributosPersonalizacion+" WHERE email = "+emailUsuario+"");
	}

	/**
	 * 
	 * @param mail
	 * @param pass
	 */
	public JSONObject importarUsuario(String mail, String pass) {
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