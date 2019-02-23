package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class YouWin extends JPanel
{
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		setBackground(Color.BLACK);
		gr.setFont(new Font("Arial", Font.BOLD, 36));
		gr.setColor(Color.yellow);
		gr.drawString("You Win", gameBoard.TestLayout.FRAME_WIDTH / 2, gameBoard.TestLayout.FRAME_HEIGHT / 2);

	}
}
