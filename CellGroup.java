import java.util.ArrayList;
import java.util.Iterator;

/**
 * The CellGroup contains a group of 9 cells that represents a box, row or
 * columns.
 * 
 * @author Thomas Tsang
 * 
 */
public class CellGroup implements Iterable<Cell> {

	/**
	 * The Constructor of the CellGroup class.
	 */
	public CellGroup() {
		cells = new ArrayList<Cell>();
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		String s = "";
		for (Cell cell : cells) {
			s += cell.toString();
		}
		return s;
	}

	/**
	 * Remove cells that conflict with the provided cell.
	 * 
	 * @param currentCell
	 *           The cell that is guranteed to be right.
	 */
	public void removeIllegalCells(Cell currentCell) {
		for (Cell cell : cells) {
			if (cell != currentCell && cell.getElement() == currentCell.getElement()) {
				cell.clearCell();
			}
		}

	}

	/**
	 * 
	 * @return The cells attribute of this class.
	 */
	public ArrayList<Cell> getCellList() {
		return cells;
	}

	/**
	 * Finds if the cell group contains a certain element.
	 * 
	 * @param element
	 *           The element that needs to be found.
	 * @return True if it contains the element, otherwise false.
	 */
	public boolean hasElement(int element) {
		for (Cell tempCell : cells) {
			if (tempCell.getElement() == element) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the cell that contains an element from the group.
	 * 
	 * @param element
	 * @return The cell that contains the element.
	 */
	public Cell get(int element) {
		for (Cell temp : cells) {
			if (temp.getElement() == element) {
				return temp;
			}
		}
		return null;
	}

	/**
	 * Finds the cell with index in the group.
	 * 
	 * @param index
	 *           The index of the cell needed.
	 * @return The cell with the index.
	 */
	public Cell getByIndex(int index) {
		for (Cell temp : cells) {
			if (temp.getIndex() == index) {
				return temp;
			}
		}
		return null;
	}

	/**
	 * Finds if the group contains a certain cell.
	 * 
	 * @param tempCell
	 *           The cell that needs to be found.
	 * @return True if the cell is contained in the group, false otherwise.
	 */
	public boolean contains(Cell tempCell) {
		// System.out.println(toString());
		return cells.contains(tempCell);
	}

	/**
	 * Checks if adding an element at this index is legal.
	 * 
	 * @param element
	 *           The element to be added.
	 * @param i
	 *           The index it will be added at.
	 * @return
	 */
	public boolean isLegal(int element, int i) {
		for (Cell tempCell : cells) {
			if (tempCell.getElement() == element && !(tempCell.getIndex() == i)) {
				return false;
			}
		}
		return true;

		// return !hasElement(element);
	}

	/**
	 * Adds a cell to the cellGroup.
	 * 
	 * @param currentCell
	 *           The cell to be added.
	 */
	public void addCell(Cell currentCell) {
		cells.add(currentCell);

	}

	/**
	 * Removes a valid move from all the cells in this group.
	 * 
	 * @param currentElement
	 *           The element to be removed.
	 * @return True if it was removed, false otherwise.
	 */
	public boolean removeMove(Integer currentElement) {
		boolean alreadyRemoved = false;
		for (Cell cell : cells) {
			if (!cell.removeMove(currentElement)) {
				alreadyRemoved = true;
			}
		}
		return alreadyRemoved;
	}

	/**
	 * Sets a valid move from all the cells in this group.
	 * 
	 * @param currentElement
	 *           The element that is a valid move.
	 */
	public void setMove(int currentElement) {
		for (Cell cell : cells) {
			cell.setMove(currentElement);
		}

	}

	@Override
	public Iterator<Cell> iterator() {
		Iterator<Cell> it = new Iterator<Cell>() {
			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < (cells.size() - 1) && cells.get(currentIndex) != null;
			}

			@Override
			public Cell next() {
				return cells.get(currentIndex++);
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub

			}

		};
		return it;
	}

	private ArrayList<Cell> cells;

}
