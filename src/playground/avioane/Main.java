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
	private static Server server;
	private static Text ipTxt;
	private static Text portTxt;
	
	/**
	 * @param args
	 */
	public static void main (String[] args)
	{
		initServer ();
		
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setLayout (new BoxLayout (BoxLayout.X_AXIS));
		
		Label lblIp = new Label (shell, SWT.NONE);
		lblIp.setText ("IP");
		
		ipTxt = new Text (shell, SWT.BORDER);
		
		Label lblPort = new Label (shell, SWT.NONE);
		lblPort.setText ("Port");
		
		portTxt = new Text (shell, SWT.BORDER);
		
		Button btnConnect = new Button (shell, SWT.NONE);
		btnConnect.setText ("Connect");
		btnConnect.addMouseListener (new MouseListener()
		{
			@Override
			public void mouseUp (MouseEvent e)
			{
				System.out.println ("Click-click!");
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
		
		shell.pack();
		shell.open ();
		
		while (!shell.isDisposed ())
		{
			if (!display.readAndDispatch ())
			{
				display.sleep ();
			}
		}
		
		deinitServer ();
		display.dispose ();
		
		System.out.println ("> Exit");
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
}
