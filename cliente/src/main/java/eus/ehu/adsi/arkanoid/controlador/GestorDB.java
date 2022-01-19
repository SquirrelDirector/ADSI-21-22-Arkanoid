<<<<<<< HEAD

package eus.ehu.adsi.arkanoid.controlador; 
 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.OutputStream; 
import java.io.PrintWriter; 
import java.io.UnsupportedEncodingException; 
import java.net.HttpURLConnection; 
import java.net.MalformedURLException; 
import java.net.Socket; 
import java.net.URL; 
import java.net.URLConnection; 
import java.net.URLEncoder; 
import java.util.List; 
import java.util.Map.Entry; 
 
import org.json.JSONArray; 
import org.json.JSONObject; 
 
public class GestorDB { 
 
	private static GestorDB miGestorDB; 
 
	private GestorDB() { 
	} 
 
	public static GestorDB getGestorDB() { 
		if(miGestorDB==null) { 
			miGestorDB = new GestorDB(); 
		} 
		return miGestorDB; 
	} 
 
	 
	public ResultadoSQL execSQL(String sql) { 
		ResultadoSQL rSQL = null; 
		JSONArray js = new JSONArray(getConsulta(sql)); 
		 
		if(js!=null) { 
			rSQL = new ResultadoSQL(js.length()); 
			for(int i=0; i<js.length(); i++){ 
				JSONObject jObj= (JSONObject)js.get(i); 
				for(Object clave : jObj.keySet()) { 
					rSQL.asignar(clave.toString(), jObj.get(clave.toString())); 
				} 
			} 
		} 
		 
		return rSQL; 
	} 
	 
	private String getConsulta(String sql) { 
		String respuesta=null; 
		String charset = "UTF-8"; 
		HttpURLConnection httpConnection; 
		try { 
			String query = String.format("query=%s", 
				    URLEncoder.encode(sql, charset)); 
			httpConnection= (HttpURLConnection) new URL("http://127.0.0.1").openConnection(); 
			enviarDatos(httpConnection, query, charset); 
			respuesta = recibirDatos(httpConnection); 
			 
		} catch (MalformedURLException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} catch (IOException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
		return respuesta; 
	} 
 
	private void enviarDatos(HttpURLConnection hurl, String consulta, String charset) throws UnsupportedEncodingException, IOException { 
		 
		hurl.setDoOutput(true); // Triggers POST 
		hurl.setRequestMethod("POST"); 
		 
		hurl.setRequestProperty("Accept-Charset", charset); 
		hurl.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset); 
 
		OutputStream output = hurl.getOutputStream(); 
		output.write(consulta.getBytes(charset)); 
	} 
	 
	private String recibirDatos(HttpURLConnection hurl) throws IOException { 
		//InputStream response = hurl.getInputStream(); 
		//int status = hurl.getResponseCode(); 
		BufferedReader in = new BufferedReader( 
                new InputStreamReader( 
                hurl.getInputStream())); 
		String respuesta=""; 
		String decodedString; 
		while ((decodedString = in.readLine()) != null) { 
			respuesta+=decodedString; 
		} 
		in.close(); 
		return respuesta; 
	}
=======
package eus.ehu.adsi.arkanoid.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

public class GestorDB {

	private static GestorDB miGestorDB;

	private GestorDB() {
	}

	public static GestorDB getGestorDB() {
		if(miGestorDB==null) {
			miGestorDB = new GestorDB();
		}
		return miGestorDB;
	}

	
	public ResultadoSQL execSQL(String sql) {
		ResultadoSQL rSQL = null;
		JSONArray js = new JSONArray(getConsulta(sql));
		
		if(js!=null) {
			rSQL = new ResultadoSQL(js.length());
			for(int i=0; i<js.length(); i++){
				JSONObject jObj= (JSONObject)js.get(i);
				for(Object clave : jObj.keySet()) {
					rSQL.asignar(clave.toString(), jObj.get(clave.toString()));
				}
			}
		}
		
		return rSQL;
	}
	
	private String getConsulta(String sql) {
		String respuesta=null;
		String charset = "UTF-8";
		HttpURLConnection httpConnection;
		try {
			String query = String.format("query=%s",
				    URLEncoder.encode(sql, charset));
			httpConnection= (HttpURLConnection) new URL("http://127.0.0.1").openConnection();
			enviarDatos(httpConnection, query, charset);
			respuesta = recibirDatos(httpConnection);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;
	}

	private void enviarDatos(HttpURLConnection hurl, String consulta, String charset) throws UnsupportedEncodingException, IOException {
		
		hurl.setDoOutput(true); // Triggers POST
		hurl.setRequestMethod("POST");
		
		hurl.setRequestProperty("Accept-Charset", charset);
		hurl.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

		OutputStream output = hurl.getOutputStream();
		output.write(consulta.getBytes(charset));
	}
	
	private String recibirDatos(HttpURLConnection hurl) throws IOException {
		//InputStream response = hurl.getInputStream();
		//int status = hurl.getResponseCode();
		BufferedReader in = new BufferedReader(
                new InputStreamReader(
                hurl.getInputStream()));
		String respuesta="";
		String decodedString;
		while ((decodedString = in.readLine()) != null) {
			respuesta+=decodedString;
		}
		in.close();
		return respuesta;
	}
>>>>>>> refs/heads/feature-cliente-base-datos
}