package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Health.HealthBar;
import ImageLoader.Assets;
import collisionDetection.ProjectileCollision;

public class Enemies extends Creatures 
{
	private double x = 0, y = 85; 
	private double speed;
	
	private int width = 120, height = 80, curHp, attack, locProj = 1, money, projDim = 50, enemDim = 160;
	
	private double addedDamage = 0;
	
	private Rectangle bounds;
	
	private boolean addedProj = false;
	
	private ArrayList<EnemyProjectile> proj;
	
	private ProjectileCollision collision;
	
	private HealthBar health;
	
	private Tower tower;
	
	private BufferedImage[] image;
	
	public Enemies(int yPos, int def, int hp, int attackPower, int moneyAdd, double spd, Tower twr, BufferedImage[] bby)
	{
		super (def, hp, attackPower);
		speed = spd;
		attack = attackPower;
		tower = twr;
		y = yPos;
		money = moneyAdd;
		
		image = bby;
		proj = new ArrayList<>();
		curHp = hp;
		health = new HealthBar(this);
		bounds = bounds(x, y, width, height);
	}
	
	//Changes movement of person/recreates bounds.
	public void move()
	{
		x += speed;
		bounds = bounds(x, y, width, height);
	}
	
	public void tick()
	{
		move();
		health.tick();
		createProj();
		for (int i=0; i<proj.size(); i++)
		{
			proj.get(i).tick();
		}
		checkCollision();
	}
	
	public void render(Graphics g)
	{
		currentImage = anim(g, image);
		g.drawImage(currentImage, (int) x, (int) y, enemDim, enemDim, null);
		health.render(g);
		for (int i=0; i<proj.size(); i++)
		{
			proj.get(i).render(g);
		}
	}
	//Creates a new projectile for enemy
	public void createProj()
	{
		if (addProj())
		{
			proj.add(new EnemyProjectile(attack, speed+speed*1.5, (int) x, (int) y, projDim, projDim, new BufferedImage[] {Assets.water}));
		}
	}
	
	//Sees if you need to add a new projectile
	public boolean addProj()
	{
		boolean needToAdd = false;
		if (getCurrent()+1==image.length && !addedProj)
		{
			needToAdd = true;
			addedProj = true;
		}
		else if (getCurrent()+1<image.length && addedProj)
		{
			addedProj = false;
		}
		return needToAdd;
	}

	//Sees if there is a collision
	private void checkCollision()
	{
		for (int i=0; i<proj.size(); i++)
		{
			collision = new ProjectileCollision(i, proj, tower, addedDamage);
			collision.tick();
		}
	}
	
	public BufferedImage findDraw(BufferedImage[] bufImage)
	{
		BufferedImage image;
		
		if (locProj>=bufImage.length)
		{
			locProj = 1;
		}
		image = bufImage[locProj];
		locProj++;
		return image;
	}
	
	public void lowerHealth(int ammount)
	{
		curHp -= ammount;
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}

	public double getX() 
	{
		return x;
	}

	public double getY() 
	{
		return y;
	}

	public int getWidth() 
	{
		return width;
	}

	public int getHeight() 
	{
		return height;
	}
	
	public int getHealth()
	{
		return curHp;
	}
	
	public int getAmount()
	{
		return money;
	}
	
	public Tower getTower()
	{
		return tower;
	}
	
	public void addAddedDamage(double amount)
	{
		addedDamage += amount;
	}
	
	public double getAddedDamage()
	{
		return addedDamage;
	}
	
	public void setAddedDamage(double amount)
	{
		addedDamage = amount;
	}
}
