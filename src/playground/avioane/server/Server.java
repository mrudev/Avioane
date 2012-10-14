package playground.avioane.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * Code-Playground
 * 
 * Avioane
 * 
 * @authors mrudev, cubiks
 *
 */
public class Server implements Runnable
{
	/*
	 * Private members
	 */
	
	private final int PORT = 9011;
	private boolean isRunning;
	private ServerSocket serverSocket; // Socket ID
	
	/**
	 * Default constructor
	 */
	public Server ()
	{
		isRunning = initServer ();
	}
	
	/**
	 * Server's main loop
	 */
	@Override
	public void run ()
	{
		while (isRunning)
		{
			try
			{
				ConnectionManager.addConnection (serverSocket.accept ());
			}
			catch (SocketTimeoutException e)
			{
				System.out.println ("Server> Exception - Timeout reached");
			}
			catch (IOException e1)
			{
				e1.printStackTrace ();
			}
			
			try
			{
				Thread.sleep (50);
			}
			catch (InterruptedException e1)
			{
				e1.printStackTrace ();
			}
			
		} // while ()
		
		deinitServer ();
	}
	
	/**
	 * Create the server's socket connection
	 * 
	 * @return - true on success, false otherwise
	 */
	private boolean initServer ()
	{
		try
		{
			// Create server socket
			serverSocket = new ServerSocket (this.PORT);
			
			try
			{
				serverSocket.setSoTimeout (5000); // 5 sec
			}
			catch (SocketException e2)
			{
				e2.printStackTrace();
			}
			
			return true;
		}
		catch (IOException e1)
		{
			e1.printStackTrace ();
		}
		catch (SecurityException e2)
		{
			e2.printStackTrace ();
		}
		catch (IllegalArgumentException e3)
		{
			e3.printStackTrace ();
		}
		
		return false;
	}
	
	/**
	 * Closes the server socket connection
	 */
	private void deinitServer ()
	{
		try
		{
			serverSocket.close ();
		}
		catch (IOException e)
		{
			e.printStackTrace ();
		}
	}
	
	/**
	 * Gracefully terminates the server thread
	 */
	public void kill ()
	{
		this.isRunning = false;
	}
}
