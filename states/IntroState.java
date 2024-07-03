package states;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ImageLoader.Assets;
import gui.Game;
import inputs.MouseManager;

public class IntroState extends State
{
	private Game game;
	private MouseManager manager;
	
	private int slideNum = 0, buttonWidth = 120, buttonHeight = 140, timer = 0, backX = 750, backNextY = 600, nextDoneX = 880, doneY = 150;
	private Rectangle backRect, nextRect, doneRect;
	private BufferedImage drawBack, drawNext, drawDone;
	
	public IntroState (Game gme)
	{
		game = gme;
		manager = game.getMouseManager();
		
		backRect = drawRect(backX, backNextY, buttonWidth-2, buttonHeight-75);
		nextRect = drawRect(nextDoneX, backNextY, buttonWidth-2, buttonHeight-75);
		doneRect = drawRect(nextDoneX, doneY, buttonWidth-2, buttonHeight-75);
		
	}
	
	public void render(Graphics g) 
	{
		g.drawImage(Assets.introSlides[slideNum], 0, 0, game.getWidth(), game.getHeight(), null);
		g.drawImage(drawBack, backX, backNextY, buttonWidth, buttonHeight, null);
		g.drawImage(drawNext, nextDoneX, backNextY, buttonWidth, buttonHeight, null);
		g.drawImage(drawDone, nextDoneX, doneY, buttonWidth, buttonHeight, null);
	}

	public void tick() 
	{
		checkDraw();
		if (clickNext())
		{
			setSlidePlus();
		}
		if (clickBack())
		{
			setSlideBack();
		}
		if (clickDone())
		{
			State.setState(game.getMenuState());
		}
		//Makes sure you don't click next or back to rapidly
		if (timer>0)
		{
			timer++;
			if (timer==30)
			{
				timer = 0;
			}
		}
	}
	
	public boolean clickNext()
	{
		boolean clicked = false;
		if (timer==0 && manager.getBounds().intersects(nextRect) && manager.isClicked())
		{
			clicked = true;
			timer = 1;
		}
		return clicked;
	}
	
	public boolean clickBack()
	{
		boolean clicked = false;
		if (timer==0 && manager.getBounds().intersects(backRect) && manager.isClicked())
		{
			clicked = true;
			timer = 1;
		}
		return clicked;
	}
	
	public boolean clickDone()
	{
		boolean clicked = false;
		if (manager.getBounds().intersects(doneRect) && manager.isClicked())
		{
			clicked = true;
		}
		return clicked;
	}
	
	public Rectangle drawRect(int x, int y, int width, int height)
	{
		return new Rectangle(x, y, width, height);
	}
	
	public void setSlidePlus()
	{
		slideNum = slideNum+1==Assets.introSlides.length ? 0 : slideNum+1;
	}

	public void setSlideBack()
	{
		slideNum = slideNum-1<0 ? Assets.introSlides.length-1 : slideNum-1;
	}
	
	public void checkDraw()
	{
		drawBack = Assets.back[0];
		drawNext = Assets.next[0];
		drawDone = Assets.done[0];
		if (manager.getBounds().intersects(backRect))
		{
			drawBack = Assets.back[1];
		}
		if (manager.getBounds().intersects(nextRect))
		{
			drawNext = Assets.next[1];
		}
		if (manager.getBounds().intersects(doneRect))
		{
			drawDone = Assets.done[1];
		}
	}
}
