package collisionDetection;

import java.awt.Rectangle;
import java.util.ArrayList;

import entities.Enemies;
import entities.MaxHealth;
import entities.Tower;

public class Collision 
{
	private int line, index;
	private ArrayList<Enemies> enemy;
	private Tower collisionTower;
	
	private MaxHealth maxHealth;
	
	public Collision(int row, int i, ArrayList<Enemies> enemies, Tower tower)
	{
		line = row;
		index = i;
		enemy = enemies;
		
		collisionTower = tower;
		tick();
	}
	
	public void tick()
	{
		if (checkCollision())
		{
			//collisionTower.setMoney(enemy.get(index).getAmount());
			enemy.remove(index);
			System.out.println("Collided || Dead");

			maxHealth = collisionTower.getTowerBr().getMaxHealth();
			
			collisionTower.setHealth(collisionTower.getHealth()-1);
			maxHealth.setHealth(maxHealth.getHealth()-1);
		}
	}
	
	//Sees if there is a collision
	public boolean checkCollision()
	{
		boolean collision = false;
		if (index<enemy.size())
		{
			if (line==1 && enemy.get(index).getBounds().intersects(collisionTower.getBounds()))
			{
				collision = true;
			}
		}
		return collision;
	}
	
	//Makes bounds
	public Rectangle bounds(int x, int y, int width, int height)
	{
		Rectangle rectangle = new Rectangle();
		rectangle.x = x;
		rectangle.y = y;
		rectangle.width = width;
		rectangle.height = height;
		return rectangle;
	}
	
	
	//Sees if person is alive
	public boolean checkAlive()
	{
		return collisionTower.getState().getEnemy().get(index).getHealth()>0;
	}
}
