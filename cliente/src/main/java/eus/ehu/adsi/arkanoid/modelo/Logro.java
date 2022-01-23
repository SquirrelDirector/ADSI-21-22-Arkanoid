package eus.ehu.adsi.arkanoid.modelo;

public class Logro {

	private int idLogro;
	private String nombre;
	private String descripcion;

	private int objetivo;
	
	public Logro(int pIdLogro, String pNombre, String pDescripcion, int pObjetivo) {
		this.idLogro = pIdLogro;
		this.nombre = pNombre;
		this.descripcion = pDescripcion;
		this.objetivo = pObjetivo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public int getObjetivo() {
		return this.objetivo;
	}
	public int getId() {
		return this.idLogro;
	}

}