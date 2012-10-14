package playground.avioane.game;


/**
 * Code-Playground
 * 
 * Avioane
 * 
 * @authors mrudev, cubiks
 *
 */
public class Board
{
	private final int BOARD_SIZE = 10;
	
	/*
	 * Private members
	 */
	Cell[][] board = new Cell [BOARD_SIZE][BOARD_SIZE];
	
	/**
	 * Construct
	 */
	public Board ()
	{
		createBoardMatrix ();
	}
	
	public void createBoardMatrix ()
	{
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			for (int j = 0; j < BOARD_SIZE; j++)
			{
				
			}
		}
	}
}
