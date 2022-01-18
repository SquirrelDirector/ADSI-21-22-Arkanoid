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
		GestorDB.getGestorDB().execSQL("UPDATE usuario SET PathMusica='"+pathMusica+"', CodigoColorFondo='"+colorFondo+"', "
				+ "CodigoColorBola='"+colorBola+"', CodigoColorPaddle='"+colorPaddle+"', CodigoColorLadrillo='"+colorLadrillo+"', "
						+ "Atributos_Personalizado='"+atributosPersonalizacion+"' WHERE Email='"+emailUsuario+"'");
	}
	
	/**
	 * 
	 * @param emailUsuario
	 * @param pathAvatar
	 * @param nombreUsu
	 */
	public void actualizarDatosUsuDB(String email, String pathAvatar, String nombreUsu) {
		GestorDB.getGestorDB().execSQL("UPDATE usuario SET PathPerfil='"+pathAvatar+"', NombreUsuario='"+nombreUsu+"' WHERE "
				+ "Email = '"+email+"'");
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

	/**
	 * 
	 * @param nombreUsu
	 */
	public boolean comprobarNombre(String nombreUsu) {
		boolean existe = false;
		ResultadoSQL resultado = GestorDB.getGestorDB().execSQL("SELECT NombreUsuario FROM usuario WHERE NombreUsuario='"+nombreUsu+"'");
		if (resultado.get("NombreUsuario").equals(nombreUsu)) existe = true;
		return existe;
	}
}