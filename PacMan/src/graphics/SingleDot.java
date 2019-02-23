package graphics;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import gameBoard.FoodPane;

public class SingleDot extends JPanel
{
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		setBackground(Color.BLACK);
		gr.setColor(Color.WHITE);
		gr.fillArc(FoodPane.PANEL_WIDTH / 2, FoodPane.PANEL_HEIGHT / 2, 10, 10, 0, 360);
	}
	
}
