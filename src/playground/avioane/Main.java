package playground.avioane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import playground.avioane.server.Server;
import org.eclipse.swt.widgets.Label;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.Button;

/**
 * Code-Playground
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
		
		deinitServer ();
		
		deinitDisplay ();
		
		System.out.println ("> Exit");
	}
	
	/**
	 * Create the display and the application window
	 */
	private static void initDisplay ()
	{
		display = new Display ();
		shell = new Shell (display);
		shell.setLayout (new BoxLayout (BoxLayout.X_AXIS));
		
		shell.pack();
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
		// Create the controls
		Label lblIp = new Label (shell, SWT.NONE);
		ipTxt = new Text (shell, SWT.BORDER);
		Label lblPort = new Label (shell, SWT.NONE);
		portTxt = new Text (shell, SWT.BORDER);
		final Button btnConnect = new Button (shell, SWT.NONE);
		final Button btnDisconnect = new Button (shell, SWT.NONE);
		
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
				
				if (null == ip || null == port)
					return;
				
				btnConnect.setEnabled (false);
				btnDisconnect.setEnabled (true);
				
				System.out.println ("Connect to <"+ip+":"+port+">");
			}
			
			@Override
			public void mouseDown (MouseEvent e)
			{
				//
			}
			
			@Override
			public void mouseDoubleClick (MouseEvent e)
			{
				//
			}
		});
		
		btnDisconnect.addMouseListener (new MouseListener ()
		{
			@Override
			public void mouseUp (MouseEvent e)
			{
				btnConnect.setEnabled (true);
				btnDisconnect.setEnabled (false);
				
				System.out.println ("Disconnecting...");
			}
			
			@Override
			public void mouseDown (MouseEvent e)
			{
				//
			}
			
			@Override
			public void mouseDoubleClick (MouseEvent e)
			{
				//
			}
		});
	}
}
