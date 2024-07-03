package states;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ImageLoader.Assets;
import gui.Game;

public class EndState extends State
{
	private final int DEF_WIDTH = 60, DEF_HEIGHT = 60;
	private Game game;
	private Rectangle bounds;
	private BufferedImage drawImage;
	private int time = 0, currentSleep = 0, currentAdd = 1, sleepingSize = 200, waveNumWidth = 550, waveNumHeight = 510, waveMaxNumHeight = 600,
					rectXRestart = 700, rectYRestart = 400, rectWidthRestart = 98, rectHeightRestart = 45, restartSize = 100;	
	
	public EndState(Game gme)
	{
		game = gme;
		bounds = new Rectangle(rectXRestart, rectYRestart, rectWidthRestart, rectHeightRestart);
	}
	
	public void render(Graphics g) 
	{
		g.drawImage(Assets.endScreen, 0, 0, game.getWidth(), game.getHeight(), null);
		
		g.drawImage(drawImage, rectXRestart, rectYRestart, restartSize, restartSize, null);
		
		drawLocation(game.getWaveNum(), waveNumWidth, waveNumHeight, g);
		drawLocation(game.getMaxWave(), waveNumWidth, waveMaxNumHeight, g);
		
		g.drawImage(Assets.sleepingCat[currentSleep], game.getWidth()/2-30, game.getHeight()/2-50, sleepingSize, sleepingSize, null);
	}
	
	public void drawLocation(int value, int xLoc, int yLoc, Graphics g)
	{
		int loc = xLoc;
		int locY = yLoc;
		for (int i=value; i>0; i/=10)
		{
			g.drawImage(Assets.numbers[i%10], loc, locY, DEF_WIDTH, DEF_HEIGHT, null);
			loc-=25;
		}
	}

	public void tick() 
	{
		drawImage = Assets.restart[0];
		if (game.getMouseManager().getBounds().intersects(bounds))
		{
			drawImage = Assets.restart[1];
			if (game.getMouseManager().isClicked())
			{
				game.reInit();
			}
		}
		checkSleepDraw();
	}
	
	public void checkSleepDraw()
	{
		time++;
		//Changes sleeping animation every 60 seconds
		if (time>60)
		{
			time = 0;
			if (currentSleep>=Assets.sleepingCat.length-1)
			{
				currentSleep = 0;
			}
			else
			{
				currentSleep += currentAdd;
			}
		}
	}

}
