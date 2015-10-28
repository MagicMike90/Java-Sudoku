import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The main board class, manage the whole sudoku board
 * 
 * @author Chengwei
 * 
 */
public class SudokuBoard {

	/**
	 * Board class constructor
	 */
	public SudokuBoard() {

		mSize = 9;
		mBoard = new JPanel();
		mBoard.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH));
		mBoard.setLayout(new GridLayout(3, 3));

		mBoxes = new ArrayList<SudokuBoardBox>();
		mUndoSteps = new Stack<StepData>();
		mRedoSteps = new Stack<StepData>();
		mManager = new SudokuManager();
		// create the boxes and grid of sudoku board
		initBoard();

		mSubBoard = new ArrayList<String>();
		mResetBoard = new ArrayList<String>();
		difficulty = SudokuGenerator.EASY;
	}

	/**
	 * This is using a singleton pattern
	 * 
	 * @return the instance of the sudoku board
	 */
	public static SudokuBoard getInstance() {
		if (instance == null) {
			instance = new SudokuBoard();
		}
		return instance;
	}

	// Create the board with grids
	public void initBoard() {
		mGridToSolve = mManager.getCurrentGrid();
		// create 3x3 grid of sub-panels,
		// add a line border to each sub-panel
		// and add to main panel
		int smallSize = mSize / 3;
		mSmallBoard = new SudokuBoardBox[smallSize][smallSize];
		for (int i = 0; i < smallSize; i++) {
			for (int j = 0; j < smallSize; j++) {
				mSmallBoard[i][j] = new SudokuBoardBox();
				mSmallBoard[i][j].initSmallBoard(new GridLayout(3, 3), BORDER_WIDTH);
				mSmallBoard[i][j].createSmallBoard(mGridToSolve.getBoxes().get(3 * i + j));
				mBoard.add(mSmallBoard[i][j].getPanel());
				mBoxes.add(mSmallBoard[i][j]);
			}
		}
	}

	/**
	 * create a new board
	 */
	public void newBoard() {
		// clear the board
		clearBoard();
		mResetBoard.clear();
		// Generate the number
		SudokuBoard.getInstance().getManager().newBoard(difficulty);

		mGridToSolve = mManager.getCurrentGrid();

		// System.out.print("init: "+mResetBoard.size());
		setBoard();
	}

	/**
	 * set a new board
	 */
	public void setBoard() {
		for (CellGroup c : mGridToSolve.getBoxes()) {
			mResetBoard.add(c.toString());
		}
		// clearBoard();
		ExtractBoardNum(mGridToSolve.getBoxes());
		int smallSize = mSize / 3;
		for (int i = 0; i < smallSize; i++) {
			for (int j = 0; j < smallSize; j++) {

				mSmallBoard[i][j].setCellNumber(mSubBoard.get(3 * i + j));
			}
		}
	}

	/**
	 * Copy the numbers of sudoku solver to board number list
	 * 
	 * @param _cellGroup
	 */
	public void ExtractBoardNum(ArrayList<CellGroup> _cellGroup) {

		for (CellGroup c : mGridToSolve.getBoxes()) {
			mSubBoard.add(c.toString());
			mResetBoard.add(c.toString());
		}

	}

	/**
	 * create the custom board
	 */
	public void createCustomBoard() {
		mManager.resetGrids();
		mGridToSolve = mManager.getCurrentGrid();
		clearBoard();
		// mResetBoard.clear();
		turnOnAction();
	}

	/**
	 * Turn on all mouse action for small grids
	 */
	public void turnOnAction() {
		for (SudokuBoardBox b : mBoxes) {
			for (SudokuBoardCell c : b.getBoardCell()) {
				c.addMouseAction();
			}
		}
	}

	/**
	 * Perform the hint action
	 */
	public void hintAction() {
		Cell temp = mManager.hint();
		// setBoard();

		if (temp != null) {
			for (SudokuBoardBox b : mBoxes) {
				for (SudokuBoardCell c : b.getBoardCell()) {
					if (c.getId() == temp.getIndex()) {
						c.setText(Integer.toString(temp.getElement()), true);
					}
				}
			}
		}

	}

	/**
	 * Play the custom board
	 */
	public void playCustomBoard() {
		ExtractBoardNum(mGridToSolve.getBoxes());
		mManager.solveCurrentGrid();
		setBoard();
	}

	/**
	 * Check if the game is finished
	 */
	public boolean isFinished() {
		/*
		 * for (SudokuBoardBox b : mBoxes) { for (SudokuBoardCell c :
		 * b.getBoardCell()) { if (!c.isFinished()){ //System.out.println(cout);
		 * return false; }
		 * 
		 * } }
		 */

		return mGridToSolve.isFilled();
	}

	/**
	 * Reset the board
	 */
	public void clearBoard() {
		mUndoSteps.clear();
		mRedoSteps.clear();
		mSubBoard.clear();
		mResetBoard.clear();

		int smallSize = mSize / 3;
		for (int i = 0; i < smallSize; i++) {
			for (int j = 0; j < smallSize; j++) {
				mSmallBoard[i][j].clear();
			}
		}
	}

	/**
	 * Re-do the previous steps
	 */
	public void undoBoard() {
		// System.out.println("undo");
		if (!mUndoSteps.isEmpty()) {
			StepData temp = mUndoSteps.pop();
			addRedoSteps(temp);
			for (SudokuBoardBox b : mBoxes) {
				for (SudokuBoardCell c : b.getBoardCell()) {
					if (c.getId() == temp.getId()) {
						mGridToSolve.getCell(temp.getId()).clearCell();
						c.setText(temp.getPreviousText(), true);
					}
				}
			}
		}
	}

	/**
	 * Get undo steps
	 * 
	 * @return undo steps
	 */
	public void addUndoSteps(StepData _d) {

		mUndoSteps.add(_d);
	}

	/**
	 * redo the last steps
	 */
	public void redoBoard() {
		if (!mRedoSteps.isEmpty()) {
			// System.out.println("redo");
			StepData temp = mRedoSteps.pop();
			addUndoSteps(temp);
			for (SudokuBoardBox b : mBoxes) {
				for (SudokuBoardCell c : b.getBoardCell()) {
					if (c.getId() == temp.getId())
						// mGridToSolve.getCell(temp.getId()).clearCell();
						c.setText(temp.getCurentText(), true);
				}
			}
		}
	}

	/**
	 * Add the step for the rodo
	 * 
	 * @param _d
	 *           data contains previous step info
	 */
	public void addRedoSteps(StepData _d) {
		mRedoSteps.add(_d);
	}

	/**
	 * Reset the board
	 */
	public void resetBoard() {
		// System.out.println("mResetBoard " + mResetBoard.size());
		int smallSize = mSize / 3;
		for (int i = 0; i < smallSize; i++) {
			for (int j = 0; j < smallSize; j++) {

				mSmallBoard[i][j].clear();
				mSmallBoard[i][j].setCellNumber(mResetBoard.get(3 * i + j));
			}
		}
		mUndoSteps.clear();
		mRedoSteps.clear();
	}

	/**
	 * Display the board
	 */
	public void display() {
		JFrame frame = new JFrame("Sudoku Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mBoard);
		frame.pack();
		// frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Display the anser of the sudoku
	 */
	public void displayAnswer() {
		clearBoard();

		// Generate the number

		mGridToSolve = mManager.getSolved();
		// Get the numbers from sudoku solver
		setBoard();
	}

	/**
	 * Get sudoku solver use in other part of the program
	 * 
	 * @return sudoku solver
	 */
	public SudokuGrid getSolver() {
		return mGridToSolve;
	}

	/**
	 * Return the main board panel, it will be added in the GUI
	 * 
	 * @return main board panel
	 */
	public JPanel getPanel() {
		return mBoard;
	}

	/**
	 * Get sudoku solver manager
	 * 
	 * @return sudoku solver manager
	 */
	public SudokuManager getManager() {
		return mManager;
	}

	/**
	 * Set difficulty
	 * 
	 * @param difficulty
	 *           t
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;

	}

	/**
	 * record the time player played
	 * 
	 * @param _time
	 *           the time player played
	 */
	public void recourdTime(int _time) {
		mRecourdedTime = _time;
		// System.out.println(mRecourdedTime);
	}

	/**
	 * get the recorded time player played
	 * 
	 * @param _time
	 *           the time player played
	 */
	public int getRecordTime() {
		return mRecourdedTime;
	}

	private int mRecourdedTime;
	private int difficulty;
	private SudokuManager mManager;
	private Stack<StepData> mUndoSteps;
	private Stack<StepData> mRedoSteps;
	private static SudokuBoard instance;
	private SudokuGrid mGridToSolve;
	private int mSize;
	private JPanel mBoard;
	private ArrayList<SudokuBoardBox> mBoxes;
	private SudokuBoardBox[][] mSmallBoard;
	private static final int BORDER_WIDTH = 3;
	private ArrayList<String> mSubBoard;
	private ArrayList<String> mResetBoard;

	// private ArrayList<String> mTemp;

}
