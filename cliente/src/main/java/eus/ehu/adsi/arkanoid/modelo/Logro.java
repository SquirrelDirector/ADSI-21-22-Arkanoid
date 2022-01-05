package eus.ehu.adsi.arkanoid.modelo;

public class Logro {

	private int idLogro;
	private String nombre;
	private String descripcion;

	public Logro(int pIdLogro, String pNombre, String pDescripcion) {
		this.idLogro = pIdLogro;
		this.nombre = pNombre;
		this.descripcion = pDescripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

}