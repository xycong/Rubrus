import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.applet.*;
import java.net.*;
import java.util.*;
import java.io.*;

/** The RubrusBoard class - carries out the tasks necessary to play the game
  * Tracks data and uses the RubrusPiece class objects
  * @author Tristan Amini, Victor Cong, Chen Chou
  * @version January 2012
  */
public class RubrusBoard extends JPanel implements MouseListener
{
    // Map to track placed pieces
    private int[] [] map;
    // Player who's turn it is
    private int currentPlayer = 1;
    // Pieces for all players
    private RubrusPiece playerPieces[] [];
    // Piece that is selected
    private int pieceMoving;
    private RubrusPiece movingPiece;
    // Coordinates of piece
    private Point lastPoint;
    // Music/audio
    private AudioClip backgroundSound;
    private AudioClip playerSkipSound;
    private AudioClip pieceSnapSound;
    private AudioClip instructionsSound;
    private AudioClip winnerSound;
    private AudioClip buttonSound;
    private AudioClip creditsSound;
    private boolean playerChange = false;
    // Images for menus
    private Image logo = new ImageIcon ("RubrusLogo.png").getImage ();
    private Image instructions1 = new ImageIcon ("Instructions-01.jpg").getImage ();
    private Image instructions2 = new ImageIcon ("Instructions-02.jpg").getImage ();
    private Image instructions3 = new ImageIcon ("Instructions-03.jpg").getImage ();
    private Image instructions4 = new ImageIcon ("Instructions-04.jpg").getImage ();
    private Image mainMenu = new ImageIcon ("MainMenu.jpg").getImage ();
    private Image credits = new ImageIcon ("About.jpg").getImage ();
    private Image rules = new ImageIcon ("Rules.jpg").getImage ();
    private Image modeChoice = new ImageIcon ("ModeSelect.jpg").getImage ();
    private Image endScreen = new ImageIcon ("EndScreen.jpg").getImage ();
    private Image background = new ImageIcon ("Background.jpg").getImage ();
    // Starting menu to show
    private Image menuToShow = mainMenu;
    private String menuShowing = "MainMenu";
    // Colours for each player
    private final Color[] COLOURS = {Color.WHITE, Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.BLACK};
    // Array to track how many pieces each player has left
    private int[] piecesLeft = {0, 21, 21, 21, 21};
    // Which players are still in the game
    private boolean[] playersLeft = {false, true, true, true, true};
    // Each players score
    private int[] playerScore = new int [4];
    // Checks for game over and how many players are out
    private boolean gameOver = false;
    private int skipCount = 0;
    // Highest score and player that scored it
    private int highestScore = 0;
    private int highestPlayer = 0;
    // Fonts
    Font font1 = new Font ("Helvetica", Font.PLAIN, 20);
    Font font2 = new Font ("Arial", Font.PLAIN, 40);
    // Modes
    private static int mode = 1;
    private static int pieceMode = 1;
    // Ties and whether game has started
    private boolean tie = false;
    private boolean gameStart = false;


    /** Constructs a new RubrusBoard object
      */
    public RubrusBoard ()
    {
	// Sets up the board area, loads in piece images and starts a new game
	setPreferredSize (new Dimension (805, 830));
	// Add mouse listeners and Key Listeners to the game board
	addMouseListener (this);
	addMouseMotionListener (new MouseMotionHandler ());
	this.setFocusable (true);
	this.addKeyListener (new KeyHandler ());
	this.requestFocusInWindow ();
	// Set up all music
	playerSkipSound = Applet.newAudioClip (getCompleteURL ("playerSkip.wav"));
	pieceSnapSound = Applet.newAudioClip (getCompleteURL ("pieceSnap.wav"));
	instructionsSound = Applet.newAudioClip (getCompleteURL ("instructionsSound.wav"));
	winnerSound = Applet.newAudioClip (getCompleteURL ("winnerSound.wav"));
	creditsSound = Applet.newAudioClip (getCompleteURL ("creditsSound.wav"));
	buttonSound = Applet.newAudioClip (getCompleteURL ("button.wav"));
	backgroundSound = Applet.newAudioClip (getCompleteURL ("backgroundMusic.wav"));

	// Save map size
	map = new int [20] [20];
	// Set up all pieces
	playerPieces = new RubrusPiece [5] [21];
	for (int piece = 0 ; piece < 21 ; piece++)
	{
	    playerPieces [1] [piece] = new RubrusPiece ();
	}
	for (int piece = 0 ; piece < 21 ; piece++)
	{
	    playerPieces [2] [piece] = new RubrusPiece ();
	}
	for (int piece = 0 ; piece < 21 ; piece++)
	{
	    playerPieces [3] [piece] = new RubrusPiece ();
	}
	for (int piece = 0 ; piece < 21 ; piece++)
	{
	    playerPieces [4] [piece] = new RubrusPiece ();
	}
	// Set up pieces
	pieceMoving = -1;
	movingPiece = null;
	backgroundSound.loop ();
    } // contructor


    /** Gets the URL needed for newAudioClip
      * @param fileName The name of the audio file
      */
    public URL getCompleteURL (String fileName)
    {
	try
	{
	    return new URL ("file:" + System.getProperty ("user.dir") + "/" + fileName);
	}
	catch (MalformedURLException e)
	{
	    System.err.println (e.getMessage ());
	}
	return null;
    }


    /** Draws the menu screen that is clicked on
      * @param mouseX       the X coordinate the mouse clicked on
      * @param mouseY       the Y coordinate the mouse clicked on
      */
    private void menu (int mouseX, int mouseY)
    {
	// Main menu
	if (menuShowing.equals ("MainMenu"))
	{
	    menuToShow = mainMenu;
	    // Clicking on mode choice
	    if (mouseX > 23 && mouseX < 420 && mouseY > 265 && mouseY < 360)
	    {
		buttonSound.play ();

		menuToShow = modeChoice;
		menuShowing = "ModeChoice";
	    }
	    // Clicking on instructions
	    else if (mouseX > 23 && mouseX < 430 && mouseY > 385 && mouseY < 480)
	    {
		buttonSound.play ();
		menuShowing = "Instructions1";
		menuToShow = instructions1;
		backgroundSound.stop ();
		instructionsSound.loop ();
	    }
	    // Clicking on Rules
	    else if (mouseX > 23 && mouseX < 223 && mouseY > 515 && mouseY < 610)
	    {
		buttonSound.play ();
		menuShowing = "Rules";
		menuToShow = rules;
	    }
	    // Clicking on Exit
	    else if (mouseX > 23 && mouseX < 169 && mouseY > 715 && mouseY < 810)
	    {
		hide ();
		System.exit (0);
	    }
	    // Clicking on Credits
	    else if (mouseX > 620 && mouseX < 810 && mouseY > 770 && mouseY < 815)
	    {
		buttonSound.play ();
		menuShowing = "Credits";
		menuToShow = credits;
		backgroundSound.stop ();
		creditsSound.loop ();
	    }
	}
	// Showing instructions
	else if (menuShowing.equals ("Instructions1"))
	{
	    // Clicking on next
	    if (mouseX > 650 && mouseX < 900 && mouseY > 770 && mouseY < 830)
	    {
		buttonSound.play ();
		menuShowing = "Instructions2";
		menuToShow = instructions2;
	    }
	}
	else if (menuShowing.equals ("Instructions2"))
	{
	    // Clicking on next
	    if (mouseX > 650 && mouseX < 900 && mouseY > 770 && mouseY < 830)
	    {
		buttonSound.play ();
		menuShowing = "Instructions3";
		menuToShow = instructions3;
	    }
	}
	else if (menuShowing.equals ("Instructions3"))
	{
	    // Clicking on next
	    if (mouseX > 650 && mouseX < 900 && mouseY > 770 && mouseY < 830)
	    {
		buttonSound.play ();
		menuShowing = "Instructions4";
		menuToShow = instructions4;
	    }
	}
	else if (menuShowing.equals ("Instructions4"))
	{
	    // Clicking on back
	    if (mouseX > 650 && mouseX < 900 && mouseY > 770 && mouseY < 830)
	    {
		buttonSound.play ();
		instructionsSound.stop ();
		backgroundSound.loop ();
		menuShowing = "MainMenu";
		menuToShow = mainMenu;
	    }
	}
	// Show rules
	else if (menuShowing.equals ("Rules"))
	{
	    // Clicking on back
	    if (mouseX > 650 && mouseX < 900 && mouseY > 770 && mouseY < 830)
	    {
		buttonSound.play ();
		menuShowing = "MainMenu";
		menuToShow = mainMenu;
	    }
	}
	// Show credits
	else if (menuShowing.equals ("Credits"))
	{
	    // Clicking on back
	    if (mouseX > 650 && mouseX < 900 && mouseY > 770 && mouseY < 830)
	    {
		buttonSound.play ();
		creditsSound.stop ();
		backgroundSound.loop ();
		menuShowing = "MainMenu";
		menuToShow = mainMenu;
	    }
	}
	// Show mode select screen
	else if (menuShowing.equals ("ModeChoice"))
	{
	    // Back to menu
	    if (mouseX > 650 && mouseX < 900 && mouseY > 770 && mouseY < 830)
	    {
		buttonSound.play ();
		menuShowing = "MainMenu";
		menuToShow = mainMenu;
	    }
	    // Start Game
	    else if (mouseX > 0 && mouseX < 280 && mouseY > 770 && mouseY < 830)
	    {
		buttonSound.play ();
		gameStart = true;
		setUpPieces ();
		if (mode == 2 || mode == 4)
		{
		    skipCount = 2;
		    playersLeft [2] = false;
		    playersLeft [3] = false;
		}

	    }
	    // 4 Player
	    else if (mouseX > 65 && mouseX < 380 && mouseY > 215 && mouseY < 279)
	    {
		buttonSound.play ();
		mode = 1;
	    }
	    // 2 Player
	    else if (mouseX > 385 && mouseX < 685 && mouseY > 215 && mouseY < 279)
	    {
		buttonSound.play ();
		mode = 2;
	    }
	    // 4 Player Center
	    else if (mouseX > 65 && mouseX < 380 && mouseY > 280 && mouseY < 344)
	    {
		buttonSound.play ();
		mode = 3;
	    }
	    // Team
	    else if (mouseX > 65 && mouseX < 380 && mouseY > 355 && mouseY < 419)
	    {
		buttonSound.play ();
		mode = 5;
	    }
	    // 2 Player Center
	    else if (mouseX > 385 && mouseX < 685 && mouseY > 280 && mouseY < 344)
	    {
		buttonSound.play ();
		mode = 4;
	    }
	    // Regular pieces
	    if (mouseX > 50 && mouseX < 360 && mouseY > 575 && mouseY < 685)
	    {
		buttonSound.play ();
		pieceMode = 1;
	    }
	    // Alternate Pieces
	    else if (mouseX > 365 && mouseX < 730 && mouseY > 575 && mouseY < 685)
	    {
		buttonSound.play ();
		pieceMode = 2;
	    }
	}
	// Show end screen
	else if (menuShowing.equals ("EndScreen"))
	{
	    // Clicking anywhere
	    if (mouseX > 0 && mouseY > 0)
	    {
		hide ();
		System.exit (0);
	    }
	}
    }


    /** Starts a new game
      */
    public void newGame ()
    {
	// Resets game variables
	gameStart = false;
	gameOver = false;
	skipCount = 0;
	highestPlayer = 0;
	highestScore = 0;
	tie = false;
	repaint ();
	// Reset grid
	map = new int [20] [20];
	// Reset scores
	playerScore = new int [4];
	// Reset player and pieces
	playerPieces = new RubrusPiece [5] [21];
	for (int piece = 0 ; piece < 21 ; piece++)
	{
	    playerPieces [1] [piece] = new RubrusPiece ();
	}
	for (int piece = 0 ; piece < 21 ; piece++)
	{
	    playerPieces [2] [piece] = new RubrusPiece ();
	}
	for (int piece = 0 ; piece < 21 ; piece++)
	{
	    playerPieces [3] [piece] = new RubrusPiece ();
	}
	for (int piece = 0 ; piece < 21 ; piece++)
	{
	    playerPieces [4] [piece] = new RubrusPiece ();
	}
	// Resets all pieces back to inventory
	for (int i = 1 ; i < 4 ; i++)
	    piecesLeft [i] = 21;
	// Resets inventory
	pieceMoving = -1;
	movingPiece = null;
	// Player resets to 1
	currentPlayer = 1;
	// Reset players
	for (int player = 1 ; player > 4 ; player++)
	    playersLeft [player] = true;
    }


    /** Returns the mode of the game (4 player, 2 player, centered, etc.)
      * @return The current mode of the game
      */
    public static int getMode ()
    {
	return mode;
    }


    /** Returns the mode of the pieces (normal, alternate)
      * @return The current piece mode of the game
      */
    public static int getPieceMode ()
    {
	return pieceMode;
    }


    /** Places pieces into array
      */
    public void setUpPieces ()
    {
	// Places all pieces from all players into the array
	for (int player = 1 ; player < 5 ; player++)
	{
	    for (int piece = 0 ; piece < 21 ; piece++)
	    {
		playerPieces [player] [piece].placeInArray (piece);
	    }
	}
    }


    /** Places pieces into array
      * @param player The player to draw pieces for
      */
    public void setUpPieces (int player)
    {
	// Places all pieces into the array for the given player
	for (int piece = 0 ; piece < 21 ; piece++)
	{
	    playerPieces [player] [piece].placeInArray (piece);
	}
    }


    /** Changes the player to the next valid player
      */
    private void changePlayer ()
    {
	// If 1 piece is left, reset pieces for that player so they can keep playing
	if (piecesLeft [currentPlayer] == 1)
	{
	    setUpPieces (currentPlayer);
	    piecesLeft [currentPlayer] = 21;
	}
	do
	{
	    // Checks if all players are out
	    if (!isGameOver ())
	    {
		gameOver = true;
		gameOver ();
	    }
	    // Increase current player
	    currentPlayer++;
	    // Sets current player to 1 to not get more than 4
	    if (currentPlayer > 4)
		currentPlayer = 1;
	}
	while (!canPlay () && skipCount < 4);
	// Checks if everyone has skipped
	if (skipCount == 4)
	    gameOver = true;
	repaint ();
	// No longer needs to change players
	playerChange = false;

    }


    /** Checks if the current player can play
      * @return Whether the player can play or not
      */
    private boolean canPlay ()
    {
	// If player has no pieces, their turn must be skipped
	if (piecesLeft [currentPlayer] == 0)
	{
	    playersLeft [currentPlayer] = false;
	    skipCount++;
	}
	// Makes sure player is still in game
	if (playersLeft [currentPlayer])
	{
	    return true;
	}
	else
	{
	    return false;
	}

    }


    /** Adjusts the players score
      */
    private void playerScore ()
    {
	playerScore [currentPlayer - 1] += playerPieces [currentPlayer] [pieceMoving].getPieceScore ();
    }


    /** Compares scores to figure out the winner
      */
    private void checkWinner ()
    {
	// Free for all
	if (mode != 5)
	{
	    // Runs through all 4 players
	    for (int player = 0 ; player < 4 ; player++)
	    {
		// If score is higher than the current highscore
		if (playerScore [player] > highestScore)
		{
		    // The player becomes the new high player
		    // and his score becomes the high score
		    tie = false;
		    highestScore = playerScore [player];
		    highestPlayer = player + 1;
		}
		// If the score is equal to the top score
		else if (playerScore [player] == highestScore && highestPlayer != player + 1)
		{
		    // The players tie with the same highscore
		    tie = true;
		    highestScore = playerScore [player];
		}
	    }
	}
	// Team mode
	else
	{
	    // Team 1 has a higher score
	    if (playerScore [0] + playerScore [3] > playerScore [1] + playerScore [2])
	    {
		highestPlayer = 1;
		highestScore = playerScore [0] + playerScore [3];
	    }
	    // Scores are the same
	    else if (playerScore [0] + playerScore [3] == playerScore [1] + playerScore [2])
	    {
		// Team 1 and 2 tie with the same score
		tie = true;
		highestScore = playerScore [0] + playerScore [3];
	    }
	    // Team 2 has a higher score
	    else
	    {
		highestPlayer = 2;
		highestScore = playerScore [1] + playerScore [2];
	    }
	}
    }


    /** Checks if all players are out
      * @return Whether the game is over or not
      */
    private boolean isGameOver ()
    {
	for (int playerToCheck = 1 ; playerToCheck > 4 ; playerToCheck++)
	{
	    System.out.println (playerToCheck);
	    if (playersLeft [playerToCheck])
		return false;
	}
	return true;
    }


    /** Checks the winner and runs the end screen
      */
    private void gameOver ()
    {
	// Checks the winner
	checkWinner ();
	// The game is over, set the screen to end screen
	gameStart = false;
	menuToShow = endScreen;
	menuShowing = "EndScreen";
    }


    /** Displays the winner(s)
      */
    private void drawGameOver (Graphics g)
    {
	repaint (0);
	winnerSound.play ();
	g.setColor (COLOURS [0]);
	g.setFont (font2);
	// One winner
	if (!tie)
	{
	    // Free for all mode
	    if (mode != 5)
	    {
		g.drawString ("Player " + highestPlayer + " wins with a score of " + highestScore + "!", 140, 535);
	    }
	    //Team mode
	    else
	    {
		g.drawString ("Team " + highestPlayer + " wins with a score of " + highestScore + "!", 135, 535);
	    }
	}
	// More than one winner
	else
	{
	    g.drawString ("It's a tie with a score of " + highestScore + "!", 185, 535);
	}
    }


    /** Draws the box where the unused pieces are held
      * @param g    the graphics to draw in
      */
    public void drawItemBox (Graphics g)
    {
	g.setColor (COLOURS [currentPlayer]);
	g.fillRect (0, 520, 790, 300);
	g.setColor (COLOURS [0]);
	g.fillRect (10, 530, 770, 280);
	g.setColor (COLOURS [5]);
	g.drawRect (10, 530, 770, 280);
    }


    /** Draws the boxes to hold mini inventories
      * @param numPlayers   the number of players to draw boxes for
      * @param g    the graphics to draw in
      */
    public void drawPlayerBoxes (int numPlayers, Graphics g)
    {
	for (int boxCount = 0 ; boxCount < numPlayers ; boxCount++)
	{
	    g.setColor (COLOURS [boxCount + 1]);
	    g.fillRect (520, 75 + 108 * boxCount, 275, 100);
	    if (boxCount < 2)
		g.fillRect (665, 5 + 34 * boxCount, 61, 30);
	    else
		g.fillRect (735, 5 + 34 * (boxCount - 2), 61, 30);
	    g.setColor (COLOURS [0]);
	    g.fillRect (525, 80 + 108 * boxCount, 265, 90);
	    if (boxCount < 2)
		g.fillRect (668, 7 + 34 * boxCount, 56, 26);
	    else
		g.fillRect (738, 7 + 34 * (boxCount - 2), 56, 26);
	    g.setColor (COLOURS [5]);
	    g.drawRect (520, 75 + 108 * boxCount, 275, 100);
	    if (boxCount < 2)
		g.drawRect (665, 5 + 34 * boxCount, 61, 30);
	    else
		g.drawRect (735, 5 + 34 * (boxCount - 2), 61, 30);
	}
    }


    /** Updates the player scores
      * @param g    the graphics to draw in
      */
    public void drawPlayerScore (Graphics g)
    {
	for (int players = 0 ; players < 4 ; players++)
	{
	    // Display the score
	    g.setColor (Color.BLACK);
	    g.setFont (font1);
	    // Draws in the 2 boxes on the left
	    if (players < 2)
		g.drawString ("" + playerScore [players], 684, 30 + (34 * players));
	    // Draws in the 2 boxes on the right
	    else
		g.drawString ("" + playerScore [players], 755, 30 + (34 * (players - 2)));
	}
    }


    /** Draws the pieces placed on the board
      * @param g The graphics to draw in
      */
    public void drawPlacedPieces (Graphics g)
    {
	// Scan board
	for (int row = 0 ; row < map.length ; row++)
	{
	    for (int col = 0 ; col < 20 ; col++)
	    {
		// Draw different colour depending on piece
		if (map [row] [col] > 0)
		{
		    g.setColor (COLOURS [map [row] [col]]);
		    g.fillRect (25 * col, 25 * row, 25, 25);
		}
	    }
	}
    }


    /** Draws the grid where the pieces will be placed
      * @param g The graphics to draw in
      */
    public void drawGrid (Graphics g)
    {
	g.setColor (Color.black);
	// Horizontal Lines
	for (int lineCount = 0 ; lineCount < 21 ; lineCount++)
	{
	    g.drawLine (0, lineCount * 25, 20 * 25, lineCount * 25);
	}
	// Vertical lines
	for (int lineCount = 0 ; lineCount < 21 ; lineCount++)
	{
	    g.drawLine (lineCount * 25, 0, lineCount * 25, 20 * 25);
	}
    }


    /** Draws small starting circles
      * @param g    the graphics to draw in
      */
    public void drawStartCircles (Graphics g)
    {
	// Standard four player/ team 4 player/ alternate pieces four player
	if (mode == 1 || mode == 5)
	{
	    g.setColor (COLOURS [1]);
	    g.fillOval (7, 7, 12, 12);
	    g.setColor (COLOURS [2]);
	    g.fillOval (482, 7, 12, 12);
	    g.setColor (COLOURS [3]);
	    g.fillOval (7, 482, 12, 12);
	    g.setColor (COLOURS [4]);
	    g.fillOval (482, 482, 12, 12);
	}
	// Two player standard
	else if (mode == 2)
	{
	    g.setColor (COLOURS [1]);
	    g.fillOval (7, 7, 12, 12);
	    g.setColor (COLOURS [4]);
	    g.fillOval (482, 482, 12, 12);
	    playersLeft [2] = false;
	    playersLeft [3] = false;
	}
	// Centered 4 player
	else if (mode == 3)
	{
	    g.setColor (COLOURS [1]);
	    g.fillOval (132, 132, 12, 12);
	    g.setColor (COLOURS [2]);
	    g.fillOval (382, 132, 12, 12);
	    g.setColor (COLOURS [3]);
	    g.fillOval (132, 382, 12, 12);
	    g.setColor (COLOURS [4]);
	    g.fillOval (382, 382, 12, 12);
	}
	// Centered 4 player
	else if (mode == 4)
	{
	    g.setColor (COLOURS [1]);
	    g.fillOval (132, 132, 12, 12);
	    g.setColor (COLOURS [4]);
	    g.fillOval (382, 382, 12, 12);
	}
    }


    /** Draws the inventory
      * @param g The graphics to use
      */
    private void drawInventory (Graphics g)
    {
	for (int currentPiece = 0 ; currentPiece < 21 ; currentPiece++)
	{
	    g.setColor (Color.ORANGE);
	    playerPieces [currentPlayer] [currentPiece].drawPiece (g, COLOURS [currentPlayer]);
	}
    }


    /** Draws the little pieces in the small boxes
      * @param g    The graphics to draw in
      */
    private void drawLittlePiece (Graphics g)
    {
	for (int player = 0 ; player < 4 ; player++)
	{
	    if (mode != 2 || mode == 2 && player == 0 || mode == 2 && player == 3)
	    {
		for (int currentPiece = 0 ; currentPiece < 21 ; currentPiece++)
		{
		    if (!playerPieces [player + 1] [currentPiece].isPiecePlaced ())
			playerPieces [player + 1] [currentPiece].drawLittlePieces (g, COLOURS [player + 1], player);
		}
	    }
	}
    }


    /** Repaint the board's drawing panel
	  * @param g The graphics context
	  */
    public void paintComponent (Graphics g)
    {
	super.paintComponent (g);
	// If game is running, show board, pieces, etc.
	if (gameStart)
	{
	    // Draw background
	    g.drawImage (background, 0, 0, this);
	    // Draw white background for grid
	    g.setColor (Color.white);
	    g.fillRect (0, 0, 500, 500);
	    // Draws all player components
	    drawPlayerBoxes (4, g);
	    drawStartCircles (g);
	    drawPlacedPieces (g);
	    drawGrid (g);
	    if (playerChange)
		changePlayer ();
	    drawItemBox (g);
	    drawLittlePiece (g);
	    drawInventory (g);
	    // Draws the logo
	    g.drawImage (logo, 520, -18, this);
	    drawPlayerScore (g);
	}
	// Show menu
	else
	{
	    g.drawImage (menuToShow, 0, 0, this);
	    // IF game over, go to game over screen
	    if (gameOver)
	    {
		gameOver ();
		drawGameOver (g);
	    }
	}
    } // paint component method


    /** Checks if the move is valid
      *@param mouseX The X placement of the mouse when piece is dropped
      *@param mouseY The Y placement of the mouse when piece is dropped
      *@return Whether the move is valid or not
      */
    public boolean validMove (int mouseX, int mouseY)
    {
	repaint ();
	// Checks if mouse is within grid
	if (mouseX >= 0 && mouseX <= 500 && mouseY >= 0 && mouseY <= 500)
	    return true;
	// Move is not valid
	return false;
    }


    /** Places the current piece onto the grid
      */
    private void placeOnGrid ()
    {

	playerPieces [currentPlayer] [pieceMoving].snapToGrid ();
	// Makes sure the piece can be placed
	if (!playerPieces [currentPlayer] [pieceMoving].placeOnMap (map, currentPlayer))
	{
	    playerPieces [currentPlayer] [pieceMoving].placeInArray (pieceMoving);
	    currentPlayer--;
	}
	else
	    // Lower number of pieces remaining for current player
	    {
		pieceSnapSound.play ();
		piecesLeft [currentPlayer]--;
		playerPieces [currentPlayer] [pieceMoving].placeInArray (-1);
		playerScore ();
	    }
	repaint ();
    }


    // Keyboard events you can listen for since this JPanel is a KeyListener
    private class KeyHandler extends KeyAdapter
    {
	/** Responds to a dragging the mouse
	  * @param event Pressing and releasing on a certain key
	  */
	public void keyReleased (KeyEvent event)
	{
	    if (pieceMoving != -1)
	    {
		// Pressing 'w','a','s','d' will flip the piece moving
		if (event.getKeyCode () == KeyEvent.VK_D || event.getKeyCode () == KeyEvent.VK_A)
		{
		    playerPieces [currentPlayer] [pieceMoving].flipPieceColumn ();
		}
		else if (event.getKeyCode () == KeyEvent.VK_W || event.getKeyCode () == KeyEvent.VK_S)
		{

		    playerPieces [currentPlayer] [pieceMoving].flipPieceRow ();
		}
	    }
	    // Repaint the screen after the change
	    repaint ();
	    // Game has started, allowing skipping turns and quitting
	    if (gameStart)
	    {
		// Space pressed, skip player's turn from now on
		if (event.getKeyCode () == KeyEvent.VK_SPACE)
		{
		    playersLeft [currentPlayer] = false;
		    skipCount++;
		    if (skipCount < 4)
		    {
			changePlayer ();
			playerSkipSound.play ();
			repaint ();
		    }
		    else
		    {
			gameOver = true;
			gameStart = false;
		    }
		}
		// Escape pressed, quit the game
		if (event.getKeyCode () == KeyEvent.VK_ESCAPE)
		{
		    hide ();
		    System.exit (0);
		}
	    }
	}
    }


    private class MouseMotionHandler extends MouseMotionAdapter
    {
	/** Responds to a dragging the mouse
	  * @param event The dragging of the mouse
	  */
	public void mouseDragged (MouseEvent event)
	{
	    if (gameStart)
	    {
		// Change position of piece being moved
		if (movingPiece != null)
		{
		    movingPiece.translate (lastPoint, event.getPoint ());
		    lastPoint = event.getPoint ();
		}
	    }
	    repaint ();
	}
    }


    /** Checks to see if the left mouse button has been clicked over a piece
      * @param event The left mouse button press
      */
    public void mousePressed (MouseEvent event)
    {
	// Only if game has started
	if (gameStart)
	{
	    // Gets the coordinates of the last mouse position
	    Point selectedPoint = event.getPoint ();
	    // Checks each piece if the point is contained in one of the pieces
	    for (int i = 0 ; i < 21 ; i++)
	    {
		// If it does contain the point, the piece becomes the moving piece
		if (playerPieces [currentPlayer] [i].contains (selectedPoint))
		{
		    // Piece becomes the moving piece
		    movingPiece = playerPieces [currentPlayer] [i];
		    pieceMoving = i;
		    lastPoint = selectedPoint;
		}
	    }
	    // If piece has been placed, it cannot be moved again
	    if (pieceMoving != -1 && playerPieces [currentPlayer] [pieceMoving].isPiecePlaced ())
	    {
		pieceMoving = -1;
		movingPiece = null;
	    }
	}
	repaint ();
	return;
    }


    /** Detects when the mouse button has been released
      * @param event Releasing the mouse button
      */
    public void mouseReleased (MouseEvent event)
    {
	if (gameStart)
	{
	    if (event.getModifiers () == MouseEvent.BUTTON1_MASK)
	    {
		if (pieceMoving != -1)
		{
		    // Check if piece is in valid position

		    if (validMove (event.getX (), event.getY ()))
		    {
			placeOnGrid ();
		    }
		    else
		    {
			playerPieces [currentPlayer] [pieceMoving].resetPosition ();
		    }
		}
		// Checks to make sure the move is valid before changing players
		if (pieceMoving != -1 && validMove (event.getX (), event.getY ()))
		    changePlayer ();
		pieceMoving = -1;
		movingPiece = null;
	    }
	}
	repaint ();
    }


    /** Checks for then the button has been cliked, only used for right mouse button
      * @param event The right mouse button click
      */
    public void mouseClicked (MouseEvent event)
    {
	// Only when game has started
	if (gameStart)
	{
	    // Rotate piece clockwise when right-clicked
	    if (event.getModifiers () == MouseEvent.BUTTON3_MASK && pieceMoving != -1)
	    {
		playerPieces [currentPlayer] [pieceMoving].rotateClockwise ();
	    }
	}
	else
	    menu (event.getX (), event.getY ());
	repaint ();
    }


    // Unused methods for mouseListener
    public void mouseEntered (MouseEvent event)
    {
    }


    public void mouseExited (MouseEvent event)
    {
    }
}


