package towerControls;

import java.awt.Graphics;
import java.awt.Rectangle;

import ImageLoader.Assets;
import entities.Tower;
import inputs.MouseManager;

public class PowerChanger 
{
	private final int X=950, Y=20, DEF_WIDTH=30, DEF_HEIGHT=30;
	
	private boolean startTick, addClickAtt = false, addClickDef = false, addClickSpd = false;
	
	private MouseManager manager;
	
	private Tower tower;
	
	private Rectangle defRectAdd, ataRectAdd, speedRectAdd;
	
	private int timePassedAdd = 0, minMoneyAtt = 1, minMoneyDef = 1, minMoneySpd = 1, minMoneyTimerAtt = 0, minMoneyTimerDef = 0, minMoneyTimerSpd = 0, drawAdder = 20;
	
	public PowerChanger(Tower twr)
	{
		tower = twr;
		manager = tower.getState().getGame().getMouseManager();
		startTick = false;
		createPlusMinusArea();
	}
	
	public void tick()
	{
		if (manager.isClicked())
		{
			//Adds PowerChanger if in bounds
			if (manager.getBounds().intersects(tower.getBounds()))
			{
				startTick = true;
				manager.setIsDifferent(true);
			
			}
			//Gets rid of bounds if out of bounds
			else if (startTick && manager.getX()<880)
			{
				startTick = false;
				manager.setIsDifferent(false);
			}
			//Sees if One of the buttons are clicked
			else
			{
				checkIntersection();
			}
		}
	}
	
	public void render(Graphics g)
	{
		if (startTick)
		{
			//Draws PowerScreen image
			g.drawImage(Assets.powerScreen, tower.getState().getGame().getWidth()-125, 0, 125, tower.getState().getGame().getHeight(), null);
			
			//Tower level
			g.drawImage(Assets.numbers[tower.getRow()], X, Y, DEF_WIDTH, DEF_HEIGHT, null);
			
			//Attack level
			drawLocation(tower.getAttack(), X+drawAdder, Y+116, g);
			drawLocation(minMoneyAtt, X+15, Y+228, g);
			//Defense level
			drawLocation(tower.getDefense(), X+drawAdder, Y+256, g);
			drawLocation(minMoneyDef, X+15, Y+354, g);
			//Speed Level
			drawLocationDouble((int) (tower.getSpeed()*10), X+23, Y+376, g);
			drawLocation(minMoneySpd, X+15, Y+476, g);
			//Health Amount
			drawLocation(tower.getHealth(), X, Y+500, g);
			
			//Tower Look
			g.drawImage(tower.getSprite()[0], X-62, Y+30, 100, 100, null);
			
			//Adds Clicked animation if one of the buttons are clicked
			if (addClickDef)
			{
				g.drawImage(Assets.clickedPower, 913, 322, 180, 180, null);
			}
			if (addClickAtt)
			{
				g.drawImage(Assets.clickedPower, 913, 179, 180, 180, null);
			}
			if (addClickSpd)
			{
				g.drawImage(Assets.clickedPower, 913, 452, 180, 180, null);
			}
			
			/*
			//Tower loc
			g.fillRect(919, 65, 40, 43);
			g.fillRect(943, 66, 40, 43);
			
			//Attack loc
			g.fillRect(919, 180, 40, 43);
			g.fillRect(944, 181, 40, 43);
			
			//Defense loc
			g.fillRect(919, 323, 40, 43);
			g.fillRect(944, 324, 40, 43);
				
			//Health loc
			g.fillRect(919, 454, 40, 43);
			g.fillRect(944, 455, 40, 43);
			*/
		}
	}
	
	public void checkIntersection()
	{
		//Looks for clicked if timePassed before last click is great enough
		if (timePassedAdd==0 && startTick)
		{	
			if (manager.getBounds().intersects(defRectAdd)  && tower.getMoney()>=minMoneyDef)
			{
				tower.setDefense(tower.getDefense()+3);
				timePassedAdd++;
				tower.setMoney(tower.getMoney()-minMoneyDef);
				tower.setAddedDamage(0);
				minMoneyTimerDef++;
				if (minMoneyTimerDef==5)
				{
					minMoneyTimerDef = 0;
					minMoneyDef++;
				}
				addClickDef = true;
			}
			if (manager.getBounds().intersects(ataRectAdd) && tower.getMoney()>=minMoneyAtt)
			{
				tower.setAttack(tower.getAttack()+1);
				timePassedAdd++;
				tower.setMoney(tower.getMoney()-minMoneyAtt);
				minMoneyTimerAtt++;
				if (minMoneyTimerAtt==5)
				{
					minMoneyTimerAtt = 0;
					minMoneyAtt++;
				}
				addClickAtt = true;
			}
			if (manager.getBounds().intersects(speedRectAdd) && tower.getMoney()>=minMoneySpd)
			{
				tower.setSpeed(tower.getSpeed()+1.0);
				tower.setMaxTime(tower.getMaxTime()-5);
				timePassedAdd++;
				tower.setMoney(tower.getMoney()-minMoneySpd);
				minMoneyTimerSpd++;
				if (minMoneyTimerSpd==5)
				{
					minMoneyTimerSpd = 0;
					minMoneySpd++;
				}
				addClickSpd = true;	
			}
		}
		else
		{
			timePassedAdd = timePassedAdd<10 ? timePassedAdd+1 : 0;
		}
		if (timePassedAdd==0)
		{
			addClickSpd = false;
			addClickDef = false;
			addClickAtt = false;
		}
	}
	
	//Draws Level on Side Bar
	public void drawLocation(int value, int xLoc, int yLoc, Graphics g)
	{
		int loc = xLoc;
		int locY = yLoc;
		for (int i=value; i>0; i/=10)
		{
			g.drawImage(Assets.numbers[i%10], loc, locY, DEF_WIDTH, DEF_HEIGHT, null);
			loc-=13;
		}
	}
	
	//Draws point levels for decimals like speed. 
	public void drawLocationDouble(int value, int xLoc, int yLoc, Graphics g)
	{
		boolean hit = false;
		int loc = xLoc;
		int locY = yLoc;
		//Draws value to the nearest tenth
		g.drawImage(Assets.numbers[value%10], loc, locY, DEF_WIDTH, DEF_HEIGHT, null);
		loc-=7;
		g.drawImage(Assets.dot, loc, locY-7, DEF_WIDTH, DEF_HEIGHT, null);
		loc-=16;
		value/=10;
		
		for (int i=value; i>0; i/=10)
		{
			hit  = true;
			g.drawImage(Assets.numbers[i%10], loc, locY, DEF_WIDTH, DEF_HEIGHT, null);
			loc-=8;
		}
		
		if (!hit)
		{
			g.drawImage(Assets.numbers[0], loc, locY, DEF_WIDTH, DEF_HEIGHT, null);
		}
	}
	
	public void createPlusMinusArea()
	{
		ataRectAdd = new Rectangle(933, 180, 40, 43);
		
		defRectAdd = new Rectangle(933, 323, 40, 43);
		
		speedRectAdd = new Rectangle(933, 454, 40, 43);
	}
	
	public boolean isStartTick()
	{
		return startTick;
	}
}
