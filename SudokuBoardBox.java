import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Sudoku box class used for manager and create sudoku board cell
 * 
 * @author Chengwei
 * 
 */
public class SudokuBoardBox {
	public SudokuBoardBox() {
		GRID_SIZE = new Dimension(50, 50);
		mSmallBoard = new JPanel();
		mBoardGrids = new ArrayList<SudokuBoardCell>();
	}

	/**
	 * Initialize the small board
	 * 
	 * @param _layout
	 *           the layout of the box
	 * @param _width
	 *           the width of the box
	 */
	void initSmallBoard(GridLayout _layout, int _width) {
		mSmallBoard.setLayout(_layout);
		mSmallBoard.setBorder(BorderFactory.createLineBorder(Color.black, _width));
	}

	/**
	 * Create a box of the sudoku board
	 * 
	 * @param _nums
	 *           the numbers going to fill into the grid
	 */
	public void createSmallBoard(CellGroup _nums) {

		for (Cell c : _nums.getCellList()) {
			SudokuBoardCell temp = new SudokuBoardCell();
			temp.initBoardGrid(GRID_SIZE, c.getIndex());
			mBoardGrids.add(temp);
			mSmallBoard.add(temp.getField());
		}

	}

	/**
	 * Set the input number to single grid
	 * 
	 * @param string
	 *           the input number
	 */
	public void setCellNumber(String _cellGroup) {
		for (int i = 0; i < mBoardGrids.size(); i++) {
			// System.out.println(_nums.getCellList().get(i).toString());
			mBoardGrids.get(i).setText(String.valueOf(_cellGroup.charAt(i)), false);
		}
	}

	public ArrayList<SudokuBoardCell> getBoardCell() {
		return mBoardGrids;
	}

	/**
	 * Empty the box
	 */
	public void clear() {
		for (int i = 0; i < mBoardGrids.size(); i++) {
			mBoardGrids.get(i).clear();
		}
	}

	/**
	 * Return the panel for add it in the main board panel
	 * 
	 * @return JPanel
	 */
	public JPanel getPanel() {
		return mSmallBoard;
	}

	private Dimension GRID_SIZE;
	private JPanel mSmallBoard;
	private ArrayList<SudokuBoardCell> mBoardGrids;

}
