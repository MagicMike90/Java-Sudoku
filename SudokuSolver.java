import java.util.ArrayList;

/**
 * The SudokuSolver class will solve a sudoku puzzle that was provided.
 * 
 * @author Thomas Tsang
 * 
 */
public class SudokuSolver {

	/**
	 * Constructor for SudokuSolver class.
	 * 
	 */

	public SudokuSolver() {
		gridToSolve = new SudokuGrid();
	}

	/**
	 * This function will solve the sudoku puzzle that was provided.
	 * 
	 * @param gridToSolve
	 *           The board that needs to be solved.
	 * @return True if the the puzzle has a solution otherwise false.
	 */

	public boolean solve(SudokuGrid gridToSolve) {
		this.gridToSolve = gridToSolve;
		// numSolution = 0;
		if (hasSolution()) {
			// System.out.println(gridToSolve.toString());
			return true;
		}
		return false;

	}

	/**
	 * A recursive helper function for the solver.
	 * 
	 * @return True if the the puzzle has a solution otherwise false.
	 */
	private boolean hasSolution() {
		if (gridToSolve.isFilled()) {
			return true;
		}
		Cell currentCell = gridToSolve.nextCell();
		ArrayList<Integer> originalValidMoves = currentCell.getValidMoves();
		for (Integer testElement : originalValidMoves) {
			if (gridToSolve.isLegal(currentCell, testElement)) {
				currentCell.setCell(testElement);
				gridToSolve.removeMove(currentCell.getElement(), currentCell.getIndex());
				if (hasSolution()) {
					return true;
				} else {
					gridToSolve.setMove(currentCell.getElement(), currentCell.getIndex());
					currentCell.clearCell();
				}
			}
		}
		currentCell.setValidMoves(originalValidMoves);
		return false;
	}

	/**
	 * Finds the number of solution of a board. Used to test uniqueness.
	 * 
	 * @param toBeGenerated
	 * @return The number of solutions on a board
	 */
	public int getNumSolution(SudokuGrid toBeGenerated) {
		gridToSolve = toBeGenerated;
		return findAllSolution(0);

	}

	/**
	 * Finds all the solution and keeps track of the number of solutions
	 * 
	 * @param numSolution
	 *           Number of solutions currently found
	 * @return The number of solutions found for this iteration of the board.
	 */
	public int findAllSolution(int numSolution) {

		if (gridToSolve.isFilled()) {
			return ++numSolution;
		}
		Cell currentCell = gridToSolve.nextCell();
		ArrayList<Integer> originalValidMoves = currentCell.getValidMoves();
		for (Integer testElement : originalValidMoves) {
			if (gridToSolve.isLegal(currentCell, testElement)) {
				currentCell.setCell(testElement);
				gridToSolve.removeMove(currentCell.getElement(), currentCell.getIndex());
				numSolution = findAllSolution(numSolution);
				if (numSolution > 1) {
					return numSolution;
				}
				gridToSolve.setMove(currentCell.getElement(), currentCell.getIndex());
				currentCell.clearCell();
			}
		}
		currentCell.setValidMoves(originalValidMoves);
		return numSolution;
	}

	private SudokuGrid gridToSolve;

}
