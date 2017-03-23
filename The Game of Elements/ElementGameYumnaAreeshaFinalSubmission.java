/*The "TheGameOfElements" class.
*@Author Yusra Irfan 
*@Version 2.4: November 2015       
*/
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class TheGameOfElements extends Applet implements MouseListener, MouseMotionListener, KeyListener, Runnable
{
    private static final long serialVersionUID = 1L;
    // declare two instance variables at the head of the program
    private Image dbImage;
    private Graphics dbg;

    // create media tracker and image variables for image
    Image img;
    MediaTracker tr;

    //image step 1
    private final String mainChar = "Main Character.png";
    private final String gameBackground = "level1background.jpg";
    private final String gameToolBackground = "gameToolBackground.png";
    private final String arrow = "arrow-low.png";
    private final String lives = "lives.png";
    private final String smallBall = "Small Ball.png";
    private final String bigBall = "Earth_Kingdom_emblem.png";
    private final String biggerBall = "bigBall.png";
    private final String level1Comp = "level1cleared.png";
    private final String level2Comp = "level2cleared.png";
    private final String gameOver = "gameOver.png";
    private final String win = "win.png";
    private final String gameBackground2 = "level2background.jpg.png";
    private final String gameBackground3 = "level3background.png";
    private final String menu = "Game Menu.png";
    private final String menuPlay = "MenuPlay.png";
    private final String menuInstructions = "MenuInstructions.png";
    private final String instructions = "Instructions.png";
    private final String instructionsBack = "InstructionsBack.png";
    private final String about = "about.png";
    private final String aboutBack = "aboutBack.png";
    private final String menuAbout = "MenuAbout.png";
    private final String playButton = "playButton.png";
    private final String pauseButton = "pauseButton.png";
    private final String pauseBox = "pauseBox.png";
    private final String level1ClearedNext = "level1clearednext.png";
    private final String level1ClearedMenu = "level1clearedmenu.png";
    private final String level2ClearedNext = "level2clearednext.png";
    private final String level2ClearedMenu = "level2clearedmenu.png";
    private final String pauseButtonRed = "pauseButtonRed.png";
    private final String pauseMenu = "pausemenu.png";
    private final String pauseResume = "pauseresume.png";
    private final String gameOverMenu = "gameovermenu.png";
    private final String gameOverPlayAgain = "gameoverplayagain.png";
    private final String winMenu = "winmenu.png";
    private final String winPlayAgain = "winplayagain.png";

    //image step 2
    Image mainCharPic;
    Image gameBackgroundPic;
    Image gameToolBackgroundPic;
    Image arrowPic;
    Image livesPic;
    Image smallBallPic;
    Image bigBallPic;
    Image biggerBallPic;
    Image level1CompPic;
    Image level2CompPic;
    Image gameBackground2Pic;
    Image gameBackground3Pic;
    Image gameOverPic;
    Image winPic;
    Image menuPic;
    Image menuPlayPic;
    Image menuInstructionsPic;
    Image instructionsPic;
    Image instructionsBackPic;
    Image aboutPic;
    Image aboutBackPic;
    Image menuAboutPic;
    Image playButtonPic;
    Image pauseButtonPic;
    Image pauseBoxPic;
    Image level1ClearedNextPic;
    Image level1ClearedMenuPic;
    Image level2ClearedNextPic;
    Image level2ClearedMenuPic;
    Image pauseButtonRedPic;
    Image pauseMenuPic;
    Image pauseResumePic;
    Image gameOverMenuPic;
    Image gameOverPlayAgainPic;
    Image winMenuPic;
    Image winPlayAgainPic;

    //font styles
    Font scoreFont = new Font ("Berlin Sans FB Demi", 1, 40);

    int level = 0;
    int appletsize_x = 1150; //width of applet
    int appletsize_y = 700; //height of applet

    int charPosX = (appletsize_x / 2) - 80; //main character starting from the middle of the screen
    int charPosY = appletsize_y - 200; //main character starting from the bottom of the screen

    int arrowPosX = charPosX + 65; //arrow positon

    int arrowPosY = appletsize_y; //int arrowPosY = 30;

    int arrowSpeedY = 0;

    int[] posX = new int [8]; //x position of the balls
    int[] posY = new int [8]; //y position of the balls
    int[] speedX = new int [8]; //x speed of the balls
    int[] speedY = new int [8]; //y speed of the balls

    /*
    Ball  0 - 4: Small balls (Water Element)(used in level 1,2,3) (count: 5 balls)
    Ball  5 & 6: big balls (Earth Element) (used in level 2,3) (count: 2 balls)
    Ball  7: bigger balls (used in level 3) (count: 1 ball)
    */

    int radiusSmall = 40; //Radius of smallball [0-4]
    int radiusBig = 80; //Radius of big ball [5]
    int radiusBig2 = 80; // Radius of big ball [6]
    int radiusBigger = 120; //Radius of bigger ball [7]

    int live1PosX = 0; //placement of lives (x position)
    int live1PosY = 0; //placement of lives (y position)
    int score = 0; //score for the game
    int count = 0; //number of lives lost
    int xpos; //x position of the mouse pointer
    int ypos; //y position of the mouse pointer
    int toolbarPosX = appletsize_x - 50; //toolbar position


    boolean[] ballMove = new boolean [8]; //if this is true only then the ball can move
    boolean[] ballHit = new boolean [8]; //this boolean becomes true when the ball hits the character and a life is lost, the the boolean is made false
    boolean levelComp1 = false; //if level 1 is complete this becomes true
    boolean levelComp2 = false;
    boolean level1start = false; //initializing how the balls start at the beginning of level 1
    boolean level2start = false; //initializing how the balls start at the beginning of level 2
    boolean level3start = false; //initializing how the balls start at the beginning of level 3

    boolean gameOverP = false;  //will be true if all lives are lost
    boolean winP = false; //will be true when all three levels are cleared
    boolean arrowHit = false; //will be true when arrow hits a ball. The purpose is to make the arrow go back down once a ball is hit
    boolean mainMenu = true; //menu screen
    boolean play = false; //game screen
    boolean instruction = false; //instructions screen
    boolean aboutScreen = false; //about screen
    boolean pause = false; //true if game is paused
    boolean back = false; //true if back is clicked in instructions or about


    //these will make the options appear different when the mouse is in range
    boolean playC = false;
    boolean aboutC = false;
    boolean instC = false;
    boolean backC = false;
    boolean nextC = false;
    boolean mainMenuC = false;
    boolean pauseC = false;
    boolean resumeC = false;
    boolean playAgainC = false;


    public void init ()  // Place the body of the initialization method here
    {
	resize (appletsize_x, appletsize_y); //applet size
	requestFocus ();

	//adding in order to use mouse clicked and mouse moved method
	addMouseListener (this);
	addMouseMotionListener (this);
	addKeyListener (this);

	//Toolkiting the Images (step 3)
	mainCharPic = getToolkit ().getImage (mainChar);
	prepareImage (mainCharPic, this);

	gameBackgroundPic = getToolkit ().getImage (gameBackground);
	prepareImage (gameBackgroundPic, this);

	gameToolBackgroundPic = getToolkit ().getImage (gameToolBackground);
	prepareImage (gameToolBackgroundPic, this);

	arrowPic = getToolkit ().getImage (arrow);
	prepareImage (arrowPic, this);

	livesPic = getToolkit ().getImage (lives);
	prepareImage (livesPic, this);

	smallBallPic = getToolkit ().getImage (smallBall);
	prepareImage (smallBallPic, this);

	bigBallPic = getToolkit ().getImage (bigBall);
	prepareImage (bigBallPic, this);

	biggerBallPic = getToolkit ().getImage (biggerBall);
	prepareImage (biggerBallPic, this);

	menuPic = getToolkit ().getImage (menu);
	prepareImage (menuPic, this);

	menuPlayPic = getToolkit ().getImage (menuPlay);
	prepareImage (menuPlayPic, this);

	menuInstructionsPic = getToolkit ().getImage (menuInstructions);
	prepareImage (menuInstructionsPic, this);

	instructionsPic = getToolkit ().getImage (instructions);
	prepareImage (instructionsPic, this);

	instructionsBackPic = getToolkit ().getImage (instructionsBack);
	prepareImage (instructionsBackPic, this);

	aboutPic = getToolkit ().getImage (about);
	prepareImage (aboutPic, this);

	aboutBackPic = getToolkit ().getImage (aboutBack);
	prepareImage (aboutBackPic, this);

	menuAboutPic = getToolkit ().getImage (menuAbout);
	prepareImage (menuAboutPic, this);

	level1CompPic = getToolkit ().getImage (level1Comp);
	prepareImage (level1CompPic, this);

	level2CompPic = getToolkit ().getImage (level2Comp);
	prepareImage (level2CompPic, this);

	gameBackground2Pic = getToolkit ().getImage (gameBackground2);
	prepareImage (gameBackground2Pic, this);

	gameBackground3Pic = getToolkit ().getImage (gameBackground3);
	prepareImage (gameBackground3Pic, this);

	gameOverPic = getToolkit ().getImage (gameOver);
	prepareImage (gameOverPic, this);

	winPic = getToolkit ().getImage (win);
	prepareImage (winPic, this);

	menuPic = getToolkit ().getImage (menu);
	prepareImage (menuPic, this);

	menuPlayPic = getToolkit ().getImage (menuPlay);
	prepareImage (menuPlayPic, this);

	menuInstructionsPic = getToolkit ().getImage (menuInstructions);
	prepareImage (menuInstructionsPic, this);

	instructionsPic = getToolkit ().getImage (instructions);
	prepareImage (instructionsPic, this);

	instructionsBackPic = getToolkit ().getImage (instructionsBack);
	prepareImage (instructionsBackPic, this);

	aboutPic = getToolkit ().getImage (about);
	prepareImage (aboutPic, this);

	aboutBackPic = getToolkit ().getImage (aboutBack);
	prepareImage (aboutBackPic, this);

	menuAboutPic = getToolkit ().getImage (menuAbout);
	prepareImage (menuAboutPic, this);

	playButtonPic = getToolkit ().getImage (playButton);
	prepareImage (playButtonPic, this);

	pauseButtonPic = getToolkit ().getImage (pauseButton);
	prepareImage (pauseButtonPic, this);

	pauseBoxPic = getToolkit ().getImage (pauseBox);
	prepareImage (pauseBoxPic, this);

	level1ClearedNextPic = getToolkit ().getImage (level1ClearedNext);
	prepareImage (level1ClearedNextPic, this);

	level1ClearedMenuPic = getToolkit ().getImage (level1ClearedMenu);
	prepareImage (level1ClearedMenuPic, this);

	level2ClearedNextPic = getToolkit ().getImage (level2ClearedNext);
	prepareImage (level2ClearedNextPic, this);

	level2ClearedMenuPic = getToolkit ().getImage (level2ClearedMenu);
	prepareImage (level2ClearedMenuPic, this);

	pauseButtonRedPic = getToolkit ().getImage (pauseButtonRed);
	prepareImage (pauseButtonRedPic, this);

	pauseMenuPic = getToolkit ().getImage (pauseMenu);
	prepareImage (pauseMenuPic, this);

	pauseResumePic = getToolkit ().getImage (pauseResume);
	prepareImage (pauseResumePic, this);

	gameOverMenuPic = getToolkit ().getImage (gameOverMenu);
	prepareImage (gameOverMenuPic, this);

	gameOverPlayAgainPic = getToolkit ().getImage (gameOverPlayAgain);
	prepareImage (gameOverPlayAgainPic, this);

	winMenuPic = getToolkit ().getImage (winMenu);
	prepareImage (winMenuPic, this);

	winPlayAgainPic = getToolkit ().getImage (winPlayAgain);
	prepareImage (winPlayAgainPic, this);



	// Add the picture to the list of images to be tracked (Step 4)
	MediaTracker tracker = new MediaTracker (this);
	tracker.addImage (mainCharPic, 0);
	tracker.addImage (gameBackgroundPic, 0);
	tracker.addImage (gameToolBackgroundPic, 0);
	tracker.addImage (arrowPic, 0);
	tracker.addImage (livesPic, 0);
	tracker.addImage (bigBallPic, 0);
	tracker.addImage (smallBallPic, 0);
	tracker.addImage (biggerBallPic, 0);
	tracker.addImage (level1CompPic, 0);
	tracker.addImage (level1CompPic, 0);
	tracker.addImage (gameOverPic, 0);
	tracker.addImage (winPic, 0);
	tracker.addImage (gameBackground2Pic, 0);
	tracker.addImage (gameBackground3Pic, 0);
	tracker.addImage (menuPic, 0);
	tracker.addImage (menuPlayPic, 0);
	tracker.addImage (menuInstructionsPic, 0);
	tracker.addImage (instructionsBackPic, 0);
	tracker.addImage (aboutPic, 0);
	tracker.addImage (aboutBackPic, 0);
	tracker.addImage (playButtonPic, 0);
	tracker.addImage (pauseButtonPic, 0);
	tracker.addImage (pauseBoxPic, 0);
	tracker.addImage (level1ClearedNextPic, 0);
	tracker.addImage (level1ClearedMenuPic, 0);
	tracker.addImage (level2ClearedNextPic, 0);
	tracker.addImage (level2ClearedMenuPic, 0);
	tracker.addImage (pauseButtonRedPic, 0);
	tracker.addImage (pauseMenuPic, 0);
	tracker.addImage (pauseResumePic, 0);
	tracker.addImage (gameOverMenuPic, 0);
	tracker.addImage (gameOverPlayAgainPic, 0);
	tracker.addImage (winMenuPic, 0);
	tracker.addImage (winPlayAgainPic, 0);


	for (int k = 0 ; k < 8 ; k++) //for loop for setting values for ball arrays
	{
	    ballMove [k] = false; //all the balls now have a fixed position
	    ballHit [k] = false; //none of the balls have hit the character
	    posX [k] = -200; //all balls have a position outside the screen till play becomes true
	    posY [k] = -200;
	    speedX [k] = 0; //all balls have no speed yet
	    speedY [k] = 0;
	}


	try
	{
	    tracker.waitForAll ();
	}


	catch (InterruptedException e)
	{
	}


	// If there were any errors loading the image, then abort the program with a message.
	if (tracker.isErrorAny ())
	{
	    showStatus ("Couldn't load ");
	    return;
	}
    } // init method


    public void start ()
    {
	// define a new thread
	Thread th = new Thread (this);
	// start this thread
	th.start ();
    }



    public void run ()
    {
	requestFocus ();
	// lower ThreadPriority
	Thread.currentThread ().setPriority (Thread.MIN_PRIORITY);

	while (true)
	{

	    for (int a = 0 ; a < 8 ; a++) //for loop for going through arrays of balls to check if they are at the borders of the screen so their current speed can be changed
	    {
		if (a < 5) //small balls
		{
		    if (posX [a] > appletsize_x - radiusSmall && ballMove [a] == true) // Ball is bounced if its x - position reaches the right border of the applet
		    {
			// Change direction of ball movement
			speedX [a] = -2;
			ballHit [a] = false; //if the arrow has been hit (becomes true) making it false again so that another life is lost if the same ball touches the character
		    }
		    // Ball is bounced if its x - position reaches the left border of the applet
		    if (posX [a] < 0 && ballMove [a] == true)
		    {
			// Change direction of ball movement
			speedX [a] = +2;
			ballHit [a] = false;
		    }


		    // Ball is bounced if its y - position reaches the bottom border of the applet

		    if (posY [a] > appletsize_y - radiusSmall && ballMove [a] == true)
		    {

			// Change direction of ball movement
			speedY [a] = -6;
			ballHit [a] = false;
		    }
		    // Ball is bounced if its y - position reaches the 3/4 of the screen of the applet. This is because the smaller balls have a smaller bounce
		    if (posY [a] < (appletsize_y - (appletsize_y / 3)) - 100 && ballMove [a] == true)
		    {
			// Change direction of ball movement
			speedY [a] = +3;
			ballHit [a] = false;
		    }
		}

		if (a == 5 || a == 6) //big balls
		{
		    if (posX [a] > appletsize_x - radiusBig && ballMove [a] == true)
		    {
			// Change direction of ball movement
			speedX [a] = -2;
			ballHit [a] = false;
		    }
		    // Ball is bounced if its x - position reaches the left border of the applet
		    if (posX [a] < 0 && ballMove [a] == true)
		    {
			// Change direction of ball movement
			speedX [a] = +2;
			ballHit [a] = false;
		    }

		    if (posY [a] < appletsize_y - 500 && ballMove [a] == true)
		    {

			// Change direction of ball movement
			speedY [a] = 4;
			ballHit [a] = false;
		    }

		    if (posY [a] > appletsize_y - radiusBig && ballMove [a] == true)
		    {

			// Change direction of ball movement
			speedY [a] = -6;
			ballHit [a] = false;

		    }
		}

		if (a == 7) //bigger ball
		{
		    if (posX [a] > appletsize_x - radiusBigger && ballMove [a] == true)
		    {

			// Change direction of ball movement
			speedX [a] = -2;
			ballHit [a] = false;
		    }
		    // Ball is bounced if its x - position reaches the left border of the applet
		    if (posX [a] < 0 && ballMove [a] == true)
		    {

			// Change direction of ball movement
			speedX [a] = +2;
			ballHit [a] = false;
		    }


		    //if the ball is g
		    if (posY [a] > appletsize_y - radiusBigger && ballMove [a] == true)
		    {

			// Change direction of ball movement
			speedY [a] = -6;
			ballHit [a] = false;
		    }
		    if (posY [a] < appletsize_y - 600 && ballMove [a] == true)
		    {

			// Change direction of ball movement
			speedY [a] = +6;
			ballHit [a] = false;
		    }
		}
	    }

	    ///when arrow hits the top or a ball, the position chaanges back to the bottom of the screen
	    if (arrowPosY <= 30 || arrowHit == true)
	    {
		arrowSpeedY = 0; // speed goes back to 0 till spacebar is clicked
		arrowPosX = charPosX + 65; //arrow position is changed back to where the character currently is
		arrowPosY = appletsize_y; //arrow goes below screen
		arrowHit = false; //this allows another ball to be hit
	    }

	    //if arrow hits the balls
	    for (int b = 0 ; b < 8 ; b++) //for loop to access arrays
	    {
		if (b == 7) //bigger ball
		    if (posY [b] >= arrowPosY && level == 3 && ballMove [b] == true) //if y position is below the y position of the arrow
			if (posX [b] <= arrowPosX && posX [b] + radiusBigger >= arrowPosX || posX [b] <= arrowPosX + 48 && posX [b] + radiusBigger >= arrowPosX && level == 3) //if the right side of the ball hits arrow or left side, or any corner/middle of the arrow
			{
			    arrowHit = true; // makes the arrow go back down
			    ballMove [5] = true; //a smaller ball (earth) appears to the right
			    ballMove [6] = true; //a smaller ball (earth) appears to the left
			    posX [5] = posX [b] - 100; //change position (left of the ball hit)
			    if (posX [5] < 0) //if the position is outside the left of the screen, make it go to the side
				posX [5] = 0;
			    posX [6] = posX [b] + 100; //change position (right of the ball hit)
			    if (posX [6] > appletsize_x)
				posX [6] = appletsize_x - radiusBig;
			    posY [5] = posX [b] - 50; //above the ball that was hit
			    posY [6] = posX [b] - 50;
			    speedX [5] = -7; //going left
			    speedY [5] = -7;
			    speedX [6] = 4; //going right
			    speedY [6] = 4;
			    radiusBigger = radiusSmall; //the big ball is made small so it can be displayed on the tool bar
			    posX [b] = appletsize_x - 350; //position on the toolbar
			    posY [b] = 2;
			    speedX [b] = 0; //making speed 0, position fixed
			    speedY [b] = 0;
			    ballMove [b] = false; //not allowing the ball to move

			    arrowPosY = appletsize_y; // arrow goes back down
			    score += 100; //adding points
			}

		if (b == 5 || b == 6) //big balls
		    if (posY [b] >= arrowPosY && (level == 2 || level == 3) && (ballMove [b] == true))
			if (posX [b] <= arrowPosX && posX [b] + radiusBig >= arrowPosX || (posX [b] <= arrowPosX + 48 && posX [b] + radiusBig >= arrowPosX))
			{

			    score += 50;
			    arrowHit = true;
			    arrowPosY = appletsize_y;

			    if (b == 5)
			    {
				radiusBig = radiusSmall;
				ballMove [0] = true; //smaller balls are coming in place of medium
				ballMove [1] = true;
				posX [0] = posX [b] - 100; //so it doesnt go out ofthe screen
				if (posX [0] < 0)
				    posX [0] = 0;
				posX [1] = posX [b] + 100;
				if (posX [1] > appletsize_x)
				    posX [1] = appletsize_x - radiusSmall;
				posY [0] = posY [b] - 50;
				posY [1] = posY [b] - 50;
				speedX [0] = -7; //the new balls are now moving
				speedY [0] = -7;
				speedX [1] = 4;
				speedY [1] = 4;
				posX [b] = appletsize_x - 250;
			    }

			    if (b == 6) //if its the 6th ball
			    {
				posX [b] = appletsize_x - 300; //position on toolbar
				radiusBig2 = radiusSmall;
				ballMove [2] = true; //another 2 small balls can move
				ballMove [3] = true;
				posX [2] = posX [b] - 100;
				if (posX [2] < 0)
				    posX [2] = 0;
				posX [3] = posX [b] + 100;
				if (posX [3] > appletsize_x)
				    posX [3] = appletsize_x - radiusSmall;
				posY [2] = posY [b] - 50;
				posY [3] = posY [b] - 50;
				speedX [2] = -7;
				speedY [2] = -7;
				speedX [3] = 4;
				speedY [3] = 4;
			    }
			    posY [b] = 2; // y position on toolbar
			    speedX [b] = 0; //ball position fixed
			    speedY [b] = 0;
			    ballMove [b] = false; //ball cannot move
			}

		if (b < 5) //small ball
		    if (posY [b] >= arrowPosY && ballMove [b] == true)
			if (posX [b] >= arrowPosX && posX [b] <= arrowPosX + 48 || (posX [b] <= arrowPosX + 48 && posX [b] + radiusSmall >= arrowPosX)) //if ball is greater than or equal to the starting of the arrow position and less than or equal to the end of the arrow picture on both sides of the ball
			{
			    posX [b] = toolbarPosX; //position on toolbar once the ball has been hit
			    toolbarPosX -= 50; //change positions so the next ball can go on a different space
			    posY [b] = 2;
			    speedX [b] = 0; //ball position fixed
			    speedY [b] = 0;
			    ballMove [b] = false;
			    score += 20;
			    arrowHit = true;
			    arrowPosY = appletsize_y; //ball cannot move
			}
	    }

	    ///Collision checker -- if ball hits character

	    for (int c = 0 ; c < 8 ; c++) //forchecking all the balls
		if (posX [c] <= charPosX + 100 && posX [c] >= charPosX && ballHit [c] == false) //if the ball is touching the character and NOT currently hitting the character
		    if (posY [c] > appletsize_y - 200)
		    {
			count++; // a life is lost
			ballHit [c] = true; //is true to show the character has been hit
		    }

	    if (level1start == true) //for starting or restarting the game
	    {
		level = 1;
		toolbarPosX = appletsize_x - 50; //toolbar space reset

		play = true; // game being played
		mainMenu = false;
		charPosX = (appletsize_x / 2) - 80; // character starting from the middle of the screen
		charPosY = appletsize_y - 200; // character on the screen
		arrowPosX = charPosX + 65;
		arrowPosY = appletsize_y; // int arrowPosY = 30;
		arrowSpeedY = 0;
		radiusSmall = 40;        // Radius of ball
		radiusBig = 80;
		radiusBig2 = 80;
		radiusBigger = 120;
		live1PosX = 0;
		live1PosY = 0;
		levelComp1 = false; //level 1 is not completed
		levelComp2 = false;
		level2start = false; //level2 cannot start
		level3start = false;
		gameOverP = false; //if the game was previously over, it is false now
		winP = false;
		pause = false;
		count = 0; // no life lost
		score = 0; // reset score

		// x - Position of ball
		for (int d = 0 ; d < 5 ; d++)
		{
		    ballMove [d] = true; //small balls can move

		    if (d % 2 == 0) //if even different speeds, to make the balls seem random
		    {
			speedX [d] = 4;
			speedY [d] = -4;
			posY [d] = appletsize_y / 5;
		    }
		    else
		    {
			speedX [d] = -4;
			speedY [d] = 4;
			posY [d] = appletsize_y / 3;
		    }
		}
		///random speeds and positions (so it is very random)
		posX [0] = appletsize_x / 2;         // x - Position of small ball
		posX [1] = appletsize_x - radiusSmall;        // x - Position of ball
		posX [2] = radiusSmall;         // x - Position of ball
		posX [3] = appletsize_x / 3;       // x - Position of ball
		posX [4] = appletsize_x / 6;
		speedX [3] = -4;
		speedY [3] = -4;
		speedX [2] = -6;
		speedY [2] = 5;

		for (int e = 5 ; e < 8 ; e++) //other balls are not on the screen
		{
		    posX [e] = -200;
		    posY [e] = -200;
		    speedX [e] = 0;
		    speedY [e] = 0;
		    ballMove [e] = false; //balls cannot move
		}
		level1start = false; // once the level starting potisions have been declared, this becomes false, now allowing the balls to change posiiton
	    }

	    //level 2 begins here
	    if (level2start == true)
	    {
		level = 2;
		toolbarPosX = appletsize_x - 50;
		ballMove [5] = true;
		ballMove [6] = true;

		radiusBig = 80;
		radiusBig2 = 80;

		charPosX = (appletsize_x / 2) - 80;

		posX [5] = 300;
		//System.out.println ("xpos b1 " + (posX [5]));
		posY [5] = 200;
		speedX [5] = -4;
		speedY [5] = 4;
		posX [6] = 400;
		//  System.out.println ("xpos b2 " + (posX [6]));
		posY [6] = 200;
		speedX [6] = 4;
		speedY [6] = 4;

		for (int f = 0 ; f < 5 ; f++)
		{
		    posX [f] = -200;
		    posY [f] = -200;
		    speedX [f] = 0;
		    speedY [f] = 0;
		    ballMove [f] = false;
		}
		levelComp2 = false;
		level2start = false;

	    }

	    //level 3 begins here
	    if (level3start)
	    {
		charPosX = (appletsize_x / 2) - 80;
		toolbarPosX = appletsize_x - 50;
		radiusBig = 80;
		radiusBig2 = 80;
		radiusBigger = 120;
		posX [7] = 300;
		posY [7] = 200;
		speedX [7] = 5;
		speedY [7] = 4;
		ballMove [7] = true;
		for (int g = 0 ; g < 7 ; g++)
		{
		    posX [g] = -200;
		    posY [g] = -200;
		    speedX [g] = 0;
		    speedY [g] = 0;
		    ballMove [g] = false;
		}
		level3start = false;

	    }
	    //level 1 complete
	    if (ballMove [0] == false && ballMove [1] == false && ballMove [2] == false && ballMove [3] == false && ballMove [4] == false && level == 1) //if all balls on this level are false (bcz they have been hit) then next level
		levelComp1 = true;


	    //level 2 complete
	    if (!ballMove [0] && !ballMove [1] && !ballMove [2] && !ballMove [3] && !ballMove [5] && !ballMove [6] && level == 2)
	    {
		levelComp2 = true;
		levelComp1 = false;

	    }
	    //win
	    if (level == 3 && !ballMove [0] && !ballMove [1] && !ballMove [2] && !ballMove [3] && !ballMove [4] && !ballMove [5] && !ballMove [6] && !ballMove [7])
		winP = true;

	    //game over
	    if (gameOverP) // if all lives are lost
		for (int h = 0 ; h < 8 ; h++)
		    if (ballMove [h] == true) //if the balls are on the screen moving (not on the tool bar)
		    { //go outside the screen and are not allowed to move
			posX [h] = -200;
			posY [h] = -200;
			speedX [h] = 0;
			speedY [h] = 0;
			ballMove [h] = false;
		    }

	    if (!pause) // update locations only if pause is false
	    {
		for (int i = 0 ; i < 8 ; i++)
		{
		    posX [i] += speedX [i]; //update the current location of the x on the ball
		    posY [i] += speedY [i]; //update the current location of the y on the ball
		}
		arrowPosY = arrowPosY + arrowSpeedY;
	    }
	    //System.out.println ("ypos b2 " + (posY [6]));




	    // repaint the applet
	    repaint ();

	    try
	    {
		// Stop thread for 20 milliseconds
		Thread.sleep (20);
	    }
	    catch (InterruptedException ex)
	    {
		// do nothing
	    }

	    // set ThreadPriority to maximum value
	    Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);
	}
    }


    public void menuSetup ()
    {

	//setting up booleans in order to make a main menu option work on level cleared boxes and pause
	play = false;
	mainMenu = true;
	levelComp2 = false;
	gameOverP = false;
	levelComp1 = false;
	winP = false;
	level = 0;
	level1start = false;
	level2start = false;
	level3start = false;
    }



    /*keyPressed detects for specific keys pressed by the user and corresponds them to different actions
    @paramKeyEventereceivesthe ASCII code value for the key character pressed by the user
     */

    public void keyPressed (KeyEvent ke)
    {
	if (ke.getKeyCode () == KeyEvent.VK_LEFT)
	{
	    if (!pause) //if pause is false
		if (!(charPosX <= 0)) //and the character is not goiing outside the screen
		    charPosX -= 50; //change position to left


	}


	// user presses right cursor key
	else if (ke.getKeyCode () == KeyEvent.VK_RIGHT)
	{
	    if (!pause)
		if (!(charPosX >= appletsize_x - 130))
		    charPosX += 50;


	}


	if (ke.getKeyCode () == KeyEvent.VK_SPACE)
	{
	    if (!pause)
		if (arrowPosY == appletsize_y) //only is the arrow is on its original posiiton
		{
		    arrowSpeedY = -10; //changing speed (going up)
		    arrowPosX = charPosX + 65; //from where the character currently was
		}
	}


	else
	{
	    /* Additionally the method prints out the ASCII - value if an other key is pressed. This is not necessary but a possibility for you to test which value a key has.*/
	    //        System.out.println ("Character: " + (char) key + " Integer Value: " + key);
	}
    }


    public void paint (Graphics g)
    {
	// Place the body of the drawing method here (100,200 is size of image)



	if (mainMenu) //main screen
	{
	    g.drawImage (menuPic, 0, 0, appletsize_x + 23, appletsize_y + 10, null); //start page

	    //changes color of play button
	    if (playC)
		g.drawImage (menuPlayPic, 0, 0, appletsize_x + 23, appletsize_y + 10, null);

	    //changes color of about button
	    if (aboutC)
		g.drawImage (menuAboutPic, 0, 0, appletsize_x + 23, appletsize_y + 10, null);

	    //changes color of instruction button
	    if (instC)
		g.drawImage (menuInstructionsPic, 0, 0, appletsize_x + 23, appletsize_y + 10, null);
	}


	if (instruction) //instructions Screen
	{
	    g.drawImage (instructionsPic, 0, 0, appletsize_x, appletsize_y, null); //background

	    //changes color of back button
	    if (backC)
		g.drawImage (instructionsBackPic, 0, 0, appletsize_x, appletsize_y, null);

	    //takes you back to menu screen
	    if (back)
		g.drawImage (menuPic, 0, 0, appletsize_x + 23, appletsize_y + 10, null);
	}


	if (aboutScreen)
	{
	    g.drawImage (aboutPic, 0, 0, appletsize_x + 23, appletsize_y + 10, null); //background

	    //changes color of back button
	    if (backC)
		g.drawImage (aboutBackPic, 0, 0, appletsize_x + 23, appletsize_y + 10, null);

	    //takes you back to menu screen
	    if (back)
		g.drawImage (menuPic, 0, 0, appletsize_x + 23, appletsize_y + 10, null);
	}


	if (play)
	{
	    g.drawImage (gameToolBackgroundPic, 0, 0, appletsize_x, appletsize_y, null); //background
	    g.drawImage (pauseButtonPic, 300, 0, 220, 50, null); //pause button pic

	    //if (pauseC)
	    //g.drawImage (pauseButtonRedPic, 300, 0, 220, 50, null);

	    //setting color and font and printing score on screen
	    g.setFont (scoreFont);
	    g.setColor (Color.BLACK);
	    g.drawString ("SCORE: " + Integer.toString (score), 550, 35);

	    if (level == 1)
		g.drawImage (gameBackgroundPic, 0, 45, appletsize_x, appletsize_y - 40, null); //level 1 background

	    if (level == 2)
		g.drawImage (gameBackground2Pic, 0, 45, appletsize_x, appletsize_y - 40, null); //level 2 background

	    if (level == 3)
		g.drawImage (gameBackground3Pic, 0, 45, appletsize_x, appletsize_y - 40, null); //level 3 background


	    else if (levelComp2 && level == 2)
	    {
		g.drawImage (level2CompPic, 320, 200, 480, 250, null); //level 2 cleared box

		//changes color of next level option
		if (nextC)
		    g.drawImage (level2ClearedNextPic, 320, 200, 480, 250, null);

		//changes color of main menu option
		if (mainMenuC)
		    g.drawImage (level2ClearedMenuPic, 320, 200, 480, 250, null);

		//setting color  and printing score on screen
		g.setColor (Color.WHITE);
		g.drawString (Integer.toString (score), 483, 387);
	    }


	    if (levelComp1 && level == 1)
	    {
		g.drawImage (level1CompPic, 320, 200, 480, 250, null); //level 1 cleared box

		//changes color of next level option
		if (nextC)
		    g.drawImage (level1ClearedNextPic, 320, 200, 480, 250, null);

		//changes color of main menu option
		if (mainMenuC)
		    g.drawImage (level1ClearedMenuPic, 320, 200, 480, 250, null);

		//setting color and printing score on screen
		g.setColor (Color.WHITE);
		g.drawString (Integer.toString (score), 484, 388);
	    }

	    if (gameOverP)
	    {
		g.drawImage (gameOverPic, 320, 200, 480, 250, null); //game over box

		//changes color of main menu option
		if (mainMenuC)
		    g.drawImage (gameOverMenuPic, 320, 200, 480, 250, null);

		//changes color of play again option
		if (playAgainC)
		    g.drawImage (gameOverPlayAgainPic, 320, 200, 480, 250, null);

		// printing score on screen
		g.drawString (Integer.toString (score), 488, 394);

	    }

	    else if (winP)
	    {
		g.drawImage (winPic, 320, 200, 480, 250, null); //win box

		//changes color of main menu option
		if (mainMenuC)
		    g.drawImage (winMenuPic, 320, 200, 480, 250, null);

		//changes color of play again option
		if (playAgainC)
		    g.drawImage (winPlayAgainPic, 320, 200, 480, 250, null);

		// printing score on screen
		g.drawString (Integer.toString (score), 475, 395);
	    }

	    if (pause)
	    {
		//changes color of pause button when clicked within range
		if (pauseC)
		    g.drawImage (pauseButtonRedPic, 300, 0, 220, 50, null);

		g.drawImage (pauseBoxPic, 320, 200, 480, 250, null); // pause box

		//changes color of resume option
		if (resumeC)
		    g.drawImage (pauseResumePic, 320, 200, 480, 250, null);

		if (mainMenu)
		{
		    mainMenu = true;
		    instruction = false;
		    play = false;
		    aboutScreen = false;
		    pause = false;

		}

		//changes color of main menu option
		if (mainMenuC)
		    g.drawImage (pauseMenuPic, 320, 200, 480, 250, null);

	    }

	    g.drawImage (arrowPic, arrowPosX, arrowPosY, 48, appletsize_y - 40, null); //arrow
	    g.drawImage (mainCharPic, charPosX, charPosY, 100, 200, null); //character

	    if (count == 0)
	    {
		//reset button
		g.drawImage (livesPic, live1PosX, live1PosY, 120, 55, null); //live 1
		g.drawImage (livesPic, live1PosX + 50, live1PosY, 120, 55, null);  //live 2
		g.drawImage (livesPic, live1PosX + 100, live1PosY, 120, 55, null); //live 3
		g.drawImage (livesPic, live1PosX + 150, live1PosY, 120, 55, null); //live 4
		g.drawImage (livesPic, live1PosX + 200, live1PosY, 120, 55, null); //live 5
	    }

	    if (count == 1)
	    {
		g.drawImage (livesPic, live1PosX, live1PosY, 120, 55, null); //live 1
		g.drawImage (livesPic, live1PosX + 50, live1PosY, 120, 55, null);  //live 2
		g.drawImage (livesPic, live1PosX + 100, live1PosY, 120, 55, null); //live 3
		g.drawImage (livesPic, live1PosX + 150, live1PosY, 120, 55, null); //live 4
	    }

	    if (count == 2)
	    {
		g.drawImage (livesPic, live1PosX, live1PosY, 120, 55, null); //live 1
		g.drawImage (livesPic, live1PosX + 50, live1PosY, 120, 55, null);  //live 2
		g.drawImage (livesPic, live1PosX + 100, live1PosY, 120, 55, null); //live 3
	    }

	    else if (count == 3)
	    {
		g.drawImage (livesPic, live1PosX, live1PosY, 120, 55, null); //live 1
		g.drawImage (livesPic, live1PosX + 50, live1PosY, 120, 55, null);  //live 2
	    }

	    else if (count == 4)
	    {
		g.drawImage (livesPic, live1PosX, live1PosY, 120, 55, null); //live 1
	    }

	    if (count == 5)
		gameOverP = true;

	    //drawing lines to seperate toolbar from background image
	    g.setColor (Color.black);
	    g.drawLine (0, 45, appletsize_x, 45);
	    g.drawLine (0, 46, appletsize_x, 46);
	    g.drawLine (0, 47, appletsize_x, 47);
	    g.drawLine (0, 48, appletsize_x, 48);


	    //drawing elemental ball
	    for (int p = 0 ; p < 8 ; p++)
	    {
		if (p < 5)
		    g.drawImage (smallBallPic, posX [p], posY [p], radiusSmall, radiusSmall, null);
		if (level == 2 || level == 3)
		{
		    if (p == 5)
			g.drawImage (bigBallPic, posX [p], posY [p], radiusBig, radiusBig, null);
		    if (p == 6)
			g.drawImage (bigBallPic, posX [p], posY [p], radiusBig2, radiusBig2, null);
		}
		if (level == 3 && p == 7)
		    g.drawImage (biggerBallPic, posX [p], posY [p], radiusBigger, radiusBigger, null);
	    }
	}
    }


    /*
    ThemouseMovedmethodisactivatedwhenever the mouse moves
     */
    public void mouseMoved (MouseEvent e)
    { // called during motion when no buttons are down
	xpos = e.getX (); // gets the x - coordinate of the mouse's location
	ypos = e.getY (); // gets the y- coordinate of the mouse's location
	//showStatus ("Mouse at (" + xpos + "," + ypos + ")");

	//will change colour for options if cursor is within range
	if (mainMenu)
	{
	    if (xpos >= 500 && xpos <= 650 && ypos >= 182 && ypos <= 240) //play
		playC = true;
	    else
		playC = false;
	    if (xpos >= 480 && xpos <= 669 && ypos >= 351 && ypos <= 408) //about
		aboutC = true;
	    else
		aboutC = false;
	    if (xpos >= 379 && xpos <= 748 && ypos >= 259 && ypos <= 317) //instruction
		instC = true;
	    else
		instC = false;
	}


	if (instruction)
	{
	    if (xpos >= 494 && xpos <= 653 && ypos >= 618 && ypos <= 687) //back
		backC = true;
	    else
		backC = false;
	}


	if (aboutScreen)
	{
	    if (xpos >= 511 && xpos <= 670 && ypos >= 600 && ypos <= 670) //back
		backC = true;
	    else
		backC = false;
	}


	if (play)
	{
	    if (levelComp1)
	    {
		if (xpos >= 347 && xpos <= 493 && ypos >= 409 && ypos <= 441) //next level
		    nextC = true;

		else
		    nextC = false;

		if (xpos >= 502 && xpos <= 639 && ypos >= 410 && ypos <= 436) //main menu
		    mainMenuC = true;

		else
		    mainMenuC = false;
	    }

	    if (levelComp2)
	    {
		if (xpos >= 342 && xpos <= 489 && ypos >= 406 && ypos <= 434) //next level
		    nextC = true;

		else
		    nextC = false;


		if (xpos >= 501 && xpos <= 639 && ypos >= 406 && ypos <= 434) //main menu
		    mainMenuC = true;

		else
		    mainMenuC = false;

	    }
	}


	if (xpos >= 300 && xpos <= 517 && ypos >= 0 && ypos <= 49) //pause button
	    pauseC = true;

	//pause box
	if (pause)
	{
	    if (xpos >= 361 && xpos <= 597 && ypos >= 304 && ypos <= 355) // resume
		resumeC = true;

	    else
		resumeC = false;

	    if (xpos >= 360 && xpos <= 597 && ypos >= 375 && ypos <= 420) // main menu
		mainMenuC = true;

	    else
		mainMenuC = false;

	}


	if (gameOverP)
	{
	    if (xpos >= 496 && xpos <= 635 && ypos >= 408 && ypos <= 437) // main menu
		mainMenuC = true;

	    else
		mainMenuC = false;

	    if (xpos >= 344 && xpos <= 485 && ypos >= 408 && ypos <= 436) //play again
		playAgainC = true;

	    else
		playAgainC = false;

	}


	if (winP)
	{
	    if (xpos >= 337 && xpos <= 484 && ypos >= 411 && ypos <= 439) //play again
		playAgainC = true;

	    else
		playAgainC = false;


	    if (xpos >= 493 && xpos <= 634 && ypos >= 412 && ypos <= 442) //main menu
		mainMenuC = true;

	    else
		mainMenuC = false;
	}
    }


    /*
      ThemouseClickedmethodisactivated whenever the mouse is clicked
       */
    public void mouseClicked (MouseEvent e)
    {
	xpos = e.getX (); // gets the x - coordinate of the mouse's location
	ypos = e.getY (); // gets the y- coordinate of the mouse's location
	//showStatus ("Mouse at (" + xpos + "," + ypos + ")");

	if (mainMenu)
	{
	    //System.out.println ("Mouse at (" + mx + "," + my + ")");

	    if (xpos >= 500 && xpos <= 650 && ypos >= 182 && ypos <= 240) //play
		level1start = true;
	    else
		play = false;
	    if (xpos >= 379 && xpos <= 748 && ypos >= 259 && ypos <= 317) //instruction
		instruction = true;
	    else
		instruction = false;

	    if (xpos >= 480 && xpos <= 669 && ypos >= 351 && ypos <= 408) //about
		aboutScreen = true;
	    else
		aboutScreen = false;
	}


	if (instruction)
	    if (xpos >= 494 && xpos <= 653 && ypos >= 618 && ypos <= 687) //back
		back = true;

	if (aboutScreen)
	    if (xpos >= 511 && xpos <= 670 && ypos >= 600 && ypos <= 670) //back
		back = true;

	if (play)
	{
	    if (levelComp1)
	    {
		if (xpos >= 347 && xpos <= 493 && ypos >= 409 && ypos <= 441) //next level
		{
		    level = 2;
		    level2start = true;
		    levelComp1 = false;
		    levelComp2 = false;
		}

		if (xpos >= 502 && xpos <= 639 && ypos >= 410 && ypos <= 436) //main menu
		    menuSetup ();
	    }
	    if (levelComp2)
	    {
		if (xpos >= 342 && xpos <= 489 && ypos >= 406 && ypos <= 434) //next level
		{
		    level = 3;
		    level3start = true;
		    levelComp2 = false;
		}

		if (xpos >= 501 && xpos <= 639 && ypos >= 406 && ypos <= 434) //main menu
		    menuSetup ();
	    }

	    if (gameOverP)
	    {
		if (xpos >= 496 && xpos <= 635 && ypos >= 408 && ypos <= 437) // main menu
		    menuSetup ();

		if (xpos >= 344 && xpos <= 485 && ypos >= 408 && ypos <= 436) //play again
		    level1start = true;
	    }

	    if (winP)
	    {
		if (xpos >= 337 && xpos <= 484 && ypos >= 411 && ypos <= 439) //play again
		    level1start = true;

		if (xpos >= 493 && xpos <= 634 && ypos >= 412 && ypos <= 442) //main menu
		    menuSetup ();

	    }

	    if (xpos >= 300 && xpos <= 517 && ypos >= 0 && ypos <= 49) //pause button
		pause = true;

	    //pause box
	    if (pause)
	    {
		if (xpos >= 361 && xpos <= 597 && ypos >= 304 && ypos <= 355) //resume
		    pause = false;

		if (xpos >= 360 && xpos <= 597 && ypos >= 375 && ypos <= 420) //main menu
		    menuSetup ();

	    }
	    repaint ();
	    e.consume ();
	}
    }


    /*ThemouseDraggedmethodisactivated whenever the mouse is dragged
	*/
    public void mouseDragged (MouseEvent e)
    { // called during motion with buttons down
	xpos = e.getX (); // gets the x - coordinate of the mouse's location
	ypos = e.getY (); // gets the y- coordinate of the mouse's location
	showStatus ("Mouse at (" + xpos + "," + ypos + ")");
	repaint ();
	e.consume ();
    }


    public void update (Graphics g)  //buffer
    {

	// initialize buffer
	if (dbImage == null)
	{
	    dbImage = createImage (this.getSize ().width, this.getSize ().height);
	    dbg = dbImage.getGraphics ();
	}


	// clear screen in background
	dbg.setColor (getBackground ());
	dbg.fillRect (0, 0, this.getSize ().width, this.getSize ().height);

	// draw elements in background
	dbg.setColor (getForeground ());
	paint (dbg);

	// draw image on the screen
	g.drawImage (dbImage, 0, 0, this);

    }


    public void keyTyped (KeyEvent e)
    {
	//does nothing

    }


    public void keyReleased (KeyEvent e)
    {
	//does nothing
    }


    public void mouseEntered (MouseEvent e)
    {
	//does nothing
    }


    public void mouseExited (MouseEvent e)
    {
	//does nothing
    }



    /*
    ThemousePressedmethoddetectswhenthemousebuttonis pressed and coresponds them to different actions
    @paramMouseEvente-Recieves the location of the mouse in relation to the applet screen
     */

    public void mousePressed (MouseEvent e)
    {
	//does nothing
    }


    /*
    ThemouseReleasedmethodisactivated whenever the mouse is released after being pressed
     */
    public void mouseReleased (MouseEvent e)
    {
	//does nothing
    }


    public void stop ()
    {
	//nothing right now
    }


    public void destroy ()
    {
	//nothing right now
    }
} // MovingBallApplet5 class
