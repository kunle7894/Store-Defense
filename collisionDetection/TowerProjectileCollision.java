package collisionDetection;

import java.util.ArrayList;

import Health.EnemyDefenseAdder;
import entities.Enemies;
import entities.EnemyProjectile;

public class TowerProjectileCollision 
{
	private int indexProj, indexEnemy;
	private ArrayList<Enemies> enemyList;
	private ArrayList<EnemyProjectile> proj;
	private EnemyDefenseAdder manageDefense;
	
	public TowerProjectileCollision(int idxProj, int idxEnemy, ArrayList<Enemies> enemy, ArrayList<EnemyProjectile> prj)
	{		
		proj = prj;
		indexProj = idxProj;
		indexEnemy = idxEnemy;
		enemyList = enemy;
		
		manageDefense = new EnemyDefenseAdder(enemyList.get(indexEnemy));
	}
	
	public void tick()
	{
		if (checkCollision())
		{
			enemyList.get(indexEnemy).lowerHealth(manageDefense.adjustDamage(proj.get(indexProj).getAttack())); //+(manageDefense.adjDeciDamage() ? 1 : 0));
			proj.remove(indexProj);
			System.out.println("Collided");
		}
	}
	
	//Checks to see if there is a collision
	public boolean checkCollision()
	{
		boolean collision = false;
		if (enemyList.get(indexEnemy).getBounds().intersects(proj.get(indexProj).getBounds()))
		{
			collision = true;
		}
		return collision;
	}

	public EnemyDefenseAdder getManageDefense()
	{
		return manageDefense;
	}
}
