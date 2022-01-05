package eus.ehu.adsi.arkanoid.modelo;

public class DestruccionBloque implements ComportamientoBloque {

	public DestruccionBloque() {
		// TODO - implement DestruccionBloque.DestruccionBloque
		throw new UnsupportedOperationException();
	}


	@Override
	public void romper() {
        Partida miPartida = Partida.getMiPartida();
        //generar numero de bloques que se deben romper
        int numBloques = (int) Math.floor((Math.random()*8)+1);

        for (int i = 0; i < numBloques; i++) {
            //generar posicion aleatoria del bloque a romper
            int pos = (int) Math.floor((Math.random()*8)+1);

            miPartida.romperBloque(pos);
        }
		
	}

}