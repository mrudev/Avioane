package playground.avioane.game;

import java.awt.Point;

/**
 * Code-Playground
 * 
 * Avioane
 * 
 * @authors mrudev, cubiks
 *
 */
public class Cell
{
	private Point loc;
	private boolean isHit;
	
	/**
	 * Construct
	 */
	public Cell (int x, int y)
	{
		setLoc (new Point (x, y));
		setHit (false);
	}

	public Point getLoc ()
	{
		return loc;
	}

	public void setLoc (Point loc)
	{
		this.loc = loc;
	}

	public boolean isHit ()
	{
		return isHit;
	}

	public void setHit (boolean isHit)
	{
		this.isHit = isHit;
	}
}
