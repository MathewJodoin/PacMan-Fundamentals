package gameBoard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import graphics.BlankPanel;
import graphics.BottomLeftCorner;
import graphics.BottomRightCorner;
import graphics.GameOver;
import graphics.HorizontalLine;
import graphics.PacmanBody;
import graphics.SingleDot;
import graphics.SingleLife;
import graphics.ThreeLivesGraphic;
import graphics.TopLeftCorner;
import graphics.TopRightCorner;
import graphics.VerticalLine;
import graphics.YouWin;
import movement.*;
import graphics.InkyBody;
import graphics.PinkyBody;
import graphics.BlinkyBody;
import graphics.ClydeBody;

public class TestLayout extends JFrame implements KeyListener
{
	public static int FRAME_WIDTH = 1000;
	public static int FRAME_HEIGHT = 1000;
	
	static public FoodPane panel = new FoodPane();
	private static Thread one;
	public JPanel bottom = new JPanel();
	public JPanel top = new JPanel();
	public JButton reset = new JButton("RESET GAME");
	public Container con = getContentPane();
	public static JLabel score = new JLabel("Score: ");
	public JLabel lives = new JLabel("Lives: ");
	public JPanel numLives = new JPanel(new GridLayout(1,3));
	public JLabel gameName = new JLabel("TEAM PACMAN");
	public Font liveScore = new Font("Arial", Font.BOLD, 36);
	public static int run = 0;
	KeyEvent resetKeyEvent;
	int numLivesLeft = 3;
	
	public GameOver gameOver = new GameOver();

	
	public TestLayout()
	{
		bottom.setLayout(new GridLayout(1, 3));
		bottom.setBackground(Color.BLACK);
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.setBackground(Color.black);
		
		score.setForeground(Color.WHITE);
		lives.setForeground(Color.white);
		gameName.setForeground(Color.YELLOW);
//		numLives.add(new SingleLife());
//		numLives.add(new SingleLife());
//		numLives.add(new SingleLife());
		
		score.setFont(liveScore);
//		lives.setFont(liveScore);
		gameName.setFont(liveScore);
		
//		bottom.add(lives);
//		bottom.add(numLives);
		bottom.add(score);
//		bottom.add(reset);
		top.add(gameName);
		
		con.setLayout(new BorderLayout());
		con.add(panel, BorderLayout.CENTER);
		con.add(bottom, BorderLayout.SOUTH);
		con.add(top, BorderLayout.NORTH);
		
		//bottom.addMouseListener(this);
		
		//reset.addKeyListener(this);
		//reset.addActionListener(this);
		//con.addKeyListener(this);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		addKeyListener(this);
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		TestLayout frame = new TestLayout();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ScoreArray.createScoreArray();
		movement.MovePlayer.setupParallelArrays();
		
		//Build Ghost Thread separate from Pacman Events
		one = new Thread()
		{
			public void run()
			{
				try
				{
					while(run < 1)
					{		
						MoveGhost2.MoveGhost(MovePlayer.oldPosition, "pinky");
						MoveGhost2.MoveGhost(MovePlayer.oldPosition, "blinky");
						MoveGhost2.MoveGhost(MovePlayer.oldPosition, "inky");
						MoveGhost2.MoveGhost(MovePlayer.oldPosition, "clyde");
						Thread.sleep(800);	
					}
				} 
				catch(InterruptedException v) 
				{
				}
			}
		};
		one.start();
	}


	@Override
	public void keyPressed(KeyEvent e)
	{
		if(MovePlayer.IsThereAWall(MovePlayer.oldPosition, e) == false)
		{
			MovePlayer.moveCharacter(MovePlayer.oldPosition, e);
		}
		if(MovePlayer.newPosition == movement.MoveGhost2.newGhostPosition[0] || MovePlayer.newPosition == movement.MoveGhost2.newGhostPosition[1] ||
				MovePlayer.newPosition == movement.MoveGhost2.newGhostPosition[2] || MovePlayer.newPosition == movement.MoveGhost2.newGhostPosition[3])
		{
			con.remove(panel);
			con.add(new GameOver(), BorderLayout.CENTER);
		}
		if(ScoreArray.scoreArraySum == 3000)
		{
			con.remove(panel);
			con.add(new YouWin(), BorderLayout.CENTER);
		}
		resetKeyEvent = e;
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
	}


}