package eus.ehu.adsi.arkanoid.vista.claseObjetos;

import org.json.JSONObject;

public class BotonLogro extends Boton{
	private JSONObject logro;
	
	public BotonLogro(String txtBoton, JSONObject logro) {
		super(txtBoton);
		this.logro=logro;
	}
	
	public JSONObject getInfoLogroAsociado() {
		return logro;
	}

}
