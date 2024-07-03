package states;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ImageLoader.Assets;
import entities.Enemies;
import entities.Tower;
import gui.Game;
import towerControls.TowerBar;

public class GameState extends State 
{
	private boolean running;
	private Game game;
	
	private Tower tower1;

	private ArrayList<Enemies> enemy;
	
	private TowerBar currentBar;
	
	private Rectangle boundsRestart;
	
	private int restartX = 780, restartY = 15, restartWidth = 78, restartHeight = 38;
	
	public GameState(Game gme)
	{
		running = true;
		game = gme;
		init();
		
		boundsRestart = new Rectangle(restartX, restartY, restartWidth, restartHeight);
	}
	
	public void init()
	{
		currentBar = new TowerBar(game);
	}
	
	public void tick()
	{
		currentBar.tick();
		if (currentBar.getStartEnemies().getstartAdding() && currentBar.getMaxHealth().getDead())
		{
			State.setState(game.getEndState());
		}
	}
	
	public void render(Graphics g)
	{
		g.clearRect(0,  0, game.getWidth(), game.getHeight());
		g.drawImage(Assets.background, 0, 0, game.getWidth()-120, game.getHeight(), null);
		g.drawImage(Assets.towerHolder, game.getWidth()-125, 0, 125, game.getHeight(), null);
		g.drawImage(getDraw(), 780, 15, 80, 80, null); 		
		
		currentBar.render(g);
	}
	
	public BufferedImage getDraw()
	{
		BufferedImage drawImage = Assets.restart[0];
		if (game.getMouseManager().getBounds().intersects(boundsRestart))
		{
			drawImage = Assets.restart[1];
			if (game.getMouseManager().isClicked())
			{
				game.reInit();
			}
		}
		return drawImage;
	}
	
	public Tower getTower1()
	{
		return tower1;
	}
	
	public ArrayList<Enemies> getEnemy()
	{
		return enemy;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public TowerBar getTowerBar()
	{
		return currentBar;
	}
	
	public boolean getRunning()
	{
		return running;
	}
}
