import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.event.*;
import java.net.*;

/** The RubrusMain class - creates the JFrame for the Rubrus Game
  * Plays a superb game of Rubrus using RubrusBoard class
  * @author Tristan Amini, Victor Cong, Chen Chou
  * @version January 23, 2012
  */
public class RubrusMain extends JFrame
{
    // Program variables for the game board
    private RubrusBoard gameBoard;


    /** Constructs a new RubrusMain frame (sets up the Game)
      */
    public RubrusMain ()
    {
	// Sets up the frame for the game
	super ("Rubrus");
	setResizable (false);
	setUndecorated (true);

	// Load up the icon image
	setIconImage (Toolkit.getDefaultToolkit ().getImage ("Square.png"));

	// Sets up the Rubrus board that plays the game
	// and add it to the centre of this frame
	gameBoard = new RubrusBoard ();
	getContentPane ().add (gameBoard, BorderLayout.CENTER);
    } // Constructor


    /** Starts up the RubrusMain frame
      * @param args An array of Strings (ignored)
      */
    public static void main (String[] args)
    {
	// Starts up the RubrusMain frame
	RubrusMain frame = new RubrusMain ();
	frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	frame.pack ();
	frame.setVisible (true);
    } // main method
}


