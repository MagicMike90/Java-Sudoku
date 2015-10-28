/**
 * A class used for storing the user action step
 * @author Chengwei
 *
 */
public class StepData {
	/**
	 * Constructor
	 * @param _id the grid id
	 * @param _prev previous input string
	 * @param _current current input string
	 */
	public StepData(int _id, String _prev, String _current)
	{
		mId = _id;
		mPrev = _prev;
		mCurrent = _current;
	}
	/**
	 * get previous input string
	 * @return previous input string
	 */
	public String getPreviousText()
	{
		return mPrev;
	}
	/**
	 * get current input string
	 * @return current input string
	 */
	public String getCurentText()
	{
		return mCurrent;
	}
	/**
	 * get grid id
	 * @return grid id
	 */
	public int getId()
	{
		return mId;
	}
	private String mPrev;
	private String mCurrent;
	private int mId;
}
