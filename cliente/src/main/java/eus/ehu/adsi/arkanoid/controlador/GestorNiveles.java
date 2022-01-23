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
        String preg="SELECT Velocidad, Anchura, Num_Ladrillos FROM Nivel WHERE idNivel ="+idNivel+";";
        ResultadoSQL rs = GestorDB.getGestorDB().execSQL(preg);
        Double DatosNivel[] = new Double[3];
        double velocidad = 0;
        int bola = (int)rs.get("Velocidad");
        if (bola == 1) velocidad = 0.1;
		else if (bola == 2) velocidad = 0.11;
		else if (bola == 3) velocidad = 0.12;
		else if (bola == 4) velocidad = 0.13;
		else if (bola == 5) velocidad = 0.14;
		else if (bola == 6) velocidad = 0.15;
		else if (bola == 7) velocidad = 0.16;
		else if (bola == 8) velocidad = 0.17;
        DatosNivel[0]= velocidad;
        DatosNivel[1]= Double.parseDouble((String) rs.get("Anchura"));
        DatosNivel[2]= Double.parseDouble((String) rs.get("Num_Ladrillos"));
        return DatosNivel;
    }

}