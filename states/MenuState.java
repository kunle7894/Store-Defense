package states;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ImageLoader.Assets;
import gui.Game;
import inputs.MouseManager;

public class MenuState extends State 
{
	private Game game;
	private BufferedImage drawImage, drawImageInst;
	private boolean clicked = false, clickedInstr = false;
	private int buttonX, buttonY, buttonWidth, buttonHeight, tankX = 0, tankXAdder = 5, tankY = 0, tankYAdder = 1, speedTank = 5;
	
	private Rectangle rectBounds = new Rectangle(), rectBoundsInstr = new Rectangle();
	
	private MouseManager manager;
	
	public MenuState(Game gme)
	{
		game = gme;
		manager = game.getMouseManager();
		drawImage = Assets.startButton[0];
		
		buttonX = game.getWidth()/2-60;
		buttonY = game.getHeight()/2-80;
		buttonWidth = 120;
		buttonHeight = 180;
		createRect();
		createRectInst();
	}
	
	public void tick()
	{
		changeStart();
		changeInstru();
		checkClick();
		if (clicked)
		{
			State.setState(game.getGameState());
			clicked = false;
		}
		if (clickedInstr)
		{
			State.setState(game.getIntroState());
			clickedInstr = false;
		}
		
		tankX+=tankXAdder;
		tankY+=tankYAdder;
		if (tankX>950)
		{
			tankXAdder = -speedTank;
		}
		if (tankX<0)
		{
			tankXAdder = speedTank;
		}
		if (tankY>650)
		{
			tankYAdder = -speedTank;
		}
		if (tankY<0)
		{
			tankYAdder = speedTank;
		}
	}
	
	
	public void render(Graphics g)
	{
		g.clearRect(0, 0, game.getWidth(), game.getHeight());
		
		g.drawImage(Assets.tank[0], tankX, tankY, buttonWidth, buttonHeight, null);
		g.drawImage(Assets.title, game.getWidth()/2-150, 30, 300, 300, null);
		g.drawImage(drawImage, buttonX, buttonY, buttonWidth, buttonHeight, null);
		
		g.drawImage(drawImageInst, buttonX, buttonY+200, buttonWidth, buttonHeight, null);
	}
	
	public void createRect()
	{
		rectBounds.x = buttonX;
		rectBounds.y = buttonY;
		rectBounds.width = buttonWidth;
		rectBounds.height = buttonHeight;
	}
	
	public void createRectInst()
	{
		rectBoundsInstr.x = buttonX;
		rectBoundsInstr.y = buttonY+200;
		rectBoundsInstr.width = buttonWidth;
		rectBoundsInstr.height = buttonHeight-20;
	}
	
	public void changeStart()
	{
		drawImage = Assets.startButton[0];
		if (manager.getBounds().intersects(rectBounds))
		{
			drawImage = Assets.startButton[1];
		}
	}
	
	public void changeInstru()
	{
		drawImageInst = Assets.instructions[0];
		if (manager.getBounds().intersects(rectBoundsInstr))
		{
			drawImageInst = Assets.instructions[1];
		}
	}
	
	public void checkClick()
	{
		//System.out.println(manager.isClicked());
		if (manager.isClicked() && manager.getBounds().intersects(rectBounds))
		{
			clicked = true;
		}
		if (manager.isClicked() && manager.getBounds().intersects(rectBoundsInstr))
		{
			clickedInstr = true;
		}
	}
	
	public boolean isClicked()
	{
		return clicked;
	}
}
