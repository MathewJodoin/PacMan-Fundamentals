package movement;

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
import graphics.HorizontalLine;
import graphics.InkyBody;
import graphics.PinkyBody;
import graphics.TopLeftCorner;
import graphics.TopRightCorner;
import graphics.VerticalLine;

public class MoveGhost
{
	static Random rand = new Random();
	
	public static int oldPinkyPosition = 21 * FoodPane.NUM_COLUMNS + 6;
	public static int newPinkyPosition = 21 * FoodPane.NUM_COLUMNS + 6;
	
	public static int oldBlinkyPosition = 21 * FoodPane.NUM_COLUMNS + 22;
	public static int newBlinkyPosition = 21 * FoodPane.NUM_COLUMNS + 22;
	
	public static int oldinkyPosition = 6 * FoodPane.NUM_COLUMNS + 22;
	public static int newinkyPosition = 6 * FoodPane.NUM_COLUMNS + 22;
	
	public static int oldClydePosition = 15 * FoodPane.NUM_COLUMNS + 22;
	public static int newClydePosition = 15 * FoodPane.NUM_COLUMNS + 22;
	
	public static int[] oldGhostPosition = {21 * FoodPane.NUM_COLUMNS + 6, 21 * FoodPane.NUM_COLUMNS + 22, 6 * FoodPane.NUM_COLUMNS + 22, 
	                                        15 * FoodPane.NUM_COLUMNS + 22};
	public static int[] newGhostPosition = {21 * FoodPane.NUM_COLUMNS + 6, 21 * FoodPane.NUM_COLUMNS + 22, 6 * FoodPane.NUM_COLUMNS + 22, 
            								15 * FoodPane.NUM_COLUMNS + 22};
	public static String[] ghostNames = {"pinky", "blinky", "inky", "clyde"};
	
	static int x = 0;
	static int pacXPos = 0;
	static int pacYPos = 0;
	static int ghostXPos = 0;
	static int ghostYPos = 0;
	static int arrayPosition = 0;
	
	public static void MoveGhost(int pacmanPosition, String ghostName)
	{
		if(Math.random() < 0.5)
		{
			x = 0;//left and right
		}
		else
		{
			x = 1;//up and down
		}
		// Verified random 0 or 1 being generated 2/9/18
		//System.out.println("Random Value: " + x);
		pacXPos = MovePlayer.rowArray[pacmanPosition];
		pacYPos = MovePlayer.columnArray[pacmanPosition];

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
				newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] - 1;
				if(IsThereAWall(newGhostPosition[arrayPosition], "left"))
				{
					newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] + 1;
				}
				else
				{
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
		else if(x == 0 && pacYPos > ghostYPos) // right
		{
				newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] + 1;
				if(IsThereAWall(newGhostPosition[arrayPosition], "right"))
				{
					newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] - 1;
				}
				else
				{
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
		
		if(x == 1 && pacXPos < ghostXPos) // up
		{
				newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] - FoodPane.NUM_COLUMNS;
				if(IsThereAWall(newGhostPosition[arrayPosition], "up"))
				{
					newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] + FoodPane.NUM_COLUMNS;
				}
				else
				{
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
		else if(x == 1 && pacXPos > ghostXPos) // down
		{
			
				newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] + FoodPane.NUM_COLUMNS;
				if(IsThereAWall(newGhostPosition[arrayPosition], "down"))
				{
					newGhostPosition[arrayPosition] = newGhostPosition[arrayPosition] - FoodPane.NUM_COLUMNS;
				}
				else
				{
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
			if(newPosition >= 0 && newPosition <= 729)
			{
				x = MovePlayer.rowArray[newPosition];
				y = MovePlayer.columnArray[newPosition];
			}
		}
		else if(direction.equals("down"))
		{
			newPosition = oldPosition + FoodPane.NUM_COLUMNS;
			if(newPosition >= 0 && newPosition <= 729)
			{
				x = MovePlayer.rowArray[newPosition];
				y = MovePlayer.columnArray[newPosition];
			}
		}
		else if(direction.equals("left"))
		{
			newPosition = oldPosition - 1;
			if(newPosition >= 0 && newPosition <= 729)
			{
				x = MovePlayer.rowArray[newPosition];
				y = MovePlayer.columnArray[newPosition];
			}
		}
		else if(direction.equals("right"))
		{
			newPosition = oldPosition + 1;
			if(newPosition >= 0 && newPosition <= 729)
			{
				x = MovePlayer.rowArray[newPosition];
				y = MovePlayer.columnArray[newPosition];
			}
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
