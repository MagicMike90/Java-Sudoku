import java.util.ArrayList;
import java.util.Random;

/**
 * The SudokuGenerator will generate random boards depending on the difficulty.
 * 
 * @author Thomas Tsang
 * 
 */
public class SudokuGenerator {
	/**
	 * Constructor for SudokuGenerator class.
	 */

	public SudokuGenerator() {
		toBeGenerated = new SudokuGrid();
	}

	/**
	 * Generates a board depending on the difficulty provided.
	 * 
	 * @param difficulty
	 *           difficulty of the board to be generated.
	 * @return The board that was generated.
	 */
	public SudokuGrid generateBoard(int difficulty) {
		toBeGenerated.resetBoard();
		// System.out.println(toBeGenerated.toString());
		randomFill();
		// System.out.println(toBeGenerated.toString());
		emptyRandom(difficulty);
		// System.out.println(toBeGenerated.toString());
		return toBeGenerated;
	}

	/**
	 * A helper function in the generation that randomly empties the cells to
	 * make a puzzle. Provides a unique solution.
	 * 
	 * @param difficulty
	 *           difficulty of the board to be generated.
	 */

	private void emptyRandom(int difficulty) {
		int randomFactor = 0;
		if (difficulty == EASY) {
			randomFactor = 20;
		} else if (difficulty == MEDIUM) {
			randomFactor = 40;
		} else if (difficulty == HARD) {
			randomFactor = 50;
		}
		Random r = new Random();
		SudokuSolver s = new SudokuSolver();
		int numberOfBlanks = 0;
		while (numberOfBlanks < randomFactor) {
			for (int i = 0; i < 81; i++) {
				Cell currentCell = toBeGenerated.getCell(i);
				int currentElement = currentCell.getElement();
				if (r.nextInt(81) < randomFactor && currentElement != 0
						&& numberOfBlanks < randomFactor) {

					toBeGenerated.setMove(currentElement, i);
					currentCell.setCell(0);
					SudokuGrid temp = new SudokuGrid();
					temp.make(toBeGenerated.toString());
					if (s.getNumSolution(temp) > 1) {
						currentCell.setCell(currentElement);
						toBeGenerated.removeMove(currentElement, i);
					} else {
						numberOfBlanks++;
					}
				}
			}

		}
	}

	/**
	 * A helper recursive function that randomly fills an empty board.
	 * 
	 * @return True if the board was filled, otherwise false.
	 */
	private boolean randomFill() {
		if (toBeGenerated.isFilled()) {
			return true;
		}

		Cell currentCell = toBeGenerated.nextFreeCell();
		ArrayList<Integer> originalValidMoves = currentCell.getValidMoves();
		while (currentCell.numValidMoves() > 0) {
			int testElement = currentCell.getRandomMove();
			if (toBeGenerated.isLegal(currentCell, testElement)) {
				currentCell.setCell(testElement);
				if (randomFill()) {
					return true;
				} else {
					currentCell.clearCell();
				}
			}
		}
		currentCell.setValidMoves(originalValidMoves);
		return false;
	}

	private SudokuGrid toBeGenerated;
	public final static int EASY = 0;
	public final static int MEDIUM = 1;
	public final static int HARD = 2;

}
