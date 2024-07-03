package Health;

import entities.Tower;

public class DefenseAdder
{
	private Tower tower;
	//private int conDefense;
	private double curDmage = 0;
	
	public DefenseAdder(Tower curTow)
	{
		tower = curTow;
	}
	
	public int adjustDamage(int attack)
	{
		/*
		 * Old Defense System too OP
		 * int defense = tower.getDefense();
		
		//Adds to transfer Damage
		tower.addAddedDamage(((double) (attack*2) / (double) (attack+defense)%1));
		curDmage = tower.getAddedDamage();
		
		conDefense = defense==0 ? attack : attack*2 / (attack+defense);
		System.out.println("Damage: "+ tower.getAddedDamage() + " Attack: " + attack);
		*/
		return attack-tower.getDefense()>0 ?  attack-tower.getDefense(): 0;
	}
	/*Part of old Attack System
	public boolean adjDeciDamage()
	{
		boolean hit = curDmage%1>=0.8 || (curDmage%1==0 && curDmage>0);
		System.out.println("Damage: "+ curDmage);
		if (hit)
		{
			tower.setAddedDamage(0);
		}
		return hit;
	}
	*/
	public double getCurDamage()
	{
		return curDmage;
	}
}
