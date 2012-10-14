package playground.avioane.server;

import java.net.Socket;
import java.util.ArrayList;

/**
 * Code-Playground
 * 
 * Avioane
 * 
 * @authors mrudev, cubiks
 *
 */
public final class ConnectionManager
{
	/*
	 * Private members
	 */
	
	private static ArrayList<Connection> connections = new ArrayList<Connection> ();
	
	/**
	 * Creates a new connection object and adds it to the list
	 * 
	 * @param con - the socket connection
	 */
	public static void addConnection (Socket con)
	{
		Connection client = new Connection (con);
		
		Thread thread = new Thread (client);
		thread.start ();
		
		connections.add (client);
	}
	
	/**
	 * Creates a new connection object and adds it to the list
	 * 
	 * @param host - the ip address of the host
	 * @param port - the port
	 */
	public static void addConnection (String host, int port)
	{
		Connection client = new Connection (host, port);
		
		Thread thread = new Thread (client);
		thread.start ();
		
		connections.add (client);
	}
	
	/**
	 * Retrieves an active connection
	 * 
	 * @param id - id of the connection to be retrieved
	 * @return
	 */
	public static Connection getConnection (String id)
	{
		for (Connection con : connections)
		{
			if (con != null) // TODO: condition
				return con;
		}
		
		return null;
	}
	
	/**
	 * Retrieves an active connection
	 * 
	 * @param id - id of the connection to be retrieved
	 * @return
	 */
	public static Connection getConnection (int index)
	{
		return connections.get (index);
	}
	
	/**
	 * Removes a connection from the list
	 * 
	 * @param con - connection context
	 */
	public static void removeConnection (Connection con)
	{
		killConnection (con);
		
		connections.remove (con);
	}
	
	/**
	 * Removes a connection from the list
	 * 
	 * @param id
	 */
	public static void removeConnection (int index)
	{
		killConnection (getConnection (index));
		
		connections.remove (index);
	}
	
	/**
	 * Removes all connections
	 */
	public static void removeAllConnections ()
	{
		for (int i = 0; i < connections.size (); i++)
		{
			removeConnection (i);
		}
	}
	
	/**
	 * Gracefully terminates the client thread
	 */
	private static void killConnection (Connection con)
	{
		if (null != con && con.isAlive ())
			con.kill ();
	}
}
