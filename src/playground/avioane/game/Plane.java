package playground.avioane.game;

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
	private Cell head;
	private Cell leftWing;
	private Cell rightWing;
	private Cell leftTail;
	private Cell rightTail;
	
	/**
	 * Construct
	 */
	public Plane (Cell head, Cell lw, Cell rw, Cell lt, Cell rt)
	{
		setHead (head);
		setLeftWing (lw);
		setRightWing (rw);
		setLeftTail (lt);
		setRightTail (rt);
	}

	public Cell getHead ()
	{
		return head;
	}

	public void setHead (Cell head)
	{
		this.head = head;
	}

	public Cell getLeftWing ()
	{
		return leftWing;
	}

	public void setLeftWing (Cell leftWing)
	{
		this.leftWing = leftWing;
	}

	public Cell getRightWing ()
	{
		return rightWing;
	}

	public void setRightWing (Cell rightWing)
	{
		this.rightWing = rightWing;
	}

	public Cell getLeftTail ()
	{
		return leftTail;
	}

	public void setLeftTail (Cell leftTail)
	{
		this.leftTail = leftTail;
	}

	public Cell getRightTail ()
	{
		return rightTail;
	}

	public void setRightTail (Cell rightTail)
	{
		this.rightTail = rightTail;
	}
}
