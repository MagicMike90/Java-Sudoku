import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

/**
 * A class that modify and display for the suduko board cell
 * 
 * @author Chengwei
 * 
 */
public class SudokuBoardCell {

	public SudokuBoardCell() {
		mField = new JTextField();
		mEditor = new SudokuCellEditor();
		mField.setEditable(false);
		mSelected = new Color(22, 130, 219);

		mHasListener = false;

		// Create a mouse listener
		mMouseListener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				;
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// System.out.println("here");
				mField.setBackground(mSelected);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// mField.setBackground(mBackgound);
				setInitBGColor();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {

				mEditor.setVisible(true);
				String num = mEditor.getButtonValue();
				/*
				 * if(num != null) {
				 * if(num.isEmpty()||SudokuBoard.getInstance().getSolver
				 * ().isCellLegal(Integer.parseInt(num), mId)) { setRightColor();
				 * System.out.println("right"); } else setWrongColor();
				 */

				String prev = mField.getText();
				setText(num, true);
				StepData temp = null;
				temp = new StepData(mId, prev, num);
				SudokuBoard.getInstance().addUndoSteps(temp);

				// }
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
		};
	}

	/**
	 * Initialize the grid
	 * 
	 * @param _size
	 *           size of the grid
	 * @param _id
	 *           grid id used for sudoku solver
	 */
	public void initBoardGrid(Dimension _size, int _id) {
		mField.setPreferredSize(_size);
		Font font = mField.getFont().deriveFont(Font.BOLD, FONT_POINTS);
		mField.setFont(font);
		setInitBGColor();
		mId = _id;

	}

	/**
	 * Return the cell id
	 * 
	 * @return cell id
	 */
	public int getId() {
		return mId;
	}

	/**
	 * Add mouse listener to the text filed
	 */
	public void addMouseAction() {
		mHasListener = true;
		mField.addMouseListener(mMouseListener);
	}

	/**
	 * Remove mouse listener to the text filed
	 */
	public void removeMouseAction() {
		mHasListener = false;
		mField.removeMouseListener(mMouseListener);
	}

	/**
	 * Clear the grid
	 */
	public void clear() {
		if (mHasListener)
			removeMouseAction();
		mField.setForeground(new Color(0, 0, 0));
		mField.setBackground(new Color(255, 255, 255));
		mField.setText("");
		SudokuBoard.getInstance().getManager().getSolved().getCell(mId).clearCell();
	}

	/**
	 * Set the text of the grid
	 * 
	 * @param _text
	 *           the string of input number
	 */
	public void setText(String _text, boolean _redo) {
		// System.out.println(_text);
		String temp = _text;
		if (_redo) {
			if (!mHasListener)
				addMouseAction();
			setInitBGColor();
			if (temp.isEmpty()
					|| SudokuBoard.getInstance().getSolver().isCellLegal(Integer.parseInt(temp), mId)) {
				setRightColor();
				mIsFinished = true;
				// System.out.println("right");
			} else {
				// System.out.println("wrong");
				mIsFinished = false;
				setWrongColor();
			}
			if (SudokuBoard.getInstance().isFinished()) {
				// System.out.println("highscore");
				// int time = SudokuBoard.getInstance().getRecordTime();
				// ScoreManager.getInstance().isHighScore(time);
			}
			// System.out.println("continue");
		} else {
			if (_text.equals("0")) {
				temp = "";
				if (!mHasListener)
					addMouseAction();
				setInitBGColor();
			} else {
				setLockColor();
				mIsFinished = true;
			}

		}

		mField.setText(temp);
	}

	/**
	 * Set the initial background color
	 */
	public void setInitBGColor() {
		mField.setBackground(new Color(255, 255, 255));
	}

	/**
	 * Change the color of the text and grid that can be changed
	 */
	public void setLockColor() {
		mField.setForeground(new Color(0, 0, 0));
		mField.setBackground(new Color(200, 200, 200));
	}

	/**
	 * Change the text color to green
	 */
	public void setRightColor() {
		mField.setForeground(new Color(22, 200, 70));
	}

	/**
	 * Change the text color to red
	 */
	public void setWrongColor() {
		mField.setForeground(new Color(255, 0, 0));
	}

	/**
	 * return the JTextfiled and add it to the panel of sub-board
	 * 
	 * @return JTexfiled component
	 */
	public JTextField getField() {
		return mField;
	}

	/**
	 * Check the cell is filled in correctly
	 * 
	 * @return true if the cell is filled in correctly
	 */
	public boolean isFinished() {
		return mIsFinished;
	}

	private boolean mIsFinished;
	private boolean mHasListener;
	private MouseListener mMouseListener;
	private int mId;
	private Color mSelected;
	private SudokuCellEditor mEditor;
	private JTextField mField;
	private static final float FONT_POINTS = 15;

}
