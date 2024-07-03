package levels;

import java.awt.Graphics;
import java.util.ArrayList;

import ImageLoader.Assets;
import entities.Enemies;
import entities.Tower;

public class EnemyAdder 
{
	private int yPos, row, time, currentTime = 0, defense = 0, health = 5, attack = 1, money = 1, waveChanger = 1800, currentWaveTime = 0, 
							waveNum = 1, waveX = 370, waveY = 0, waveDim = 300, numSize = 48, statAdder = 1;
	private double speed = 0.9f, speedAdder = 0.15f;
	
	private boolean startTravel = false;
	
	private ArrayList<Enemies> enemies;
	private Tower tower;
	
	public EnemyAdder(int yLoc, ArrayList<Enemies> enemy, int howMany, Tower twr)
	{
		yPos = yLoc;
		enemies = enemy;
		time = howMany;
		tower = twr;
		row = tower.getRow();
	}
	
	//Adds new Enemies if time has been reached
	public void tick()
	{
		//Makes the enemy linked to the tower(in case a new tower is placed after the game starts
		if (row==1)
		{
			tower = tower.getTowerBr().getTower1();
		}
		if (row==2)
		{
			tower = tower.getTowerBr().getTower2();
		}
		if (row==3)
		{
			tower = tower.getTowerBr().getTower3();
		}		
		
		//Adds a new enemy if time is reached
		if (currentTime==time)
		{
			currentTime = -1;
			enemies.add(new Enemies(yPos, defense, health, attack, money, speed, tower, Assets.baby));
		}
		
		//Makes stronger enemies when wave number changes
		if (currentWaveTime==waveChanger)
		{
			attack+=statAdder;
			speed+=speedAdder;
			defense+=statAdder;
			//time -= time>200 ? 10 : 0;
			//time -= 10;
			currentWaveTime = 0;
			waveNum++;
			System.out.println("Wave Number: "+waveNum);
			startTravel = true;
			health+=statAdder;
			
			tower.getGame().setWaveNum(waveNum);
			if (tower.getGame().getMaxWave()<waveNum)
			{
				tower.getGame().setMaxWave(waveNum);
			}
			
			if(waveNum%10==0)
			{
				money++;
			}
		}
		currentTime++;
		currentWaveTime++;
		checkHealth();
	}
	
	public void render(Graphics g)
	{
		//Draws wave number at the beginning of a wave
		if (startTravel)
		{
			g.drawImage(Assets.waveNumber, waveX, waveY, waveDim, waveDim, null);
			findDrawNumbers(waveNum, waveX+numSize*4+30, waveY, g);
			waveY++;
			if (waveY>=800)
			{
				startTravel = false;
				waveY = 0;
			}
		}	
	}
	
	//Converts the numbers to an image
	public void findDrawNumbers(int value, int xLoc, int yLoc, Graphics g)
	{
		int loc = xLoc;
		int locY = yLoc;
		for (int i=value; i>0; i/=10)
		{
			g.drawImage(Assets.numbers[i%10], loc, locY, numSize, numSize, null);
			loc-=29;
		}
	}
	
	//Removes the enemy if health is too low
	public void checkHealth()
	{
		for (int i=0; i<enemies.size(); i++)
		{
			if (enemies.get(i).getHealth()<=0)
			{
				tower.setMoney(tower.getMoney()+enemies.get(i).getAmount());
				enemies.remove(i);
			}
		}
	}
	
	public Tower getTower()
	{
		return tower;
	}
}
