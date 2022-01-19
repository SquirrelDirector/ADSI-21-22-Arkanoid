package eus.ehu.adsi.arkanoid.modelo;

public class Cronometro implements Runnable {
	private Thread t;
    private int minutos;
    private int segundos;
    private volatile boolean stopped;
    
    public Cronometro() {
        this.t = new Thread(this, "Crono");
        this.t.start();
    }

    public int getMinutos() { return minutos; }
    public int getSegundos() { return segundos; }

    @Override
    public void run() {
        minutos = 0;
        segundos = 0;
        stopped = false;
        while(!stopped) {
            if(segundos == 60) {
                minutos++;
                segundos = 0;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { e.printStackTrace(); }
            segundos++;
        }
    }

    public boolean estaParado(){return stopped;}

    public void reset(){
        minutos = 0;
        segundos = 0;
        stopped = true;
    }
    
    public void parar() {
    	stopped = true;
    }
}
