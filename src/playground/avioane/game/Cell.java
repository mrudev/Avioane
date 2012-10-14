package playground.avioane.game;

import java.awt.Point;
import java.awt.Rectangle;

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
	private final int CELL_SIZE = 10;
	
	/*
	 * Private members
	 */
	private Rectangle loc;
	private boolean isHit;
	
	/**
	 * Construct
	 */
	public Cell (int x, int y)
	{
		setLoc (new Rectangle (x, y, CELL_SIZE, CELL_SIZE));
		setHit (false);
	}
	
	boolean mark (int x, int y)
	{
		return isHit = loc.contains (new Point (x, y));
	}
	
	public Rectangle getLoc ()
	{
		return loc;
	}
	
	public void setLoc (Rectangle loc)
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
