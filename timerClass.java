//this class stores the time for use as a timer
public class timerClass {

	private static int counter;


	public int tick(){	//returns the time counted so far
		return counter;
	}

	public timerClass(){ //sets the timer to 0
		counter = 0;
	}

	public static void decCounter(){ //decrements the counter
		counter--;
	}

	public static void incCounter(){//increments the counter
		counter++;;
	}

}

