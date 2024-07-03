package towerControls;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ImageLoader.Assets;
import collisionDetection.Collision;
import entities.Enemies;
import entities.MaxHealth;
import entities.Tower;
import gui.Game;
import inputs.MouseManager;
import levels.EnemyAdder;
import levels.StartEnemyAdder;

public class TowerBar 
{
	private Game game;
	private MouseManager manager;
	
	private boolean tower1Set = false, tower2Set = false, tower3Set = false, towerClicked = false;
	
	private final int x, y, width, height, towerX, towerY, towerWidth, towerHeight;

	private int timePassed = 0;
	
	private Tower tower1, tower2, tower3;

	private Rectangle[] box = new Rectangle[5];
	
	private BufferedImage[] currentTow;
	
	private ArrayList<Enemies> enemy1, enemy2, enemy3;
	
	private Collision collision1, collision2, collision3;
	
	private EnemyAdder moreEnemies1, moreEnemies2, moreEnemies3;
	
	private StartEnemyAdder startGame;
	
	public TowerBar(Game gme)
	{
		game = gme;
		manager = game.getMouseManager();
		x = game.getWidth()-101;
		y = game.getHeight()-667;
		width = 80;
		height = 80;
		
		towerX = game.getWidth()-111;
		towerY = game.getHeight()-687;
		towerWidth = 100;
		towerHeight = 112;
		
		startGame = new StartEnemyAdder(this);
		
		//Boxs refer to the tower locations
		box[0] = new Rectangle(towerX, towerY, towerWidth, towerHeight);
		box[1] = new Rectangle(towerX, towerY+140, towerWidth, towerHeight);
		box[2] = new Rectangle(towerX, towerY+280, towerWidth, towerHeight);
		box[3] = new Rectangle(towerX, towerY+420, towerWidth, towerHeight);
		box[4] = new Rectangle(towerX, towerY+560, towerWidth, towerHeight);
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Assets.waterTower[0], x, y, width, height, null);
		g.drawImage(Assets.crokeTower[0], x, y+150, width, height, null);
		g.drawImage(Assets.geraldTower[0], x, y+290, width, height, null);
		
		if (!towerClicked && timePassed<30)
		{
			//Looks for tower location if bounds are met
			if (checkRange(manager.getBounds()) && !manager.isDifferent() && manager.isClicked())
			{
				currentTow = findImage(setBounds(manager.getX(), manager.getY(), 2, 2));
				towerClicked = true;
			}
		}
		else
		{
			//Draw tower in location of the mouse
			g.drawImage(currentTow[0], manager.getX(), manager.getY(), width, height, null);
			timePassed++;
			
			//Sees if tower is placed in one of the locations
			if (manager.isClicked() && timePassed>30)
			{
				towerClicked = false;
				if (manager.getY()<=225 && manager.getX()<880 && !tower1Set)
				{
					if (currentTow==Assets.geraldTower)
					{
						tower1 = new Tower(120, 1, 5, 24, 1, 15.0, currentTow, game.getGameState(), 1);		
					}
					else
					{
						tower1 = new Tower(120, 1, 5, 3, 1, 0.9, currentTow, game.getGameState(), 1);	
					}
					tower1Set = true;
				}
				else if(manager.getY()>225 && manager.getY()<=460 && manager.getX()<880 && !tower2Set)
				{
					if (currentTow==Assets.geraldTower)
					{

						tower2 = new Tower(360, 1, 5, 24, 2, 15.0, currentTow, game.getGameState(), 2);		
					}
					else
					{
						tower2 = new Tower(360, 1, 5, 3, 2, 0.9, currentTow, game.getGameState(), 2);
					}
					tower2Set = true;
				}
				else if (manager.getY()>460 && manager.getX()<880 && !tower3Set)
				{
					if (currentTow==Assets.geraldTower)
					{
						tower3 = new Tower(600, 1, 5, 24, 3, 15.0, currentTow, game.getGameState(), 3);
					}
					else
					{
						tower3 = new Tower(600, 1, 5, 3, 3, 0.9, currentTow, game.getGameState(), 3);
					}
					tower3Set = true;
				}
				//Makes it so you have to wait at least half a second to place down a tower
				timePassed = 0;
			}
		}
		if (tower1Set && tower1.getAlive())
		{
			tower1.render(g);
		}
		if (tower2Set && tower2.getAlive())
		{
			tower2.render(g);
		}
		if (tower3Set && tower3.getAlive())
		{
			tower3.render(g);
		}
		if (startGame.getstartAdding())
		{
			moreEnemies1.render(g);
			for (int i=0; i<enemy1.size(); i++)
			{
				enemy1.get(i).render(g);
			}
			
			moreEnemies2.render(g);
			for (int i=0; i<enemy2.size(); i++)
			{
				enemy2.get(i).render(g);
			}
			
			moreEnemies3.render(g);
			for (int i=0; i<enemy3.size(); i++)
			{
				enemy3.get(i).render(g);
			}
			startGame.getMaxHealth().render(g);
		}
		startGame.render(g);
		/*Rect Bounds
		First
		g.fillRect(game.getWidth()-111, game.getHeight()-687, 100, 112);
		Second
		g.fillRect(game.getWidth()-111, game.getHeight()-547, 100, 112);
		Third
		g.fillRect(game.getWidth()-111, game.getHeight()-407, 100, 112);
		Fourth
		g.fillRect(game.getWidth()-111, game.getHeight()-267, 100, 112);
		Fifth
		g.fillRect(game.getWidth()-111, game.getHeight()-127, 100, 112);
		*/
	}
	
	public void tick()
	{
		if (tower1Set && tower1.getAlive())
		{
			tower1.tick();
		}
		else if (tower1Set)
		{
			manager.setIsDifferent(false);
			tower1Set = false;
		}
		if (tower2Set && tower2.getAlive())
		{
			tower2.tick();
		}
		else if (tower2Set)
		{
			manager.setIsDifferent(false);
			tower2Set = false;
		}
		if (tower3Set && tower3.getAlive())
		{
			tower3.tick();
		}
		else if (tower3Set)
		{
			manager.setIsDifferent(false);
			tower3Set = false;
		}
		//Ticks adding enemies if time has run out
		if (startGame.getstartAdding())
		{
			moreEnemies1.tick();
			for (int i=0; i<enemy1.size(); i++)
			{
				enemy1.get(i).tick();
				collision1 = new Collision(1, i, enemy1, tower1);
				collision1.tick();
			}
			
			moreEnemies2.tick();
			for (int i=0; i<enemy2.size(); i++)
			{
				enemy2.get(i).tick();
				collision2 = new Collision(1, i, enemy2, tower2);
				collision2.tick();
			}
			
			moreEnemies3.tick();
			for (int i=0; i<enemy3.size(); i++)
			{
				enemy3.get(i).tick();
				collision3 = new Collision(1, i, enemy3, tower3);
				collision3.tick();
			}
			startGame.getMaxHealth().tick();
		}
		startGame.tick();
	}
	
	private Rectangle setBounds(int x, int y, int width, int height) 
	{
		Rectangle rect = new Rectangle();
		rect.x = x;
		rect.y = y;
		rect.width = width;
		rect.height = height;
		return rect;
	}

	private boolean checkRange(Rectangle mouse)
	{
		boolean intersect = false;
		for (int i=0; i<5 && !intersect; i++)
		{
			if (mouse.intersects(box[i]))
			{
				intersect = true;
			}
		}
		return intersect;
	}
	
	//finds tower clicked on
	private BufferedImage[] findImage(Rectangle mouse)
	{
		boolean intersect = false;
		BufferedImage[] image = Assets.waterTower;		
		for (int i=0; i<5 && !intersect; i++)
		{
			if (mouse.intersects(box[i]))
			{
				intersect = true;
				if (i==0)
				{
					image = Assets.waterTower;
				}
				if (i==1)
				{
					image = Assets.crokeTower;
				}
				if (i==2)
				{
					image = Assets.geraldTower;
				}
				if (i==4)
				{
					image = Assets.gumballTower;
				}
			}
		}
		return image;
	}
	
	public ArrayList<Enemies> getEnemies1()
	{
		return enemy1;
	}
	
	public ArrayList<Enemies> getEnemies2()
	{
		return enemy2;
	}
	
	public ArrayList<Enemies> getEnemies3()
	{
		return enemy3;
	}
	
	public StartEnemyAdder getStartEnemies()
	{
		return startGame;
	}
	
	public void setEnemies1(ArrayList<Enemies> emy)
	{
		enemy1 = emy;
	}
	
	public void setMoreEnemies1(EnemyAdder emy)
	{
		moreEnemies1 = emy;
	}
	
	public Tower getTower1()
	{
		return tower1;
	}
	
	public void setEnemies2(ArrayList<Enemies> emy)
	{
		enemy2 = emy;
	}
	
	public void setMoreEnemies2(EnemyAdder emy)
	{
		moreEnemies2 = emy;
	}
	
	public Tower getTower2()
	{
		return tower2;
	}
	
	public void setEnemies3(ArrayList<Enemies> emy)
	{
		enemy3 = emy;
	}
	
	public void setMoreEnemies3(EnemyAdder emy)
	{
		moreEnemies3 = emy;
	}
	
	public Tower getTower3()
	{
		return tower3;
	}
	
	public boolean getTower1Set()
	{
		return tower1Set;
	}
	
	public boolean getTower2Set()
	{
		return tower2Set;
	}
	
	public boolean getTower3Set()
	{
		return tower3Set;
	}
	
	public void setTower1Set(Tower twr)
	{
		tower1 = twr;
	}
	
	public void setTower2Set(Tower twr)
	{
		tower2 = twr;
	}
	
	public void setTower3Set(Tower twr)
	{
		tower3 = twr;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public MaxHealth getMaxHealth()
	{
		return startGame.getMaxHealth();
	}
	
	public void setTower1Adder(boolean val)
	{
		tower1Set = val;
	}
	
	public void setTower2Adder(boolean val)
	{
		tower2Set = val;
	}
	
	public void setTower3Adder(boolean val)
	{
		tower3Set = val;
	}
	
}
