import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The cell editor class, used for modify the sudoku board cell
 * @author Chengwei
 *
 */
public class SudokuCellEditor extends JDialog{
    
    /** Creates a new instance of Editor */
    public SudokuCellEditor() {
        super();
        initialize();
    }
    /**
     * Initialize the editor
     */
    private void initialize()
    {
    	mPannel=new JPanel();
    	this.mPannel.setLayout(new GridLayout(4,3));
    	mBottons = new JButton[9];
         this.mBottons[0] = new JButton("1");
         this.mBottons[1] = new JButton("2");
         this.mBottons[2] = new JButton("3");
         this.mBottons[3] = new JButton("4");
         this.mBottons[4] = new JButton("5");
         this.mBottons[5] = new JButton("6");
         this.mBottons[6] = new JButton("7");
         this.mBottons[7] = new JButton("8");
         this.mBottons[8] = new JButton("9");
         this.bottonCancel = new JButton("C");
         this.bottonCancel.setToolTipText("Cancel Button");
         
         Font carattere =new Font("SansSerif",Font.PLAIN,16);
         this.mBottons[0].setFont(carattere);
         this.mBottons[1].setFont(carattere);
         this.mBottons[2].setFont(carattere);
         this.mBottons[3].setFont(carattere);
         this.mBottons[4].setFont(carattere);
         this.mBottons[5].setFont(carattere);
         this.mBottons[6].setFont(carattere);
         this.mBottons[7].setFont(carattere);
         this.mBottons[8].setFont(carattere);
         this.bottonCancel.setFont(carattere);
         
         //Add actions for the buttons
         this.mBottons[0].addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 botton1ActionPerformed(evt);
             }
         });
         this.mBottons[1].addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 botton2ActionPerformed(evt);
             }
         });
         this.mBottons[2].addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 botton3ActionPerformed(evt);
             }
         });
         this.mBottons[3].addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 botton4ActionPerformed(evt);
             }
         });
         this.mBottons[4].addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 botton5ActionPerformed(evt);
             }
         });
         this.mBottons[5].addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 botton6ActionPerformed(evt);
             }
         });
         this.mBottons[6].addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 botton7ActionPerformed(evt);
             }
         });
         this.mBottons[7].addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 botton8ActionPerformed(evt);
             }
         });
         this.mBottons[8].addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 botton9ActionPerformed(evt);
             }
         });
         this.bottonCancel.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 bottonCancelActionPerformed(evt);
             }
         });

         this.mPannel.add(this.mBottons[0]);
         this.mPannel.add(this.mBottons[1]);
         this.mPannel.add(this.mBottons[2]);
         this.mPannel.add(this.mBottons[3]);
         this.mPannel.add(this.mBottons[4]);
         this.mPannel.add(this.mBottons[5]);
         this.mPannel.add(this.mBottons[6]);
         this.mPannel.add(this.mBottons[7]);
         this.mPannel.add(this.mBottons[8]);
         this.mPannel.add(this.bottonCancel);
         
         //Create a text field for user input
         mTextfield = new KeyTextField(3);
         Font font = mTextfield.getFont().deriveFont(Font.BOLD, 12);
         mTextfield.requestFocus();
         mTextfield.setFont(font);
         mTextfield.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				  int keyCode = e.getKeyCode();
					
				  //System.out.println(KeyEvent.getKeyText(keyCode));
				  if(KeyEvent.getKeyText(keyCode).equals("Enter"))
				  {
					  mValue = (mTextfield.getText());
					  setVisible(false);
				  }
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        	 
         });
         this.mPannel.add(this.mTextfield);
         
         this.getContentPane().setLayout(new BorderLayout());
         this.getContentPane().add(mPannel, BorderLayout.CENTER);
        // this.getContentPane().add(mPannel);
         this.setSize(100,100);
         //this.setLocationRelativeTo(Board.getInstance().getPanel());
         this.setModal(true);
         this.pack();
    }
    /**
     * Get the value player enter
     * @return the value
     */
    public String getButtonValue()
    {
    	return mValue;
    }
    /**
     * Set on and off the editor
     * @param _v true if the editor is on
     */
    public void setVisible(Boolean _v)
    {
    	mPannel.setVisible(_v);
    }
    /**
     * Perform the button 1 action
     * @param evt action event
     */
    private void botton1ActionPerformed(ActionEvent evt){
        mValue="1";
        this.setVisible(false);
    }
    /**
     * Perform the button 1 action
     * @param evt action event
     */
    private void botton2ActionPerformed(ActionEvent evt){
        mValue="2";
        this.setVisible(false);
    }
    /**
     * Perform the button 1 action
     * @param evt action event
     */
    private void botton3ActionPerformed(ActionEvent evt){
        mValue="3";
        this.setVisible(false);
    }
    /**
     * Perform the button 1 action
     * @param evt action event
     */
    private void botton4ActionPerformed(ActionEvent evt){
        mValue="4";
        this.setVisible(false);
    }
    /**
     * Perform the button 1 action
     * @param evt action event
     */
    private void botton5ActionPerformed(ActionEvent evt){
        mValue="5";
        this.setVisible(false);
    }
    /**
     * Perform the button 1 action
     * @param evt action event
     */
    private void botton6ActionPerformed(ActionEvent evt){
        mValue="6";
        this.setVisible(false);
    }
    /**
     * Perform the button 1 action
     * @param evt action event
     */
    private void botton7ActionPerformed(ActionEvent evt){
        mValue="7";
        this.setVisible(false);
    }
    /**
     * Perform the button 1 action
     * @param evt action event
     */
    private void botton8ActionPerformed(ActionEvent evt){
        mValue="8";
        this.setVisible(false);
    }
    /**
     * Perform the button 1 action
     * @param evt action event
     */
    private void botton9ActionPerformed(ActionEvent evt){
        mValue="9";
        this.setVisible(false);
    }
    /**
     * Perform the button 1 action
     * @param evt action event
     */
    private void bottonCancelActionPerformed(ActionEvent evt){
        mValue="";
        this.setVisible(false);
    }


	private static final long serialVersionUID = 1L;

    private JPanel mPannel;
    private JButton[] mBottons;
    private KeyTextField mTextfield;
    private JButton bottonCancel;
    public String mValue;


}