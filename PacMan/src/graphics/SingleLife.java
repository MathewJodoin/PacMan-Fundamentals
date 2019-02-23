package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import gameBoard.FoodPane;

public class SingleLife extends JPanel
{
	public void paintComponent(Graphics gr)
	{
	super.paintComponent(gr);
	setBackground(Color.BLACK);
	gr.setColor(Color.RED);
	gr.fillArc(0, 0, FoodPane.PANEL_WIDTH - 5, FoodPane.PANEL_HEIGHT - 5, 0, 360);
	}
}
