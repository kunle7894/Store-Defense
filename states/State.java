package states;

import java.awt.Graphics;

public abstract class State 
{
	private static State currentState;

	public State()
	{
		
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();

	public static void setState(State state)
	{
		currentState = state;
	}
	
	public static State getState()
	{
		return currentState;
	}
}
