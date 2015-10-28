import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;


/**
 * High score manager used for loading, saving ,checking, the user data.
 * @author Chengwei
 *
 */
public class ScoreManager {


	/**
	 * ScoreManager constructor
	 */
	private ScoreManager() {
		mHighScores =  new Stack<UserData>();
		mSlots = 10;
		
        
        mScoreKeyboard = new ScoreKeyboard("High Score");
        mScoreKeyboard.setVisible(false);
		// Loading data from text file
		Load();
	}
	/**
	 * Singleton pattern return the instance of score manager
	 * @return instance of score manager
	 */
	public static ScoreManager getInstance() {
		if (instance == null) {
			instance = new ScoreManager();
		}
		return instance;
	}
	/**
	 * Loading the data form text file
	 */
	private void Load()
	{
		try {
			
			Scanner scanner = new Scanner(new File("score.txt"));
		    scanner.useDelimiter(System.getProperty("line.separator"));	
		    while (scanner.hasNext()) {
				
				String line = scanner.next();
				String[] temp = line.split("\\s+");
				
				String tempName = temp[0];
				int tempScore = Integer.parseInt(temp[1]);
				UserData data = new UserData(tempName, tempScore);
				mHighScores.add(data);
				
				System.out.println(line);
		    }
		   	scanner.close();
		  	} catch(IOException e){
	    		e.printStackTrace();
	    	}
		
		SortingData();
	}

	/**
	 * Sorting the high score
	 */
	private void SortingData()
	{

	   // ArrayList<UserData> temp = (ArrayList<UserData>) mHighScore.clone();
		UserData temp =  new UserData("",0);
	    for(int i=0; i< mHighScores.size();i++)
        {
            for(int j=i + 1; j< mHighScores.size(); j++)
            {
                if(mHighScores.get(i).getScore() > mHighScores.get(j).getScore())
                {
                	temp.copy(mHighScores.get(i));
                    //int temp = list[i];
                	mHighScores.get(i).copy(mHighScores.get(j));
                    //list[i] = list[j];
                	mHighScores.get(j).copy(temp);
                }
            }
        }
	}
	/**
	 * Check whether the input score is the high score or not
	 * @param _score the player have for current game
	 * @return if the new score is a high score, return true
	 */
	public boolean isHighScore(int _score)
	{
		for(UserData s : mHighScores)
		{
			if(_score < s.getScore())
			{
				mScoreKeyboard.setVisible(true);
				mHighScore = _score;

				return true;
			}
		}
		return false;
	}
	/**
	 * Saving the high score
	 */
	public void Save()
	{
		UserData newScore =  new UserData(mScoreKeyboard.getName(), mHighScore);
		System.out.println("getname:"+mScoreKeyboard.getName());
		mHighScores.add(newScore);
		//Sorting the score again
		SortingData();
		while(mHighScores.size() > mSlots) mHighScores.pop();

		try{
    		//String data = " This content will append to the end of the file";
 
    		File file =new File("score.txt");
 
    		//if file doesn't exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
    		//overwrite the file
    		FileWriter fileWritter = new FileWriter(file.getName(),false);
    	    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	    
    	    
    		for(UserData s : mHighScores){
	    	    String output = s.getName()+" " + Integer.toString(s.getScore());
	    	    //System.out.println(output);
	    	    bufferWritter.write(output);
	    	    bufferWritter.newLine();
    		}
    	    bufferWritter.close();
 
    	}catch(IOException e){
    		e.printStackTrace();
    	}
	}
	/**
	 * Get score list
	 * @return high score lists
	 */
	public Stack<UserData> getScore()
	{
		return mHighScores;
	}
	
	private static ScoreManager instance;
	private Stack<UserData> mHighScores;
	private int mHighScore;
	private int mSlots;
	private ScoreKeyboard mScoreKeyboard;
}
