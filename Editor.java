import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor extends JDialog {
    
    /** Creates a new instance of Editor */
    public Editor() {
        super();
        try { 
        	UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());    
        } 
        catch(Exception e) {}
  
        initialize();
        
    }

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
         this.getContentPane().setLayout(new BorderLayout());
         this.getContentPane().add(mPannel, BorderLayout.CENTER);
         this.setSize(100,100);
         this.setLocationRelativeTo(null);
         this.setModal(true);
         Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();      
         int x=(int)((dimension.getWidth() - 400)/2);
         int y=(int)((dimension.getHeight() - 400)/2);
        this.setLocation(x, y);
         this.pack();
    }
    public String getButtonValue()
    {
    	return mValue;
    }
    public void setVisible(Boolean _v)
    {
    	mPannel.setVisible(_v);
    }
    public static void main(String args[]) {
        Editor ed = new Editor();
        ed.setVisible(true);
    }
    private void botton1ActionPerformed(ActionEvent evt){
        mValue="1";
        this.setVisible(false);
    }
    private void botton2ActionPerformed(ActionEvent evt){
        mValue="2";
        this.setVisible(false);
    }
    private void botton3ActionPerformed(ActionEvent evt){
        mValue="3";
        this.setVisible(false);
    }
    private void botton4ActionPerformed(ActionEvent evt){
        mValue="4";
        this.setVisible(false);
    }
    private void botton5ActionPerformed(ActionEvent evt){
        mValue="5";
        this.setVisible(false);
    }
    private void botton6ActionPerformed(ActionEvent evt){
        mValue="6";
        this.setVisible(false);
    }
    private void botton7ActionPerformed(ActionEvent evt){
        mValue="7";
        this.setVisible(false);
    }
    private void botton8ActionPerformed(ActionEvent evt){
        mValue="8";
        this.setVisible(false);
    }
    private void botton9ActionPerformed(ActionEvent evt){
        mValue="9";
        this.setVisible(false);
    }
    private void bottonCancelActionPerformed(ActionEvent evt){
        mValue=" ";
        this.setVisible(false);
    }
    
	private static final long serialVersionUID = 1L;

    private JPanel mPannel;
    private JButton[] mBottons;
    private JButton bottonCancel;
    public String mValue;
}