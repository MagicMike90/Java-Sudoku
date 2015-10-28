/**
 * A class used for storing user data
 * @author Chengwei
 *
 */
public class UserData {
	/**
	 * UserData constructor
	 * @param _name user name
	 * @param _score user score
	 */
	public UserData(String _name, int _score)
	{
		mName =  _name;
		mScore = _score;
	}
	/**
	 * Set user name
	 * @param _name user name
	 */
	public void setName(String _name)
	{
		mName = _name;
	}
	/**
	 * Set user score
	 * @param _score user score
	 */
	public void setScore(int _score)
	{
		mScore = _score;
	}
	/**
	 * get user name.
	 * @return user name
	 */
	public String getName()
	{
		return mName;
	}
	/**
	 * get user score
	 * @return user score
	 */
	public int getScore()
	{
		return mScore;
	}
	/**
	 * Copy user data
	 * @param _data the data want to be copied
	 */
	public void copy(UserData _data)
	{
		mName = _data.getName();
		mScore = _data.getScore();
	}
	private String mName;
	private int mScore;
}
