package movement;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import gameBoard.FoodPane;
import gameBoard.TestLayout;
import graphics.BlankPanel;
import graphics.BlinkyBody;
import graphics.BottomLeftCorner;
import graphics.BottomRightCorner;
import graphics.ClydeBody;
import graphics.GameOver;
import graphics.HorizontalLine;
import graphics.InkyBody;
import graphics.PinkyBody;
import graphics.TopLeftCorner;
import graphics.TopRightCorner;
import graphics.VerticalLine;

public class MoveGhost2
{
	static Random rand = new Random();
	
	//Starting positions for all of the ghosts.  Can be any position as long as it's not a wall
	public static int oldPinkyPosition = 20 * FoodPane.NUM_COLUMNS + 20;
	public static int newPinkyPosition = 20 * FoodPane.NUM_COLUMNS + 20;
	
	public static int oldBlinkyPosition = 2 * FoodPane.NUM_COLUMNS + 20;
	public static int newBlinkyPosition = 2 * FoodPane.NUM_COLUMNS + 20;
	
	public static int oldinkyPosition = 20 * FoodPane.NUM_COLUMNS + 2;
	public static int newinkyPosition = 20 * FoodPane.NUM_COLUMNS + 2;
	
	public static int oldClydePosition = 29 * FoodPane.NUM_COLUMNS + 14;
	public static int newClydePosition = 29 * FoodPane.NUM_COLUMNS + 14;
	
	public static int[] oldGhostPosition = {20 * FoodPane.NUM_COLUMNS + 20, 2 * FoodPane.NUM_COLUMNS + 20, 20 * FoodPane.NUM_COLUMNS + 2, 
	                                        29 * FoodPane.NUM_COLUMNS + 14};
	public static int[] newGhostPosition = {20 * FoodPane.NUM_COLUMNS + 20, 2 * FoodPane.NUM_COLUMNS + 20, 20 * FoodPane.NUM_COLUMNS + 2, 
            								29 * FoodPane.NUM_COLUMNS + 14};
	public static String[] ghostNames = {"pinky", "blinky", "inky", "clyde"};
	
	static int x = 0;
	static int pacXPos = 0;
	static int pacYPos = 0;
	static int ghostXPos = 0;
	static int ghostYPos = 0;
	public static int arrayPosition = 0;
	
	public static void MoveGhost(int pacmanPosition, String ghostName)
	{
		//Using a randomly generated 1 or 0 to determine which direction the ghosts move toward pacman.  Can be problematic when ghosts are close
		// The randomness means the ghosts may go in the wrong direction.  The farther away the ghosts get the less noticable this is.
		// Can correct in future updates.
		if(Math.random() < 0.5)
		{
			x = 0;//left and right
		}
		else
		{
			x = 1;//up and down
		}
		// Verified random 0 or 1 being generated 2/9/18

		pacXPos = MovePlayer.rowArray[pacmanPosition];
		pacYPos = MovePlayer.columnArray[pacmanPosition];
		
		//Run through list of ghost names to match up to the current ghost being moved.  
		//Using parallel arrays to keep track of which position corresponds to which ghost
		for(int i = 0; i < ghostNames.length; ++i)
		{
			if(ghostNames[i].equals(ghostName))
			{
				arrayPosition = i;
			}
		}
		ghostXPos = MovePlayer.rowArray[oldGhostPosition[arrayPosition]];
		ghostYPos = MovePlayer.columnArray[oldGhostPosition[arrayPosition]];
		
		if(x == 0 && pacYPos < ghostYPos) // Moving Ghost left
		{
				while(IsThereAWall(newGhostPosition[arrayPosition], "left")) //Checks if there is a wall in the direction we want to move.
				{
					if(IsThereAWall(newGhostPosition[arrayPosition], "up") == false)//move ghost up until it can move left
					{
						newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] - FoodPane.NUM_COLUMNS;
						TestLayout.panel.remove(oldGhostPosition[arrayPosition]);
						TestLayout.panel.add(MovePlayer.whatToDraw(oldGhostPosition[arrayPosition]), oldGhostPosition[arrayPosition]);
						TestLayout.panel.remove(newGhostPosition[arrayPosition]);
						TestLayout.panel.add(whichGhost(arrayPosition), newGhostPosition[arrayPosition]);
						TestLayout.panel.invalidate();
						TestLayout.panel.revalidate();
						TestLayout.panel.repaint();
						oldGhostPosition[arrayPosition] = newGhostPosition[arrayPosition];
					}
					else
					{
						while(IsThereAWall(newGhostPosition[arrayPosition], "left")) //move ghost down until it can move left
						{
							newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] + FoodPane.NUM_COLUMNS;
							TestLayout.panel.remove(oldGhostPosition[arrayPosition]);
							TestLayout.panel.add(MovePlayer.whatToDraw(oldGhostPosition[arrayPosition]), oldGhostPosition[arrayPosition]);
							TestLayout.panel.remove(newGhostPosition[arrayPosition]);
							TestLayout.panel.add(whichGhost(arrayPosition), newGhostPosition[arrayPosition]);
							TestLayout.panel.invalidate();
							TestLayout.panel.revalidate();
							TestLayout.panel.repaint();
							oldGhostPosition[arrayPosition] = newGhostPosition[arrayPosition];
						}
					}
				}
				//finally move ghost left
				newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] - 1;
				TestLayout.panel.remove(oldGhostPosition[arrayPosition]);
				TestLayout.panel.add(MovePlayer.whatToDraw(oldGhostPosition[arrayPosition]), oldGhostPosition[arrayPosition]);
				TestLayout.panel.remove(newGhostPosition[arrayPosition]);
				TestLayout.panel.add(whichGhost(arrayPosition), newGhostPosition[arrayPosition]);
				TestLayout.panel.invalidate();
				TestLayout.panel.revalidate();
				TestLayout.panel.repaint();
				oldGhostPosition[arrayPosition] = newGhostPosition[arrayPosition];
		}
		else if(x == 0 && pacYPos > ghostYPos) //Moving Ghost Right
		{
			while(IsThereAWall(newGhostPosition[arrayPosition], "right"))
			{
				if(IsThereAWall(newGhostPosition[arrayPosition], "up") == false)//move ghost up until it can move right
				{
					newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] - FoodPane.NUM_COLUMNS;
					TestLayout.panel.remove(oldGhostPosition[arrayPosition]);
					TestLayout.panel.add(MovePlayer.whatToDraw(oldGhostPosition[arrayPosition]), oldGhostPosition[arrayPosition]);
					TestLayout.panel.remove(newGhostPosition[arrayPosition]);
					TestLayout.panel.add(whichGhost(arrayPosition), newGhostPosition[arrayPosition]);
					TestLayout.panel.invalidate();
					TestLayout.panel.revalidate();
					TestLayout.panel.repaint();
					oldGhostPosition[arrayPosition] = newGhostPosition[arrayPosition];
				}
				else
				{
					while(IsThereAWall(newGhostPosition[arrayPosition], "right")) //move ghost down until it can move right
					{
						newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] + FoodPane.NUM_COLUMNS;
						TestLayout.panel.remove(oldGhostPosition[arrayPosition]);
						TestLayout.panel.add(MovePlayer.whatToDraw(oldGhostPosition[arrayPosition]), oldGhostPosition[arrayPosition]);
						TestLayout.panel.remove(newGhostPosition[arrayPosition]);
						TestLayout.panel.add(whichGhost(arrayPosition), newGhostPosition[arrayPosition]);
						TestLayout.panel.invalidate();
						TestLayout.panel.revalidate();
						TestLayout.panel.repaint();
						oldGhostPosition[arrayPosition] = newGhostPosition[arrayPosition];
					}
				}
			}
			// finally move ghost right.  
			newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] + 1;
			TestLayout.panel.remove(oldGhostPosition[arrayPosition]);
			TestLayout.panel.add(MovePlayer.whatToDraw(oldGhostPosition[arrayPosition]), oldGhostPosition[arrayPosition]);
			TestLayout.panel.remove(newGhostPosition[arrayPosition]);
			TestLayout.panel.add(whichGhost(arrayPosition), newGhostPosition[arrayPosition]);
			TestLayout.panel.invalidate();
			TestLayout.panel.revalidate();
			TestLayout.panel.repaint();
			oldGhostPosition[arrayPosition] = newGhostPosition[arrayPosition];
		}
		
		else if(x == 1 && pacXPos < ghostXPos) // up
		{
			while(IsThereAWall(newGhostPosition[arrayPosition], "up"))
			{
				if(IsThereAWall(newGhostPosition[arrayPosition], "left") == false)//move ghost left until it can move up
				{
					newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] - 1;
					TestLayout.panel.remove(oldGhostPosition[arrayPosition]);
					TestLayout.panel.add(MovePlayer.whatToDraw(oldGhostPosition[arrayPosition]), oldGhostPosition[arrayPosition]);
					TestLayout.panel.remove(newGhostPosition[arrayPosition]);
					TestLayout.panel.add(whichGhost(arrayPosition), newGhostPosition[arrayPosition]);
					TestLayout.panel.invalidate();
					TestLayout.panel.revalidate();
					TestLayout.panel.repaint();
					oldGhostPosition[arrayPosition] = newGhostPosition[arrayPosition];
				}
				else
				{
					while(IsThereAWall(newGhostPosition[arrayPosition], "up")) //move ghost right until it can move up
					{
						newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] + 1;
						TestLayout.panel.remove(oldGhostPosition[arrayPosition]);
						TestLayout.panel.add(MovePlayer.whatToDraw(oldGhostPosition[arrayPosition]), oldGhostPosition[arrayPosition]);
						TestLayout.panel.remove(newGhostPosition[arrayPosition]);
						TestLayout.panel.add(whichGhost(arrayPosition), newGhostPosition[arrayPosition]);
						TestLayout.panel.invalidate();
						TestLayout.panel.revalidate();
						TestLayout.panel.repaint();
						oldGhostPosition[arrayPosition] = newGhostPosition[arrayPosition];
					}
				}
			}
			// finally move ghost up.  
			newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] - FoodPane.NUM_COLUMNS;
			TestLayout.panel.remove(oldGhostPosition[arrayPosition]);
			TestLayout.panel.add(MovePlayer.whatToDraw(oldGhostPosition[arrayPosition]), oldGhostPosition[arrayPosition]);
			TestLayout.panel.remove(newGhostPosition[arrayPosition]);
			TestLayout.panel.add(whichGhost(arrayPosition), newGhostPosition[arrayPosition]);
			TestLayout.panel.invalidate();
			TestLayout.panel.revalidate();
			TestLayout.panel.repaint();
			oldGhostPosition[arrayPosition] = newGhostPosition[arrayPosition];
		}
		else if(x == 1 && pacXPos > ghostXPos) // down
		{
			while(IsThereAWall(newGhostPosition[arrayPosition], "down"))
			{
				if(IsThereAWall(newGhostPosition[arrayPosition], "left") == false)//move ghost left until it can move down
				{
					newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] - 1;
					TestLayout.panel.remove(oldGhostPosition[arrayPosition]);
					TestLayout.panel.add(MovePlayer.whatToDraw(oldGhostPosition[arrayPosition]), oldGhostPosition[arrayPosition]);
					TestLayout.panel.remove(newGhostPosition[arrayPosition]);
					TestLayout.panel.add(whichGhost(arrayPosition), newGhostPosition[arrayPosition]);
					TestLayout.panel.invalidate();
					TestLayout.panel.revalidate();
					TestLayout.panel.repaint();
					oldGhostPosition[arrayPosition] = newGhostPosition[arrayPosition];
				}
				else
				{
					while(IsThereAWall(newGhostPosition[arrayPosition], "down")) //move ghost right until it can move down
					{
						newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] + 1;
						TestLayout.panel.remove(oldGhostPosition[arrayPosition]);
						TestLayout.panel.add(MovePlayer.whatToDraw(oldGhostPosition[arrayPosition]), oldGhostPosition[arrayPosition]);
						TestLayout.panel.remove(newGhostPosition[arrayPosition]);
						TestLayout.panel.add(whichGhost(arrayPosition), newGhostPosition[arrayPosition]);
						TestLayout.panel.invalidate();
						TestLayout.panel.revalidate();
						TestLayout.panel.repaint();
						oldGhostPosition[arrayPosition] = newGhostPosition[arrayPosition];
					}
				}
			}
			// finally move ghost down.  
			newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] + FoodPane.NUM_COLUMNS;
			TestLayout.panel.remove(oldGhostPosition[arrayPosition]);
			TestLayout.panel.add(MovePlayer.whatToDraw(oldGhostPosition[arrayPosition]), oldGhostPosition[arrayPosition]);
			TestLayout.panel.remove(newGhostPosition[arrayPosition]);
			TestLayout.panel.add(whichGhost(arrayPosition), newGhostPosition[arrayPosition]);
			TestLayout.panel.invalidate();
			TestLayout.panel.revalidate();
			TestLayout.panel.repaint();
			oldGhostPosition[arrayPosition] = newGhostPosition[arrayPosition];
		}

	}
	public static JPanel whichGhost(int arrayPosition)
	{
		if(arrayPosition == 0)
		{
			return new PinkyBody();
		}
		else if(arrayPosition == 1)
		{
			return new BlinkyBody();
		}
		else if(arrayPosition == 2)
		{
			return new InkyBody();
		}
		else if(arrayPosition == 3)
		{
			return new ClydeBody();
		}
		return null;
	}
	public static boolean IsThereAWall(int oldPosition, String direction)
	{
		int x = 0;
		int y = 0;
		int newPosition = 0;
		if(direction.equals("up"))
		{
			newPosition = oldPosition - FoodPane.NUM_COLUMNS;
				x = MovePlayer.rowArray[newPosition];
				y = MovePlayer.columnArray[newPosition];
		}
		else if(direction.equals("down"))
		{
			newPosition = oldPosition + FoodPane.NUM_COLUMNS;
				x = MovePlayer.rowArray[newPosition];
				y = MovePlayer.columnArray[newPosition];
		}
		else if(direction.equals("left"))
		{
			newPosition = oldPosition - 1;
				x = MovePlayer.rowArray[newPosition];
				y = MovePlayer.columnArray[newPosition];
		}
		else if(direction.equals("right"))
		{
			newPosition = oldPosition + 1;
				x = MovePlayer.rowArray[newPosition];
				y = MovePlayer.columnArray[newPosition];
		}
		if(FoodPane.panelArray[x][y] instanceof BottomLeftCorner || FoodPane.panelArray[x][y] instanceof BottomRightCorner ||
				FoodPane.panelArray[x][y] instanceof TopLeftCorner || FoodPane.panelArray[x][y] instanceof TopRightCorner || 
				FoodPane.panelArray[x][y] instanceof VerticalLine  || FoodPane.panelArray[x][y] instanceof HorizontalLine)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
