package levels;

import java.awt.Graphics;
import java.util.ArrayList;

import ImageLoader.Assets;
import entities.Enemies;
import entities.MaxHealth;
import entities.Tower;
import towerControls.TowerBar;

public class StartEnemyAdder 
{
	private int timer, timerDim = 48, xTimer = 380, yTimer = 20, changeCounter = 60, towLoc1 = 120, towLoc2 = 360, towLoc3 = 600,
					towDef = 1, defHp = 5, towAttk = 3, locTow1 = 1, locTow2 = 2, locTow3 = 3, enemDef = 0, enemAtk = 1, moneyAdd = 1, time = 400;
	private double speed = 0.9;
	private boolean startAdding = false, hit = false;
	
	private TowerBar towerBar;
	
	private MaxHealth maxHealth;
	
	public StartEnemyAdder(TowerBar bar)
	{
		towerBar = bar;
		
		timer = 10;
	}
	
	public void tick()
	{
		changeCounter--;
		if (changeCounter==0)
		{
			changeCounter = 60;
			timer--;
		}
		//Starts to add enemies if time runs out
		if (timer==0)
		{
			addMainHealth();
			checkPlaced();
			startAdding = true;
			checkIntialize();
		}
	}
	
	public void render(Graphics g)
	{
		drawLocation(timer, xTimer, yTimer, g);
	}
	
	public void checkIntialize()
	{
		if (!hit && startAdding)
		{
			hit = true;
			
			towerBar.setEnemies1(new ArrayList<>());
			towerBar.getEnemies1().add(new Enemies(towLoc1-35, enemDef, defHp, enemAtk, moneyAdd, speed, towerBar.getTower1(), Assets.baby));
			towerBar.setMoreEnemies1(new EnemyAdder(towLoc1-35, towerBar.getEnemies1(), time, towerBar.getTower1()));
			
			towerBar.setEnemies2(new ArrayList<>());
			towerBar.getEnemies2().add(new Enemies(towLoc2-40, enemDef, defHp, enemAtk, moneyAdd, speed, towerBar.getTower2(), Assets.baby));
			towerBar.setMoreEnemies2(new EnemyAdder(towLoc2-40, towerBar.getEnemies2(), time, towerBar.getTower2()));
			
			towerBar.setEnemies3(new ArrayList<>());
			towerBar.getEnemies3().add(new Enemies(towLoc3-40, enemDef, defHp, enemAtk, moneyAdd, speed, towerBar.getTower3(), Assets.baby));
			towerBar.setMoreEnemies3(new EnemyAdder(towLoc3-40, towerBar.getEnemies3(), time, towerBar.getTower3()));
		}
	}
	
	public void checkPlaced()
	{
		//Adds towers if not added when time runs out
		if (!towerBar.getTower1Set())
		{
			towerBar.setTower1Set(new Tower(towLoc1, towDef, defHp, towAttk, locTow1, speed, Assets.waterTower, towerBar.getGame().getGameState(), locTow1));
			towerBar.setTower1Adder(true);
		}
		if (!towerBar.getTower2Set())
		{
			towerBar.setTower2Set(new Tower(towLoc2, towDef, defHp, towAttk, locTow2, speed, Assets.waterTower, towerBar.getGame().getGameState(), locTow2));
			towerBar.setTower2Adder(true);
		}
		if (!towerBar.getTower3Set())
		{
			towerBar.setTower3Set(new Tower(towLoc3, towDef, defHp, towAttk, locTow3, speed, Assets.waterTower, towerBar.getGame().getGameState(), locTow3));
			towerBar.setTower3Adder(true);
		}
	}
	
	public void drawLocation(int value, int xLoc, int yLoc, Graphics g)
	{
		int loc = xLoc;
		int locY = yLoc;
		for (int i=value; i>0; i/=10)
		{
			g.drawImage(Assets.numbers[i%10], loc, locY, timerDim, timerDim, null);
			loc-=15;
		}
	}
	
	public void addMainHealth()
	{
		maxHealth = new MaxHealth(10);
	}
	
	public MaxHealth getMaxHealth()
	{
		return maxHealth;
	}
	
	public boolean getstartAdding()
	{
		return startAdding;
	}
}
