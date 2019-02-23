package gameBoard;

//This class sets up the array that keeps track of the score.  As pacman eats power pellets his score is incremented by 10 points 3000 points is a winning score.
public class ScoreArray
{
	public static int[] scoreArray = new int[FoodPane.NUM_ROWS * FoodPane.NUM_COLUMNS];
	public static int scoreArraySum = 10;//accounts for the fact pacman starts on a square that won't be counted
	
	//Set up array to determine if all powerpellets are gone. 
	public static void createScoreArray()
	{
		for(int i = 0; i < scoreArray.length; ++i)
		{
			scoreArray[i] = 0;
		}
		//Account for pacmans original position
		scoreArray[FoodPane.NUM_COLUMNS + 2] = 1;
	}
	
	//This method updates the score and changes the state of the score array from a 0 to a 1.  
	//This allows us to check if pacman already visited the current square and not add 10 pts twice.
	public static void updateScoreArray(int pacmanPosition)
	{
		scoreArray[pacmanPosition] = 1;
		scoreArraySum = 0;
		for(int i = 0; i < scoreArray.length; ++i)
		{
			if(scoreArray[i] == 1)
				{
					scoreArraySum += 10;
				}
		}
	}
	
	
}
