package playground.avioane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import playground.avioane.server.ConnectionManager;
import playground.avioane.server.Server;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

/**
 * Code-Playground
 * 
 * Avioane
 * 
 * @authors mrudev, cubiks
 *
 */
public class Main
{
	/*
	 * Private members
	 */
	private static Display display;
	private static Shell shell;
	private static Server server;
	
	/*
	 * Controls declaration
	 */
	private static Text ipTxt;
	private static Text portTxt;
	
	/**
	 * @param args
	 */
	public static void main (String[] args)
	{
		initServer ();
		
		initDisplay ();
		
		createConnectivityMenu ();
		
		while (!shell.isDisposed ())
		{
			if (!display.readAndDispatch ())
			{
				display.sleep ();
			}
		}
		
		ConnectionManager.removeAllConnections ();
		
		deinitServer ();
		
		deinitDisplay ();
		
		System.out.println ("Main> Exit");
	}
	
	/**
	 * Create the display and the application window
	 */
	private static void initDisplay ()
	{
		display = new Display ();
		shell = new Shell (display);
		
//		shell.pack();
		shell.open ();
	}
	
	/**
	 * Dispose the display
	 */
	private static void deinitDisplay ()
	{
		display.dispose ();
	}
	
	/**
	 * Utility function to create and start the server thread
	 */
	private static void initServer ()
	{
		server = new Server ();
		
		Thread thread = new Thread (server);
		thread.start ();
	}
	
	/**
	 * Utility function to terminate the server thread
	 */
	private static void deinitServer ()
	{
		server.kill ();
	}
	
	/**
	 * Creates the connectivity controls area
	 */
	private static void createConnectivityMenu ()
	{
		shell.setLayout(null);
		
		// Create the controls
		Label lblIp = new Label (shell, SWT.NONE);
		ipTxt = new Text (shell, SWT.BORDER);
		Label lblPort = new Label (shell, SWT.NONE);
		portTxt = new Text (shell, SWT.BORDER);
		final Button btnConnect = new Button (shell, SWT.NONE);
		final Button btnDisconnect = new Button (shell, SWT.NONE);
		
		// Set controls location
		lblIp.setBounds (225, 35, 13, 17);
		ipTxt.setBounds (350, 35, 125, 27);
		lblPort.setBounds (225, 70, 30, 17);
		portTxt.setBounds (350, 70, 50, 27);
		btnConnect.setBounds (225, 105, 100, 29);
		btnDisconnect.setBounds (350, 105, 100, 29);
		
		// Set controls properties
		lblPort.setText ("Port");
		lblIp.setText ("IP");
		
		btnConnect.setText ("Connect");
		btnDisconnect.setText ("Disconnect");
		btnDisconnect.setEnabled (false);
		
		// Add event listeners to the buttons
		btnConnect.addMouseListener (new MouseListener()
		{
			@Override
			public void mouseUp (MouseEvent e)
			{
				String ip = ipTxt.getText ();
				String port = portTxt.getText ();
				
				if (ip.isEmpty () || port.isEmpty ())
					return;
				
				btnConnect.setEnabled (false);
				btnDisconnect.setEnabled (true);
				
				ConnectionManager.addConnection (ip, Integer.parseInt (port));
				
				System.out.println ("Connect to <" + ip + ":" + port + ">");
			}
			
			@Override
			public void mouseDown (MouseEvent e) { /*TODO:*/ }
			
			@Override
			public void mouseDoubleClick (MouseEvent e) { /*TODO:*/ }
		});
		
		btnDisconnect.addMouseListener (new MouseListener ()
		{
			@Override
			public void mouseUp (MouseEvent e)
			{
				btnConnect.setEnabled (true);
				btnDisconnect.setEnabled (false);
				
				ConnectionManager.removeConnection (ConnectionManager.getConnection ("id"));
				
				System.out.println ("Main> Disconnecting...");
			}
			
			@Override
			public void mouseDown (MouseEvent e) { /*TODO:*/ }
			
			@Override
			public void mouseDoubleClick (MouseEvent e) { /*TODO:*/ }
		});
	}
}
