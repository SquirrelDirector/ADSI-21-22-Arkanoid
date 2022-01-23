package eus.ehu.adsi.arkanoid.modelo;

public class DestruccionBloque implements ComportamientoBloque {

	public DestruccionBloque() {

	}


	@Override
	public void romper() {
        Partida miPartida = Partida.getMiPartida();
        int numBloques = miPartida.getNumBloques();
        if (numBloques > 1) {
            //generar numero de bloques que se deben romper
            numBloques = (int) Math.floor((Math.random()*numBloques));
            
            for (int i = 0; i < numBloques; i++) {
                //generar posicion aleatoria del bloque a romper
                int pos = (int) Math.floor((Math.random()*miPartida.getNumBloques()));
                if (pos < miPartida.getNumBloques()) {
                	miPartida.romperBloque(pos);
                }
                
            }
        }
		
	}

}