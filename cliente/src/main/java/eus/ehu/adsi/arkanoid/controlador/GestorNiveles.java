package eus.ehu.adsi.arkanoid.controlador;

public class GestorNiveles {

	private static GestorNiveles miGestorNiveles = null;
	private GestorNiveles() {
	}

	public static GestorNiveles getGestorNiveles() {
		if (miGestorNiveles == null) {
			miGestorNiveles = new GestorNiveles();
		}
		return miGestorNiveles;
	}

	/**
	 * 
	 * @param idNivel
	 */
	public Double[] obtenerDatosNivel(int idNivel) {
        String preg="SELECT Velocidad, Anchura, Num_Ladrillos FROM Nivel WHERE idNivel=%int%";
        ResultadoSQL rs = GestorDB.getGestorDB().execSQL(preg);
        Double DatosNivel[] = new Double[3];
        DatosNivel[1]=(Double) rs.get("Velocidad");
        DatosNivel[2]=(Double) rs.get("Anchura");
        DatosNivel[3]=(Double) rs.get("Num_Ladrillos");
        return DatosNivel;
    }

}