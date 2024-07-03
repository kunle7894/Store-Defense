package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Health.HealthBar;
import ImageLoader.Assets;
import collisionDetection.TowerProjectileCollision;
import gui.Game;
import states.GameState;
import towerControls.PowerChanger;
import towerControls.TowerBar;

public class Tower extends Creatures
{
	private BufferedImage[] sprite;
	private int row, x, y, width, height, time, maxTime = 200, currentHealth, rowTower, locProj = 1, money, projDim = 100;
	
	private double speed, addedDamage = 0;
	
	private ArrayList<EnemyProjectile> proj;

	private HealthBar health;
	
	private Rectangle bounds;
	
	private TowerProjectileCollision collision;
	
	private boolean alive = true;
	
	private Game game;
	
	private MoneyCounter monies;
	
	private GameState state;
	
	private PowerChanger powerUps;
	
	private TowerBar towerBar;
	
	public Tower(int loc, int def, int hp, int atck, int column, double spd, BufferedImage[] image, GameState gme, int cur) 
	{
		super(def, hp, atck);
		money = 1;
		sprite = image;
		row = column;
		game = gme.getGame();
		towerBar = gme.getTowerBar();
		currentHealth = hp;
		x = game.getWidth()-260;
		y = loc;
		state = gme;
		
		proj = new ArrayList<>();
		health = new HealthBar(this);
		speed = spd;
		rowTower = cur;
		monies = new MoneyCounter(this, 1);
		
		width = game.getWidth()/6-24;
		height = (int) (game.getHeight()/6.5);
		
		
		bounds = bounds(x+20, y, width, height);
		
		powerUps = new PowerChanger(this);
	}
	
	public void tick()
	{
		health.tick();
		//Creates projectiles when game started
		if (state.getTowerBar().getStartEnemies().getstartAdding())
		{
			createProj();
			for (int i=0; i<proj.size(); i++)
			{
				proj.get(i).tick();
			}
			checkCollision();
			checkAlive();
		}
		powerUps.tick();
		monies.tick();
	}
	
	public void render(Graphics g)
	{
		g.drawImage(sprite[0], x, y, width, height, null);
		health.render(g);
		
		//Creates projectiles when game started
		if (state.getTowerBar().getStartEnemies().getstartAdding())
		{
			for (int i=0; i<proj.size(); i++)
			{
				proj.get(i).render(g);
			}
		}
		powerUps.render(g);
		monies.render(g);
	}
	
	//Creates a projectile
	public void createProj()
	{
		if (addProj())
		{
			proj.add(new EnemyProjectile(super.getAttack(), -(speed+speed*1.5), (int) x-125, (int) y-5, projDim, projDim, findDraw(sprite)));
		}
	}
	
	//Sees if you need to add a projectile
	public boolean addProj()
	{
		boolean needToAdd = false;
		time++;
		if (time>maxTime)
		{
			needToAdd = true;
			time = 0;
		}
		return needToAdd;
	}

	//Sees if there is a collision
	private void checkCollision()
	{
		ArrayList<Enemies> enemy = getTowerBar(state.getTowerBar());
		for (int i=0; i<enemy.size(); i++)
		{
			for (int j=0; j<proj.size(); j++)
			{
				collision = new TowerProjectileCollision(j, i, enemy, proj);
				collision.tick();
			}
		}
	}
	
	public ArrayList<Enemies> getTowerBar(TowerBar tower)
	{
		ArrayList<Enemies> curEnemy = tower.getEnemies3();
		if (rowTower==1)
		{
			curEnemy = tower.getEnemies1();
		}
		else if (rowTower==2)
		{
			curEnemy = tower.getEnemies2();
		}
		return curEnemy;
	}
	
	public BufferedImage[] getSprite()
	{
		return sprite;
	}
	
	public void lowerHealth(int ammount)
	{
		currentHealth -= ammount;
	}
	
	public void checkAlive()
	{
		if (currentHealth<=0)
		{
			alive = false;
		}
	}
	
	public BufferedImage[] findDraw(BufferedImage[] bufImage)
	{
		BufferedImage[] image = new BufferedImage[1];
		if (locProj>=bufImage.length)
		{
			locProj = 1;
		}
		image[0] = bufImage[locProj];
		locProj++;
		
		if (bufImage==Assets.geraldTower)
		{
			image = new BufferedImage[]{bufImage[locProj-1], bufImage[locProj]};
			locProj++;
		}
				
		return image;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getHealth()
	{
		return currentHealth;
	}
	
	public void setHealth(int health)
	{
		currentHealth = health;
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public int getDamage()
	{
		return super.getAttack();
	}
	
	public boolean getAlive()
	{
		return alive;
	}
	
	public GameState getState()
	{
		return state;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public void setMoney(int mny)
	{
		money = mny;
	}
	
	public int getMoney()
	{
		return money;
	}
	
	public void addAddedDamage(double amount)
	{
		addedDamage += amount;
	}
	
	public double getAddedDamage()
	{
		return addedDamage;
	}
	
	//May be unnecessary
	public void setAddedDamage(double amount)
	{
		addedDamage = amount;
	}
	
	public double getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(double spd)
	{
		speed = spd;
	}
	
	public int getMaxTime()
	{
		return maxTime;
	}
	
	public void setMaxTime(int mT)
	{
		maxTime = mT;
	}
	
	public TowerBar getTowerBr()
	{
		return towerBar;
	}
	
	public Game getGame()
	{
		return game;
	}
}