package entities;

import java.awt.Graphics;

import Health.HealthBar;

public class MaxHealth 
{
	
	private int health;
	
	private HealthBar healthBar;
	
	private boolean dead = false;
	
	public MaxHealth(int hp)
	{
		health = hp;
		healthBar = new HealthBar(this);
	}
	
	public void render(Graphics g)
	{
		healthBar.render(g);
	}
	
	public void tick()
	{
		healthBar.tick();
		if (health<=0)
		{
			dead = true;
		}
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int heal)
	{
		health = heal;
	}
	
	public boolean getDead()
	{
		return dead;
	}
}
