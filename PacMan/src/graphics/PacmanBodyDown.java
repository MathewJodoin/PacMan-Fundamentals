package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import gameBoard.FoodPane;

public class PacmanBodyDown extends JPanel
{
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		setBackground(Color.BLACK);
		gr.setColor(Color.YELLOW);
		gr.fillArc(0, 0, FoodPane.PANEL_WIDTH, FoodPane.PANEL_HEIGHT - 6, 225, -270);
	}
}
