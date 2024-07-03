package Health;

import java.awt.Graphics;

import ImageLoader.Assets;
import entities.Enemies;
import entities.MaxHealth;
import entities.Tower;

public class HealthBar 
{
	/*healthbar locations
	g.drawImage(Assets.healthBar[1], 54, 60, 50, 38, null);
	g.drawImage(Assets.healthBar[0], 50, 50, 60, 60, null);
	*/
	private Enemies entitiy;
	private Tower entity;
	private MaxHealth mainHel;
	
	private int x, y, health, healthSize, lateHealth, loc, sizes = 60;
	private double healthPer;
	
	public HealthBar(Enemies object)
	{
		entitiy = object;
		health = entitiy.getHealth();
		healthSize = 43;
		lateHealth = health;
		healthPer = (double) (healthSize)/100; //Gets Percentage
		loc = 0;
	}
	
	public HealthBar(Tower tower)
	{
		entity = tower;
		health = tower.getHealth();
		healthSize = 43;
		healthPer = (double) (healthSize)/100;
		lateHealth = health;
		loc = 1;
		x = entity.getX();
		y = entity.getY();
	}
	
	public HealthBar(MaxHealth helth)
	{
		mainHel = helth;
		health = mainHel.getHealth();
		healthSize = 43*3;
		healthPer = (double) (healthSize)/100;
		lateHealth = health;
		loc = 2;
		x = 360;
		y = 50;
		sizes = 180;
	}
	
	public void tick()
	{
		//changes HealhBar size
		checkHealth();
		
		if (loc==0)
		{	
			x = (int) entitiy.getX() + 35;
			y = (int) entitiy.getY() + 40;
		}
		if (loc==1)
		{
			y = (int) entity.getY() + 70;	
		}
	}
	
	public void render(Graphics g)
	{
		if (loc==0 || loc==1)
		{
			g.drawImage(Assets.healthBar[1], x+4, y+5, healthSize, sizes, null);
			g.drawImage(Assets.healthBar[0], x, y, sizes, sizes, null);
		}
		else
		{
			g.drawImage(Assets.healthBar[1], x+15, y+15, healthSize, sizes, null);
			g.drawImage(Assets.healthBar[0], x, y, sizes, sizes, null);
		
		}
	}
	
	//Changes HealthBar size
	public void checkHealth()
	{
		if (loc==0 && lateHealth!=entitiy.getHealth())
		{
			healthSize -= (int) ((100-(double)(entitiy.getHealth())/(double)(lateHealth)*100)*healthPer);
			lateHealth = entitiy.getHealth();
		}
		else if (loc==1 && lateHealth!=entity.getHealth())
		{
			healthSize -= (int) ((100-(double)(entity.getHealth())/(double)(lateHealth)*100)*healthPer);
			lateHealth = entity.getHealth();
		}
		else if (loc==2 && lateHealth!=mainHel.getHealth())
		{
			healthSize -= (int) ((100-(double)(mainHel.getHealth())/(double)(lateHealth)*100)*healthPer);
			lateHealth = mainHel.getHealth();
		}
		healthSize = healthSize<0 ? 0 : healthSize;
	}
}
