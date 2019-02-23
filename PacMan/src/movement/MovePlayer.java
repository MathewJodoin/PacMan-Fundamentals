package movement;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import gameBoard.FoodPane;
import gameBoard.ScoreArray;
import gameBoard.TestLayout;
import graphics.BlankPanel;
import graphics.BottomLeftCorner;
import graphics.BottomRightCorner;
import graphics.GameOver;
import graphics.HorizontalLine;
import graphics.PacmanBody;
import graphics.PacmanBodyDown;
import graphics.PacmanLeftBody;
import graphics.PacmanUpBody;
import graphics.SingleDot;
import graphics.TopLeftCorner;
import graphics.TopRightCorner;
import graphics.VerticalLine;



public class MovePlayer
{
	static int[] rowArray = new int[FoodPane.NUM_COLUMNS * FoodPane.NUM_ROWS];
	static int[] columnArray = new int[FoodPane.NUM_COLUMNS * FoodPane.NUM_ROWS];
	
	public static int newPosition = FoodPane.NUM_COLUMNS + 1;
	public static int oldPosition = FoodPane.NUM_COLUMNS + 1;

	//JPanels with gameboard graphics are inserted into a JPanel using a numbering system from 0 to 868.
	//Unfortunately this doesn't give any insight into the x vs. y positioning of pacman vs. the ghosts.  Need this to know which way to move.
	//To get this we make 2 arrays, 1 of just row numbers and the other of just column numbers.  This can then be used to with the the number between 0 and 868
	//to get the x-y position for example.  [21][12].  You can now compare x values and y values of ghosts and pacman to determine which direction ghosts must move.
	public static void setupParallelArrays()
	{
		int k = 0;
		for(int i = 0; i < FoodPane.NUM_ROWS; ++i)
		{
			for(int j = 0; j < FoodPane.NUM_COLUMNS; ++j)
			{
				columnArray[k] = j;
				k++;
			}
		}
		k = 0;
		for(int i = 0; i < FoodPane.NUM_ROWS; ++i)
		{
			for(int j = 0; j < FoodPane.NUM_COLUMNS; ++j)
			{
				rowArray[k] = i;
				k++;
			}
		}
	}
	
	//This method takes the old position and a key event corresponding to a direction
	//Moving up requires subtracting a full row from your current position
	//Moving down requires adding a full row, moving left you subtract 1,
	//moving right you add 1.
	public static void moveCharacter(int oldPosition1, KeyEvent e)
	{
		oldPosition = oldPosition1;
		System.out.println(oldPosition);
			if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				newPosition = newPosition - FoodPane.NUM_COLUMNS;
				ScoreArray.updateScoreArray(oldPosition);
				TestLayout.panel.remove(oldPosition);
				TestLayout.panel.add(movement.MovePlayer.whatToDraw(oldPosition), oldPosition);
				TestLayout.panel.remove(newPosition);
				TestLayout.panel.add(new PacmanUpBody(), newPosition);
				TestLayout.panel.invalidate();
				TestLayout.panel.revalidate();
				TestLayout.panel.repaint();
				oldPosition = newPosition;
				TestLayout.score.setText("Score: " + Integer.toString(ScoreArray.scoreArraySum));
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				newPosition = newPosition + FoodPane.NUM_COLUMNS;
				ScoreArray.updateScoreArray(oldPosition);
				TestLayout.panel.remove(oldPosition);
				TestLayout.panel.add(movement.MovePlayer.whatToDraw(oldPosition), oldPosition);
				TestLayout.panel.remove(newPosition);
				TestLayout.panel.add(new PacmanBodyDown(), newPosition);
				TestLayout.panel.invalidate();
				TestLayout.panel.revalidate();
				TestLayout.panel.repaint();
				oldPosition = newPosition;
				TestLayout.score.setText("Score: " + Integer.toString(ScoreArray.scoreArraySum));
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				//Checks to see if Pacman is at 1 of 2 spots on board where he appears on
				// other side.
				if(oldPosition == 393)
				{
					newPosition = newPosition + 26;
					ScoreArray.updateScoreArray(oldPosition);
					TestLayout.panel.remove(oldPosition);
					TestLayout.panel.add(movement.MovePlayer.whatToDraw(oldPosition), oldPosition);
					TestLayout.panel.remove(newPosition);
					TestLayout.panel.add(new PacmanLeftBody(), newPosition);
					TestLayout.panel.invalidate();
					TestLayout.panel.revalidate();
					TestLayout.panel.repaint();
					oldPosition = newPosition;
					TestLayout.score.setText("Score: " + Integer.toString(ScoreArray.scoreArraySum));
				}
				else
				{
				newPosition = newPosition - 1;
				ScoreArray.updateScoreArray(oldPosition);
				TestLayout.panel.remove(oldPosition);
				TestLayout.panel.add(movement.MovePlayer.whatToDraw(oldPosition), oldPosition);
				TestLayout.panel.remove(newPosition);
				TestLayout.panel.add(new PacmanLeftBody(), newPosition);
				TestLayout.panel.invalidate();
				TestLayout.panel.revalidate();
				TestLayout.panel.repaint();
				oldPosition = newPosition;
				TestLayout.score.setText("Score: " + Integer.toString(ScoreArray.scoreArraySum));
				}
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				if(oldPosition == 418)
				{
					newPosition = newPosition - 26;
					ScoreArray.updateScoreArray(oldPosition);
					TestLayout.panel.remove(oldPosition);
					TestLayout.panel.add(movement.MovePlayer.whatToDraw(oldPosition), oldPosition);
					TestLayout.panel.remove(newPosition);
					TestLayout.panel.add(new PacmanLeftBody(), newPosition);
					TestLayout.panel.invalidate();
					TestLayout.panel.revalidate();
					TestLayout.panel.repaint();
					oldPosition = newPosition;
					TestLayout.score.setText("Score: " + Integer.toString(ScoreArray.scoreArraySum));
				}
				else
				{
				newPosition = newPosition + 1;
				ScoreArray.updateScoreArray(oldPosition);
				TestLayout.panel.remove(oldPosition);
				TestLayout.panel.add(movement.MovePlayer.whatToDraw(oldPosition), oldPosition);
				TestLayout.panel.remove(newPosition);
				TestLayout.panel.add(new PacmanBody(), newPosition);
				TestLayout.panel.invalidate();
				TestLayout.panel.revalidate();
				TestLayout.panel.repaint();
				oldPosition = newPosition;
				TestLayout.score.setText("Score: " + Integer.toString(ScoreArray.scoreArraySum));
				}
			}
		}
	
	// This method is shared by both pacman and ghost movement.  When pacman moves through a square, it must replace with a blank panel.
	// When a ghost moves through a square it replaces it with a blank panel if pacman has already been there, or a powerpellet if pacman hasn't.
	public static Component whatToDraw(int oldPosition)
	{
		//Score array tells us whether pacman has already visited that space.
		if(ScoreArray.scoreArray[oldPosition] == 1)
		{
			return new BlankPanel();
		}
		else
		{
			return new SingleDot();
		}
	}

	//Neither ghosts or pacman can move through walls.  This method takes the current position and the direction the character wishes to move.
	//It checks the direction they are moving for a wall and returns true if there is one, false if clear.
	public static boolean IsThereAWall(int oldPosition1, KeyEvent e)
	{
		int x = 0;
		int y = 0;
		int newPosition1 = 0;
		//Following converts absolute position to [x][y] position to search the 2D array of the gameboard.
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			newPosition1 = oldPosition1 - FoodPane.NUM_COLUMNS;
			x = rowArray[newPosition1];
			y = columnArray[newPosition1];
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			newPosition1 = oldPosition1 + FoodPane.NUM_COLUMNS;
			x = rowArray[newPosition1];
			y = columnArray[newPosition1];
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			newPosition1 = oldPosition1 - 1;
			x = rowArray[newPosition1];
			y = columnArray[newPosition1];
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			newPosition1 = oldPosition1 + 1;
			x = rowArray[newPosition1];
			y = columnArray[newPosition1];
		}
		//Checks the new position for a wall
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
