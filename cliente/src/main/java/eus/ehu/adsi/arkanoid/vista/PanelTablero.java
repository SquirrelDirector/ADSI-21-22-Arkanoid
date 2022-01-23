package eus.ehu.adsi.arkanoid.vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.modelo.Bloque;
import eus.ehu.adsi.arkanoid.modelo.BloqueSuerte;
import eus.ehu.adsi.arkanoid.modelo.Bola;
import eus.ehu.adsi.arkanoid.modelo.Config;
import eus.ehu.adsi.arkanoid.modelo.Paddle;
import eus.ehu.adsi.arkanoid.modelo.Partida;
/**
 * One ball bouncing inside a rectangular box. 
 * All codes in one file. Poor design!
 */
// Extends JPanel, so as to override the paintComponent() for custom rendering codes. 
@SuppressWarnings("serial")
public class PanelTablero extends JPanel {
   // Container box's width and height
   private static final int BOX_WIDTH = Config.SCREEN_WIDTH;
   private static final int BOX_HEIGHT = Config.SCREEN_HEIGHT;
   
   //Bricks
   private ArrayList<Bloque> bricks;
   private static final double sizeX = Config.BLOCK_WIDTH;
   private static final double sizeY = Config.BLOCK_HEIGHT;
  
   // Ball's properties
   private Bola bola = new Bola(Config.SCREEN_WIDTH / 2 - 10, Config.SCREEN_HEIGHT / 2 - 115);
   
   //Paddle
   //private Paddle paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 150); 
   private Paddle paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 50);
   
   private static double sizeXPaddle = Config.PADDLE_WIDTH;
   private static final double sizeYPaddle = Config.PADDLE_HEIGHT;
  
   /** Constructor to create the UI components and init game objects. */
   public PanelTablero() {
      this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
      
      bricks = Partida.getMiPartida().getBloques();
   }
  
   /** Custom rendering codes for drawing the JPanel */
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);    // Paint background
  
      // Draw the box
      g.setColor(Config.BACKGROUND_COLOR);
      g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
      
      // Draw bricks
      int i = 0;
      for (Bloque brick : bricks) {
    	  if (brick instanceof BloqueSuerte) g.setColor(Config.LUCK_BRICK_COLOR);
    	  else g.setColor(Config.BRICK_COLOR);
    	  g.fillRect((int) brick.left()-30+(int)sizeX/2, (int) brick.top()-5+(int)sizeY/2, (int) sizeX, (int) sizeY);
  		  i++;
	  }
  
      // Draw the ball
      drawBola(g, bola);
      
      // Draw paddle
      drawPaddle(g, paddle);
      
   }
   
   public void updateTablero(Object arg, Graphics g) {
	   if (arg instanceof Bloque) {
		   Bloque bl = (Bloque) arg;
		   drawBloqueRoto(g, bl);
	   } else if (arg instanceof Bola) {
		   Bola b = (Bola) arg;
		   drawBola(g, b);
	   } else if (arg instanceof Paddle) {
		   Paddle p = (Paddle) arg;
		   drawPaddle(g, p);
	   }
	   
	   repaint();
	   revalidate();
   }
   
   private void drawBola(Graphics g, Bola b) { 
	   //Borrar bola
	   g.setColor(Config.BACKGROUND_COLOR);
	   g.fillOval((int) bola.left(), (int) bola.top(), (int) bola.radius * 2,(int) bola.radius * 2);
	   
	   //Redibujar bola
	   g.setColor(Config.BALL_COLOR);
	   g.fillOval((int) b.left(), (int) b.top(), (int) b.radius * 2,(int) b.radius * 2);

	   //Actualizar posicion y radio de la bola
	   //bola.x = b.x;
	   //bola.y = b.y;
	   //bola.radius = b.radius;
	   bola = b;
	   
   }
   
   private void drawPaddle(Graphics g, Paddle pl) {
	   g.setColor(Config.BACKGROUND_COLOR);
	   g.fillRect((int) (paddle.left()), (int) (paddle.top()), (int) paddle.getSize(), (int) sizeYPaddle);
	   
	   g.setColor(Config.PADDLE_COLOR);
	   g.fillRect((int) (pl.left()), (int) (pl.top()), (int) pl.getSize(), (int) sizeYPaddle);

	   //paddle.x = pl.x;
	   //paddle.y = pl.y;
	   //pl.getSize();
	   paddle = pl;
   }
   
   private void drawBloqueRoto(Graphics g, Bloque bl) {
	   g.setColor(Config.BACKGROUND_COLOR);
	   g.fillRect((int) (bl.left()-30), (int) (bl.top()-5), (int) sizeX, (int) sizeY);
   }
   
   public void moverPaddle(KeyEvent event) {
	   switch (event.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			System.out.println("izquierda");
			Arkanoid.getArkanoid().moverPaddleLeft();
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("derecha");
			Arkanoid.getArkanoid().moverPaddleRight();
			break;
		default:
			break;
		}
	   Arkanoid.getArkanoid().updatePaddle();
   }
   
   public void pararPaddle(KeyEvent event) {
	   switch (event.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			Arkanoid.getArkanoid().pararPaddle();
			break;
		default:
			break;
		}
	   Arkanoid.getArkanoid().updatePaddle();
   }
}
