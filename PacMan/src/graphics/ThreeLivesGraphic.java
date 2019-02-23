package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import gameBoard.FoodPane;

public class ThreeLivesGraphic extends JPanel
{
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		setBackground(Color.BLACK);
		gr.setColor(Color.RED);
		gr.fillArc(300, 600, 10, 10, 0, 360);
	}
}
