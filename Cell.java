import java.util.ArrayList;
import java.util.Random;

/**
 * The individual cells of a sudoku grid.
 * 
 * @author Thomas Tsang
 * 
 */
public class Cell {
	/**
	 * Constructor for the Cell class.
	 * 
	 * @param element
	 *           The initial element of the Cell.
	 * @param index
	 *           The index of the Cell.
	 */
	public Cell(int element, int index) {
		this.index = index;
		this.element = element;
		validMoves = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			validMoves.add(i);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		String s = element.toString();
		return s;
	}

	/**
	 * @return The <code>element</code> attribute of the class.
	 */
	public int getElement() {
		return element;
	}

	/**
	 * 
	 * @return The <code>index</code> attribute of the class.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the element of the class to the one provided.
	 * 
	 * @param element
	 *           Element to be set.
	 */
	public void setCell(int element) {
		this.element = element;
	}

	/**
	 * Finds the number of valid moves for this cell.
	 * 
	 * @return The number of valid moves for the cell.
	 */
	public int numValidMoves() {
		return validMoves.size();
	}

	/**
	 * @return All possible valid moves for this cell.
	 */
	public ArrayList<Integer> getValidMoves() {
		return new ArrayList<Integer>(validMoves);
	}

	/**
	 * Sets the validMoves attribute to the one provided.
	 * 
	 * @param validMoves
	 *           The valid moves to be set.
	 */
	public void setValidMoves(ArrayList<Integer> validMoves) {
		this.validMoves = validMoves;
	}

	/**
	 * Resets the cell to contains all the valid moves i.e. The cell can take any
	 * move.
	 */
	public void resetValidMoves() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			temp.add(i);
		}
		validMoves = temp;
	}

	/**
	 * Clears the element in the cell.
	 */
	public void clearCell() {
		this.element = 0;

	}

	/**
	 * Returns a random valid move for this cell.
	 * 
	 * @return A valid move.
	 */
	public int getRandomMove() {
		Random randomGenerator = new Random();
		// System.out.println(numValidMoves());
		Integer move;
		if (numValidMoves() != 1) {
			move = validMoves.get(randomGenerator.nextInt(numValidMoves() - 1));
		} else {
			move = validMoves.get(0);
		}
		validMoves.remove(move);
		return move;

	}

	/**
	 * Clears the valid moves of this cell.
	 */
	public void clearValidMoves() {
		validMoves.clear();

	}

	/**
	 * Remove a move if it is no longer valid.
	 * 
	 * @param currentElement
	 *           The move that is no longer valid.
	 * @return Whether it was removed or not.
	 */
	public boolean removeMove(Integer currentElement) {
		// System.out.println(validMoves + " " + index);
		return (validMoves.remove(currentElement));

	}

	/**
	 * Sets a move that is valid again.
	 * 
	 * @param currentElement
	 *           The move which is valid again.
	 */
	public void setMove(Integer currentElement) {
		if (!validMoves.contains(currentElement)) {
			validMoves.add(currentElement);
			// System.out.println(validMoves);
		}

	}

	private Integer index;
	private Integer element;
	private ArrayList<Integer> validMoves;
}
