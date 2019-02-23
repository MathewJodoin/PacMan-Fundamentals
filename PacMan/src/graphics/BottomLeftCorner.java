package graphics;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import gameBoard.FoodPane;



public class BottomLeftCorner extends JPanel
{
	boolean canPacMoveHere = false;
	
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		Graphics2D g2d = (Graphics2D) gr.create();
		setBackground(Color.BLACK);
		g2d.setColor(Color.blue);
		g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.drawLine(FoodPane.PANEL_WIDTH / 2, FoodPane.PANEL_HEIGHT / 2, FoodPane.PANEL_WIDTH, FoodPane.PANEL_HEIGHT / 2);
		g2d.drawLine(FoodPane.PANEL_WIDTH / 2, FoodPane.PANEL_HEIGHT / 2, FoodPane.PANEL_WIDTH / 2, 0);
	}
}
