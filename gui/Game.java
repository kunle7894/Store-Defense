package gui;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import ImageLoader.Assets;
import inputs.MouseManager;
import states.EndState;
import states.GameState;
import states.IntroState;
import states.MenuState;
import states.State;

public class Game implements Runnable
{
	private String title;
	private int height, width, waveNum = 1, maxWave = 1;
	private boolean running = false;
	
	private Assets asset;
	
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Display display;
	
	private State game;
	private State menu;
	private State endState;
	private State introState;
	
	private MouseManager mouseManager;
	
	public Game(String tit, int with, int hight)
	{
		title = tit;
		height = hight;
		width = with;
	}
	
	public void init()
	{
		asset = new Assets();
		asset.init();
		
		display = new Display(title, width, height);
		
		mouseManager = new MouseManager();
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		menu = new MenuState(this);
		game = new GameState(this);
		endState = new EndState(this);
		introState = new IntroState(this);
		
		State.setState(menu);
	}
	
	public void reInit()
	{
		menu = new MenuState(this);
		game = new GameState(this);
		endState = new EndState(this);
		introState = new IntroState(this);
		
		State.setState(menu);
	}

	public void render() 
	{
		boolean needToRender = true;
		bs = display.getCanvas().getBufferStrategy();
		
		if  (bs==null)
		{
			display.getCanvas().createBufferStrategy(3);
			needToRender = false;
		}
		if (needToRender)
		{
			g = bs.getDrawGraphics();
			//Clears Screen
			
			State.getState().render(g);
			
			//End Draw
			bs.show();
			g.dispose();
		}
	}

	public void tick() 
	{
		State.getState().tick();
	}
	
	//More efficient game to have 60 frames and 60 ticks
	public void run()
	{
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while (running)
		{
			now = System.nanoTime();
			delta += (now-lastTime)/timePerTick;
			timer += now-lastTime;
			lastTime = now;
			
			if (delta>=1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if (timer>=1000000000)
			{
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}	
		stop();
	}
	
	public synchronized void start()
	{
		if (!running)
		{
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop()
	{
		if (running)
		{
			try 
			{
				thread.join();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public MouseManager getMouseManager()
	{
		return mouseManager;
	}
	
	public GameState getGameState()
	{
		return (GameState) game;
	}
	
	public MenuState getMenuState()
	{
		return (MenuState) menu;
	}
	
	public EndState getEndState()
	{
		return (EndState) endState;
	}
	
	public IntroState getIntroState()
	{
		return (IntroState) introState;
	}
	
	public int getWaveNum()
	{
		return waveNum;
	}
	
	public void setWaveNum(int num)
	{
		waveNum = num;
	}
	
	public int getMaxWave()
	{
		return maxWave;
	}
	
	public void setMaxWave(int num)
	{
		maxWave = num;
	}
}
