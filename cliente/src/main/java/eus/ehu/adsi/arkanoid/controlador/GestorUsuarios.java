package eus.ehu.adsi.arkanoid.controlador;

import org.json.JSONArray;
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
						+ "Atributos_Personalizado='"+atributosPersonalizacion+"' WHERE Email='"+emailUsuario+"';");
	}
	
	/**
	 * 
	 * @param emailUsuario
	 * @param pathAvatar
	 * @param nombreUsu
	 */
	public void actualizarDatosUsuDB(String email, String pathAvatar, String nombreUsu) {
		GestorDB.getGestorDB().execSQL("UPDATE usuario SET PathPerfil='"+pathAvatar+"', NombreUsuario='"+nombreUsu+"' WHERE Email = '"+email+"'");
	}

	/**
	 * 
	 * @param mail
	 * @param pass
	 */
	public JSONObject importarUsuario(String mail, String pass) {
		String preg="SELECT Contraseņa FROM Usuario WHERE Email = '"+mail+"';";
		ResultadoSQL res=GestorDB.getGestorDB().execSQL(preg);
		String usrPass=(String) res.get("Contraseņa");
		if (usrPass==null) //no existe tal usuario
			return null;
		if (!pass.equals(usrPass)) //el usuario no tiene tal contraseņa
			return null;
		
		preg="SELECT NombreUsuario, NivelDefault, PathMusica, PathPerfil, CodigoColorFondo, CodigoColorBola, CodigoColorPaddle, CodigoColorLadrillo, VelocidadCustom, AnchuraCustom, AceleracionCustom, NumLadrillosCustom FROM Usuario WHERE Email = '"+mail+"';";
		res=GestorDB.getGestorDB().execSQL(preg);
		JSONObject datos=new JSONObject();
		
		datos.put("email", mail);
		String nombre=(String) res.get("NombreUsuario");
		datos.put("nombreUsuario", nombre);
		
		datos.put("atributosPersonalizado", res.get("Atributos_Personalizado"));
		datos.put("pathPerfil", res.get("PathPerfil"));
		datos.put("pathMusica", res.get("PathMusica"));
		
		datos.put("codigoColorFondo", res.get("CodigoColorFondo"));
		datos.put("codigoColorBola", res.get("CodigoColorBola"));
		datos.put("codigoColorLadrillo", res.get("CodigoColorLadrillo"));
		datos.put("codigoColorPaddle", res.get("CodigoColorPaddle"));
		
		datos.put("nivelDefault", res.get("NivelDefault"));
		
		preg="SELECT Nombre, IdLogro, Descripcion, FechaObtencion, Progreso, Objetivo FROM TieneLogro NATURAL JOIN Logro WHERE Usuario ='"+nombre+"';";
		res=GestorDB.getGestorDB().execSQL(preg);
		JSONArray logros=new JSONArray();
		JSONObject logro;
		
		for (int i=0;i<res.longitud;i++){
			logro=new JSONObject();
			
			logro.put("nombre", res.get("Nombre"));
			logro.put("idLogro", res.get("IdLogro"));
			logro.put("descripcion", res.get("Descripcion"));
			logro.put("fechaObtencion", res.get("FechaObtencion"));
			logro.put("progreso", res.get("Progreso"));
			logro.put("objetivo", res.get("Objetivo"));
			
			logros.put(logro);
			res.next();
		}
		datos.put("logros", logros);
		
		preg="SELECT IdNivel, ValorFechaHora, Tiempo, Numero FROM Puntuacion WHERE Usuario = '"+nombre+"';";
		res=GestorDB.getGestorDB().execSQL(preg);
		JSONArray ranking=new JSONArray();
		JSONObject partida;
		
		for (int i=0;i<res.longitud;i++){
			partida=new JSONObject();
			
			partida.put("idNivel", res.get("IdNivel"));
			partida.put("valorFechaHora", res.get("ValorFechaHora"));
			partida.put("tiempo", res.get("Tiempo"));
			partida.put("numero", res.get("Numero"));
			
			ranking.put(partida);
			res.next();
		}
		datos.put("ranking", ranking);
		return datos;
	}

	/**
	 * 
	 * @param mail
	 */
	public boolean existeUsuario(String mail) {
		String preg="SELECT NombreUsuario FROM Usuario WHERE Email = '"+mail+"';";
		ResultadoSQL res=GestorDB.getGestorDB().execSQL(preg);
		return (res.get("NombreUsuario")!=null);
	}

	/**
	 * 
	 * @param usr
	 * @param mail
	 * @param pass
	 */
	public void crearUsuario(String usr, String mail, String pass) {
		//TODO - establecer valores predeterminados de personalizacion, sea por el propio SQL o por una nueva clase de modelo que los almacene
		String preg="INSERT INTO Usuario (Email, NombreUsuario, Contrasena) VALUES ('"+mail+"', '"+usr+"', '"+pass+"');";
		GestorDB.getGestorDB().execSQL(preg);
		
		preg="SELECT idLogro FROM Logro;";
		ResultadoSQL res=GestorDB.getGestorDB().execSQL(preg);
		String id;
		
		for (int i=0;i<res.longitud;i++){
			id=(String) res.get("idLogro");
			preg="INSERT INTO TieneLogro (Usuario, idLogro) VALUES ('"+mail+"', '"+id+"');";
			res.next();
		}
	}

	/**
	 * 
	 * @param mail
	 * @param pass
	 */
	public void cambiarContrasena(String mail, String pass) {
		String preg="UPDATE Usuario SET Contrasena="+pass+" WHERE Mail='"+mail+"';";
		GestorDB.getGestorDB().execSQL(preg);
	}

	public boolean existeNombre(String usr) {
		String preg="SELECT Email FROM Usuario WHERE NombreUsuario = '"+usr+"';";
		ResultadoSQL res=GestorDB.getGestorDB().execSQL(preg);
		return (res.get("Email")!=null);
	}

	/**
	 * 
	 * @param nombreUsu
	 */
	public boolean comprobarNombre(String nombreUsu) {
		boolean existe = false;
		ResultadoSQL resultado = GestorDB.getGestorDB().execSQL("SELECT NombreUsuario FROM usuario WHERE NombreUsuario='"+nombreUsu+"';");
		if (resultado.get("NombreUsuario").equals(nombreUsu)) existe = true;
		return existe;
	}

	public void cerrarCuenta(String mail) {
		String preg="DELETE FROM Usuario WHERE Email='"+mail+"';";
		GestorDB.getGestorDB().execSQL(preg);
	}
}