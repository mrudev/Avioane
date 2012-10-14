package playground.avioane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
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
	
	private static Canvas myGrid;
	private static Canvas opponentGrid;
	
	/*
	 * Controls declaration
	 */
	private static Text ipTxt;
	private static Text portTxt;
	private static Text head1;
	private static Text tail1;
	private static Text head2;
	private static Text tail2;
	private static Text head3;
	private static Text tail3;
	private static Text hitTxt;
	
	/**
	 * @param args
	 */
	public static void main (String[] args)
	{
		initServer ();
		
		initDisplay ();
		
		createConnectivityMenu ();
		
		createGameControls ();
		
		createPlayground ();
		
		drawPlane ();
		
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
		shell.setBounds (200, 200, 600, 600);
		
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
	 * Creates the playground areas
	 */
	private static void createPlayground ()
	{
		myGrid = new Canvas (shell, SWT.NONE);
		
		myGrid.setBounds (10, 10, 230, 230);
		
		// Create a paint handler for the canvas
		myGrid.addPaintListener (new PaintListener () 
		{
	      public void paintControl (PaintEvent e) 
	      {
	        e.gc.setForeground (e.display.getSystemColor (SWT.COLOR_BLACK));
	        
	        for (int i = 0; i < 11; i++)
	        {
	        	e.gc.drawLine (10 + i * 20, 10, 10 + i * 20, 210);
	        	e.gc.drawLine (10, 10 + i * 20, 210, 10 + i * 20);
	        }
	      }
	    });
		
		opponentGrid = new Canvas (shell, SWT.NONE);
		
		opponentGrid.setBounds (10, 250, 230, 230);
		
		// Create a paint handler for the canvas
		opponentGrid.addPaintListener (new PaintListener () 
		{
	      public void paintControl (PaintEvent e) 
	      {
	        e.gc.setForeground (e.display.getSystemColor (SWT.COLOR_BLACK));

	        for (int i = 0; i < 11; i++)
	        {
	        	e.gc.drawLine (10 + i * 20, 10, 10 + i * 20, 210);
	        	e.gc.drawLine (10, 10 + i * 20, 210, 10 + i * 20);
	        }
	      }
	    });
	}
	
	/**
	 * Creates the connectivity controls area
	 */
	private static void createConnectivityMenu ()
	{
		shell.setLayout (null);
		
		// Create the controls
		Label lblIp = new Label (shell, SWT.NONE);
		ipTxt = new Text (shell, SWT.BORDER);
		Label lblPort = new Label (shell, SWT.NONE);
		portTxt = new Text (shell, SWT.BORDER);
		final Button btnConnect = new Button (shell, SWT.NONE);
		final Button btnDisconnect = new Button (shell, SWT.NONE);
		
		// Set controls location
		lblIp.setBounds (260, 35, 13, 17);
		ipTxt.setBounds (385, 35, 125, 27);
		lblPort.setBounds (260, 70, 30, 17);
		portTxt.setBounds (385, 70, 50, 27);
		btnConnect.setBounds (260, 105, 100, 29);
		btnDisconnect.setBounds (385, 105, 100, 29);
		
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
	
	/**
	 * Creates game controls area
	 */
	public static void createGameControls ()
	{
		// Create the controls
		Label lblPlane1 = new Label (shell, SWT.NONE);
		head1 = new Text (shell, SWT.BORDER);
		tail1 = new Text (shell, SWT.BORDER);
		Label lblPlane2 = new Label (shell, SWT.NONE);
		head2 = new Text (shell, SWT.BORDER);
		tail2 = new Text (shell, SWT.BORDER);
		Label lblPlane3 = new Label (shell, SWT.NONE);
		head3 = new Text (shell, SWT.BORDER);
		tail3 = new Text (shell, SWT.BORDER);
		
		hitTxt = new Text (shell, SWT.BORDER);
		
		final Button btnDonePlane1 = new Button (shell, SWT.NONE);
		final Button btnDonePlane2 = new Button (shell, SWT.NONE);
		final Button btnDone = new Button (shell, SWT.NONE);
		final Button btnHit = new Button (shell, SWT.NONE);
		
		// Set controls location
		lblPlane1.setBounds (260, 260, 50, 17);
		head1.setBounds (320, 260, 40, 17);
		tail1.setBounds (380, 260, 40, 17);
		lblPlane2.setBounds (260, 290, 50, 17);
		head2.setBounds (320, 290, 40, 17);
		tail2.setBounds (380, 290, 40, 17);
		lblPlane3.setBounds (260, 320, 50, 17);
		head3.setBounds (320, 320, 40, 17);
		tail3.setBounds (380, 320, 40, 17);
		
		hitTxt.setBounds (260, 360, 40, 17);
		
		btnDonePlane1.setBounds (440, 260, 40, 17);
		btnDonePlane2.setBounds (440, 290, 40, 17);
		btnDone.setBounds (440, 320, 40, 17);
		btnHit.setBounds (320, 360, 40, 17);
		
		// Set controls properties
		lblPlane1.setText ("Plane 1");
		lblPlane2.setText ("Plane 2");
		lblPlane3.setText ("Plane 3");
		btnDonePlane1.setText ("Done");
		btnDonePlane2.setText ("Done");
		btnDone.setText ("Done");
		btnHit.setText ("Hit");
	}
	
	/**
	 * Draws a plane on the grid
	 */
	private static void drawPlane ()//(Point head, Point tail)
	{
		 myGrid.addPaintListener (new PaintListener ()
		 {
			@Override
			public void paintControl (PaintEvent e) 
			{
				int x = 110, y = 90;
				
				e.gc.setForeground (e.display.getSystemColor (SWT.COLOR_DARK_CYAN));
				e.gc.setLineWidth (3);
				
				// Plane down
				
//				e.gc.drawLine (x, y, x, y + 20);
//				e.gc.drawLine (x + 20, y + 20, x + 20, y + 40);
//				e.gc.drawLine (x, y + 40, x, y + 60);
//				e.gc.drawLine (x + 20, y + 60, x + 20, y + 80);
//				
//				e.gc.drawLine (x - 20, y, x - 20, y + 20);
//				e.gc.drawLine (x - 40, y + 20, x - 40, y + 40);
//				e.gc.drawLine (x - 20, y + 40, x - 20, y + 60);
//				e.gc.drawLine (x - 40, y + 60, x - 40, y + 80);
//			
//				e.gc.drawLine (x, y, x - 20, y);
//				e.gc.drawLine (x + 20, y + 80, x - 40, y + 80);
//				
//				e.gc.drawLine (x, y + 20, x + 20, y + 20);
//				e.gc.drawLine (x + 20, y + 40, x, y + 40);
//				e.gc.drawLine (x, y + 60, x + 20, y + 60);
//				
//				e.gc.drawLine (x - 20, y + 20, x - 40, y + 20);
//				e.gc.drawLine (x - 40, y + 40, x - 20, y + 40);
//				e.gc.drawLine (x - 20, y + 60, x - 40, y + 60);
				
				// Plane up
				
//				e.gc.drawLine (x, y, x, y - 20);
//				e.gc.drawLine (x - 20, y - 20, x - 20, y - 40);
//				e.gc.drawLine (x, y - 40, x, y - 60);
//				e.gc.drawLine (x - 20, y - 60, x - 20, y - 80);
//				
//				e.gc.drawLine (x + 20, y, x + 20, y - 20);
//				e.gc.drawLine (x + 40, y - 20, x + 40, y - 40);
//				e.gc.drawLine (x + 20, y - 40, x + 20, y - 60);
//				e.gc.drawLine (x + 40, y - 60, x + 40, y - 80);
//
//				e.gc.drawLine (x, y, x + 20, y);
//				e.gc.drawLine (x - 20, y - 80, x + 40, y - 80);
//				
//				e.gc.drawLine (x, y - 20, x - 20, y - 20);
//				e.gc.drawLine (x - 20, y - 40, x, y - 40);
//				e.gc.drawLine (x, y - 60, x - 20, y - 60);
//				
//				e.gc.drawLine (x + 20, y - 20, x + 40, y - 20);
//				e.gc.drawLine (x + 40, y - 40, x + 20, y - 40);
//				e.gc.drawLine (x + 20, y - 60, x + 40, y - 60);
				
				// Plane left
				
				e.gc.drawLine (x, y, x - 20, y);
				e.gc.drawLine (x - 20, y - 20, x - 40, y - 20);
				e.gc.drawLine (x - 40, y, x - 60, y);
				e.gc.drawLine (x - 60, y - 20, x - 80, y - 20);
				
				e.gc.drawLine (x, y - 20, x - 20, y - 20);
				e.gc.drawLine (x - 20, y - 40, x - 40, y - 40);
				e.gc.drawLine (x - 40, y - 20, x - 60, y - 20);
				e.gc.drawLine (x - 60, y - 40, x - 80, y - 40);
//				
//
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				e.gc.drawLine ();
				
				// Plane right
				
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				
//
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				
//				e.gc.drawLine ();
//				e.gc.drawLine ();
//				e.gc.drawLine ();
			}
		});
	}
}
