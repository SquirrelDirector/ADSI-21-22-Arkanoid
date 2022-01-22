package eus.ehu.adsi.arkanoid.modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class Cronometro extends Observable implements Runnable {
    private Thread t;
    private int minutos;
    private int segundos;
    private volatile boolean stopped;

    public Cronometro() {
        this.t = new Thread(this, "Crono");
        this.t.start();
    }
    public String getMinutosFormat() {
    	if(minutos<10) {
    		return "0"+minutos;
    	}else {
    		return minutos+"";
    	}
    }
    
    public String getSegundosFormat() {
    	if(segundos<10) {
    		return "0"+segundos;
    	}else {
    		return segundos+"";
    	}
    }
    public int getMinutos() { return minutos; }
    public int getSegundos() { return segundos; }
    public int getSegundosTotales(){ return segundos+minutos*60; }

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
            setChanged();
            String tiempo = (segundos < 10) ? minutos+":0"+segundos : minutos+":"+segundos ;
            notifyObservers(tiempo);
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
        setChanged();
        String tiempo = (segundos < 10) ? minutos+":0"+segundos : minutos+":"+segundos ;
        notifyObservers(tiempo);
    }
    
    public void parar() {
    	stopped = true;
    }
}
