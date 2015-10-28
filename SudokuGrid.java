import java.util.ArrayList;

/**
 * The SudokuGrid class contains the information about the board and all the
 * cells.
 * 
 * @author Thomas Tsang
 * 
 */
public class SudokuGrid {

	/**
	 * Constructor for the SudokuGrid. Initialises an empty board.
	 */
	public SudokuGrid() {
		rows = new ArrayList<CellGroup>(CELL_GROUP_SIZE);
		columns = new ArrayList<CellGroup>(CELL_GROUP_SIZE);
		boxes = new ArrayList<CellGroup>(CELL_GROUP_SIZE);
		initGrid();
	}

	/**
	 * Makes a grid according to a string that was provided.
	 * 
	 * @param sudokuBoard
	 *           The string of all numbers in a puzzle in order of indexes.
	 */
	public void make(String sudokuBoard) {
		for (int i = 0; i < 81; i++) {
			int currentElement = Character.getNumericValue(sudokuBoard.charAt(i));
			Cell currentCell = getRowWithCell(i).getByIndex(i);
			currentCell.setCell(currentElement);
			currentCell.resetValidMoves();
		}

		for (int i = 0; i < 81; i++) {
			Integer currentElement = Character.getNumericValue(sudokuBoard.charAt(i));
			if (!currentElement.equals(0)) {
				removeMove(currentElement, i);
			}
		}
	}

	/**
	 * Removes the valid moves of the cells that are in the same row, box or
	 * column of the element provided.
	 * 
	 * @param currentElement
	 *           The element that was just assigned.
	 * @param i
	 *           The index at which the element was assigned.
	 */
	public void removeMove(Integer currentElement, int i) {
		getRowWithCell(i).removeMove(currentElement);
		getColumnWithCell(i).removeMove(currentElement);
		getBoxWithCell(i).removeMove(currentElement);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		String s = "";
		for (CellGroup row : rows) {
			s += row.toString();
		}

		return s;
	}

	/**
	 * @return The <code>boxes</code> attribute of the class.
	 */
	public ArrayList<CellGroup> getBoxes() {
		return boxes;
	}

	/**
	 * Sets the valid moves of the cells in the same box, row and column of a
	 * recently cleared cell.
	 * 
	 * @param currentElement
	 *           The element that was just cleared.
	 * @param i
	 *           The index at which the element was cleared.
	 */
	public void setMove(int currentElement, int i) {
		getRowWithCell(i).setMove(currentElement);
		getColumnWithCell(i).setMove(currentElement);
		getBoxWithCell(i).setMove(currentElement);

	}

	/**
	 * Finds the next cell with the least amount of possible moves.
	 * 
	 * @return The cell with the least amount of possible moves.
	 */
	public Cell nextCell() {
		Cell leastMovesCell = nextFreeCell();
		for (CellGroup row : rows) {
			for (Cell cell : row) {
				if (cell.getElement() == BLANK && cell.numValidMoves() < leastMovesCell.numValidMoves()) {
					leastMovesCell = cell;
				}
			}
		}
		return leastMovesCell;
	}

	/**
	 * Return whether setting a cell with a specific element is legal.
	 * 
	 * @param tempCell
	 *           The cell to be set.
	 * @param element
	 *           The element it will be set to.
	 * @return True if the setting the cell would be legal, otherwise false.
	 */
	public boolean isLegal(Cell tempCell, int element) {
		int i = tempCell.getIndex();
		CellGroup rowWithCell = getRowWithCell(i);
		CellGroup boxWithCell = getColumnWithCell(i);
		CellGroup columnWithCell = getBoxWithCell(i);

		return rowWithCell.isLegal(element, tempCell.getIndex())
				&& boxWithCell.isLegal(element, tempCell.getIndex())
				&& columnWithCell.isLegal(element, tempCell.getIndex());
	}

	/**
	 * Gets the next/first free cell in the grid according to index.
	 * 
	 * @return The first free cell it sees or null if there are none.
	 */
	public Cell nextFreeCell() {
		for (CellGroup row : rows) {
			if (row.hasElement(BLANK)) {
				return row.get(BLANK);
			}
		}
		// System.out.println(nextCell);
		return null;
	}

	/**
	 * Checks if the grid contains no empty spaces.
	 * 
	 * @return True if there are no empty spaces, otherwise false.
	 */
	public boolean isFilled() {
		boolean filled = true;
		for (CellGroup row : rows) {
			if (row.hasElement(BLANK)) {
				filled = false;
			}
		}
		return filled;
	}

	/**
	 * Determines if putting an element at an index is legal and if it is sets
	 * the element, otherwise clears it.
	 * 
	 * @param element
	 *           The element to be set.
	 * @param index
	 *           The index it will be set at.
	 * @return if the it was legal.
	 */
	public boolean isCellLegal(Integer element, int index) {
		Cell currentCell = getCell(index);
		boolean legal = isLegal(currentCell, element);
		if (legal) {
			currentCell.setCell(element);
		} else {
			currentCell.clearCell();
		}
		return legal;
	}

	/**
	 * Resets the board to an empty board.
	 * 
	 */
	public void resetBoard() {
		make(EMPTY_BOARD);

	}

	/**
	 * Removes any conflicting cells with the cell provided. As the cell provided
	 * is guaranteed to be correct.
	 * 
	 * @param currentCell
	 *           A cell which could be conflicted with.
	 */
	public void removeIllegalCells(Cell currentCell) {
		int i = currentCell.getIndex();
		CellGroup rowWithCell = getRowWithCell(i);
		CellGroup boxWithCell = getColumnWithCell(i);
		CellGroup columnWithCell = getBoxWithCell(i);
		rowWithCell.removeIllegalCells(currentCell);
		boxWithCell.removeIllegalCells(currentCell);
		columnWithCell.removeIllegalCells(currentCell);

	}

	/**
	 * Gets a cell according to its index.
	 * 
	 * @param i
	 *           index of the cell needed.
	 * @return The cell at that index.
	 */
	public Cell getCell(int i) {
		return getRowWithCell(i).getByIndex(i);
	}

	/**
	 * initialises the Grid.
	 */
	private void initGrid() {
		for (int i = 0; i < 9; i++) {
			rows.add(new CellGroup());
			columns.add(new CellGroup());
			boxes.add(new CellGroup());
		}
		for (int i = 0; i < 81; i++) {
			Cell currentCell = new Cell(0, i);
			getRowWithCell(i).addCell(currentCell);
			getColumnWithCell(i).addCell(currentCell);
			getBoxWithCell(i).addCell(currentCell);
		}
	}

	/**
	 * Gets the row containing the index.
	 * 
	 * @param i
	 *           The index.
	 * @return The row which contains the index.
	 */
	private CellGroup getRowWithCell(int i) {
		return rows.get(i / CELL_GROUP_SIZE);
	}

	/**
	 * Gets the column containing the index
	 * 
	 * @param i
	 *           The index.
	 * @return The column which contains the index.
	 */
	private CellGroup getColumnWithCell(int i) {
		return columns.get(i % CELL_GROUP_SIZE);
	}

	/**
	 * Gets the box containing the index.
	 * 
	 * @param i
	 *           The index.
	 * @return The box which contains the index.
	 */
	private CellGroup getBoxWithCell(int i) {
		return boxes.get(((i / 27) * 3) + ((i % 9) / 3));
	}

	private ArrayList<CellGroup> rows;
	private ArrayList<CellGroup> columns;
	private ArrayList<CellGroup> boxes;
	private final int CELL_GROUP_SIZE = 9;
	private final int BLANK = 0;
	private final String EMPTY_BOARD = "000000000" + "000000000" + "000000000" + "000000000"
			+ "000000000" + "000000000" + "000000000" + "000000000" + "000000000";

}
