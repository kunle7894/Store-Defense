package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EnemyProjectile extends Creatures
{
	private double speed;
	private int x, y, width, height, attack, time, animTime = 60, locAnim = 0;
	private BufferedImage proj;
	private BufferedImage[] image;
	private Rectangle bounds;
	
	public EnemyProjectile(int atck, double spd, int loc, int locY, int wdh, int ht, BufferedImage[] img) 
	{
		super(0, 0, atck);
		attack = atck;
		speed = spd;
		x = loc + 74;
		y = locY + 19;
		proj = img[locAnim];
		image = img;
		width = wdh;
		height = ht;
		time = 0;
	}
	
	//Moves projectiles
	public void move()
	{
		x += (int) speed;
		if (time==10)
		{
			y+=1;
			time = 0;
		}
		time++;
	}
	
	public void tick()
	{
		move();
		bounds = bounds(x, y, width, height-25);
		anim();
	}
	
	public void render(Graphics g)
	{
		g.drawImage(proj, x, y, width, height, null);
	}

	public void anim()
	{
		if (animTime>60)
		{
			if (locAnim>=image.length)
			{
				locAnim = 0;
				System.out.println(locAnim);
			}
			
			proj = image[locAnim];
			animTime = 0;
			locAnim++;
		}		
		animTime++;
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public int getAttack()
	{
		return attack;
	}
}
