package Health;

import entities.Enemies;

public class EnemyDefenseAdder
{
	private Enemies enemy;
	//private int convDefense;
	private double curDamage = 0;
	
	public EnemyDefenseAdder(Enemies curEne)
	{
		enemy = curEne;
	}
	
	public int adjustDamage(int attack)
	{/*
		Old Defense System
		int defense = enemy.getDefense();
		
		//Adds to transfer Damage
		enemy.addAddedDamage(((double) (attack*2) / (double) (attack+defense))%1);
		curDamage = enemy.getAddedDamage();
		System.out.println("Enemy Damage: "+ curDamage + " Attack: " + attack);
		convDefense = defense==0 ? attack : attack*2 / (attack+defense);
	*/
		return attack-enemy.getDefense()>0 ?  attack-enemy.getDefense(): 0;
	}
	
	
	/*
	 * Old Defense System
	public boolean adjDeciDamage()
	{
		boolean hit = curDamage%1>=0.8 || (curDamage>1 && curDamage%1==0);
		if (hit)
		{
			enemy.setAddedDamage(0);
		}
		return hit;
	}
	*/
	public double getCurDamage()
	{
		return curDamage;
	}
}
