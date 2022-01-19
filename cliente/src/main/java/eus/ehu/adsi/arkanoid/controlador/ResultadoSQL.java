package eus.ehu.adsi.arkanoid.controlador; 
 
import java.util.ArrayList; 
import java.util.HashMap; 
 
public class ResultadoSQL { 
	HashMap <String, ArrayList<Object>> map; 
	int indice=0; 
	int longitud=0; 
	public ResultadoSQL(int pLongitud) { 
		map = new HashMap<String,ArrayList<Object>>(); 
		longitud=pLongitud; 
	} 
 
	 
	public void next() { 
			indice++; 
	} 
	 
	public boolean hasNext() { 
		return indice<longitud; 
	} 
 
	public Object get(String clave) { 
		ArrayList<Object> lista= map.get(clave);  
		return lista.get(indice); 
	} 
 
 
	public void asignar(String clave, Object valor) { 
		ArrayList<Object> lista = map.get(clave); 
		if(lista!=null) { 
			lista.add(valor); 
		}else { 
			lista=new ArrayList<Object>(); 
			lista.add(valor); 
		} 
		map.put(clave, lista); 
		 
	} 
}