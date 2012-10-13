package playground.avioane.server;

import java.net.Socket;
import java.util.ArrayList;

/**
 * Code-Playground
 * 
 * @author mrudev, cubiks
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
			if (con != null)
				return con;
		}
		
		return null;
	}
	
	/**
	 * Removes a connection from the list
	 * @param id
	 */
	public static void removeConnection (Connection con)
	{
		if (con != null)
		{
			killConnection (con);
			
			connections.remove (con);
		}
	}
	
	/**
	 * Gracefully terminates the client thread
	 */
	private static void killConnection (Connection con)
	{
		if (con.isAlive ())
			con.kill ();
	}
}
