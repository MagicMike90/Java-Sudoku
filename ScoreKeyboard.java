
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class use for player to perform keyboard input after get high score 
 * @author Chengwei
 *
 */
public class ScoreKeyboard extends JDialog implements KeyListener,ActionListener
{


    /**
     * Constructor calling super method
     * @param name give a name to keyboard
     */
    public ScoreKeyboard(String name) {
        //super(name);
    	mPanel=new JPanel();
    	addComponentsToPane();
    }
    /**
     * Add in all the keyboard's components
     */
    private void addComponentsToPane() {
        
    	//Create the clear button
        JButton button = new JButton("Clear");
        button.addActionListener(this);
        Font font = button.getFont().deriveFont(Font.BOLD, 15);
        button.setFont(font);
        
        //Display the information
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        font = displayArea.getFont().deriveFont(Font.BOLD, 20);
        displayArea.setForeground(new Color(0,92,0));
        displayArea.setBackground(new Color(200,200,200));
        displayArea.setFont(font);
        
        displayArea.setText("Please key in your name");

        //Text filed for user input
        mTpingArea = new JTextField(10);
        mTpingArea.addKeyListener(this);
        font = mTpingArea.getFont().deriveFont(Font.BOLD, 30);
        mTpingArea.setFont(font);
     
        mPanel.add(mTpingArea, BorderLayout.CENTER);
        mPanel.add(displayArea, BorderLayout.PAGE_START);
        mPanel.add(button, BorderLayout.PAGE_END);
        
        getContentPane().add(mPanel);
       // this.setSize(100,100);
        //this.setModal(true);
        this.pack();
    }

    
    
    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        //displayInfo(e, "KEY TYPED: ");
    }
    
    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        //displayInfo(e, "KEY PRESSED: ");
    	 int keyCode = e.getKeyCode();
    /*	 String keyString = "key code = " + keyCode
                 + " ("
                 + KeyEvent.getKeyText(keyCode)
                 + ")";
    	 System.out.println(keyString);
    	 */
    	 if(KeyEvent.getKeyText(keyCode).equals("Enter"))
    	 {
    		 mUserName = mTpingArea.getText();
    		 ScoreManager.getInstance().Save();
    		 this.setVisible(false);
    	 }

    }
    
    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        //displayInfo(e, "KEY RELEASED: ");
    }
    
    /** Handle the button click. */
    public void actionPerformed(ActionEvent e) {
        //Clear the text components.
    	mTpingArea.setText("");

        //Return the focus to the typing area.
        mTpingArea.requestFocusInWindow();
    }
    public String getName()
    {
    	return mUserName;
    }
	private static final long serialVersionUID = 1L;
	JTextArea displayArea;
    JTextField mTpingArea;
    private JPanel mPanel;
    private String mUserName;
}