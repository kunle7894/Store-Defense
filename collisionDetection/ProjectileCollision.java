package collisionDetection;

import java.awt.Rectangle;
import java.util.ArrayList;

import Health.DefenseAdder;
import entities.EnemyProjectile;
import entities.Tower;

public class ProjectileCollision
{
	private int index;
	private ArrayList<EnemyProjectile> proj;
	private DefenseAdder manageDefense;
	private Tower tower;
	
	public ProjectileCollision(int idx, ArrayList<EnemyProjectile> prj, Tower tower1, double damage)
	{
		proj = prj;
		index = idx;
		tower = tower1;
		manageDefense = new DefenseAdder(tower1);
	}
	
	public void tick()
	{
		if (checkCollision())
		{
			tower.lowerHealth(manageDefense.adjustDamage(proj.get(index).getAttack()));
			proj.remove(index);
			System.out.println("Collided");
		}
	}
	
	//Checks to see if there is a collision
	public boolean checkCollision()
	{
		boolean collision = false;
		if (proj.get(index).getBounds().intersects(tower.getBounds()))
		{
			collision = true;
		}
		return collision;
	}
	
	public Rectangle bounds(int x, int y, int width, int height)
	{
		Rectangle rectangle = new Rectangle();
		rectangle.x = x;
		rectangle.y = y;
		rectangle.width = width;
		rectangle.height = height;
		return rectangle;
	}
	
	public DefenseAdder getManageDefense()
	{
		return manageDefense;
	}
}
