package entities;

import java.awt.Graphics;

import ImageLoader.Assets;

public class MoneyCounter
{
	private Tower tower;
	
	private int currentMoney;
	private int x, y, width, height, yMoney;
	
	private boolean needZero;
	
	public MoneyCounter(Tower twr, int curMon)
	{
		tower = twr;
		currentMoney = curMon;
		x = 20;
		y = tower.getY()-103;
		yMoney = y+26;
		width = 70;
		height = 70;
		needZero = true;
	}
	
	public void tick()
	{
		currentMoney = tower.getMoney();
	}
	
	public void render(Graphics g)
	{
		int loc = 58;
		g.drawImage(Assets.coin, x, y, width, height, null);
		if (needZero)
		{
			g.drawImage(Assets.numbers[0], loc, yMoney, x, x, null);
		}
		for (int i=currentMoney; i>0; i/=10)
		{
			g.drawImage(Assets.numbers[i%10], loc, yMoney, x, x, null);
			loc-=12;
			needZero = false;
		}
	}
	
	public Tower getTower()
	{
		return tower;
	}
	
	public int getMoney()
	{
		return currentMoney;
	}
	
}
