package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import gameBoard.FoodPane;

public class InkyBody extends JPanel
{
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		setBackground(Color.BLACK);
		gr.setColor(Color.cyan);
		gr.fillArc(0, 0, FoodPane.PANEL_WIDTH - 5, FoodPane.PANEL_HEIGHT - 5, 0, 360);
		gr.setColor(Color.white);
		gr.fillOval(FoodPane.PANEL_HEIGHT/4, FoodPane.PANEL_WIDTH/4, 10, 10);
		gr.setColor(Color.BLUE);
		gr.fillOval(FoodPane.PANEL_HEIGHT/4, FoodPane.PANEL_WIDTH/4, 5, 5);
		gr.setColor(Color.WHITE);
		gr.fillOval(FoodPane.PANEL_HEIGHT/4 + 10, FoodPane.PANEL_WIDTH/4, 10, 10);
		gr.setColor(Color.blue);
		gr.fillOval(FoodPane.PANEL_HEIGHT/4 + 10, FoodPane.PANEL_WIDTH/4, 5, 5);
	}
}
