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
public class Plane
{
	private Point head;
	private Point leftWing;
	private Point rightWing;
	private Point leftTail;
	private Point rightTail;
	
	/**
	 * Construct
	 */
	public Plane ()
	{
		
	}

	public Point getHead ()
	{
		return head;
	}

	public void setHead (Point head)
	{
		this.head = head;
	}

	public Point getLeftWing ()
	{
		return leftWing;
	}

	public void setLeftWing (Point leftWing)
	{
		this.leftWing = leftWing;
	}

	public Point getRightWing ()
	{
		return rightWing;
	}

	public void setRightWing (Point rightWing)
	{
		this.rightWing = rightWing;
	}

	public Point getLeftTail ()
	{
		return leftTail;
	}

	public void setLeftTail (Point leftTail)
	{
		this.leftTail = leftTail;
	}

	public Point getRightTail ()
	{
		return rightTail;
	}

	public void setRightTail (Point rightTail)
	{
		this.rightTail = rightTail;
	}
}
