package playground.avioane.game;

/**
 * Code-Playground
 * 
 * Avioane
 * 
 * @authors mrudev, cubiks
 *
 */
public class Player
{
	private Plane[] planes = new Plane[3];
	
	/**
	 * Construct
	 */
	public Player ()
	{
		
	}

	public Plane[] getPlanes ()
	{
		return planes;
	}

	public void setPlanes (Plane[] planes)
	{
		this.planes = planes;
	}
}
