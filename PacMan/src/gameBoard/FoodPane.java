package gameBoard;

import java.awt.*;
import javax.swing.*;

import graphics.BlankPanel;
import graphics.BlinkyBody;
import graphics.ClydeBody;
import graphics.HorizontalLine;
import graphics.InkyBody;
import graphics.PacmanBody;
import graphics.PinkyBody;
import graphics.SingleDot;
import graphics.TopLeftCorner;
import graphics.TopRightCorner;
import graphics.VerticalLine;
import graphics.BottomLeftCorner;
import graphics.BottomRightCorner;

// This method sets up the board.  Each powerpellet and wall is individually addressed below then assembled into the gameboard.
public class FoodPane extends JPanel
{
	static int FRAME_WIDTH = TestLayout.FRAME_WIDTH;
	static int FRAME_HEIGHT = TestLayout.FRAME_HEIGHT;
	public static int NUM_COLUMNS = 28;
	public static int NUM_ROWS = 31;
	//Uses width of the JFrame to determine the size of the panels being created.  If you change the size of the JFrame, all panels should scale to fit new window.
	public static int PANEL_WIDTH = FRAME_WIDTH / NUM_COLUMNS;
	public static int PANEL_HEIGHT = FRAME_HEIGHT / NUM_ROWS;
	public static JPanel[][] panelArray = new JPanel[NUM_ROWS] [NUM_COLUMNS];
//2,20
	public static int[] topLeftCornerRow = {0, 0, 2, 2, 2, 2, 6, 6, 6, 6, 6, 7, 9, 9, 10, 12, 15, 15, 15, 18, 
			19, 19, 21, 21, 21, 21, 22, 24, 24, 24, 24, 25, 25, 27, 27};
	public static int[] topLeftCornerColumn = {0, 14, 2, 7, 16, 22, 2, 7, 10, 19, 22, 14, 16, 22, 8, 10, 7, 19, 22, 10, 
			0, 14, 2, 7, 16, 22, 23, 7, 10, 19, 25, 0, 14, 2, 16};
	public static int[] topRightCornerRow = {0, 0, 2, 2, 2, 2, 6, 6, 6, 6, 6, 7, 9, 9, 10, 12, 15, 15, 15, 
			18, 19, 19, 21, 21, 21, 21, 22, 24, 24, 24, 24, 25, 25, 27, 27};
	public static int[] topRightCornerColumn = {13, 27, 5, 11, 20, 25, 5, 8, 17, 20, 25, 13, 5, 11, 19, 17, 5, 8, 20, 
			17, 13, 27, 5, 11, 20, 25, 4, 2, 8, 17, 20, 13, 27, 11, 25};
	public static int[] bottomRightCornerRow = {4, 4, 4, 4, 4, 7, 7, 7, 9, 9, 10, 10, 13, 13, 13, 
			16, 19, 19, 19, 19, 22, 22, 22, 22, 24, 25, 25, 25, 25, 27, 27, 28, 28, 28, 30};
	public static int[] bottomRightCornerColumn = {5, 11, 14, 20, 25, 5, 17, 25, NUM_COLUMNS - 1, 19, 11, 14, 5, 8, 20, 
			17, 5, 8, 17, 20, 11, 14, 20, 25, 27, 2, 5, 17, 23, 7, 19, 11, 14, 25, NUM_COLUMNS - 1};
	public static int[] bottomLeftCornerRow = {4, 4, 4, 4, 4, 7, 7, 7, 9, 9, 10, 10, 13, 13, 13, 16, 19, 19, 19, 19, 22, 22, 22, 22, 
			24, 25, 25, 25, 25, 27, 27, 28, 28, 28, NUM_ROWS - 1};
	public static int[] bottomLeftCornerColumn = {2, 7, 13, 16, 22, 2, 10, 22, 0, 8, 16, 13, 7, 19, 22, 10, 7, 10, 19, 22, 2, 7, 13, 16,
			0, 4, 10, 22, 25, 8, 20, 2, 13, 16, 0};
	public static int[] horizontalLineRow = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 
			7, 7, 7, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, //30
			13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, //26
			18, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, //29
			22, 22, 22, 22, 22, 22, 22, 22, 24, 24, 24, 24, 24, 24, 24, 24, 25, 25, 25, 25, 25, 25,
			27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 
			30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
	public static int[] horizontalLineColumn = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 
			3, 4, 8, 9, 10, 17, 18, 19, 23, 24, 3, 4, 8, 9, 10, 17, 18, 19, 23, 24, 3, 4, 11, 12, 13, 14, 15, 16, 23, 24, 
			3, 4, 11, 12, 15, 16, 23, 24, 1, 2, 3, 4, 9, 10, 17, 18, 23, 24, 25, 26, 9, 10, 17, 18, 11, 12, 13, 14, 15, 16, //30
			0, 1, 2, 3, 4, 23, 24, 25, 26, 27, 0, 1, 2, 3, 4, 23, 24, 25, 26, 27, 11, 12, 13, 14, 15, 16, //26
			11, 12, 13, 14, 15, 16, 1, 2, 3, 4, 11, 12, 15, 16, 23, 24, 25, 26, 3, 4, 8, 9, 10, 17, 18, 19, 23, 24, //29
			3, 8, 9, 10, 17, 18, 19, 24, 1, 11, 12, 13, 14, 15, 16, 26, 1, 11, 12, 15, 16, 26,
			3, 4, 5, 6, 9, 10, 17, 18, 21, 22, 23, 24, 3, 4, 5, 6, 7, 8, 9, 10, 17, 18, 19, 20, 21, 22, 23, 24, 
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
	public static int[] verticalLineRow = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 5, 5, 6, 6, 
			7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11,
			12, 12, 12, 12, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 17, 
			18, 18, 18, 18, 18, 18, 20, 20, 20, 20, 21, 21, 21, 21, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24,
			25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 26, 26, 27, 27, 27, 27, 28, 28, 29, 29};
	public static int[] verticalLineColumn = {0, 13, 14, 27, 0, 13, 14, 27, 0, 2, 5, 7, 11, 13, 14, 16, 20, 22, 25, 27, 0, 27, 0, 27, 0, 27, 
			0, 7, 8, 19, 20, 27, 0, 7, 8, 13, 14, 19, 20, 27, 7, 13, 14, 20, 5, 7, 20, 22, 5, 7, 8, 19, 20, 22, 5,
			5, 7, 8, 19, 20, 22, 10, 17, 10, 17, 10, 17, 5, 7, 8, 19, 20, 22, 5, 7, 8, 19, 20, 22, 
			5, 7, 8, 19, 20, 22, 0, 13, 14, 27, 0, 13, 14, 27, 0, 22, 27, 5, 0, 4, 5, 22, 23, 27, 4, 5, 22, 23, 
			7, 8, 19, 20, 0, 7, 8, 13, 14, 19, 20, 27, 0, 13, 14, 27, 0, 27, 0, 27};
	
	FoodPane()
	{
		setLayout(new GridLayout(NUM_ROWS, NUM_COLUMNS));
		setSize(NUM_COLUMNS, NUM_ROWS);
		setVisible(true);
		
		for(int i = 0; i < topLeftCornerRow.length; ++i)
		{
			panelArray[topLeftCornerRow[i]][topLeftCornerColumn[i]] = new TopLeftCorner();
		}
		for(int i = 0; i < bottomRightCornerRow.length; ++i)
		{
			panelArray[bottomRightCornerRow[i]][bottomRightCornerColumn[i]] = new BottomRightCorner();
		}
		for(int i = 0; i < bottomLeftCornerRow.length; ++i)
		{
			panelArray[bottomLeftCornerRow[i]][bottomLeftCornerColumn[i]] = new BottomLeftCorner();
		}
		for(int i = 0; i < topRightCornerRow.length; ++i)
		{
			panelArray[topRightCornerRow[i]][topRightCornerColumn[i]] = new TopRightCorner();
		}
		for(int i = 0; i < horizontalLineRow.length; ++i)
		{
			panelArray[horizontalLineRow[i]][horizontalLineColumn[i]] = new HorizontalLine();
		}
		for(int i = 0; i < verticalLineRow.length; ++i)
		{
			panelArray[verticalLineRow[i]][verticalLineColumn[i]] = new VerticalLine();
		}
		
		// Runs through the entire JPanel and inserts a powerpellet if there is no wall.
		for(int j = 0; j < NUM_ROWS; j++)
		{
			for(int k = 0; k < NUM_COLUMNS; k++)
			{
//				if(panelArray[j][k] == null)
//				{
//					panelArray[j][k] = new SingleDot();
//				}
				
				if(FoodPane.panelArray[j][k] instanceof BottomLeftCorner || FoodPane.panelArray[j][k] instanceof BottomRightCorner ||
						FoodPane.panelArray[j][k] instanceof TopLeftCorner || FoodPane.panelArray[j][k] instanceof TopRightCorner || 
						FoodPane.panelArray[j][k] instanceof VerticalLine  || FoodPane.panelArray[j][k] instanceof HorizontalLine)
				{
					
				}
				else
				{
					panelArray[j][k] = new SingleDot();
				}
			}
		}
		
		for(int j = 0; j < NUM_ROWS; j++)
		{
			for(int k = 0; k < NUM_COLUMNS; k++)
			{
				add(panelArray[j][k]);
			}
		}
	}
}
