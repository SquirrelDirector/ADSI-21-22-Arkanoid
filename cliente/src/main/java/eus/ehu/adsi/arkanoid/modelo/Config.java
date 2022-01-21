package eus.ehu.adsi.arkanoid.modelo;


import java.awt.Color;

public abstract class Config {

	public static final int SCREEN_WIDTH = 800;  
	public static final int SCREEN_HEIGHT = 600;  
	public static final double BALL_RADIUS = 10.0;  
	public static double BALL_VELOCITY = 2;  
	public static double PADDLE_WIDTH = 60.0;  
	public static final double PADDLE_HEIGHT = 20.0;  
	public static final double PADDLE_VELOCITY = 4;  
	public static final double BLOCK_WIDTH = 60.0;  
	public static final double BLOCK_HEIGHT = 20.0;  
	public static final int COUNT_BLOCKS_X = 11;  
	public static int COUNT_BLOCKS_Y = 4;  
	public static final int PLAYER_LIVES = 5;  
	public static final double FT_SLICE = 1.0;  
	public static final double FT_STEP = 1.0;   
	public static Color BACKGROUND_COLOR = new Color(255,0,0);  
	public static Color BALL_COLOR = new Color(255,181,0);  
	public static Color PADDLE_COLOR = new Color(78,255,0);  
	public static Color BRICK_COLOR = new Color(244,255,0);  
	public static final Color LUCK_BRICK_COLOR = Color.yellow; 
	public static String PATH_MUSICA = "/sonidoPersonalizar/Sonido1.wav";
	public static String PATH_PERFIL;  //TODO - Definir path por defecto


}