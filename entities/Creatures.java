package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Creatures extends Entitity
{
	protected BufferedImage currentImage;
	private int current = 0, time = 0, currentAdd = 0;
	
	public Creatures(int def, int hp, int attack)
	{
		super(def, hp, attack, true);
	}
	
	//Animates through BufferedImage array
	protected BufferedImage anim(Graphics g, BufferedImage[] image)
	{
		time++;
		if (time>60)
		{
			time = 0;
			if (current>=image.length-1)
			{
				currentAdd = -1;
			}
			else if (current==0)
			{
				currentAdd = 1;
			}
			current += currentAdd;
		}
		return image[current];
	}
	
	//Makes bounds for BufferedImage
	protected Rectangle bounds(double x, double y, int width, int height)
	{
		Rectangle bounds = new Rectangle();
		bounds.x = (int) x;
		bounds.y = (int) y;
		bounds.width = width;
		bounds.height = height;
		return bounds;
		
	}
	
	public int getCurrent()
	{
		return current;
	}
}
