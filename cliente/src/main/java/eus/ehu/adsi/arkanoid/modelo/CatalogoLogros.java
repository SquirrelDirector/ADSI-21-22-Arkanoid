package eus.ehu.adsi.arkanoid.modelo;

public class CatalogoLogros {

	private Coleccion<Logro> listaLogros;
	private static CatalogoLogros miCatalogoLogros;

	private CatalogoLogros() {
		// TODO - implement CatalogoLogros.CatalogoLogros
		throw new UnsupportedOperationException();
	}

	public static CatalogoLogros getMiCatalogoLogros() {
		return this.miCatalogoLogros;
	}

	/**
	 * 
	 * @param nombre
	 */
	public boolean buscarLogro(String nombre) {
		// TODO - implement CatalogoLogros.buscarLogro
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public JSON getInfoLogro(String nombre) {
		// TODO - implement CatalogoLogros.getInfoLogro
		throw new UnsupportedOperationException();
	}

}