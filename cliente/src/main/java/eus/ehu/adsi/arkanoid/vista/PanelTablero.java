package eus.ehu.adsi.arkanoid.vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import eus.ehu.adsi.arkanoid.controlador.Arkanoid;
import eus.ehu.adsi.arkanoid.modelo.Bloque;
import eus.ehu.adsi.arkanoid.modelo.Bola;
import eus.ehu.adsi.arkanoid.modelo.Config;
import eus.ehu.adsi.arkanoid.modelo.Paddle;
/**
 * One ball bouncing inside a rectangular box. 
 * All codes in one file. Poor design!
 */
// Extends JPanel, so as to override the paintComponent() for custom rendering codes. 
@SuppressWarnings("serial")
public class PanelTablero extends JPanel {
   // Container box's width and height
   private static final int BOX_WIDTH = Config.SCREEN_WIDTH - 20;
   private static final int BOX_HEIGHT = Config.SCREEN_HEIGHT - 115;
   
   //Bricks
   private ArrayList<Bloque> bricks = new ArrayList<Bloque>();
   private static final double sizeX = Config.BLOCK_WIDTH;
   private static final double sizeY = Config.BLOCK_HEIGHT;
   private int ladrilloSuerte;
  
   // Ball's properties
   private Bola bola = new Bola(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
   
   //Paddle
   private Paddle paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 150); 
   private static double sizeXPaddle = Config.PADDLE_WIDTH;
   private static final double sizeYPaddle = Config.PADDLE_HEIGHT;
  
   /** Constructor to create the UI components and init game objects. */
   public PanelTablero() {
      this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
      
      // Lista de bloques
      for (int iX = 0; iX < Config.COUNT_BLOCKS_X; ++iX) {
    	  for (int iY = 0; iY < Config.COUNT_BLOCKS_Y; ++iY) {
    		  bricks.add(new Bloque((iX + 1) * (sizeX + 2) - 10, (iY + 2) * (sizeY + 2) - 30));
		  }
	  }
      
      Random r = new Random();
      ladrilloSuerte = r.nextInt(bricks.size());
  
      // Start the ball bouncing (in its own thread)
      /*Thread gameThread = new Thread() {
         public void run() {
            while (true) { // Execute one update step
               // Calculate the ball's new position
            	bola.x += bola.velocityX;
            	bola.y += bola.velocityY;
                // Check if the ball moves over the bounds
                // If so, adjust the position and speed.
                if (bola.x - bola.radius < 0) {
                	bola.velocityX = -bola.velocityX; // Reflect along normal
                	bola.x = bola.radius; // Re-position the ball at the edge
                } else if (bola.x + bola.radius > BOX_WIDTH) {
                	bola.velocityX = -bola.velocityX;
                    bola.x = BOX_WIDTH - bola.radius;
                }
                // May cross both x and y bounds
                if (bola.y - bola.radius < 0) {
                	bola.velocityY = -bola.velocityY;
                    bola.y = bola.radius;
                } else if (bola.y + bola.radius > BOX_HEIGHT) {
                	bola.velocityY = -bola.velocityY;
                    bola.y = BOX_HEIGHT - bola.radius;
                }
                // Refresh the display
                repaint(); // Callback paintComponent()
                // Delay for timing control and give other threads a chance
                try {
                    Thread.sleep(10);  // milliseconds
                } catch (InterruptedException ex) { }
            }
         }
      };
      gameThread.start();  // Callback run()*/
      
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
    	  if (i == ladrilloSuerte) g.setColor(Config.LUCK_BRICK_COLOR);
    	  else g.setColor(Config.BRICK_COLOR);
    	  g.fillRect((int) brick.left(), (int) brick.top(), (int) sizeX, (int) sizeY);
  		  i++;
	  }
  
      // Draw the ball
      drawBola(g, bola);
      
      // Draw paddle
      drawPaddle(g, paddle);
      
   }
   
   public void updateTablero(Object arg, Graphics g) {
	   System.out.println(arg);
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
	   
	   //repaint();
	   //revalidate();
   }
   
   private void drawBola(Graphics g, Bola b) {
	   g.setColor(Config.BALL_COLOR);
	   g.fillOval((int) b.left(), (int) b.top(), (int) b.radius * 2,(int) b.radius * 2);
   }
   
   private void drawPaddle(Graphics g, Paddle pl) {
	   g.setColor(Config.PADDLE_COLOR);
	   g.fillRect((int) (pl.left()), (int) (pl.top()), (int) sizeXPaddle, (int) sizeYPaddle);
   }
   
   private void drawBloqueRoto(Graphics g, Bloque bl) {
	   g.setColor(Config.BACKGROUND_COLOR);
	   g.fillRect((int) (bl.left()), (int) (bl.top()), (int) sizeX, (int) sizeY);
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
