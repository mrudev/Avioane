package playground.avioane.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Code-Playground
 * 
 * @author mrudev, cubiks
 *
 */
public class Connection implements Runnable
{
	/*
	 * Private members
	 */
	
	private boolean isRunning;
	
	private Socket connection; // connection ID
	DataOutputStream output;
	BufferedReader input;
	
	/**
	 * Default constructor
	 */
	public Connection (Socket con)
	{
		this.connection = con;
	}
	
	public Connection (String host, int port)
	{
		initConnection (host, port);
	}
	
	/**
	 * Client connection's main loop
	 */
	@Override
	public void run ()
	{
		isRunning = initCommunication ();
		
		// Loop around
		while (isRunning)
		{
			try
			{
				if (input.ready ())
				{
					String received = null;
					
					// Read the command from the input stream
					received = input.readLine ();
					
					System.out.println ("Received: " + received);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace ();
			}
			
			// Sleep a while between checks for messages
			try
			{
				Thread.sleep (50);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace ();
			}
			
		} // while ()
		
		deinitCommunication ();
		
		deinitConnection ();
	}
	
	/**
	 * Transmits a message to the server
	 * 
	 * @param message
	 */
	public void sendMessage (String message)
	{
		// Compute the response
		String response = "Received: " + message;
		
		// Flush stream
		try
		{
			output.writeBytes (response + "\n");
			output.flush ();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if the client connection thread is still running
	 * 
	 * @return
	 */
	public boolean isAlive ()
	{
		return isRunning;
	}
	
	/**
	 * Gracefully terminates the client connection thread
	 */
	public void kill ()
	{
		this.isRunning = false;
	}
	
	/**
	 * Creates the socket connection with the host
	 * 
	 * @param host
	 * @param port
	 * @return - true on success, false otherwise
	 */
	private boolean initConnection (String host, int port)
	{
		try
		{
			this.connection = new Socket (host, port);
			
			return true;
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Closes the socket connection with the client
	 */
	private void deinitConnection ()
	{
		try
		{
			this.connection.close ();
		}
		catch (IOException e)
		{
			e.printStackTrace ();
		}
	}
	
	/**
	 * Opens the input/output streams for communication
	 * 
	 * @return - true on success, false otherwise
	 */
	private boolean initCommunication ()
	{
		output = null;
		input = null;
		
		try
		{
			// get output handler
			output = new DataOutputStream (connection.getOutputStream ());
			output.flush ();
			
			// get input
			input = new BufferedReader (new InputStreamReader (connection.getInputStream ()));
			
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace ();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		
		return false;
	}
	
	/**
	 * Closes the input/output streams
	 */
	private void deinitCommunication ()
	{
		try
		{
			input.close ();
			output.close ();
		}
		catch (IOException e)
		{
			e.printStackTrace ();
		}
	}
}
