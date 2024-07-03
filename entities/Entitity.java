package entities;

import java.awt.Graphics;

public abstract class Entitity 
{
	private int defense, hp, attackPower;
	private boolean movable;
	
	public Entitity(int def, int health, int attack, boolean moves)
	{
		defense = def;
		hp = health;
		attackPower = attack;
		movable = moves;
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	public int getDefense()
	{
		return defense;
	}

	public void setAttack(int attackPower) 
	{
		this.attackPower = attackPower;
	}

	public void setDefense(int defense) 
	{
		this.defense = defense;
	}

	public void setHp(int hp) 
	{
		this.hp = hp;
	}

	public int getHp()
	{
		return hp;
	}
	
	public int getAttack()
	{
		return attackPower;
	}
	
	public boolean isMovable()
	{
		return movable;
	}
}
