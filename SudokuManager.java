/**
 * The SudokuManager class manages the grids that are being used by the current
 * boards. It handles the generation and solving of the boards while also
 * supplying hints when the board is being played.
 * 
 * @author Thomas Tsang
 * 
 */

public class SudokuManager {

	/**
	 * Constructor for SudokuManager Class. Initialises the solver and generator.
	 */
	public SudokuManager() {
		s = new SudokuSolver();
		g = new SudokuGenerator();
		solved = new SudokuGrid();
		currentGrid = new SudokuGrid();
	}

	/**
	 * Generates a random board depending on the difficulty given.
	 * 
	 * @param difficulty
	 *           The difficulty of the board that needs to be generated.
	 * 
	 * @return The board that was generated.
	 */
	public SudokuGrid newBoard(int difficulty) {
		currentGrid = g.generateBoard(difficulty);
		solveCurrentGrid();
		return currentGrid;
	}

	/**
	 * Solves the current board that is being played. However if the player makes
	 * a wrong move and there is no solution it will use a previous solved board.
	 * 
	 */
	public void solveCurrentGrid() {
		SudokuGrid temp = new SudokuGrid();
		temp.make(currentGrid.toString());
		if (s.solve(temp)) {
			solved = temp;
			// System.out.println("has Solution");
		}
		// System.out.println(solved);
	}

	/**
	 * Resets the current board and the solution.
	 * 
	 */

	public void resetGrids() {
		currentGrid.resetBoard();
		solved.resetBoard();
	}

	/**
	 * Provides a hint for the player by altering the current board. It will
	 * remove any cells that conflicts with the solution.
	 * 
	 * @return The cell that the hint was inputted into.
	 */
	public Cell hint() {
		// solveCurrentGrid();
		for (int i = 0; i < 81; i++) {
			Cell currentCell = currentGrid.getCell(i);
			Cell solvedCell = solved.getCell(i);
			if (currentCell.getElement() != solvedCell.getElement()) {
				currentCell.setCell(solvedCell.getElement());
				currentGrid.removeIllegalCells(currentCell);
				return currentCell;
			}

		}
		return null;
	}

	/**
	 * 
	 * @return The currentGrid attribute of the class.
	 */
	public SudokuGrid getCurrentGrid() {
		return currentGrid;
	}

	/**
	 * 
	 * @return The solved attribute of the class.
	 */

	public SudokuGrid getSolved() {
		solveCurrentGrid();
		return solved;
	}

	private SudokuSolver s;
	private SudokuGenerator g;
	private SudokuGrid solved;
	private SudokuGrid currentGrid;
}
