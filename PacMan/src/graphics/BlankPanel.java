package graphics;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BlankPanel extends JPanel
{
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		setBackground(Color.BLACK);
	}
}
