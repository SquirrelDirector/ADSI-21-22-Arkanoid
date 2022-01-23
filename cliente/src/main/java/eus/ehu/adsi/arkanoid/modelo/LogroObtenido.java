package eus.ehu.adsi.arkanoid.modelo;

import java.util.Date;

public class LogroObtenido {

	private Date fecha;
	private Logro logro;
	private int progreso;

	public LogroObtenido(Date pFecha, Logro pLogro, int pProgreso) {
		this.fecha = pFecha;
		this.logro = pLogro;
		this.progreso = pProgreso;
	}

	public Date getFechaObtencion() {
		return this.fecha;
	}

	public String getDescripcion() {
		return this.logro.getDescripcion();
	}

	public int getProgreso() {
		return this.progreso;
	}
	
	public int getObjetivo() {
		return logro.getObjetivo();
	}

	public String getNombre() {
		return this.logro.getNombre();
	}

	/**
	 * 
	 * @param nombreLogro
	 */
	public boolean esLogro(String nombreLogro) {
		return this.getNombre().equals(nombreLogro);
	}
	public void actualizarProgreso(int num) {
		if(this.progreso<100) {
			this.progreso = this.progreso + (num);	
		}
		
	}
	public Logro getLogro() {
		return logro;
	}
}