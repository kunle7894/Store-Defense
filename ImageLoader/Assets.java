package ImageLoader;

import java.awt.image.BufferedImage;

public class Assets 
{
	public static BufferedImage background, water, title, towerHolder, coin, powerScreen, dot, waveNumber, clickedPower, endScreen;

	public static BufferedImage[] baby = new BufferedImage[3];
	public static BufferedImage[] tank = new BufferedImage[2];
	public static BufferedImage[] healthBar = new BufferedImage[2];
	public static BufferedImage[] waterTower = new BufferedImage[2];
	public static BufferedImage[] gumballTower = new BufferedImage[3];
	public static BufferedImage[] startButton = new BufferedImage[2];
	public static BufferedImage[] crokeTower = new BufferedImage[8];
	public static BufferedImage[] geraldTower = new BufferedImage[3];
	public static BufferedImage[] heraldTower = new BufferedImage[2];
	public static BufferedImage[] numbers = new BufferedImage[10];
	public static BufferedImage[] restart = new BufferedImage[2];
	public static BufferedImage[] next = new BufferedImage[2];
	public static BufferedImage[] back = new BufferedImage[2];
	public static BufferedImage[] done = new BufferedImage[2];
	public static BufferedImage[] instructions = new BufferedImage[2];
	public static BufferedImage[] introSlides = new BufferedImage[6];
	public static BufferedImage[] sleepingCat = new BufferedImage[4];
	
	private int width = 256, height = 256, widthSlides = 1000, heightSlides = 700;
	
	public void init()
	{
		//Creates a new SpriteSheet
		SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/characters/peopleSheet.png"));
		SpriteSheet background1 = new SpriteSheet(ImageLoader.loadImage("/characters/background.png"));
		SpriteSheet holder = new SpriteSheet(ImageLoader.loadImage("/characters/towerHolder.png"));
		SpriteSheet powerScrn = new SpriteSheet(ImageLoader.loadImage("/characters/powerIncreaser.png"));
		SpriteSheet introScreen = new SpriteSheet(ImageLoader.loadImage("/characters/introSlides.png"));
		waveNumber = ImageLoader.loadImage("/characters/WaveNumber.png");
		endScreen = ImageLoader.loadImage("/characters/EndScreen.png");
		
		background = background1.crop(0, 0, 1500, 1500);
		
		title = sheet1.crop(width*2, height*9, width*2, height);

		water = sheet1.crop(width*2, 0, width, height);

		towerHolder = holder.crop(0, 0, 200, 1000);
		
		powerScreen = powerScrn.crop(0, 0, 200, 1000);
		
		coin = sheet1.crop(width*4, height*9, width, height);
		
		clickedPower = sheet1.crop(width*6, height*9, width, height);
		
		//Crops sprites
		baby[0] = sheet1.crop(width, 0, width, height);
		baby[1] = sheet1.crop(width, height, width, height);
		baby[2] = sheet1.crop(width, height*2, width, height);
		
		tank[0] = sheet1.crop(0, 0, width, height);
		tank[1] = sheet1.crop(0, height, width, height);
		
		healthBar[0] = sheet1.crop(0, height*2, width, height);
		healthBar[1] = sheet1.crop(0, height*3, width, height);
		
		waterTower[0] = sheet1.crop(width*2, height, width, height);
		waterTower[1] = sheet1.crop(width*3, 0, width, height);
	
		startButton[0] = sheet1.crop(0, height*9, width, height);
		startButton[1] = sheet1.crop(width, height*9, width, height);
	
		crokeTower[0] = sheet1.crop(width*2, height*3, width, height);
		
		crokeTower[1] = sheet1.crop(width, height*3, width, height);
		crokeTower[2] = sheet1.crop(0, height*4, width, height);
		crokeTower[3] = sheet1.crop(width, height*4, width, height);
		crokeTower[4] = sheet1.crop(0, height*5, width, height);
		crokeTower[5] = sheet1.crop(width, height*5, width, height);
		crokeTower[6] = sheet1.crop(0, height*6, width, height);
		crokeTower[7] = sheet1.crop(width, height*6, width, height);
	
		numbers[0] = sheet1.crop(width*9, height*8, width, height);
		numbers[1] = sheet1.crop(0, height*8, width, height);
		numbers[2] = sheet1.crop(width, height*8, width, height);
		numbers[3] = sheet1.crop(width*2, height*8, width, height);
		numbers[4] = sheet1.crop(width*3, height*8, width, height);
		numbers[5] = sheet1.crop(width*4, height*8, width, height);
		numbers[6] = sheet1.crop(width*5, height*8, width, height);
		numbers[7] = sheet1.crop(width*6, height*8, width, height);
		numbers[8] = sheet1.crop(width*7, height*8, width, height);
		numbers[9] = sheet1.crop(width*8, height*8, width, height);
		
		restart[0] = sheet1.crop(width*7, height*9, width, height);
		restart[1] = sheet1.crop(width*8, height*9, width, height);
	
		instructions[0] = sheet1.crop(0, height*7, width, height);
		instructions[1] = sheet1.crop(width, height*7, width, height);
		
		next[0] = sheet1.crop(width*2, height*7, width, height);
		next[1] = sheet1.crop(width*3, height*7, width, height);
		
		back[0] = sheet1.crop(width*4, height*7, width, height);
		back[1] = sheet1.crop(width*5, height*7, width, height);
		
		done[0] = sheet1.crop(width*6, height*7, width, height);
		done[1] = sheet1.crop(width*7, height*7, width, height);
		
		introSlides[0] = introScreen.crop(widthSlides*2, heightSlides, widthSlides, heightSlides);
		introSlides[1] = introScreen.crop(0, 0, widthSlides, heightSlides);
		introSlides[2] = introScreen.crop(widthSlides, 0, widthSlides, heightSlides);
		introSlides[3] = introScreen.crop(widthSlides*2, 0, widthSlides, heightSlides);
		introSlides[4] = introScreen.crop(0, heightSlides, widthSlides, heightSlides);
		introSlides[5] = introScreen.crop(widthSlides, heightSlides, widthSlides, heightSlides);
		
		dot = sheet1.crop(width*5, height*9, width, height);
		
		gumballTower[0] = sheet1.crop(width*2, height*2, width, height);
		gumballTower[1] = sheet1.crop(0, 0, width, height);
		gumballTower[2] = sheet1.crop(0, height, width, height);
	
		sleepingCat[1] = sheet1.crop(width*3, height, width, height);
		sleepingCat[2] = sheet1.crop(width*3, height*2, width, height);
		sleepingCat[3] = sheet1.crop(width*3, height*3, width, height);
		sleepingCat[0] = sheet1.crop(width*3, height*4, width, height);
	
		geraldTower[0] = sheet1.crop(width*2, height*4, width, height);
		geraldTower[1] = sheet1.crop(width*2, height*5, width, height);
		geraldTower[2] = sheet1.crop(width*2, height*6, width, height);
		
		heraldTower[0] = sheet1.crop(width*4, 0, width, height);
	}
}
