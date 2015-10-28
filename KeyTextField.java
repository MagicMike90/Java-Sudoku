import java.awt.event.*;

import javax.swing.*;

/**
 * A class inherit from JTextField to perform text field limitations
 * @author Chengwei
 *
 */
public class KeyTextField extends JTextField
{

	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public KeyTextField()
	{
		this( "", 10 );
	}
	/**
	 * Constructor
	 * @param col length can be made
	 */
	public KeyTextField(int col )
	{
		this( "", col );
	}
	/**
	 * Constructs a new TextField initialized with the specified text and columns.
	 */
	public KeyTextField( String s, int col )
	{
		super( s, col );
	}
	/**
	 * process the text field key event
	 */
	protected void processKeyEvent( KeyEvent e )
	{
		int keyCode = e.getKeyCode();
		if(KeyEvent.getKeyText(keyCode).equals("Backspace")||
			  KeyEvent.getKeyText(keyCode).equals("Enter"))
		{
			super.processKeyEvent( e );
		}
		if(49<= keyCode&&keyCode <=58)
		{
			super.processKeyEvent( e );
			String mValue = KeyEvent.getKeyText(keyCode);
			this.setText(mValue);
   		}
	}	
}
