package eus.ehu.adsi.arkanoid.modelo;

public class Puntuacion {

	private Usuario usuario;
	private int nivel;
	private int puntuacion;
	private String fecha;
	private int tiempo;

	public Puntuacion(Usuario pUsuario, int pNivel, int pPuntuacion, String pFecha, int pTiempo) {
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
	public String getFecha(){
		return this.fecha;
	}
	public int getTiempo(){
		return this.tiempo;
	}
}