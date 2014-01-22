import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
import javax.swing.*;

/** The RubrusPiece class - handles the piece objects
  * Keeps track of piece methods and data
  * @author Tristan Amini, Victor Cong, Chen Chou
  * @version January 2012
  */

public class RubrusPiece
{
    // Arrays to hold the pieces
    private int[] [] pieceArea;
    private int[] [] defaultArea;
    private int[] [] flipArray;
    // The coordinates of the piece
    private int pieceX;
    private int pieceY;
    private int pieceStartX;
    private int pieceStartY;
    // The attributes of the piece
    private int pieceScore = 0;
    private int pieceNumber = -1;
    private boolean isPlaced = false;
    // Variables to hold game mode selection
    private int gameMode = 0;
    private int pieceMode = 0;


    /** Constructor for the RubrusPiece class
      */
    public RubrusPiece ()
    {
	// Set piece area to blank
	pieceArea = new int [5] [5];
	// Get game and piece mode
	gameMode = RubrusBoard.getMode ();
	pieceMode = RubrusBoard.getPieceMode ();
    }


    /** Places the piece in the array
      * @param index    the piece that is being placed
      */
    public void placeInArray (int index)
    {
	// Resets the array containers and all variables
	pieceNumber = index;
	pieceArea = new int [5] [5];
	defaultArea = new int [5] [5];
	isPlaced = false;
	// Determines pieces to draw based on pieceMode
	pieceMode = RubrusBoard.getPieceMode ();
	// Regular piece mode
	if (pieceMode == 1)
	{
	    // Check if piece has been placed
	    if (index == -1)
	    {
		isPlaced = true;
		pieceArea = new int [5] [5];
	    }
	    //1 block
	    else if (index == 0)
	    {
		pieceArea [2] [2] = 1;
		pieceScore = 1;
		pieceX = -20;
		pieceY = 488;
	    }
	    // Line of 2 squares
	    else if (index == 1)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [2] = 1;
		pieceScore = 2;
		pieceX = -20;
		pieceY = 563;
	    }
	    // Line of 3 squares
	    else if (index == 2)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 3;
		pieceX = -20;
		pieceY = 628;
	    }
	    // 3 block L shape
	    else if (index == 3)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [2] [3] = 1;
		pieceScore = 3;
		pieceX = -20;
		pieceY = 720;
	    }
	    // Line of 4 squares
	    else if (index == 4)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [2] = 1;
		pieceArea [4] [2] = 1;
		pieceScore = 4;
		pieceX = 478;
		pieceY = 513;
	    }
	    // Small T piece
	    else if (index == 5)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [2] [3] = 1;
		pieceScore = 4;
		pieceX = 125;
		pieceY = 720;
	    }
	    // square piece
	    else if (index == 6)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceScore = 4;
		pieceX = 265;
		pieceY = 720;
	    }
	    // 4 piece L shape
	    else if (index == 7)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [2] = 1;
		pieceArea [3] [1] = 1;
		pieceScore = 4;
		pieceX = 265;
		pieceY = 628;
	    }
	    // 4 block z shape
	    else if (index == 8)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [2] [3] = 1;
		pieceScore = 4;
		pieceX = 520;
		pieceY = 720;
	    }
	    // 5 block line
	    else if (index == 9)
	    {
		pieceArea [2] [0] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [2] [3] = 1;
		pieceArea [2] [4] = 1;
		pieceScore = 5;
		pieceX = 125;
		pieceY = 488;
	    }
	    // Hat shape
	    else if (index == 10)
	    {
		pieceArea [2] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [2] [3] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [3] = 1;
		pieceScore = 5;
		pieceX = 380;
		pieceY = 695;
	    }
	    // Plus sign
	    else if (index == 11)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [2] [3] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 5;
		pieceX = 380;
		pieceY = 628;
	    }
	    // 5 piece b shape
	    else if (index == 12)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [2] [3] = 1;
		pieceScore = 5;
		pieceX = 660;
		pieceY = 720;
	    }
	    // W shape
	    else if (index == 13)
	    {
		pieceArea [1] [3] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [2] [3] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 5;
		pieceX = 265;
		pieceY = 513;
	    }
	    // -|-- shape
	    else if (index == 14)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [2] = 1;
		pieceArea [4] [2] = 1;
		pieceScore = 5;
		pieceX = 685;
		pieceY = 513;
	    }
	    // Large L shape
	    else if (index == 15)
	    {
		pieceArea [0] [2] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [2] = 1;
		pieceArea [3] [3] = 1;
		pieceScore = 5;
		pieceX = 558;
		pieceY = 538;
	    }
	    // |_ shape
	    else if (index == 16)
	    {
		pieceArea [2] [0] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [2] = 1;
		pieceArea [4] [2] = 1;
		pieceScore = 5;
		pieceX = 125;
		pieceY = 603;
	    }
	    // ___i- sign
	    else if (index == 17)
	    {
		pieceArea [2] [0] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [1] [3] = 1;
		pieceScore = 5;
		pieceX = 125;
		pieceY = 563;
	    }
	    // T piece
	    else if (index == 18)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [2] = 1;
		pieceArea [3] [3] = 1;
		pieceScore = 5;
		pieceX = 380;
		pieceY = 513;
	    }
	    // s piece
	    else if (index == 19)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [2] = 1;
		pieceArea [3] [3] = 1;
		pieceScore = 5;
		pieceX = 520;
		pieceY = 628;
	    }
	    // Weird bird shape
	    else if (index == 20)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [2] [3] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 5;
		pieceX = 660;
		pieceY = 628;
	    }
	}
	// Alternate pieces
	if (pieceMode == 2)
	{
	    // If the piece has been place, the array is reset to 0
	    if (index == -1)
	    {
		isPlaced = true;
		pieceArea = new int [5] [5];
	    }
	    //1 block
	    else if (index == 0)
	    {
		pieceArea [2] [2] = 1;
		pieceScore = 1;
		pieceX = -20;
		pieceY = 488;
	    }
	    // Line of 2 squares
	    else if (index == 1)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [2] = 1;
		pieceScore = 2;
		pieceX = -20;
		pieceY = 548;
	    }
	    // Diagonal line of 3 squares
	    else if (index == 2)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [3] = 1;
		pieceScore = 3;
		pieceX = 5;
		pieceY = 608;
	    }
	    // 3 block arrow shape
	    else if (index == 3)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [3] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 3;
		pieceX = -20;
		pieceY = 700;
	    }
	    // Diagonal T shape of 4 squares
	    else if (index == 4)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [3] = 1;
		pieceScore = 4;
		pieceX = 440;
		pieceY = 513;
	    }
	    // Diagonal cube
	    else if (index == 5)
	    {
		pieceArea [1] [2] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [3] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 4;
		pieceX = 100;
		pieceY = 700;
	    }
	    //
	    else if (index == 6)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [2] = 1;
		pieceArea [3] [3] = 1;
		pieceScore = 4;
		pieceX = 210;
		pieceY = 700;
	    }
	    // 4 piece V shape
	    else if (index == 7)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 4;
		pieceX = 395;
		pieceY = 608;
	    }
	    // 4 block crescent shape
	    else if (index == 8)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 4;
		pieceX = 560;
		pieceY = 700;
	    }
	    // 5 block x
	    else if (index == 9)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [3] = 1;
		pieceScore = 5;
		pieceX = 80;
		pieceY = 513;
	    }
	    // 5 block crescent
	    else if (index == 10)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [3] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 5;
		pieceX = 325;
		pieceY = 700;
	    }
	    // Face shape
	    else if (index == 11)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [2] = 1;
		pieceArea [3] [3] = 1;
		pieceScore = 5;
		pieceX = 435;
		pieceY = 700;
	    }
	    // Slanted t shape
	    else if (index == 12)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [3] = 1;
		pieceScore = 5;
		pieceX = 660;
		pieceY = 700;
	    }
	    // 6 block J
	    else if (index == 13)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 6;
		pieceX = 200;
		pieceY = 513;
	    }
	    // 2/3 cube
	    else if (index == 14)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 6;
		pieceX = 685;
		pieceY = 513;
	    }
	    // Arrow
	    else if (index == 15)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [1] = 1;
		pieceScore = 6;
		pieceX = 560;
		pieceY = 513;
	    }
	    // Unfinished i
	    else if (index == 16)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [3] = 1;
		pieceScore = 6;
		pieceX = 140;
		pieceY = 608;
	    }
	    // ball piece
	    else if (index == 17)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [3] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 6;
		pieceX = 265;
		pieceY = 608;
	    }
	    // hook piece
	    else if (index == 18)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 6;
		pieceX = 320;
		pieceY = 513;
	    }
	    // undescribable
	    else if (index == 19)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [2] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [2] [3] = 1;
		pieceArea [3] [1] = 1;
		pieceArea [3] [3] = 1;
		pieceScore = 6;
		pieceX = 530;
		pieceY = 608;
	    }
	    // y shape
	    else if (index == 20)
	    {
		pieceArea [1] [1] = 1;
		pieceArea [1] [3] = 1;
		pieceArea [2] [1] = 1;
		pieceArea [2] [2] = 1;
		pieceArea [2] [3] = 1;
		pieceArea [3] [2] = 1;
		pieceScore = 6;
		pieceX = 660;
		pieceY = 608;
	    }
	}
	// Sets the start coordinates
	pieceStartX = pieceX;
	pieceStartY = pieceY;

	// Checks each row and column for a piece, then assigns it to default array
	// to keep track of orientation
	for (int row = 0 ; row < 5 ; row++)
	{
	    for (int col = 0 ; col < 5 ; col++)
	    {
		if (pieceArea [row] [col] == 1)
		{
		    defaultArea [row] [col] = 1;
		}
	    }
	}
    }


    /** Resets the orientation and position of the piece
      */
    public void resetPosition ()
    {
	// Position is reset
	pieceX = pieceStartX;
	pieceY = pieceStartY;

	// Using the index, the piece is reinputed into the array
	placeInArray (pieceNumber);
    }


    /** Checks if piece can be placed based on sides, corners and pieces underneath
	*@param map             the map the piece will be placed on
	*@param currentPlayer   the player placing the piece
	*@return                whether the piece can be placed there or not based on sides
	*/
    public boolean placeOnMap (int[] [] map, int currentPlayer)
    {
	// Find the row and column in the map the piece will be placed
	int baseRow = pieceY / 25;
	int baseCol = pieceX / 25;
	boolean valid = true;
	boolean cornerFound = false;
	// Checks through for each square in th epiece array
	for (int row = 0 ; row < 5 ; row++)
	{
	    for (int col = 0 ; col < 5 ; col++)
	    {
		// Only occurs when there is a block there
		if (pieceArea [row] [col] == 1)

		    {
			// Make sure no block placed under it
			int mapRow = row + baseRow;
			int mapCol = col + baseCol;
			if (mapRow > 19 || mapCol > 19 || mapRow < 0 || mapCol < 0 || map [mapRow] [mapCol] != 0)
			    return false;
			// If there is no corner connection found yet
			if (!cornerFound)
			{
			    // Check corners and sides
			    cornerFound = checkCorners (mapRow, mapCol, currentPlayer, map);
			}
			// Checks if it touches a piece
			valid = checkSides (mapRow, mapCol, map, currentPlayer);
			// If any square touches a piece, it cannot be placed
			if (!valid)
			    return false;
		    }
	    }
	}
	// If corner has been found, make sure it doesn't touch another
	if (cornerFound)
	{
	    for (int row = 0 ; row < 5 ; row++)
	    {
		for (int col = 0 ; col < 5 ; col++)
		{
		    // Only occurs when there is a block there
		    if (pieceArea [row] [col] == 1)
		    {
			int mapRow = row + baseRow;
			int mapCol = col + baseCol;
			// Edit the map to put in the new piece
			map [mapRow] [mapCol] = currentPlayer;
		    }
		}
	    }
	    // Piece has been placed
	    return true;
	}
	// Invalid position, piece snaps back to inventory
	else
	    return false;
    }


    /** Checks if pieces are touching by corners
	*@param mapRow          the row the piece will be drawn on
	*@param mapCol          the column the piece will be drawn on
	*@param map             the map the piece will be placed on
	*@param currentPlayer   the player placing the piece
	*@return                whether the piece can be placed there or not based on corners
	*/
    public boolean checkCorners (int mapRow, int mapCol, int currentPlayer, int[] [] map)
    {
	boolean cornerFound = false;
	// Get the game mode
	gameMode = RubrusBoard.getMode ();
	// Check to make sure it's at starting corners or touching a corner
	if (!cornerFound)
	{
	    // Standard four player / teams
	    if (gameMode == 1 || gameMode == 5)
	    {
		// Start circles
		if (currentPlayer == 1 && mapRow == 0 && mapCol == 0)
		    return true;
		else if (currentPlayer == 2 && mapRow == 0 && mapCol == 19)
		    return true;
		else if (currentPlayer == 3 && mapRow == 19 && mapCol == 0)
		    return true;
		else if (currentPlayer == 4 && mapRow == 19 && mapCol == 19)
		    return true;
	    }
	    // Two player standard
	    else if (gameMode == 2)
	    {
		// Start from circles
		if (currentPlayer == 1 && mapRow == 0 && mapCol == 0)
		    return true;
		else if (currentPlayer == 4 && mapRow == 19 && mapCol == 19)
		    return true;
	    }
	    // Centered four player
	    else if (gameMode == 3)
	    {
		// Start from circles closer to center
		if (currentPlayer == 1 && mapRow == 5 && mapCol == 5)
		    return true;
		else if (currentPlayer == 2 && mapRow == 5 && mapCol == 15)
		    return true;
		else if (currentPlayer == 3 && mapRow == 15 && mapCol == 5)
		    return true;
		else if (currentPlayer == 4 && mapRow == 15 && mapCol == 15)
		    return true;
	    }
	    // Centered 2 player
	    else if (gameMode == 4)
	    {
		// Checks to start from circles closer to center
		if (currentPlayer == 1 && mapRow == 5 && mapCol == 5)
		    return true;
		else if (currentPlayer == 4 && mapRow == 15 && mapCol == 15)
		    return true;
	    }
	    // Checks directly to the top left of the piece
	    if (mapRow != 0 && mapCol != 0 && map [mapRow - 1] [mapCol - 1] == currentPlayer)
	    {
		return true;
	    }
	    // Checks directly to the top right of the piece
	    else if (mapRow != 0 && mapCol != 19 && map [mapRow - 1] [mapCol + 1] == currentPlayer)
	    {
		return true;
	    }
	    // Checks directly to the bottom left of the piece
	    else if (mapRow != 19 && mapCol != 0 && map [mapRow + 1] [mapCol - 1] == currentPlayer)
	    {
		return true;
	    }
	    // Checks directly to the bottom right of the piece
	    else if (mapRow != 19 && mapCol != 19 && map [mapRow + 1] [mapCol + 1] == currentPlayer)
	    {
		return true;
	    }
	}
	// Does not connect on corners
	return false;
    }


    /** Checks if pieces are touching by sides
       *@param mapRow          the row the piece will be drawn on
       *@param mapCol          the column the piece will be drawn on
       *@param map             the map the piece will be placed on
       *@param currentPlayer   the player placing the piece
       *@return                whether the piece can be placed there or not based on sides
       */
    private boolean checkSides (int mapRow, int mapCol, int[] [] map, int currentPlayer)
    {
	// Check above piece
	if (mapRow != 0 && map [mapRow - 1] [mapCol] == currentPlayer)
	    return false;
	// Check below piece
	if (mapRow != 19 && map [mapRow + 1] [mapCol] == currentPlayer)
	    return false;
	// Check left of piece
	if (mapCol != 0 && map [mapRow] [mapCol - 1] == currentPlayer)
	    return false;
	// Check right of piece
	if (mapCol != 19 && map [mapRow] [mapCol + 1] == currentPlayer)
	    return false;

	// Not touching on sides
	return true;
    }


    /** Draws each individual block of each piece
      * @param g The graphics to use
      * @param colour The colour in which to draw the piece in
      */
    public void drawPiece (Graphics g, Color colour)
    {
	// Checks the array for a piece
	for (int row = 0 ; row < 5 ; row++)
	{
	    for (int column = 0 ; column < 5 ; column++)
	    {
		if (pieceArea [row] [column] == 1)
		{
		    // Draws the piece 25 by 25, multiplying 25 to not overlap
		    g.setColor (colour);
		    g.fillRect (pieceX + (25 * column), pieceY + (25 * row), 25, 25);
		    g.setColor (Color.BLACK);
		    g.drawRect (pieceX + (25 * column), pieceY + (25 * row), 25, 25);
		}
	    }
	}
    }


    /** Draws the little pieces on the side
      * @param g The graphics to use
      * @param colour The colour in which to draw the piece in
      * @param player The player to draw the piece for
      */
    public void drawLittlePieces (Graphics g, Color colour, int player)
    {
	// Checks the array for a piece
	for (int row = 0 ; row < 5 ; row++)
	{
	    for (int column = 0 ; column < 5 ; column++)
	    {
		// Uses default area to maintain orientation
		if (defaultArea [row] [column] == 1)
		{
		    // Draws the piece 7 by 7 and sets to the side
		    g.setColor (colour);
		    g.fillRect (pieceStartX / 3 + (7 * column) + 530, pieceStartY / 3 + (7 * row) + (108 * player) - 95, 7, 7);
		    g.setColor (Color.BLACK);
		    g.drawRect (pieceStartX / 3 + (7 * column) + 530, pieceStartY / 3 + (7 * row) + (108 * player) - 95, 7, 7);
		}
	    }
	}
    }


    /** Checks whether the rectangle contains a given point
      * @param position  The coordinates of the point
      * @return  Whether the point is within the rectangle
      */
    public boolean contains (Point position)
    {
	// Scans the entire array for a piece
	for (int row = 0 ; row < 5 ; row++)
	{
	    for (int column = 0 ; column < 5 ; column++)
	    {
		// Only occurs if there is a piece there
		if (pieceArea [row] [column] == 1)
		{
		    // Creates a rectangle for each piece of the block
		    Rectangle area = new Rectangle (pieceX + (25 * column), pieceY + (25 * row), 25, 25);
		    // Looks for the position in the rectangle
		    if (area.contains (position))
			return true;
		}
	    }
	}
	// Otherwise return false
	return false;
    }


    /** Translate the piece from initial to final point
      * @param initial  The inital coordinate of the piece
      * @param finalPos  The final coordinate of the piece
      */
    public void translate (Point initial, Point finalPos)
    {
	// Translates the piece by subtracting final by initial points
	pieceX += (finalPos.x - initial.x);
	pieceY += (finalPos.y - initial.y);
    }


    /** Rotates the piece area clockwise
      */
    public void rotateClockwise ()
    {
	// Creates a temporary rotate array
	int[] [] rotatedArray = new int [5] [5];
	//Goes through each row and column looking for a block
	for (int row = 0 ; row < 5 ; row++)
	{
	    for (int col = 0 ; col < 5 ; col++)
		if (pieceArea [row] [col] == 1)
		{
		    // Inverses the y and the x and switches their positions
		    rotatedArray [col] [((-1) * (row - 2)) + 2] = pieceArea [row] [col];
		}
	}

	// Makes the pieceArea now equal to the rotated array
	pieceArea = rotatedArray;
    }


    /** Flips the columns of the piece
      */
    public void flipPieceColumn ()
    {
	// Holds the array of the flipped piece
	flipArray = new int [5] [5];
	// Scans the original array for a block
	for (int row = 0 ; row < 5 ; row++)
	{
	    for (int col = 0 ; col < 5 ; col++)
	    {
		// Only occurs when there is a block there
		if (pieceArea [row] [col] == 1)
		{
		    // Subtracts from 4 to get opposing side
		    flipArray [row] [4 - col] = 1;
		}
	    }
	}
	// Reassigns the flip array back into the original array
	pieceArea = flipArray;
    }


    /** Flips the row of the piece
      */
    public void flipPieceRow ()
    {
	// Holds the array of the flipped piece
	flipArray = new int [5] [5];
	// Scans the original array for a block
	for (int row = 0 ; row < 5 ; row++)
	{
	    for (int col = 0 ; col < 5 ; col++)
	    {
		// Only occurs when there is a block there
		if (pieceArea [row] [col] == 1)
		{
		    // Subtracts from 4 to get opposing side
		    flipArray [4 - row] [col] = 1;
		}
	    }
	}
	// Reassigns the flip array back into the original array
	pieceArea = flipArray;
    }


    /** Finds the appropriate square on the grid for the piece to snap onto
      */
    public void snapToGrid ()
    {
	pieceX = (int) Math.round (pieceX / 25.0) * 25;
	pieceY = (int) Math.round (pieceY / 25.0) * 25;
    }


    /** Returns the X coordinate of the piece array
      * @return  The x coordinate of the top left point of the array
      */
    public int getPieceX ()
    {
	return pieceX;
    }


    /** Returns the y coordinate of the array
      * @return  The y coordinate of the top left of the array
      */
    public int getPieceY ()
    {
	return pieceY;
    }


    /** Returns whether the piece has been placed
      * @return  The boolean containing whether the piece has been placed
      */
    public boolean isPiecePlaced ()
    {
	return isPlaced;
    }


    /** Returns the number of pieces used to make up the block
      * @return  The piece score
      */
    public int getPieceScore ()
    {
	return pieceScore;
    }


    /** Returns the array in which the piece is stored
      * @return  The array where the piece is stored
      */
    public int[] [] getPieceArea ()
    {
	return pieceArea;
    }
}



