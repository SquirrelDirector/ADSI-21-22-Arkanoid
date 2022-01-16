package eus.ehu.adsi.arkanoid.modelo;

import java.beans.PropertyChangeEvent;
import java.util.Date;

public class Puntuacion {

	private Usuario usuario;
	private int nivel;
	private int puntuacion;
	private Date fecha;
	private int tiempo;

	public Puntuacion(Usuario pUsuario, int pNivel, int pPuntuacion, Date pFecha, int pTiempo) {
		this.usuario = pUsuario;
		this.nivel = pNivel;
		this.puntuacion = pPuntuacion;
		this.fecha = pFecha;
		this.tiempo = pTiempo;
	}

	public Usuario getUsuario(){
		return this.usuario;
	}
	public int getNivel(){
		return this.nivel;
	}
	public int getPuntuacion(){
		return this.puntuacion;
	}
	public Date getFecha(){
		return this.fecha;
	}
	public int getTiempo(){
		return this.tiempo;
	}
}