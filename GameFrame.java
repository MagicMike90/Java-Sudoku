import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication1;
/**
 * 
 * 
 * 
 * @author User
 */

public class GameFrame extends javax.swing.JFrame {

	/**
	 * Creates new form GameFrame
	 */
	public GameFrame() {
		// mBoard = new Board();
		initComponents();

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	public void initComponents() {
		//initializing buttons/labels
		backToMenu = new javax.swing.JButton();
		hint = new javax.swing.JButton();
		timerText = new javax.swing.JLabel();
		undoButton = new javax.swing.JButton();
		redoButton = new javax.swing.JButton();
		resetButton = new javax.swing.JButton();
		newGameButton = new javax.swing.JButton();
		PlaceSudokuBoardHere = new javax.swing.JLabel();
		solveButton = new javax.swing.JButton();
		timerOutput = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		backToMenu.setText("Back to Menu"); //set back to menu text
		backToMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backToMenuActionPerformed(evt);
			}
		});

		hint.setText("Hint"); //set hint text
		hint.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				hintActionPerformed(evt);
			}
		});
		timerText.setText("Timer:"); //set timer text

		undoButton.setText("Undo"); //set undo button text
		undoButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// undoButtonActionPerformed(evt);
				SudokuBoard.getInstance().undoBoard();
			}
		});

		redoButton.setText("Redo"); //set redo button text
		redoButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// undoButtonActionPerformed(evt);
				SudokuBoard.getInstance().redoBoard();
			}
		});

		resetButton.setText("Reset"); //set reset button text
		resetButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				SudokuBoard.getInstance().resetBoard();
			}
		});

		newGameButton.setText("New Game"); //set new game button text
		newGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				SudokuBoard.getInstance().newBoard();
			}
		});

		PlaceSudokuBoardHere.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		PlaceSudokuBoardHere.setText("SudokuBoardHere");

		solveButton.setText("Solve this Board"); //set solve board button text
		solveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				solveButtonActionPerformed(evt);
			}
		});

		timerOutput.setText("");

		// ////////////////
		//create timer thread to measure time
		int delay = 1000; // milliseconds
		Timer t;

		final timerClass tCounter = new timerClass();
		//create action to happen every second
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				SudokuBoard.getInstance().recourdTime(tCounter.tick());
				final String outputString = Integer.toString(tCounter.tick());
				timerOutput.setText(outputString);
				timerClass.incCounter();

			}
		};
		t = new Timer(delay, taskPerformer);

		t.start(); //start the timer

		// ////////////////
		//adding all components together
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup().addComponent(backToMenu)
								.addGap(0, 0, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(32, 32, 32)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(redoButton)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																										188, Short.MAX_VALUE))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(undoButton)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(resetButton)
																								.addGap(51, 51, 51)))
																.addComponent(newGameButton))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(61, 61, 61)
																								.addComponent(timerText)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addComponent(timerOutput)
																								.addGap(16, 16, 16)
																								.addComponent(solveButton)
																								.addGap(18, 18, 18)
																								.addComponent(hint))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(71, 71, 71)
																								.addComponent(
																										SudokuBoard
																												.getInstance()
																												.getPanel(),
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										265,
																										javax.swing.GroupLayout.PREFERRED_SIZE)))
																.addGap(0, 0, Short.MAX_VALUE))).addGap(40, 40, 40)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(backToMenu)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(hint).addComponent(timerText)
												.addComponent(solveButton).addComponent(timerOutput))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(SudokuBoard.getInstance().getPanel(),
										javax.swing.GroupLayout.PREFERRED_SIZE, 265,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(undoButton).addComponent(resetButton)
												.addComponent(newGameButton))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(redoButton).addGap(14, 14, 14)));
		this.getContentPane().setBackground(Color.cyan); //set background colour

		pack();
	}// </editor-fold>

	private void backToMenuActionPerformed(java.awt.event.ActionEvent evt) {

		this.setVisible(false);
		// ///////////////
		//start timer to allow timer to start at 0, increment by one after choosing another new game
		Timer t;
		int delay = 1000;

		// final timerClass tCounter = new timerClass() ;
		ActionListener timerDecTask = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				timerClass.decCounter();

			}
		};

		t = new Timer(delay, timerDecTask);

		t.start(); //start this timer
		// //////////////
		new MainFrameUI().setVisible(true);

	}
//solve button action
	private void solveButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// SudokuBoard.getInstance().setGridToSolved();
		SudokuBoard.getInstance().displayAnswer();
	}
//hint button action
	private void hintActionPerformed(java.awt.event.ActionEvent evt) {
		// SudokuBoard.getInstance().setGridToSolved();
		SudokuBoard.getInstance().hintAction();
	}

	/**
	 * @param args
	 *           the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GameFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel PlaceSudokuBoardHere;
	private javax.swing.JButton backToMenu;
	private javax.swing.JButton hint;
	private javax.swing.JButton newGameButton;
	private javax.swing.JButton redoButton;
	private javax.swing.JButton resetButton;
	private javax.swing.JButton solveButton;
	private javax.swing.JLabel timerOutput;
	private javax.swing.JLabel timerText;
	private javax.swing.JButton undoButton;
	// End of variables declaration

}
