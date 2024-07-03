package launcher;

import gui.Game;

public class Launcher
{
	private static int frameWidh = 1000, frameHeight = 700;

	public static void main(String[] args)
	{
		Game game = new Game("StoreDefense", frameWidh, frameHeight);
		game.start();
	}
}
